/**
 * 
 */
package com.renaissance.etl.util


byte[] buffer = ZipHelper.readZipFile2Buffer("E:/SVN/renaissance/data/test.zip");
//println new String(buffer)

ZipHelper.writeBuffer2ZipFile(buffer, 'test.txt','E:/SVN/renaissance/data/test1.zip')
println "Done!"