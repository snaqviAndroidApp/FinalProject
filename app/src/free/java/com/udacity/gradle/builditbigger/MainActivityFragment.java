package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        TextView jokePlaceHolder = root.findViewById(R.id.instructions_text_view);           /**  widget (textView for joke) **/

        jokePlaceHolder.setText("Hi from Fragment");

        Button bJoke = root.findViewById(R.id.bTellJoke);
        bJoke.setOnClickListener(new View.OnClickListener() {


            /** Sending data to display activity: DeliverJoke.java **/

            @Override
            public void onClick(View v) {
                new EndpointsAsyncTask(new OnPostTask() {

                    @Override
                    public void onPostTask(String result) {}
                }).execute(new Pair<Context, String>(getActivity(), "")
                );
            }
        });

        AdView mAdView = root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
        return root;
    }

}
