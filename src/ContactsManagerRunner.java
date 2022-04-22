import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ContactsManagerRunner {

    public static void main(String[] args) {

        HashMap<String, Contact> contacts = getContacts();

        viewAllContacts(contacts);

    }

    private static HashMap<String, Contact> getContacts() {
        Contact contact1 = new Contact("Bob", "210-444-4444");

        Contact contact2 = new Contact("Sally", "218-253-4544");

        Contact contact3 = new Contact("Joe", "719-890-1530");

        Contact contact4 = new Contact("Mersedyes Livingston", "719-890-1530");

        HashMap<String, Contact> contacts = new HashMap<>();

        contacts.put("1", contact1);
        contacts.put("2", contact2);
        contacts.put("3", contact3);
        contacts.put("4", contact4);

        return contacts;
    }

    public static String nameFormatter(String contactProperty) {
        for (int i = contactProperty.length(); i <= 24; i++) {
            contactProperty = contactProperty + " ";
        }
        return contactProperty;
    }

    public static String phoneNumberFormatter(String contactProperty) {
        for (int i = contactProperty.length(); i <= 12; i++) {
            contactProperty = contactProperty + " ";
        }
        return contactProperty;
    }

    public static void printHeader() {
        System.out.println("Name                     |  Phone Number  |");
        System.out.println("-------------------------------------------");
    }

    public static void printContact(HashMap<String, Contact> contacts) {
        for (Map.Entry<String, Contact> person : contacts.entrySet()) {

            Contact chosenOne = person.getValue();

            System.out.println(nameFormatter(chosenOne.getName()) + "|  " + phoneNumberFormatter(chosenOne.getPhoneNumber()) + " |");

        }

    }

    public static void viewAllContacts (HashMap<String, Contact> contacts) {
        printHeader();
        printContact(contacts);
    }

    public static void getNewKey () {

    }

    public static void addContact (HashMap<String, Contact> contacts) {
        String uniqueID = UUID.randomUUID().toString();

        System.out.println(uniqueID);
    }
}


