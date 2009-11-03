/**
 * 
 */
package com.renaissance.etl.web.sina

import com.renaissance.etl.transform.*
import com.renaissance.etl.models.*

SinaPageFactory factory = new SinaPageFactory();

stock = new StockBasicInfo(code:'601166',market:'SH');
today = new Date();
endDate  = today.minus(1);
startDate = endDate.minus(5);

println factory.getPriceHistoryURL(stock, startDate, endDate);

