package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.Bundle;

import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private TextView jokePlaceHolder_paid;
    View root;
    private Button bJoke;

    public MainActivityFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_main, container, false);
        jokePlaceHolder_paid = root.findViewById(R.id.instructions_text_view);          // widget textView for joke
        jokePlaceHolder_paid.setText("Hi from Fragment Paid");

        bJoke = root.findViewById(R.id.bTellJoke);
        bJoke.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new EndpointsAsyncTask(new OnPostTask() {

                    @Override
                    public void onPostTask(String result) {
                        /**
                         * Instead of instantiating DeliverJoke-Activity here, using one
                         * Instance of the using main-module as common for all flavors
                         **/
                    }
                }).execute(new Pair<Context, String>(getActivity(), "")
                );
            }
        });
        return root;
    }

}
