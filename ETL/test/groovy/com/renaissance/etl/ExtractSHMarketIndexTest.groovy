/*
 * Senario：
 * 从baidu下载沪市综合指数
 * 
 */
package com.renaissance.etl

import com.renaissance.etl.models.*
import groovy.util.slurpersupport.GPathResult
import com.renaissance.etl.web.baidu.*
import static com.renaissance.etl.transform.StockTransformer.*

def pageFactory = new BaiduPageFactory()
def GPathResult html = pageFactory.getSHIndexWebPage()
def MarketIndex marketIndex = transform2MarketIndex(html)

println "date = "+marketIndex.date
println "open = "+marketIndex.open
println "close = "+marketIndex.close
println "lastClose = "+marketIndex.lastClose
println "turnover = "+marketIndex.turnover
