public class CreateImage {

    public static void main(String[] args) {

        // Image
        final double aspectRatio = 16.0 / 9.0;
        final int imageWidth = 400;
        final int imageHeight = (int)(imageWidth / aspectRatio);

        // Camera
        double viewportHeight = 2.0;
        double viewportWidth = aspectRatio * viewportHeight;
        double focalLength = 1.0;

        Vec3 origin = new Vec3(0, 0, 0);
        Vec3 horizontal = new Vec3(viewportWidth, 0, 0);
        Vec3 vertical = new Vec3(0, viewportHeight, 0);
        Vec3 lowerLeftCorner = origin.sub(horizontal.div(2.0))
                                .sub(vertical.div(2.0))
                                .sub(new Vec3(0, 0, focalLength));

        // Render
        ImageRecording.myRecord("P3\n" + imageWidth + " " + imageHeight + "\n255\n");

        for (int j = imageHeight - 1; j >= 0; --j) {
            for (int i = 0; i < imageWidth; ++i) {
                double u = (double) i / (imageWidth - 1);
                double v = (double) j / (imageHeight - 1);

                Vec3 direction = lowerLeftCorner.add(horizontal.mul(u)).add(vertical.mul(v)).div(origin);
                Ray r = new Ray(origin, direction);

                //TODO хз если честно.
                Color pixelColor = Color.rayColor(r);

                ImageRecording.myRecord(pixelColor.toString(pixelColor));
            }
        }
    }
}