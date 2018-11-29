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
import GIS.MetaData;
import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.Placemark;

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

	public static GisLayer csv2GisLayer(ArrayList<String[]> csvReadedFile) {
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

	public static void gisLayer2KML(GisLayer certainLayer, String filePath) throws FileNotFoundException {
		final Kml kml = new Kml();
		Document doc = kml.createAndSetDocument();
		System.out.println("abcd");
		Iterator<GIS_element> it = certainLayer.iterator();
		StringBuilder descOfElement = new StringBuilder();
		while (it.hasNext()) {
			descOfElement = new StringBuilder();
			GisElement current = (GisElement) it.next();
			MetaData currentData = (MetaData) current.getData();
			String[] wholeData = currentData.getDataArray();
			descOfElement.append("BBSSID: "+wholeData[0] + "\n");
			descOfElement.append("Capabilities: " + wholeData[2] + "\n");
			descOfElement.append("Timestamp: " + currentData.getUTC() + "\n");
			descOfElement.append("date: " + wholeData[3]);
			Placemark place = doc.createAndAddPlacemark().withName(wholeData[1]).withOpen(Boolean.TRUE);
			place.setDescription(descOfElement.toString());
			place.createAndSetTimeStamp().withWhen(wholeData[3]);
			place.createAndSetPoint().addToCoordinates(((GpsCoord) current.getGeom()).getLon(),
					((GpsCoord) current.getGeom()).getLat(), ((GpsCoord) current.getGeom()).getAlt());
		}
		System.out.println("abcd#2");
		try {
			kml.marshal(new File(filePath));
		} catch (IOException e) {
			System.out.println("ERR in KML MARSHAL");
			return;
		}
		System.out.println("abcd#3");
	}

}
