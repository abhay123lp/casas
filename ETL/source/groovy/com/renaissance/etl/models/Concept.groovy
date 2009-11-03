/**
 * 
 */
package com.renaissance.etl.models

public class Concept{

    def code
    def name
    def concept
    
    public Map toMap(){
        def result = new HashMap()
        result["code"]=code
        result["name"]=name
        result["concept"]=concept
        return result;
    }
}
