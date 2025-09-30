package com.example.calcultator;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Exercise exercise;

    private TextView successnum;
    private Button challenge;
    private Button until20;
    private Button question;
    private TextView firstnum;
    private TextView secundnum;
    private TextView answer;
    private Button checkanswer;
    int result;

    private ExerciseCallBack ECB;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();


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


        challenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exercise.rendomten();

            }
        });
        until20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exercise.rendom20();
            }
        });

        question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exercise.rendomquestion();
            }
         });

        checkanswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(exercise.checkanswer() == true){
                    successnum.setText(String.valueOf(Integer.parseInt(successnum.getText().toString())+1));
                    Toast.makeText(MainActivity.this, "correct", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "wrong", Toast.LENGTH_SHORT).show();
                }
            }
            });




    }









}









