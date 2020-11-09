package com.example.a2019papaer2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.a2019papaer2.DataBase.DBhandler;

import java.util.ArrayList;

public class GameList extends AppCompatActivity {

    ListView list;
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_list);

        list = findViewById(R.id.listGL);
        DBhandler dBhandler = new  DBhandler(getApplicationContext());

        ArrayList arr = dBhandler.Viewgames();

        arrayAdapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,arr);
        list.setAdapter(arrayAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = list.getItemAtPosition(i).toString();
                Intent b = new Intent(getApplicationContext(),GameOverView.class);
                b.putExtra("name",name);
                startActivity(b);
            }
        });


    }
}