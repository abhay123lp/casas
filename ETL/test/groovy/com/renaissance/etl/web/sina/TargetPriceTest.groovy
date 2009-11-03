/**
 * 
 */
package com.renaissance.etl.web.sina

import com.renaissance.etl.web.*
import groovy.util.slurpersupport.GPathResult
import com.renaissance.etl.transform.*
import com.renaissance.etl.*

context = AppContextManager.getAppContext()
temp = context["StockRepository"]
println temp.class.name
stockList = temp.getStockList()

facade = new SinaDataExtractFacade()

stockList.each{stock->
    println stock.code+": "+stock.name
    facade.downloadPrediction(stock.code)
}






