package com.example.libraryms;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import Books.Book;
import Database.DatabaseHelper;
import Models.ClientModel;
import Person.Client;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserBooksFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserBooksFragment extends Fragment {

    private static final String client_email = "";
    private static final String client_pass = "";
    private final DatabaseHelper db = new DatabaseHelper<>(requireContext());
    private ClientModel cm;
    private TableLayout tablebooks;
    private TableRow bookrow;
    private Button btnpayment;
    private TextView txtfees;
    private String clientemail;
    private String clientpass;

    public UserBooksFragment() {
        // Required empty public constructor
    }
    public static UserBooksFragment newInstance(String param1, String param2) {
        UserBooksFragment fragment = new UserBooksFragment();
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
        View v = inflater.inflate(R.layout.fragment_user_books, container, false);
        tablebooks = v.findViewById(R.id.iduserbooks);
        bookrow = v.findViewById(R.id.firstbooksRow);
        btnpayment = v.findViewById(R.id.btnpay);
        txtfees = v.findViewById(R.id.txtfees);
        cm = new ClientModel<>(db);
        Client c = new Client("",clientemail);
        List<Book> borrowed = cm.getAllBooks(c);
        for (Book b:borrowed) {
            bookrow = new TableRow(requireContext());
        }
        btnpayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double value = cm.payBook(c);
                txtfees.setText(value.toString());
            }
        });
        return v;
    }
}