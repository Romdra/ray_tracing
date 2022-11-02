public class Metal extends Material {

    public Vec3 albedo;
    public double fuzz;

    public Metal (Vec3 a, double f) {
        this.albedo = a;
        this.fuzz = f < 1 ? f : 1;
    }

    public boolean scatter(Ray rIn, HitRecord rec, Vec3 attenuation, Ray scattered) {
        Vec3 reflected = Vec3.reflect(Vec3.unitVector(rIn.direction()), rec.normal);
        scattered.set(new Ray(rec.p, reflected.plus(Vec3.randomInUnitSphere().mul(fuzz))));
        attenuation.set(albedo);
        return (scattered.direction().dot(rec.normal) > 0);
    }
}
