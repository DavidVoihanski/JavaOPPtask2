package GIS;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import File_format.Csv2Kml;
import algorithm.MultiCSV;

public class testing_Main {
/**
 * just an example of how the CSV2KML works (and an evidence it dose work lol)
 */
	public static void main(String[] args) {
//		//single csv 2 kml file testing
	String pathKML = "C:" + File.separator + "Users" + File.separator + "evgen" + File.separator
				+ "eclipse-workspace" + File.separator + "OopAssignment2-4" + File.separator + "kmlFilesOutPut"
			+ File.separator ;
//		pathKML="output.kml";
//		String pathCSV = "/csvFilesTest/WigleWifi_20171201110209.csv";
//		try {
//			Csv2Kml.csv2kml(pathCSV, pathKML);
//		} catch (FileNotFoundException e) {
//			System.out.println("ERR in CSV2KML, ERR: " + e.getMessage());
//		}
		//
		String folderPath = "C:"+File.separator+"Users"+File.separator+"evgen"+File.separator+"eclipse-workspace"+File.separator+"OopAssignment2-4"+File.separator+"csvFilesTest";
		GisProject contents=MultiCSV.readFolder(folderPath);
		MultiCSV.folder2Kml(contents.getArray(),pathKML+File.separator+"kmlKKKK.kml");
		
	}

}
