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

        for(int j = imageHeight-1; j >= 0; --j) {
            for (int i = 0; i < imageWidth; ++i) {
                var r = (double)i / (imageWidth - 1);
                var g = (double)j / (imageHeight - 1);
                var b = 0.25;

                int rImg = (int)Math.floor(255.999 * r);
                int gImg = (int)Math.floor(255.999 * g);
                int bImg = (int)Math.floor(255.999 * b);

                String data = (rImg + " " + gImg + " " + bImg + "\n");
                List<String> lines = Arrays.asList(data);
                Files.write(Paths.get("/home/roman/Documents/Projects/Image.ppm"),
                        lines,
                        StandardCharsets.UTF_8,
                        StandardOpenOption.CREATE,
                        StandardOpenOption.APPEND);
            }
        }
    }
}