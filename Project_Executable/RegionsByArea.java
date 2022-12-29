package Project;

import java.util.Comparator;

/**
 * Regions by Area Class
 * @author Ruben Castro Gonzalez and Alejandro Carrazoni Entenza.
 */
public class RegionsByArea implements Comparator<RectangularRegion> {

    /**
     * This method compares two regions by its area.
     * @param o1 This is the first region we are going to compare.
     * @param o2 This is the second region we are going to compare.
     * @return If region1 has a bigger area that region2 returns 1, if region2 has a bigger area that region1 returns -1 if they are equal returns the region with the first name in alphabetical order.
     */
    public int compare(RectangularRegion o1, RectangularRegion o2) {
        if (o1.getArea() > o2.getArea()) {
            return 1;
        } else if (o1.getArea() < o2.getArea()) {
            return -1;

        } else {
            return o1.getName().compareTo(o2.getName());
        }

    }

}
