public class Color {
    double[] e = new double[3];

    public Color() {}
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

    public String toString(int samplesPerPixel) {
        double scale = 1.0 / samplesPerPixel;

        double r = Math.sqrt(r() * scale);
        double g = Math.sqrt(g() * scale);
        double b = Math.sqrt(b() * scale);

        return ((int)Math.floor(256 * clamp(r, 0.0, 0.999)) + " "
                + (int)Math.floor(256 * clamp(g, 0.0, 0.999)) + " "
                + (int)Math.floor(256 * clamp(b, 0.0, 0.999))+ "\n");
    }

    public static double clamp(double x, double min, double max) {
        if (x < min) return min;
        if (x > max) return max;
        return x;
    }
}

