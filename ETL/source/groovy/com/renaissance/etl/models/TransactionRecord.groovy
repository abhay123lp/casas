package com.renaissance.etl.models
import com.renaissance.etl.transform.StringHelper
import com.renaissance.etl.DataExpert

public class TransactionRecord{
    def open  //����
    def lastClose //����
    def close //����
    def high  //��߼�
    def low   //��ͼ�
    def average //�м��
    def changePrice //�ǵ�
    def changePct   //�ǵ���
    def turnover  //�ɽ�(��)
    def lastTurnover  //���ճɽ�(��)
    def turnoverPct //������
    def circulatingValue //��ͨ��ֵ
    def aggregateValue   //����ֵ
    def commissionRate //ί��
    def date //����
    
    public Map toMap(stock){
    	
    	def result = new HashMap()
        
        if(StringHelper.parseDate(date) == null){
            return result
        }
        
        changePrice=close.toFloat()-lastClose.toFloat()
        changePct=changePrice/lastClose.toFloat()*100
        lastTurnover = DataExpert.getLastTurnover(stock);
        
        result["code"]=stock.code
        result["date"]=date
        result["lastClose"]=lastClose
        result["open"]=open
        result["close"]=close
        result["high"]=high
        result["low"]=low
        result["average"]=average
        result["changePrice"]=changePrice
        result["changePct"]=changePct
        result["turnover"]=turnover
        result["lastTurnover"]=lastTurnover
        result["turnoverPct"]=turnoverPct
        result["circulatingValue"]=circulatingValue
        result["aggregateValue"]=aggregateValue
        result["commissionRate"]=commissionRate
        return result
    }
}
