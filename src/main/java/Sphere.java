public class Sphere extends Hittable {

    private Vec3 center;
    private double radius;

    public Sphere(Vec3 cen, double r) {
        this.center = cen;
        this.radius = r;
    }

    public boolean hit(Ray r, double t_min, double t_max, HitRecord rec) {
        Vec3 oc = r.origin().sub(center);

        double a = r.direction().length_squared();
        double half_b = oc.dot(r.direction());
        double c = oc.length_squared() - Math.pow(radius, 2);

        double discriminant = Math.pow(half_b, 2) - a * c;
        if (discriminant < 0) return false;
        double sqrtD = Math.sqrt(discriminant);

        double root = (-half_b - sqrtD) / a;
        if (root < t_min || t_max < root) {
            root = (-half_b + sqrtD) / a;
            if (root < t_min || t_max < root) return false;
        }

        rec.t = root;
        rec.p = r.linePosition(rec.t);
        Vec3 outwardNormal = (rec.p.sub(center)).div(radius);
        rec.setFaceNormal(r, outwardNormal);

        return true;
    }
}
