package com.renaissance.etl;

//import org.codehaus.groovy.scriptom.*;
//import org.codehaus.groovy.scriptom.tlb.office.excel.XlChartType
//import org.codehaus.groovy.scriptom.tlb.office.excel.XlSheetType
//import org.codehaus.groovy.scriptom.tlb.office.excel.XlRowCol
//import org.codehaus.groovy.scriptom.tlb.office.excel.XlChartLocation
//import org.codehaus.groovy.scriptom.tlb.office.excel.Excel;
//import org.codehaus.groovy.scriptom.tlb.office2003.excel.*
//import org.codehaus.groovy.scriptom.util.office.ExcelHelper

//def helper = new ExcelHelper();

//helper.process(new File("E:\\SVN\\renaissance\\ETL\\config\\sh601166_2009-08-24.xls")){workbook ->
//    def worksheet = workbook.Sheets.Item(1);
//    SafeArray a = worksheet.UsedRange.Value
//    def rows = a.bounds[0];
//    def cols = a.bounds[1];
//    println "\t${a.toString()}"
//    println "a.getBounds().size() ="+a.getBounds().size() 
//    println rows.size()
//    println cols.size()
//    
//    a.bounds[0].each{row->
//      println "row = "+row;
//      a.bounds[1].each{col-> 
//    	  print a[row,col].toString()+"\t"
//      }
//      println ""
//    }
//
//}
