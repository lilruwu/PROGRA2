package Project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Main Class
 *
 * @author Ruben Castro Gonzalez and Alejandro Carrazoni Entenza
 */
public class MosaicProject {

    private static ArrayList<String> insList = new ArrayList<>();

    public static FileReader fr;

    public static String line;

    public static String filename;

    public static Mosaic mosaic;

    /**
     * Main class, executes the program.
     *
     * @param args This is the name of the instructions file given by console.
     */
    public static void main(String[] args) {

        readInstructions(args[0]);
        doInstructions();
    }

    /**
     * This method processes the Instructions.txt file for get the instructions
     * to do given by console.
     *
     * @param instructions Is a String which is the name of the file.
     */
    private static void readInstructions(String instructions) {
        try {

            File archivo = new File("Project/" + instructions);
            fr = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fr);

            while ((line = br.readLine()) != null) {
                insList.add(line);

            }

        } catch (FileNotFoundException e) {
            System.out.println("File does not exist");
            Mosaic.saveToFile("error.txt", "File not found");
        } catch (IOException ex) {
            System.out.println("An error occured on the lecture of the file");
        } finally {

            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

    }

    /**
     * This method is the one that recieves the instructions of the
     * Instructions.txt file and do them.
     */
    private static void doInstructions() {
        for (int i = 0; i < insList.size(); i++) {
            line = insList.get(i);
            if (line.contains("ReadMosaic")) {

                filename = line.substring(line.indexOf(' ') + 1);
                mosaic = new Mosaic("Project/" + filename);

            } else if (line.contains("CreateRegion")) {
                String rName;
                String originXs, originYs, hs, ws;
                int originX, originY, h, w;

                String parameters = line.substring(line.indexOf(' ') + 1);
                String[] aux = parameters.split(",");

                rName = aux[0];
                originXs = aux[1];
                originX = Integer.parseInt(originXs);
                originYs = aux[2];
                originY = Integer.parseInt(originYs);
                hs = aux[3];
                h = Integer.parseInt(hs);
                ws = aux[4];
                w = Integer.parseInt(ws);
                Coordinate xy = new Coordinate(originX, originY);
                RectangularRegion region = new RectangularRegion(mosaic, rName, xy, h, w);
                mosaic.addRegion(region);

            } else if (line.contains("ChangeLuminosityMosaic")) {
                int value;
                String valueS;
                String parameters = line.substring(line.indexOf(' ') + 1);

                valueS = parameters;
                value = Integer.parseInt(valueS);
                mosaic.changeLuminosity(value);

            } else if (line.contains("ChangeLuminosityRegion")) {
                int value;
                String valueS;
                String regionname;
                String parameters = line.substring(line.indexOf(' ') + 1);
                String[] aux = parameters.split(",");

                valueS = aux[0];
                value = Integer.parseInt(valueS);
                regionname = aux[1];
                RectangularRegion region = mosaic.getRegion(regionname);
                region.changeLuminosity(value);

            } else if (line.contains("ChangeLuminosityTile")) {
                int value;
                String valueS;
                String originXs, originYs;
                int originX, originY;
                String parameters = line.substring(line.indexOf(' ') + 1);
                String[] aux = parameters.split(",");

                valueS = aux[0];
                value = Integer.parseInt(valueS);
                originXs = aux[1];
                originX = Integer.parseInt(originXs);
                originYs = aux[2];
                originY = Integer.parseInt(originYs);
                Tile tile = mosaic.getTile(new Coordinate(originX, originY));
                tile.changeLuminosity(value);
            } else if (line.contains("ChangeStatusMosaic")) {
                int value;
                String valueS;
                String parameters = line.substring(line.indexOf(' ') + 1);

                valueS = parameters;
                value = Integer.parseInt(valueS);
                mosaic.changeStatus(value);

            } else if (line.contains("ChangeStatusRegion")) {
                int value;
                String valueS;
                String regionname;
                String parameters = line.substring(line.indexOf(' ') + 1);
                String[] aux = parameters.split(",");

                valueS = aux[0];
                value = Integer.parseInt(valueS);
                regionname = aux[1];
                RectangularRegion region = mosaic.getRegion(regionname);
                region.changeStatus(value);

            } else if (line.contains("ChangeStatusTile")) {
                int value;
                String valueS;
                String originXs, originYs;
                String parameters = line.substring(line.indexOf(' ') + 1);
                String[] aux = parameters.split(",");

                int originX, originY;
                valueS = aux[0];
                value = Integer.parseInt(valueS);
                originXs = aux[1];
                originX = Integer.parseInt(originXs);
                originYs = aux[2];
                originY = Integer.parseInt(originYs);
                Tile tile = mosaic.getTile(new Coordinate(originX, originY));
                tile.changeStatus(value);

            } else if (line.contains("SortRegionsByArea")) {
                filename = line.substring(line.indexOf(' ') + 1);
                mosaic.sortRegionbyAreaAsc();
                mosaic.saveToFile(filename, mosaic.toStringRegions());

            } else if (line.contains("SaveMosaic")) {
                filename = line.substring(line.indexOf(' ') + 1);
                mosaic.saveToFile(filename, mosaic.toString());

            }

        }

    }

}
