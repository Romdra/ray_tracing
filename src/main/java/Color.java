import java.io.*;

public class Color extends Vec3{

    public Color (double r, double g, double b) {

        super(r, g, b);
    }
    public String toString(Color pixelColor) {
        return ((int)Math.floor(255.999 * pixelColor.r()) + " "
                + (int)Math.floor(255.999 * pixelColor.g()) + " "
                + (int)Math.floor(255.999 * pixelColor.b()) + "\n");
    }

    public Color rayColor(Ray r) {
        Vec3 unitDirection = new Vec3();
        unitDirection = unitDirection.unitVector(r.direction());
        double t = 0.5 * (unitDirection.y() + 1.0);

        return (Color) new Color(1.0, 1.0, 1.0).mul(1.0 - t)
                .add(new Color(0.5, 0.7, 1.0).mul(t));
    }
}

