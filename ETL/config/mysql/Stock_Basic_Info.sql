USE stock;
DROP TABLE IF EXISTS Stock_Basic_Info;
CREATE TABLE Stock_Basic_Info
(
	code CHAR(10) NOT NULL,
	name CHAR(50) NOT NULL,
	market CHAR(4) NOT NULL,
	industry VARCHAR(50) NOT NULL,
	region CHAR(50) NULL,
	PRIMARY KEY (code),
	UNIQUE UQ_StockBasicInfo_code(code)
) ;


