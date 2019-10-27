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
import android.widget.Toast;



/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private TextView jokePlaceHolder_paid;
    View root;
    private Button bJoke;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_main, container, false);

        // widget textView for joke
        jokePlaceHolder_paid = root.findViewById(R.id.instructions_text_view);
        jokePlaceHolder_paid.setText("Hi from Fragment Paid");

        bJoke = root.findViewById(R.id.bTellJoke);
        bJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new EndpointsAsyncTask(new OnPostTask() {
                    @Override
                    public void onPostTask(String result) {
                        Toast.makeText(getActivity(), result, Toast.LENGTH_LONG).show();
                    }
                }
                ).execute(new Pair<Context, String>(getActivity(), "Manfred"));
            }
        });
        return root;
    }

}
