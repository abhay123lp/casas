
package com.renaissance.etl.models

public class FinancialReport{
    
    def primaryEPS  // ����ÿ������
    def fullyDilutedEPS  //ϡ��ÿ������
    def weightedAverageEPSWithoutNonrecureGnL //ÿ�������Ȩƽ��(�۳��Ǿ����������)
    def netAssetsValuePS //ÿ�ɾ��ʲ�
    def adjustedNetAssetsValuePS //ÿ�ɾ��ʲ�(������)
    def netCashFlowPS    //ÿ�ɾ�Ӫ��������ֽ���������
    def dilutedReturnOnNetAssets  //���ʲ�������̯��(%)
    def weightedReturnOnNetAssets //���ʲ������ʼ�Ȩ(%)
    def primaryBusinessIncome//��Ӫҵ������
    def primaryBusinessProfit//��Ӫҵ������
    def tradeProfit//Ӫҵ����
    def yield//Ͷ������
    def netNonrevenueReceipt//Ӫҵ����֧����
    def grossBenefit//�����ܶ�
    def retainedProfit//������
    def retainedProfitWithoutNonrecureGnL//������(�۳��Ǿ����������)
    def netCashFlowOnTrade//��Ӫ��������ֽ���������
    def netCashIncrease //�ֽ��ֽ�ȼ��ﾻ���Ӷ�
    def liquidAsset//�����ʲ�
    def liquidLiability//������ծ
    def totalAssets//���ʲ�
    def shareholderEquityWithoutMinority//�ɶ�Ȩ�治�������ɶ�Ȩ��
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
