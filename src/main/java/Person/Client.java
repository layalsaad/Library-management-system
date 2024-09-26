package Person;

import Database.DatabaseHelper;
import ObserverDP.Observer;

public class Client extends IPerson {
    private int age;

    public Client(String name, String emailaddress) {
        super(name, emailaddress);
    }

    public Client(String name, int age, String emailaddress, String password) {
        super(name,emailaddress,password);
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
