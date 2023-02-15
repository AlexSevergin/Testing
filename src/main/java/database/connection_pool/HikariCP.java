package database.connection_pool;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.log4j.Logger;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class HikariCP {
    private static final Logger LOG = Logger.getLogger(HikariCP.class);
    private static HikariDataSource dataSource = null;
    private static final String PROPERTIES_PATH = "/db.properties";

    static {
        LOG.info("Initializing HikariCP");
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            LOG.error("Haven't found com.mysql.jdbc.Driver", e);
            e.printStackTrace();
        }
        Properties properties = new Properties();
        try {
            properties.load(HikariCP.class.getResourceAsStream(PROPERTIES_PATH));
        } catch (IOException e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
        }
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(properties.getProperty("db.url"));
        config.setUsername(properties.getProperty("db.user"));
        config.setPassword(properties.getProperty("db.password"));
        config.addDataSourceProperty("minimumIdle", properties.getProperty("db.minsize"));
        config.addDataSourceProperty("maximumPoolSize", properties.getProperty("db.maxsize"));
        dataSource = new HikariDataSource(config);
    }

    private HikariCP() {}

    public static Connection getConnection() throws SQLException {
        LOG.info("Getting HikariCP connection");
        return dataSource.getConnection();
    }

    public static void close() throws SQLException {
        if (dataSource != null) {
            LOG.info("Closing HikariCP");
            dataSource.close();
        }
    }
}