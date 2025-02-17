
package org.drip.simm.parameters;

/*
 * -*- mode: java; tab-width: 4; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 */

/*!
 * Copyright (C) 2022 Lakshmi Krishnamurthy
 * Copyright (C) 2021 Lakshmi Krishnamurthy
 * Copyright (C) 2020 Lakshmi Krishnamurthy
 * Copyright (C) 2019 Lakshmi Krishnamurthy
 * Copyright (C) 2018 Lakshmi Krishnamurthy
 * 
 *  This file is part of DROP, an open-source library targeting analytics/risk, transaction cost analytics,
 *  	asset liability management analytics, capital, exposure, and margin analytics, valuation adjustment
 *  	analytics, and portfolio construction analytics within and across fixed income, credit, commodity,
 *  	equity, FX, and structured products. It also includes auxiliary libraries for algorithm support,
 *  	numerical analysis, numerical optimization, spline builder, model validation, statistical learning,
 *  	graph builder/navigator, and computational support.
 *  
 *  	https://lakshmidrip.github.io/DROP/
 *  
 *  DROP is composed of three modules:
 *  
 *  - DROP Product Core - https://lakshmidrip.github.io/DROP-Product-Core/
 *  - DROP Portfolio Core - https://lakshmidrip.github.io/DROP-Portfolio-Core/
 *  - DROP Computational Core - https://lakshmidrip.github.io/DROP-Computational-Core/
 * 
 * 	DROP Product Core implements libraries for the following:
 * 	- Fixed Income Analytics
 * 	- Loan Analytics
 * 	- Transaction Cost Analytics
 * 
 * 	DROP Portfolio Core implements libraries for the following:
 * 	- Asset Allocation Analytics
 *  - Asset Liability Management Analytics
 * 	- Capital Estimation Analytics
 * 	- Exposure Analytics
 * 	- Margin Analytics
 * 	- XVA Analytics
 * 
 * 	DROP Computational Core implements libraries for the following:
 * 	- Algorithm Support
 * 	- Computation Support
 * 	- Function Analysis
 *  - Graph Algorithm
 *  - Model Validation
 * 	- Numerical Analysis
 * 	- Numerical Optimizer
 * 	- Spline Builder
 *  - Statistical Learning
 * 
 * 	Documentation for DROP is Spread Over:
 * 
 * 	- Main                     => https://lakshmidrip.github.io/DROP/
 * 	- Wiki                     => https://github.com/lakshmiDRIP/DROP/wiki
 * 	- GitHub                   => https://github.com/lakshmiDRIP/DROP
 * 	- Repo Layout Taxonomy     => https://github.com/lakshmiDRIP/DROP/blob/master/Taxonomy.md
 * 	- Javadoc                  => https://lakshmidrip.github.io/DROP/Javadoc/index.html
 * 	- Technical Specifications => https://github.com/lakshmiDRIP/DROP/tree/master/Docs/Internal
 * 	- Release Versions         => https://lakshmidrip.github.io/DROP/version.html
 * 	- Community Credits        => https://lakshmidrip.github.io/DROP/credits.html
 * 	- Issues Catalog           => https://github.com/lakshmiDRIP/DROP/issues
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *   	you may not use this file except in compliance with the License.
 *   
 *  You may obtain a copy of the License at
 *  	http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  	distributed under the License is distributed on an "AS IS" BASIS,
 *  	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  
 *  See the License for the specific language governing permissions and
 *  	limitations under the License.
 */

/**
 * <i>BucketVegaSettings</i> holds the Settings that govern the Generation of the ISDA SIMM Single Bucket
 * Vega Sensitivities. The References are:
 * 
 * <br><br>
 *  <ul>
 *  	<li>
 *  		Andersen, L. B. G., M. Pykhtin, and A. Sokol (2017): Credit Exposure in the Presence of Initial
 *  			Margin https://papers.ssrn.com/sol3/papers.cfm?abstract_id=2806156 <b>eSSRN</b>
 *  	</li>
 *  	<li>
 *  		Albanese, C., S. Caenazzo, and O. Frankel (2017): Regression Sensitivities for Initial Margin
 *  			Calculations https://papers.ssrn.com/sol3/papers.cfm?abstract_id=2763488 <b>eSSRN</b>
 *  	</li>
 *  	<li>
 *  		Anfuso, F., D. Aziz, P. Giltinan, and K. Loukopoulus (2017): A Sound Modeling and Back-testing
 *  			Framework for Forecasting Initial Margin Requirements
 *  				https://papers.ssrn.com/sol3/papers.cfm?abstract_id=2716279 <b>eSSRN</b>
 *  	</li>
 *  	<li>
 *  		Caspers, P., P. Giltinan, R. Lichters, and N. Nowaczyk (2017): Forecasting Initial Margin
 *  			Requirements - A Model Evaluation https://papers.ssrn.com/sol3/papers.cfm?abstract_id=2911167
 *  				<b>eSSRN</b>
 *  	</li>
 *  	<li>
 *  		International Swaps and Derivatives Association (2017): SIMM v2.0 Methodology
 *  			https://www.isda.org/a/oFiDE/isda-simm-v2.pdf
 *  	</li>
 *  </ul>
 * 
 * <br><br>
 *  <ul>
 *		<li><b>Module </b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/PortfolioCore.md">Portfolio Core Module</a></li>
 *		<li><b>Library</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/MarginAnalyticsLibrary.md">Initial and Variation Margin Analytics</a></li>
 *		<li><b>Project</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/src/main/java/org/drip/simm/README.md">Initial Margin Analytics based on ISDA SIMM and its Variants</a></li>
 *		<li><b>Package</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/src/main/java/org/drip/simm/parameters/README.md">ISDA SIMM Risk Factor Parameters</a></li>
 *  </ul>
 * <br><br>
 * 
 * @author Lakshmi Krishnamurthy
 */

public class BucketVegaSettings extends org.drip.simm.parameters.BucketSensitivitySettings
{
	private double _impliedVolatility = java.lang.Double.NaN;
	private double _historicalVolatilityRatio = java.lang.Double.NaN;

	/**
	 * Retrieve the ISDA 2.0 Equity Vega Settings
	 * 
	 * @param bucketIndex The Bucket Index
	 * 
	 * @return The ISDA 2.0 Equity Vega Settings
	 */

	public static BucketVegaSettings ISDA_EQ_20 (
		final int bucketIndex)
	{
		org.drip.simm.equity.EQBucket equityBucket =
			org.drip.simm.equity.EQSettingsContainer20.BucketMap().get (bucketIndex);

		if (null == equityBucket)
		{
			return null;
		}

		try
		{
			return new BucketVegaSettings (
				equityBucket.vegaRiskWeight() * equityBucket.deltaRiskWeight(),
				org.drip.simm.equity.EQRiskThresholdContainer20.DeltaVegaThresholdMap().get
					(bucketIndex).vega(),
				equityBucket.memberCorrelation(),
				java.lang.Math.sqrt (365. / 14.) /
					org.drip.measure.gaussian.NormalQuadrature.InverseCDF (0.99),
				org.drip.simm.equity.EQSystemics20.HISTORICAL_VOLATILITY_RATIO
			);
		}
		catch (java.lang.Exception e)
		{
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Retrieve the ISDA 2.1 Equity Vega Settings
	 * 
	 * @param bucketIndex The Bucket Index
	 * 
	 * @return The ISDA 2.1 Equity Vega Settings
	 */

	public static BucketVegaSettings ISDA_EQ_21 (
		final int bucketIndex)
	{
		org.drip.simm.equity.EQBucket equityBucket =
			org.drip.simm.equity.EQSettingsContainer21.BucketMap().get (bucketIndex);

		if (null == equityBucket)
		{
			return null;
		}

		try
		{
			return new BucketVegaSettings (
				equityBucket.vegaRiskWeight() * equityBucket.deltaRiskWeight(),
				org.drip.simm.equity.EQRiskThresholdContainer21.DeltaVegaThresholdMap().get
					(bucketIndex).vega(),
				equityBucket.memberCorrelation(),
				java.lang.Math.sqrt (365. / 14.) /
					org.drip.measure.gaussian.NormalQuadrature.InverseCDF (0.99),
				org.drip.simm.equity.EQSystemics21.HISTORICAL_VOLATILITY_RATIO
			);
		}
		catch (java.lang.Exception e)
		{
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Construct the Standard ISDA 2.0 Commodity Vega Settings for the specified Bucket
	 * 
	 * @param bucketIndex The Bucket Index
	 * 
	 * @return The Standard ISDA 2.0 Commodity Vega Settings for the specified Bucket
	 */

	public static BucketVegaSettings ISDA_CT_20 (
		final int bucketIndex)
	{
		org.drip.simm.commodity.CTBucket commodityBucket =
			org.drip.simm.commodity.CTSettingsContainer20.BucketMap().get (bucketIndex);

		if (null == commodityBucket)
		{
			return null;
		}

		try
		{
			return new org.drip.simm.parameters.BucketVegaSettings (
				org.drip.simm.commodity.CTSystemics20.VEGA_RISK_WEIGHT * commodityBucket.deltaRiskWeight(),
				org.drip.simm.commodity.CTRiskThresholdContainer20.DeltaVegaThresholdMap().get
					(bucketIndex).vega(),
				commodityBucket.memberCorrelation(),
				java.lang.Math.sqrt (365. / 14.) /
					org.drip.measure.gaussian.NormalQuadrature.InverseCDF (0.99),
				org.drip.simm.commodity.CTSystemics20.HISTORICAL_VOLATILITY_RATIO
			);
		}
		catch (java.lang.Exception e)
		{
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Construct the Standard ISDA 2.1 Commodity Vega Settings for the specified Bucket
	 * 
	 * @param bucketIndex The Bucket Index
	 * 
	 * @return The Standard ISDA 2.1 Commodity Vega Settings for the specified Bucket
	 */

	public static BucketVegaSettings ISDA_CT_21 (
		final int bucketIndex)
	{
		org.drip.simm.commodity.CTBucket commodityBucket =
			org.drip.simm.commodity.CTSettingsContainer21.BucketMap().get (bucketIndex);

		if (null == commodityBucket)
		{
			return null;
		}

		try
		{
			return new org.drip.simm.parameters.BucketVegaSettings (
				org.drip.simm.commodity.CTSystemics21.VEGA_RISK_WEIGHT * commodityBucket.deltaRiskWeight(),
				org.drip.simm.commodity.CTRiskThresholdContainer21.DeltaVegaThresholdMap().get
					(bucketIndex).vega(),
				commodityBucket.memberCorrelation(),
				java.lang.Math.sqrt (365. / 14.) /
					org.drip.measure.gaussian.NormalQuadrature.InverseCDF (0.99),
				org.drip.simm.commodity.CTSystemics21.HISTORICAL_VOLATILITY_RATIO
			);
		}
		catch (java.lang.Exception e)
		{
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Construct the Standard ISDA 2.0 Bucket FX Settings
	 * 
	 * @param vegaCategory The Vega Category
	 * 
	 * @return The Standard ISDA 2.0 Bucket FX Settings
	 */

	public static BucketVegaSettings ISDA_FX_20 (
		final java.lang.String vegaCategory)
	{
		java.util.Map<java.lang.String, java.lang.Double> fxConcentrationCategoryVegaMap =
			org.drip.simm.fx.FXRiskThresholdContainer20.CategoryVegaMap();

		try
		{
			return fxConcentrationCategoryVegaMap.containsKey (vegaCategory) ?
				new org.drip.simm.parameters.BucketVegaSettings (
					org.drip.simm.fx.FXSystemics20.VEGA_RISK_WEIGHT *
						org.drip.simm.fx.FXSystemics20.DELTA_RISK_WEIGHT,
					fxConcentrationCategoryVegaMap.get (vegaCategory),
					org.drip.simm.fx.FXSystemics20.CORRELATION,
					java.lang.Math.sqrt (365. / 14.) /
						org.drip.measure.gaussian.NormalQuadrature.InverseCDF (0.99),
					org.drip.simm.fx.FXSystemics20.HISTORICAL_VOLATILITY_RATIO
				) : null;
		}
		catch (java.lang.Exception e)
		{
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Construct the Standard ISDA 2.1 Bucket FX Settings
	 * 
	 * @param vegaCategory The Vega Category
	 * 
	 * @return The Standard ISDA 2.1 Bucket FX Settings
	 */

	public static BucketVegaSettings ISDA_FX_21 (
		final java.lang.String vegaCategory)
	{
		java.util.Map<java.lang.String, java.lang.Double> fxConcentrationCategoryVegaMap =
			org.drip.simm.fx.FXRiskThresholdContainer21.CategoryVegaMap();

		try
		{
			return fxConcentrationCategoryVegaMap.containsKey (vegaCategory) ?
				new org.drip.simm.parameters.BucketVegaSettings (
					org.drip.simm.fx.FXSystemics21.VEGA_RISK_WEIGHT *
						org.drip.simm.fx.FXSystemics21.DELTA_RISK_WEIGHT,
					fxConcentrationCategoryVegaMap.get (vegaCategory),
					org.drip.simm.fx.FXSystemics21.CORRELATION,
					java.lang.Math.sqrt (365. / 14.) /
						org.drip.measure.gaussian.NormalQuadrature.InverseCDF (0.99),
					org.drip.simm.fx.FXSystemics21.HISTORICAL_VOLATILITY_RATIO
				) : null;
		}
		catch (java.lang.Exception e)
		{
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * BucketVegaSettings Constructor
	 * 
	 * @param riskWeight The Vega Risk Weight
	 * @param concentrationFactor The Concentration Factor
	 * @param memberCorrelation The Member Correlation
	 * @param impliedVolatility The Implied Volatility
	 * @param historicalVolatilityRatio The Historical Volatility Ratio
	 * 
	 * @throws java.lang.Exception Thrown if the Inputs are Invalid
	 */

	public BucketVegaSettings (
		final double riskWeight,
		final double concentrationFactor,
		final double memberCorrelation,
		final double impliedVolatility,
		final double historicalVolatilityRatio)
		throws java.lang.Exception
	{
		super (
			riskWeight,
			concentrationFactor,
			memberCorrelation
		);

		if (!org.drip.numerical.common.NumberUtil.IsValid (_impliedVolatility = impliedVolatility) ||
				0. > _impliedVolatility ||
			!org.drip.numerical.common.NumberUtil.IsValid (_historicalVolatilityRatio =
				historicalVolatilityRatio) || 0. > _historicalVolatilityRatio)
		{
			throw new java.lang.Exception ("BucketVegaSettings Constructor => Invalid Inputs");
		}
	}

	/**
	 * Retrieve the Implied Volatility
	 * 
	 * @return The Implied Volatility
	 */

	public double impliedVolatility()
	{
		return _impliedVolatility;
	}

	/**
	 * Retrieve the Historical Volatility Ratio
	 * 
	 * @return The Historical Volatility Ratio
	 */

	public double historicalVolatilityRatio()
	{
		return _historicalVolatilityRatio;
	}

	/**
	 * Retrieve the Raw Vega Risk Weight
	 * 
	 * @return The Raw Vega Risk Weight
	 */

	public double rawRiskWeight()
	{
		return super.riskWeight();
	}

	@Override public double riskWeight()
	{
		return super.riskWeight() * _impliedVolatility;
	}
}
