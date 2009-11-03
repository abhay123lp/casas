#####################################################################
# 单日股票交易走势 trend：
# code:        代码
# name:        简称，
# date:        日期
# priceGrowthRate: 价格涨跌幅，
# volumeGrowthRate:   成交量涨跌幅
#
#####################################################################
DROP VIEW IF EXISTS trend;
CREATE OR REPLACE ALGORITHM=UNDEFINED DEFINER=`damon`@`localhost` SQL SECURITY DEFINER VIEW trend 
AS select b.code, b.name, t.date, t.changePct AS priceGrowthRate,(t.turnover-t.lastTurnover)/t.lastTurnover*100 volumeGrowthRate
from (transaction_record t join stock_basic_info b) where (t.code = b.code);

#####################################################################
# 10日股票交易走势 trend10：
# code:        代码
# name:        简称，
# date:        日期
# priceGrowthRate:    价格涨跌幅
# volumeGrowthRate:   成交量涨跌幅
#
#####################################################################
DROP VIEW IF EXISTS trend10;
CREATE OR REPLACE ALGORITHM=UNDEFINED DEFINER=`damon`@`localhost` SQL SECURITY DEFINER VIEW trend10
AS select b.code, b.name, t.date, (t.close-a.avgPrice)/a.avgPrice*100 priceGrowthRate, (t.turnover*t.average*100-a.avgVolume)/a.avgVolume*100 volumeGrowthRate
from ((transaction_record t left join stock_basic_info b on t.code=b.code) right join avg10 a on(t.code=a.code and t.date=a.date));

#####################################################################
# 30日股票交易走势 trend30：
# code:        代码
# name:        简称，
# date:        日期
# priceGrowthRate:    价格涨跌幅
# volumeGrowthRate:   成交量涨跌幅
#
#####################################################################
DROP VIEW IF EXISTS trend30;
CREATE OR REPLACE ALGORITHM=UNDEFINED DEFINER=`damon`@`localhost` SQL SECURITY DEFINER VIEW trend30
AS select b.code, b.name, t.date, (t.close-a.avgPrice)/a.avgPrice*100 priceGrowthRate, (t.turnover*t.average*100-a.avgVolume)/a.avgVolume*100 volumeGrowthRate
from ((transaction_record t left join stock_basic_info b on t.code=b.code) right join avg30 a on(t.code=a.code and t.date=a.date));
