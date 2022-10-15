public class CreateImage {
    public static boolean hitSphere(Vec3 center, double radius, Ray r) {
        Vec3 oc = r.origin().sub(center);
        double a = r.direction().dot(r.direction());
        double b = 2.0 * oc.dot(r.direction());
        double c = oc.dot(oc) - radius * radius;
        double discriminant = b * b - 4 * a * c;
        return (discriminant > 0);
    }
    public static Color rayColor(Ray r) {
        if (hitSphere(new Vec3(0,0,-1), 0.5, r))
            return new Color(1, 0, 0);
        Vec3 unitDirection = Vec3.unitVector(r.direction());
        double t = 0.5 * (unitDirection.y() + 1.0);
        Vec3 rc = new Vec3(1.0, 1.0, 1.0).mul(1.0 - t)
                .add(new Vec3(0.5, 0.7, 1.0).mul(t));
        return Color.fromVec3(rc);
    }

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

                Vec3 direction = lowerLeftCorner.add(horizontal.mul(u))
                        .add(vertical.mul(v)).sub(origin);
                Ray r = new Ray(origin, direction);
                Color pixelColor = rayColor(r);
                ImageRecording.myRecord(pixelColor.toString(pixelColor));
            }
        }
    }
}