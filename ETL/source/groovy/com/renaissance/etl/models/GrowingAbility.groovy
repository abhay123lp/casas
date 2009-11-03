/**
 * 
 */
package com.renaissance.etl.models

public class GrowingAbility{
    def year
    def primaryRevenueGrowthRate//主营收入增长率(%)
    def primaryProfitGrowthRate//主营利润增长率(%)
    def profitGrowthRate//营业利润增长率(%)
    def preTaxProfitGrowthRate//税息前利润增长率(%)
    def retainedProfitGrowthRate//净利润增长率(%)
    def ePSGrowthRate//每股收益增长率(%)
    def adjustedEPSGrowthRate//调整后每股收益增长率(%)
    def dividendPSGrowthRate//每股红利增长率(%)

    def toMap(stock){
    	def result = new HashMap()
    	result["code"]=stock.code
    	result["fiscal_year"]=year
       	result["primaryRevenueGrowthRate"]=primaryRevenueGrowthRate
       	result["primaryProfitGrowthRate"]=primaryProfitGrowthRate
       	result["profitGrowthRate"]=profitGrowthRate
       	result["preTaxProfitGrowthRate"]=preTaxProfitGrowthRate
       	result["retainedProfitGrowthRate"]=retainedProfitGrowthRate
       	result["ePSGrowthRate"]=ePSGrowthRate
       	result["adjustedEPSGrowthRate"]=adjustedEPSGrowthRate
       	result["dividendPSGrowthRate"]=dividendPSGrowthRate
    	return result
    }

}
