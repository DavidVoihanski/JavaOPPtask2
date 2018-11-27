package GIS;

import java.util.Arrays;
import java.util.Date;


import Coords.GpsCoord;
import Geom.Geom_element;
import Geom.Point3D;

public class MetaData implements Meta_data {
	private GpsCoord certainGpsCoord;
	private String[] data;

	public MetaData(Geom_element certainGpsCoord, String[] dataInCertainPoint) {
		this.certainGpsCoord = (GpsCoord) certainGpsCoord;
		this.data = dataInCertainPoint;
	}

	@Override
	public long getUTC() {
		int[] dataInInteger = splitterForDateTime(this.data[3]);
		Date outPut=new Date();
		//Date outPut = new Date(dataInInteger[2], dataInInteger[1], dataInInteger[0], dataInInteger[3],
		//		dataInInteger[4]);
		//outPut.UTC(year, month, date, hrs, min, sec)
		return outPut.UTC(dataInInteger[0]-1900, dataInInteger[1]-1, dataInInteger[2], dataInInteger[3], dataInInteger[4], 0);
	}

	@Override
	public Point3D get_Orientation() {
		// TODO Auto-generated method stub
		return null;
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
			if (i <3) {
				outPut[i] = Integer.parseInt(date[i]);
			} else {
				outPut[i] = Integer.parseInt(time[i - 3]);
			}
		}
		return outPut;
	}

	@Override
	public String toString() {
		return "MetaData:" + certainGpsCoord + ", data: " + Arrays.toString(data);
	}

}
