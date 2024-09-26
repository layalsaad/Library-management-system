package com.example.libraryms;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import Controllers.LibController;
import Database.DatabaseHelper;
import Models.LibModel;
import Person.Client;
import Person.Librarian;

public class SignInActivity extends AppCompatActivity {
    private Button SignUp, SignIn;
    private EditText edtemail, edtpass;
    private DatabaseHelper<Client> dbHandler;
    private Librarian librarian;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        dbHandler = new DatabaseHelper<>(SignInActivity.this);
        SignUp= findViewById(R.id.btnSignUp);
        SignIn = findViewById(R.id.btnsignin);
        edtemail = findViewById(R.id.edtemail);
        edtpass = findViewById(R.id.edtpass);
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignInActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtemail.getText().toString();
                String pass = edtpass.getText().toString();
                if((librarian.getInstance().getemailaddress()).equals(email) && (librarian.getInstance().getPassword()).equals(pass)){
                    Intent i = new Intent(SignInActivity.this,LibrarianHome.class);
                    startActivity(i);
                }
                checkaccount(email,pass);
            }
        });

    }
    public void checkaccount(String email, String pass){
        SQLiteDatabase db = dbHandler.getReadableDatabase();
        String query = " SELECT * FROM clients WHERE emailaddress = ? AND password = ? ";
        Cursor c = db.rawQuery(query,new String[]{email,pass});
        boolean exists = c.getCount()>0;
        c.close();
        if(exists){
            Intent i  = new Intent(SignInActivity.this,UserHome.class);
            startActivity(i);
        }
        else{
            Toast.makeText(SignInActivity.this, "email address or password is incorrect", Toast.LENGTH_SHORT).show();
        }
    }
}
