public class Ray {

    private Vec3 dir;
    private Vec3 orig;

    public Ray(Vec3 origin, Vec3 direction) {
        this.orig = origin;
        this.dir = direction;
    }

    public Vec3 origin() {return orig;}
    public Vec3 direction() {return dir;}

    public Vec3 linePosition(double t) {

        return orig.add(dir.mul(t));
    }
}
