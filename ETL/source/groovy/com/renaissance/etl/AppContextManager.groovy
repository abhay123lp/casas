package com.renaissance.etl
import org.springframework.context.ApplicationContext
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource
import groovy.sql.Sql
import groovy.sql.DataSet

/**
 * Application context
 * 1. Stock index repository
 * 2. BaiduPageFactory
 * 3. Transformer
 * 4. DataSource 
 */
public class AppContextManager{
    
    private static appContext = new HashMap()
    public static final MYSQL = "mysql"
    public static final TestStocks = "testStocks"
    public static final StockRepository = "StockRepository"
    
    public static final MarketIndex = "MarketIndex"
    public static final StockBasicInfo = "StockBasicInfo"
    public static final TransactionRecord = "TransactionRecord"
    public static final FinancialReport = "FinancialReport"
    public static final EPSPrediction = "EPSPrediction"
    public static final TargetPrice = "TargetPrice"
    public static final Concept = "Concept"
    public static final Dynamic = "Dynamic"
    public static final Static = "Static"
    public static final Trend = "Trend"

    public static final BusinessEfficiency = "BusinessEfficiency"
    public static final DebtPayingAbility = "DebtPayingAbility"
    public static final GrowingAbility = "GrowingAbility"
    public static final Profitability = "Profitability"

	public static getAppContext(){

    	if(appContext.size()==0){
    		String dataDir = System.getProperty("data.dir");
    		if(dataDir == null){
    			println "the system property 'data.dir' is missing, please define it with -Ddata.dir!";
    			System.exit(0);
    		}
    		
        	appContext["testStocks"] = new StockRepository(fileName:"config/stocks_test.txt")
        	appContext["StockRepository"] = new StockRepository(fileName:"config/stocks.txt")
        	def mysql = Sql.newInstance(
                            'jdbc:mysql://localhost:3306/stock',
                            "damon",
                            "1024Leo!",
                            'com.mysql.jdbc.Driver' )

            appContext["mysql"] = mysql
            appContext["MarketIndex"] = mysql.dataSet("market_index")
            appContext["StockBasicInfo"] = mysql.dataSet("stock_basic_info")
            appContext["TransactionRecord"] = mysql.dataSet("transaction_record")
            appContext["FinancialReport"] = mysql.dataSet("financial_report")
            appContext["EPSPrediction"] = mysql.dataSet("eps_prediction")
            appContext["TargetPrice"] = mysql.dataSet("target_price")
            appContext["Concept"] = mysql.dataSet("concept")
            appContext["Dynamic"] = mysql.dataSet("dynamic")
            appContext["Static"] = mysql.dataSet("static")
            appContext["Trend"] = mysql.dataSet("trend")

            // the following tables don't exists so far
            appContext["BusinessEfficiency"] = mysql.dataSet("business_efficiency")
            appContext["DebtPayingAbility"] = mysql.dataSet("debt_paying_ability")
            appContext["GrowingAbility"] = mysql.dataSet("growing_ability")
            appContext["Profitability"] = mysql.dataSet("profitability")
        }
    	return appContext
    }

}
