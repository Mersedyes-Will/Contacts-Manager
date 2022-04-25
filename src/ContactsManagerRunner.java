import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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

    public static String nameFormatter(String name) {
        for (int i = name.length(); i <= 24; i++) {
            name = name + " ";
        }
        return name;
    }

    public static String phoneNumberFormatter(String phoneNumber) {
        for (int i = phoneNumber.length(); i <= 12; i++) {
            phoneNumber = phoneNumber + " ";
        }
        return phoneNumber;
    }

    public static void printHeader() {
        System.out.println("Name                     |  Phone Number  |");
        System.out.println("-------------------------------------------");
    }

    public static void printContacts(HashMap<String, Contact> contacts) {
        for (Map.Entry<String, Contact> person : contacts.entrySet()) {

            Contact chosenOne = person.getValue();

            System.out.println(nameFormatter(chosenOne.getName()) + "|  " + phoneNumberFormatter(chosenOne.getPhoneNumber()) + " |");

        }

    }

    public static void viewAllContacts (HashMap<String, Contact> contacts) {
        printHeader();
        printContacts(contacts);
    }

    public static String getNewKey (HashMap<String, Contact> contacts) {
        String newKey = "";
        for (int i = 1; i <= contacts.size(); i++) {
            String currentKey = String.valueOf(i);
            if (contacts.containsKey(currentKey)) {

            } else {
                newKey = currentKey;
            }
        }
        return newKey;
    }

    public static void addContact (HashMap<String, Contact> contacts) {

    }

    public static int displayMenu(){
        System.out.println("-----------------------------------------");
        System.out.println("\nGood day, please select a menu item:");
        System.out.println("  1. View contacts");
        System.out.println("  2. Add a new contact");
        System.out.println("  3. Search a contact by name");
        System.out.println("  4. Delete an existing contact");
        System.out.println("  5. Exit");
        System.out.println("\nEnter option (1, 2, 3, 4, or 5): ");
        System.out.println("-----------------------------------------");

        Scanner sc = new Scanner(System.in);
        int selectChoice = sc.nextInt();
        return selectChoice;
    }
}


