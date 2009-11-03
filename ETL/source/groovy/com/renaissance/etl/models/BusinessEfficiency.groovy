/**
 * 
 */
package com.renaissance.etl.models

public class BusinessEfficiency{
    def year //年度
    def receivablesVelocity//应收帐款周转率
    def daysOfReceivablesTurnover//应收帐款周转天数
    def stockVelocity//存货周转率
    def daysOfStockTurnover//存货周转天数
    def fixedAssetVelocity//固定资产周转率
    def totalAssetsVelocity//总资产周转率
    def netAssetsVelocity//净资产周转率
    
    def toMap(stock){
    	def result = new HashMap()
    	result["code"]=stock.code
    	result["fiscal_year"]=year
      	result["receivablesVelocity"]=receivablesVelocity
       	result["daysOfReceivablesTurnover"]=daysOfReceivablesTurnover
       	result["fixedAssetVelocity"]=fixedAssetVelocity
      	result["totalAssetsVelocity"]=totalAssetsVelocity
       	result["netAssetsVelocity"]=netAssetsVelocity
    	return result
    }
}
