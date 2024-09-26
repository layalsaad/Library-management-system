package com.example.libraryms;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import Controllers.LibController;
import Models.LibModel;
import Person.Client;


public class ManageClientsFragment extends Fragment {

    private static final String lib_emailaddress = "admin@gmail.com";
    private static final String lib_paswsword = "librarian";
    private LibModel libmodel;
    private final LibController lib = new LibController(libmodel);
    private String libemailaddress;
    private String libpassword;
    private EditText edtcname,edtcemail,edtcage;
    private Button btnadd,btndelete;

    public ManageClientsFragment() {
        // Required empty public constructor
    }
    public static ManageClientsFragment newInstance(String param1, String param2) {
        ManageClientsFragment fragment = new ManageClientsFragment();
        Bundle args = new Bundle();
        args.putString(lib_emailaddress, param1);
        args.putString(lib_paswsword, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            libemailaddress = getArguments().getString(lib_emailaddress);
            libpassword = getArguments().getString(lib_paswsword);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_manage_clients, container, false);
        edtcname = v.findViewById(R.id.edtclientname);
        edtcemail = v.findViewById(R.id.edtemail);
        edtcage = v.findViewById(R.id.edtage);
        btnadd = v.findViewById(R.id.btnadd);
        btndelete = v.findViewById(R.id.btndelete);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Client c = new Client(edtcname.getText().toString(),Integer.parseInt(edtcage.getText().toString()),edtcemail.getText().toString(),"");
                ((LibModel) lib.getmodel()).adding(c);
            }
        });
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Client c = new Client(edtcname.getText().toString(),Integer.parseInt(edtcage.getText().toString()),edtcemail.getText().toString(),"");
                ((LibModel) lib.getmodel()).removing(c);
            }
        });
        return v;
    }
}