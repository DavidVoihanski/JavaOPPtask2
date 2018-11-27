package Coords;

import java.util.InvalidPropertiesFormatException;

import Geom.Point3D;

public class GpsCoord {
	/**
	 * this class represents a GPS coordinate
	 */
	// MyCoords instance used to "get" the methods from the MyCoords class
	private MyCoords convertMethods;
	// Point3D instance that represents the internal coordinates of the GPS
	// coordinate
	private Point3D internalPoint;

	/**
	 * GPS coordinate constructor
	 * 
	 * @param lat latitude of this certain GPS point in a decimal degree
	 *            representation
	 * @param lon longitude of this certain GPS point in a decimal degree
	 *            representation
	 * @param alt altitude of this certain GPS point in a decimal meters
	 *            representation
	 * @throws InvalidPropertiesFormatException in case the Point3D created doesn't
	 *                                          represent a valid GPS coordinate
	 */
	public GpsCoord(double lat, double lon, double alt) throws InvalidPropertiesFormatException {
		this.convertMethods = new MyCoords();
		this.internalPoint = new Point3D(lat, lon, alt);
		if (!convertMethods.isValid_GPS_Point(internalPoint)) {
			throw new InvalidPropertiesFormatException(
					"the point: " + internalPoint + " dose not represent a valid GPS coord");
		}
	}

	/**
	 * calculates the 3D distance between two GPS coordinates
	 * 
	 * @param inPutCoord input GPS coordinate
	 * @return 3D distance in meters
	 */
	public double distance3D(GpsCoord inPutCoord) {
		return (convertMethods.distance3d(this.internalPoint, inPutCoord.internalPoint));
	}

	/**
	 * calculates the 2D distance between two GPS coords
	 * 
	 * @param inPutCoord input GPS coordinate
	 * @return 2D distance in meters
	 */
	public double distance2D(GpsCoord inPutCoord) {
		return convertMethods.distance2d(this.internalPoint, inPutCoord.internalPoint);
	}

	/**
	 * calculates the difference 3D vector between two points
	 * 
	 * @param inPutCoord input GPS coordinate
	 * @return 3D vector which values are the difference between both GPS
	 *         coordinates in meters
	 */
	public Point3D vector3D(GpsCoord inPutCoord) {
		return convertMethods.vector3D(this.internalPoint, inPutCoord.internalPoint);
	}

	/**
	 * calculates the azimuth, elevation and distance between two GPS coordinates
	 * 
	 * @param inPutCoord input GPS coordinate
	 * @return a double array which has 3 elements as [0] is azimuth in decimal
	 *         degrees, [1] is elevation in decimal degrees and [2] is 3D distance
	 *         in meters
	 */
	public double[] azimuth_elevation_dist(GpsCoord inPutCoord) {
		return convertMethods.azimuth_elevation_dist(this.internalPoint, inPutCoord.internalPoint);
	}

}