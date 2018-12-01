package GIS;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class GisProject implements GIS_project {
	private ArrayList<GIS_layer>layers;
	private Meta_data data;
	/**
	 * Default constructor
	 */
	public GisProject() {
		layers=new ArrayList<GIS_layer>();
		data=new ProjectMetaData();
	}
	/**
	 * Adds a GIS_layer to this array list of GIS_layers
	 * @param e GIS_layer to be added
	 * @return returns true if added and false otherwise
	 */
	@Override
	public boolean add(GIS_layer e) {
		Iterator<GIS_layer>layerIt=layers.iterator();
		GIS_element currElement;
		GIS_layer currLayer;
		while(layerIt.hasNext()) {	//goes through every element in every layer and checks if e contains it
			currLayer=layerIt.next();
			Iterator<GIS_element>elementIt=currLayer.iterator();
			while(elementIt.hasNext()) {
				currElement=elementIt.next();
				if(e.contains(currElement))e.remove(currElement);	//if e contains the current element removes it from e
			}
		}
		if(e.size()>0) {	//if after all duplicates were removed e still has elements in it
			layers.add(e);	//and it to the list
			return true;
		}
		return false;
	}
	/**
	 * Adds every GIS_layer to this array list
	 * @param c Collection of GIS_layers to be added
	 * @return Returns true if all of the layers were added successfully and false otherwise
	 */
	@Override
	public boolean addAll(Collection<? extends GIS_layer> c) {
		Iterator<? extends GIS_layer>it=c.iterator();
		GIS_layer currLayer;
		boolean flag=true;
		while(it.hasNext()) {
			currLayer=it.next();
			if(!add(currLayer))flag=false;
		}
		return flag;
	}
	/**
	 * Resets this collection
	 */
	@Override
	public void clear() {
		layers=new ArrayList<GIS_layer>();
	}

	@Override
	public boolean contains(Object o) {
		return this.layers.contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return this.layers.containsAll(c);
	}

	@Override
	public boolean isEmpty() {
		return this.layers.isEmpty();
	}

	@Override
	public Iterator<GIS_layer> iterator() {
		return this.layers.iterator();
	}

	@Override
	public boolean remove(Object o) {
		return this.layers.remove(o);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return this.layers.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return this.layers.retainAll(c);
	}

	@Override
	public int size() {
		return this.layers.size();
	}

	@Override
	public Object[] toArray() {
		return this.layers.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return this.layers.toArray(a);
	}

	@Override
	public Meta_data get_Meta_data() {
		return this.data;
	}
	public ArrayList<GIS_layer> getArray(){
		return this.layers;
	}

}
