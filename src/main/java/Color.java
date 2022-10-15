public class Color {
    double[] e = new double[3];
    public Color (double r, double g, double b) {
        e[0] = r;
        e[1] = g;
        e[2] = b;
    }

    public double r() {return e[0];}
    public double g() {return e[1];}
    public double b() {return e[2];}

    public static Color fromVec3(Vec3 v) {

        return new Color(v.x(), v.y(), v.z());
    }

    public String toString(Color pixelColor) {
        return ((int)Math.floor(255.999 * pixelColor.r()) + " "
                + (int)Math.floor(255.999 * pixelColor.g()) + " "
                + (int)Math.floor(255.999 * pixelColor.b()) + "\n");
    }
}

