/**
 * 
 */
package com.renaissance.etl.models

public class Profitability{
    def year //年度
    def fullyDilutedEPS//每股收益(摊薄)
    def adjustedDilutedEPS//调整后每股收益
    def netAssetsValuePS//每股净资产
    def adjustedNetAssetsVPS//调整后每股净资产
    def contributedSurplusPS//每股资本公积金
    def salesRevenuePS//每股销售收入
    def cashFlowPS//每股现金流量
    def cashProvidedByOperationsPS//每股经营现金流
    def primaryProfitMargin//主营业务利润率(%)
    def nonrecurringGainsAndLossRatio//非经常性损益比率(%)
    def preTaxProfitMargin//息税前利润率(%)
    def allCapitalEarningsRate//总资产收益率(%)
    def netCapitalEarningsRate//净资产收益率(%)
    
    def toMap(stock){
    	def result = new HashMap()
    	result["code"]=stock.code
    	result["fiscal_year"]=year
       	result["fullyDilutedEPS"]=fullyDilutedEPS
       	result["adjustedDilutedEPS"]=adjustedDilutedEPS
       	result["netAssetsValuePS"]=netAssetsValuePS
       	result["adjustedNetAssetsVPS"]=adjustedNetAssetsVPS
       	result["contributedSurplusPS"]=contributedSurplusPS
       	result["salesRevenuePS"]=salesRevenuePS
       	result["cashFlowPS"]=cashFlowPS
       	result["cashProvidedByOperationsPS"]=cashProvidedByOperationsPS
       	result["primaryProfitMargin"]=primaryProfitMargin
       	result["nonrecurringGainsAndLossRatio"]=nonrecurringGainsAndLossRatio
       	result["preTaxProfitMargin"]=preTaxProfitMargin
       	result["allCapitalEarningsRate"]=allCapitalEarningsRate
       	result["netCapitalEarningsRate"]=netCapitalEarningsRate
    	return result
    }

}
