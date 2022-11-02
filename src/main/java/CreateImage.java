public class CreateImage {
    static final double INFINITY = Double.POSITIVE_INFINITY;
    public static void main(String[] args) {
        // Image
        final double aspectRatio = 16.0 / 9.0;
        final int imageWidth = 400;
        final int imageHeight = (int)(imageWidth / aspectRatio);
        final int samplesPerPixel = 100;
        final int max_depth = 50;

        // World
        HittableList world = new HittableList(new Hittable[5]);

        Material ground = new Lambertian(new Vec3(0.8, 0.8, 0.0));
        Material center = new Lambertian(new Vec3(0.1, 0.2, 0.5));
        Material left = new Dielectric(1.5);
        Material right = new Metal(new Vec3(0.8, 0.6, 0.2), 0.0);

        world.objects[0] = new Sphere(new Vec3(0.0, -100.5, -1.0), 100.0, ground);
        world.objects[1] = new Sphere(new Vec3(0.0, 0.0, -1.0), 0.5, center);
        world.objects[2] = new Sphere(new Vec3(-1.0, 0.0, -1.0), 0.5, left);
        world.objects[3] = new Sphere(new Vec3(-1.0, 0.0, -1.0), -0.45, left);
        world.objects[4] = new Sphere(new Vec3(1.0, 0.0, -1.0), 0.5, right);

        // Camera
        Camera cam = new Camera(new Vec3(-2, 2, 1), new Vec3(0,0,-1), new Vec3(0,1,0), 20.0, aspectRatio);

        // Render
        ImageRecording.myRecord("P3\n" + imageWidth + " " + imageHeight + "\n255\n");

        for (int j = imageHeight - 1; j >= 0; --j) {
            for (int i = 0; i < imageWidth; ++i) {
                Vec3 pixelColor = new Vec3(0,0,0);
                for (int k = 0; k < samplesPerPixel; ++k) {
                    double u = (i + Math.random()) / (imageWidth - 1);
                    double v = (j + Math.random()) / (imageHeight - 1);
                    Ray r = cam.getRay(u, v);
                    pixelColor = pixelColor.plus(rayColor(r, world, max_depth));
                }
                ImageRecording.myRecord(Color.fromVec3(pixelColor).toString(samplesPerPixel));
            }
        }
    }

    public static Vec3 rayColor(Ray r, Hittable world, int depth) {
        HitRecord rec = new HitRecord();

        if(depth <= 0) {
            return new Vec3(0,0,0);
        }

        if(world.hit(r, 0.001, INFINITY, rec)) {
            Ray scattered = new Ray();
            Vec3 attenuation = new Vec3();

            if (rec.mat.scatter(r, rec, attenuation, scattered)) {
                return attenuation.mul(rayColor(scattered, world, depth-1));
            }
            return new Vec3(0, 0, 0);
        }

        Vec3 unitDirection = Vec3.unitVector(r.direction());
        double t = 0.5 * (unitDirection.y() + 1.0);
        return new Vec3(1.0, 1.0, 1.0).mul(1.0 - t)
                .plus(new Vec3(0.5, 0.7, 1.0).mul(t));
    }

    public static double degreesToRadians(double degrees) {
        return degrees * Math.PI / 180.0;
    }
}