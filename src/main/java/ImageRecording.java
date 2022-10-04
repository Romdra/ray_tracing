import java.io.FileWriter;
import java.io.IOException;

public class ImageRecording {

    public static void myRecord(String string) {
        try(FileWriter writer = new FileWriter("Image.ppm", true)) {
            writer.write(string);
            writer.flush();
        } catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
