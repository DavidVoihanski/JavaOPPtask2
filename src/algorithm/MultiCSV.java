package algorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

import File_format.Csv2Kml;
import GIS.GIS_layer;
import GIS.GisLayer;
import de.micromata.opengis.kml.v_2_2_0.Kml;

public class MultiCSV extends Csv2Kml{
	// TODO: finish this class which will contain methods to implement the recursive
	// algorithm to read quantity of CSV's, convert them to layers and create a
	// SINGLE (!!) KML file with all of them
	private Filter filter;
	private File pathNames[];
	private File folder;
	private int index;
	int size;
	private ArrayList<GIS_layer>layers;
	private Kml kml;
	
	public MultiCSV(String directory) {
		filter=new Filter();
		folder=new File("directory"); 
		pathNames=folder.listFiles(filter);
		index=0;
		size=pathNames.length;
		scan(index);
	}
	
	public void folder2Kml() {
		Iterator<GIS_layer>it=layers.iterator();
		GIS_layer currentLayer;
		while(it.hasNext()) {
			currentLayer=it.next();
			
		}
	}
	
    private void scan(int index) {
    	
	    GisLayer currLayer=(GisLayer)csv2Layer(pathNames[index].getPath());
	    layers.add(currLayer);
	    index++;
	    if(index>=size)return;
	    scan(index);
	    
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
