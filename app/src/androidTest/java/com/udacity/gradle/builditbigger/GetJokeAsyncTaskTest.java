package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static junit.framework.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class GetJokeAsyncTaskTest {

    private Context instrumentationCtx;

    @Before
    public void setup() {
        instrumentationCtx = InstrumentationRegistry.getContext();
    }

    @Rule
    public IntentsTestRule<MainActivity> rule = new IntentsTestRule<>(MainActivity.class);


    @Test
    public void testVerifyEchoResponse() {
       // assertEquals("hello", "hello");
        EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask();
        endpointsAsyncTask.execute(instrumentationCtx);
        String joke = null;
        try {
            joke = endpointsAsyncTask.get(30, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        assertTrue(joke.length() > 0);
        /*onView(withId(R.id.tell_joke_button)).perform(click());
        intended(hasComponent(hasClassName(JokeActivity.class.getName())));
        intended(hasExtra(equalTo(Intent.EXTRA_TEXT), notNullValue()));*/
    }

}
