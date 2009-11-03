USE stock;
DROP TABLE IF EXISTS Transaction_Record;
CREATE TABLE Transaction_Record
(
	code CHAR(10) NOT NULL,
	date DATE NOT NULL,
	lastClose NUMERIC(10,2) NULL,
	open NUMERIC(10,2) NULL,
	close NUMERIC(10,2) NULL,
	high NUMERIC(10,2) NULL,
	low NUMERIC(10,2) NULL,
	average NUMERIC(10,2) NULL,
	changePrice NUMERIC(10,2) NULL,
	changePct NUMERIC(10,2) NULL,
	turnover NUMERIC(10) NULL,
	lastTurnover NUMERIC(10) NULL,
	turnoverPct NUMERIC(10,2) NULL,
	circulatingValue NUMERIC(10,2) NULL,
	aggregateValue NUMERIC(10,2) NULL,
	commissionRate NUMERIC(10,2) NULL,
	PRIMARY KEY (code, date)
) ;


