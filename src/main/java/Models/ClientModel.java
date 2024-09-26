package Models;

import java.util.ArrayList;
import java.util.List;

import Books.Book;
import Database.DatabaseHelper;
import ObserverDP.Observer;
import Person.Client;

public class ClientModel<T> extends Model<Book> implements Observer {
    private final DatabaseHelper db;
    private final List<Book> books;
    public ClientModel(DatabaseHelper database){
        super(new ArrayList<>());
        //books should get all books borrowed by the client from db
        books=new ArrayList<>();
        this.db = database;
    }
    //this methods gets borrowed books by a client
    public List<Book> getAllBooks(Client c){
        return db.getborrowed(c);
    }
    //those methods are used by the client to modify his/her data
    public void updateBook(Book b,int id) {db.modifyData(b,id);}
    public boolean borrowBook(Book b) {return db.borrow(b) != 0;}
    public boolean returnBook(Book b){return db.retrieve(b) != 0;}

    public double payBook(Client c){
        List<Book> books = getAllBooks(c);
        double total_bill=0;
        for (Book b:books) {
            if(b.hasNext()){
                total_bill += b.getPrice();
                b.next();
            }
        }
        return total_bill;
    }

    @Override
    public void update() {
        db.notifyAll();
    }
}
