package com.example.libraryms;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.List;

import Books.Book;
import Books.Comedy;
import Books.Drama;
import Books.Literature;
import Books.ScienceFiction;
import Controllers.LibController;
import Database.DatabaseHelper;
import Models.ClientModel;
import Models.LibModel;

public class LibBooksFragment<T> extends Fragment {

    private static final String client_email = "";
    private static final String client_pass = "";
    private LibModel libmodel;
    private final DatabaseHelper db = new DatabaseHelper<>(requireContext());
    private final LibController lib = new LibController(libmodel);
    private String clientemail;
    private String clientpass;
    private Button btnadd,btnmodify,btnputback;
    private ClientModel cm;
    private TableLayout tablebooks;
    private TableRow bookrow;

    public LibBooksFragment() {
        // Required empty public constructor
    }

    public static LibBooksFragment newInstance(String param1, String param2) {
        LibBooksFragment fragment = new LibBooksFragment();
        Bundle args = new Bundle();
        args.putString(client_email, param1);
        args.putString(client_pass, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            clientemail = getArguments().getString(client_email);
            clientpass = getArguments().getString(client_pass);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_lib_books, container, false);
        cm = new ClientModel(db);
        tablebooks = v.findViewById(R.id.idlibrarybooks);
        bookrow = v.findViewById(R.id.firstbooksRow);
        btnadd = v.findViewById(R.id.btnadd);
        btnmodify = v.findViewById(R.id.btnmodify);
        btnputback = v.findViewById(R.id.btnputback);
        filltable();
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookrow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TableRow clickedRow = (TableRow) v;
                        String cat = clickedRow.getChildAt(4).toString();
                        Book b;
                        switch (cat){
                            case "Comedy":
                                b = new Comedy(clickedRow.getChildAt(0).toString(),clickedRow.getChildAt(1).toString());
                                cm.borrowBook(b);
                            case "Drama":
                                b = new Drama(clickedRow.getChildAt(0).toString(),clickedRow.getChildAt(1).toString());
                                cm.borrowBook(b);
                            case "Literature":
                                b = new Literature(clickedRow.getChildAt(0).toString(),clickedRow.getChildAt(1).toString());
                                cm.borrowBook(b);
                            case "Science fiction":
                                b = new ScienceFiction(clickedRow.getChildAt(0).toString(),clickedRow.getChildAt(1).toString());
                                cm.borrowBook(b);
                        }
                    }
                });
            }
        });
        btnmodify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookrow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TableRow clickedRow = (TableRow) v;
                        String cat = clickedRow.getChildAt(4).toString();
                        Book b;
                        switch (cat){
                            case "Comedy":
                                b = new Comedy(clickedRow.getChildAt(0).toString(),clickedRow.getChildAt(1).toString());
                                cm.updateBook(b,b.getId());
                            case "Drama":
                                b = new Drama(clickedRow.getChildAt(0).toString(),clickedRow.getChildAt(1).toString());
                                cm.updateBook(b,b.getId());
                            case "Literature":
                                b = new Literature(clickedRow.getChildAt(0).toString(),clickedRow.getChildAt(1).toString());
                                cm.updateBook(b,b.getId());
                            case "Science fiction":
                                b = new ScienceFiction(clickedRow.getChildAt(0).toString(),clickedRow.getChildAt(1).toString());
                                cm.updateBook(b,b.getId());
                        }
                    }
                });
            }
        });
        btnputback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookrow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TableRow clickedRow = (TableRow) v;
                        String cat = clickedRow.getChildAt(4).toString();
                        Book b;
                        switch (cat){
                            case "Comedy":
                                b = new Comedy(clickedRow.getChildAt(0).toString(),clickedRow.getChildAt(1).toString());
                                cm.returnBook(b);
                            case "Drama":
                                b = new Drama(clickedRow.getChildAt(0).toString(),clickedRow.getChildAt(1).toString());
                                cm.returnBook(b);
                            case "Literature":
                                b = new Literature(clickedRow.getChildAt(0).toString(),clickedRow.getChildAt(1).toString());
                                cm.returnBook(b);
                            case "Science fiction":
                                b = new ScienceFiction(clickedRow.getChildAt(0).toString(),clickedRow.getChildAt(1).toString());
                                cm.returnBook(b);
                        }
                    }
                });
            }
        });
        return v;
    }

    private void filltable() {
        List<T> data = ((LibModel) lib.getmodel()).getbooksclients();
        for (T t:data) {
            if(t instanceof Book){
                bookrow = new TableRow(requireContext());
            }
        }
    }
}