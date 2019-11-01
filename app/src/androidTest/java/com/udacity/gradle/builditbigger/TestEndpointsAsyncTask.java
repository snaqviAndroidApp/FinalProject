
package com.udacity.gradle.builditbigger;
import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.core.util.Pair;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

import static android.app.PendingIntent.getActivity;

@RunWith(androidx.test.ext.junit.runners.AndroidJUnit4.class)
//public class TestEndpointsAsyncTask extends  junit.framework.TestCase {
public class TestEndpointsAsyncTask {

    private Context textContext;
    private View testView;

    @Before
    public void initComponent(){
//        textContext = new Context();
//        testView = new View(){

//        };
    }


    @Test
    public void testAsyncTask(){
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        new EndpointsAsyncTask(new OnPostTask() {
            @Override
            public void onPostTask(String result) {
                Log.d("asnycTask test results: ",  result);
                countDownLatch.countDown();//           notify the count down latch
            }

        }).execute();
//        }).execute(new Pair<Context, String>(getActivity(textContext), "Manfred"));
    }
}
