/**
 * 
 */
package com.renaissance.etl.web.baidu
import com.renaissance.etl.models.*

//def index = new StockIndex(index:"000001", type:StockIndex.SZ)
//println BaiduPageFactory.getFinancialIndicatorPage(index)

def pageFactory = new BaiduPageFactory()
def stockSh = new StockBasicInfo(index:"601166", type:StockBasicInfo.SH)
def stockSz = new StockBasicInfo(index:"000001", type:StockBasicInfo.SZ)

def stockBasicInfo = new StockBasicInfo()
def transactionRecord = new TransactionRecord()
def businessEfficiency = new BusinessEfficiency()
def debtPayingAbility = new DebtPayingAbility()
def growingAbility = new GrowingAbility()
def profitability = new Profitability()
def financialReport = new FinancialReport()

pageFactory.getBaiduWebPage(stockSh, stockBasicInfo)
pageFactory.getBaiduWebPage(stockSh, transactionRecord)
pageFactory.getBaiduWebPage(stockSh, financialReport)
pageFactory.getBaiduWebPage(stockSh, profitability)
pageFactory.getBaiduWebPage(stockSh, businessEfficiency)
pageFactory.getBaiduWebPage(stockSh, debtPayingAbility)
pageFactory.getBaiduWebPage(stockSh, growingAbility)

pageFactory.getBaiduWebPage(stockSz, stockBasicInfo)
pageFactory.getBaiduWebPage(stockSz, transactionRecord)
pageFactory.getBaiduWebPage(stockSz, financialReport)
pageFactory.getBaiduWebPage(stockSz, profitability)
pageFactory.getBaiduWebPage(stockSz, businessEfficiency)
pageFactory.getBaiduWebPage(stockSz, debtPayingAbility)
pageFactory.getBaiduWebPage(stockSz, growingAbility)


