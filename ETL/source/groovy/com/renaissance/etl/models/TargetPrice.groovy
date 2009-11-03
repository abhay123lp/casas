/**
 * 
 */
package com.renaissance.etl.models


public class TargetPrice{
    def code
    def name
    def date
    def targetPrice
    
    public Map toMap(){
        def result = new HashMap()
        result["code"]=code
        result["name"]=name
        result["date"]=date
        result["targetPrice"]=targetPrice
        return result;
    }

}
