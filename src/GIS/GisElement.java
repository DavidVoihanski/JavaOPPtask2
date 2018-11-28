package GIS;

import java.util.InvalidPropertiesFormatException;
import Coords.GpsCoord;
import Geom.Geom_element;

public class GisElement implements GIS_element {
	private String[] dataLine;
	private MetaData elementsData;

	public GisElement(String[] certainLine) {
		this.dataLine = certainLine;
	}

	@Override
	public Geom_element getGeom() {
		GpsCoord outPut = null;
		try {
			outPut = new GpsCoord(Double.parseDouble(this.dataLine[6]), Double.parseDouble(this.dataLine[7]),
					Double.parseDouble(this.dataLine[8]));
		} catch (NumberFormatException | InvalidPropertiesFormatException e) {
			e.printStackTrace();
			return null;
		}
		return (Geom_element) outPut;
	}

	@Override
	public Meta_data getData() {
		this.elementsData = new MetaData(this.getGeom(), this.pullData());
		return ((Meta_data)elementsData);
	}

	private String[] pullData() {
		String[] outPut = { this.dataLine[0], this.dataLine[1], this.dataLine[2], this.dataLine[3], this.dataLine[4],
				this.dataLine[5], this.dataLine[9], this.dataLine[10] };
		return outPut;
	}

}
