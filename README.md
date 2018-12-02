Second assignment in object oriented programming course, Ariel uni.
What is it:
This repository contains the second assignment in Ariel university's object oriented programming course.
As for right now, this project is a foundation to the rest of the continuous course assignment.
This project main features are:
1.	Calculations on GPS coordinates, such as adding a meter vector to a GPS coordinate, calculating 2D and 3D distance between two GPS coordinates, returning the 3D vector between two GPS coordinates, etc..
2.	Converting CSV files to KML files using the JAK library by Micromata GmbH.
(link is found in the end of the readMe in the credits section)

How to use:
As for calculations preformed on GPS coordinates, you can use the MyCoords class, this class can work with instances of POINT3D class to represent GPS coordinates, while all of the calculation method is found on MyCoords.
 If you wish to work with instances of GpsCoord class to represent GpsCoords you can as well as all the methods which implemented on the MyCoord class are implemented in GpsCoord class too, in fact this class is logically more suitable to represent GPS coordinates.
NOTICE: it's impossible to create a GpsCoord instance which values doesn't represent a valid GPS coordinate, the constructor will throw an exception.
As for converting CSV type files to KML, you can use the class "EntryPoint" which is found in the default package, this class contains only a main method which is the entry point for this project, all you have to do, is use the String variable inputCsvFolderPath as a path to the wanted folder in which the CSV files are stored, then, use the outputPathForKML String variable as the path for the output KML file (please notice that if you will convert bunch of CSV files to KML, the output will be just one KML file separated by folders inside it), you can choose to rename the output KML using chooseKmlFileName or you can just keep the default name – "kmlTEST".
 




How this works:
This project contains five packages, we believe the best method of describing it will be covering the packages:
1)"Coords"- this package contains the interface "cords_converter" which was given as instructions to fulfill, the class "MyCoords" implements this interface and represents all the calculations you can perform on GPS Coordinates in this project, "GpsCoord "is a class which represents a GPS coordinate (implementing the interface "geom_element" found in the geom package), using Point3D as an internal point and contains all the methods which you can find in "MyCoords" and more.
2)"File_Format"- this package contains the class "Csv2Kml", which is an abstract class containing only static methods as it has no data members, used to convert one single GIS layer to a KML file.
3)  "GIS"-this package represents Geographic Information System, which contains interfaces which used as instructions to fulfill: GIS_element, GIS_layer, GIS_project and Meta_data.
These interfaces are implemented in the classes:
*GisElement- which represents a single line in a CSV file, i.e. a single "placemark" in the soon to be KML file.
*GisLayer- which is a set of GisElements, representing a whole CSV file, which is converted to a KML folder using the JAK library.
*GisProject- which is a set of GisLayers, representing a whole folder of CSV files which is converted to a KML file with a certain number of folders inside it which depends on the amount of CSV files read.
* LayerMetaData/ProjectMetaData/MetaData- are classes which represent the meta data of a GIS project/layer/element, as their features are somewhat different, we created a meta data class for any GIS class.
4)"Geom"- which represents a geometric element, contains the interface "geom_element", which is implemented by "Point3D" and "GpsCoord" classes.
5)"Algorithm"- which represents the algorithm used to read multiply CSV files to convert to a single, folder separated KML file, this package contains the class "MultiCSV" which is an abstract class which extends the ""Csv2Kml" class, as the "MultiCSV" class is a kind of "Csv2Kml" class.
  Credits:
1)information about GPS coordinates:
https://msdn.microsoft.com/en-us/library/aa578799.aspx
http://cosinekitty.com/compass.html
https://www.movable-type.co.uk/scripts/latlong.html

2)JAK library, an API for converting KML files:
https://github.com/micromata/javaapiforkml
