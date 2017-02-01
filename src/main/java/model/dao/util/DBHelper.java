package model.dao.util;

import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by lexy on 01.12.16.
 */
public class DBHelper {
    private static DataSource dataSource = null;
    private final static Logger logger = Logger.getLogger(DBHelper.class);

    public static Connection getConnection() {
        try {
            if (dataSource != null) {
                return dataSource.getConnection();
            }

            Context initContext = new InitialContext();
            Context encContext = (Context) initContext.lookup("java:/comp/env");
            dataSource = (DataSource) encContext.lookup("jdbc/electiveDB");
            return dataSource.getConnection();

        } catch (SQLException | NamingException e) {
            logger.error(e.toString());
            return null;
        }
    }

    public static String getQuery(String queryName) {
        try {
            Properties prop = new Properties();
            InputStream inputStream = DBHelper.class.getClassLoader().getResourceAsStream("/queries.properties");
            prop.load(inputStream);

            return prop.getProperty(queryName);
        } catch (IOException e) {
            logger.error(e.toString());
            return null;
        }
    }
}
