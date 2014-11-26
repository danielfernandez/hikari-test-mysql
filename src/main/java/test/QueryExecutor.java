package test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.zaxxer.hikari.HikariDataSource;

public class QueryExecutor {




    public static List<List<Object>> executeQuery(final HikariDataSource dataSource, final String sql) throws SQLException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {

            connection = dataSource.getConnection();
            statement = connection.createStatement();


            statement.execute(sql);
            resultSet = statement.getResultSet();


            final ResultSetMetaData rsMetaData = resultSet.getMetaData();
            final int columnCount = rsMetaData.getColumnCount();

            final List<List<Object>> results = new ArrayList<List<Object>>();
            while (resultSet.next()) {

                final List<Object> row = new ArrayList<Object>();

                for (int i = 1; i <= columnCount; i++) {
                    row.add(resultSet.getObject(i));
                }

                results.add(row);

            }
            return results;

        } finally {

            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException ignored) {
                // ignored -- exception inside finally!
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException ignored) {
                // ignored -- exception inside finally!
            }

// --------------------------------------------------------
// This Commented out in order to leave connection unclosed
// --------------------------------------------------------
//            try {
//                if (connection != null) {
//                    connection.close();
//                }
//            } catch (SQLException ignored) {
//                // ignored -- exception inside finally!
//            }

        }
    }



    private QueryExecutor() {
        super();
    }


}
