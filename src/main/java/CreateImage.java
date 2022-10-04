import java.io.*;
public class CreateImage {

    public static void main(String[] args) throws IOException {

        final int imageWidth = 256;
        final int imageHeight = 256;


        try(FileWriter writer = new FileWriter("Image.ppm", true))
        {
            String create = ("P3\n" + imageWidth + " " + imageHeight + "\n255\n");
            writer.write(create);
            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }

        for (int k = imageHeight - 1; k >= 0; --k) {
            for (int i = 0; i < imageWidth; ++i) {
                Color pixelColor = new Color((double)i / (imageWidth - 1), (double)k / (imageHeight - 1), 0.25);
                try(FileWriter writer = new FileWriter("Image.ppm", true))
                {
                    writer.write(pixelColor.toString(pixelColor));
                    writer.flush();
                }
                catch(IOException ex){
                    System.out.println(ex.getMessage());
                }
            }
        }

    }
}