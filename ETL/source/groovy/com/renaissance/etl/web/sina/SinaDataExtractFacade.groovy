/**
 * 
 */
package com.renaissance.etl.web.sina

import org.codehaus.groovy.grails.web.json.*
import groovy.util.slurpersupport.GPathResult

import com.renaissance.etl.AppContextManager
import com.renaissance.etl.models.*
import com.renaissance.etl.util.*;
import com.renaissance.etl.web.HTMLUtil
import com.renaissance.etl.transform.StringHelper
import java.util.concurrent.*;

/**
 * SINA网站有关于股票的行业，地域，概念，新浪分类信息。
 * 另外还有各个股票的价值估值
 * 所有数据下载后记录到数据表Stock_Basic_Info和eps_prediction
 */
public class SinaDataExtractFacade{
	
    def context = AppContextManager.getAppContext()
    def stockBasicInfoTable = context.StockBasicInfo //保存行业地域
    def epsPredictionTable = context.EPSPrediction   //保存EPS预测
    def targetPriceTable = context.TargetPrice       //保存价值估值
    def conceptTable = context.Concept               //保存概念分类

    def factory = new SinaPageFactory();

//	factory.industries
//	factory.locations
//	factory.concepts
//	factory.sinaCategories

    public downloadAllPrediction(){
        for(stock in stockBasicInfoTable.rows()){
        	println stock.code+":"+stock.name
        	downloadPrediction(stock);
    	}
    }
    
    // Update table: EPS_Prediction and Target_Price
    public downloadPrediction(stock){

    	GPathResult html = factory.getTargetPriceURL(stock.code)
        if(html==null) return
        
        //找到"评级及盈利预测"单元td
        GPathResult td = html."**".find{
                if(it.name()=="TD" && it.@width=="540"){
                    if(it.text().indexOf("评级及盈利预测")>=0){
                        return true
                    }
                }
                return false
            }
        if(td==null) return 
        
        EPSPrediction prediction = 
        	new EPSPrediction(code:stock.code,name:stock.name)
        TargetPrice targetPrice = 
        	new TargetPrice(code:stock.code,name:stock.name, date:new Date().getDateString())
        
        //println "td = "+td
        String text = td.text()
        //语句分解
        String[] tockens=text.split("：|，|。|（|）")
        //tockens.each{println it}
        tockens.each{sentence->
            switch(sentence){
                // 每股收益预测
                case{it.indexOf("每股收益")>=0}:
                    List nums = StringHelper.extractDigits(sentence)
                    prediction.year=nums[0].toInteger()
                    prediction.eps=nums[1].toDouble()
                    //println nums[0]+" "+nums[1]
                    updateEPSPrediction(prediction)
                    break;
                // 目标股价
                case{it.indexOf("目标股价")>=0}:
                    List nums = StringHelper.extractDigits(sentence)
                    targetPrice.targetPrice = nums.get(0)
                    updateTargetPrice(targetPrice)
                    break;
            }
        }
    }

    private updateTargetPrice(TargetPrice newItem){
    	println "目标价:"+newItem.targetPrice
        boolean doesExist = false;
        for(it in targetPriceTable.rows()){
            if(it.code == newItem.code){
            	doesExist = true
                break;
            }
        }
    	
    	// if the stock code exists in the table Target_Price, then update
    	if(doesExist){
            String sql = 
                """update target_price 
                   set targetPrice=$newItem.targetPrice, date='$newItem.date'
                   where code='${newItem.code}'"""
            targetPriceTable.executeUpdate(sql);
    	}else{
            // else add this item
            def binding = newItem.toMap()
    		targetPriceTable.add(binding)
    	}
    }

    private updateEPSPrediction(EPSPrediction newItem){
    	println "EPS预测:"+newItem.year+"-"+newItem.eps
    	
   		// 先检查是否已经存在
   		boolean doesExist = false;
   	    for(it in epsPredictionTable.rows()){
   	    	if(it.code == newItem.code && it.year == newItem.year){
   	    		doesExist = true
   	    		break;
   	    	}
   	    }
   	    if(doesExist){
            // 向数据库中update EPS预测数据
            String sql = 
                """update EPS_Prediction 
                   set eps=$newItem.eps
                   where code='${newItem.code}' and year=$newItem.year"""
            targetPriceTable.executeUpdate(sql);
   	    }else{
            // 向数据库中insert EPS预测数据
   	    	def binding = newItem.toMap()
  	    	epsPredictionTable.add(binding)
   	    }
    }


    // Update table: stock_basic_info
    public downloadIndustry(){
    	// 遍历所有行业，下载每个行业所包含的股票
        factory.industries.each{industry, nodeParam->
            JSONArray stockJSONArray = factory.getStockListByType(nodeParam)
            
            stockJSONArray.each{it ->
                // skip all B-class stocks
                if(!StockHelper.isBClass(it.name)){
                	println "add "+it.name+" to "+industry
                    StockBasicInfo stock = 
                    	new StockBasicInfo(code:it.code,name:it.name,industry:industry)
                    String sql = 
                        """update stock_basic_info set industry='$industry'
                           where code='${it.code}'"""
                    println "execute:"+sql
                    stockBasicInfoTable.executeUpdate(sql);
                }
            }
        }
	}

    // Update table: stock_basic_info
    public downloadLocation(){
        // 遍历所有地域，下载每个行业所包含的股票
        factory.regions.each{region, nodeParam->
            JSONArray stockJSONArray = factory.getStockListByType(nodeParam)
            
            stockJSONArray.each{it ->
                // skip all B-class stocks
                if(!StockHelper.isBClass(it.name)){
                    //println "add "+it.name+" to "+location
                    String sql = 
                        """update stock_basic_info set region='$region'
                           where code='${it.code}'"""
                    println "execute:"+sql
                    stockBasicInfoTable.executeUpdate(sql);
                }
            }
        }
    }

	private insertConceptTable(Concept newItem){
        boolean doesExist = false;
        for(it in conceptTable.rows()){
            if(it.code == newItem.code && it.concept == newItem.concept){
                doesExist = true
                break;
            }
        }
        
        // if the stock code doesn't exist in the table Target_Price, then add it
        if(!doesExist){
            def binding = newItem.toMap()
            conceptTable.add(binding)
        }
	}
    // Update table: concept
    public downloadConcepts(){
        // 遍历所有概念，下载每个行业所包含的股票
        factory.concepts.each{concept, nodeParam->
            JSONArray stockJSONArray = factory.getStockListByType(nodeParam)
            
            stockJSONArray.each{it ->
                // skip all B-class stocks
                if(!StockHelper.isBClass(it.name)){
                    println "add "+it.name+" to "+ concept
                    Concept item = 
                    	new Concept(code:it.code,name:it.name, concept:concept)
                    insertConceptTable(item)
                }
            }
        }
    }

    public downloadSinaCategory(){
        // 遍历所有概念，下载每个行业所包含的股票
        factory.sinaCategories.each{sinaCategory, nodeParam->
            JSONArray stockJSONArray = factory.getStockListByType(nodeParam)
            
            stockJSONArray.each{it ->
                // skip all B-class stocks
                if(!StockHelper.isBClass(it.name)){
                    println "add "+it.name+" to "+ sinaCategory
                    Concept item = 
                        new Concept(code:it.code,name:it.name, concept:sinaCategory)
                    insertConceptTable(item)
                }
            }
        }
    }

    public downloadAClass(){
        JSONArray stockJSONArray = factory.getStockListByType(factory.hushenAClass)
            
        stockJSONArray.each{it ->
            StockCategory stockCategory = getStockCategory(it.code,it.name)
        }
    }
    
    public downloadPriceHistory(stock, startDate, endDate){
        GPathResult html = factory.getPriceHistoryURL(stock, startDate, endDate);
        
        if(html==null) return;
        
        //找到"评级及盈利预测"单元td
        GPathResult table = html."**".find{
            return (it.name()=="TABLE" && it.@id=="datalist")
        };
        
        if (table==null) return;
        
        GPathResult trs = table?.TBODY.TR;
        for(tr in trs){
            println "price = "+tr.TD[0];
            println "turnover = "+tr.TD[1];
            println ""
        }
    }
    
    public downloadAllTransactionParticulars(date){
    	println new Date().getTimeString()
    	println "Begin download Transaction Particulars...."
    	ExecutorService pool = Executors.newFixedThreadPool(5);
        
    	for(stock in stockBasicInfoTable.rows()){
            def localStock = new StockBasicInfo(code:stock.code,name:stock.name,market:stock.market);
            def localDate = date.clone();
    	    def run = {
    	        downloadTransactionParticulars(localStock,localDate);
    	    }
            pool.submit(run as Runnable);
        }
    	pool.shutdown();
    	while(!pool.isTerminated()){
    		println "the thread pool is still alive";
    		pool.awaitTermination(30,TimeUnit.SECONDS);
    	}

        println "Done"
        println new Date().getTimeString()
    }
    
    public downloadTransactionParticulars(stock, date){
    	String text = factory.getTransactionParticulars(stock, date);
    	if(text==null) return;
    	File base = new File(System.getProperty("data.dir"));
    	File dir = new File(base,date.format("yyyy-MM-dd"));
    	if(!dir.exists()){
    	    if(!dir.mkdir()){
    	        println "failed to create the directory:"+dir.getAbsolutePath();
    	        System.exit(0);
    	    }
    	}
    	byte[] buffer = text.getBytes();
    	def destFile = new File(dir, stock.code+".zip");
    	def entry = stock.code+".txt";
    	ZipHelper.writeBuffer2ZipFile(buffer, entry, destFile);
    	println "write to "+dest.getAbsolutePath();
    }
}
