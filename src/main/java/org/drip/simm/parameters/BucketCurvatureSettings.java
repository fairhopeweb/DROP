
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
 * <i>BucketCurvatureSettings</i> holds the ISDA SIMM Curvature Settings for Interest Rates, Qualifying and
 * Non-qualifying Credit, Equity, Commodity, and Foreign Exchange. The References are:
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

public class BucketCurvatureSettings extends org.drip.simm.parameters.BucketVegaSettings
{
	private double _tenorScalingFactor = java.lang.Double.NaN;

	/**
	 * Construct the ISDA Standard BucketCurvatureSettings
	 * 
	 * @param riskWeight The Vega Risk Weight
	 * @param memberCorrelation The Member Correlation
	 * @param impliedVolatility The Implied Volatility
	 * @param vegaDurationDays The Bucket Vega Duration in Days
	 * 
	 * @return The ISDA Standard BucketCurvatureSettings
	 */

	public static final BucketCurvatureSettings ISDA (
		final double riskWeight,
		final double memberCorrelation,
		final double impliedVolatility,
		final int vegaDurationDays)
	{
		try
		{
			return new BucketCurvatureSettings (
				riskWeight,
				memberCorrelation,
				impliedVolatility,
				org.drip.function.r1tor1.ISDABucketCurvatureTenorScaler.Standard().evaluate
					(vegaDurationDays)
			);
		}
		catch (java.lang.Exception e)
		{
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Construct the Standard ISDA 2.0 EQ Bucket Curvature Settings
	 * 
	 * @param bucketIndex The Bucket Index
	 * @param vegaDurationDays The Vega Duration Days
	 * 
	 * @return The Standard ISDA 2.0 EQ Bucket Curvature Settings
	 */

	public static BucketCurvatureSettings ISDA_EQ_20 (
		final int bucketIndex,
		final int vegaDurationDays)
	{
		org.drip.simm.equity.EQBucket equityBucket =
			org.drip.simm.equity.EQSettingsContainer20.BucketMap().get (bucketIndex);

		try
		{
			return null == equityBucket ? null : BucketCurvatureSettings.ISDA (
				equityBucket.vegaRiskWeight() * equityBucket.deltaRiskWeight(),
				equityBucket.memberCorrelation(),
				java.lang.Math.sqrt (365. / 14.) /
					org.drip.measure.gaussian.NormalQuadrature.InverseCDF (0.99),
				vegaDurationDays
			);
		}
		catch (java.lang.Exception e)
		{
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Construct the Standard ISDA 2.1 EQ Bucket Curvature Settings
	 * 
	 * @param bucketIndex The Bucket Index
	 * @param vegaDurationDays The Vega Duration Days
	 * 
	 * @return The Standard ISDA 2.1 EQ Bucket Curvature Settings
	 */

	public static BucketCurvatureSettings ISDA_EQ_21 (
		final int bucketIndex,
		final int vegaDurationDays)
	{
		org.drip.simm.equity.EQBucket equityBucket =
			org.drip.simm.equity.EQSettingsContainer21.BucketMap().get (bucketIndex);

		try
		{
			return null == equityBucket ? null : BucketCurvatureSettings.ISDA (
				equityBucket.vegaRiskWeight() * equityBucket.deltaRiskWeight(),
				equityBucket.memberCorrelation(),
				java.lang.Math.sqrt (365. / 14.) /
					org.drip.measure.gaussian.NormalQuadrature.InverseCDF (0.99),
				vegaDurationDays
			);
		}
		catch (java.lang.Exception e)
		{
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Construct the Standard ISDA 2.0 CT Bucket Curvature Settings
	 * 
	 * @param bucketIndex The Bucket Index
	 * @param vegaDurationDays The Vega Duration Days
	 * 
	 * @return The Standard ISDA 2.0 CT Bucket Curvature Settings
	 */

	public static BucketCurvatureSettings ISDA_CT_20 (
		final int bucketIndex,
		final int vegaDurationDays)
	{
		org.drip.simm.commodity.CTBucket commodityBucket =
			org.drip.simm.commodity.CTSettingsContainer20.BucketMap().get (bucketIndex);

		try
		{
			return null == commodityBucket ? null : BucketCurvatureSettings.ISDA (
				org.drip.simm.commodity.CTSystemics20.VEGA_RISK_WEIGHT * commodityBucket.deltaRiskWeight(),
				commodityBucket.memberCorrelation(),
				java.lang.Math.sqrt (365. / 14.) /
					org.drip.measure.gaussian.NormalQuadrature.InverseCDF (0.99),
				vegaDurationDays
			);
		}
		catch (java.lang.Exception e)
		{
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Construct the Standard ISDA 2.1 CT Bucket Curvature Settings
	 * 
	 * @param bucketIndex The Bucket Index
	 * @param vegaDurationDays The Vega Duration Days
	 * 
	 * @return The Standard ISDA 2.1 CT Bucket Curvature Settings
	 */

	public static BucketCurvatureSettings ISDA_CT_21 (
		final int bucketIndex,
		final int vegaDurationDays)
	{
		org.drip.simm.commodity.CTBucket commodityBucket =
			org.drip.simm.commodity.CTSettingsContainer21.BucketMap().get (bucketIndex);

		try
		{
			return null == commodityBucket ? null : BucketCurvatureSettings.ISDA (
				org.drip.simm.commodity.CTSystemics21.VEGA_RISK_WEIGHT * commodityBucket.deltaRiskWeight(),
				commodityBucket.memberCorrelation(),
				java.lang.Math.sqrt (365. / 14.) /
					org.drip.measure.gaussian.NormalQuadrature.InverseCDF (0.99),
				vegaDurationDays
			);
		}
		catch (java.lang.Exception e)
		{
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Construct the Standard ISDA 2.0 FX Bucket Curvature Settings
	 * 
	 * @param vegaCategory The Vega Category
	 * @param vegaDurationDays The Vega Duration Days
	 * 
	 * @return The Standard ISDA 2.0 FX Bucket Curvature Settings
	 */

	public static BucketCurvatureSettings ISDA_FX_20 (
		final java.lang.String vegaCategory,
		final int vegaDurationDays)
	{
		java.util.Map<java.lang.String, java.lang.Double> fxConcentrationCategoryVegaMap =
			org.drip.simm.fx.FXRiskThresholdContainer20.CategoryVegaMap();

		try {
			return !fxConcentrationCategoryVegaMap.containsKey (vegaCategory) ? null :
				BucketCurvatureSettings.ISDA (
					org.drip.simm.fx.FXSystemics20.VEGA_RISK_WEIGHT *
						org.drip.simm.fx.FXSystemics20.DELTA_RISK_WEIGHT,
					org.drip.simm.fx.FXSystemics20.CORRELATION,
					java.lang.Math.sqrt (365. / 14.) /
						org.drip.measure.gaussian.NormalQuadrature.InverseCDF (0.99),
					vegaDurationDays
				);
		}
		catch (java.lang.Exception e)
		{
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Construct the Standard ISDA 2.1 FX Bucket Curvature Settings
	 * 
	 * @param vegaCategory The Vega Category
	 * @param vegaDurationDays The Vega Duration Days
	 * 
	 * @return The Standard ISDA 2.1 FX Bucket Curvature Settings
	 */

	public static BucketCurvatureSettings ISDA_FX_21 (
		final java.lang.String vegaCategory,
		final int vegaDurationDays)
	{
		java.util.Map<java.lang.String, java.lang.Double> fxConcentrationCategoryVegaMap =
			org.drip.simm.fx.FXRiskThresholdContainer21.CategoryVegaMap();

		try {
			return !fxConcentrationCategoryVegaMap.containsKey (vegaCategory) ? null :
				BucketCurvatureSettings.ISDA (
					org.drip.simm.fx.FXSystemics21.VEGA_RISK_WEIGHT *
						org.drip.simm.fx.FXSystemics21.DELTA_RISK_WEIGHT,
					org.drip.simm.fx.FXSystemics21.CORRELATION,
					java.lang.Math.sqrt (365. / 14.) /
						org.drip.measure.gaussian.NormalQuadrature.InverseCDF (0.99),
					vegaDurationDays
				);
		}
		catch (java.lang.Exception e)
		{
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * BucketCurvatureSettings Constructor
	 * 
	 * @param riskWeight The Vega Risk Weight
	 * @param memberCorrelation The Member Correlation
	 * @param impliedVolatility The Implied Volatility
	 * @param tenorScalingFactor The Tenor Scaling Factor
	 * 
	 * @throws java.lang.Exception Thrown if the Inputs are Invalid
	 */

	public BucketCurvatureSettings (
		final double riskWeight,
		final double memberCorrelation,
		final double impliedVolatility,
		final double tenorScalingFactor)
		throws java.lang.Exception
	{
		super (
			riskWeight,
			1.,
			memberCorrelation,
			impliedVolatility,
			1.
		);

		if (!org.drip.numerical.common.NumberUtil.IsValid (_tenorScalingFactor = tenorScalingFactor))
		{
			throw new java.lang.Exception ("BucketCurvatureSettings Constructor => Invalid Inputs");
		}
	}

	/**
	 * Retrieve the Tenor Scaling Factor
	 * 
	 * @return The Tenor Scaling Factor
	 */

	public double tenorScalingFactor()
	{
		return _tenorScalingFactor;
	}

	/**
	 * Retrieve the Vega Risk Weight
	 * 
	 * @return The Vega Risk Weight
	 */

	public double vegaRiskWeight()
	{
		return super.riskWeight();
	}

	@Override public double riskWeight()
	{
		return super.riskWeight() * _tenorScalingFactor;
	}
}
