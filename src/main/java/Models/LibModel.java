package Models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Books.Book;
import Database.DatabaseHelper;
import ObserverDP.Observable;
import ObserverDP.Observer;
import Person.Client;
import Person.Librarian;

public class LibModel<T> extends Model implements Observable {
    private final DatabaseHelper db;
    private final ArrayList<T> items;
    private final ArrayList<Observer> observers = new ArrayList<>();

    private LibModel(DatabaseHelper db) {
        super(new ArrayList<T>());
        this.getInstance();
        this.items = new ArrayList<T>();
        this.db = db;
    }
    //singleton used to ensure that the account of the librarian is unique and cannot have more than one account as admin
    public LibModel<T> getInstance()
    {
        if (this != null){
            return this;
        }
        return new LibModel(db);
    }
    public List<T> getbooksclients(){
        return db.getData();
    }
    public boolean issueBook(Book b, Client c, Date issue, Date due){
        return db.issue(b, c, issue, due) != -1;
    }
    public boolean returnBook(Book b, Client c){
        return db.putback(b, c) != -1;
    }

    //adds book, category, or client
    public boolean adding(T t)
    {
        return db.insertData(t) != -1;
    }

    //removes book, category, or client
    public boolean removing(T t){
        return db.removeData(t) != 0;
    }

    //modifiess book, category, or client
    public boolean modifying(T t, int id){
        return db.modifyData(t, id) != 0;
    }
    @Override
    public void registerObserver(Observer ob)
    {
        observers.add(ob);
    }
    @Override
    public void unregisterObserver(Observer ob)
    {
        observers.remove(ob);
    }
    //lambda used to notify observers that are clients
    @Override
    public void notifyObservers()
    {
        observers.forEach((item) -> item.update());
    }

    public void dataChanged(){
        //update database
        notifyObservers();
    }
}
