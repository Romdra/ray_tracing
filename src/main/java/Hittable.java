public abstract class Hittable {
    public boolean hit(Ray r, double t_min, double t_max, HitRecord rec) {
        return false;
    }
}
