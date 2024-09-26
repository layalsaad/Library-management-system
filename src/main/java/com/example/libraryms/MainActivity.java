package com.example.libraryms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Database.DatabaseHelper;
import Person.Client;


public class MainActivity extends AppCompatActivity {
    private Button SignIn;
    private Button SignUp;
    private EditText Username,emailaddress,Age,Password;
    private DatabaseHelper dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SignIn=findViewById(R.id.btnSignIn);
        SignUp=findViewById(R.id.btnSignUp);
        Username=findViewById(R.id.idEdtuserName);
        emailaddress=findViewById(R.id.idEdtemailAddress);
        Age=findViewById(R.id.idEdtAge);
        Password=findViewById(R.id.idEdtPassword);

        SignUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Client client=new Client(Username.getText().toString(),Integer.parseInt(Age.getText().toString()),emailaddress.getText().toString(),Password.getText().toString());

                if(Integer.parseInt(Age.getText().toString())>15) {
                    dbHandler = new DatabaseHelper(MainActivity.this);
                    dbHandler.insertData(client);
                    Toast.makeText(MainActivity.this, "User successfully added", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Age is invalid, it should be an integer greater than 15", Toast.LENGTH_SHORT).show();
                }

            }
        });
        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SignInActivity.class);
                startActivity(intent);
            }
        });

    }
}