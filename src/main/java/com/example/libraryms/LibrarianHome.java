package com.example.libraryms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import Database.DatabaseHelper;

public class LibrarianHome extends AppCompatActivity {
    private DatabaseHelper dbHandler;
    private TextView txtmanagebooks, txtmanageclients,txtissue, txtreturn,txtviewrecords;
    private Button Signout;
    private TableLayout librarybookstable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentManager fm = getSupportFragmentManager();
        txtmanagebooks = findViewById(R.id.txtManagebooks);
        txtmanageclients = findViewById(R.id.txtManageclients);
        txtissue = findViewById(R.id.txtIssuebook);
        txtreturn = findViewById(R.id.txtReturnbook);
        txtviewrecords = findViewById(R.id.txtViewrecords);
        Signout = findViewById(R.id.btnSignout);
        librarybookstable = findViewById(R.id.idlibrarybooks);

        Signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LibrarianHome.this,MainActivity.class);
                startActivity(i);
            }
        });
        //to display books management fragment
        txtmanagebooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fm.beginTransaction()
                        .replace(R.id.fragmentContainerViewLibrarian,ManageBooksFragment.class,null)
                        .setReorderingAllowed(true).addToBackStack(null).commit();
            }
        });
        //to display clients management fragment
        txtmanageclients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fm.beginTransaction()
                        .replace(R.id.fragmentContainerViewLibrarian,ManageClientsFragment.class,null)
                        .setReorderingAllowed(true).addToBackStack(null).commit();
            }
        });
        //to display issue book fragment
        txtissue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fm.beginTransaction()
                        .replace(R.id.fragmentContainerViewLibrarian,IssueFragment.class,null)
                        .setReorderingAllowed(true).addToBackStack(null).commit();
            }
        });
        //to display return book fragment
        txtreturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fm.beginTransaction()
                        .replace(R.id.fragmentContainerViewLibrarian,ReturnFragment.class,null)
                        .setReorderingAllowed(true).addToBackStack(null).commit();
            }
        });
        //to display view records fragment
        txtviewrecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fm.beginTransaction()
                        .replace(R.id.fragmentContainerViewLibrarian,ViewRecordsFragment.class,null)
                        .setReorderingAllowed(true).addToBackStack(null).commit();
            }
        });
    }
}
