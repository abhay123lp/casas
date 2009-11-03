/**
 * 
 */
package com.renaissance.etl.models

public class DebtPayingAbility{
    def year //年度
    def liquidityRatio//流动比率
    def quickRatio//速动比率
    def interestCoverageRatio//利息保障系数
    def assetsLiabilitiesRatio//资产负债率(%)

    def toMap(stock){
    	def result = new HashMap()
    	result["code"]=stock.code
    	result["fiscal_year"]=year
      	result["liquidityRatio"]=liquidityRatio
       	result["quickRatio"]=quickRatio
       	result["interestCoverageRatio"]=interestCoverageRatio
      	result["assetsLiabilitiesRatio"]=assetsLiabilitiesRatio
    	return result
    }
}
