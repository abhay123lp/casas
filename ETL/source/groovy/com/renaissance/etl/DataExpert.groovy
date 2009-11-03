/**
 * Provide basic knowledge based on database 
 */
package com.renaissance.etl

import groovy.sql.Sql
import groovy.sql.DataSet
import com.renaissance.etl.*;


public class DataExpert{
    static HashMap context = AppContextManager.getAppContext();
    static DataSet marketIndices = context.MarketIndex;
    
    static TreeSet<String> tradingDates = new TreeSet<String>();
    
    private static init(){
        if(tradingDates.size()==0){
            marketIndices.each{
                tradingDates.add(it.date)
                //tradingDates = tradingDates.descendingSet()
            }
        }
    }
    public static java.sql.Date getLatestDate(){
        init();
        return tradingDates.last();
    }
    
    public static java.sql.Date nextDate(java.sql.Date date){
        init();
        return tradingDates.higher(date);
    }

    public static java.sql.Date previousDate(java.sql.Date date){
        init();
        return tradingDates.lower(date);
    }

    public static deleteStockBasicInfo(){
        String sql = "delete from stock_basic_info";
        context.StockBasicInfo.execute(sql);
    }
    public static deleteFinancialReport(){
        String sql = "delete from financial_report";
        context.FinancialReport.execute(sql);
    }
    public static deleteConcept(){
        String sql = "delete from concept";
        context.Concept.execute(sql);
    }
    public static getLastTurnover(stock){
        
        DataSet dataSet = context.TransactionRecord;
        String sql = "select * from transaction_record where code='${stock.code}' group by date desc"
        def latestTransaction = dataSet.firstRow(sql);
        return latestTransaction?.turnover;
    }
    
    public static updateAvgTable(){
    	Sql sql = context.mysql;
    	String today = new Date().format('yyyy-MM-dd');
    	String command = "call update_avg30('$today')";
    	sql.call(command);
        command = "call update_avg10('$today')";
        sql.call(command);
    }
}
