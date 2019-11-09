package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import androidx.core.util.Pair;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

public class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, Pair<Context, String>> {

    private MyApi myApiService = null;
    private Context context;
    private OnPostTask onPostTask_;

    public EndpointsAsyncTask(OnPostTask inOnPostTask__){
        this.onPostTask_ = inOnPostTask__;
    }

    @Override
    protected Pair<Context, String> doInBackground(Pair<Context, String>... params) {
        if (myApiService == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("http://192.168.0.2:8080/_ah/api/")                             // pc ip-address
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            myApiService = builder.build();                                         // end options for devappserver
        }
        context = params[0].first;
        String name = params[0].second;
        try {
            return new Pair<>(context, myApiService.sayHi().execute().getData());
        } catch (IOException e) {
            e.printStackTrace();
            return new Pair<>(context, e.getLocalizedMessage());
        }
    }

    @Override
    protected void onPostExecute(Pair<Context, String> contextStringPair) {
        Intent iDeliverJoke = new Intent(contextStringPair.first, DeliverJoke.class);
        String jokeStr = (contextStringPair.second != null) ? contextStringPair.second : "sorry, no joke available! ";
        iDeliverJoke.putExtra("joke", jokeStr);
        context.startActivity(iDeliverJoke);
    }
}

