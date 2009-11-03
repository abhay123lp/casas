USE stock;
DROP TABLE IF EXISTS Target_Price;
CREATE TABLE Target_Price
(
	code CHAR(10) NULL,
	name CHAR(50) NULL,
	date DATE NULL,
	targetPrice NUMERIC(10,2) NULL
) ;


