package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import ghar.dfw.perm.javajokelib.JavaJokes;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    TextView jokePlaceHolder;
    View root;
    Button bJoke;


    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        View root = inflater.inflate(R.layout.fragment_main, container, false);
        root = inflater.inflate(R.layout.fragment_main, container, false);

        // widget (textView for joke
        jokePlaceHolder = root.findViewById(R.id.instructions_text_view);
        jokePlaceHolder.setText("Hi from Fragment");

        bJoke = root.findViewById(R.id.bTellJoke);
        bJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jokePlaceHolder.setText("onClick in onCreateView");
                jokePlaceHolder.setText(getJokes());
            }
        });



        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
        return root;
    }

    private String getJokes() {

        JavaJokes javaJokes = new JavaJokes();
        return javaJokes.getJoke();

    }


    public void onClick_jokeDisplay(View view) {

        jokePlaceHolder.setText("onClick");
    }
}
