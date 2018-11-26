package Geom;
import Coords.MyCoords;
public class GpsCoord extends MyCoords implements Geom_element {
	
	private Point3D thisP;
	
	public GpsCoord(double lat,double lon , double at) throws Exception {
		thisP=new Point3D(lat,lon,at);
		if(!isValid_GPS_Point(thisP))throw new Exception("Not a valid gps coord");
	}
	
	@Override
	public double distance3D(Point3D p) {
		if(!isValid_GPS_Point(p)) {
			System.out.println("Input point is not a gps point!");
			return Double.NaN;
		}
		return distance3d(thisP,p);
	}

	@Override
	public double distance2D(Point3D p) {
		return distance3D(p);
	}

	
}
