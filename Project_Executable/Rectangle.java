package Project;

/**
 * Rectangle Class
 *
 * @author Ruben Castro Gonzalez and Alejandro Carrazoni Entenza.
 */
public class Rectangle extends Figure {

    // Attributes
    public int height;
    public int wide;

    //Static Attributes
    private static int max_height = 100;
    private static int min_height = 0;
    private static int max_wide = 100;
    private static int min_wide = 0;

    // Constructor.
    /**
     * This is the constructor of the rectangle, it initializes a rectangle with
     * color, height and width.
     *
     * @param height This is the input height of the rectangle.
     * @param wide This is the input width of the rectangle.
     * @param color This is the input color of the rectangle.
     */
    Rectangle(int height, int wide, Color color) { //The constructor must take the values of the class and pass the attributes to initialize. The constructor will be called later passing the parameters of the object that we want to create

        super(color);
        if (height > max_height) {
            this.height = max_height;
        } else if (height < min_height) {
            this.height = min_height;
        } else {
            this.height = height;
        }

        if (wide > max_wide) {
            this.wide = max_wide;
        } else if (wide < min_wide) {
            this.wide = min_wide;
        } else {
            this.wide = wide;
        }

    }

    // Non-static or Instance Methods
    public float area_rectangle;
    public float area_tile;
    public float area_percentage;

    /**
     * This method returns the percentage of the area that the rectangle
     * occupies inside the tile.
     *
     * @return It will return the rectangle´s area.
     */
    public float area() {
        area_tile = Tile.wTile * Tile.hTile;
        area_rectangle = height * wide / 100; //se usa circle.radius en lugar de int en caso de recibir un entero porque en este caso recibes un objeto que contiene variables o atributos, no una variables, osea recibes un objeto de variables no una variable, así que haces objeto.atributo
        area_percentage = (area_rectangle / area_tile);
        return area_percentage;

    }

    /**
     * This method is used to compare rectangles and to check whether if they
     * are equal.
     *
     * @param rectangle The parameter given is the rectangle given to be
     * compared.
     * @return It returns true if the rectangles are equal, this means the same
     * width, height and color, else it returns false.
     */
    public boolean isEqualTo(Figure rectangle) {
        if ((getColor() == rectangle.getColor()) && (getHeight() == ((Rectangle) rectangle).getHeight()) && (getWide() == ((Rectangle) rectangle).getWide())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method is used to print out the information about the rectangle,
     * this is, the height, width and the color of this one.
     *
     * @return It returns the width, the height of the rectangle and the color
     * of this one by use of the getters.
     */
    public String toString() {

        return ("Height: " + getHeight() + ", Wide: " + getWide() + ", Color: " + getColor());

    }

    // Getters And Setters
    public int getHeight() {
        return height;
    }

    public int getWide() {
        return wide;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWide(int wide) {
        this.wide = wide;
    }

}
