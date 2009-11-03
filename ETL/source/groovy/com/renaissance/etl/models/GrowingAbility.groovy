/**
 * 
 */
package com.renaissance.etl.models

public class GrowingAbility{
    def year
    def primaryRevenueGrowthRate//��Ӫ����������(%)
    def primaryProfitGrowthRate//��Ӫ����������(%)
    def profitGrowthRate//Ӫҵ����������(%)
    def preTaxProfitGrowthRate//˰Ϣǰ����������(%)
    def retainedProfitGrowthRate//������������(%)
    def ePSGrowthRate//ÿ������������(%)
    def adjustedEPSGrowthRate//������ÿ������������(%)
    def dividendPSGrowthRate//ÿ�ɺ���������(%)

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
