import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ContactsManagerRunner {

    public static void main(String[] args) {

        HashMap<String, Contact> contacts = getContacts();
        System.out.println(printContact(contacts));
//        do{
//            initGradesApp(contacts);
//        } while(Input.yesNo("Would you like to search for another student? (y/n)"));
//
//    }
//
//    private static void initGradesApp(HashMap<String, Contact> contacts){
//        System.out.println(contacts.keySet().stream().reduce("", (allNames, currentName) -> {
//            return allNames + "\n -> " + currentName;
//        }));
//
//        String username = Input.getString("\nPlease enter the GitHub username of a student if you want more info: \n");
//
//        if (contacts.containsKey(username)){
//            Contact chosenOne = contacts.get(username);
//            System.out.println("Student Name: " + chosenOne.getName().toUpperCase(Locale.ROOT));
//            //System.out.println(chosenOne.getGradeAverage());
//
//        }else{
//            System.out.println("No username found matching: " + username);
//        }
    }


    private static HashMap<String, Contact> getContacts() {
        Contact contact1 = new Contact("Bob", "210-444-4444");

        Contact contact2 = new Contact("Sally", "218-253-4544");

        Contact contact3 = new Contact("Joe", "719-890-1530");

        HashMap<String, Contact> contacts = new HashMap<>();

//        String student1gh = "billiversustheworld";

        contacts.put("1", contact1);
        contacts.put("2", contact2);
        contacts.put("3", contact3);

        return contacts;
    }

    public static String stringFormatter(String contactProperty) {
        for (int i = contactProperty.length(); i <= 15; i++) {
            contactProperty = contactProperty + " ";
        }
        return contactProperty;
    }

    public void printHeader() {
        System.out.println("      Name      |  Phone Number  ");
        System.out.println("----------------------------------");
    }

    public static void printContact(String contacts) {
        for (Map.Entry<String, Object> person : contacts.entrySet()) {
            System.out.println("key" + person.getKey() + "value" + person.getValue());
        }
//            Contact chosenOne = contacts.get(i);
//            System.out.println(stringFormatter(chosenOne.getName()) + "|" + stringFormatter(chosenOne.getPhoneNumber()) + "|");
    }
}


