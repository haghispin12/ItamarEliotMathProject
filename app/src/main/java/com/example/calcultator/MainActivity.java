package com.example.calcultator;


import android.content.Intent;
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


    /**
     *  לוקח את הציון מהrate_activity. מעדכן את מחלקת user מה הציון שנתן המשתמש
     */
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


        /**
         * כשמגרילים שני מספרים בשביל התרגיל.זה מציג את שני המספרים,ושומר אותם בשביל לבדוק אם המשתמש צדק
         */
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

    /**
     * לוקח את כל הרכיבים כמו כפתורים וכדומה ומייצר אובייקטים שגם יהיה אפשר להאזין
     */
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

        // כפתור דירוג: פותח את Rate_Activity כדי לתת ציון וחוזר עם התוצאה
        Rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Rate_Activity.class);
                activityResultLauncher.launch(intent);

            }
        });


        // אתגר 1–10: מגריל תרגיל קשה יותר ומוסיף 20 נקודות למשתמש
        challenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exercise.rendomten();
                points = 20;
                User.addPoints(points);

            }
        });


        // תרגיל עד 20: מגריל תרגיל בינוני ומוסיף 10 נקודות למשתמש
        until20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exercise.rendom20();
                points = 10;
                User.addPoints(points);

            }
        });
        // תרגיל קל: מגריל מכפלה פשוטה ומוסיף 5 נקודות למשתמש
        question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exercise.rendomquestion();
                points = 5;
                User.addPoints(points);

            }
         });
        // בדיקת תשובה: בודק אם המשתמש צדק, מציג הודעה ומעדכן ניקוד מצטבר
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
        // מעבר למסך משתמשים/פירות: פותח את ShowFruitsActivity
        showusers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShowFruitsActivity.class);
                startActivity(intent);


//                Gson gson = new Gson();
//                String json = gson.toJson(User);
//
//                Bundle bundle = new Bundle();
//                bundle.putString("User", json);
//                fragment_showusers fragment = new fragment_showusers();
//                fragment.setArguments(bundle);
//                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutuser,fragment).commit();
            }
        });





    }










}









