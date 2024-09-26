package Person;

public abstract class IPerson {
    private int id;
    private String name;
    //private String address;
    private String emailaddress;
    private String password;

    public IPerson(String name, String emailaddress) {
        this.name = name;
        this.emailaddress = emailaddress;
    }

    public IPerson(String name, String emailaddress, String password) {
        this.name = name;
        //this.address = address;
        this.emailaddress = emailaddress;
        this.password = password;
    }

    public IPerson() {}

    public int getId() { return id; }

    public void setId(int id) {this.id = id;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }*/

    public String getemailaddress() {
        return emailaddress;
    }

    public void setemailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public abstract int getAge();

    public abstract void setAge(int age);
}
