package com.renaissance.etl.models;

public class StockHelper {
    
    public static boolean isBClass(String stockName) {
        if(stockName.indexOf("��")<0 && stockName.indexOf("B")<0) {
            return false;
        }else {
            return true;
        }
    }

    public static boolean isSpetialTreatment(String stockName) {
        if(stockName.indexOf("ST")<0 ) {
            return false;
        }else {
            return true;
        }
    }
}