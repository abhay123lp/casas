/**
 * 
 */
package com.renaissance.etl.web.sina
import com.renaissance.etl.models.*

SinaDataExtractFacade facade = new SinaDataExtractFacade();

//facade.downloadIndustry();
//facade.downloadLocation();
//facade.downloadConcepts();
//facade.downloadSinaCategory();
//facade.downloadAllPrediction()


stock = new StockBasicInfo(code:'000001',market:'SZ');
today = new Date();
endDate  = today.minus(1);
startDate = endDate.minus(5);

//facade.downloadPriceHistory(stock, startDate, endDate)
//facade.downloadTransactionParticulars(stock, today);

facade.downloadAllTransactionParticulars(today);
