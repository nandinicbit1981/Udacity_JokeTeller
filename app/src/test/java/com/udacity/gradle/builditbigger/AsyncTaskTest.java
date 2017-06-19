package com.udacity.gradle.builditbigger;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertTrue;

/**
 * Created by nandpa on 6/18/17.
 */

public class AsyncTaskTest {
    @Test
    public void addition_isCorrect() throws Exception {
        EndpointsAsyncTask task = new EndpointsAsyncTask();
        task.execute();
        String joke = task.get(30, TimeUnit.SECONDS);

        assertTrue(joke.length() > 0);
    }
}
