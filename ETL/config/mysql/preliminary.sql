#####################################################################
# ��Ʊ���ݼ� preliminaryXX:
# static.code:             ����
# static.name:             ��ƣ�
# static.region:           ������
# static.industry:         ��ҵ��
# static.concept:          ���
# static.targetPrice:      Ŀ���
# dynamic.date:            ����
# dynamic.price:           �ּ�
# dynamic.volume:          �ɽ���
# dynamic.marketValue:     ����ֵ(��Ԫ)
# dynamic.pe:              ��ӯ�ʣ�
# dynamic.relativePrice:   ��Լ۸�(�ּ�/����)
# trend.priceGrowthRate:   �۸��ǵ�����
# trend.volumeGrowthRate:  �ɽ����ǵ���
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
