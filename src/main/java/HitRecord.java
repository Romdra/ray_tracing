public class HitRecord {
    Vec3 p;
    Vec3 normal;
    Material mat;
    double t;
    boolean frontFace;

    public void setFaceNormal(Ray r, Vec3 outwardNormal) {
        this.frontFace = r.direction().dot(outwardNormal) < 0;
        this.normal = frontFace ? outwardNormal : outwardNormal.mul(-1);
    }

    public void set(HitRecord hr) {
        this.t = hr.t;
        this.p = hr.p;
        this.normal = hr.normal;
        this.mat = hr.mat;
        this.frontFace = hr.frontFace;
    }
}
