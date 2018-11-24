package Coords;

import Geom.Point3D;
import java.util.Arrays;

/**
 * david ahi timhak et ze ahrei she-tesaiem, lo mahakti ki hashavti ulai she ze
 * iazor leha le helek me habdikot, begadol akol amur laavod
 * 
 * @author Eminem
 *
 */
public class temp_test_main {

	public static void main(String[] args) {
		Point3D test1 = new Point3D(32.103315, 35.209039, 670);
		Point3D vector_meter = new Point3D(32.106352, 35.205225, 650);
		MyCoords test = new MyCoords();
		// testZZ= test.add(new Point3D(32.103315, 35.209039), new Point3D(337.6989921,
		// -359.2492069));
		System.out.println(Arrays.toString(test.azimuth_elevation_dist(test1, vector_meter)));
		// System.out.println(test.distance3d(test1, vector_meter));
	}

}
