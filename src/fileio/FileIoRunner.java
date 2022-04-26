//package fileio;
//
//import java.nio.file.Path;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//
//public class FileIoRunner {
//
//    // Here, we have our 'orchestration' method init() - the piece of code used to bring together our helpers and
//    // arranged in a way we see fit for our usecase.
//    // We could write another method somewhere which orchestrates the FileHelper methods completely differently!
//    public static void init(){
//        Path newPath = FileHelper.tryMakeFileDirectory();
//
//        List<Contact> contactsToWrite = new ArrayList<>();
//        contactsToWrite.add(new Contact("Casey", "1111111111", 1));
//        contactsToWrite.add(new Contact("Ry", "2222222222", 2));
//        FileHelper.tryWriteFile(newPath, contactsToWrite);
//
//        HashMap<Integer, Contact> contacts = new HashMap<>();
//        for (Contact contact : FileHelper.tryReadFile(newPath)){
//            contacts.put(contact.getId(), contact);
//        }
//
//        for (Map.Entry<Integer, Contact> contact : contacts.entrySet()){
//            System.out.println(contact.getKey());
//            System.out.println(contact.getValue().getName() + " | " + contact.getValue().getPhoneNo());
//        }
//    }
//}
