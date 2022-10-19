import java.awt.*;

public class HittableList extends Hittable {

    public HittableList() {

    }
    
    public boolean hit (Ray r, double t_min, double t_max, HitRecord rec) {
        HitRecord tempRec;
        boolean hitAnything = false;
        double closetSoFar = t_max;

        for () {
            if(hit(r, t_min, closetSoFar, tempRec)) {
                hitAnything = true;
                closetSoFar = tempRec.t;
                rec = tempRec;
            }
        }

        return hitAnything;
    }
}
