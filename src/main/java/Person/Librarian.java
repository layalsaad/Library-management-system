package Person;

import java.util.ArrayList;

public class Librarian extends IPerson{
    private static Librarian obj=null;

    private ArrayList<String> categories;

    private Librarian() {
        super("librarian","admin@gmail.com","librarian");
        categories.add("Comedy");
        categories.add("Drama");
        categories.add("Literature");
        categories.add("Science fiction");
        this.getInstance();
    }
    public Librarian getInstance(){
        if(obj == null){
            obj = new Librarian();
        }
        return obj;
    }

    public ArrayList<String> getCategories() {
        return categories;
    }

    @Override
    public int getAge() {
        return 0;
    }

    @Override
    public void setAge(int age) {

    }
}
