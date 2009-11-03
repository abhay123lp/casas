package com.renaissance.etl.web.sina;

import grails.converters.JSON
import org.codehaus.groovy.grails.web.json.*
import com.renaissance.etl.transform.*
import com.renaissance.etl.web.*
import com.renaissance.etl.models.*
import groovy.util.slurpersupport.GPathResult

/**
 *  SINA.com 网站上包含关于上市公司的新浪财经板块分类，概念，
 *  地域，证监会行业，价值估值等信息。此类保存所有相关的网页URL
 *  地址常量。
 * 
 */
public class SinaPageFactory {

    //证监会行业分类
    def industries = new HashMap<String, String>();
    //地域
    def regions = new HashMap<String, String>();
    //概念
    def concepts = new HashMap<String, String>();
    //新浪分类
    def sinaCategories = new HashMap<String, String>();
    //所有沪深股市A股
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
    	//证监会行业
        //农、林、牧、渔业 
        industries["农业"]="&node=hangye_ZA01";
        industries["林业"]="&node=hangye_ZA03";
        industries["畜牧业"]="&node=hangye_ZA05";
        industries["渔业"]="&node=hangye_ZA07";
        industries["农林牧渔业服务业"]="&node=hangye_ZA09";
        //采掘业
        industries["煤炭采选业"]="&node=hangye_ZB01";
        industries["石油和天然气开采业"]="&node=hangye_ZB03";
        industries["黑色金属矿采选业"]="&node=hangye_ZB05";
        industries["有色金属矿采选业"]="&node=hangye_ZB07";
        industries["非金属矿采选业"]="&node=hangye_ZB09";
        industries["其他矿采选业"]="&node=hangye_ZB49";
        industries["采掘服务"]="&node=hangye_ZB50";
        //制造业
        industries["食品饮料"]="&node=hangye_ZC0";
        industries["纺织服装皮毛"]="&node=hangye_ZC1";
        industries["木材家具"]="&node=hangye_ZC2";
        industries["造纸印刷"]="&node=hangye_ZC3";
        industries["石油化工塑胶塑料"]="&node=hangye_ZC4";
        industries["电子"]="&node=hangye_ZC5";
        industries["金属非金属"]="&node=hangye_ZC6";
        industries["机械设备仪表"]="&node=hangye_ZC7";
        industries["医药生物制品"]="&node=hangye_ZC8";
        industries["其他制造业"]="&node=hangye_ZC99";
        //电力、供水供气业 
        industries["电力供水供气业"]="&node=hangye_ZD01";
        industries["煤气生产和供应业"]="&node=hangye_ZD03";
        industries["自来水生产供应业"]="&node=hangye_ZD05";
        //建筑业
        industries["土木工程建筑业"]="&node=hangye_ZE01";
        industries["装修装饰业"]="&node=hangye_ZE05";
        //交通运输、仓储业
        industries["铁路运输业"]="&node=hangye_ZF01";
        industries["公路运输业"]="&node=hangye_ZF03";
        industries["管道运输业"]="&node=hangye_ZF05";
        industries["水上运输业"]="&node=hangye_ZF07";
        industries["航空运输业"]="&node=hangye_ZF09";
        industries["交通运输辅助业"]="&node=hangye_ZF11";
        industries["其他交通运输业"]="&node=hangye_ZF19";
        industries["仓储业"]="&node=hangye_ZF21";
        //信息技术业
        industries["通信及相关设备业"]="&node=hangye_ZG81";
        industries["计算机及相关设备业"]="&node=hangye_ZG83";
        industries["通信服务业"]="&node=hangye_ZG85";
        industries["计算机应用服务业"]="&node=hangye_ZG87";
        //批发和零售贸易
        industries["食品饮料家用批发"]="&node=hangye_ZH01";
        industries["能源材料和机电业"]="&node=hangye_ZH03";
        industries["零售业"]="&node=hangye_ZH11";
        industries["商业经纪与代理业"]="&node=hangye_ZH21";
        //金融、保险业
        industries["银行业"]="&node=hangye_ZI01";
        industries["保险业"]="&node=hangye_ZI11";
        industries["证券期货业"]="&node=hangye_ZI21";
        industries["金融信托业"]="&node=hangye_ZI31";
        industries["基金业"]="&node=hangye_ZI41";
        industries["其他金融业"]="&node=hangye_ZI99";
        //房地产业
        industries["房地产开发与经营业"]="&node=hangye_ZJ01";
        industries["房地产管理业"]="&node=hangye_ZJ05";
        //社会服务业
        industries["公共设施服务业"]="&node=hangye_ZK01";
        industries["专业科研服务业"]="&node=hangye_ZK20";
        industries["餐饮业"]="&node=hangye_ZK30";
        industries["旅馆业"]="&node=hangye_ZK32";
        industries["旅游业"]="&node=hangye_ZK34";
        industries["卫生保健护理业"]="&node=hangye_ZK37";
        industries["租赁服务业"]="&node=hangye_ZK39";
        industries["其他社会服务业"]="&node=hangye_ZK99";
        //传播与文化产业
        industries["出版业"]="&node=hangye_ZL01";
        industries["声像业"]="&node=hangye_ZL05";
        industries["广播电影电视业"]="&node=hangye_ZL10";
        industries["艺术业"]="&node=hangye_ZL15";
        industries["信息传播服务业"]="&node=hangye_ZL20";
        industries["其他传播文化产业"]="&node=hangye_ZL99";
        //综合类
        industries["综合类"]="&node=hangye_ZM";

        //概念
        concepts["3G概念"]="&node=gainian_08";
        concepts["ST股"]="&node=gainian_22";
        concepts["次新股"]="&node=gainian_31";
        concepts["奥运概念"]="&node=gainian_50";
        concepts["资产注入"]="&node=gainian_58";
        concepts["数字电视"]="&node=gainian_60";
        concepts["参股劵商"]="&node=gainian_62";
        concepts["世博概念"]="&node=gainian_65";
        concepts["外贸并购"]="&node=gainian_70";
        concepts["交叉持股"]="&node=gainian_72";
        concepts["参股金融"]="&node=gainian_74";
        concepts["未股改"]="&node=gainian_76";
        concepts["水务板块"]="&node=gainian_80";
        concepts["央企控股"]="&node=gainian_83";
        concepts["借壳上市"]="&node=gainian_85";
        concepts["整体上市"]="&node=gainian_87";
        concepts["期股概念"]="&node=gainian_89";
        concepts["长三角"]="&node=gainian_91";
        concepts["迪士尼"]="&node=gainian_93";
        concepts["灾后重建"]="&node=gainian_95";
        concepts["QFII持股"]="&node=gainian_15";
        concepts["振兴东北"]="&node=gainian_29";
        concepts["基金重仓"]="&node=gainian_34";
        concepts["退市警示"]="&node=gainian_57";
        concepts["再融资"]="&node=gainian_59";
        concepts["航天航空"]="&node=gainian_61";
        concepts["增持承诺"]="&node=gainian_64";
        concepts["股权激励"]="&node=gainian_69";
        concepts["军工概念"]="&node=gainian_71";
        concepts["创投概念"]="&node=gainian_73";
        concepts["循环经济"]="&node=gainian_75";
        concepts["节能环保"]="&node=gainian_79";
        concepts["软件开发"]="&node=gainian_82";
        concepts["资源板块"]="&node=gainian_84";
        concepts["新能源"]="&node=gainian_86";
        concepts["三通概念"]="&node=gainian_88";
        concepts["OTC医药"]="&node=gainian_90";
        concepts["出口退税"]="&node=gainian_92";
        concepts["信托持股"]="&node=gainian_94";
        
        //地域
        regions["北京"]="&node=diyu_1100";
        regions["天津"]="&node=diyu_1200";
        regions["河北"]="&node=diyu_1300";
        regions["山西"]="&node=diyu_1400";
        regions["内蒙古"]="&node=diyu_1500";
        regions["辽宁"]="&node=diyu_2100";
        regions["吉林"]="&node=diyu_2200";
        regions["黑龙江"]="&node=diyu_2300";
        regions["上海"]="&node=diyu_3100";
        regions["江苏"]="&node=diyu_3200";
        regions["浙江"]="&node=diyu_3300";
        regions["安徽"]="&node=diyu_3400";
        regions["福建"]="&node=diyu_3500";
        regions["江西"]="&node=diyu_3600";
        regions["山东"]="&node=diyu_3700";
        regions["河南"]="&node=diyu_4100";
        regions["湖北"]="&node=diyu_4200";
        regions["湖南"]="&node=diyu_4300";
        regions["广东"]="&node=diyu_4400";
        regions["广州"]="&node=diyu_4401";
        regions["深圳"]="&node=diyu_4410";
        regions["广西"]="&node=diyu_4500";
        regions["海南"]="&node=diyu_4600";
        regions["四川"]="&node=diyu_5100";
        regions["贵州"]="&node=diyu_5200";
        regions["云南"]="&node=diyu_5300";
        regions["西藏"]="&node=diyu_5400";
        regions["重庆"]="&node=diyu_5500";
        regions["陕西"]="&node=diyu_6100";
        regions["甘肃"]="&node=diyu_6200";
        regions["青海"]="&node=diyu_6300";
        regions["宁夏"]="&node=diyu_6400";
        regions["新疆"]="&node=diyu_6500";
        
        //新浪分类
        sinaCategories["电力"]="&node=sina_dl";
        sinaCategories["电子信息"]="&node=sina_dzxx";
        sinaCategories["纺织服装"]="&node=sina_fzfz";
        sinaCategories["供水供气"]="&node=sina_gsgd";
        sinaCategories["化工化纤"]="&node=sina_hghx";
        sinaCategories["计算机"]="&node=sina_jsj";
        sinaCategories["机械"]="&node=sina_jx";
        sinaCategories["酿酒食品"]="&node=sina_njsp";
        sinaCategories["煤炭石油"]="&node=sina_mtsy";
        sinaCategories["汽车类"]="&node=sina_qcl";
        sinaCategories["商业连锁"]="&node=sina_syls";
        sinaCategories["外贸"]="&node=sina_wm";
        sinaCategories["银行信托"]="&node=sina_yhxt";
        sinaCategories["运输物流"]="&node=sina_yswl";
        sinaCategories["造纸印刷"]="&node=sina_zzys";
        sinaCategories["电器"]="&node=sina_dq";
        sinaCategories["房地产"]="&node=sina_fdc";
        sinaCategories["工程建筑"]="&node=sina_gcjz";
        sinaCategories["钢铁"]="&node=sina_gt";
        sinaCategories["建材"]="&node=sina_jc";
        sinaCategories["交通设施"]="&node=sina_jtss";
        sinaCategories["教育传媒"]="&node=sina_jycm";
        sinaCategories["旅游酒店"]="&node=sina_lyjd";
        sinaCategories["农林牧渔"]="&node=sina_nlmy";
        sinaCategories["券商"]="&node=sina_qs";
        sinaCategories["通信"]="&node=sina_ts";
        sinaCategories["仪电仪表"]="&node=sina_ydyb";
        sinaCategories["有色金属"]="&node=sina_ysjs";
        sinaCategories["医药"]="&node=sina_yy";
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
