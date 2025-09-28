package com.example.calcultator;

import static android.widget.Toast.LENGTH_SHORT;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Exercise {
    private TextView successnum;
    private Button challenge;
    private Button until20;
    private Button question;
    private TextView firstnum;
    private TextView secundnum;
    private TextView answer;
    private Button checkanswer;
    int result;


    private void initView() {
        firstnum = firstnum.findViewById(R.id.firstnum);
        secundnum = secundnum.findViewById(R.id.secundnum);


    }

    public void rendomten() { // הגרלת מספרים בין 1 ל10
        Random r = new Random();
        int num1 = r.nextInt(10)+1;
        int num2 = r.nextInt(90)+10;
        firstnum.setText(String.valueOf(num1));
        secundnum.setText(String.valueOf(num2));
        result = num1*num2;

    }

    public void rendom20(){
        Random r = new Random();
        int num1 = r.nextInt(10)+1;
        int num2 = r.nextInt(10)+10;
        firstnum.setText(String.valueOf(num1));
        secundnum.setText(String.valueOf(num2));
        result = num1*num2;

    }

    public void rendomquestion(){
        Random r = new Random();
        int num1 = r.nextInt(10)+1;
        int num2 = r.nextInt(10)+1;
        firstnum.setText(String.valueOf(num1));
        secundnum.setText(String.valueOf(num2));
        result = num1*num2;
    }

    public boolean checkanswer(){
        if (Integer.parseInt(answer.getText().toString()) == result){
//          return true
        }
        else{
//          return false
        }
        return false;
    }





}
