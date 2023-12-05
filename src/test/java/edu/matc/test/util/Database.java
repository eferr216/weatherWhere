package edu.matc.test.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
/**
 * Provides access to the database
 * Created on 8/31/16
 *
 * @author pwaite
 */
public class Database {

    private final Logger logger = LogManager.getLogger(this.getClass());
    //create an object of the class Database
    private static Database instance = new Database();

    private Properties properties;
    private Connection connection;

    // private constructor prevents instantiating this class anywhere else
    private Database() {
        loadProperties();
    }

    /**
     * Establish a connection to the database.
     */
    private void loadProperties() {
        properties = new Properties();
        try {
            properties.load (this.getClass().getResourceAsStream("/database.properties"));
        } catch (IOException ioe) {
            System.out.println("Database.loadProperties()...Cannot load the properties file");
            ioe.printStackTrace();
        } catch (Exception e) {
            System.out.println("Database.loadProperties()..." + e);
            e.printStackTrace();
        }

    }

    /**
     * Get the only Database object available.
     * @return
     */
    public static Database getInstance() {
        return instance;
    }

    /**
     * Get the connection.
     * @return
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Connect to the database.
     * @throws Exception
     */
    public void connect() throws Exception {
        if (connection != null)
            return;

        try {
            Class.forName(properties.getProperty("driver"));
        } catch (ClassNotFoundException e) {
            throw new Exception("Database.connect()... Error: MySQL Driver not found");
        }

        String url = properties.getProperty("url");
        connection = DriverManager.getConnection(url, properties.getProperty("username"), properties.getProperty("password"));
    }

    /**
     * Disconnect from the database.
     */
    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Cannot close connection" + e);
            }
        }

        connection = null;
    }

    /**
     * Run the sql.
     * @param sqlFile the sql file to be read and executed line by line.
     */
    public void runSQL(String sqlFile) {

        Statement stmt = null;
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        //InputStream inputStream = classLoader.getResourceAsStream(sqlFile);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(classLoader.getResourceAsStream(sqlFile)))) {

            connect();
            stmt = connection.createStatement();

            String sql = "";

            while (br.ready()) {
                char inputValue = (char)br.read();

                if (inputValue == ';') {
                    stmt.executeUpdate(sql);
                    sql = "";
                }
                else {
                    sql += inputValue;
                }

            }

        } catch (SQLException se) {
            logger.error(se);
        } catch (Exception e) {
            logger.error(e);
        } finally {
            disconnect();
        }
    }
}


