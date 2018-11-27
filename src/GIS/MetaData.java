package GIS;

import Coords.GpsCoord;
import Geom.Geom_element;
import Geom.Point3D;

public class MetaData implements Meta_data {
	private GpsCoord certainGpsCoord;
	private String[] data;

	public MetaData(Geom_element certainGpsCoord, String[] dataInCertainPoint) {
		this.certainGpsCoord = certainGpsCoord;
		this.data = dataInCertainPoint;
	}

	@Override
	public long getUTC() {

	}

	@Override
	public Point3D get_Orientation() {
		// TODO Auto-generated method stub
		return null;
	}

}
