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
            dictionary["今开盘"]="open"
            dictionary["昨收盘"]="lastClose"
            dictionary["最高价"]="high"
            dictionary["最低价"]="low"
            dictionary["均价"]="average"
            dictionary["成交"]="turnover"
            dictionary["流通"]="circulatingValue"
            dictionary["市值"]="aggregateValue"
            dictionary["换手"]="turnoverPct"
            dictionary["委比"]="commissionRate"
            
            //GrowingAbility
            dictionary["主营收入增长率(%)"]="primaryRevenueGrowthRate"
            dictionary["主营利润增长率(%)"]="primaryProfitGrowthRate"
            dictionary["营业利润增长率(%)"]="profitGrowthRate"
            dictionary["税息前利润增长率(%)"]="preTaxProfitGrowthRate"
            dictionary["净利润增长率(%)"]="retainedProfitGrowthRate"
            dictionary["每股收益增长率(%)"]="ePSGrowthRate"
            dictionary["调整后每股收益增长率(%)"]="adjustedEPSGrowthRate"
            dictionary["每股红利增长率(%)"]="dividendPSGrowthRate"
            
            //BusinessEfficiency
            dictionary["应收帐款周转率"]="receivablesVelocity"
            dictionary["应收帐款周转天数(天)"]="daysOfReceivablesTurnover"
            dictionary["存货周转率"]="stockVelocity"
            dictionary["存货周转天数(天)"]="daysOfStockTurnover"
            dictionary["固定资产周转率"]="fixedAssetVelocity"
            dictionary["总资产周转率"]="totalAssetsVelocity"
            dictionary["净资产周转率"]="netAssetsVelocity"
            
            //DebtPayingAbility
            dictionary["流动比率"]="liquidityRatio"
            dictionary["速动比率"]="quickRatio"
            dictionary["利息保障系数"]="interestCoverageRatio"
            dictionary["资产负债率(%)"]="assetsLiabilitiesRatio"
            
            //FinanciaReport
            dictionary["基本每股收益"]="primaryEPS"
            dictionary["稀释每股收益"]="fullyDilutedEPS"
            dictionary["每股收益加权平均(扣除非经常性损益后)"]="weightedAverageEPSWithoutNonrecureGnL"
            dictionary["每股净资产"]="netAssetsValuePS"
            dictionary["每股净资产(调整后)"]="adjustedNetAssetsValuePS"
            dictionary["每股经营活动产生的现金流量净额"]="netCashFlowPS"
            dictionary["净资产收益率摊薄(%)"]="dilutedReturnOnNetAssets"
            dictionary["净资产收益率加权(%)"]="weightedReturnOnNetAssets"
            dictionary["主营业务收入"]="primaryBusinessIncome"
            dictionary["主营业务利润"]="primaryBusinessProfit"
            dictionary["营业利润"]="tradeProfit"
            dictionary["投资收益"]="yield"
            dictionary["营业外收支净额"]="netNonrevenueReceipt"
            dictionary["利润总额"]="grossBenefit"
            dictionary["净利润"]="retainedProfit"
            dictionary["净利润(扣除非经常性损益后)"]="retainedProfitWithoutNonrecureGnL"
            dictionary["经营活动产生的现金流量净额"]="netCashFlowOnTrade"
            dictionary["现金及现金等价物净增加额"]="netCashIncrease"
            dictionary["流动资产"]="liquidAsset"
            dictionary["流动负债"]="liquidLiability"
            dictionary["总资产"]="totalAssets"
            dictionary["股东权益不含少数股东权益"]="shareholderEquityWithoutMinority"
            
            //Profitability
            dictionary["每股收益(摊薄)"]="fullyDilutedEPS"
            dictionary["调整后每股收益"]="adjustedDilutedEPS"
            dictionary["调整后每股净资产"]="adjustedNetAssetsVPS"
            dictionary["每股资本公积金"]="contributedSurplusPS"
            dictionary["每股销售收入"]="salesRevenuePS"
            dictionary["每股现金流量"]="cashFlowPS"
            dictionary["每股经营现金流"]="cashProvidedByOperationsPS"
            dictionary["主营业务利润率(%)"]="primaryProfitMargin"
            dictionary["非经常性损益比率(%)"]="nonrecurringGainsAndLossRatio"
            dictionary["息税前利润率(%)"]="preTaxProfitMargin"
            dictionary["总资产收益率(%)"]="allCapitalEarningsRate"
            dictionary["净资产收益率(%)"]="netCapitalEarningsRate"
            
        }
        return dictionary
    }
    public static setValueByChinese(self, name, value){
        getDictionary();
        
        if(dictionary.containsKey(name)){
        	// "--"表示无效数据
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
