package Books;

import java.util.List;

import IteratorDP.Iterator;

public abstract class Book implements Iterator {
    private String name;
    private String author;
    private int id;
    private int original_amount;
    private int current_amount;
    private String category;
    private List<Book> books;
    private static int currentPosition=0;

    public Book(String name, String author,String category) {
        this.name = name;
        this.author = author;
        this.category = category;
        this.original_amount = 1;
        this.current_amount = this.original_amount;
    }

    public Book(String name, String author,int original_amount, int current_amount, String category) {
        this.name = name;
        this.author = author;
        this.category = category;
        this.original_amount = original_amount;
        this.current_amount = current_amount;
    }

    public Book() {}
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }
    public String getName(){
        return  this.name;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getAuthor(){
        return  this.author;
    }
    public void setAuthor(String author){
        this.author=author;
    }
    public int getOriginal_amount() {
        return this.original_amount;
    }

    public void setOriginal_amount(int original_amount) {
        this.original_amount = original_amount;
    }

    public abstract int getCurrent_amount();

    public abstract void setCurrent_amount(int current_amount);

    public abstract double getPrice();

    public abstract void setPrice(double price);
    public abstract String getCat();
    public void setCat(String c){
        this.category = c;
    }
    @Override
    public boolean hasNext()
    {
        return currentPosition<books.size();
    }
    @Override
    public void next()
    {
        currentPosition++;
    }
    @Override
    public Book getNext()
    {
        return books.get(currentPosition);
    }
}
