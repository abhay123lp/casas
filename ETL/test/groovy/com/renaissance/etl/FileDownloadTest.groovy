
package com.renaissance.etl

String url = "http://market.finance.sina.com.cn/downxls.php?date=2009-08-24&symbol=sh601166"
def file = new FileOutputStream(new File("E:/SVN/renaissance/ETL/config/test.xls"));
def out = new BufferedOutputStream(file);
out << url.toURL().openStream();
out.close();

result = System.getProperty("zzz")
println result
file = new File(".");
println file.getAbsolutePath();


//System.getenv().each{prop, value->
//    println prop+"->"+value;
//}

println ""

TreeMap newMap = new TreeMap();
System.properties.each{prop, value->
    newMap.put(prop,value);
}

newMap.each{prop, value->
println prop+"->"+value;
}
