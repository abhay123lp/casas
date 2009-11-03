USE stock;
DROP TABLE IF EXISTS avg10;
CREATE TABLE avg10
(
	code CHAR(10) NOT NULL,
	date DATE NOT NULL,
	avgPrice NUMERIC(10,2) NULL,
	avgVolume NUMERIC(20,2) NULL,
	PRIMARY KEY (code, date)
) ;


DELIMITER $$

DROP PROCEDURE IF EXISTS `update_avg10` $$
CREATE DEFINER=`damon`@`localhost` PROCEDURE `update_avg10`(IN todate DATE)
Begin
    insert into avg10
    select code, `todate` date, avg(average) avgPrice, avg(turnover*average*100) avgVolume
    from transaction_record
    where datediff(`todate`, date)<10 and datediff(`todate`, date)=0 group by code;
End $$

DELIMITER ;