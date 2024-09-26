package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Books.Book;
import Books.Comedy;
import Books.Drama;
import Books.Literature;
import Books.ScienceFiction;
import Models.LibModel;
import ObserverDP.Observer;
import Person.Client;

public class DatabaseHelper<T> extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "LibraryMS.db";
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_TABLE_CLIENTS = "CREATE TABLE clients ( id INTEGER PRIMARY KEY AUTOINCREMENT , name TEXT , age INTEGER , emailaddress TEXT , password TEXT )";
    private static final String CREATE_TABLE_BOOKS = "CREATE TABLE books ( id INTEGER PRIMARY KEY AUTOINCREMENT , name TEXT , author TEXT , originalamount TEXT , currentamount TEXT , FOREIGN KEY ( categoryid ) REFERENCES categories ( id ))";
    private static final String CREATE_TABLE_CATEGORIES = "CREATE TABLE categories ( id INTEGER PRIMARY KEY AUTOINCREMENT , name TEXT )";
    private static final String CREATE_TABLE_OPERATIONS = "CREATE TABLE operations ( id INTEGER PRIMARY KEY AUTOINCREMENT , issuedate DATE , duedate DATE , FOREIGN KEY ( bookid ) REFERENCES books ( id ) , FOREIGN KEY ( clientid ) REFERENCES clients ( id ))";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CLIENTS);
        db.execSQL(CREATE_TABLE_BOOKS);
        db.execSQL(CREATE_TABLE_CATEGORIES);
        db.execSQL(CREATE_TABLE_OPERATIONS);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS books");
        db.execSQL("DROP TABLE IF EXISTS clients");
        db.execSQL("DROP TABLE IF EXISTS categories");
        db.execSQL("DROP TABLE IF EXISTS operations");
        onCreate(db);
    }
    // Insert data method used by librarian
    public long insertData(T t) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        LibModel lib = null;


        if(t instanceof Client){
            contentValues.put("name", ((Client) t).getName());
            contentValues.put("age", ((Client) t).getAge());
            contentValues.put("emailaddress", ((Client) t).getemailaddress());
            contentValues.put("password",((Client) t).getPassword());
            long id = db.insert("clients", null, contentValues);
            lib.getInstance().registerObserver((Observer) t);
            return id;
        }
        if(t instanceof Book){
            contentValues.put("name", ((Book) t).getName());
            contentValues.put("author", ((Book) t).getAuthor());
            contentValues.put("originalamount", ((Book) t).getOriginal_amount());
            contentValues.put("currentamount",((Book) t).getCurrent_amount());
            long id = db.insert("books", null, contentValues);
            return id;
        }
        else{
            contentValues.put("name",(String) t);
            long id = db.insert("categories",null,contentValues);
            return id;
        }
    }
    //used by librarian
    public int modifyData(T t, int id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        if(t instanceof Book){
            cv.put("name",((Book) t).getName());
            cv.put("author", ((Book) t).getAuthor());
            cv.put("originalamount",((Book) t).getOriginal_amount());
            cv.put("currentamount",((Book) t).getCurrent_amount());
            int rowsAffected = db.update("books",cv,"id = ? ",new String[]{String.valueOf(id)});
            return rowsAffected;
        }
        if(t instanceof Client){
            cv.put("name", ((Client) t).getName());
            cv.put("age", ((Client) t).getAge());
            cv.put("emailaddress", ((Client) t).getemailaddress());
            cv.put("password",((Client) t).getPassword());
            int rowsAffected = db.update("clients",cv,"id = ? ",new String[]{String.valueOf(id)});
            return rowsAffected;
        }
        else{
            cv.put("name",(String) t);
            int rowsAffected = db.update("categories",cv,"id = ? ",new String[]{String.valueOf(id)});
            return rowsAffected;
        }

    }
    //used by the librarian
    public int removeData(T t){
        SQLiteDatabase db = this.getWritableDatabase();
        if(t instanceof Book){
            int id = db.delete("books","name = ? ",new String[] {((Book) t).getName()});
            return id;
        }
        if(t instanceof Client){
            int id = db.delete("clients","id = ? ",new String[] {String.valueOf(((Client) t).getId())});
            return id;
        }
        else{
            int id = db.delete("categories","id = ? ",new String[] {(String) t});
            return id;
        }
    }
    //used by librarian to issue a book for a specific client
    public long issue(Book b, Client c, Date issue, Date due){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("currentamount",b.getCurrent_amount()-1);
        db.update("books",cv,"id = ? ",new String[]{String.valueOf(b.getId())});
        ContentValues cvop = new ContentValues();
        cvop.put("issuedate",issue.toString());
        cvop.put("duedate",due.toString());
        cvop.put("bookid",b.getId());
        cvop.put("clientid", c.getId());
        long id = db.insert("operations",null,cvop);
        return id;
    }
    //used by librarian to return a book that was borrowed to the library
    public long putback(Book b, Client c){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("currentamount",b.getCurrent_amount()+1);
        db.update("books",cv,"id = ? ",new String[]{String.valueOf(b.getId())});
        long id = db.delete("operations","bookid = ? AND clientid = ?",new String[]{String.valueOf(b.getId()),String.valueOf(c.getId())});
        return id;
    }
    //used by client to borrow a book
    public int borrow(Book b){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("currentamount",b.getCurrent_amount()-1);
        int rowsaffected = db.update("books",cv,"id = ? ",new String[]{String.valueOf(b.getId())});
        return rowsaffected;
    }
    //used by client to return a book
    public int retrieve(Book b){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("currentamount",b.getCurrent_amount()+1);
        int rowsAffected = db.update("books",cv,"id = ? ",new String[]{String.valueOf(b.getId())});
        return rowsAffected;
    }
    //method to get all books and clients will be used to view records by librarian
    public List<T> getData(){
        List<T> items = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String querybooks = "SELECT * FROM books";
        Cursor c = db.rawQuery(querybooks,null);
        if(c.moveToFirst()){
            do{
                String q = "SELECT * FROM categories";
                Cursor cq = db.rawQuery(q,null);
                if(cq.moveToFirst()){
                    String category = cq.getString(0);
                    Book b;
                    if(category.equals("Comedy")){
                        b = new Comedy(c.getString(1),c.getString(2),
                                Integer.parseInt(c.getString(3)),Integer.parseInt(c.getString(4)));
                    }
                    else if(category.equals("Drama")){
                        b = new Drama(c.getString(1),c.getString(2),
                                Integer.parseInt(c.getString(3)),Integer.parseInt(c.getString(4)));
                    }
                    else if(category.equals("Literature")){
                        b = new Literature(c.getString(1), c.getString(2),
                                Integer.parseInt(c.getString(3)), Integer.parseInt(c.getString(4)));
                    }
                    else{
                        b = new ScienceFiction(c.getString(1), c.getString(2),
                                Integer.parseInt(c.getString(3)), Integer.parseInt(c.getString(4)));
                    }
                    items.add((T) b);
                }
            }while(c.moveToNext());
        }
        String queryclients = "SELECT * FROM clients";
        Cursor c1 = db.rawQuery(queryclients,null);
        if(c1.moveToFirst()){
            do{
                Client client = new Client(c1.getString(1),Integer.parseInt(c1.getString(2)),c1.getString(3),c1.getString(4));
                items.add((T) client);
            }while (c1.moveToNext());
        }
        return items;
    }
    //used by client to see the list of books he has borrowed
    public List<Book> getborrowed(Client c){
        List<Book> borrowedbooks = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = " SELECT bookid FROM operations WHERE clientid = " + c.getId();
        Cursor cop = db.rawQuery(query,null);
        if(cop.moveToFirst()){
            do{
                int id = Integer.parseInt(cop.getString(0));
                String querybooks = "SELECT * FROM books WHERE id = "+id;
                Cursor cbook = db.rawQuery(querybooks,null);
                if(cbook.moveToFirst()){
                    do{
                        String q = "SELECT * FROM categories";
                        Cursor cq = db.rawQuery(q,null);
                        if(cq.moveToFirst()){
                            String category = cq.getString(0);
                            Book b;
                            if(category.equals("Comedy")){
                                b = new Comedy(cbook.getString(1),cbook.getString(2),
                                        Integer.parseInt(cbook.getString(3)),Integer.parseInt(cbook.getString(4)));
                            }
                            else if(category.equals("Drama")){
                                b = new Drama(cbook.getString(1),cbook.getString(2),
                                        Integer.parseInt(cbook.getString(3)),Integer.parseInt(cbook.getString(4)));
                            }
                            else if(category.equals("Literature")){
                                b = new Literature(cbook.getString(1), cbook.getString(2),
                                        Integer.parseInt(cbook.getString(3)), Integer.parseInt(cbook.getString(4)));
                            }
                            else{
                                b = new ScienceFiction(cbook.getString(1), cbook.getString(2),
                                        Integer.parseInt(cbook.getString(3)), Integer.parseInt(cbook.getString(4)));
                            }
                            borrowedbooks.add(b);
                        }
                    }while(cbook.moveToNext());
                }
            }while(cop.moveToNext());
        }
        return borrowedbooks;
    }
}
