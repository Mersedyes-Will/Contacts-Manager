import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;



public class ContactsManagerRunner {

    public static HashMap<String, Contact> contacts = getContacts();

    public static void main(String[] args) {

        executeChoice(getChoice());
//        FileHelper.tryWriteFile(FileHelper.tryMakeFileDirectory(), contacts.values().stream().toList());

    }

    public static String nextId (HashMap<String, Contact> contacts) {
        String nextId = String.valueOf(contacts.size() + 1);
        return nextId;
    }

    private static HashMap<String, Contact> getContacts() {

        HashMap<String, Contact> contacts = new HashMap<>();

        for (Contact contactee : FileHelper.tryReadFile(FileHelper.tryMakeFileDirectory())) {
            contacts.put(nextId(contacts), contactee);
        }
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
        System.out.println("\nName                     |  Phone Number  |");
        System.out.println("-------------------------------------------");
    }

    public static void printContacts(HashMap<String, Contact> contacts) {
        for (Map.Entry<String, Contact> person : contacts.entrySet()) {
            Contact chosenOne = person.getValue();
            System.out.println(nameFormatter(chosenOne.getName()) + "|  " + phoneNumberFormatter(chosenOne.getPhoneNumber()) + " |");
        }
    }

    public static void previewContact(String name, String phoneNumber) {
        System.out.println(nameFormatter(name) + "|  " + phoneNumberFormatter(phoneNumber) + " |");
    }

    public static void viewContacts(HashMap<String, Contact> contacts) {
        printHeader();
        printContacts(contacts);
    }

    public static int getChoice() {
        System.out.println("\n-----------------------------------------");
        System.out.println("Good day, please select a menu item:");
        System.out.println("  1. View contacts");
        System.out.println("  2. Add a new contact");
        System.out.println("  3. Search a contact by name");
        System.out.println("  4. Delete an existing contact");
        System.out.println("  5. Exit");
        System.out.println("\nEnter option (1, 2, 3, 4, or 5): ");
        System.out.println("-----------------------------------------");
        System.out.println("");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        System.out.println("");
        return choice;
    }

    public static void executeChoice(int choice) {
        for (int i = 1; i <= 5; i++) {
            if (choice == 1) {
                viewContacts(contacts);
                System.out.println("\n");
                executeChoice(getChoice());
                break;
            } else if (choice == 2) {
                addContact();
                System.out.println(contacts.values().size());
                break;
            } else if (choice == 3){
                searchContact(contacts);
                break;
            } else if (choice == 4){
                deleteContact(contacts);
                break;
            } else if (choice == 5) {
                System.out.flush();
                System.out.println("-----------------------------------------");
                System.out.println("\nHave a nice day! :D");
                System.out.println("-----------------------------------------");
                System.exit(0);
            } else {
                System.out.flush();
                System.out.println("-----------------------------------------");
                System.out.println("\nThat was not a valid entry.  Try Again.");
                executeChoice(getChoice());
            }
        }
    }

    public static void addContact() {
        String name = addName();
        String phoneNumber = addPhoneNumber();
        printHeader();
        previewContact(name, phoneNumber);
        String confirmContact = Input.getString("\nAre you sure the above information is correct, and you would like to add this contact?  (Y/N):");
        Boolean confirm1 = Input.yesNo(confirmContact);

        if (confirm1) {
            Contact newContact = new Contact(name, phoneNumber);
            contacts.put(nextId(contacts), newContact);
            FileHelper.tryWriteFile(FileHelper.tryMakeFileDirectory(), contacts.values().stream().toList());
            System.out.println("\n-----------------------------------------");
            System.out.println("Contact has been added");
            System.out.println("-----------------------------------------");
            executeChoice(getChoice());
        }   else {
            System.out.println("\n-----------------------------------------");
            System.out.println("Contact was not added");
            System.out.println("-----------------------------------------");
            System.out.println("\nWould you like to re-enter the contact information?");
            String confirmTryAddAgain = Input.getString("\nEnter (Y) to continue or (N) to return to main menu:");
            Boolean confirm3 = Input.yesNo(confirmTryAddAgain);
            System.out.println("-----------------------------------------");
            if (confirm3) {
                addContact();
            } else {
                executeChoice(getChoice());
            }
        }
    }

    public static String addName() {
        System.out.println("\n-----------------------------------------");
        String name = Input.getString("\nPlease Enter the Contact's Name:");
        System.out.println("-----------------------------------------");
        return name;
    }

    public static String addPhoneNumber() {
        System.out.println("\n-----------------------------------------");
        String phoneNumber = Input.getString("\nPlease Enter the Contact's Phone Number:");
        System.out.println("-----------------------------------------");
        return phoneNumber;
    }

    public static void searchContact(HashMap<String, Contact> contacts) {
        System.out.println("-----------------------------------------");
        String searchInput = Input.getString("\nPlease Enter the Contact Name to Search:").toLowerCase();
        System.out.println("-----------------------------------------");

        for (String key : contacts.keySet()) {
            String name = ((Contact) contacts.get(key)).getName().toLowerCase();
            String phoneNumber = ((Contact) contacts.get(key)).getPhoneNumber().toLowerCase();
            if (name.contains(searchInput)) {
                printHeader();
                previewContact(name, phoneNumber);
            }
        }

        System.out.println("\n-----------------------------------------");
        System.out.println("\nWould you like to search again?");
        String confirmSearchAgain = Input.getString("\nEnter (Y) to search again or (N) to return to main menu:");
        Boolean confirm = Input.yesNo(confirmSearchAgain);
        System.out.println("-----------------------------------------");

        if (confirm) {
            searchContact(contacts);
        } else {
            executeChoice(getChoice());
        }
    }

    public static void deleteContact (HashMap<String, Contact> contacts) {
        System.out.println("-----------------------------------------");
        String searchInput = Input.getString("\nPlease search for the contact you would like to delete:");
        System.out.println("-----------------------------------------");
        Boolean foundContact = false;

        for (String key : contacts.keySet()) {
            String name = ((Contact) contacts.get(key)).getName().toLowerCase();
            String phoneNumber = ((Contact) contacts.get(key)).getPhoneNumber().toLowerCase();
            if (searchInput.equalsIgnoreCase(name)) {
                foundContact = true;
                printHeader();
                previewContact(name, phoneNumber);
                String confirmDelete = Input.getString("\nDelete the contact(s)?  Type (Y) to delete or (N) to continue").toLowerCase();
                Boolean confirm1 = Input.yesNo(confirmDelete);
                System.out.println("-----------------------------------------");
                if (confirm1) {
                    contacts.remove(searchInput);
                    System.out.println("-----------------------------------------");
                    System.out.println("Contact was deleted.");
                    System.out.println("-----------------------------------------");
                }
            }
        }
        if (!foundContact) {
            System.out.println("\n-----------------------------------------");
            System.out.println("No contacts were found.  Please try your search again.");
            System.out.println("-----------------------------------------");
            deleteContact(contacts);
        }

        System.out.println("\n-----------------------------------------");
        System.out.println("\nWould you like to delete another contact?");
        String confirmDeleteAgain = Input.getString("\nEnter (Y) to delete another contact or (N) to return to main menu:");
        Boolean confirm2 = Input.yesNo(confirmDeleteAgain);
        System.out.println("-----------------------------------------");

        if (confirm2) {
            deleteContact(contacts);
        } else {
            executeChoice(getChoice());
        }
    }

    public static void writeContacts (List<Contact> contactsToWrite) {

    }

}




