package com.renaissance.etl.web.baidu
import com.renaissance.etl.web.*
import com.renaissance.etl.models.*
import groovy.util.slurpersupport.GPathResult

/**
 * For each stock, baidu.com contains the following pages
 * 公司资料
 * 每日交易
 * 财务指标
 * 盈利能力
 * 经营效率
 * 偿债能力
 * 成长能力
 */
public class BaiduPageFactory{
    
    def Map shModel2UrlMap =  new HashMap()
    def Map szModel2UrlMap =  new HashMap()
    
    public BaiduPageFactory(){
        // 上海
        shModel2UrlMap["StockBasicInfo"]    ={stock-> return "http://baidu.hexun.com/stock/crop.php?code=${stock.code}.sh" }
        shModel2UrlMap["TransactionRecord"] ={stock-> return "http://baidu.hexun.com/stock/fa.php?code=${stock.code}.sh&t=5" }
        shModel2UrlMap["FinancialReport"]   ={stock-> return "http://baidu.hexun.com/stock/fa.php?code=${stock.code}.sh&t=5" }
        shModel2UrlMap["Profitability"]     ={stock-> return "http://baidu.hexun.com/stock/fa.php?code=${stock.code}.sh&t=1" }
        shModel2UrlMap["BusinessEfficiency"]={stock-> return "http://baidu.hexun.com/stock/fa.php?code=${stock.code}.sh&t=2" }
        shModel2UrlMap["DebtPayingAbility"] ={stock-> return "http://baidu.hexun.com/stock/fa.php?code=${stock.code}.sh&t=3" }
        shModel2UrlMap["GrowingAbility"]    ={stock-> return "http://baidu.hexun.com/stock/fa.php?code=${stock.code}.sh&t=4" }
        
        //深圳
        szModel2UrlMap["StockBasicInfo"]    ={stock-> return "http://baidu.hexun.com/stock/crop.php?code=${stock.code}.sz" }
        szModel2UrlMap["TransactionRecord"] ={stock-> return "http://baidu.hexun.com/stock/fa.php?code=${stock.code}.sz&t=5" }
        szModel2UrlMap["FinancialReport"]   ={stock-> return "http://baidu.hexun.com/stock/fa.php?code=${stock.code}.sz&t=5" }
        szModel2UrlMap["Profitability"]     ={stock-> return "http://baidu.hexun.com/stock/fa.php?code=${stock.code}.sz&t=1" }
        szModel2UrlMap["BusinessEfficiency"]={stock-> return "http://baidu.hexun.com/stock/fa.php?code=${stock.code}.sz&t=2" }
        szModel2UrlMap["DebtPayingAbility"] ={stock-> return "http://baidu.hexun.com/stock/fa.php?code=${stock.code}.sz&t=3" }
        szModel2UrlMap["GrowingAbility"]    ={stock-> return "http://baidu.hexun.com/stock/fa.php?code=${stock.code}.sz&t=4" }
    }
    
    //
    public GPathResult getBaiduWebPage(stock, targetVar){
        def className = targetVar.class.name.tokenize(".")[-1]
        def url = null
        
        switch(stock.market){
            case StockBasicInfo.SH:
                url = shModel2UrlMap[className](stock)
                break
            case StockBasicInfo.SZ:
                url = szModel2UrlMap[className](stock)
                break
            default:
                return null
        }
        
        return HTMLUtil.parse(url)
    }

    public GPathResult getSHIndexWebPage(){
    	return HTMLUtil.parse("http://baidu.hexun.com/stock/crop.php?code=000001.sh")
    }
    
}