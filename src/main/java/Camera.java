public class Camera {

    private Vec3 origin;
    private Vec3 lowerLeftCorner;
    Vec3 horizontal;
    Vec3 vertical;

    public Camera() {
        final double aspectRatio = 16.0 / 9.0;
        double viewportHeight = 2.0;
        double viewportWidth = aspectRatio * viewportHeight;
        double focalLength = 1.0;

        origin = new Vec3(0, 0, 0);
        horizontal = new Vec3(viewportWidth, 0, 0);
        vertical = new Vec3(0, viewportHeight, 0);
        lowerLeftCorner = origin.sub(horizontal.div(2.0))
                .sub(vertical.div(2.0))
                .sub(new Vec3(0, 0, focalLength));
    }

    public Ray getRay(double u, double v) {
        Vec3 direction = lowerLeftCorner.add(horizontal.mul(u))
                .add(vertical.mul(v)).sub(origin);
       return new Ray(origin, direction);
    }
}
