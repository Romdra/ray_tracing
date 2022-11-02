public class Camera {

    private Vec3 origin;
    private Vec3 lowerLeftCorner;
    Vec3 horizontal;
    Vec3 vertical;

    public Camera(Vec3 lookFrom, Vec3 lookAt, Vec3 vup,
                  double vfov, double aspectRatio) {
        double theta = CreateImage.degreesToRadians(vfov);
        double h = Math.tan(theta/2);
        double viewportHeight = 2.0 * h;
        double viewportWidth = aspectRatio * viewportHeight;

        Vec3 w = Vec3.unitVector(lookFrom.minus(lookAt));
        Vec3 u = Vec3.unitVector(vup.cross(w));
        Vec3 v = w.cross(u);

        origin = lookFrom;
        horizontal = u.mul(viewportWidth);
        vertical = v.mul(viewportHeight);
        lowerLeftCorner = origin.minus(horizontal.div(2.0))
                .minus(vertical.div(2.0))
                .minus(w);
    }

    public Ray getRay(double s, double t) {
        Vec3 direction = lowerLeftCorner.plus(horizontal.mul(s))
                .plus(vertical.mul(t)).minus(origin);
       return new Ray(origin, direction);
    }
}
