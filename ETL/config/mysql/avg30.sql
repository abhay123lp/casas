USE stock;
DROP TABLE IF EXISTS avg30;
CREATE TABLE avg30
(
	code CHAR(10) NOT NULL,
	date DATE NOT NULL,
	avgPrice NUMERIC(10,2) NULL,
	avgVolume NUMERIC(20,2) NULL,
	PRIMARY KEY (code, date)
) ;


DELIMITER $$

DROP PROCEDURE IF EXISTS `update_avg30` $$
CREATE DEFINER=`damon`@`localhost` PROCEDURE `update_avg30`(IN todate DATE)
    MODIFIES SQL DATA
Begin
    insert into avg30
    select code, `todate` date, avg(average) avgPrice, avg(turnover*average*100) avgVolume
    from transaction_record
    where datediff(`todate`, date)<30 and datediff(`todate`, date)=0 group by code;
End $$

DELIMITER ;

