
package com.renaissance.etl.transform

import java.text.DecimalFormat
import java.text.SimpleDateFormat

public class StringHelper{
    //数值正则表达式
    static final pattern = /-?(\d+,)*\d+(.\d+)?/

    static final formater1 = new DecimalFormat("###,###,###,###,###.##")
    static final formater2 = new DecimalFormat("###############.##")
    static final dateFormat = new SimpleDateFormat("yyyy-MM-dd")

    //提取字符串中包含的数据
    static public extractDigits(String orig){
        def matcher = orig=~ pattern
        def result = new ArrayList()

        while(matcher.find()){
            result.add(matcher.group())
        }
        return result
    }

    //将 xxx,xxx.xx 格式的数转换成 xxxxxx.xx
    static public String normalizeNumber(String num){
    	def temp = formater1.parse(num)
        return formater2.format(temp)
    }

    //解析日期
    static public Date parseDate(String value){
    	try{
            return dateFormat.parse(value)
    	}catch(java.text.ParseException e){
    		return null
    	}
    }
}
