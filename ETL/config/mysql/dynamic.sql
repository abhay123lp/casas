#####################################################################
# ��̬���Ա� dynamic��
# code:           ����
# date:           ����
# price:          �ּ�
# volume:         �ɽ���
# marketValue:    ����ֵ(��Ԫ)
# pe:             ��ӯ�ʣ�
# relativePrice:  ��Լ۸�(�ּ�/����)
# 
#####################################################################
create view dynamic as
select t.code, g.name, t.date, t.close price, t.turnover*t.average*100 volume, t.aggregateValue marketValue, t.close/(f.primaryEPS*12/month(f.fiscal_season)) pe,
t.close/g.targetPrice relativePrice
from transaction_record t left join financial_report f on t.code=f.code left join target_Price g on t.code=g.code
