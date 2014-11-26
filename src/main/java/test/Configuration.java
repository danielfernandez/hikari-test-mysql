package test;

public final class Configuration {

    public static final String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
    public static final String JDBC_URL = "jdbc:mysql:[host]/[database]";
    public static final String USER = "[user]";
    public static final String PASSWORD = "[password]";

    public static final String QUERY = "SELECT * FROM [TABLE]";



    private Configuration() {
        super();
    }


}
