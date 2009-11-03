/**
 * 
 */
package com.renaissance.etl
import java.util.zip.*;

ZipFile zFile = new ZipFile("E:/SVN/renaissance/data/000001/2009-08-25.zip");
ByteArrayOutputStream dest = new ByteArrayOutputStream(12000);

zFile.entries().each{entry->
    println "extracting: "+entry.getName();
    long size = entry.getSize();
    InputStream is = zFile.getInputStream(entry);
    
    int count = 0;
    byte[] data= new byte[1024];

    while( (count=is.read(data, 0, 1024)) !=-1){
        dest.write(data, 0, count);
    }
    dest.flush();
    dest.close();
} 

InputStream input = new ByteArrayInputStream(dest.toByteArray());
    input.eachLine{line ->
    println line;
}
    
