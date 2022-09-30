public class Vec3 {

    double[] e = new double[3];
    public Vec3() {}
    public Vec3(double e0, double e1, double e2) {
        e[0] = e0;
        e[1] = e1;
        e[2] = e2;
    }

    public double x() {return e[0];}
    public double y() {return e[1];}
    public double z() {return e[2];}

    public Vec3 add(Vec3 v) {
        return new Vec3(e[0] += v.e[0], e[1] += v.e[1], e[2] += v.e[2]);
    }

    public Vec3 multiply(double t) {
        return new Vec3(e[0] *= t, e[1] *= t, e[2] *= t);
    }

    public Vec3 divide(double t) {
        return new Vec3(e[0] *= 1/t, e[1] *= 1/t, e[2] *= 1/t);
    }

    public double length() {
        return Math.sqrt(length_squared());
    }
    public  double length_squared() {
        return e[0]*e[0] + e[1]*e[1] + e[2]*e[2];
    }

    public double dot(Vec3 u, Vec3 v) {
        return u.e[0] * v.e[0]
             + u.e[1] * v.e[1]
             + u.e[2] * v.e[2];
    }

    public Vec3 cross(Vec3 u, Vec3 v) {
        return new Vec3(u.e[1] * v.e[2] - u.e[2] * v.e[1],
                        u.e[2] * v.e[0] - u.e[0] * v.e[2],
                        u.e[0] * v.e[1] - u.e[1] * v.e[0]);
    }

    public Vec3 unit_vector(Vec3 v) {
        return v.divide(v.length());
    }
}
