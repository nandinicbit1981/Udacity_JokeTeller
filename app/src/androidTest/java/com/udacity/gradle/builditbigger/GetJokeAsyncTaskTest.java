package com.udacity.gradle.builditbigger;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class GetJokeAsyncTaskTest {

    @Rule
    public IntentsTestRule<MainActivity> rule = new IntentsTestRule<>(MainActivity.class);


    @Test
    public void testVerifyEchoResponse() {
       // assertEquals("hello", "hello");
        final EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask();
        MainActivity mainActivity = rule.getActivity();
        endpointsAsyncTask.execute(mainActivity);
        try {
            Thread.sleep(5000);
            System.out.println("poraaaaa^^^^^^^^^^^^^^^^^^^^^^^^");
            assertTrue(mainActivity.joke.length() > 0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
