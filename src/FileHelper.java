import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

// The idea here is to encapsulate the code we can use for doing File I/O (input/output)
// This way, it's easy to maintain and we know exactly where to go when needing little helpers to do file work!
public class FileHelper {


    // using 'try' in front of a method name indicates there is a chance the method could fail
    // it tacitly informs the developers that they need to account for that possibility
    public static Path tryMakeFileDirectory(){
        String directory = "data";
        String filename = "info.json";

        // make the Path objects for the director and file
        Path dataDirectory = Paths.get(directory);
        Path dataFile = Paths.get(directory, filename);

        // because Files.createDirectories and Files.createFile can throw IOException,
        // we need to toss them into a try/catch block so it doesn't crash the program
        try{
            if (Files.notExists(dataDirectory)) {
                Files.createDirectories(dataDirectory);
            }
            if (! Files.exists(dataFile)) {
                Files.createFile(dataFile);
            }
            return dataFile;
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }
        // oof - null check needed!
        return null;
    }


    // like tryMakeFileDirectory, we isolated the File writing operation to its own method
    // this ensures any failure to be successful is handling at this small scope
    // also, if there is an error, it's much easier to identify and fix!
    public static void tryWriteFile(Path filePath, List<Contact> contactsToWrite){
        // do while loop while the retries are less than 5
        try{
            String contactStr = new Gson().toJson(contactsToWrite);
            Files.write(filePath, Stream.of(contactStr).toList());
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    // same as above for tryReadFile
    // note, Files.readAllLines RETURNS a List<String> -> meaning you need to use it somehow!
    public static List<Contact> tryReadFile(Path filePath){
        try {
            return Stream
                    .of(new Gson().fromJson(Files.readAllLines(filePath).get(0), Contact[].class))
                    .toList();
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }
}
