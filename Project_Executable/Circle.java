package Project;

/**
 * Circle Class
 *
 * @author Ruben Castro Gonzalez and Alejandro Carrazoni Entenza.
 */
public class Circle extends Figure {

    // Attributes 
    public int radius;   // percentage value of the radius

    // Static Attributes
    private static final int rmax = 50; //Constant
    private static final int rmin = 0; //Constant

    private static final float PI = 3.141592f; //In static methods we use static variables

    // Constructor, initializes the variables
    /**
     * This is the constructor of the circle, it initializes a circle with color
     * and radius.
     *
     * @param radius This is the radius that the circle will have.
     * @param color This is the color that the circle will have.
     * @see Circle inherates of figure.
     * @see Circle uses color class.
     */
    Circle(int radius, Color color) {
        super(color);
        if (radius > rmax) {
            this.radius = rmax;
        } else if (radius < rmin) {
            this.radius = rmin;
        } else {
            this.radius = radius;
        }

    }

    public float area_circle; //Here we save the area of the circle

    public float area_tile; //Here we save the area of the tile

    public float area_percentage; //Percentage of the circle inside the tile

    /**
     * This method returns the percentage of the area that the circle occupies
     * inside the tile.
     *
     * @return It will return the circle´s area.
     */
    public float area() {
        area_tile = Tile.wTile * Tile.hTile; //To use variables from other clasess we put class.variable
        area_circle = PI * (radius * radius) / 100;
        area_percentage = (area_circle / area_tile);
        return area_percentage;
    }

    /**
     * This method is used to compare circles and to check whether if they are
     * equal.
     *
     * @param circle The parameter given is the circle given to be compared.
     * @return It returns true if the circles are equal, this means the same
     * radius and color, else it returns false.
     */
    public boolean isEqualTo(Figure circle) {
        if ((getColor() == circle.getColor()) && (getRadius() == ((Circle) circle).getRadius())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method is used to print out the information about the circle, this
     * is, the radius and the color of this one.
     *
     * @return It returns the color of the circle and the radius of this one by
     * use of the getters.
     */
    public String toString() {

        return (getColor() + "," + getRadius());
    }

    // Getters And Setters
    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
