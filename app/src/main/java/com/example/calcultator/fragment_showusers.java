package com.example.calcultator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;


public class fragment_showusers extends Fragment {

    private EditText usernamefr;
    private Button addimage;
    private Button adduser;
    private TextView score;
    private TextView rating;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_showusers, container, false);
        usernamefr = v.findViewById(R.id.usernamefr);
        score = v.findViewById(R.id.score);
        rating = v.findViewById(R.id.rating);
        Gson gson = new Gson();
        String json = getArguments().getString("User");
        User user = gson.fromJson(json, User.class);
        usernamefr.setText(user.getName());
        score.setText(String.valueOf("score:"+user.getPoints()));
        rating.setText(String.valueOf("rating:"+user.getRate()));

        return v;



    }


}