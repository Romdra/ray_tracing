public class Sphere extends Hittable {

    Vec3 center;
    Material mat;
    double radius;

    public Sphere(Vec3 cen, double r, Material m) {
        this.center = cen;
        this.radius = r;
        this.mat = m;
    }

    public boolean hit(Ray r, double tMin, double tMax, HitRecord rec) {
        Vec3 oc = r.origin().sub(center);

        double a = r.direction().lengthSquared();
        double half_b = oc.dot(r.direction());
        double c = oc.lengthSquared() - Math.pow(radius, 2);

        double discriminant = Math.pow(half_b, 2) - a * c;
        if (discriminant < 0) return false;
        double sqrtD = Math.sqrt(discriminant);

        double root = (-half_b - sqrtD) / a;
        if (root < tMin || tMax < root) {
            root = (-half_b + sqrtD) / a;
            if (root < tMin || tMax < root) return false;
        }

        rec.t = root;
        rec.p = r.linePosition(rec.t);
        Vec3 outwardNormal = (rec.p.sub(center)).div(radius);
        rec.setFaceNormal(r, outwardNormal);
        rec.mat = mat;

        return true;
    }
}
