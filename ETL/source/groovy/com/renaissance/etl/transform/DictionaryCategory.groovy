/**
 * 
 */
package com.renaissance.etl.transform
import java.text.DecimalFormat

public class DictionaryCategory{
    private static dictionary = new HashMap()

    public static getDictionary(){
        if(dictionary.size() ==0){
            
            //TransactionRecord
            dictionary["����"]="open"
            dictionary["������"]="lastClose"
            dictionary["��߼�"]="high"
            dictionary["��ͼ�"]="low"
            dictionary["����"]="average"
            dictionary["�ɽ�"]="turnover"
            dictionary["��ͨ"]="circulatingValue"
            dictionary["��ֵ"]="aggregateValue"
            dictionary["����"]="turnoverPct"
            dictionary["ί��"]="commissionRate"
            
            //GrowingAbility
            dictionary["��Ӫ����������(%)"]="primaryRevenueGrowthRate"
            dictionary["��Ӫ����������(%)"]="primaryProfitGrowthRate"
            dictionary["Ӫҵ����������(%)"]="profitGrowthRate"
            dictionary["˰Ϣǰ����������(%)"]="preTaxProfitGrowthRate"
            dictionary["������������(%)"]="retainedProfitGrowthRate"
            dictionary["ÿ������������(%)"]="ePSGrowthRate"
            dictionary["������ÿ������������(%)"]="adjustedEPSGrowthRate"
            dictionary["ÿ�ɺ���������(%)"]="dividendPSGrowthRate"
            
            //BusinessEfficiency
            dictionary["Ӧ���ʿ���ת��"]="receivablesVelocity"
            dictionary["Ӧ���ʿ���ת����(��)"]="daysOfReceivablesTurnover"
            dictionary["�����ת��"]="stockVelocity"
            dictionary["�����ת����(��)"]="daysOfStockTurnover"
            dictionary["�̶��ʲ���ת��"]="fixedAssetVelocity"
            dictionary["���ʲ���ת��"]="totalAssetsVelocity"
            dictionary["���ʲ���ת��"]="netAssetsVelocity"
            
            //DebtPayingAbility
            dictionary["��������"]="liquidityRatio"
            dictionary["�ٶ�����"]="quickRatio"
            dictionary["��Ϣ����ϵ��"]="interestCoverageRatio"
            dictionary["�ʲ���ծ��(%)"]="assetsLiabilitiesRatio"
            
            //FinanciaReport
            dictionary["����ÿ������"]="primaryEPS"
            dictionary["ϡ��ÿ������"]="fullyDilutedEPS"
            dictionary["ÿ�������Ȩƽ��(�۳��Ǿ����������)"]="weightedAverageEPSWithoutNonrecureGnL"
            dictionary["ÿ�ɾ��ʲ�"]="netAssetsValuePS"
            dictionary["ÿ�ɾ��ʲ�(������)"]="adjustedNetAssetsValuePS"
            dictionary["ÿ�ɾ�Ӫ��������ֽ���������"]="netCashFlowPS"
            dictionary["���ʲ�������̯��(%)"]="dilutedReturnOnNetAssets"
            dictionary["���ʲ������ʼ�Ȩ(%)"]="weightedReturnOnNetAssets"
            dictionary["��Ӫҵ������"]="primaryBusinessIncome"
            dictionary["��Ӫҵ������"]="primaryBusinessProfit"
            dictionary["Ӫҵ����"]="tradeProfit"
            dictionary["Ͷ������"]="yield"
            dictionary["Ӫҵ����֧����"]="netNonrevenueReceipt"
            dictionary["�����ܶ�"]="grossBenefit"
            dictionary["������"]="retainedProfit"
            dictionary["������(�۳��Ǿ����������)"]="retainedProfitWithoutNonrecureGnL"
            dictionary["��Ӫ��������ֽ���������"]="netCashFlowOnTrade"
            dictionary["�ֽ��ֽ�ȼ��ﾻ���Ӷ�"]="netCashIncrease"
            dictionary["�����ʲ�"]="liquidAsset"
            dictionary["������ծ"]="liquidLiability"
            dictionary["���ʲ�"]="totalAssets"
            dictionary["�ɶ�Ȩ�治�������ɶ�Ȩ��"]="shareholderEquityWithoutMinority"
            
            //Profitability
            dictionary["ÿ������(̯��)"]="fullyDilutedEPS"
            dictionary["������ÿ������"]="adjustedDilutedEPS"
            dictionary["������ÿ�ɾ��ʲ�"]="adjustedNetAssetsVPS"
            dictionary["ÿ���ʱ�������"]="contributedSurplusPS"
            dictionary["ÿ����������"]="salesRevenuePS"
            dictionary["ÿ���ֽ�����"]="cashFlowPS"
            dictionary["ÿ�ɾ�Ӫ�ֽ���"]="cashProvidedByOperationsPS"
            dictionary["��Ӫҵ��������(%)"]="primaryProfitMargin"
            dictionary["�Ǿ������������(%)"]="nonrecurringGainsAndLossRatio"
            dictionary["Ϣ˰ǰ������(%)"]="preTaxProfitMargin"
            dictionary["���ʲ�������(%)"]="allCapitalEarningsRate"
            dictionary["���ʲ�������(%)"]="netCapitalEarningsRate"
            
        }
        return dictionary
    }
    public static setValueByChinese(self, name, value){
        getDictionary();
        
        if(dictionary.containsKey(name)){
        	// "--"��ʾ��Ч����
        	if(value==null || value =~ "--") {
        		value = null
        		return
        	}else{
                value = StringHelper.normalizeNumber(value)
        	}
        	
            println self.class.name+": set "+name+"("+dictionary[name]+") = "+value
            self."${dictionary[name]}"=value
        }
    }

}
