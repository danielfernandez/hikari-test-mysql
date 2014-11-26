package test;

import java.util.Properties;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikariDataSourceFactory  {

    private static final String HIKARI_DRIVER_PROPERTY = "driverClassName";
    private static final String HIKARI_JDBC_URL_PROPERTY = "jdbcUrl";
    private static final String HIKARI_USER_PROPERTY = "username";
    private static final String HIKARI_PASSWORD_PROPERTY = "password";



    public static HikariDataSource createDataSource() {
        
        try {

            /* ---------------------
             * CONNECTION PROPERTIES
             */

            final Properties properties = new Properties();
            properties.setProperty(HIKARI_DRIVER_PROPERTY, Configuration.DRIVER_CLASS_NAME);
            properties.setProperty(HIKARI_JDBC_URL_PROPERTY, Configuration.JDBC_URL);
            properties.setProperty(HIKARI_USER_PROPERTY, Configuration.USER);
            properties.setProperty(HIKARI_PASSWORD_PROPERTY, Configuration.PASSWORD);

            return new HikariDataSource(new HikariConfig(properties));
            
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Error creating data source", e);
        }
    }


    private HikariDataSourceFactory() {
        super();
    }

}
