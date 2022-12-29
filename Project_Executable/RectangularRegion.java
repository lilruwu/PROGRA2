package Project;

import java.io.*;
import java.util.*;

/**
 * Region Class
 * @author Ruben Castro Gonzalez and Alejandro Carrazoni Entenza.
 */
public class RectangularRegion implements Luminosity, Status {

    //Attributes
    public Mosaic mosaic;
    public String name;
    public Coordinate origin;
    public int h;
    public int w;
    public ArrayList<Coordinate> coordinates = new ArrayList<>();//falta este por rellenar

    //Constructor
    /**
     * This is the constructor of the regions that create a region given the
     * following parameters.
     *
     * @param m The mosaic where the region belongs.
     * @param name The name of the region.
     * @param origin The origin coordinates (x,y)
     * @param h The height of tiles of the region.
     * @param w The width of tiles of the region.
     */
    RectangularRegion(Mosaic m, String name, Coordinate origin, int h, int w) {
        this.mosaic = m;
        this.name = name;
        this.origin = origin;
        //aqui falta asociar el alto y el ancho
        this.h = h;
        this.w = w;

        fitRegionToMosaic(m.rows, m.columns, origin.row, origin.column, h, w); //check that the region is inside the mosaic 
        setCoordinates(m.rows, m.columns, origin.row, origin.column);
        shuffle(coordinates);

    }

    //Methods
    /**
     * This method fits the region to the mosiac if the region is bigger that
     * the maximum size of the mosaic.
     *
     * @param mosaicRows The rows that the mosaic have.
     * @param mosaicColumns The columns that the mosaic have.
     * @param originRow The origin row of the region.
     * @param originColumn The origin column of the region.
     * @param height The height of the region.
     * @param width The width of the region.
     */
    public void fitRegionToMosaic(int mosaicRows, int mosaicColumns, int originRow, int originColumn, int height, int width) {
        if ((originRow + height - 1) > mosaicRows) {
            this.h = mosaicRows - originRow + 1;

        } else if ((originColumn + width - 1) > mosaicColumns) {
            this.w = mosaicColumns - originColumn + 1;
        }

    }

    /**
     * This method shuffles all the coordinates of a region.
     *
     * @param coordinates It recieves the coordinates of the region we want to
     * shuffle.
     */
    public void shuffle(ArrayList<Coordinate> coordinates) {
        Collections.shuffle(coordinates);
    }

    /**
     * This method sort all the coordinates of a region of the mosaic.
     */
    public void sortByCoordinateAsc() {
        Collections.sort(coordinates);
    }

    /**
     * This method gets the area of a region.
     *
     * @return Returns the coordinates that are the area of a region.
     */
    public int getArea() {
        return coordinates.size();
    }

    /**
     * This method strings all the information of a region.
     *
     * @return Returns an string with all the information of a region.
     */
    public String toString() {
        return (this.name + ":(" + this.origin.row + "," + this.origin.column + ")," + this.h + "-" + this.w + ":" + this.coordinates);
    }

    /**
     * This method creates the coordinates of the region.
     *
     * @param mosaicRows The rows of the mosaic.
     * @param mosaicColumns The columns of the mosaic.
     * @param originRow The origin row of the region.
     * @param originColumn The origin column of the region.
     */
    public void setCoordinates(int mosaicRows, int mosaicColumns, int originRow, int originColumn) {
        //this.mosaicRows= new Mosaic.rows;
        for (int row = originRow; row < originRow + h; row++) {

            for (int column = originColumn; column < originColumn + w; column++) {

                Coordinate coord = new Coordinate(row, column);
                coordinates.add(coord);

            }
        }
    }

    /**
     * This method changes the luminosity of the whole region, that means
     * increment or decrement all the colors of the regions's tiles by a
     * expected value.
     *
     * @param value It is an integer that is how much we are going to change the
     * ilumination of the region.
     */
    public void changeLuminosity(int value) {
        Map<Coordinate, Tile> mapTiles = getMosaic().getMapTiles(); //La llave es de la izquierda y es un objeto de tipo de coordinate y el valor (derecha) es un objeto de tipo Tile    }
        for (Coordinate coordinate : coordinates) {
            mapTiles.get(coordinate).changeLuminosity(value);
        }
    }

    /**
     * This method change the status of the whole region between 0,1,2 (0=turn
     * off all the region,1=turn on all the region,2=turn on the tile and off
     * the figure).
     *
     * @param value It is the value of the status we recieve(0,1,2).
     */
    public void changeStatus(int value) {
        Map<Coordinate, Tile> mapTiles = getMosaic().getMapTiles(); //La llave es de la izquierda y es un objeto de tipo de coordinate y el valor (derecha) es un objeto de tipo Tile    }
        for (Coordinate coordinate : coordinates) {
            mapTiles.get(coordinate).changeStatus(value);
        }
    }

    //Getters and setters
    public String getName() {
        return this.name;
    }

    public Coordinate origin() {
        return this.origin;
    }

    public int getH() {
        return this.h;
    }

    public int getW() {
        return this.w;
    }

    public ArrayList<Coordinate> getCoordinates() {
        return this.coordinates;
    }

    public Mosaic getMosaic() {
        return mosaic;
    }

    public void setMosaic(Mosaic mosaic) {
        this.mosaic = mosaic;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOrigin(Coordinate origin) {
        this.origin = origin;
    }

    public void setH(int h) {
        this.h = h;
    }

    public void setW(int w) {
        this.w = w;
    }

}
