public class Metal extends Material {

    public Vec3 albedo;

    public Metal (Vec3 a) {
        this.albedo = a;
    }

    public boolean scatter(Ray rIn, HitRecord rec, Vec3 attenuation, Ray scattered) {
        Vec3 reflected = Vec3.reflect(Vec3.unitVector(rIn.direction()), rec.normal);
        scattered = new Ray(rec.p, reflected);
        attenuation = albedo;
        return (scattered.direction().dot(rec.normal) > 0);
    }
}
