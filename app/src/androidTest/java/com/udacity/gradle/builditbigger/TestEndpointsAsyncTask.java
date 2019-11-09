
package com.udacity.gradle.builditbigger;
import android.content.Context;
import android.util.Log;

import androidx.core.util.Pair;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;


import static java.sql.Types.NULL;


@RunWith(androidx.test.ext.junit.runners.AndroidJUnit4.class)
public class TestEndpointsAsyncTask {

    private Context textContext;
    Pair<Context, String> newPair;

    @Before
    public void initComponent(){

        // Initialize fields here
        textContext = androidx.test.core.app.ApplicationProvider.getApplicationContext();
    }

    @Test
    public void testAsyncTask(){
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        try {
            newPair = new EndpointsAsyncTask(new OnPostTask() {

                @Override
                public void onPostTask(String result) {
                    Log.d("asnycTask test results: ", result);

//                    countDownLatch.countDown();
                }
             }
            ).execute(new Pair<Context, String>(textContext, ""))
                    .get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // try {
            Assert.assertNotEquals("", newPair.second);
            Assert.assertNotEquals(NULL, newPair.second);
            Assert.assertNotEquals(Error.class, newPair.second);

//        countDownLatch.await();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
