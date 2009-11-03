package com.renaissance.etl.models
import com.renaissance.etl.transform.StringHelper
import com.renaissance.etl.DataExpert

public class TransactionRecord{
    def open  //开盘
    def lastClose //收盘
    def close //收盘
    def high  //最高价
    def low   //最低价
    def average //中间价
    def changePrice //涨跌
    def changePct   //涨跌幅
    def turnover  //成交(手)
    def lastTurnover  //昨日成交(手)
    def turnoverPct //换手率
    def circulatingValue //流通市值
    def aggregateValue   //总市值
    def commissionRate //委比
    def date //日期
    
    public Map toMap(stock){
    	
    	def result = new HashMap()
        
        if(StringHelper.parseDate(date) == null){
            return result
        }
        
        changePrice=close.toFloat()-lastClose.toFloat()
        changePct=changePrice/lastClose.toFloat()*100
        lastTurnover = DataExpert.getLastTurnover(stock);
        
        result["code"]=stock.code
        result["date"]=date
        result["lastClose"]=lastClose
        result["open"]=open
        result["close"]=close
        result["high"]=high
        result["low"]=low
        result["average"]=average
        result["changePrice"]=changePrice
        result["changePct"]=changePct
        result["turnover"]=turnover
        result["lastTurnover"]=lastTurnover
        result["turnoverPct"]=turnoverPct
        result["circulatingValue"]=circulatingValue
        result["aggregateValue"]=aggregateValue
        result["commissionRate"]=commissionRate
        return result
    }
}
