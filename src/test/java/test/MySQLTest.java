package test;

import org.junit.Assert;

public class MySQLTest {

    @org.junit.Test
    public void testIter() throws Exception {

        System.out.println("Executing queries...");

        for (int i = 0; i < 100; i++) {
            Assert.assertTrue(TestRunner.run());
        }

        System.out.println("Queries executed. All data sources closed. Waiting 60sec to allow GC or connection timeouts...");

        // Wait for 60secs to allow any cleanup operations to take place
        Thread.currentThread().sleep(60000L);

        System.out.println("Execution finished");

    }

    @org.junit.Test
    public void testSimple() throws Exception {
        Assert.assertTrue(TestRunner.run());
    }

}
