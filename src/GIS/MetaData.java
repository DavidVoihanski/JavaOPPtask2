package GIS;

import java.util.Arrays;
import java.util.Date;

import Coords.GpsCoord;
import Geom.Point3D;

public class MetaData implements Meta_data {
	private GpsCoord certainGpsCoord;
	private String[] data;

	public MetaData(GpsCoord certainGpsCoord, String[] dataInCertainPoint) {
		this.certainGpsCoord = certainGpsCoord;
		this.data = dataInCertainPoint;
	}

	public MetaData(Meta_data toCopyFrom) {
		MetaData temp = (MetaData) toCopyFrom;
		this.certainGpsCoord = temp.certainGpsCoord;
		this.data = temp.data;
	}

	@SuppressWarnings({ "static-access", "deprecation" })
	@Override
	public long getUTC() {
		int[] dataInInteger = splitterForDateTime(this.data[3]);
		Date outPut = new Date();
		return outPut.UTC(dataInInteger[0] - 1900, dataInInteger[1] - 1, dataInInteger[2], dataInInteger[3],
				dataInInteger[4], 0);
	}

	@Override
	public Point3D get_Orientation() {
		// TODO Auto-generated method stub
		return null;
	}
	public String[] getDataArray() {
		return this.data;
	}

	private int[] splitterForDateTime(String timeData) {
		String splitBy = " ";
		String[] timeAndDate = timeData.split(splitBy);
		int[] outPut = new int[5];
		splitBy = "-";
		String[] date = timeAndDate[0].split(splitBy);
		splitBy = ":";
		String[] time = timeAndDate[1].split(splitBy);
		for (int i = 0; i < outPut.length; i++) {
			if (i < 3) {
				outPut[i] = Integer.parseInt(date[i]);
			} else {
				outPut[i] = Integer.parseInt(time[i - 3]);
			}
		}
		return outPut;
	}

	@Override
	public String toString() {
		return "GPS location: " + certainGpsCoord + ", meta data: " + Arrays.toString(data);
	}

}
