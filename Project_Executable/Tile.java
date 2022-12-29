package Project;

import java.util.HashSet;

/**
 * Tile Class
 * @author Ruben Castro Gonzalez and Alejandro Carrazoni Entenza.
 */
public class Tile implements Luminosity, Status {

    //Attributes
    private Color color;
    private Color BaseColor;
    private Figure figure;
    private int size;

    public String position;

    public int status;
    int luminosityChange;

    //Static attributes
    public static String center = "C";
    public static String up = "U";
    public static String down = "D";
    public static String left = "L";
    public static String right = "R";
    public static int wTile;
    public static int hTile;

    //Constructors
    /**
     * This is the constructor if the tile it initilizes a tile with the
     * following parameters.
     *
     * @param color This is the original color of the tile.
     * @param figure This is the figure of the tile.
     * @param position This is the position where the figure will be at the
     * tile.
     * @param status This is the original status of the tile.
     */
    Tile(Color color, Figure figure, String position, int status) {
        this.color = color;
        this.figure = figure;
        this.position = position;
        //this.size=size;
        this.status = status;
        this.luminosityChange = 0;
    }

    /*
    This constructor is for initialize a tile without figure
     */
    Tile(Color color, int status) {
        this.color = color;
        //this.size=size;
        this.status = status;
        this.luminosityChange = 0;
    }

    /**
     * This method is for check if the tile has figure or not.
     *
     * @return Returns true if the tile has figure and false if it is not.
     */
    public boolean hasFigure() {
        if (getFigure() != null) {
            return true;
        }
        return false;
    }

    /**
     * This method compares if the position of two tiles is the same.
     *
     * @param position Is the postion of the tile.
     * @return True if they are the same position and false if it is not.
     */
    public boolean equals(String position) {
        if (position == this.position) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method check if two tiles are equal or not in color and figure.
     *
     * @param tile This is the tile we are going to compare.
     * @return True if they have all of their atributtes equal and false if they
     * are one or more different.
     */
    public boolean isEqualTo(Tile tile) {  //
        if (hasFigure() == false && tile.hasFigure() == false) {
            if (this.color.isEqualTo(tile.getColor()) == true) {
                return true;
            } else {
                return false;
            }
        }

        if (hasFigure() == true && tile.hasFigure() == false) {
            return false;
        }

        if (hasFigure() == false && tile.hasFigure() == true) {
            return false;
        }

        if (tile.hasFigure() == true && hasFigure() == true) {

            if (getFigure().isEqualTo(tile.getFigure()) == true) {
                if (getColor().isEqualTo(tile.getColor()) == true) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This method returns all the information of a tile.
     *
     * @return This returns a string with all the information of the tile.
     */
    public String toString() {
        if (figure instanceof Circle) {
            return (status + "{" + this.color + ",CIR:{" + figure.getColor() + "," + this.position + "," + ((Circle) figure).getRadius() + "}");
            //return ("Color: " + this.color + ", "+this.circle+ ", Position: "+this.position);//+", Size: "+this.size);   

        } else if (figure instanceof Rectangle) {
            return (status + "{" + this.color + ",REC:{" + figure.getColor() + "," + this.position + "," + ((Rectangle) figure).getHeight() + "," + ((Rectangle) figure).getWide() + "}");
        } else {
            return (status + "{" + this.color);
        }
    }

    /**
     * This method changes the luminosity of the whole tile, that means
     * increment or decrement all the colors of the tile.
     *
     * @param value It is an integer that is how much we are going to change the
     * ilumination of the tile.
     */
    public void changeLuminosity(int value) {
        //We are checking if the tile is on, to change the luminosity
        setLuminosityChange(getLuminosityChange() + value);
        if (getStatus() != 0) {
            Color current = getColor();
            current.setR(value);
            current.setG(value);
            current.setB(value);
            setColor(current);
            setBaseColor(current);
            if ((getStatus() == 1) && (hasFigure())) {
                current = getFigure().getColor();
                current.setR(value);
                current.setG(value);
                current.setB(value);
                getFigure().setColor(current);
                getFigure().setBaseColor(current);
            }
        }
    }

    /**
     * This method change the status of the tile between 0,1,2 (0=turn off all
     * the tile,1=turn on the tile,2=turn on the tile and off the figure).
     *
     * @param value It is the value of the status we recieve(0,1,2).
     */
    public void changeStatus(int value) {
        Figure figure;
        if (getStatus() != 0 && value == 0) {
            setBaseColor(getColor());
            setColor(Color.BLACK());
            figure = getFigure();
            if (figure != null && figure.getStatus() != 0) { //Este es caso de que tenga figura
                figure.setBaseColor(figure.getColor());
                figure.setColor(Color.BLACK());
                figure.setStatus(value);
                setFigure(figure);
            }
            this.status = value;

        } else if (value == 1) {
            setColor(getBaseColor());
            figure = getFigure();
            if (figure != null) {
                figure.setColor(getFigure().getBaseColor()); //Color base de la figura
                figure.setStatus(value);

                setFigure(figure);
            }
            this.status = value;

        } else if (value == 2) {
            setColor(getBaseColor());
            figure = getFigure();
            if (figure != null && figure.getStatus() != 0) {
                figure.setBaseColor(figure.getColor());
                figure.setColor(Color.BLACK());
                figure.setStatus(0);
                setFigure(figure);
            }
            this.status = value;

        }

    }
    //Getter and setters

    public Color getColor() {
        return color;
    }

    public Figure getFigure() {
        return figure;
    }

    public String getPosition() {
        return position;
    }

    public int getSize() {
        return size;
    }

    public int getStatus() {
        return status;
    }

    public int getLuminosityChange() {
        return luminosityChange;
    }

    public Color getBaseColor() {
        return BaseColor;
    }

    public void setLuminosityChange(int luminosityChange) {
        this.luminosityChange = luminosityChange;
    }

    public void setBaseColor(Color BaseColor) {
        this.BaseColor = BaseColor;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setFigure(Figure figure) {
        this.figure = figure;
    }

    public void setPosition(String center, String up, String down, String left, String right) {
        this.center = center;
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
    }

    public void setSize(int newWTile, int newHTile) { //Esto vimos que se hace así en stackover flow
        wTile = newWTile;
        hTile = newHTile;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
