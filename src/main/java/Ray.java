public class Ray {

    Vec3 dir;
    Vec3 orig;

    public Ray() {}
    public Ray(Vec3 origin, Vec3 direction) {
        this.orig = origin;
        this.dir = direction;
    }
    public Vec3 origin() {return orig;}
    public Vec3 direction() {return dir;}

    public Vec3 linePosition(double t) {

        return dir.add(orig.mul(t));
    }
}
