/**
 * 
 */
package com.renaissance.etl.models

public class StockCategory{
    def code
    def name
    def industries = new ArrayList()//֤�����ҵ
    def concepts = new ArrayList()//����
    def locations = new ArrayList()//����
    def sinaCategories = new ArrayList()//���˷���
}
