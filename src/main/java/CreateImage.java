public class CreateImage {
    static final double INFINITY = Double.POSITIVE_INFINITY;
    public static void main(String[] args) {
        // Image
        final double aspectRatio = 3.0 / 2.0;
        final int imageWidth = 1200;
        final int imageHeight = (int)(imageWidth / aspectRatio);
        final int samplesPerPixel = 500;
        final int max_depth = 50;

        // World
        HittableList world = randomScene();

        // Camera
        Vec3 lookFrom = new Vec3(13, 2, 3);
        Vec3 lookAt = new Vec3(0, 0, 0);
        Vec3 vup = new Vec3(0, 1, 0);
        double distToFocus = 10.0;
        double aperture = 0.1;
        Camera cam = new Camera(lookFrom, lookAt, vup,
                20.0, aspectRatio, aperture, distToFocus);

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

    public static HittableList randomScene() {
        int n = 500;
        Hittable[] world = new Hittable[n+1];

        Material ground = new Lambertian(new Vec3(0.5, 0.5, 0.5));
        world[0] = new Sphere(new Vec3(0, -1000, 0), 1000, ground);
        int i = 1;
        for (int a = -11; a < 11; a++) {
            for (int b = -11; b < 11; b++) {
                double chooseMat = Math.random();
                Vec3 center = new Vec3(a+0.9*Math.random(), 0.2, b+0.9*Math.random());

                if (center.minus(new Vec3(4, 0.2, 0)).length() > 0.9) {
                    Material sphereMaterial;

                    if (chooseMat < 0.8) {
                        //diffuse
                        Vec3 albedo = Vec3.random().mul(Vec3.random());
                        sphereMaterial = new Lambertian(albedo);
                        world[i++] = new Sphere(center, 0.2, sphereMaterial);
                    } else if (chooseMat < 0.95) {
                        //metal
                        Vec3 albedo = Vec3.random(0.5, 1);
                        double fuzz = Math.random()*0.5;
                        sphereMaterial = new Metal(albedo, fuzz);
                        world[i++] = new Sphere(center, 0.2, sphereMaterial);
                    } else {
                        //glass
                        sphereMaterial = new Dielectric(1.5);
                        world[i++] = new Sphere(center, 0.2, sphereMaterial);
                    }
                }
            }
        }

        Material material1 = new Dielectric(1.5);
        world[i++] = new Sphere(new Vec3(0.0, 1.0, 0.0), 1.0, material1);

        Material material2 = new Lambertian(new Vec3(0.4, 0.2, 0.1));
        world[i++] = new Sphere(new Vec3(-4.0, 1.0, 0.0), 1.0, material2);

        Material material3 = new Metal(new Vec3(0.7, 0.6, 0.5), 0.0);
        world[i++] = new Sphere(new Vec3(4.0, 1.0, 0.0), 1.0, material3);

        return new HittableList(world, i);
    }
}