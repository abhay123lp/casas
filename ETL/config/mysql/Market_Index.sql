USE stock
;
DROP TABLE IF EXISTS Market_Index
;
CREATE TABLE Market_Index
(
	date DATE NOT NULL,
	code CHAR(10) NULL,
	lastClose NUMERIC(10,2) NULL,
	open NUMERIC(10,2) NULL,
	close NUMERIC(10,2) NULL,
	high NUMERIC(10,2) NULL,
	low NUMERIC(10,2) NULL,
	average NUMERIC(10,2) NULL,
	turnover NUMERIC(15) NULL,
	PRIMARY KEY (date),
	UNIQUE UQ_MarketIndex_date(date)
) 
;


