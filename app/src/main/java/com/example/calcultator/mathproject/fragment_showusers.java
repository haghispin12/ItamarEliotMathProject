package com.example.calcultator.mathproject;

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

import com.example.calcultator.R;
import com.google.gson.Gson;

import java.util.ArrayList;


public class fragment_showusers extends Fragment {

    private EditText usernamefr;
    private Button addimage;
    private Button adduser;
    private TextView score;
    private TextView rating;
    private ImageView showPic;
    public User user;
    DBHelper dph;
    Uri uri;
    ArrayList<User> list;
    private Button update;
    private Button delete;
    public User current;


    ActivityResultLauncher<Intent> startCamera = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        showPic.setImageURI(uri);
                        user.setUri(uri);
                    }

                }

            });

    // שולף מהsql את כל המשתמשים ומחזיר אותם ברשימה
    private ArrayList<User> pullout() {
        return dph.selectAll();
    }

    public void showtheuser(User updated){
        usernamefr.setText(updated.getName());
        score.setText("score:" + updated.getPoints());
        rating.setText("rating:" + updated.getRate());
        showPic.setImageBitmap(updated.getPicture());

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        dph = new DBHelper(requireContext());

        list = pullout();




        View v = inflater.inflate(R.layout.fragment_showusers, container, false);
        usernamefr = v.findViewById(R.id.usernamefr);
        score = v.findViewById(R.id.score);
        rating = v.findViewById(R.id.rating);
        showPic = v.findViewById(R.id.showPic);
        addimage = v.findViewById(R.id.addimage);
        adduser = v.findViewById(R.id.adduser);
        update = v.findViewById(R.id.update);
        delete = v.findViewById(R.id.delete);

        Gson gson = new Gson();
        String json = getArguments().getString("User");
        user = gson.fromJson(json, User.class);
        usernamefr.setText(user.getName());
        score.setText(String.valueOf("score:"+user.getPoints()));
        rating.setText(String.valueOf("rating:"+user.getRate()));

        // open camera
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

        adduser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long id = dph.insert(user, requireContext());
                list = pullout();
                if (list.size() >= 2) {
                    showtheuser(list.get(1));
                    current = list.get(1);
                }


                int i=0;



            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newName = usernamefr.getText().toString().trim();
                if (!newName.isEmpty()) {
                    current.setname(newName);
                    dph.update(current);
                    showtheuser(current);
                }

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dph.deleteById(Long.parseLong(current.getId()));
            }
        });





        return v;



    }




}