package com.example.calcultator;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallerLauncher;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Exercise exercise;

    private User user;

    private TextView successnum;
    private Button challenge;
    private Button until20;
    private Button question;
    private TextView firstnum;
    private TextView secundnum;
    private TextView answer;
    private Button checkanswer;
    private Button Rate;
    int result;

    int points;

    int currentpoints;

    private ExerciseCallBack ECB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        user = new User(username);
        Toast.makeText(this, "hello "+ username, Toast.LENGTH_SHORT).show();
        user.setPoints(points);
        user.getPoints();



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



        Rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Rate_Activity.class);




            }
        });



        challenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exercise.rendomten();
                points = 20;

            }
        });
        until20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exercise.rendom20();
                points = 10;

            }
        });

        question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exercise.rendomquestion();
                points = 5;

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




    }









}









