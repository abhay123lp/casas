package com.renaissance.etl.web.baidu

import static com.renaissance.etl.transform.StockTransformer.*
import com.renaissance.etl.*
import com.renaissance.etl.models.*
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException
import groovy.util.slurpersupport.GPathResult
import com.renaissance.etl.transform.*

/**
 * Context:
 * stock index file:       the output file
 * BaiduPageFactory:       return specific web page of a stock
 * Transformer:            extract data model from web page
 * DataSource
 */
/*
 * Workflow: 
 * 1. for each stock index
 * 2. extract TransactionRecord, FinancialReport, BusinessEfficiency, 
 *    DebtPayingAbility, GrowingAbility, Profitability from Baidu web site
 * 3. Verify each of these data is new than the one in database
 * 4. save data model to database
 */

public class BaiduDataExtractFacade{
	def context = AppContextManager.getAppContext()
	def stockList = context.StockBasicInfo
	
	def pageFactory = new BaiduPageFactory()
	def sql = context.MYSQL
	boolean isTradingDate = false

    public boolean downloadMarketIndex(){
		pageFactory = new BaiduPageFactory()
		GPathResult html = pageFactory.getSHIndexWebPage()
		MarketIndex marketIndex = transform2MarketIndex(html)
		marketIndex.code='000001'
		
		Date tradingDate = StringHelper.parseDate(marketIndex.date)
		Date today = new Date()
        // check if the date is today
		if((today.dateString == tradingDate.dateString)){
			println "today is trading day!"
			isTradingDate = true
	        // save date to database
	        // need to define database for trading date
	        def dataSet = context.MarketIndex
            // save to mysql
            def columns = marketIndex.toMap()
            columns.each{key, value->
                println key+"->"+value
            }
			try{
                dataSet.add columns
            }catch(MySQLIntegrityConstraintViolationException e){
                //duplicate record, skip this one
            }
		}else{
			isTradingDate = false
		}
		return isTradingDate;
	}

	public void downloadTransactionRecord(stock){
        
        def dataSet = context.TransactionRecord
        def transactionRecord = new TransactionRecord()

        println "download "+stock.code+": "+stock.name
        def GPathResult html = pageFactory.getBaiduWebPage(stock, transactionRecord)
        def record = transform2TransactionRecord(html)
        if(record==null) return 
        
        //save to mysql
        def columns = record.toMap(stock)
        columns.each{key, value->
            println key+"->"+value
        }
        try{
            if(columns.size()>0){
                dataSet.add columns
            }
        }catch(MySQLIntegrityConstraintViolationException e){
            //duplicate record, skip this one
        }
        println ""

	}

	public void downloadTransactionRecord(){
		// 如果不是交易日，无需下载交易数据
		if(!isTradingDate) return 
        println "download transaction report now!"

		stockList.each{stock->
		    if(stock.code>='000000'){
	            downloadTransactionRecord(stock)
		    }
		}
    }

    public void downloadFinancialReport(){
        def financialReport = new FinancialReport()
        def dataSet = context.FinancialReport;

        stockList.each{stock->
            //财报
            println "download Financial Report for "+stock.code+": "+stock.name
            def html = pageFactory.getBaiduWebPage(stock, financialReport)
            def report = transform2FinancialReport(html)
    
            def columns = report.toMap(stock)
            columns.each{key, value->
                println key+"->"+value
            }
            try{
                dataSet.add columns
            }catch(MySQLIntegrityConstraintViolationException e){
                //duplicate record, skip this one
            }
        }
    }
    
    public void downloadBusinessEfficiency(){
        def businessEfficiency = new BusinessEfficiency()
        def dataSet = context[AppContextManager.BusinessEfficiency]
        
        stockList.each{stock->
            //经营效率
            def html = pageFactory.getBaiduWebPage(stock, businessEfficiency)
            def efficiency = transform2BusinessEfficiency(html)
            println "BusinessEfficiency"
            def columns = efficiency.toMap(stock)

            columns.each{key, value->
                println key+"->"+value
            }
            try{
                dataSet.add columns
            }catch(MySQLIntegrityConstraintViolationException e){
                //duplicate record, skip this one
            }
            println ""
        }
    }
    
    public void downloadDebtPayingAbility(){
        def debtPayingAbility = new DebtPayingAbility()
        def dataSet = context[AppContextManager.DebtPayingAbility]

        stockList.each{stock->
            //偿债能力
            def html = pageFactory.getBaiduWebPage(stock, debtPayingAbility)
            def debtPaying = transform2DebtPayingAbility(html)
            println "DebtPayingAbility"
            def columns = debtPaying.toMap(stock)
            
            columns.each{key, value->
                println key+"->"+value
            }
            try{
                dataSet.add columns
            }catch(MySQLIntegrityConstraintViolationException e){
                //duplicate record, skip this one
            }
            println ""
        }
    }
    
    public void downloadGrowingAbility(){
        def growingAbility = new GrowingAbility()
        def dataSet = context[AppContextManager.GrowingAbility]

        stockList.each{stock->
            //成长能力
            def html = pageFactory.getBaiduWebPage(stock, growingAbility)
            def growing = transform2GrowingAbility(html)
            println "GrowingAbility"
            def columns = growing.toMap(stock)
            columns.each{key, value->
                println key+"->"+value
            }
            try{
                dataSet.add columns
            }catch(MySQLIntegrityConstraintViolationException e){
                //duplicate record, skip this one
            }
            println ""
        }
    }

    public void downloadProfitability(){
        def profitability = new Profitability()
        def dataSet = context[AppContextManager.Profitability]

        stockList.each{stock->
            //盈利能力
            def html = pageFactory.getBaiduWebPage(stock, profitability)
            profitability = transform2Profitability(html)
            println "Profitability"
            def columns = profitability.toMap(stock)

            columns.each{key, value->
                println key+"->"+value
            }
            try{
                dataSet.add columns
            }catch(MySQLIntegrityConstraintViolationException e){
                //duplicate record, skip this one
            }
            println ""
        }
    }

    public void verifyStockID(){
        def rawStockIndexList = new RawStockIndexList().getRawStockList()
        def dataSet = context.StockBasicInfo
        
           rawStockIndexList.each{
            String code = it.code

            // flush writer and take a break
            if(code.toInteger() % 100 == 0) {
                sleep(1000)
            }

            StockBasicInfo basicInfo = new StockBasicInfo()
            GPathResult html = pageFactory.getBaiduWebPage(it, basicInfo)
            basicInfo = transform2StockBasicInfo(html)

            // check stockIndex is valid or not
            if(basicInfo != null){
                basicInfo.market = it.market
                Map columns = basicInfo.toMap()
                   columns.each{key, value->
                    println key+"->"+value
                }
                try{
                    dataSet.add columns
                }catch(MySQLIntegrityConstraintViolationException e){
                    //duplicate record, skip this one
                }
            }
           }
    }

    /** 
     * Extract FinancialReport, BusinessEfficiency, DebtPayingAbility
     * GrowingAbility and Profitability
     */ 
    public void weeklyJob(){
        downloadFinancialReport()
        downloadBusinessEfficiency()
        downloadDebtPayingAbility()
        downloadGrowingAbility()
        downloadProfitability()
    }
}
