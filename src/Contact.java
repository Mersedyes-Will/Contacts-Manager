public class Contact {

    private int id;
    private String name;
    private String phoneNumber;

    public Contact(int id){
        this.id = id;
    }

    public int getId(int id){
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public static String stringConstructor (String contactProperty) {
        for (int i = contactProperty.length(); i <= 15; i++) {
            contactProperty = contactProperty + " ";
        }
        return contactProperty;
    }

    public void printContact(){
//            System.out.println("      Name      |  Phone Number  ");
//            System.out.println("----------------------------------");
            System.out.println(stringConstructor(getName()) + "|" + stringConstructor(getPhoneNumber()) + "|");
    }








}
