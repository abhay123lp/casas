package com.renaissance.etl.web

import org.cyberneko.html.parsers.SAXParser
import groovy.util.slurpersupport.GPathResult

public class HTMLUtil{
	
    //HTML parser based on NekoHTML
    private static htmlParser = null
    
    public static GPathResult parse(url){
        if(htmlParser==null){
            htmlParser = new XmlSlurper(new SAXParser())
            htmlParser.setFeature("http://xml.org/sax/features/namespaces",false)
        }
        
        def GPathResult html=null
        int times = 0
        while(times<10){
            println "times = "+times
            println url
            try{
                html = htmlParser.parse(url)
                break
            }catch(FileNotFoundException e1){
            	println "get FileNotFoundException"
            	break;
            }catch(e){
                // the connection is lost, wait for 2 seconds
                times++
                sleep(2000)
            }
        }
        return html
    }
}
