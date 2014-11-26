package test;

import java.util.List;

import com.zaxxer.hikari.HikariDataSource;

public class TestRunner {


    public static boolean run() throws Exception {

        final HikariDataSource dataSource = HikariDataSourceFactory.createDataSource();

        boolean resultsOK = true;

        try {

            // It's important that we use less connections (5) than the pool maximum (20) that way, we wont stop
            // waiting for more connections and will actually get to the point where dataSource.shutdown() is
            // executed and the connections should be recclaimed.
            for (int i = 0; i < 5; i++) {

                final List<List<Object>> results = QueryExecutor.executeQuery(dataSource, Configuration.QUERY);

                if (results.isEmpty()) {
                    resultsOK = false;
                }

            }

        } finally {
            dataSource.shutdown();
        }

        return resultsOK;

    }


}
