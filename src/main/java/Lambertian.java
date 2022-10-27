public class Lambertian extends Material {

    private Vec3 albedo;

    public Lambertian(Vec3 a) {
        this.albedo = a;
    }

    public boolean scatter(Ray rIn, HitRecord rec, Vec3 attenuation, Ray scattered) {
        Vec3 scatterDirection = rec.normal.add(Vec3.randomUnitVector());

        if (scatterDirection.nearZero()) {
            scatterDirection = rec.normal;
        }

        scattered = new Ray(rec.p, scatterDirection);
        attenuation = albedo;
        return true;
    }
}
