package by.training.testing.controller;

import by.training.testing.controller.exception.ControllerRuntimeException;
import by.training.testing.dao.impl.connection.ConnectionPool;
import by.training.testing.dao.impl.connection.ConnectionPoolException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        try {
            ConnectionPool.getInstance().InitPoolData();
        }
        catch (ConnectionPoolException e) {
            throw new ControllerRuntimeException(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionPool.getInstance().dispose();
    }
}
