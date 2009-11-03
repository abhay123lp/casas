#####################################################################
# ���չ�Ʊ�������� trend��
# code:        ����
# name:        ��ƣ�
# date:        ����
# priceGrowthRate: �۸��ǵ�����
# volumeGrowthRate:   �ɽ����ǵ���
#
#####################################################################
DROP VIEW IF EXISTS trend;
CREATE OR REPLACE ALGORITHM=UNDEFINED DEFINER=`damon`@`localhost` SQL SECURITY DEFINER VIEW trend 
AS select b.code, b.name, t.date, t.changePct AS priceGrowthRate,(t.turnover-t.lastTurnover)/t.lastTurnover*100 volumeGrowthRate
from (transaction_record t join stock_basic_info b) where (t.code = b.code);

#####################################################################
# 10�չ�Ʊ�������� trend10��
# code:        ����
# name:        ��ƣ�
# date:        ����
# priceGrowthRate:    �۸��ǵ���
# volumeGrowthRate:   �ɽ����ǵ���
#
#####################################################################
DROP VIEW IF EXISTS trend10;
CREATE OR REPLACE ALGORITHM=UNDEFINED DEFINER=`damon`@`localhost` SQL SECURITY DEFINER VIEW trend10
AS select b.code, b.name, t.date, (t.close-a.avgPrice)/a.avgPrice*100 priceGrowthRate, (t.turnover*t.average*100-a.avgVolume)/a.avgVolume*100 volumeGrowthRate
from ((transaction_record t left join stock_basic_info b on t.code=b.code) right join avg10 a on(t.code=a.code and t.date=a.date));

#####################################################################
# 30�չ�Ʊ�������� trend30��
# code:        ����
# name:        ��ƣ�
# date:        ����
# priceGrowthRate:    �۸��ǵ���
# volumeGrowthRate:   �ɽ����ǵ���
#
#####################################################################
DROP VIEW IF EXISTS trend30;
CREATE OR REPLACE ALGORITHM=UNDEFINED DEFINER=`damon`@`localhost` SQL SECURITY DEFINER VIEW trend30
AS select b.code, b.name, t.date, (t.close-a.avgPrice)/a.avgPrice*100 priceGrowthRate, (t.turnover*t.average*100-a.avgVolume)/a.avgVolume*100 volumeGrowthRate
from ((transaction_record t left join stock_basic_info b on t.code=b.code) right join avg30 a on(t.code=a.code and t.date=a.date));
