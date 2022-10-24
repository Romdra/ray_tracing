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

    public String toString(int samplesPerPixel) {
        double scale = 1.0 / samplesPerPixel;

        int r = (int)Math.floor(256 * clamp(r() * scale, 0.0, 0.999));
        int g = (int)Math.floor(256 * clamp(g() * scale, 0.0, 0.999));
        int b = (int)Math.floor(256 * clamp(b() * scale, 0.0, 0.999));
        return (r + " " + g + " " + b + "\n");
    }

    public static double clamp(double x, double min, double max) {
        if (x < min) return min;
        if (x > max) return max;
        return x;
    }
}

