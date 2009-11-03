/**
 * 
 */
package com.renaissance.etl.models

public class MarketIndex{

	def code
    def date
    def open
    def close
    def lastClose
    def low
    def high
    def average
    def circulatingValue
    def aggregateValue
    def turnoverPct
    def turnover
    def commissionRate
    
    public Map toMap(){
        def result = new HashMap()
        result["code"]=code
        result["date"]=date
        result["open"]=open
        result["close"]=close
        result["lastClose"]=lastClose
        result["low"]=low
        result["high"]=high
        result["turnover"]=turnover
        result["average"]=average
        return result
    }

}
