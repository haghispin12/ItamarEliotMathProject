package com.example.calcultator;


import static android.content.Intent.getIntent;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {
    private Exercise exercise;

    private User User;

    private TextView successnum;
    private Button challenge;
    private Button until20;
    private Button question;
    private TextView firstnum;
    private TextView secundnum;
    private TextView answer;
    private Button checkanswer;
    private Button showusers;
    private Button Rate;
    int result;

    int points;

    int currentpoints;

    private ExerciseCallBack ECB;


    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    int myrate = result.getData().getIntExtra("Rate_key",-1);
                    Toast.makeText(MainActivity.this, "rate is " + myrate, Toast.LENGTH_SHORT).show();
                    User.setRate(myrate);

                }
            });



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        Intent intent = getIntent();
        String Rate_key = intent.getStringExtra("Rate_key");
        String username = intent.getStringExtra("username");

        User = new User(username);
        Toast.makeText(this, "hello "+ username, Toast.LENGTH_SHORT).show();
        User.setPoints(points);
        User.getPoints();




        ECB = new ExerciseCallBack() {
            @Override
            public void correct(int num1, int num2) {
                firstnum.setText(String.valueOf(num1));
                secundnum.setText(String.valueOf(num2));
                result = num1*num2;

            }

        };

        exercise = new Exercise(ECB);

    }

    private void initView() {
        successnum = findViewById(R.id.successnum);
        challenge = findViewById(R.id.challenge);
        until20 = findViewById(R.id.until20);
        question = findViewById(R.id.question);
        firstnum = findViewById(R.id.firstnum);
        secundnum = findViewById(R.id.secundnum);
        answer = findViewById(R.id.answer);
        checkanswer = findViewById(R.id.checkanswer);
        Rate = findViewById(R.id.Rate);
        showusers = findViewById(R.id.showusers);


        Rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Rate_Activity.class);
                activityResultLauncher.launch(intent);

            }
        });



        challenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exercise.rendomten();
                points = 20;
                User.addPoints(points);

            }
        });
        until20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exercise.rendom20();
                points = 10;
                User.addPoints(points);

            }
        });

        question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exercise.rendomquestion();
                points = 5;
                User.addPoints(points);

            }
         });

        checkanswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(answer.getText().toString()) == result){
                    Toast.makeText(MainActivity.this, "correct", Toast.LENGTH_SHORT).show();
                    successnum.setText(String.valueOf(currentpoints + points));
                    currentpoints = currentpoints + points;
                }
                else{
                    Toast.makeText(MainActivity.this, "wrong", Toast.LENGTH_SHORT).show();
                }
            }
            });

        showusers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson gson = new Gson();
                String json = gson.toJson(User);

                Bundle bundle = new Bundle();
                bundle.putString("User", json);
                fragment_showusers fragment = new fragment_showusers();
                fragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutuser,fragment).commit();
            }
        });





    }










}









