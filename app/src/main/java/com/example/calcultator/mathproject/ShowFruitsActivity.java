package com.example.calcultator.mathproject;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calcultator.R;

import java.util.ArrayList;

public class ShowFruitsActivity extends AppCompatActivity {

    private RecyclerView rcShowUsers;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_fruits_users);
        initview();




    }

    private void initview(){
        rcShowUsers = findViewById(R.id.rcShowUsers);
        ArrayList<Fruit> fruits = new ArrayList<>();
        fruits.add(new Fruit("banana", R.drawable.banana));
        fruits.add(new Fruit("apple",R.drawable.apple));
        fruits.add(new Fruit("orange",R.drawable.orange));
        fruits.add(new Fruit("grapes",R.drawable.grapes));
        fruits.add(new Fruit("lemon",R.drawable.lemon));

        MyFruitsAdapter adapter = new MyFruitsAdapter(fruits, new MyFruitsAdapter.OnItemClickListener1() {
            @Override
            public void onItemClick(Fruit item) {
                Toast.makeText(ShowFruitsActivity.this, item.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        rcShowUsers.setLayoutManager(new LinearLayoutManager(this));
        rcShowUsers.setAdapter(adapter);
        rcShowUsers.setHasFixedSize(true);


    }
}