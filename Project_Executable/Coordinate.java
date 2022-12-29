package Project;

import java.io.*;
import java.util.*;

/**
 * Coordinate Class
 *
 * @author Ruben Castro Gonzalez and Alejandro Carrazoni Entenza.
 */
public class Coordinate implements Comparable<Coordinate> {

    public int row;

    public int column;

    /**
     * Is in charge of initializating a coordinate.
     *
     * @param row Referred to the row at the beginning of the region.
     * @param column Referred to the column at the beginning of the region.
     */
    public Coordinate(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    /**
     * This method is in charge of comparing two coordinates and checking if
     * they are equals.
     *
     * @param coord The parameter is the coordinate of the origin of the region.
     * @return It returns -1 if either the row or the column is different from
     * the given one, it returns 1 if it is equal.
     */
    public int compareTo(Coordinate coord) {
        if (row < coord.row) {
            return -1;

        } else if (row > coord.row) {
            return 1;
        } else {

            if (column < coord.column) {
                return -1;
            } else if (column > coord.column) {
                return 1;
            } else {
                return 0;
            }

        }
    }

    /**
     * This method is used to print out the information about the coordinate,
     * this is, the row and the column of this one.
     *
     * @return It returns the information about the coordinate, row and column.
     */
    public String toString() {
        return "(" + row + "," + column + ")";
    }
}
