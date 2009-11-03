/**
 * 
 */
package com.renaissance.etl.models

public class DebtPayingAbility{
    def year //���
    def liquidityRatio//��������
    def quickRatio//�ٶ�����
    def interestCoverageRatio//��Ϣ����ϵ��
    def assetsLiabilitiesRatio//�ʲ���ծ��(%)

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
