public class Dielectric extends Material {

    private double iR; //Index of Refraction

    public Dielectric(double indexOfRefraction) {
        this.iR = indexOfRefraction;
    }

    public boolean scatter(Ray rIn, HitRecord rec, Vec3 attenuation, Ray scattered) {
        attenuation.set(new Vec3(1.0, 1.0, 1.0));
        double refractionRatio = rec.frontFace ? (1.0/iR) : iR;

        Vec3 unitDirection = Vec3.unitVector(rIn.direction());
        double cosTheta = Math.min(unitDirection.mul(-1.0).dot(rec.normal), 1.0);
        double sinTheta = Math.sqrt(1.0 - Math.pow(cosTheta, 2));

        boolean cannotRefract = refractionRatio * sinTheta > 1.0;
        Vec3 direction = new Vec3();
        if (cannotRefract || reflectance(cosTheta, refractionRatio) > Math.random()) {
            direction.set(Vec3.reflect(unitDirection, rec.normal));
        } else {
            direction.set(Vec3.refract(unitDirection, rec.normal, refractionRatio));
        }

        scattered.set(new Ray(rec.p, direction));
        return true;
    }

    // Schlick's approximation
    private double reflectance(double cosine, double refIdx) {
        double r0 = (1 - refIdx) / (1 + refIdx);
        r0 = r0 * r0;
        return r0 + (1 - r0) * Math.pow((1 - cosine), 5);
    }
}
