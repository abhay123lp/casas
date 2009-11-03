package com.renaissance.etl.web.sina;

import grails.converters.JSON
import org.codehaus.groovy.grails.web.json.*
import com.renaissance.etl.transform.*
import com.renaissance.etl.web.*
import com.renaissance.etl.models.*
import groovy.util.slurpersupport.GPathResult

/**
 *  SINA.com ��վ�ϰ����������й�˾�����˲ƾ������࣬���
 *  ����֤�����ҵ����ֵ��ֵ����Ϣ�����ౣ��������ص���ҳURL
 *  ��ַ������
 * 
 */
public class SinaPageFactory {

    //֤�����ҵ����
    def industries = new HashMap<String, String>();
    //����
    def regions = new HashMap<String, String>();
    //����
    def concepts = new HashMap<String, String>();
    //���˷���
    def sinaCategories = new HashMap<String, String>();
    //���л������A��
    def hushenAClass="&node=hs_a"

    //SINA JSON web interface 
    def getHQNodeStockCountBaseURL = "http://vip.stock.finance.sina.com.cn/quotes_service/api/json_v2.php/Market_Center.getHQNodeStockCount?";
    def getHQNodeDataBaseURL       = "http://vip.stock.finance.sina.com.cn/quotes_service/api/json_v2.php/Market_Center.getHQNodeData?num=200";
    

    public int getCountByType(String nodePara){
    	String url = getHQNodeStockCountBaseURL+nodePara
    	String input = url.toURL().text
    	if(input==null||input=="null"){
            return 0;
        }else{
            List numList = StringHelper.extractDigits(input)
            return numList.get(0).toInteger() 
        }
    }

    public JSONArray getStockListByType(String nodePara){
    	int num = getCountByType(nodePara)
    	if(num==0) return null;
    	
    	String numPara = "&num="+num
        String url = getHQNodeDataBaseURL+numPara+nodePara
        JSONArray jsonArray = JSON.parse(url.toURL().text)
        return jsonArray;
    }

    public SinaPageFactory(){
    	//֤�����ҵ
        //ũ���֡�������ҵ 
        industries["ũҵ"]="&node=hangye_ZA01";
        industries["��ҵ"]="&node=hangye_ZA03";
        industries["����ҵ"]="&node=hangye_ZA05";
        industries["��ҵ"]="&node=hangye_ZA07";
        industries["ũ������ҵ����ҵ"]="&node=hangye_ZA09";
        //�ɾ�ҵ
        industries["ú̿��ѡҵ"]="&node=hangye_ZB01";
        industries["ʯ�ͺ���Ȼ������ҵ"]="&node=hangye_ZB03";
        industries["��ɫ�������ѡҵ"]="&node=hangye_ZB05";
        industries["��ɫ�������ѡҵ"]="&node=hangye_ZB07";
        industries["�ǽ������ѡҵ"]="&node=hangye_ZB09";
        industries["�������ѡҵ"]="&node=hangye_ZB49";
        industries["�ɾ����"]="&node=hangye_ZB50";
        //����ҵ
        industries["ʳƷ����"]="&node=hangye_ZC0";
        industries["��֯��װƤë"]="&node=hangye_ZC1";
        industries["ľ�ļҾ�"]="&node=hangye_ZC2";
        industries["��ֽӡˢ"]="&node=hangye_ZC3";
        industries["ʯ�ͻ����ܽ�����"]="&node=hangye_ZC4";
        industries["����"]="&node=hangye_ZC5";
        industries["�����ǽ���"]="&node=hangye_ZC6";
        industries["��е�豸�Ǳ�"]="&node=hangye_ZC7";
        industries["ҽҩ������Ʒ"]="&node=hangye_ZC8";
        industries["��������ҵ"]="&node=hangye_ZC99";
        //��������ˮ����ҵ 
        industries["������ˮ����ҵ"]="&node=hangye_ZD01";
        industries["ú�������͹�Ӧҵ"]="&node=hangye_ZD03";
        industries["����ˮ������Ӧҵ"]="&node=hangye_ZD05";
        //����ҵ
        industries["��ľ���̽���ҵ"]="&node=hangye_ZE01";
        industries["װ��װ��ҵ"]="&node=hangye_ZE05";
        //��ͨ���䡢�ִ�ҵ
        industries["��·����ҵ"]="&node=hangye_ZF01";
        industries["��·����ҵ"]="&node=hangye_ZF03";
        industries["�ܵ�����ҵ"]="&node=hangye_ZF05";
        industries["ˮ������ҵ"]="&node=hangye_ZF07";
        industries["��������ҵ"]="&node=hangye_ZF09";
        industries["��ͨ���丨��ҵ"]="&node=hangye_ZF11";
        industries["������ͨ����ҵ"]="&node=hangye_ZF19";
        industries["�ִ�ҵ"]="&node=hangye_ZF21";
        //��Ϣ����ҵ
        industries["ͨ�ż�����豸ҵ"]="&node=hangye_ZG81";
        industries["�����������豸ҵ"]="&node=hangye_ZG83";
        industries["ͨ�ŷ���ҵ"]="&node=hangye_ZG85";
        industries["�����Ӧ�÷���ҵ"]="&node=hangye_ZG87";
        //����������ó��
        industries["ʳƷ���ϼ�������"]="&node=hangye_ZH01";
        industries["��Դ���Ϻͻ���ҵ"]="&node=hangye_ZH03";
        industries["����ҵ"]="&node=hangye_ZH11";
        industries["��ҵ���������ҵ"]="&node=hangye_ZH21";
        //���ڡ�����ҵ
        industries["����ҵ"]="&node=hangye_ZI01";
        industries["����ҵ"]="&node=hangye_ZI11";
        industries["֤ȯ�ڻ�ҵ"]="&node=hangye_ZI21";
        industries["��������ҵ"]="&node=hangye_ZI31";
        industries["����ҵ"]="&node=hangye_ZI41";
        industries["��������ҵ"]="&node=hangye_ZI99";
        //���ز�ҵ
        industries["���ز������뾭Ӫҵ"]="&node=hangye_ZJ01";
        industries["���ز�����ҵ"]="&node=hangye_ZJ05";
        //������ҵ
        industries["������ʩ����ҵ"]="&node=hangye_ZK01";
        industries["רҵ���з���ҵ"]="&node=hangye_ZK20";
        industries["����ҵ"]="&node=hangye_ZK30";
        industries["�ù�ҵ"]="&node=hangye_ZK32";
        industries["����ҵ"]="&node=hangye_ZK34";
        industries["������������ҵ"]="&node=hangye_ZK37";
        industries["���޷���ҵ"]="&node=hangye_ZK39";
        industries["����������ҵ"]="&node=hangye_ZK99";
        //�������Ļ���ҵ
        industries["����ҵ"]="&node=hangye_ZL01";
        industries["����ҵ"]="&node=hangye_ZL05";
        industries["�㲥��Ӱ����ҵ"]="&node=hangye_ZL10";
        industries["����ҵ"]="&node=hangye_ZL15";
        industries["��Ϣ��������ҵ"]="&node=hangye_ZL20";
        industries["���������Ļ���ҵ"]="&node=hangye_ZL99";
        //�ۺ���
        industries["�ۺ���"]="&node=hangye_ZM";

        //����
        concepts["3G����"]="&node=gainian_08";
        concepts["ST��"]="&node=gainian_22";
        concepts["���¹�"]="&node=gainian_31";
        concepts["���˸���"]="&node=gainian_50";
        concepts["�ʲ�ע��"]="&node=gainian_58";
        concepts["���ֵ���"]="&node=gainian_60";
        concepts["�ιɄ���"]="&node=gainian_62";
        concepts["��������"]="&node=gainian_65";
        concepts["��ó����"]="&node=gainian_70";
        concepts["����ֹ�"]="&node=gainian_72";
        concepts["�ιɽ���"]="&node=gainian_74";
        concepts["δ�ɸ�"]="&node=gainian_76";
        concepts["ˮ����"]="&node=gainian_80";
        concepts["����ع�"]="&node=gainian_83";
        concepts["�������"]="&node=gainian_85";
        concepts["��������"]="&node=gainian_87";
        concepts["�ڹɸ���"]="&node=gainian_89";
        concepts["������"]="&node=gainian_91";
        concepts["��ʿ��"]="&node=gainian_93";
        concepts["�ֺ��ؽ�"]="&node=gainian_95";
        concepts["QFII�ֹ�"]="&node=gainian_15";
        concepts["���˶���"]="&node=gainian_29";
        concepts["�����ز�"]="&node=gainian_34";
        concepts["���о�ʾ"]="&node=gainian_57";
        concepts["������"]="&node=gainian_59";
        concepts["���캽��"]="&node=gainian_61";
        concepts["���ֳ�ŵ"]="&node=gainian_64";
        concepts["��Ȩ����"]="&node=gainian_69";
        concepts["��������"]="&node=gainian_71";
        concepts["��Ͷ����"]="&node=gainian_73";
        concepts["ѭ������"]="&node=gainian_75";
        concepts["���ܻ���"]="&node=gainian_79";
        concepts["�������"]="&node=gainian_82";
        concepts["��Դ���"]="&node=gainian_84";
        concepts["����Դ"]="&node=gainian_86";
        concepts["��ͨ����"]="&node=gainian_88";
        concepts["OTCҽҩ"]="&node=gainian_90";
        concepts["������˰"]="&node=gainian_92";
        concepts["���гֹ�"]="&node=gainian_94";
        
        //����
        regions["����"]="&node=diyu_1100";
        regions["���"]="&node=diyu_1200";
        regions["�ӱ�"]="&node=diyu_1300";
        regions["ɽ��"]="&node=diyu_1400";
        regions["���ɹ�"]="&node=diyu_1500";
        regions["����"]="&node=diyu_2100";
        regions["����"]="&node=diyu_2200";
        regions["������"]="&node=diyu_2300";
        regions["�Ϻ�"]="&node=diyu_3100";
        regions["����"]="&node=diyu_3200";
        regions["�㽭"]="&node=diyu_3300";
        regions["����"]="&node=diyu_3400";
        regions["����"]="&node=diyu_3500";
        regions["����"]="&node=diyu_3600";
        regions["ɽ��"]="&node=diyu_3700";
        regions["����"]="&node=diyu_4100";
        regions["����"]="&node=diyu_4200";
        regions["����"]="&node=diyu_4300";
        regions["�㶫"]="&node=diyu_4400";
        regions["����"]="&node=diyu_4401";
        regions["����"]="&node=diyu_4410";
        regions["����"]="&node=diyu_4500";
        regions["����"]="&node=diyu_4600";
        regions["�Ĵ�"]="&node=diyu_5100";
        regions["����"]="&node=diyu_5200";
        regions["����"]="&node=diyu_5300";
        regions["����"]="&node=diyu_5400";
        regions["����"]="&node=diyu_5500";
        regions["����"]="&node=diyu_6100";
        regions["����"]="&node=diyu_6200";
        regions["�ຣ"]="&node=diyu_6300";
        regions["����"]="&node=diyu_6400";
        regions["�½�"]="&node=diyu_6500";
        
        //���˷���
        sinaCategories["����"]="&node=sina_dl";
        sinaCategories["������Ϣ"]="&node=sina_dzxx";
        sinaCategories["��֯��װ"]="&node=sina_fzfz";
        sinaCategories["��ˮ����"]="&node=sina_gsgd";
        sinaCategories["��������"]="&node=sina_hghx";
        sinaCategories["�����"]="&node=sina_jsj";
        sinaCategories["��е"]="&node=sina_jx";
        sinaCategories["���ʳƷ"]="&node=sina_njsp";
        sinaCategories["ú̿ʯ��"]="&node=sina_mtsy";
        sinaCategories["������"]="&node=sina_qcl";
        sinaCategories["��ҵ����"]="&node=sina_syls";
        sinaCategories["��ó"]="&node=sina_wm";
        sinaCategories["��������"]="&node=sina_yhxt";
        sinaCategories["��������"]="&node=sina_yswl";
        sinaCategories["��ֽӡˢ"]="&node=sina_zzys";
        sinaCategories["����"]="&node=sina_dq";
        sinaCategories["���ز�"]="&node=sina_fdc";
        sinaCategories["���̽���"]="&node=sina_gcjz";
        sinaCategories["����"]="&node=sina_gt";
        sinaCategories["����"]="&node=sina_jc";
        sinaCategories["��ͨ��ʩ"]="&node=sina_jtss";
        sinaCategories["������ý"]="&node=sina_jycm";
        sinaCategories["���ξƵ�"]="&node=sina_lyjd";
        sinaCategories["ũ������"]="&node=sina_nlmy";
        sinaCategories["ȯ��"]="&node=sina_qs";
        sinaCategories["ͨ��"]="&node=sina_ts";
        sinaCategories["�ǵ��Ǳ�"]="&node=sina_ydyb";
        sinaCategories["��ɫ����"]="&node=sina_ysjs";
        sinaCategories["ҽҩ"]="&node=sina_yy";
    }
    
    public static GPathResult getTargetPriceURL(String code){
        // Example: 
        // http://finance.sina.com.cn/stock/message/sogusina/invest600189.htm
        String url = "http://finance.sina.com.cn/stock/message/sogusina/invest"+code+".htm";
        GPathResult html = HTMLUtil.parse(url);
        return html;
    }
    
    public static GPathResult getPriceHistoryURL(stock, startDate, endDate){
        String symbol = stock.market.toLowerCase()+stock.code; 
        String start = startDate.format("yyyy-MM-dd");
        String end = endDate.format("yyyy-MM-dd");
        String url = "http://market.finance.sina.com.cn/pricehis.php?symbol=${symbol}&startdate=${start}&enddate=${end}";
        GPathResult html = HTMLUtil.parse(url);
        return html;
    }
    
    public static getTransactionParticulars(stock, date){
        println stock.code+":"+stock.name
        String symbol = stock.market.toLowerCase()+stock.code;
        String dataString = date.format("yyyy-MM-dd");
        String url = "http://market.finance.sina.com.cn/downxls.php?date=${dataString}&symbol=${symbol}"
        
        println "parsing "+url
        def text = url.toURL().text;
        //println text
        if(text.indexOf("alert")>0) return null
        return text;
        
    }
}
