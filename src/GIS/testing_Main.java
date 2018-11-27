package GIS;

import java.util.ArrayList;

import Coords.GpsCoord;
import File_format.Csv2Kml;

public class testing_Main {

	public static void main(String[] args) {
		ArrayList<String[]> a = Csv2Kml.csvReader("/csvFilesTest/WigleWifi_20171201110209.csv");
		GisElement test_1 = new GisElement(a.get(2));
		GpsCoord b = (GpsCoord) test_1.getGeom();
		System.out.println(b.getInternalPoint());
	}

}
