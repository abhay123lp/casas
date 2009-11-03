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
        //println key+" has "+0+" ���й�˾"
    }else{
        numList = StringHelper.extractDigits(input)
        //println key+" has "+numList.get(0)+" ���й�˾"
        total = total+numList.get(0).toInteger() 
    }
}

println "��ҵ����: "+total+" ���й�˾"

///////////////////////////////////////////////////////////////////
total=0
factory.locations.each{key,value->
    String url = factory.getHQNodeStockCountBaseURL+value
    String input = url.toURL().text
    //println input
    if(input==null||input=="null"){
        //println key+" has "+0+" ���й�˾"
    }else{
        numList = StringHelper.extractDigits(input)
        //println key+" has "+numList.get(0)+" ���й�˾"
        total = total+numList.get(0).toInteger()
    }
}
println "�������: "+total+" ���й�˾"

///////////////////////////////////////////////////////////////////
total=0
factory.concepts.each{key,value->
    String url = factory.getHQNodeStockCountBaseURL+value
    String input = url.toURL().text
    //println input
    if(input==null||input=="null"){
        //println key+" has "+0+" ���й�˾"
    }else{
        numList = StringHelper.extractDigits(input)
        //println key+" has "+numList.get(0)+" ���й�˾"
        total = total+numList.get(0).toInteger()
    }
}
println "�������: "+total+" ���й�˾"


///////////////////////////////////////////////////////////////////
total=0
factory.sinaCategories.each{key,value->
    String url = factory.getHQNodeStockCountBaseURL+value
    String input = url.toURL().text
    //println input
    if(input==null||input=="null"){
    }else{
        numList = StringHelper.extractDigits(input)
        //println key+" has "+numList.get(0)+" ���й�˾"
        total = total+numList.get(0).toInteger()
    }
}
println "���˷���: "+total+" ���й�˾"
