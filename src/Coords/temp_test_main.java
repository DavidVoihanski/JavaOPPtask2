//package Coords;
//
//import Geom.Point3D;
//import java.util.Arrays;
//
///**
// * david ahi timhak et ze ahrei she-tesaiem, lo mahakti ki hashavti ulai she ze
// * iazor leha le helek me habdikot, begadol akol amur laavod
// * 
// * @author Eminem
// *
// */
//public class temp_test_main {
//
//	public static void main(String[] args) {
//		Point3D gps1 = new Point3D(30, 5.1, 670);
//		Point3D gps2 = new Point3D(30.1, 5, 600);
//		double dist=MyCoords.distance3d(gps1, gps2); //should be 538+-
//		//System.out.println(dist);
//		//gps1 = new Point3D(32.063607, 34.835309, 670);
//		//gps2 = new Point3D(31.990175, 34.800077, 9000);
//		dist=MyCoords.distance3d(gps1, gps2);
//		//System.out.println(dist);   //should be 8796+-
//		double arr[]=MyCoords.azimuth_elevation_dist(gps1, gps2);
//		for (int i = 0; i < arr.length; i++) {
//			System.out.println(arr[i]);
//		}
//		
//		//MyCoords test = new MyCoords();
//		// testZZ= test.add(new Point3D(32.103315, 35.209039), new Point3D(337.6989921,
//		// -359.2492069));
//		//System.out.println(Arrays.toString(test.azimuth_elevation_dist(test1, vector_meter)));
//		// System.out.println(test.distance3d(test1, vector_meter));
//	}
//
//}
