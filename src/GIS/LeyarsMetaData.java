package GIS;

import java.util.ArrayList;
import java.util.Date;

import Geom.Point3D;

public class LeyarsMetaData implements Meta_data {
	
	private Date dateOfCreation;

	public LeyarsMetaData() {
		
		dateOfCreation = new Date();
	}

	@Override
	public long getUTC() {
	return this.dateOfCreation.UTC(this.dateOfCreation.getYear(), this.dateOfCreation.getMonth(), this.dateOfCreation.getDay(),
			this.dateOfCreation.getHours(), this.dateOfCreation.getMinutes(), this.dateOfCreation.getSeconds());
	}

	@Override
	public Point3D get_Orientation() {
		// TODO Auto-generated method stub
		return null;
	}
    @Override
    public String toString() {
    	return "This layer was created in: "+ this.dateOfCreation.toString();
    }
}
