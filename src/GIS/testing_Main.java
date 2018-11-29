package GIS;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;


import File_format.Csv2Kml;

public class testing_Main {

	public static void main(String[] args) {
		ArrayList<String[]> a = Csv2Kml.csvReader("/csvFilesTest/WigleWifi_20171201110209.csv");
		GisLayer test = Csv2Kml.csv2GisLayer(a);
		String path = "C:" + File.separator + "Users" + File.separator + "evgen" + File.separator + "eclipse-workspace"
				+ File.separator + "OopAssignment2-4" + File.separator + "kmlFilesOutPut" + File.separator + "kml2.kml";
		try {
			Csv2Kml.gisLayer2KML(test, path);
		} catch (FileNotFoundException e) {
			System.out.println("ERR");
			return;
		}
		System.out.println("done..");
	}

}
