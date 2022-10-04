import java.io.*;

public class Color extends Vec3{

    public Color (double r, double g, double b) {

        super(r, g, b);
    }

    public String toString(Color pixel_color) {
        return ((int)Math.floor(255.999 * pixel_color.r()) + " "
                + (int)Math.floor(255.999 * pixel_color.g()) + " "
                + (int)Math.floor(255.999 * pixel_color.b()) + "\n");
    }

}

