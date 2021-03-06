package com.foursquare.heapaudit.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestThreaded {

    // Test threaded scenario.

    @Test public void Threaded() throws InterruptedException, IOException {

        Process process = Runtime.getRuntime().exec("java -javaagent:" + System.getProperty("javaagent") + "=-Icom.foursquare.heapaudit.tutorials.example5.Threaded@run.+#-Xthreaded -classpath test-classes com/foursquare/heapaudit/tutorials/example5/Threaded");

        process.waitFor();

        BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));

        String result = "";

        for (String s = input.readLine(); s != null; s = input.readLine()) {

            result += s + "\n";

        }

        assertTrue(result.matches("(?m)^HEAP: com/foursquare/heapaudit/tutorials/example5/Threaded@run\\(\\)V x1$[\\s\\S]*^      - com.foursquare.heapaudit.tutorials.Example[$]Bar \\(\\d+ bytes\\) x1$[\\s\\S]*"));

        assertFalse(result.matches("(?m)[\\s\\S]*^      - com.foursquare.heapaudit.tutorials.Example[$]Foo \\(\\d+ bytes\\) x\\d+$[\\s\\S]*"));

    }

}
