package com.example.a2019papaer2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a2019papaer2.DataBase.DBhandler;

public class AddGame extends AppCompatActivity {


    Button add;
    EditText name,year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_game);


        name = findViewById(R.id.nameAG);
        year = findViewById(R.id.yarAG);
        add = findViewById(R.id.addAG);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBhandler dBhandler = new  DBhandler(getApplicationContext());
                boolean x = dBhandler.addGame(name.getText().toString(),year.getText().toString());
                if(x ==true){
                    Toast.makeText(AddGame.this, "Game Added Succssfully", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(AddGame.this, "Ga,e Not Added Succssfully", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}