package GIS;

import java.util.ArrayList;
import java.util.Collection;
import java.util.InvalidPropertiesFormatException;
import java.util.Iterator;


import Coords.GpsCoord;

public class GisLayer implements GIS_layer {
	private ArrayList<GIS_element> set;
	private LeyarsMetaData layerData;

	public GisLayer() {
		this.set = new ArrayList<>();
		layerData = new LeyarsMetaData();
	}

	@Override
	public boolean add(GIS_element e) {
		GisElement temp = new GisElement(e);
		Iterator<GIS_element> it = set.iterator();
		while (it.hasNext()) {
			GisElement current = (GisElement) it.next();
			try {
				if (isEqual(current, temp)) {
					return false;
				}
			} catch (InvalidPropertiesFormatException e1) {
				e1.printStackTrace();
				return false;
			}
		}
		set.add(temp);
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends GIS_element> c) {
		boolean output = true;
		Iterator<? extends GIS_element> it = c.iterator();
		while (it.hasNext()) {
			GIS_element current = it.next();
			if (!this.set.add((GisElement) current)) {
				output = false;
			}
		}
		return output;
	}

	@Override
	public void clear() {
		this.set.clear();
	}

	@Override
	public boolean contains(Object o) {
		return this.set.contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return this.set.containsAll(c);
	}

	@Override
	public boolean isEmpty() {
		return this.set.isEmpty();
	}

	@Override
	public Iterator<GIS_element> iterator() {
		return (Iterator<GIS_element>) this.set.iterator();
	}

	@Override
	public boolean remove(Object o) {
		return this.set.remove(o);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		Iterator<?> it = c.iterator();
		boolean flag = true;
		while (it.hasNext()) {
			Object toRemove = it.next();
			if (!remove(toRemove)) {
				flag = false;
			}
		}
		return flag;
	}

	@Override
	public int size() {
		return this.set.size();
	}

	@Override
	public Object[] toArray() {
		return this.set.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return this.set.toArray(a);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return this.retainAll(c);
	}

	@Override
	public Meta_data get_Meta_data() {
		return (Meta_data) layerData;
	}

	private boolean isEqual(GisElement arg1, GisElement arg2) throws InvalidPropertiesFormatException {
		GpsCoord arg1Location = new GpsCoord(arg1.getGeom());
		GpsCoord arg2Location = new GpsCoord(arg2.getGeom());
		MetaData arg1Data = new MetaData(arg1.getData());
		MetaData arg2Data = new MetaData(arg2.getData());
		if (arg1Location.distance3D(arg2Location) == 0 && arg1Data.toString().equals(arg2Data.toString())) {
			return true;
		}
		return false;
	}
}
