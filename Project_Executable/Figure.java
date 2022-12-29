package Project;

/**
 * Figure Class
 *
 * @author Ruben Castro Gonzalez and Alejandro Carrazoni Entenza
 */

public abstract class Figure {

    private int status;
    private Color color;
    private Color BaseColor;

    /**
     * The constructor is in charge of initializating the values of color and
     * status.
     *
     * @param color This parameter is referred to the color of the figure.
     */
    public Figure(Color color) {
        this.color = color;
        this.status = 1;
    }

    public Color getColor() {
        return color;
    }

    public Color getBaseColor() {
        return BaseColor;
    }

    public int getStatus() {
        return status;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setBaseColor(Color BaseColor) {
        this.BaseColor = BaseColor;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public abstract float area();

    public abstract boolean isEqualTo(Figure figure);

    /**
     * This method is in charge of printing out the color of the figure in the
     * RGB format.
     *
     * @return It returns the information about the color of the figure.
     */
    public String toString() {
        return "Color: " + color;
    }
}
