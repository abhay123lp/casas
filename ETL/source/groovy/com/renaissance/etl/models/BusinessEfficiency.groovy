/**
 * 
 */
package com.renaissance.etl.models

public class BusinessEfficiency{
    def year //���
    def receivablesVelocity//Ӧ���ʿ���ת��
    def daysOfReceivablesTurnover//Ӧ���ʿ���ת����
    def stockVelocity//�����ת��
    def daysOfStockTurnover//�����ת����
    def fixedAssetVelocity//�̶��ʲ���ת��
    def totalAssetsVelocity//���ʲ���ת��
    def netAssetsVelocity//���ʲ���ת��
    
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
