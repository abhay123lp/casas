/**
 * 
 */
package com.renaissance.etl.models

public class EPSPrediction{
    def String code
    def String name
    def int year
    def double eps

    public Map toMap(){
        def result = new HashMap()
        result["code"]=code
        result["name"]=name
        result["year"]=year
        result["eps"]=eps
        return result
    }
}
