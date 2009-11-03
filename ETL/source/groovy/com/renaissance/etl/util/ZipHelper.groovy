/**
 * 
 */
package com.renaissance.etl.util
import java.util.zip.*;


public class ZipHelper{
    
    static public writeBuffer2ZipFile(byte[] source, String entryName, String outFileName){
    	writeBuffer2ZipFile(source, entryName, new File(outFileName))
    }
    static public writeBuffer2ZipFile(byte[] source, String entryName, File file){
    	
    	FileOutputStream dest = new FileOutputStream(file);
    	ZipOutputStream output = new ZipOutputStream(new BufferedOutputStream(dest));
    	
    	//add entry
    	ZipEntry entry = new ZipEntry(entryName);
    	output.putNextEntry(entry);
    	
    	//write
    	int size = 0;
    	byte[] buffer = new byte[1024];
   		output.write(source, 0, source.length);
    	
    	output.closeEntry();
    	output.flush();
    	output.close();
    } 
    
    static public byte[] readZipFile2Buffer(String fileName){
        ZipFile zFile = new ZipFile(fileName);
        ByteArrayOutputStream dest = new ByteArrayOutputStream(12000);
        
        zFile.entries().each{entry->
            println "extracting: "+entry.getName();
            InputStream is = zFile.getInputStream(entry);
            
            int count = 0;
            byte[] data= new byte[1024];
            
            while( (count=is.read(data, 0, 1024)) !=-1){
                dest.write(data, 0, count);
            }
        }
        dest.flush();
        dest.close();
        return dest.toByteArray();
    }
}
