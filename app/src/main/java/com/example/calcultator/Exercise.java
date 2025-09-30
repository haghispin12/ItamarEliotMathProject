package com.example.calcultator;

import static android.widget.Toast.LENGTH_SHORT;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Exercise {

    int result;
    private int num1;
    private int num2;

    private ExerciseCallBack ECB;

    public Exercise(ExerciseCallBack ecb) {
        this.ECB = ecb;
    }




    public void rendomten() { // הגרלת מספרים בין 1 ל10
        Random r = new Random();
        int num1 = r.nextInt(10)+1;
        int num2 = r.nextInt(90)+10;


        result = num1*num2;
        ECB.correct(num1,num2);

    }

    public void rendom20(){
        Random r = new Random();
        int num1 = r.nextInt(10)+1;
        int num2 = r.nextInt(10)+10;

        result = num1*num2;
        ECB.correct(num1,num2);

    }

    public void rendomquestion(){
        Random r = new Random();
        int num1 = r.nextInt(10)+1;
        int num2 = r.nextInt(10)+1;

        result = num1*num2;
        ECB.correct(num1,num2);

    }

    public boolean checkanswer(){
//        if (Integer.parseInt(answer.getText().toString()) == result){
////          return true
//        }
//        else{
////          return false
//        }
        return false;
    }





}
