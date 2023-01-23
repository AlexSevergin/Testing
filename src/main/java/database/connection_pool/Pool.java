package database.connection_pool;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * This class is responsible for creating a pool that is connected to the database.
 * Singleton.
 * @author Oleksandr Severhin
 */
public class Pool {
    private static final Logger LOG = Logger.getLogger(Pool.class);
    private static final int GET_CONNECTION_MILLIS = 1000;
    private static final String PROPERTIES_PATH = "/db.properties";
    private static Pool INSTANCE;
    private Map<Connection, Boolean> connections; // all the connection that are in the pool
    private final String URL;
    private final String USER;
    private final String PASSWORD;

    /**
     * Creates a HashMap (Connection, true) using data stored in db.properties.
     */
    private Pool() {
        Properties properties = new Properties();
        try {
            properties.load(getClass().getResourceAsStream(PROPERTIES_PATH));
            Class.forName(properties.getProperty("db.driver"));
            URL = properties.getProperty("db.url");
            USER = properties.getProperty("db.user");
            PASSWORD = properties.getProperty("db.password");
            int capacity = Integer.parseInt(properties.getProperty("db.poolsize"));
            connections = new HashMap<>(capacity);
            for (int i = 0; i < capacity; i++) {
                connections.put(createConnection(), true);
            }
        } catch (ClassNotFoundException | SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Pool getInstance() {
        if (INSTANCE == null)
            synchronized (Pool.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Pool();
                }
            }
        return INSTANCE;
    }

    private ConnectionPool createConnection() throws SQLException {
        return new ConnectionPool(DriverManager.getConnection(URL, USER, PASSWORD), this);
    }

    /***
     * Puts connection in Pool instead of closing.
     * @param poolConnection
     */
    public void free(ConnectionPool poolConnection) {
        connections.put(poolConnection, true);
    }

    public Connection getConnection() {
        Connection result = null;
        for (Map.Entry<Connection, Boolean> entry : connections.entrySet()) {
            if (entry.getValue()) {
                synchronized (this) {
                    if (entry.getValue()) {
                        Connection key = entry.getKey();
                        connections.put(key, false);
                        result = key;
                    }
                }
            }
        }
        if (result == null) {
            try {
                Thread.sleep(GET_CONNECTION_MILLIS);
            } catch (InterruptedException e) {
                LOG.error(e.getMessage());
                LOG.info("Retrying to get connection.");
            }
            return getConnection();
        } else {
            return result;
        }
    }
}
