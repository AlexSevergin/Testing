package database;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DBManager {

    private static DBManager instance;
    public static final String SETTINGS_FILE = "app.properties";

    public static synchronized DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    public Connection getConnection() throws DBException {
        Connection con;
        try {
            con = DriverManager.getConnection(conUrl());
        } catch (SQLException ex) {
            throw new DBException(ex.getMessage());
        }
        return con;
    }

    private String conUrl() {
        String con = null;
        try (InputStream input = new FileInputStream(SETTINGS_FILE)) {
            Properties prop = new Properties();
            prop.load(input);
            con = prop.getProperty("connection.url");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return con;
    }

    private void close(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void close(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void close(Connection con, Statement stmt, ResultSet rs) {
        close(rs);
        close(stmt);
        close(con);
    }

    private void rollback(Connection con) {
        if (con != null) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

}
