/**
 * 
 */
package com.renaissance.etl.web.baidu
import com.renaissance.etl.models.*

BaiduDataExtractFacade facade = new BaiduDataExtractFacade()
//facade.downloadMarketIndex()
//facade.verifyStockID()
//facade.downloadTransactionRecord()
//facade.dailyJob()
stock = new StockBasicInfo(code:"002280",name:"ÐÂÊÀ¼Í",market:"SZ");
facade.downloadTransactionRecord(stock)
