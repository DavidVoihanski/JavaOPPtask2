package GIS;

import java.util.ArrayList;
import java.util.Iterator;

import Coords.GpsCoord;
import File_format.Csv2Kml;

public class testing_Main {

	public static void main(String[] args) {
		ArrayList<String[]> a = Csv2Kml.csvReader("/csvFilesTest/WigleWifi_20171201110209.csv");
		GisElement test_1 = new GisElement(a.get(2));
		GisElement test_2 = new GisElement(a.get(3));
		GisLayer testlayer=new GisLayer();
		testlayer.add(test_1);
		testlayer.add(test_2);
		//Iterator<GIS_element>it=testlayer.();
		
		MetaData z = (MetaData) test_1.getData();
		System.out.println(z);
//		GpsCoord b = (GpsCoord) test_1.getGeom();
//		System.out.println(b.getInternalPoint());
//		MetaData c = (MetaData) test_1.getData();
//		long time=c.getUTC();
//		System.out.println(test_1.compareTo(test_2));
	}
}
