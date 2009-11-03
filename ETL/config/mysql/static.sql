#####################################################################
# 上市公司静态表 static：
# code:        代码，
# name:        简称，
# region:      地区，
# industry:    行业，
# concept:     概念，
# targetPrice: 目标价
# 
#####################################################################
create view static as
select b.code, b.name, b.industry, b.region, c.concept, t.targetPrice
from stock_basic_info b left join concept c on b.code=c.code left join target_price t on b.code=t.code

