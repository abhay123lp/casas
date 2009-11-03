
package com.renaissance.etl.models

public class FinancialReport{
    
    def primaryEPS  // 基本每股收益
    def fullyDilutedEPS  //稀释每股收益
    def weightedAverageEPSWithoutNonrecureGnL //每股收益加权平均(扣除非经常性损益后)
    def netAssetsValuePS //每股净资产
    def adjustedNetAssetsValuePS //每股净资产(调整后)
    def netCashFlowPS    //每股经营活动产生的现金流量净额
    def dilutedReturnOnNetAssets  //净资产收益率摊薄(%)
    def weightedReturnOnNetAssets //净资产收益率加权(%)
    def primaryBusinessIncome//主营业务收入
    def primaryBusinessProfit//主营业务利润
    def tradeProfit//营业利润
    def yield//投资收益
    def netNonrevenueReceipt//营业外收支净额
    def grossBenefit//利润总额
    def retainedProfit//净利润
    def retainedProfitWithoutNonrecureGnL//净利润(扣除非经常性损益后)
    def netCashFlowOnTrade//经营活动产生的现金流量净额
    def netCashIncrease //现金及现金等价物净增加额
    def liquidAsset//流动资产
    def liquidLiability//流动负债
    def totalAssets//总资产
    def shareholderEquityWithoutMinority//股东权益不含少数股东权益
    def date

    def toMap(stock){
    	def result = new HashMap()
    	result["code"]=stock.code
    	result["fiscal_season"]=date
       	result["primaryEPS"]=primaryEPS
       	result["fullyDilutedEPS"]=fullyDilutedEPS
       	result["weightedAverageEPSWithoutNonrecureGnL"]=weightedAverageEPSWithoutNonrecureGnL
       	result["netAssetsValuePS"]=netAssetsValuePS
       	result["adjustedNetAssetsValuePS"]=adjustedNetAssetsValuePS
       	result["netCashFlowPS"]=netCashFlowPS
       	result["dilutedReturnOnNetAssets"]=dilutedReturnOnNetAssets
       	result["weightedReturnOnNetAssets"]=weightedReturnOnNetAssets
       	result["primaryBusinessIncome"]=primaryBusinessIncome
       	result["primaryBusinessProfit"]=primaryBusinessProfit
       	result["tradeProfit"]=tradeProfit
       	result["yield"]=yield
       	result["netNonrevenueReceipt"]=netNonrevenueReceipt
       	result["grossBenefit"]=grossBenefit
       	result["retainedProfit"]=retainedProfit
       	result["retainedProfitWithoutNonrecureGnL"]=retainedProfitWithoutNonrecureGnL
       	result["netCashFlowOnTrade"]=netCashFlowOnTrade
       	result["netCashIncrease"]=netCashIncrease
       	result["liquidAsset"]=liquidAsset
       	result["liquidLiability"]=liquidLiability
       	result["totalAssets"]=totalAssets
       	result["shareholderEquityWithoutMinority"]=shareholderEquityWithoutMinority
    	return result
    }
}
