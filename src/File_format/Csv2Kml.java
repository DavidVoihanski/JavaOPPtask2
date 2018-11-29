package File_format;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import Coords.GpsCoord;
import GIS.GIS_element;
import GIS.GisElement;
import GIS.GisLayer;
import GIS.MetaData;
import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.Placemark;

/**
 * this class is used to read CSV files from certain path on the computer, and
 * convert them to GIS layer which is finally converted to KML file
 * 
 * @author Evgeny&David
 *
 */
public class Csv2Kml {
	/**
	 * converting an CSV file which found on the input path, and creating a KML file
	 * with all the information asked on the output path
	 * 
	 * @param csvInputPath        the place where the CSV file is found
	 * @param wantedKmlOutputPath the place where you wish to create the KML file
	 * @throws FileNotFoundException if the file isn't found
	 */
	public static void csv2kml(String csvInputPath, String wantedKmlOutputPath) throws FileNotFoundException {
		ArrayList<String[]> readedCsv = csvReader(csvInputPath);//"reading" the csv file
		GisLayer geoLayer = csv2GisLayer(readedCsv);//turning it to GIS layer
		gisLayer2KML(geoLayer, wantedKmlOutputPath);// turning the GIS layer to KML
		System.out.println("done...");
	}

	// private supporting methods:

	private static ArrayList<String[]> csvReader(String filePath) {
		ArrayList<String[]> readedInfoOutPut = new ArrayList<>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		String line = "";
		String csvSplitBy = ",";
		try {
			while ((line = br.readLine()) != null) {
				readedInfoOutPut.add(line.split(csvSplitBy));
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
					return null;
				}
		}
		return readedInfoOutPut;
	}

	private static GisLayer csv2GisLayer(ArrayList<String[]> csvReadedFile) {
		Iterator<String[]> it = csvReadedFile.iterator();
		it.next();
		it.next();
		GisLayer layerOutput = new GisLayer();
		while (it.hasNext()) {
			String[] currentLine = it.next();
			layerOutput.add(new GisElement(currentLine));
		}
		return layerOutput;
	}

	private static void gisLayer2KML(GisLayer certainLayer, String filePath) throws FileNotFoundException {
		final Kml kml = new Kml();
		Document doc = kml.createAndSetDocument();
		Iterator<GIS_element> it = certainLayer.iterator();
		StringBuilder descOfElement = new StringBuilder();
		while (it.hasNext()) {
			descOfElement = new StringBuilder();
			GisElement current = (GisElement) it.next();
			MetaData currentData = (MetaData) current.getData();
			String[] wholeData = currentData.getDataArray();
			descOfElement.append("BBSSID: " + wholeData[0] + "\n");
			descOfElement.append("Capabilities: " + wholeData[2] + "\n");
			descOfElement.append("Timestamp: " + currentData.getUTC() + "\n");
			descOfElement.append("date: " + wholeData[3]);
			Placemark place = doc.createAndAddPlacemark().withName(wholeData[1]).withOpen(Boolean.TRUE);
			place.setDescription(descOfElement.toString());
			String timeStamp = wholeData[3];
			timeStamp = timeStamp.replace(' ', 'T');
			place.createAndSetTimeStamp().withWhen(timeStamp);
			place.createAndSetPoint().addToCoordinates(((GpsCoord) current.getGeom()).getLon(),
					((GpsCoord) current.getGeom()).getLat(), ((GpsCoord) current.getGeom()).getAlt());
		}
		try {
			kml.marshal(new File(filePath));
		} catch (IOException e) {
			System.out.println("ERR in KML MARSHAL");
			return;
		}
	}

}
