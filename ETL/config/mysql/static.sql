#####################################################################
# ���й�˾��̬�� static��
# code:        ���룬
# name:        ��ƣ�
# region:      ������
# industry:    ��ҵ��
# concept:     ���
# targetPrice: Ŀ���
# 
#####################################################################
create view static as
select b.code, b.name, b.industry, b.region, c.concept, t.targetPrice
from stock_basic_info b left join concept c on b.code=c.code left join target_price t on b.code=t.code

