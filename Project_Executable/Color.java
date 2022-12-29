package Project;

/**
 * Color Class
 *
 * @author Ruben Castro Gonzalez and Alejandro Carrazoni Entenza.
 */
public class Color {

    public int r;

    public int g;

    public int b;

    private static final int MAX = 255;

    /**
     * Initializes the three possible colors.
     *
     * @param r Initializes the red color.
     * @param g Initializes the green color.
     * @param b Initializes the black color.
     */
    Color(int r, int g, int b) {
        if ((r > MAX) || (g > MAX) || (b > MAX)) {
            this.r = MAX;
            this.g = MAX;
            this.b = MAX;

        } else {
            this.r = r;
            this.g = g;
            this.b = b;
        }
    }

    public static final Color WHITE() {
        return new Color(255, 255, 255);
    }

    public static final Color BLACK() {
        return new Color(0, 0, 0);
    }

    public static final Color RED() {
        return new Color(255, 0, 0);
    }

    public static final Color GREEN() {
        return new Color(0, 255, 0);
    }

    public static final Color BLUE() {
        return new Color(0, 0, 255);
    }

    public static final Color YELLOW() {
        return new Color(255, 255, 0);
    }

    public static final Color MAGENTA() {
        return new Color(255, 0, 255);
    }

    public static final Color CYAN() {
        return new Color(0, 255, 255);
    }

    /**
     * It is in charge of comparing two given colors to check if their value for
     * green red and blue is exactly the same.
     *
     * @param color This is the color recieved that is going to be compared.
     * @return It returns true if the colors are the same and false otherwise.
     */
    public boolean isEqualTo(Color color) {
        if ((color.r == this.r) && (color.g == this.g) && (color.b == this.b)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * It is in charge of printing the values of colors red, green and blue.
     *
     * @return It returns the color in the format specified.
     */
    public String toString() {
        return "R" + r + "G" + g + "B" + b;
    }

    public int getR() {
        return this.r;
    }

    public int getG() {
        return this.g;
    }

    public int getB() {
        return this.b;
    }

    public void setR(int r) {
        int difference = 0;

        if (r + getR() > 255) {
            difference = (r + getR()) - 255;
            this.r = difference - 1;
        } else if (r + getR() < 0) {
            difference = getR() + r;
            this.r = (difference + 255) + 1;
        } else {
            this.r = r + getR();
        }
    }

    public void setG(int g) {
        int difference = 0;

        if (g + getG() > 255) {
            difference = (g + getG()) - 255;
            this.g = difference - 1;
        } else if (g + getG() < 0) {
            difference = getG() + g;
            this.g = (difference + 255) + 1;
        } else {
            this.g = g + getG();
        }
    }

    public void setB(int b) {
        int difference = 0;

        if (b + getB() > 255) {
            difference = (b + getB()) - 255;
            this.b = difference - 1;
        } else if (b + getB() < 0) {
            difference = getB() + b;
            this.b = (difference + 255) + 1;
        } else {
            this.b = b + getB();
        }
    }
}
