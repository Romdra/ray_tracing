import java.util.Arrays;

public class Vec3 {

    double[] e = new double[3];
    public Vec3() {}
    public Vec3(double e0, double e1, double e2) {
        e[0] = e0;
        e[1] = e1;
        e[2] = e2;
    }

    //COORDINATE
    public double x() {return e[0];}
    public double y() {return e[1];}
    public double z() {return e[2];}

    public Vec3 add(Vec3 v) {
        return new Vec3(e[0] + v.e[0], e[1] + v.e[1], e[2] + v.e[2]);
    }

    public Vec3 mul(double t) {
        return new Vec3(e[0] * t, e[1] * t, e[2] * t);
    }
    public Vec3 div(double t) {
        return new Vec3(e[0] / t, e[1] / t, e[2] / t);
    }

    public double length() {
        return Math.sqrt(length_squared());
    }
    public  double length_squared() {
        return e[0]*e[0] + e[1]*e[1] + e[2]*e[2];
    }

    public void set(Vec3 v) {
        e[0] = v.e[0];
        e[1] = v.e[1];
        e[2] = v.e[2];
    }

    // Vec3 Utility methods

    public Vec3 sub(Vec3 v) {
        return new Vec3(e[0] - v.e[0], e[1] - v.e[1], e[2] - v.e[2]);
    }

    public Vec3 mul(Vec3 v) {
        return new Vec3(e[0] * v.e[0], e[1] * v.e[1], e[2] * v.e[2]);
    }

    public Vec3 div(Vec3 v) {
        return new Vec3(e[0] / v.e[0], e[1] / v.e[1], e[2] / v.e[2]);
    }

    public double dot( Vec3 v) {
        return e[0] * v.e[0]
             + e[1] * v.e[1]
             + e[2] * v.e[2];
    }

    public Vec3 cross(Vec3 v) {
        return new Vec3(e[1] * v.e[2] - e[2] * v.e[1],
                        e[2] * v.e[0] - e[0] * v.e[2],
                        e[0] * v.e[1] - e[1] * v.e[0]);
    }

    public static Vec3 unitVector(Vec3 v) {
        return v.div(v.length());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vec3 vec3 = (Vec3) o;
        return Arrays.equals(e, vec3.e);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(e);
    }
}
