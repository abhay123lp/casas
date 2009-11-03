/**
 * 
 */
package com.renaissance.etl.models

public class Profitability{
    def year //���
    def fullyDilutedEPS//ÿ������(̯��)
    def adjustedDilutedEPS//������ÿ������
    def netAssetsValuePS//ÿ�ɾ��ʲ�
    def adjustedNetAssetsVPS//������ÿ�ɾ��ʲ�
    def contributedSurplusPS//ÿ���ʱ�������
    def salesRevenuePS//ÿ����������
    def cashFlowPS//ÿ���ֽ�����
    def cashProvidedByOperationsPS//ÿ�ɾ�Ӫ�ֽ���
    def primaryProfitMargin//��Ӫҵ��������(%)
    def nonrecurringGainsAndLossRatio//�Ǿ������������(%)
    def preTaxProfitMargin//Ϣ˰ǰ������(%)
    def allCapitalEarningsRate//���ʲ�������(%)
    def netCapitalEarningsRate//���ʲ�������(%)
    
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
