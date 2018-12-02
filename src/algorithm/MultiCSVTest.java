package algorithm;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.Iterator;

import org.junit.jupiter.api.Test;

import Coords.GpsCoord;
import GIS.GIS_element;
import GIS.GIS_layer;
import GIS.GisElement;
import GIS.GisLayer;
import GIS.GisProject;

class MultiCSVTest {

	@Test
	void testReadFolder() {
		// the tester CSV's was put in this folder:
		String inputPath = "C:" + File.separator + "Users" + File.separator + "evgen" + File.separator
				+ "eclipse-workspace" + File.separator + "OopAssignment2-4" + File.separator + "csvFilesTest";
		GisProject testProject = MultiCSV.readFolder(inputPath);
		Iterator<GIS_layer> it = testProject.iterator();
		int counter;
		assertTrue(testProject.size() == 2);// asserting that the method did read 2 files
		while (it.hasNext()) {
			GisLayer current = (GisLayer) it.next();
			if (current.size() != 159 && current.size() != 185) {// asserting we got the right amount of elements in
																	// each read file
				fail("not enough csv lines scanned");
			}
			// we planted a unique GPS coord in the 122th line of both CSV files, now we're
			// asserting that this GPS coord was read correctly 
			counter = 0;
			Iterator<GIS_element> itLayer = current.iterator();
			while (itLayer.hasNext()) {
				counter++;
				GIS_element currentElement = (GisElement) itLayer.next();
				if (counter == 120) {
					GpsCoord testGPS = (GpsCoord) currentElement.getGeom();
					System.out.println(testGPS);
					if (testGPS.getLat() != 32.17112265 && testGPS.getLon() != 34.8133483
							&& testGPS.getAlt() != 30.37432785) {
						fail("wrong values in certain csv element");
					}
				}
			}
		}
	}

//	@Test
//	void testFolder2Kml() {
//		fail("Not yet implemented");
//	}

}
