import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ContactsManagerRunner {

    public static void main(String[] args) {
        HashMap<String, Contact> contacts = getContacts();
        executeChoice(contacts, getChoice());
    }

    public static String nextId (HashMap<String, Contact> contacts) {
        String nextId = String.valueOf(contacts.size() + 1);
        return nextId;
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

    public static void executeChoice(HashMap<String, Contact> contacts, int choice) {
        for (int i = 1; i <= 5; i++) {
            if (choice == 1) {
                viewContacts(contacts);
                System.out.println("\n");
                executeChoice(contacts, getChoice());
                break;
            } else if (choice == 2) {
                addContact(contacts);
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
                executeChoice(contacts, getChoice());
            }
        }
    }

    public static void addContact(HashMap<String, Contact> contacts) {
        String name = addName();
        String phoneNumber = addPhoneNumber();
        printHeader();
        previewContact(name, phoneNumber);
        String confirmContact = Input.getString("\nIs the above information correct?  (Y/N):");
        Boolean confirm1 = Input.yesNo(confirmContact);

        if (confirm1) {
            Contact newContact = new Contact(name, phoneNumber);
            contacts.put(nextId(contacts), newContact);
            System.out.println("\n-----------------------------------------");
            System.out.println("Contact has been added");
            String confirmReturnToMainMenu = Input.getString("\nWould you like to return to the main menu?  (Y/N):");
            Boolean confirm2 = Input.yesNo(confirmReturnToMainMenu);
            System.out.println("-----------------------------------------");

            if (confirm2) {
                executeChoice(contacts, getChoice());
            }
        }   else {
            System.out.println("\n-----------------------------------------");
            System.out.println("Contact was not added");
            System.out.println("\nWould you like to re-enter the contact information?");
            String confirmTryAddAgain = Input.getString("\nEnter (Y) to continue or (N) to return to main menu:");
            Boolean confirm3 = Input.yesNo(confirmTryAddAgain);
            System.out.println("-----------------------------------------");

            if (confirm3) {
                addContact(contacts);
            } else {
                executeChoice(contacts, getChoice());
            }
        }
    }

    public static String addName() {
        System.out.println("-----------------------------------------");
        String name = Input.getString("\nPlease Enter the Contact's Name:");
        System.out.println("-----------------------------------------");
        return name;
    }

    public static String addPhoneNumber() {
        System.out.println("-----------------------------------------");
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
            executeChoice(contacts, getChoice());
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
                String confirmDelete = Input.getString("\nAre you sure you would like to delete the contact(s)?" +
                        "\nType (Y) to delete or (N) to continue").toLowerCase();
                Boolean confirm1 = Input.yesNo(confirmDelete);
                System.out.println("-----------------------------------------");
                if (confirm1) {

                    // Why you no work?
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
            executeChoice(contacts, getChoice());
        }

    }
}




