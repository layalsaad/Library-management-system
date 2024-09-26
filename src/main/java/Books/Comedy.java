package Books;

public class Comedy extends Book{
    private double price = 3.0;
    private int original_amount_of_comedy_books;
    private int current_amount_of_comedy_books;

    public Comedy(String name,String author){
        super(name,author,"Comedy");
        this.original_amount_of_comedy_books = 1;
        this.current_amount_of_comedy_books = original_amount_of_comedy_books;
    }
    public Comedy(String name, String author, int original_amount) {
        super(name,author,"Comedy");
        this.original_amount_of_comedy_books = original_amount;
        this.current_amount_of_comedy_books = original_amount;
    }

    public Comedy(String name, String author, int original_amount_of_comedy_books, int current_amount_of_comedy_books) {
        super(name, author, "Comedy");
        this.original_amount_of_comedy_books = original_amount_of_comedy_books;
        this.current_amount_of_comedy_books = current_amount_of_comedy_books;
    }

    public int getCurrent_amount(){
        return this.current_amount_of_comedy_books;
    }
    public void setCurrent_amount(int current_amount){
        this.current_amount_of_comedy_books=current_amount;
    }
    public int getOriginal_amount(){
        return this.original_amount_of_comedy_books;
    }
    public void setOriginal_amount(int original_amount){
        this.original_amount_of_comedy_books=original_amount;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String getCat() {
        return "Comedy";
    }
}
