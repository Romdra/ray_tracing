import java.io.*;
public class CreateImage {

    public static void main(String[] args) throws IOException {

        final int imageWidth = 256;
        final int imageHeight = 256;

        ImageRecording.myRecord("P3\n" + imageWidth + " " + imageHeight + "\n255\n");

        for (int k = imageHeight - 1; k >= 0; --k) {
            for (int i = 0; i < imageWidth; ++i) {
                Color pixelColor = new Color((double)i / (imageWidth - 1), (double)k / (imageHeight - 1), 0.25);
                ImageRecording.myRecord(pixelColor.toString(pixelColor));
            }
        }
    }
}