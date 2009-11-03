/**
 * 
 */
package com.renaissance.etl.web.sina

import com.renaissance.etl.transform.*

SinaPageFactory factory = new SinaPageFactory();
def int total = 0

factory.industries.each{key,value->
    String url = factory.getHQNodeStockCountBaseURL+value
    String input = url.toURL().text
    if(input==null||input=="null"){
        //println key+" has "+0+" 上市公司"
    }else{
        numList = StringHelper.extractDigits(input)
        //println key+" has "+numList.get(0)+" 上市公司"
        total = total+numList.get(0).toInteger() 
    }
}

println "行业分类: "+total+" 上市公司"

///////////////////////////////////////////////////////////////////
total=0
factory.locations.each{key,value->
    String url = factory.getHQNodeStockCountBaseURL+value
    String input = url.toURL().text
    //println input
    if(input==null||input=="null"){
        //println key+" has "+0+" 上市公司"
    }else{
        numList = StringHelper.extractDigits(input)
        //println key+" has "+numList.get(0)+" 上市公司"
        total = total+numList.get(0).toInteger()
    }
}
println "地域分类: "+total+" 上市公司"

///////////////////////////////////////////////////////////////////
total=0
factory.concepts.each{key,value->
    String url = factory.getHQNodeStockCountBaseURL+value
    String input = url.toURL().text
    //println input
    if(input==null||input=="null"){
        //println key+" has "+0+" 上市公司"
    }else{
        numList = StringHelper.extractDigits(input)
        //println key+" has "+numList.get(0)+" 上市公司"
        total = total+numList.get(0).toInteger()
    }
}
println "概念分类: "+total+" 上市公司"


///////////////////////////////////////////////////////////////////
total=0
factory.sinaCategories.each{key,value->
    String url = factory.getHQNodeStockCountBaseURL+value
    String input = url.toURL().text
    //println input
    if(input==null||input=="null"){
    }else{
        numList = StringHelper.extractDigits(input)
        //println key+" has "+numList.get(0)+" 上市公司"
        total = total+numList.get(0).toInteger()
    }
}
println "新浪分类: "+total+" 上市公司"
