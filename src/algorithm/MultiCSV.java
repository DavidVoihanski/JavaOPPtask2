package algorithm;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import File_format.Csv2Kml;
import GIS.GIS_layer;
import GIS.GisLayer;
import GIS.GisProject;
import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Kml;
/**
 * This class has 2 purposes:
 * 1: Recursively going through a folder and looking for files ending with .csv, returning an 
 *    array list of GIS_layes made from each file.
 *    
 * 2: Taking an array list of GIS_layes and converting them into a single KML file 
 * 
 * @author David&Evegeny
 *
 */
public abstract class MultiCSV extends Csv2Kml{
    /**
     *  Scans a directory for csv files and them to GIS_layers
     *  
     * @param directory the directory containing wanted csv files to convert
     * @return returns an arraylist of layers, each layer is a csv folder
     */
	public static GisProject readFolder(String directory) {
		GisProject layers=new GisProject();
		Filter filter=new Filter();
		File folder=new File(directory); 
		File pathNames[]=folder.listFiles(filter);
		int index=0;
		int size=pathNames.length;
		scan(index,size,layers,pathNames);
		return layers;
	}
	/**
	 * Takes an array list of GIS_layes and converts it to a KML file
	 * @param layers Input array list of GIS_layers
	 * @param outputPath wanted path to save the KML file at 
	 */
	public static void folder2Kml(ArrayList<GIS_layer>layers,String outputPath) {
		Kml kml=new Kml();
		Document doc = kml.createAndSetDocument();
		Iterator<GIS_layer>it=layers.iterator();
		GIS_layer currentLayer;
		while(it.hasNext()) {
			currentLayer=it.next();
			gisLayer2KML(currentLayer,kml,doc);
		}
		writeKml(kml,outputPath);
	}
	//recursively going through the files in a folder and turning them to GIS_layers
	private static void scan(int index,int size,GisProject layers,File pathNames[]) {
		
		GisLayer currLayer=(GisLayer)csv2Layer(pathNames[index].getPath());//converts to layer
		layers.add(currLayer);//adds to arraylist
		index++;
		if(index>=size)return;
		scan(index,size,layers,pathNames);

	}

















}
