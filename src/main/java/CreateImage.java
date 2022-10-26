public class CreateImage {
    static final double INFINITY = Double.POSITIVE_INFINITY;
    static final double PI = 3.1415926535897932385;
    public static void main(String[] args) {
        // Image
        final double aspectRatio = 16.0 / 9.0;
        final int imageWidth = 400;
        final int imageHeight = (int)(imageWidth / aspectRatio);
        final int samplesPerPixel = 100;

        // World
        Hittable[] w = new Hittable[2];
        HittableList world = new HittableList(w,w.length);
        world.objects[0] = new Sphere(new Vec3(0,0,-1), 0.5);
        world.objects[1] = new Sphere(new Vec3(0,-100.5,-1), 100);

        // Camera
        Camera cam = new Camera();

        // Render
        ImageRecording.myRecord("P3\n" + imageWidth + " " + imageHeight + "\n255\n");

        for (int j = imageHeight - 1; j >= 0; --j) {
            for (int i = 0; i < imageWidth; ++i) {
                Vec3 pixelColor = new Vec3(0,0,0);
                for (int k = 0; k < samplesPerPixel; ++k) {
                    double u = (i + Math.random()) / (imageWidth - 1);
                    double v = (j + Math.random()) / (imageHeight - 1);
                    Ray r = cam.getRay(u, v);
                    pixelColor = pixelColor.add(rayColor(r, world));
                }
                ImageRecording.myRecord(Color.fromVec3(pixelColor).toString(samplesPerPixel));
            }
        }
    }

    public static Vec3 rayColor(Ray r, Hittable world) {
        HitRecord rec = new HitRecord();
        if(world.hit(r, 0, INFINITY, rec)) {
            return new Vec3(1,1,1).add(rec.normal).mul(0.5);
        }

        Vec3 unitDirection = Vec3.unitVector(r.direction());
        double t = 0.5 * (unitDirection.y() + 1.0);
        return new Vec3(1.0, 1.0, 1.0).mul(1.0 - t)
                .add(new Vec3(0.5, 0.7, 1.0).mul(t));
    }

    public static double degrees_to_radians(double degrees) {
        return degrees * PI / 180.0;
    }
}