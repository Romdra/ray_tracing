public class HittableList extends Hittable {

    int size;
    Hittable[] objects;

    public HittableList() {}
    public HittableList(Hittable[] objects, int size) {
        this.objects = objects;
        this.size = size;
    }

    public boolean hit (Ray r, double t_min, double t_max, HitRecord rec) {
        HitRecord tempRec = new HitRecord();
        boolean hitAnything = false;
        double closetSoFar = t_max;

        for (int i = 0; i < size; i++) {
            if(objects[i].hit(r, t_min, closetSoFar, tempRec)) {
                hitAnything = true;
                closetSoFar = tempRec.t;
                rec.set(tempRec);
            }
        }

        return hitAnything;
    }
}
