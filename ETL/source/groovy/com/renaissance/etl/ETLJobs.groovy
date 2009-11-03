package com.renaissance.etl

import com.renaissance.etl.web.baidu.BaiduDataExtractFacade
import com.renaissance.etl.web.sina.SinaDataExtractFacade

BaiduDataExtractFacade baiduFacade = new BaiduDataExtractFacade()
SinaDataExtractFacade  sinaFacade = new SinaDataExtractFacade()

calender = Calendar.getInstance();
today = calender.get(Calendar.DAY_OF_WEEK);

if(Calendar.SUNDAY==today){
    /* ÿ���յ�����:
     * Baidu �������й�˾�б�
     * Baidu ���ع�˾���񱨱�
     * Sina ������ҵ����
     * Sina ���ص������
     * Sina ���ظ������
     * Sina ����EPSԤ��ͼ�ֵ��ֵ
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
    /* ÿ�������յ�����:
     * ������֤�ۺ�ָ��
     * ���ػ���A�ɽ�������
     */
    boolean isTradingDate = baiduFacade.downloadMarketIndex();

    if(isTradingDate){
        baiduFacade.downloadTransactionRecord();
        DataExpert.updateAvgTable()
        // ���ճɽ���ϸ��7���Ժ��������
        //sinaFacade.downloadAllTransactionParticulars(new Date());
    }
}
