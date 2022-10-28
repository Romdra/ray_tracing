public abstract class Hittable {
    public boolean hit(Ray r, double tMin, double tMax, HitRecord rec) {
        return false;
    }
}
