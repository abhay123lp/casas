/**
 * 
 */
package com.renaissance.etl.models


public class PriceHistory{
    def String code;
    def Date start;
    def Date end;
    def List<DataItem> history = new ArrayList<DataItem>(100);
}

public class DataItem{
    def price;
    def turnover;
}
