
package org.drip.state.creator;

/*
 * -*- mode: java; tab-width: 4; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 */

/*!
 * Copyright (C) 2022 Lakshmi Krishnamurthy
 * Copyright (C) 2021 Lakshmi Krishnamurthy
 * Copyright (C) 2020 Lakshmi Krishnamurthy
 * Copyright (C) 2019 Lakshmi Krishnamurthy
 * Copyright (C) 2018 Lakshmi Krishnamurthy
 * Copyright (C) 2017 Lakshmi Krishnamurthy
 * Copyright (C) 2016 Lakshmi Krishnamurthy
 * Copyright (C) 2015 Lakshmi Krishnamurthy
 * Copyright (C) 2014 Lakshmi Krishnamurthy
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
 * <i>ScenarioBasisCurveBuilder</i> implements the construction of the scenario basis curve using the input
 * instruments and their quotes.
 *
 *  <br><br>
 *  <ul>
 *		<li><b>Module </b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/ProductCore.md">Product Core Module</a></li>
 *		<li><b>Library</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/FixedIncomeAnalyticsLibrary.md">Fixed Income Analytics</a></li>
 *		<li><b>Project</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/src/main/java/org/drip/state/README.md">Latent State Inference and Creation Utilities</a></li>
 *		<li><b>Package</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/src/main/java/org/drip/state/creator/README.md">Scenario State Curve/Surface Builders</a></li>
 *  </ul>
 * <br><br>
 * 
 * @author Lakshmi Krishnamurthy
 */

public class ScenarioBasisCurveBuilder {

	/**
	 * Create an Instance of the Custom Splined Basis Curve
	 * 
	 * @param strName Curve Name
	 * @param dtSpot The Spot Date
	 * @param friReference Reference Leg FRI
	 * @param friDerived Derived Leg FRI
	 * @param bBasisOnReference TRUE - The Basis Quote is on the Reference Leg
	 * @param astrTenor Array of the Tenors
	 * @param adblBasis Array of the Basis Spreads
	 * @param scbc The Segment Custom Builder Control
	 * 
	 * @return The Instance of the Basis Curve
	 */

	public static final org.drip.state.basis.BasisCurve CustomSplineBasisCurve (
		final java.lang.String strName,
		final org.drip.analytics.date.JulianDate dtSpot,
		final org.drip.state.identifier.ForwardLabel friReference,
		final org.drip.state.identifier.ForwardLabel friDerived,
		final boolean bBasisOnReference,
		final java.lang.String[] astrTenor,
		final double[] adblBasis,
		final org.drip.spline.params.SegmentCustomBuilderControl scbc)
	{
		if (null == strName || null == dtSpot || strName.isEmpty() || null == astrTenor || null == adblBasis)
			return null;

		int iNumTenor = astrTenor.length;
		int[] aiBasisPredictorOrdinate = new int[iNumTenor + 1];
		double[] adblBasisResponseValue = new double[iNumTenor + 1];
		org.drip.spline.params.SegmentCustomBuilderControl[] aSCBC = new
			org.drip.spline.params.SegmentCustomBuilderControl[iNumTenor];

		if (0 == iNumTenor || iNumTenor != adblBasis.length) return null;

		for (int i = 0; i <= iNumTenor; ++i) {
			if (0 != i) {
				java.lang.String strTenor = astrTenor[i - 1];

				if (null == strTenor || strTenor.isEmpty()) return null;

				org.drip.analytics.date.JulianDate dtMaturity = dtSpot.addTenor (strTenor);

				if (null == dtMaturity) return null;

				aiBasisPredictorOrdinate[i] = dtMaturity.julian();
			} else
				aiBasisPredictorOrdinate[i] = dtSpot.julian();

			adblBasisResponseValue[i] = 0 == i ? adblBasis[0] : adblBasis[i - 1];

			if (0 != i) aSCBC[i - 1] = scbc;
		}

		try {
			return new org.drip.state.curve.BasisSplineBasisCurve (friReference, friDerived,
				bBasisOnReference, new org.drip.spline.grid.OverlappingStretchSpan
					(org.drip.spline.stretch.MultiSegmentSequenceBuilder.CreateCalibratedStretchEstimator
						(strName, aiBasisPredictorOrdinate, adblBasisResponseValue, aSCBC, null,
							org.drip.spline.stretch.BoundarySettings.NaturalStandard(),
								org.drip.spline.stretch.MultiSegmentSequence.CALIBRATE)));
		} catch (java.lang.Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Create an Instance of the Cubic Polynomial Splined Basis Curve
	 * 
	 * @param strName Curve Name
	 * @param dtStart The Tenor Start Date
	 * @param friReference Reference Leg FRI
	 * @param friDerived Derived Leg FRI
	 * @param bBasisOnReference TRUE - The Basis Quote is on the Reference Leg
	 * @param astrTenor Array of the Tenors
	 * @param adblBasis Array of the Basis Spreads
	 * 
	 * @return The Instance of the Basis Curve
	 */

	public static final org.drip.state.basis.BasisCurve CubicPolynomialBasisCurve (
		final java.lang.String strName,
		final org.drip.analytics.date.JulianDate dtStart,
		final org.drip.state.identifier.ForwardLabel friReference,
		final org.drip.state.identifier.ForwardLabel friDerived,
		final boolean bBasisOnReference,
		final java.lang.String[] astrTenor,
		final double[] adblBasis)
	{
		try {
			return CustomSplineBasisCurve (strName, dtStart, friReference, friDerived, bBasisOnReference,
				astrTenor, adblBasis, new org.drip.spline.params.SegmentCustomBuilderControl
					(org.drip.spline.stretch.MultiSegmentSequenceBuilder.BASIS_SPLINE_POLYNOMIAL, new
						org.drip.spline.basis.PolynomialFunctionSetParams (4),
							org.drip.spline.params.SegmentInelasticDesignControl.Create (2, 2), null, null));
		} catch (java.lang.Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Create an Instance of the Quartic Polynomial Splined Basis Curve
	 * 
	 * @param strName Curve Name
	 * @param dtStart The Tenor Start Date
	 * @param friReference Reference Leg FRI
	 * @param friDerived Derived Leg FRI
	 * @param bBasisOnReference TRUE - The Basis Quote is on the Reference Leg
	 * @param astrTenor Array of the Tenors
	 * @param adblBasis Array of the Basis Spreads
	 * 
	 * @return The Instance of the Basis Curve
	 */

	public static final org.drip.state.basis.BasisCurve QuarticPolynomialBasisCurve (
		final java.lang.String strName,
		final org.drip.analytics.date.JulianDate dtStart,
		final org.drip.state.identifier.ForwardLabel friReference,
		final org.drip.state.identifier.ForwardLabel friDerived,
		final boolean bBasisOnReference,
		final java.lang.String[] astrTenor,
		final double[] adblBasis)
	{
		try {
			return CustomSplineBasisCurve (strName, dtStart, friReference, friDerived, bBasisOnReference,
				astrTenor, adblBasis, new org.drip.spline.params.SegmentCustomBuilderControl
					(org.drip.spline.stretch.MultiSegmentSequenceBuilder.BASIS_SPLINE_POLYNOMIAL, new
						org.drip.spline.basis.PolynomialFunctionSetParams (5),
							org.drip.spline.params.SegmentInelasticDesignControl.Create (2, 2), null, null));
		} catch (java.lang.Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Create an Instance of the Kaklis-Pandelis Splined Basis Curve
	 * 
	 * @param strName Curve Name
	 * @param dtStart The Tenor Start Date
	 * @param friReference Reference Leg FRI
	 * @param friDerived Derived Leg FRI
	 * @param bBasisOnReference TRUE - The Basis Quote is on the Reference Leg
	 * @param astrTenor Array of the Tenors
	 * @param adblBasis Array of the Basis Spreads
	 * 
	 * @return The Instance of the Basis Curve
	 */

	public static final org.drip.state.basis.BasisCurve KaklisPandelisBasisCurve (
		final java.lang.String strName,
		final org.drip.analytics.date.JulianDate dtStart,
		final org.drip.state.identifier.ForwardLabel friReference,
		final org.drip.state.identifier.ForwardLabel friDerived,
		final boolean bBasisOnReference,
		final java.lang.String[] astrTenor,
		final double[] adblBasis)
	{
		try {
			return CustomSplineBasisCurve (strName, dtStart, friReference, friDerived, bBasisOnReference,
				astrTenor, adblBasis, new org.drip.spline.params.SegmentCustomBuilderControl
					(org.drip.spline.stretch.MultiSegmentSequenceBuilder.BASIS_SPLINE_KAKLIS_PANDELIS,
						new org.drip.spline.basis.KaklisPandelisSetParams (2),
							org.drip.spline.params.SegmentInelasticDesignControl.Create (2, 2), null, null));
		} catch (java.lang.Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Create an Instance of the KLK Hyperbolic Splined Basis Curve
	 * 
	 * @param strName Curve Name
	 * @param dtStart The Tenor Start Date
	 * @param friReference Reference Leg FRI
	 * @param friDerived Derived Leg FRI
	 * @param bBasisOnReference TRUE - The Basis Quote is on the Reference Leg
	 * @param astrTenor Array of the Tenors
	 * @param adblBasis Array of the Basis Spreads
	 * @param dblTension The Tension Parameter
	 * 
	 * @return The Instance of the Basis Curve
	 */

	public static final org.drip.state.basis.BasisCurve KLKHyperbolicBasisCurve (
		final java.lang.String strName,
		final org.drip.analytics.date.JulianDate dtStart,
		final org.drip.state.identifier.ForwardLabel friReference,
		final org.drip.state.identifier.ForwardLabel friDerived,
		final boolean bBasisOnReference,
		final java.lang.String[] astrTenor,
		final double[] adblBasis,
		final double dblTension)
	{
		try {
			return CustomSplineBasisCurve (strName, dtStart, friReference, friDerived, bBasisOnReference,
				astrTenor, adblBasis, new org.drip.spline.params.SegmentCustomBuilderControl
					(org.drip.spline.stretch.MultiSegmentSequenceBuilder.BASIS_SPLINE_KLK_HYPERBOLIC_TENSION,
						new org.drip.spline.basis.ExponentialTensionSetParams (dblTension),
							org.drip.spline.params.SegmentInelasticDesignControl.Create (2, 2), null, null));
		} catch (java.lang.Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Create an Instance of the KLK Rational Linear Splined Basis Curve
	 * 
	 * @param strName Curve Name
	 * @param dtStart The Tenor Start Date
	 * @param friReference Reference Leg FRI
	 * @param friDerived Derived Leg FRI
	 * @param bBasisOnReference TRUE - The Basis Quote is on the Reference Leg
	 * @param astrTenor Array of the Tenors
	 * @param adblBasis Array of the Basis Spreads
	 * @param dblTension The Tension Parameter
	 * 
	 * @return The Instance of the Basis Curve
	 */

	public static final org.drip.state.basis.BasisCurve KLKRationalLinearBasisCurve (
		final java.lang.String strName,
		final org.drip.analytics.date.JulianDate dtStart,
		final org.drip.state.identifier.ForwardLabel friReference,
		final org.drip.state.identifier.ForwardLabel friDerived,
		final boolean bBasisOnReference,
		final java.lang.String[] astrTenor,
		final double[] adblBasis,
		final double dblTension)
	{
		try {
			return CustomSplineBasisCurve (strName, dtStart, friReference, friDerived, bBasisOnReference,
				astrTenor, adblBasis, new org.drip.spline.params.SegmentCustomBuilderControl
					(org.drip.spline.stretch.MultiSegmentSequenceBuilder.BASIS_SPLINE_KLK_RATIONAL_LINEAR_TENSION,
				new org.drip.spline.basis.ExponentialTensionSetParams (dblTension),
					org.drip.spline.params.SegmentInelasticDesignControl.Create (2, 2), null, null));
		} catch (java.lang.Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Create an Instance of the KLK Rational Quadratic Splined Basis Curve
	 * 
	 * @param strName Curve Name
	 * @param dtStart The Tenor Start Date
	 * @param friReference Reference Leg FRI
	 * @param friDerived Derived Leg FRI
	 * @param bBasisOnReference TRUE - The Basis Quote is on the Reference Leg
	 * @param astrTenor Array of the Tenors
	 * @param adblBasis Array of the Basis Spreads
	 * @param dblTension The Tension Parameter
	 * 
	 * @return The Instance of the Basis Curve
	 */

	public static final org.drip.state.basis.BasisCurve KLKRationalQuadraticBasisCurve (
		final java.lang.String strName,
		final org.drip.analytics.date.JulianDate dtStart,
		final org.drip.state.identifier.ForwardLabel friReference,
		final org.drip.state.identifier.ForwardLabel friDerived,
		final boolean bBasisOnReference,
		final java.lang.String[] astrTenor,
		final double[] adblBasis,
		final double dblTension)
	{
		try {
			return CustomSplineBasisCurve (strName, dtStart, friReference, friDerived, bBasisOnReference,
				astrTenor, adblBasis, new org.drip.spline.params.SegmentCustomBuilderControl
					(org.drip.spline.stretch.MultiSegmentSequenceBuilder.BASIS_SPLINE_KLK_RATIONAL_QUADRATIC_TENSION,
				new org.drip.spline.basis.ExponentialTensionSetParams (dblTension),
					org.drip.spline.params.SegmentInelasticDesignControl.Create (2, 2), null, null));
		} catch (java.lang.Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
