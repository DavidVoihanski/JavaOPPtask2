

# Second assignment in object oriented programming course, Ariel uni.

**What is it:**

This repository contains the second assignment in Ariel university&#39;s object oriented programming course.

As for right now, this project is a foundation to the rest of the continuous course assignment.

This project main features are:

1. Calculations on GPS coordinates, such as adding a meter vector to a GPS coordinate, calculating 2D and 3D distance between two GPS coordinates, returning the 3D vector between two GPS coordinates, etc..
2. Converting CSV files to KML files using the JAK library by Micromata GmbH.

(link is found in the end of the readMe in the credits section)

**How to use:**

As for calculations preformed on GPS coordinates, you can use the MyCoords class, this class can work with instances of POINT3D class to represent GPS coordinates, while all of the calculation method is found on MyCoords.

 If you wish to work with instances of GpsCoord class to represent GpsCoords you can as well as all the methods which implemented on the MyCoord class are implemented in GpsCoord class too, in fact this class is logically more suitable to represent GPS coordinates.

**NOTICE: it&#39;s impossible to create a GpsCoord instance which values doesn&#39;t represent a valid GPS coordinate, the constructor will throw an exception.**

As for converting CSV type files to KML, you can use the class &quot;EntryPoint&quot; which is found in the default package, this class contains only a main method which is the entry point for this project, all you have to do, is use the String variable inputCsvFolderPathas a path to the wanted folder in which the CSV files are stored, then, use theoutputPathForKMLString variable as the path for the output KML file (please notice that if you will convert bunch of CSV files to KML, the output will be just one KML file separated by folders inside it), you can choose to rename the output KML usingchooseKmlFileName or you can just keep the default name – &quot;kmlTEST&quot;.

** **





**How this works:**

This project contains five packages, we believe the best method of describing it will be covering the packages:

**1)****&quot;Coords&quot; ****-** this package contains the interface &quot;cords\_converter&quot; which was given as instructions to fulfill, the class &quot;MyCoords&quot; implements this interface and represents all the calculations you can perform on GPS Coordinates in this project, &quot;GpsCoord &quot;is a class which represents a GPS coordinate (implementing the interface &quot;geom\_element&quot; found in the geom package), using Point3D as an internal point and contains all the methods which you can find in &quot;MyCoords&quot; and more.

**2)****&quot;File\_Format&quot; ****-** this package contains the class &quot;Csv2Kml&quot;, which is an abstract class containing only static methods as it has no data members, used to convert one single GIS layer to a KML file.

**3)  ****&quot;GIS&quot; ****-** this package represents **G** eographic **I** nformation **S** ystem, which contains interfaces which used as instructions to fulfill: GIS\_element, GIS\_layer, GIS\_project and Meta\_data.

These interfaces are implemented in the classes:

\*GisElement- which represents a single line in a CSV file, i.e. a single &quot;placemark&quot; in the soon to be KML file.

\*GisLayer- which is a set of GisElements, representing a whole CSV file, which is converted to a KML folder using the JAK library.

\*GisProject- which is a set of GisLayers, representing a whole folder of CSV files which is converted to a KML file with a certain number of folders inside it which depends on the amount of CSV files read.

\* LayerMetaData/ProjectMetaData/MetaData- are classes which represent the meta data of a GIS project/layer/element, as their features are somewhat different, we created a meta data class for any GIS class.

**4)****&quot;Geom&quot; ****-** which represents a geometric element, contains the interface &quot;geom\_element&quot;, which is implemented by &quot;Point3D&quot; and &quot;GpsCoord&quot; classes.

**5)****&quot;Algorithm&quot; ****-** which represents the algorithm used to read multiply CSV files to convert to a single, folder separated KML file, this package contains the class &quot;MultiCSV&quot; which is an abstract class which extends the &quot;&quot;Csv2Kml&quot; class, as the &quot;MultiCSV&quot; class **is a** kind of &quot;Csv2Kml&quot; class.

  **Credits:**

1)information about GPS coordinates:

[https://msdn.microsoft.com/en-us/library/aa578799.aspx](https://msdn.microsoft.com/en-us/library/aa578799.aspx)

[http://cosinekitty.com/compass.html](http://cosinekitty.com/compass.html)

[https://www.movable-type.co.uk/scripts/latlong.html](https://www.movable-type.co.uk/scripts/latlong.html)

2)JAK library, an API for converting KML files:

https://github.com/micromata/javaapiforkml

