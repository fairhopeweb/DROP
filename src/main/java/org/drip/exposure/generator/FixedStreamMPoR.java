
package org.drip.exposure.generator;

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
 * <i>FixedStreamMPoR</i> estimates the MPoR Variation Margin and the Trade Payments for the given Fixed
 * Coupon Stream off of the Realized Market Path. The References are:
 *  
 * <br><br>
 *  	<ul>
 *  		<li>
 *  			Andersen, L. B. G., M. Pykhtin, and A. Sokol (2017): Re-thinking Margin Period of Risk
 *  				https://papers.ssrn.com/sol3/papers.cfm?abstract_id=2902737 <b>eSSRN</b>
 *  		</li>
 *  		<li>
 *  			Andersen, L. B. G., M. Pykhtin, and A. Sokol (2017): Credit Exposure in the Presence of
 *  				Initial Margin https://papers.ssrn.com/sol3/papers.cfm?abstract_id=2806156 <b>eSSRN</b>
 *  		</li>
 *  		<li>
 *  			Albanese, C., and L. Andersen (2014): Accounting for OTC Derivatives: Funding Adjustments and
 *  				the Re-Hypothecation Option https://papers.ssrn.com/sol3/papers.cfm?abstract_id=2482955
 *  				<b>eSSRN</b>
 *  		</li>
 *  		<li>
 *  			Burgard, C., and M. Kjaer (2017): Derivatives Funding, Netting, and Accounting
 *  				https://papers.ssrn.com/sol3/papers.cfm?abstract_id=2534011 <b>eSSRN</b>
 *  		</li>
 *  		<li>
 *  			Piterbarg, V. (2010): Funding Beyond Discounting: Collateral Agreements and Derivatives
 *  				Pricing <i>Risk</i> <b>21 (2)</b> 97-102
 *  		</li>
 *  		<li>
 *  	</ul>
 *
 *	<br><br>
 *  <ul>
 *		<li><b>Module </b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/PortfolioCore.md">Portfolio Core Module</a></li>
 *		<li><b>Library</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/ExposureAnalyticsLibrary.md">Exposure Analytics</a></li>
 *		<li><b>Project</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/src/main/java/org/drip/exposure/README.md">Exposure Group Level Collateralized/Uncollateralized Exposure</a></li>
 *		<li><b>Package</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/src/main/java/org/drip/exposure/generator/README.md">Rates Stream Margin Period Exposure</a></li>
 *  </ul>
 * 
 * @author Lakshmi Krishnamurthy
 */

public class FixedStreamMPoR extends org.drip.exposure.generator.StreamMPoR
{

	/**
	 * FixedStreamMPoR Constructor
	 * 
	 * @param stream The Fixed Coupon Stream Instance
	 * @param notional The Fixed Coupon Stream Notional
	 * 
	 * @throws java.lang.Exception Thrown if the Inputs are Invalid
	 */

	public FixedStreamMPoR (
		final org.drip.product.rates.Stream stream,
		final double notional)
		throws java.lang.Exception
	{
		super (
			stream,
			notional
		);
	}

	@Override public double variationMarginEstimate (
		final int forwardDate,
		final org.drip.exposure.universe.MarketPath marketPath)
		throws java.lang.Exception
	{
		if (null == marketPath)
		{
			throw new java.lang.Exception ("FixedStreamMPoR::variationMarginEstimate => Invalid Inputs");
		}

		double variationMarginEstimate = 0.;

		double overnightReplicatorForward = marketPath.marketVertex (forwardDate).overnightReplicator();

		for (org.drip.analytics.cashflow.CompositePeriod period : stream().periods())
		{
			int periodEndDate = period.endDate();

			if (periodEndDate < forwardDate)
			{
				continue;
			}

			org.drip.analytics.output.CompositePeriodCouponMetrics compositePeriodCouponMetrics =
				period.couponMetrics (
					forwardDate,
					null
				);

			if (null == compositePeriodCouponMetrics)
			{
				throw new java.lang.Exception
					("FixedStreamMPoR::variationMarginEstimate => Cannot Compute CompositePeriodCouponMetrics");
			}

			variationMarginEstimate += period.couponDCF() *
				period.notional (periodEndDate) *
				compositePeriodCouponMetrics.rate() *
				period.couponFactor (periodEndDate) *
				overnightReplicatorForward /
				marketPath.marketVertex (period.payDate()).overnightReplicator();
		}

		return variationMarginEstimate * notional();
	}

	@Override public org.drip.exposure.mpor.TradePayment tradePayment (
		final int forwardDate,
		final org.drip.exposure.universe.MarketPath marketPath)
	{
		if (null == marketPath)
		{
			return null;
		}

		for (org.drip.analytics.cashflow.CompositePeriod period : stream().periods())
		{
			int periodPayDate = period.payDate();

			if (periodPayDate == forwardDate)
			{
				int periodEndDate = period.endDate();

				org.drip.analytics.output.CompositePeriodCouponMetrics compositePeriodCouponMetrics =
					period.couponMetrics (
						periodEndDate,
						null
					);

				if (null == compositePeriodCouponMetrics)
				{
					return null;
				}

				try
				{
					return org.drip.exposure.mpor.TradePayment.Standard (
						notional() * period.couponDCF() *
						period.notional (periodEndDate) *
						compositePeriodCouponMetrics.rate() *
						period.couponFactor (periodEndDate)
					);
				}
				catch (java.lang.Exception e)
				{
					e.printStackTrace();

					return null;
				}
			}
		}

		return org.drip.exposure.mpor.TradePayment.Standard (0.);
	}

	@Override public org.drip.exposure.mpor.TradePayment[] denseTradePaymentArray (
		final int startDate,
		final int endDate,
		final org.drip.exposure.universe.MarketPath marketPath)
	{
		if (endDate < startDate ||
			null == marketPath)
		{
			return null;
		}

		int denseDateCount = endDate - startDate + 1;
		org.drip.exposure.mpor.TradePayment[] denseTradePaymentArray = new
			org.drip.exposure.mpor.TradePayment[denseDateCount];

		for (org.drip.analytics.cashflow.CompositePeriod period : stream().periods())
		{
			int periodPayDate = period.payDate();

			if (periodPayDate < startDate)
			{
				continue;
			}

			if (periodPayDate > endDate)
			{
				break;
			}

			int periodEndDate = period.endDate();

			org.drip.analytics.output.CompositePeriodCouponMetrics compositePeriodCouponMetrics =
				period.couponMetrics (
					periodEndDate,
					null
				);

			if (null == compositePeriodCouponMetrics)
			{
				return null;
			}

			try
			{
				denseTradePaymentArray[periodPayDate - startDate] =
					org.drip.exposure.mpor.TradePayment.Standard (
						notional() * period.couponDCF() *
						period.notional (periodEndDate) *
						compositePeriodCouponMetrics.rate() *
						period.couponFactor (periodEndDate)
					);
			}
			catch (java.lang.Exception e)
			{
				e.printStackTrace();

				return null;
			}
		}

		org.drip.exposure.mpor.TradePayment zeroTradePayment = org.drip.exposure.mpor.TradePayment.Standard
			(0.);

		for (int denseDateIndex = 0; denseDateIndex < denseDateCount; ++denseDateIndex)
		{
			if (null == denseTradePaymentArray[denseDateIndex])
			{
				denseTradePaymentArray[denseDateIndex] = zeroTradePayment;
			}
		}

		return denseTradePaymentArray;
	}
}
