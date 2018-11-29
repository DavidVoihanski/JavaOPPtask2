package GIS;

import java.util.InvalidPropertiesFormatException;
import Coords.GpsCoord;
import Geom.Geom_element;

public class GisElement implements GIS_element {
	private MetaData elementsData;
	private GpsCoord location;

	public GisElement(String[] certainCsvLine) {
		this.location = this.getLocation(certainCsvLine);
		this.elementsData = this.getData(certainCsvLine);
	}

	public GisElement(GIS_element toCopyFrom) {
		this.elementsData = (MetaData) toCopyFrom.getData();
		this.location = (GpsCoord) toCopyFrom.getGeom();
	}

	@Override
	public Geom_element getGeom() {
		return (Geom_element) this.location;
	}

	@Override
	public Meta_data getData() {
		return (Meta_data) this.elementsData;
	}

	// private methods :
	private GpsCoord getLocation(String[] certainCsvLine) {
		GpsCoord outputLocation = null;
		try {
			outputLocation = new GpsCoord(Double.parseDouble(certainCsvLine[6]), Double.parseDouble(certainCsvLine[7]),
					Double.parseDouble(certainCsvLine[8]));
		} catch (NumberFormatException | InvalidPropertiesFormatException e) {
			e.printStackTrace();
			return null;
		}
		return outputLocation;
	}

	private String[] pullData(String[] certainCsvLine) {
		String[] outPut = { certainCsvLine[0], certainCsvLine[1], certainCsvLine[2], certainCsvLine[3],
				certainCsvLine[4], certainCsvLine[5], certainCsvLine[9], certainCsvLine[10] };
		return outPut;
	}

	private MetaData getData(String[] certainCsvLine) {
		MetaData outputData = new MetaData(this.getLocation(certainCsvLine), this.pullData(certainCsvLine));
		return (outputData);
	}

}
