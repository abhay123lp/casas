package com.renaissance.etl.web.sina
import grails.converters.JSON

url="http://vip.stock.finance.sina.com.cn/quotes_service/api/json_v2.php/Market_Center.getHQNodeData?page=1&num=20&sort=symbol&asc=1&node=hangye_ZA03&_s_r_a=init"
jsonArray = JSON.parse(url.toURL().text)
obj = jsonArray.getJSONObject(0)
println obj.code+" "+obj.name


