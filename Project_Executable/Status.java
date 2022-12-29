package Project;
/**
 * Status Class
 * @author Ruben Castro Gonzalez and Alejandro Carrazoni Entenza
 */
public interface Status {

    /**
     * It is the Status interface method that change the status between 0,1,2(0=turn off all the mosaic/tile/figure,1=turn on all the mosaic/tile/figure,2=turn on the tile and off the figure).
     * @param value It is the integer value of the status we want to change (0,1,2).
     */
    public void changeStatus(int value);
}
