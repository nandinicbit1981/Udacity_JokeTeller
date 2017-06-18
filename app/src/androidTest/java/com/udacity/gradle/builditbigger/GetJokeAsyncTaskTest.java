package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;

import java.util.concurrent.TimeUnit;

public class GetJokeAsyncTaskTest  extends AndroidTestCase {

    public void testJokeDownload() {

        try {
            EndpointsAsyncTask task = new EndpointsAsyncTask();
            task.execute();
            String joke = task.get(30, TimeUnit.SECONDS);

            assertTrue(joke.length() > 0);

        } catch (Exception e) {
            fail("Operation timed out");
        }
    }

}
