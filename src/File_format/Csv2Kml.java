package File_format;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.omg.CORBA.Current;

import Coords.GpsCoord;
import GIS.GIS_element;
import GIS.GIS_layer;
import GIS.GisElement;
import GIS.GisLayer;
import de.micromata.opengis.kml.v_2_2_0.Kml;

public class Csv2Kml {
	public static ArrayList<String[]> csvReader(String filePath) {
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

	public static GIS_layer csv2GisLayer(ArrayList<String[]> csvReadedFile) {
		GisLayer output = new GisLayer();
		Iterator<String[]> it = csvReadedFile.iterator();
		while(it.hasNext()) {
			String[] currentCsvString = it.next();
			GisElement currentElement = new GisElement(currentCsvString);
			output.add(currentElement);
		}
		return output;
	}
	public static void gisLayer2KML (GisLayer certainLayer) throws FileNotFoundException {
		final Kml kml = new Kml();
		Iterator<GIS_element> it = certainLayer.iterator();
		while(it.hasNext()) {
			GisElement current =  (GisElement) it.next();
			kml.createAndSetPlacemark()
			   .withName(current.getData().toString()).withOpen(Boolean.TRUE)
					.createAndSetPoint().addToCoordinates();
		}
		kml.marshal(new File("/kmlFileOutout/kml1.kml"));
	}
	//KML file OUTPUT FROM GIS LAYER METHOD//
}
