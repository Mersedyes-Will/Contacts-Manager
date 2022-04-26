public class Contact {

    private String name;
    private String phoneNo;

    public Contact(String name, String phoneNo){
        this.name = name;
        this.phoneNo = phoneNo;
    }

    public String getName() {

        return name;
    }

    public String getPhoneNumber() {
        return phoneNo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNo = phoneNumber;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                '}';
    }

}
