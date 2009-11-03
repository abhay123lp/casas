/*
 * Senario��
 * ��baidu���ش���Ϊ600003�Ĺ�Ʊ�������ݣ������浽�ļ�"config/stocks_test.txt"
 */
package com.renaissance.etl

import com.renaissance.etl.models.*
import com.renaissance.etl.web.baidu.*

import static com.renaissance.etl.transform.StockTransformer.*

def pageFactory = new BaiduPageFactory()

def output = new File("config/stocks_test.txt")
output.delete()
output.createNewFile()
def writer = output.newWriter()

def rawStock= new StockBasicInfo(index:"600003",type:StockBasicInfo.SH)
def html = pageFactory.getBaiduWebPage(rawStock, rawStock)
stock = transform2StockBasicInfo(html)
writer.writeLine(stock.index+","+stock.name+","+stock.type+","+stock.industry.join(","))
println stock?.index
println stock?.name
println stock?.type
println stock?.industry
writer.close()

