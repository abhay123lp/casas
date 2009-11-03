#####################################################################
# 动态属性表 dynamic：
# code:           代码
# date:           日期
# price:          现价
# volume:         成交额
# marketValue:    总市值(亿元)
# pe:             市盈率，
# relativePrice:  相对价格(现价/估价)
# 
#####################################################################
create view dynamic as
select t.code, g.name, t.date, t.close price, t.turnover*t.average*100 volume, t.aggregateValue marketValue, t.close/(f.primaryEPS*12/month(f.fiscal_season)) pe,
t.close/g.targetPrice relativePrice
from transaction_record t left join financial_report f on t.code=f.code left join target_Price g on t.code=g.code
