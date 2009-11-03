package com.renaissance.etl

import grails.spring.BeanBuilder
import com.renaissance.etl.models.StockBasicInfo
import com.renaissance.etl.web.baidu.*

import static com.renaissance.etl.transform.StockTransformer.*
import static java.lang.System.gc


/**
 * Context:
 * RawStockIndexGenerator: generate list of raw stock index
 * BaiduPageFactory:       return specific web page of a stock
 * Transformer:            extract data model from web page
 * stock index file:       the output file
 */

def rawStockIndexList = new RawStockIndexList().getRawStockList()
def pageFactory = new BaiduPageFactory()
def output = new File("config/stocks.txt")
output.delete()
output.createNewFile()

/*
 * Workflow: 
 * 1. generate URl from raw stock index
 * 2. parse the web page to html tree
 * 3. transform the html tree to data model of StockIndex
 * 4. write StockIndex to output file
 */
def writer = output.newWriter()
 
 rawStockIndexList.each{
     def index = it.code

     // flush writer and take a break
     if(index.toInteger() % 10 == 0) {
         writer.flush()
         sleep(1000)
     }

     stock = new StockBasicInfo()
     def html = pageFactory.getBaiduWebPage(it, stock)
     stock = transform2StockBasicInfo(html)

     // check stockIndex is valid or not
     if(stock != null){
         stock.market = it.market
         writer.writeLine(stock.code+","+stock.name+","+stock.market+","+stock.industry.join(","))
     }
 }
writer.close()




