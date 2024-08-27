import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Write_hex_code {
    private String name;
    private FileWriter myWriter;
    private File F;
    public Write_hex_code(String name) {
        try {
            String path = Paths.get("").toAbsolutePath().toString();
            this.myWriter = new FileWriter(path.substring(0, path.lastIndexOf("\\")+1) + name + ".txt");
        }
        catch (Exception e) {
            System.out.println("Error creating new file.");
            e.printStackTrace();
        }
    }
    public void write(String data) {
        try {
            myWriter.write(data);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
