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

    public Vec3 plus(Vec3 v) {
        return new Vec3(e[0] + v.e[0], e[1] + v.e[1], e[2] + v.e[2]);
    }

    public Vec3 mul(double t) {
        return new Vec3(e[0] * t, e[1] * t, e[2] * t);
    }
    public Vec3 div(double t) {
        return new Vec3(e[0] / t, e[1] / t, e[2] / t);
    }

    public double length() {
        return Math.sqrt(lengthSquared());
    }
    public  double lengthSquared() {
        return e[0]*e[0] + e[1]*e[1] + e[2]*e[2];
    }

    public void set(Vec3 v) {
        e[0] = v.e[0];
        e[1] = v.e[1];
        e[2] = v.e[2];
    }

    // Vec3 Utility methods

    public Vec3 minus(Vec3 v) {
        return new Vec3(e[0] - v.e[0], e[1] - v.e[1], e[2] - v.e[2]);
    }

    public Vec3 mul(Vec3 v) {
        return new Vec3(e[0] * v.e[0], e[1] * v.e[1], e[2] * v.e[2]);
    }

    public Vec3 div(Vec3 v) {
        return new Vec3(e[0] / v.e[0], e[1] / v.e[1], e[2] / v.e[2]);
    }

    public double dot(Vec3 v) {
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

    public static Vec3 random() {
        return new Vec3((Math.random()*2 - 1), (Math.random()*2 - 1),
                (Math.random()*2 - 1));
    }

    public static Vec3 randomInUnitSphere() {
        while (true) {
            Vec3 p = random();
            if (p.lengthSquared() >= 1) continue;
            return p;
        }
    }

    public static Vec3 randomUnitVector() {
        return unitVector(randomInUnitSphere());
    }
    public static Vec3 randomInHemisphere(Vec3 normal) {
        Vec3 inUnitSphere = randomInUnitSphere();
        if (inUnitSphere.dot(normal) > 0.0)
            return inUnitSphere;
        else
            return inUnitSphere.mul(-1);
    }

    public boolean nearZero() {
        double s = 1e-8;
        return (Math.abs(e[0]) < s) && (Math.abs(e[1]) < s) && (Math.abs(e[2]) < s);
    }

    public static Vec3 reflect(Vec3 v, Vec3 n) {
        return v.minus(n.mul(v.dot(n)).mul(2));
    }

    public static Vec3 refract(Vec3 uv, Vec3 n, double etaiOverEtat) {
        double cosTheta = Math.min(n.dot(uv.mul(-1.0)), 1.0);
        Vec3 rOutPerp = uv.plus(n.mul(cosTheta)).mul(etaiOverEtat);
        Vec3 rOutParallel = n.mul(-Math.sqrt(Math.abs(1.0 - rOutPerp.lengthSquared())));
        return rOutPerp.plus(rOutParallel);
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
