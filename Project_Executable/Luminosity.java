package Project;

/**
 * Luminosity Class
 *
 * @author Ruben Castro Gonzalez and Alejandro Carrazoni Entenza.
 */
public interface Luminosity {

    /**
     * This method is in charge of changing the luminosity of a tile, a region
     * or the whole mosaic.
     *
     * @param value This is the value of the change of ilumination.
     */
    public void changeLuminosity(int value);
}
