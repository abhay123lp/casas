package com.renaissance.etl.transform
import com.renaissance.etl.web.HTMLUtil
import com.renaissance.etl.models.*
import com.renaissance.etl.transform.StockTransformer

    public testTransform2TransactionRecord(){
        def url ='http://baidu.hexun.com/stock/fa.php?code=601166.sh'
        def html=HTMLUtil.parse(url)
        println "processing "<<url
        def record = StockTransformer.transform2TransactionRecord(html)
        println ""
        println "return transaction record"
        println ""
        record.properties.each{key, value->
            println key + " = " + value
        }
    }
    
public testTransform2FinancialReport(){
    def url ='http://baidu.hexun.com/stock/fa.php?code=601166.sh'
    def html=HTMLUtil.parse(url)
    println "processing "<<url
    def report = StockTransformer.transform2FinancialReport(html)
    println ""
    println "return financial report"
    println ""
    report.properties.each{key, value->
        println key + " = " + value
    }
}

public testTransform2Profitability(){
    def url ='http://baidu.hexun.com/stock/fa.php?code=601166.sh&t=1'
    def html=HTMLUtil.parse(url)
    println "processing "<<url
    def profitability = StockTransformer.transform2Profitability(html)
    
    println ""
    println "return profitability"
    println ""
    profitability.properties.each{key, value->
        println key + " = " + value
    }
}

public testTransform2BusinessEfficiency(){
    def url ='http://baidu.hexun.com/stock/fa.php?code=601166.sh&t=2'
    def html=HTMLUtil.parse(url)
    println "processing "<<url
    def businessEfficiency = StockTransformer.transform2BusinessEfficiency(html)
    
    println ""
    println "return Business Efficiency"
    println ""
    businessEfficiency.properties.each{key, value->
        println key + " = " + value
    }
}


public testTransform2DebtPayingAbility(){
    def url ='http://baidu.hexun.com/stock/fa.php?code=601166.sh&t=3'
    def html=HTMLUtil.parse(url)
    println "processing "<<url
    def debtPayingAbility = StockTransformer.transform2DebtPayingAbility(html)
    
    println ""
    println "return Debt Paying Ability"
    println ""
    debtPayingAbility.properties.each{key, value->
        println key + " = " + value
    }
}

public testTransform2GrowingAbility(){
    def url ='http://baidu.hexun.com/stock/fa.php?code=601166.sh&t=4'
    def html=HTMLUtil.parse(url)
    println "processing "<<url
    def growingAbility = StockTransformer.transform2GrowingAbility(html)
    
    println ""
    println "return Growing Ability"
    println ""
    growingAbility.properties.each{key, value->
        println key + " = " + value
    }
}

public testTransform2BasicInfo(){
    def url ='http://baidu.hexun.com/stock/crop.php?code=601166.sh'
    def html=HTMLUtil.parse(url)
    println "processing "<<url
    def basicInfo = StockTransformer.transform2StockBasicInfo(html)
    
    println ""
    println "return Basic Info"
    println ""
    basicInfo.properties.each{key, value->
        println key + " = " + value
    }
}

testTransform2BasicInfo()
//testTransform2TransactionRecord()
//testTransform2FinancialReport()
//testTransform2Profitability()
//testTransform2BusinessEfficiency()
//testTransform2DebtPayingAbility()
//testTransform2GrowingAbility()





