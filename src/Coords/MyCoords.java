package Coords;

import Geom.Point3D;

public class MyCoords implements coords_converter {
	private final static int earthRadius = 6371;
	private final static double lonNorm = 0.847091174;

	@Override
	public Point3D add(Point3D gps, Point3D local_vector_in_meter) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * calculating the distance between two GPS point using the formula from given
	 * excel file
	 */
	@Override
	public double distance3d(Point3D gps0, Point3D gps1) {
		// turning the difference between two given GPS cords to radian
		double radianLatDiff = ((gps1.x() - gps0.x()) * Math.PI) / 180;
		double radianLonDiff = ((gps1.y() - gps0.y()) * Math.PI) / 180;
		// turning radian to actual meters
		double meterLatDiff = Math.sin(radianLatDiff) * earthRadius;
		double meterLonDiff = Math.sin(radianLonDiff) * earthRadius * lonNorm;
		// calculating the distance using the distance formula
		return (Math.sqrt(Math.pow(meterLatDiff, 2) + Math.pow(meterLonDiff, 2)));
	}

	@Override
	public Point3D vector3D(Point3D gps0, Point3D gps1) {
//		Point3D outPutVector3D = new 
		double altDiff= 
		double lonDiff;
		double altDiff;
	}

	@Override
	public double[] azimuth_elevation_dist(Point3D gps0, Point3D gps1) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * checking whether given GPS point is valid by values, NOTICE : altitude is
	 * check based on dead sea lowest altitude which is (-430) [according
	 * to:https://en.wikipedia.org/wiki/Dead_Sea] and mount everest highest altitude
	 * which is 8,848 [according to:https://en.wikipedia.org/wiki/Mount_Everest]
	 */
	@Override
	public boolean isValid_GPS_Point(Point3D p) {
		return ((p.x() >= -90 && p.x() <= 90) && (p.y() >= -180 && p.y() <= 180) && (p.z() >= -430 && p.z() <= 8848));
	}

}
