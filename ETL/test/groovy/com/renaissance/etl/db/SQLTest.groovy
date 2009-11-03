/**
 * 
 */
package com.renaissance.etl.db
import com.renaissance.etl.StockRepository

def stocks = new StockRepository(fileName:"config/stockIndex.txt").getStockList()
def sql = groovy.sql.Sql.newInstance(
		'jdbc:mysql://localhost:3306/stock' ,
				"damon",
				"1024Leo!",
				'com.mysql.jdbc.Driver' )
println stocks.size()

def dataSet = sql.dataSet('DebtPayingAbility')
println dataSet.rows()
println dataSet.getSql()
//stocks.each{ stock->
//  def fields = stock.toMap()
//  fields.each{key,value->
//      println key+"-->"+value
//  }
//  stockBaiscInfoTable.add fields
//  println ""
//}

