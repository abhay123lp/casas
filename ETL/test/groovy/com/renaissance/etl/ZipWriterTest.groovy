/**
 * 
 */
package com.renaissance.etl
import java.util.zip.*;

//output
FileOutputStream dest = new FileOutputStream("E:/SVN/renaissance/data/test.zip");
ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(dest));

ZipEntry entry = new ZipEntry("test.txt");
out.putNextEntry(entry);

//input
FileInputStream fi = new FileInputStream("E:/SVN/renaissance/data/2009-08-25.txt");

//read and write
int size = 0;
byte[] buffer = new byte[1024];
while((size = fi.read(buffer, 0, 1024)) !=-1){
	out.write(buffer, 0, size);
}

fi.close()
out.closeEntry();
out.close();

