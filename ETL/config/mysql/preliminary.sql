#####################################################################
# 股票数据集 preliminaryXX:
# static.code:             代码
# static.name:             简称，
# static.region:           地区，
# static.industry:         行业，
# static.concept:          概念，
# static.targetPrice:      目标价
# dynamic.date:            日期
# dynamic.price:           现价
# dynamic.volume:          成交额
# dynamic.marketValue:     总市值(亿元)
# dynamic.pe:              市盈率，
# dynamic.relativePrice:   相对价格(现价/估价)
# trend.priceGrowthRate:   价格涨跌幅，
# trend.volumeGrowthRate:  成交量涨跌幅
#####################################################################
create view preliminary as
select s.*, d.date, d.price, d.volume, d.marketValue, d.pe, d.relativePrice, t.priceGrowthRate, t.volumeGrowthRate
from static s, trend t, dynamic d
where s.code=t.code and s.code=d.code and d.date=t.date

create view preliminary10 as
select s.*, d.date, d.price, d.volume, d.marketValue, d.pe, d.relativePrice, t.priceGrowthRate, t.volumeGrowthRate
from static s, trend10 t, dynamic d
where s.code=t.code and s.code=d.code and d.date=t.date

create view preliminary30 as
select s.*, d.date, d.price, d.volume, d.marketValue, d.pe, d.relativePrice, t.priceGrowthRate, t.volumeGrowthRate
from static s, trend30 t, dynamic d
where s.code=t.code and s.code=d.code and d.date=t.date
