package com.example.libraryms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class UserHome extends AppCompatActivity {
    private TextView txtbooks, txtmybooks;
    private Button Signout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentManager fm = getSupportFragmentManager();
        txtbooks = findViewById(R.id.txtBooks);
        txtmybooks = findViewById(R.id.txtMybooks);
        Signout = findViewById(R.id.btnSignout);
        Signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UserHome.this,MainActivity.class);
                startActivity(i);
            }
        });
        //display the library books fragment
        txtbooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fm.beginTransaction().replace(R.id.fragmentContainerViewUser,LibBooksFragment.class,null)
                        .setReorderingAllowed(true).addToBackStack(null).commit();
            }
        });
        //display the user books fragment
        txtmybooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fm.beginTransaction().replace(R.id.fragmentContainerViewUser,UserBooksFragment.class,null)
                        .setReorderingAllowed(true).addToBackStack(null).commit();
            }
        });
    }
}
