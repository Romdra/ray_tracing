public class Color {
    int[] e = new int[3];
    public Color (int r, int g, int b) {
        e[0] = r;
        e[1] = g;
        e[2] = b;
    }

    public int r() {return e[0];}
    public int g() {return e[1];}
    public int b() {return e[2];}

    public static Color fromVec3(Vec3 v) {

        return new Color((int) v.x(), (int) v.y(), (int) v.z());
    }

    public String toString(Color pixelColor) {
        return ((int)Math.floor(255.999 * pixelColor.r()) + " "
                + (int)Math.floor(255.999 * pixelColor.g()) + " "
                + (int)Math.floor(255.999 * pixelColor.b()) + "\n");
    }
}

