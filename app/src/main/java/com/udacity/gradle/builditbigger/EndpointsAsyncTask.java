package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import androidx.core.util.Pair;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

public class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {
    private MyApi myApiService = null;
    private Context context;
    private OnPostTask onPostTask_Paid;

    public EndpointsAsyncTask(OnPostTask inOnPostTask_Paid){
        this.onPostTask_Paid = inOnPostTask_Paid;
    }


    @Override
    protected String doInBackground(Pair<Context, String>... params) {
//    protected String doInBackground(Void... params) {
        if (myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    .setRootUrl("http://192.168.0.2:8080/_ah/api/")                             // pc ip-address
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            myApiService = builder.build();                                      // end options for devappserver
        }

        try {
            context = params[0].first;
            String name = params[0].second;
            return myApiService.sayHi().execute().getData();                     //  working
        } catch (IOException e) {
            return e.getMessage();
        }

    }

    @Override
    protected void onPostExecute(String result) {
        onPostTask_Paid.onPostTask(result);
    }
}

