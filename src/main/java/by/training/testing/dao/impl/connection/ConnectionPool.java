package by.training.testing.dao.impl.connection;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public final class ConnectionPool {

    private static final int POOL_SIZE = 5;
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);

    private BlockingQueue<Connection> connectionQueue;
    private BlockingQueue<Connection> givenAwayConQueue;

    private String driverName;
    private String url;
    private String user;
    private String password;
    private int poolSize;

    private ConnectionPool(){
        DBResourceManager dbResourceManager = DBResourceManager.getInstance();
        driverName = dbResourceManager.getValue(DBParameter.DB_DRIVER);
        url = dbResourceManager.getValue(DBParameter.DB_URL);
        user = dbResourceManager.getValue(DBParameter.DB_USER);
        password = dbResourceManager.getValue(DBParameter.DB_PASSWORD);

        try {
            poolSize = Integer.parseInt(dbResourceManager.getValue(DBParameter.DB_POOL_SIZE));
        }
        catch (NumberFormatException e) {
            poolSize = POOL_SIZE;
        }
    }

    private static final ConnectionPool instance = new ConnectionPool();

    public static ConnectionPool getInstance() {
        return instance;
    }

    public void initParams(String driverName, String url, String user, String password, int poolSize){
        this.driverName = driverName;
        this.url = url;
        this.user = user;
        this.password = password;

        try {
            this.poolSize = poolSize;
        }
        catch (NumberFormatException e) {
            this.poolSize = POOL_SIZE;
        }
    }

    public void initPoolData() throws ConnectionPoolException {
        try {
            Class.forName(driverName);
            givenAwayConQueue = new ArrayBlockingQueue<>(poolSize);
            connectionQueue = new ArrayBlockingQueue<>(poolSize);

            for (int i = 0; i < poolSize; i++) {
                Connection connection = DriverManager.getConnection(url, user, password);
                connection.setAutoCommit(true);
                connectionQueue.add(connection);
            }
        } catch (SQLException e) {
            throw new ConnectionPoolException("SQL Exception in connection pool", e);
        } catch (ClassNotFoundException e) {
            throw new ConnectionPoolException("Can't find database driver class", e);
        }
    }

    public Connection takeConnection() throws ConnectionPoolException {
        Connection connection;
        try {
            connection = connectionQueue.take();
            givenAwayConQueue.add(connection);
        }
        catch (InterruptedException e) {
            throw new ConnectionPoolException("Error connecting to datasource", e);
        }
        return connection;
    }

    public void returnConnection(Connection connection) {
        if(givenAwayConQueue.remove(connection))
            connectionQueue.add(connection);
    }

    public void dispose() {
        try {
            closeConnectionQueue(givenAwayConQueue);
            closeConnectionQueue(connectionQueue);
        }
        catch (SQLException e) {
            LOGGER.debug(e);
        }
    }

    private void closeConnectionQueue(Queue<Connection> queue) throws SQLException {
        Connection connection;
        while((connection = connectionQueue.poll()) != null) {
            if(!connection.getAutoCommit())
                connection.commit();
            connection.close();
        }
    }
}
