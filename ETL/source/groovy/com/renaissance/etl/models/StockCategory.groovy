/**
 * 
 */
package com.renaissance.etl.models

public class StockCategory{
    def code
    def name
    def industries = new ArrayList()//证监会行业
    def concepts = new ArrayList()//概念
    def locations = new ArrayList()//地域
    def sinaCategories = new ArrayList()//新浪分类
}
