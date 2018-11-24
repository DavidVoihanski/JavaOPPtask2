package Coords;

import Geom.Point3D;
/**
 * this class is the implementation of coords_converter interface
 * @author Evgeny
 *
 */
public class MyCoords implements coords_converter {
	// earths radius based on given excel
	private final static int earthRadius = 6371000;
	private final static int diastanceLimit = 200 * 1000;

	/**
	 * adding a meter vector to GPS coordinate and returning the new GPS coordinate
	 * after the sum
	 */
	@Override
	public Point3D add(Point3D gps, Point3D local_vector_in_meter) {
		// calculating the lon norm
		final double lonNorm = Math.cos(Point3D.d2r(gps.x()));
		// converting the decimal degrees to radian
		double radianLat = Point3D.d2r(gps.x());
		double radianLon = Point3D.d2r(gps.y());
		;
		// converting the radian to meter
		double meterLat = (Math.sin(radianLat)) * earthRadius;
		double meterLon = (Math.sin(radianLon)) * (earthRadius * lonNorm);
		// adding the meters from the vector to the one we got
		double gps1MeterLat = meterLat + local_vector_in_meter.x();
		double gps1MeterLon = meterLon + local_vector_in_meter.y();
		double gps1MeterAlt = gps.z() + local_vector_in_meter.z();
		// converting the meter back to radian
		double radianGps1Lat = Math.asin(gps1MeterLat / (earthRadius));
		double radianGps1Lon = Math.asin(gps1MeterLon / (earthRadius * lonNorm));
		// converting the radian back to decimal degrees
		double gps1Lat = Point3D.r2d(radianGps1Lat);
		double gps1Lon = Point3D.r2d(radianGps1Lon);
		// returning the GPS point of Point3d type
		return new Point3D(gps1Lat, gps1Lon, gps1MeterAlt);
	}

	/**
	 * calculating the distance between two GPS point using the formula from given
	 * excel file which is calculated in vector3D method <br>
	 * NOTICE: the max distance this method can calculate is 200 km, any distance
	 * bigger than this will return NaN to indicate that the method didn't calculate
	 * a thing
	 */
	@Override
	public double distance3d(Point3D gps0, Point3D gps1) {
		Point3D meterDiffVector = new Point3D(vector3D(gps0, gps1));
		double outPutDistance = Math.sqrt(Math.pow(meterDiffVector.x(), 2) + Math.pow(meterDiffVector.y(), 2));
		if (outPutDistance >= diastanceLimit) {
			System.out.println("the diastnce is too big, cannot calculate it");
			return Double.NaN;
		}
		return outPutDistance;
	}

	/**
	 * calculating the meter vector between two GPS coordinates
	 */
	@Override
	public Point3D vector3D(Point3D gps0, Point3D gps1) {
		// calculating the lon norm
		final double lonNorm = Math.cos(Point3D.d2r(gps0.x()));
		// turning the difference between two given GPS cords to radian
		double radianLatDiff = Point3D.d2r(gps1.x() - gps0.x());
		double radianLonDiff = Point3D.d2r(gps1.y() - gps0.y());
		// turning radian to actual meters (and calculating the alt diff)
		double meterLatDiff = (Math.sin(radianLatDiff)) * earthRadius;
		double meterLonDiff = (Math.sin(radianLonDiff)) * (earthRadius * lonNorm);
		double meterAltDiff = gps1.z() - gps0.z();
		// returning a 3d point with these wanted values
		return new Point3D(meterLatDiff, meterLonDiff, meterAltDiff);
	}

	/**
	 * returning a double type array where [1]=> is the azimuth between given gps0
	 * and gps1 <br>
	 * [2]=> is the elevation between gps0 and gps1 <br>
	 * [3]=> is the distance between gps0 and gps1 <br>
	 * NOTICE: this method won't work for two coordinates which are too distant, the
	 * MAX distance is defined to be 200km
	 */
	@Override
	public double[] azimuth_elevation_dist(Point3D gps0, Point3D gps1) {
		double[] outPut = new double[3];
		// ***calculating the distance, we're starting from the distance because if the
		// GPS coordinates too distant we can't calculate nothing based on them:
		double distBetweenPoints = distance3d(gps0, gps1);
		if (distBetweenPoints == Double.NaN) {
			System.out.println("the diastnce is too big, cannot calculate it");
			return null;
		}
		outPut[2] = distBetweenPoints;
		// ***calculating the azimuth:
		// converting all GPS decimal degree values to radian:
		double radianLatGps0 = Point3D.d2r(gps0.x());
		double radianLonGps0 = Point3D.d2r(gps0.y());
		double radianLatGps1 = Point3D.d2r(gps1.x());
		double radianLonGps1 = Point3D.d2r(gps1.y());
		// calculating x & y according to the formula for forward azimuth [according
		// to:https://www.movable-type.co.uk/scripts/latlong.html]
		double y = Math.sin(radianLonGps1 - radianLonGps0) * Math.cos(radianLatGps1);
		double x = Math.cos(radianLatGps0) * Math.sin(radianLatGps1)
				- Math.sin(radianLatGps0) * Math.cos(radianLatGps1) * Math.cos(radianLonGps1 - radianLonGps0);
		// using arctan function on both values
		double azimuth = Math.atan2(y, x);
		// converting back to decimal degrees
		azimuth = Point3D.r2d(azimuth);
		if (azimuth < 0) {
			azimuth = 360 + azimuth;
		}
		outPut[0] = azimuth;
		// ***calculating the elevation based on the trigonometric functions tangens in
		// a right-angled triangle:
		double elevation = Math.atan((gps1.z() - gps0.z()) / distBetweenPoints);
		outPut[1] = Point3D.r2d(elevation);
		return outPut;
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
