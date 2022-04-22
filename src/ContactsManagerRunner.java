import java.util.HashMap;
import java.util.Locale;

public class ContactsManagerRunner {

    public static void main(String[] args) {

        HashMap<String, Contact> contacts = getContacts();

        do{
            initGradesApp(contacts);
        } while(Input.yesNo("Would you like to search for another student? (y/n)"));

    }

    private static void initGradesApp(HashMap<String, Contact> contacts){
        System.out.println(contacts.keySet().stream().reduce("", (allNames, currentName) -> {
            return allNames + "\n -> " + currentName;
        }));

        String username = Input.getString("\nPlease enter the GitHub username of a student if you want more info: \n");

        if (contacts.containsKey(username)){
            Contact chosenOne = contacts.get(username);
            System.out.println("Student Name: " + chosenOne.getName().toUpperCase(Locale.ROOT));
            System.out.println(chosenOne.getGradeAverage());

        }else{
            System.out.println("No username found matching: " + username);
        }
    }


    private static HashMap<String, Contact> getContacts() {
        Contact student1 = new Contact("billi");

        student1.addGrade(90);
        student1.addGrade(65);
        student1.addGrade(100);

        Contact student2 = new Contact("susie");

        student2.addGrade(100);
        student2.addGrade(100);
        student2.addGrade(100);

        Contact student3 = new Contact("ralph");

        student3.addGrade(0);
        student3.addGrade(0);
        student3.addGrade(25);

        HashMap<String, Contact> contacts = new HashMap<>();

        String student1gh = "billiversustheworld";

        contacts.put(student1gh, student1);
        contacts.put("ohsusanna", student2);
        contacts.put("rektralph", student3);

        return contacts;
    }
}

