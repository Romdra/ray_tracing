import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

public class Color extends Vec3{

    public Color (double r, double g, double b) {
        super(r, g, b);
    }
    public void writeColor(Color pixel_color) throws IOException {

        String data = ((int)Math.floor(255.999 * pixel_color.r()) + " "
                     + (int)Math.floor(255.999 * pixel_color.g()) + " "
                     + (int)Math.floor(255.999 * pixel_color.b()) + "\n");

        List<String> lines = Arrays.asList(data);
        Files.write(Paths.get("/home/roman/Documents/Projects/Image.ppm"),
                lines,
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND);
    }
}
