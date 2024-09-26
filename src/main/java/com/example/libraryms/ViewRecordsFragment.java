package com.example.libraryms;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.List;

import Books.Book;
import Controllers.LibController;
import Models.LibModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ViewRecordsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewRecordsFragment<T> extends Fragment {
    private static final String lib_emailaddress = "admin@gmail.com";
    private static final String lib_paswsword = "librarian";
    private LibModel libmodel;
    private final LibController lib = new LibController(libmodel);
    private String libemailaddress;
    private String libpassword;
    private TableLayout tablebooks,tableclients;
    private TableRow bookrow,clientrow;
    public ViewRecordsFragment() {
        // Required empty public constructor
    }
    public static ViewRecordsFragment newInstance(String param1, String param2) {
        ViewRecordsFragment fragment = new ViewRecordsFragment();
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
        View v = inflater.inflate(R.layout.fragment_view_records, container, false);
        tablebooks = v.findViewById(R.id.idlibrarybooks);
        tableclients = v.findViewById(R.id.idlibraryclients);
        bookrow = v.findViewById(R.id.firstbooksRow);
        clientrow = v.findViewById(R.id.firstclientRow);
        filltables();
        return v;
    }

    public void filltables(){
        List<T> data = ((LibModel) lib.getmodel()).getbooksclients();
        for (T t:data) {
            if(t instanceof Book){
                bookrow = new TableRow(requireContext());
            }
            else{
                clientrow = new TableRow(requireContext());
            }
        }
    }
}