package com.renaissance.etl.transform
import com.renaissance.etl.models.*
import groovy.util.slurpersupport.GPathResult

/*
 * Transform html tree to data model class
 */
public class StockTransformer{

    //提取股票资料
    def static transform2StockBasicInfo(GPathResult html){

        // try to get stock name from the web page
        def div =html."**".find{it.name()=="DIV" && it.@id=="stockName"}
        def td0 = div?.TABLE?.TR?.TD?.getAt(0)
        def name = td0?.SPAN?.getAt(0)?.text()
        
        // the name is null, this stock code is invalid
        if(name == null){ 
            return null
        }
        def stock = new StockBasicInfo()
        stock.name = name
        stock.code = td0.SPAN[1].text().split(":|：")[1]

        // get the industry
        def table =html."**".find{it.name()=="TABLE" && it.@id=="itprofile"}
        def trList = table."**".findAll{it.name()=="TR"}

        use(DictionaryCategory,StringHelper){
            def cell = trList[3].TD[3].text()
            def tockens = cell.split(",|、")
            stock.industry = tockens.join("")
        }
        return stock
    }

    //提取每日交易
    def static transform2TransactionRecord(GPathResult html){
        def record = new TransactionRecord()
        def stockNameDiv =html."**".find{it.name()=="DIV" && it.@id=="stockName"}
        
        if( stockNameDiv==null ) return null
        
        // TD[1] includes close, change/changePct and time
        def td1 = stockNameDiv?.TABLE?.TR?.TD?.getAt(1)
        record.close = td1?.SPAN[0].text()
        def tockens = StringHelper.extractDigits(td1?.SPAN[1].text())
        record.changePrice = tockens[0]
        record.changePct = tockens[1]
        record.date = td1?.SPAN[2].text().trim()
        
        // TD[2] includes open,high,low,mean,turnover,caption,
        // circulatingValue,aggregateValue,turnoverRate, commissionRate
        def td2 = stockNameDiv?.TABLE?.TR?.TD?.getAt(2)
        def tds = td2.TABLE."**".findAll{it.name()=="TD"}
        for(td in tds){
            tockens = td.text().split(":|：")
            if(tockens.size()>=2){
                use(DictionaryCategory){
                    def numbers = StringHelper.extractDigits(tockens[1])
                    record.setValueByChinese(tockens[0],numbers[0])
                }
                
            }
        }
        return record
    }
    
    //提取财务报告
    def static transform2FinancialReport(GPathResult html){
        def report = new FinancialReport()
        def table =html."**".find{it.name()=="TABLE" && it.@id=="itprofile"}
        def trList = table."**".findAll{it.name()=="TR"}
        
        //take report date
        report.date = trList[0].TD[1].text()
        use(DictionaryCategory){
            trList.each{ tr->
              def name = tr.TD[0].text()
              def value = tr.TD[1].text()
              
              report.setValueByChinese(name, value)
            }
        }
        return report
    }

    //提取盈利能力
    def static transform2Profitability(GPathResult html){
        def profitability = new Profitability()
        def table =html."**".find{it.name()=="TABLE" && it.@id=="itprofile"}
        def trList = table."**".findAll{it.name()=="TR"}
        
        use(DictionaryCategory,StringHelper){
            def ditig = trList[0].TD[1].text().extractDigits()
            profitability.year = ditig[0]
            trList.each{ tr->
              def name = tr.TD[0].text()
              def value = tr.TD[1].text()
              profitability.setValueByChinese(name, value)
            }
        }
        return profitability
    }

    //提取经营效率
    def static transform2BusinessEfficiency(GPathResult html){
        def businessEfficiency = new BusinessEfficiency()
        
        def table =html."**".find{it.name()=="TABLE" && it.@id=="itprofile"}
        def trList = table."**".findAll{it.name()=="TR"}

        use(DictionaryCategory,StringHelper){
            businessEfficiency.year = trList[0].TD[1].text().extractDigits()[0]
            trList.each{ tr->
              def name = tr.TD[0].text()
              def value = tr.TD[1].text()
              businessEfficiency.setValueByChinese(name, value)
            }
        }
        return businessEfficiency
    }
    
    //提取偿债能力
    def static transform2DebtPayingAbility(GPathResult html){
        def debtPayingAbility = new DebtPayingAbility()
        
        def table =html."**".find{it.name()=="TABLE" && it.@id=="itprofile"}
        def trList = table."**".findAll{it.name()=="TR"}

        use(DictionaryCategory,StringHelper){
            debtPayingAbility.year = trList[0].TD[1].text().extractDigits()[0]
            trList.each{ tr->
              def name = tr.TD[0].text()
              def value = tr.TD[1].text()
              debtPayingAbility.setValueByChinese(name, value)
            }
        }
        return debtPayingAbility
    }

    //提取成长能力
    def static transform2GrowingAbility(GPathResult html){
        def growingAbility = new GrowingAbility()
        
        def table =html."**".find{it.name()=="TABLE" && it.@id=="itprofile"}
        def trList = table."**".findAll{it.name()=="TR"}

        use(DictionaryCategory,StringHelper){
            growingAbility.year = trList[0].TD[1].text().extractDigits()[0]
            trList.each{ tr->
              def name = tr.TD[0].text()
              def value = tr.TD[1].text()
              growingAbility.setValueByChinese(name, value)
            }
        }
        return growingAbility
    }
    
    // Get Market Index 
    def static transform2MarketIndex(GPathResult html){
        def marketIndex = new MarketIndex()
        
        // try to get stock name from the web page
        def stockNameDiv =html."**".find{it.name()=="DIV" && it.@id=="stockName"}

        // TD[1] includes close, change/changePct and time
        def td1 = stockNameDiv?.TABLE?.TR?.TD?.getAt(1)
        def temp = td1?.SPAN[0].text()
        marketIndex.close = StringHelper.normalizeNumber(temp) 
   	    marketIndex.date = td1?.SPAN[2].text().trim()

        def td2 = stockNameDiv?.TABLE?.TR?.TD?.getAt(2)
        def tds = td2.TABLE."**".findAll{it.name()=="TD"}
        def tockens
        for(td in tds){
            tockens = td.text().split(":|：")
            if(tockens.size()>=2){
                use(DictionaryCategory){
                    def numbers = StringHelper.extractDigits(tockens[1])
                    marketIndex.setValueByChinese(tockens[0],numbers[0])
                }
            }
        }
        return marketIndex
    }
}
