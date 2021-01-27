package dao.impl.connection;

import java.util.ResourceBundle;

public class DBResourceManagerTest {

    private static final DBResourceManagerTest instance = new DBResourceManagerTest();

    private ResourceBundle bundle = ResourceBundle.getBundle("db_test");

    public static DBResourceManagerTest getInstance() {
        return instance;
    }

    public String getValue(String key) {
        return bundle.getString(key);
    }
}
