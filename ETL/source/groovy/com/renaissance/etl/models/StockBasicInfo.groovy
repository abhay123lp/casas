/**
 * 
 */
package com.renaissance.etl.models

public class StockBasicInfo{
    //Shen Zhen
    public static final String SH="SH"
    //Shang Hai
    public static final String SZ="SZ"
    //Hang Kang
    public static final String HK="HK"
    //Open Fund
    public static final String OF="OF"

    def code
    def name
    def market
    def String[] industry //À˘ Ù––“µ
    def region
    public StockBasicInfo(){
    	
    }
    
    public StockBasicInfo(index,name,type,industry){
        this.code=code
        this.name=name
        this.market=market
        this.industry=industry
    }
    public Map toMap(){
    	def result = new HashMap()
    	result["code"]=code
    	result["name"]=name
    	result["market"]=market
    	if(region !=null){
    		result["region"]=region
    	}
    	result["industry"]=industry
    	return result
    }

}

