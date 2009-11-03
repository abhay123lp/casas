package com.renaissance.etl
import com.renaissance.etl.models.*
import java.text.DecimalFormat

public class RawStockIndexList{

    // return all possible stock indices on SH and SZ stock market
    public getRawStockList(){
       def formatter = new DecimalFormat("000000")
       
       //Shang Hai stock
       def indexList = 600000..<601999
       def shStockList = indexList.collect{
           new StockBasicInfo(code:formatter.format(it), market:StockBasicInfo.SH)
       }

       //Sheng Zhen Stock
       indexList = (1..1000) +(1696)+(1896)+ (2001..2400)
       def szStockList = indexList.collect{
           new StockBasicInfo(code:formatter.format(it), market:StockBasicInfo.SZ)
       }
       
       return shStockList+szStockList
   }
}
