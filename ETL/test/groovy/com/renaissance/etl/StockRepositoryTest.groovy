package com.renaissance.etl

def target = new StockRepository(fileName:"config/stocks.txt")
def stockList = target.getStockList()
println stockList.size()
println stockList[0].index
println stockList[0].name
println stockList[0].type
println stockList[0].industry

println stockList[100].index
println stockList[100].name
println stockList[100].type
println stockList[100].industry
