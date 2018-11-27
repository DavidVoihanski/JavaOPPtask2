package Geom;
import Coords.MyCoords;
public class GpsCoord extends MyCoords implements Geom_element {
	
	private Point3D thisP;
	
	public GpsCoord(double lat,double lon , double alt) throws Exception {
		thisP=new Point3D(lat,lon,alt);
		if(!isValid_GPS_Point(thisP))throw new Exception("Not a valid gps coord");
	}
	
	@Override
	public double distance3D(Point3D p) {
		if(!isValid_GPS_Point(p)) {
			System.out.println("Input point is not a gps point!");
			return Double.NaN;
		}
		GpsCoord temp;
		try {
			temp = new GpsCoord(p.x(), p.y(), p.z());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Double.NaN;
		}
		return distance3d(this,temp);
	}

	@Override
	public double distance2D(Point3D p) {
		return distance3D(p);
	}

	public Point3D getPoint() {
		return this.thisP;
	}
}
