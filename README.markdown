
# MySQL + HikariCP unclosed connection shutdown test

  This repository contains a test for HikariCP's closing of MySQL unclosed connections at data source shutdown.

  See https://github.com/brettwooldridge/HikariCP/issues/206

  The `test.Configuration` class contains the database connection parameters, which must be changed and pointed to
  a working MySQL server in order to execute the test. Any `SELECT` query will be OK.

  The `test.MySQLTest` in the `src/test/java` folder contains the JUnit test that can be used for running the test,
  specifically its `runIter()` test method will be of interest for profiling. It will execute the `TestRunner` 100
  times and then wait for 60 seconds hoping that some connections get closed in that interval.

  The `test.TestRunner` class creates a `HikariDataSource` with the configuration in `test.Configuration`, executes
  5 queries on it (5 different connections) and then shuts the data source down.

  The `test.QueryExecutor` class executes the query and leaves the connection unclosed on purpose.

  Observed result is that unclosed MySQL connections **never get closed after data source shutdown**. Even if we extend
  the wait period at the end of the test to 10 minutes, nothing happens.