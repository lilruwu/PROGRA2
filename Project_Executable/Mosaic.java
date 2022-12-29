package Project;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Mosaic Class
 *
 * @author Ruben Castro Gonzalez and Alejandro Carrazoni Entenza
 */
public class Mosaic implements Luminosity, Status {

    //Attributes
    public Tile tile;
    public Map<Coordinate, Tile> mapTiles = new TreeMap<>();
    public int rows;
    public int columns;
    private ArrayList<RectangularRegion> regions = new ArrayList<>();
    private static ArrayList<String> tilesArchivo = new ArrayList<>();
    File archivo;
    FileReader fr;
    BufferedReader br;
    String rowSize = "";
    String columnSize = "";

    // Static attributes 
    String mosaicToString = "";
    String regionsToString = "";
    String pixels = "";
    int pixels_int;

// Constructors
    /**
     * This is the constructor of the whole mosaic, it reads the TileMosaic.txt
     * file and creates a mosaic with the defined paramaters in the file.
     *
     * @param input This is the input file(TileMosaic.txt).
     */
    Mosaic(String input) {

        String line = "";
        String text = "";
        char car = ' ';
        String elementin = "";
        String elementlist = "";
        String r = "";
        String c = "";
        String s = "";
        int r_int, c_int, s_int;
        String RED = "";
        String RED2 = "";
        String GREEN = "";
        String GREEN2 = "";
        String BLUE = "";
        String BLUE2 = "";
        int RED_int, GREEN_int, BLUE_int, RED2_int, GREEN2_int, BLUE2_int;
        String figure = "";
        String position = "";
        String radius = "";
        int radius_int;
        String height;
        int height_int;
        String width;
        int width_int;
        String height2;
        int length2;
        int length3;
        String height3;
        String[] list;
        String height4;
        String width2;
        String width3;
        String width4;
        String[] list2;
        Color colorin;
        Color colorin2;
        Tile tile_output = null;

        try {
            archivo = new File(input);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            // Reading of the file
            String linea;
            while ((linea = br.readLine()) != null) {
                tilesArchivo.add(linea);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist");
            saveToFile("error.txt", "File not found");
        } catch (IOException ex) {
            System.out.println("An error occured on the lecture of the file");
        } finally {
            // In the finally we close the file, to make sure that it is closed even if it´s good or if it pops an exception
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        for (int j = 0; j < tilesArchivo.size(); j++) {
            line = tilesArchivo.get(j);
            car = line.charAt(0);

            if (car == '*') {
                continue;
            } else if (car != '(') {

                rowSize = line.substring(0, line.indexOf("x"));
                setRows(Integer.parseInt(rowSize));
                // System.out.println(rowSize);

                columnSize = line.substring(line.indexOf("x") + 1, line.indexOf(","));
                setColumns(Integer.parseInt(columnSize));
                pixels = line.substring(line.indexOf("x", 4) + 1);
                pixels_int = Integer.parseInt(pixels);

                initialize();

            } else {

                elementin = line.substring(0, line.indexOf(':'));

                r = elementin.substring(1, elementin.indexOf(','));
                r_int = Integer.parseInt(r);
                c = elementin.substring(elementin.indexOf(',') + 1, elementin.indexOf(")"));
                c_int = Integer.parseInt(c);

                Coordinate coord = new Coordinate(r_int, c_int);
                //With this if we are checking if the coordinate of the tile is inside the range of the mosaic
                if ((r_int > rows) || (c_int > columns)) {
                    try {
                        throw new TileOutOfBoundsException("The coordinate of the tile is out of the mosaic´s range");
                    } catch (TileOutOfBoundsException ex) {
                        System.out.println(ex.getMessage());
                        saveToFile("error.txt", ex.getMessage());
                        System.exit(0);
                    }
                }

                //value of the state
                s = line.substring(line.indexOf(':') + 1, line.indexOf("{"));
                s_int = Integer.parseInt(s);

                if (line.length() < 24) { // This means it doesn´t have figure

                    RED = line.substring(line.indexOf("R") + 1, line.indexOf("G"));
                    RED_int = Integer.parseInt(RED);

                    GREEN = line.substring(line.indexOf("G") + 1, line.indexOf("B"));
                    GREEN_int = Integer.parseInt(GREEN);

                    BLUE = line.substring(line.indexOf("B") + 1, line.indexOf("}"));
                    BLUE_int = Integer.parseInt(BLUE);

                    colorin = new Color(RED_int, GREEN_int, BLUE_int);
                    tile_output = new Tile(colorin, s_int);
                    tile_output.setSize(pixels_int, pixels_int);
                    mapTiles.put(coord, tile_output);

                } else {

                    RED = line.substring(line.indexOf("R") + 1, line.indexOf("G"));
                    RED_int = Integer.parseInt(RED);

                    GREEN = line.substring(line.indexOf("G") + 1, line.indexOf("B"));
                    GREEN_int = Integer.parseInt(GREEN);

                    BLUE = line.substring(line.indexOf("B") + 1, line.indexOf(",", 5));
                    BLUE_int = Integer.parseInt(BLUE);

                    colorin = new Color(RED_int, GREEN_int, BLUE_int);

                    figure = line.substring(line.indexOf(",", 5) + 1, line.indexOf(':', 8));

                    RED2 = line.substring(line.indexOf("{", 10) + 2, line.indexOf("G", 18));
                    RED2_int = Integer.parseInt(RED2);

                    GREEN2 = line.substring(line.indexOf("G", 20) + 1, line.indexOf("B", 23));
                    GREEN2_int = Integer.parseInt(GREEN2);

                    BLUE2 = line.substring(line.indexOf("B", 22) + 1, line.indexOf(",", 25));
                    BLUE2_int = Integer.parseInt(BLUE2);

                    colorin2 = new Color(RED2_int, GREEN2_int, BLUE2_int);

                    position = line.substring(line.indexOf(",", 24) + 1, line.indexOf(",", 24) + 2);

                    if (figure.equals("CIR")) {
                        radius = line.substring(line.indexOf(",", 24) + 3, line.indexOf("}"));
                        radius_int = Integer.parseInt(radius);

                        //Correct use of the inheritance
                        Figure circlef = new Circle(radius_int, colorin2);

                        tile_output = new Tile(colorin, circlef, position, s_int);

                        tile_output.setSize(pixels_int, pixels_int);
                        mapTiles.put(coord, tile_output);

                    } else if (figure.equals("REC")) {
                        length2 = line.length();
                        height2 = line.substring(0, length2);
                        height3 = new String(height2);
                        list = height3.split(",");
                        height_int = Integer.parseInt(list[4]);

                        length3 = line.length();
                        width2 = line.substring(0, length3);
                        width3 = new String(width2);
                        list2 = width3.split(",");
                        width4 = line.substring(line.indexOf(list2[5]), line.indexOf("}"));
                        width_int = Integer.parseInt(width4);

                        Figure rectanglef = new Rectangle(height_int, width_int, colorin2);
                        tile_output = new Tile(colorin, rectanglef, position, s_int);
                        tile_output.setSize(pixels_int, pixels_int);
                        mapTiles.put(coord, tile_output);

                    } //Closes the tile with rectangle
                }  	// Closes the tile with figure   
            }
        } // Closes the while input file

    }

    /**
     * This method saves the mosaic into the output mosaic file.
     *
     * @param output This is the output mosaic file (OutputMosaic.txt)
     * @param content This is the content that will be stored into the file, in
     * this case is the whole mosaic.
     */
    public static void saveToFile(String output, String content) {
        FileWriter outputFile = null;
        try {
            outputFile = new FileWriter("Project/" + output); //esto escribe en el archivo 
            outputFile.write(content);
            outputFile.close();

        } catch (FileNotFoundException e1) {
            System.out.println("File couldn't be opened");
            System.exit(-1);
        } catch (IOException e2) {
            System.out.println(e2);
        }
    }

    // Instance Methods
    /**
     * This method initializes not defined tiles of a mosaic in white color.
     */
    public void initialize() {
        for (int row1 = 1; row1 < rows + 1; row1++) {
            for (int col1 = 1; col1 < columns + 1; col1++) {
                Coordinate coord = new Coordinate(row1, col1);
                Tile tileaux = new Tile(Color.WHITE(), 1);
                tileaux.setSize(pixels_int, pixels_int);
                mapTiles.put(coord, tileaux);
            }
        }
    }

    /**
     * This method prints the whole mosaic information.
     *
     * @return It returns an string with all the iformation of the mosaic.
     */
    public String toString() {
        mosaicToString = rowSize + "x" + columnSize + "," + pixels + "x" + pixels + "\n";
        Iterator it = mapTiles.keySet().iterator(); //Iterator is an object that allows to go through a collection of data

        while (it.hasNext()) { //Checks if there is something later
            Coordinate coord = (Coordinate) it.next(); //Returns the object coordinate
            mosaicToString = mosaicToString + ("(" + coord.getRow() + "," + coord.getColumn() + "):" + mapTiles.get(coord).toString() + "}\n");
        }

        return mosaicToString;
    }

    /**
     * This method prints all the information of all the regions of a mosaic.
     *
     * @return It returns all the informations of all the regions of the mosaic.
     */
    public String toStringRegions() {

        regionsToString = "";
        for (RectangularRegion region : regions) {
            regionsToString = regionsToString + region.toString() + "\n";
        }
        return regionsToString;
    }

    /**
     * This method returns a selected region of the mosaic.
     *
     * @param name It is the name of the region we want to get.
     * @return It returns an string that is the information of the region we
     * want to get.
     */
    public RectangularRegion getRegion(String name) {
        RectangularRegion regionfounded = null;
        for (RectangularRegion region1 : regions) {
            if (region1.getName().equals(name)) {
                regionfounded = region1;
            }

        }
        return regionfounded;
    }

    /**
     * This method returns all the information of a tile of the mosaic.
     *
     * @param c It is the coordinates of the tile that we want to get.
     * @return It returns the information of the tile we want to get.
     */
    public Tile getTile(Coordinate c) {
        return mapTiles.get(c); //Funcion propia de las Collections
    }

    /**
     * This method analyze the tiles and get the figure each tile has in it.
     *
     * @return Returns the figure name each tile has in it.
     */
    public ArrayList<String> listFigureClasses() {
        ArrayList<String> listFigures = new ArrayList<>();
        Iterator it = mapTiles.keySet().iterator(); //Simular un bucle de tipo for-each para recorrer el map
        while (it.hasNext()) {
            Coordinate k = (Coordinate) it.next();
            if (getTile(k).hasFigure()) {
                String figureName = getTile(k).getFigure().getClass().getSimpleName();
                if (!listFigures.contains(figureName)) {
                    listFigures.add(figureName);
                }
            }
        }
        return listFigures;
    }

    /**
     * This method counts how many figures of each type we have in a mosaic.
     *
     * @param figureClass It is the type of figure we want to count.
     * @return Returns an integer that is the number of figures of the type we
     * want(circles, rectangles or blank) we want to get.
     */
    public int totalNumberFiguresClass(String figureClass) {
        int n = 0;
        Iterator it = mapTiles.keySet().iterator();
        while (it.hasNext()) {
            Coordinate k = (Coordinate) it.next();
            if (getTile(k).hasFigure()) {
                String figureName = getTile(k).getFigure().getClass().getSimpleName();
                if (figureName.equals(figureClass)) {
                    n++;
                }
            } else {
                n++;
            }
        }
        return n;
    }

    /**
     * This method sort all the regions by their area and they coordinates too.
     */
    public void sortRegionbyAreaAsc() {
        for (RectangularRegion r : regions) {
            r.sortByCoordinateAsc();
        }
        Collections.sort(regions, new RegionsByArea());
    }

    /**
     * This method is used to add a new region to the mosaic.
     *
     * @param r Is a region parameters that we pass to the method to add a
     * region to the mosaic.
     */
    public void addRegion(RectangularRegion r) {

        regions.add(r);

    }

    /**
     * This method changes the luminosity of the whole mosaic, that means
     * increment or decrement all the colors of the mosaic's tiles by a expected
     * value.
     *
     * @param value It is an integer that is how much we are going to change the
     * ilumination of the mosaic.
     */
    public void changeLuminosity(int value) {
        Iterator ite = mapTiles.keySet().iterator(); //Creamos el objeto iterator para que se desplaze por las llaves de el objeto mapTiles.
        while (ite.hasNext()) {
            Coordinate coor = (Coordinate) ite.next(); //Es un casting, conversión de tipo, transformamos un objeto a una coordenada en específico.
            mapTiles.get(coor).changeLuminosity(value);
        }
    }

    /**
     * This method change the status of the whole mosaic between 0,1,2 (0=turn
     * off all the mosaic,1=turn on all the mosaic,2=turn on the tile and off
     * the figure).
     *
     * @param value It is the value of the status we recieve(0,1,2).
     */
    public void changeStatus(int value) {
        Iterator ite = mapTiles.keySet().iterator(); //Creamos el objeto iterator para que se desplaze por las llaves de el objeto mapTiles.
        while (ite.hasNext()) {
            Coordinate coor = (Coordinate) ite.next(); //Es un casting, conversión de tipo, transformamos un objeto a una coordenada en específico.
            mapTiles.get(coor).changeStatus(value);
        }

    }

    // Getters And Setters
    public Tile getTile() {
        return this.tile;
    }

    public int getRows() {
        return this.rows;
    }

    public int getColumns() {
        return this.columns;
    }

    public Collection<RectangularRegion> getRegions() {
        return this.regions;
    }

    public Map<Coordinate, Tile> getMapTiles() {
        return mapTiles;
    }

    public void setMapTiles(Map<Coordinate, Tile> mapTiles) {
        this.mapTiles = mapTiles;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public void setRegions(ArrayList<RectangularRegion> regions) {
        this.regions = regions;
    }
}
