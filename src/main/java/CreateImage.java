import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

public class CreateImage {

    public static void main(String[] args) throws IOException {

        final int imageWidth = 256;
        final int imageHeight = 256;

        String create = ("P3\n" + imageWidth + " " + imageHeight + "\n255\n");
        List<String> rows = Arrays.asList(create);
        Files.write(Paths.get("/home/roman/Documents/Projects/Image.ppm"),
                rows,
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND);

        for (int k = imageHeight - 1; k >= 0; --k) {
            for (int i = 0; i < imageWidth; ++i) {
                Color pixelColor = new Color((double)i / (imageWidth - 1), (double)k / (imageHeight - 1), 0.25);
                pixelColor.writeColor(pixelColor);
            }
        }
    }
}