USE stock;
DROP TABLE IF EXISTS Financial_Report;
CREATE TABLE Financial_Report
(
	code CHAR(10) NOT NULL,
	fiscal_season DATE NOT NULL,
	primaryEPS NUMERIC(16,2) NULL,
	fullyDilutedEPS NUMERIC(16,2) NULL,
	weightedAverageEPSWithoutNonrecureGnL NUMERIC(16,2) NULL,
	netAssetsValuePS NUMERIC(16,2) NULL,
	adjustedNetAssetsValuePS NUMERIC(16,2) NULL,
	netCashFlowPS NUMERIC(16,2) NULL,
	dilutedReturnOnNetAssets NUMERIC(16,2) NULL,
	weightedReturnOnNetAssets NUMERIC(16,2) NULL,
	primaryBusinessIncome NUMERIC(16,2) NULL,
	primaryBusinessProfit NUMERIC(16,2) NULL,
	tradeProfit NUMERIC(16,2) NULL,
	yield NUMERIC(16,2) NULL,
	netNonrevenueReceipt NUMERIC(16,2) NULL,
	grossBenefit NUMERIC(16,2) NULL,
	retainedProfit NUMERIC(16,2) NULL,
	retainedProfitWithoutNonrecureGnL NUMERIC(16,2) NULL,
	netCashFlowOnTrade NUMERIC(16,2) NULL,
	netCashIncrease NUMERIC(16,2) NULL,
	liquidAsset NUMERIC(16,2) NULL,
	liquidLiability NUMERIC(16,2) NULL,
	totalAssets NUMERIC(16,2) NULL,
	shareholderEquityWithoutMinority NUMERIC(16,2) NULL,
	PRIMARY KEY (code, fiscal_season)
) ;


