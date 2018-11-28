package GIS;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.hamcrest.core.IsInstanceOf;

import Geom.Point3D;

public class GisLayer implements GIS_layer {
	private ArrayList<GIS_element> set;
    private LeyarsMetaData layerData; 
	public GisLayer() {
		this.set = new ArrayList<>();
		layerData=new LeyarsMetaData();
	}

	@Override
	public boolean add(GIS_element e) {
		Iterator<GIS_element> it = set.iterator();
		while (it.hasNext()) {
			GIS_element current = it.next();
			if (isEqual(current, e)) {
				return false;
			}
		}
		set.add(e);
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends GIS_element> c) {
		boolean output = true;
		Iterator<? extends GIS_element> it = c.iterator();
		while (it.hasNext()) {
			GIS_element current = it.next();
			if (!this.set.add(current)) {
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
		return this.set.iterator();
	}

	@Override
	public boolean remove(Object o){
			return this.set.remove(o);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		Iterator<?>it= c.iterator();
		boolean flag=true;
		while(it.hasNext()) {
			Object toRemove=it.next();
			if(!remove(toRemove)) {
				flag=false;
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
		return (Meta_data)layerData;
	}

	private boolean isEqual(GIS_element arg1, GIS_element arg2) {
		if ((!arg1.getData().toString().equals(arg2.getData().toString()))
				|| (arg1.getGeom().distance3D((Point3D) arg2) != 0)) {
			return false;
		}
		return true;
	}
}
