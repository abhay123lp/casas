package com.renaissance.etl
import com.renaissance.etl.models.*

/**
 * load stock indices from file 
 */
public class StockRepository{
    def fileName
    private stockList
    
    public getStockList(){
        
        if(stockList == null){
            def file = new File(fileName)
            // iterate each line and create StockIndex
            StockBasicInfo stock = null
            def lines = file.readLines()
            
            stockList = lines.collect{line ->
                //println line
                def tockens = line.split(",")
                stock = new StockBasicInfo(code:tockens[0],name:tockens[1])
                return stock
            }
        }
        return stockList
    }
}
