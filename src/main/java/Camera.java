public class Camera {

    private Vec3 origin;
    private Vec3 lowerLeftCorner;
    private Vec3 horizontal;
    private Vec3 vertical;
    private Vec3 u = new Vec3();
    private Vec3 v = new Vec3();
    private Vec3 w = new Vec3();
    private double lensRadius;

    public Camera(Vec3 lookFrom, Vec3 lookAt, Vec3 vup, double vfov,
                  double aspectRatio, double aperture, double focusDist) {
        double theta = CreateImage.degreesToRadians(vfov);
        double h = Math.tan(theta/2);
        double viewportHeight = 2.0 * h;
        double viewportWidth = aspectRatio * viewportHeight;

        w.set(Vec3.unitVector(lookFrom.minus(lookAt)));
        u.set(Vec3.unitVector(vup.cross(w)));
        v.set(w.cross(u));

        origin = lookFrom;
        horizontal = u.mul(viewportWidth).mul(focusDist);
        vertical = v.mul(viewportHeight).mul(focusDist);
        lowerLeftCorner = origin.minus(horizontal.div(2.0))
                .minus(vertical.div(2.0))
                .minus(w.mul(focusDist));
        lensRadius = aperture / 2;
    }

    public Ray getRay(double s, double t) {
        Vec3 rd = Vec3.randomInUnitDisk().mul(lensRadius);
        Vec3 offset = u.mul(rd.x()).plus(v.mul(rd.y()));

        Vec3 direction = lowerLeftCorner.plus(horizontal.mul(s))
                .plus(vertical.mul(t)).minus(origin).minus(offset);
       return new Ray(origin.plus(offset), direction);
    }
}
