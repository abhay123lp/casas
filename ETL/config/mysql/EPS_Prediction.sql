USE stock;
DROP TABLE IF EXISTS EPS_Prediction;
CREATE TABLE EPS_Prediction
(
	code CHAR(10) NOT NULL,
	year INTEGER NOT NULL,
	name CHAR(50) NULL,
	eps NUMERIC(10,2) NULL,
	PRIMARY KEY (code, year)
) ;


