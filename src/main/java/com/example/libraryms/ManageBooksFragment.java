package com.example.libraryms;

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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ManageBooksFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ManageBooksFragment extends Fragment {
    private static final String lib_emailaddress = "admin@gmail.com";
    private static final String lib_paswsword = "librarian";
    private LibModel libmodel;
    private final LibController lib = new LibController(libmodel);
    private String libemailaddress;
    private String libpassword;
    private EditText edtbname,edtbauthor,edtquantity;
    private RadioGroup rbcat;
    private RadioButton rb;
    private Button btnadd,btnmodify,btndelete;

    public ManageBooksFragment() {
        // Required empty public constructor
    }

    public static ManageBooksFragment newInstance(String param1, String param2) {
        ManageBooksFragment fragment = new ManageBooksFragment();
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
        View v = inflater.inflate(R.layout.fragment_manage_books, container, false);
        edtbname = v.findViewById(R.id.edtbookname);
        edtbauthor = v.findViewById(R.id.edtauthor);
        edtquantity = v.findViewById(R.id.edtamount);
        rbcat = v.findViewById(R.id.idrgcat);
        rb = v.findViewById(rbcat.getCheckedRadioButtonId());
        btnadd = v.findViewById(R.id.btnadd);
        btnmodify = v.findViewById(R.id.btnmodify);
        btndelete = v.findViewById(R.id.btndelete);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book b;
                switch (rb.getText().toString()){
                    case "Comedy":
                        b = new Comedy(edtbname.getText().toString(),edtbauthor.getText().toString(),Integer.parseInt(edtquantity.getText().toString()));
                        ((LibModel) lib.getmodel()).adding(b);
                    case "Drama":
                        b = new Drama(edtbname.getText().toString(),edtbauthor.getText().toString(),Integer.parseInt(edtquantity.getText().toString()));
                        ((LibModel) lib.getmodel()).adding(b);
                    case "Literature":
                        b = new Literature(edtbname.getText().toString(),edtbauthor.getText().toString(),Integer.parseInt(edtquantity.getText().toString()));
                        ((LibModel) lib.getmodel()).adding(b);
                    case "Science fiction":
                        b = new ScienceFiction(edtbname.getText().toString(),edtbauthor.getText().toString(),Integer.parseInt(edtquantity.getText().toString()));
                        ((LibModel) lib.getmodel()).adding(b);
                }
            }
        });
        btnmodify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book b;
                switch (rb.getText().toString()){
                    case "Comedy":
                        b = new Comedy(edtbname.getText().toString(),edtbauthor.getText().toString(),Integer.parseInt(edtquantity.getText().toString()));
                        ((LibModel) lib.getmodel()).modifying(b,b.getId());
                    case "Drama":
                        b = new Drama(edtbname.getText().toString(),edtbauthor.getText().toString(),Integer.parseInt(edtquantity.getText().toString()));
                        ((LibModel) lib.getmodel()).modifying(b,b.getId());
                    case "Literature":
                        b = new Literature(edtbname.getText().toString(),edtbauthor.getText().toString(),Integer.parseInt(edtquantity.getText().toString()));
                        ((LibModel) lib.getmodel()).modifying(b,b.getId());
                    case "Science fiction":
                        b = new ScienceFiction(edtbname.getText().toString(),edtbauthor.getText().toString(),Integer.parseInt(edtquantity.getText().toString()));
                        ((LibModel) lib.getmodel()).modifying(b,b.getId());
                }
            }
        });
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book b;
                switch (rb.getText().toString()){
                    case "Comedy":
                        b = new Comedy(edtbname.getText().toString(),edtbauthor.getText().toString(),Integer.parseInt(edtquantity.getText().toString()));
                        ((LibModel) lib.getmodel()).removing(b);
                    case "Drama":
                        b = new Drama(edtbname.getText().toString(),edtbauthor.getText().toString(),Integer.parseInt(edtquantity.getText().toString()));
                        ((LibModel) lib.getmodel()).removing(b);
                    case "Literature":
                        b = new Literature(edtbname.getText().toString(),edtbauthor.getText().toString(),Integer.parseInt(edtquantity.getText().toString()));
                        ((LibModel) lib.getmodel()).removing(b);
                    case "Science fiction":
                        b = new ScienceFiction(edtbname.getText().toString(),edtbauthor.getText().toString(),Integer.parseInt(edtquantity.getText().toString()));
                        ((LibModel) lib.getmodel()).removing(b);
                }
            }
        });
        return v;
    }
}