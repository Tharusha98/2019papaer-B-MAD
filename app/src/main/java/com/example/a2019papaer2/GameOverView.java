package com.example.a2019papaer2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a2019papaer2.DataBase.DBhandler;
import com.example.a2019papaer2.DataBase.DataBaseMaster;

import java.util.ArrayList;

public class GameOverView extends AppCompatActivity {


    SeekBar seekBar;
    EditText comments;
    Button submit;
    TextView rate,name;
    ListView com;
    String names;
    ArrayAdapter arrayAdapter;
    int tot = 0;
    int count = 0,rating;
    int rat = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over_view);
        Intent i = getIntent();
        names = i.getStringExtra("name");


        comments = findViewById(R.id.comGO);
        seekBar = findViewById(R.id.seekBarGO);
        submit  = findViewById(R.id.submitGO);
        rate = findViewById(R.id.curentratingGO);
        name= findViewById(R.id.textGO);
        com = findViewById(R.id.listGO);

        name.setText(names);


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                rat = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBhandler dBhandler = new  DBhandler(getApplicationContext());
                boolean x = dBhandler.insertComments(names, String.valueOf(rat),comments.getText().toString() );

                if(x == true){
                    Toast.makeText(GameOverView.this, "Comment insert Sucssfully", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(GameOverView.this, "Comment not inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });



        DBhandler dBhandler = new  DBhandler(getApplicationContext());
        ArrayList arrayList = dBhandler.viewcomments(names);

        arrayAdapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,arrayList);
        com.setAdapter(arrayAdapter);

         ArrayList rates = dBhandler.getratings(names);
         System.out.println("rate"+rates);

        for(int h = 0; h < rates.size();h++){
            tot = tot + Integer.parseInt((String) rates.get(h));
            count++;
        }

try {
    rating = tot / rates.size();
    System.out.println("rates" + rating);
    rate.setText(rating + "");

}catch(Exception e) {




}







    }
}