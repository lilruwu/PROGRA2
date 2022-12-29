package Project;

/**
 * Exception Class
 * @author Ruben Castro Gonzalez and Alejandro Carrazoni Entenza.
 */
public class TileOutOfBoundsException extends Exception {

    /**
     * This is an exception that ocurrs when you introuduce a tile that is out of bounds of the mosaic.
     * @param string This is the name of the file where errors or exceptions are stored.
     */
    public TileOutOfBoundsException(String string) {
        super(string);
    }

}
