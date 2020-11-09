package com.example.a2019papaer2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a2019papaer2.DataBase.DBhandler;

public class MainActivity extends AppCompatActivity {

    Button reg,login;
    EditText uname,pwd;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uname = findViewById(R.id.unameMA);
        pwd = findViewById(R.id.pwdMA);
        reg = findViewById(R.id.regMA);
        login = findViewById(R.id.loginMA);


        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBhandler dBhandler = new  DBhandler(getApplicationContext());
                if(uname.getText().toString().equals("admin")){
                    type = "admin";
                }else{
                    type = "user";
                }
                boolean x = dBhandler.registeruser(uname.getText().toString(),pwd.getText().toString(),type);

                if(x ==true){
                    Toast.makeText(MainActivity.this, "User Register Succssfully", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "User Not Added Succssfully", Toast.LENGTH_SHORT).show();
                }

            }


        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBhandler dBhandler = new  DBhandler(getApplicationContext());
                boolean y = dBhandler.loginuser(uname.getText().toString(),pwd.getText().toString());
                if( y == true) {
                    if (uname.getText().toString().equals("admin")) {
                        Toast.makeText(MainActivity.this, "User login Succssfully", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(), AddGame.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(MainActivity.this, "User login Succssfully", Toast.LENGTH_SHORT).show();
                        Intent k = new Intent(getApplicationContext(),GameList.class);
                        startActivity(k);
                    }
                    }else{

                    Toast.makeText(MainActivity.this, "Login not Succssfully", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}