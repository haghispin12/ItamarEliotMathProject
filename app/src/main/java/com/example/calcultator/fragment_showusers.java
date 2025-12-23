package com.example.calcultator;

import static android.app.Activity.RESULT_OK;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;


public class fragment_showusers extends Fragment {

    private EditText usernamefr;
    private Button addimage;
    private Button adduser;
    private TextView score;
    private TextView rating;
    private ImageView showPic;
    Uri uri;


    ActivityResultLauncher<Intent> startCamera = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        showPic.setImageURI(uri);
                    }

                }

            });



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
        showPic = v.findViewById(R.id.showPic);
        addimage = v.findViewById(R.id.addimage);
        adduser = v.findViewById(R.id.adduser);

        Gson gson = new Gson();
        String json = getArguments().getString("User");
        User user = gson.fromJson(json, User.class);
        usernamefr.setText(user.getName());
        score.setText(String.valueOf("score:"+user.getPoints()));
        rating.setText(String.valueOf("rating:"+user.getRate()));

        addimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.TITLE, "New Picture");
                values.put(MediaStore.Images.Media.DESCRIPTION, "From Camera");
                uri =
                        requireContext().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                startCamera.launch(cameraIntent);

            }
        });



        return v;



    }



}