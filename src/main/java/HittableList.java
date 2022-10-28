public class HittableList extends Hittable {

    int size;
    Hittable[] objects;

    public HittableList() {}
    public HittableList(Hittable[] objects) {
        this.objects = objects;
        size = objects.length;
    }

    public boolean hit (Ray r, double tMin, double tMax, HitRecord rec) {
        HitRecord tempRec = new HitRecord();
        boolean hitAnything = false;
        double closetSoFar = tMax;

        for (int i = 0; i < size; i++) {
            if(objects[i].hit(r, tMin, closetSoFar, tempRec)) {
                hitAnything = true;
                closetSoFar = tempRec.t;
                rec.set(tempRec);
            }
        }

        return hitAnything;
    }
}
