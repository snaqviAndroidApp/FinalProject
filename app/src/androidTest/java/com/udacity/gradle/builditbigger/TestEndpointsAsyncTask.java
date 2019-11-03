
package com.udacity.gradle.builditbigger;
import android.content.Context;
import android.util.Log;

import androidx.core.util.Pair;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;


import static java.sql.Types.NULL;


@RunWith(androidx.test.ext.junit.runners.AndroidJUnit4.class)
public class TestEndpointsAsyncTask {

    private Context textContext;

    @Before
    public void initComponent(){

        // Initialize fields here
        textContext = androidx.test.core.app.ApplicationProvider.getApplicationContext();
    }

    @Test
    public void testAsyncTask(){
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        new EndpointsAsyncTask(new OnPostTask() {
            @Override
            public void onPostTask(String result) {
                Log.d("asnycTask test results: ",  result);

                Assert.assertNotEquals("", result);
                Assert.assertNotEquals(NULL, result);
                Assert.assertNotEquals(Error.class, result);

                countDownLatch.countDown();                 //           notify the count down latch
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

//        }).execute();
        }).execute(new Pair<Context, String>(textContext, "Manfred"));
    }
}
