package algorithm;

import java.io.File;
import java.io.FileFilter;

public class Filter implements FileFilter {

	@Override
	public boolean accept(File pathname) {
		if(pathname.getPath().endsWith(".csv"))return true;
		return false;
	}

}
