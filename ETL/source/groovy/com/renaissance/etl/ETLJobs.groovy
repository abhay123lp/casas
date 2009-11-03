package com.renaissance.etl

import com.renaissance.etl.web.baidu.BaiduDataExtractFacade
import com.renaissance.etl.web.sina.SinaDataExtractFacade

BaiduDataExtractFacade baiduFacade = new BaiduDataExtractFacade()
SinaDataExtractFacade  sinaFacade = new SinaDataExtractFacade()

calender = Calendar.getInstance();
today = calender.get(Calendar.DAY_OF_WEEK);

if(Calendar.SUNDAY==today){
    /* 每周日的任务:
     * Baidu 更新上市公司列表
     * Baidu 下载公司财务报表
     * Sina 下载行业分类
     * Sina 下载地域分类
     * Sina 下载概念分类
     * Sina 下载EPS预测和价值估值
     */
    //delete the table stock_basic_info, Financial_Report, Concept at first
    DataExpert.deleteStockBasicInfo();
    DataExpert.deleteFinancialReport();
    DataExpert.deleteConcept();

    baiduFacade.verifyStockID();   //stock_basic_info
    sinaFacade.downloadIndustry(); //stock_basic_info
    sinaFacade.downloadLocation(); //stock_basic_info

    baiduFacade.downloadFinancialReport(); //Financial_Report

    sinaFacade.downloadSinaCategory(); //Concept
    sinaFacade.downloadConcepts();     //Concept

    sinaFacade.downloadAllPrediction(); //target_price, eps_prediction
}

if(Calendar.MONDAY<=today && today<=Calendar.FRIDAY){
    /* 每个交易日的任务:
     * 下载上证综合指数
     * 下载沪深A股交易数据
     */
    boolean isTradingDate = baiduFacade.downloadMarketIndex();

    if(isTradingDate){
        baiduFacade.downloadTransactionRecord();
        DataExpert.updateAvgTable()
        // 当日成交明细在7点以后才能下载
        //sinaFacade.downloadAllTransactionParticulars(new Date());
    }
}
