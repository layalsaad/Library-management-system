package com.example.libraryms;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import Books.Book;
import Books.Comedy;
import Books.Drama;
import Books.Literature;
import Books.ScienceFiction;
import Controllers.LibController;
import Models.LibModel;
import Person.Client;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReturnFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReturnFragment extends Fragment {

    private static final String lib_emailaddress = "admin@gmail.com";
    private static final String lib_paswsword = "librarian";
    private String libemailaddress;
    private String libpassword;
    private LibModel libmodel;
    private final LibController lib = new LibController(libmodel);
    private EditText edtcname, edtcemail,edtbname;
    private RadioGroup rbcat;
    private RadioButton rb;
    private Button btnreturn;

    public ReturnFragment() {
        // Required empty public constructor
    }
    public static ReturnFragment newInstance(String param1, String param2) {
        ReturnFragment fragment = new ReturnFragment();
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
        View v = inflater.inflate(R.layout.fragment_return, container, false);
        edtcname = v.findViewById(R.id.edtclientname);
        edtcemail = v.findViewById(R.id.edtclientemail);
        edtbname = v.findViewById(R.id.edtbookname);
        rbcat = v.findViewById(R.id.idrgcat);
        rb = v.findViewById(rbcat.getCheckedRadioButtonId());
        btnreturn = v.findViewById(R.id.btnreturn);
        btnreturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Client c = new Client(edtcname.getText().toString(),edtcemail.getText().toString());
                Book b;
                switch (rb.getText().toString()){
                    case "Comedy":
                        b = new Comedy(edtbname.getText().toString(),"");
                        ((LibModel) lib.getmodel()).returnBook(b,c);
                    case "Drama":
                        b = new Drama(edtbname.getText().toString(),"");
                        ((LibModel) lib.getmodel()).returnBook(b,c);
                    case "Literature":
                        b = new Literature(edtbname.getText().toString(),"");
                        ((LibModel) lib.getmodel()).returnBook(b,c);
                    case "Science fiction":
                        b = new ScienceFiction(edtbname.getText().toString(),"");
                        ((LibModel) lib.getmodel()).returnBook(b,c);
                }
            }
        });
        return v;
    }
}