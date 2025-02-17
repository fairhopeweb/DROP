
package org.drip.specialfunction.group;

/*
 * -*- mode: java; tab-width: 4; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 */

/*!
 * Copyright (C) 2022 Lakshmi Krishnamurthy
 * Copyright (C) 2021 Lakshmi Krishnamurthy
 * Copyright (C) 2020 Lakshmi Krishnamurthy
 * Copyright (C) 2019 Lakshmi Krishnamurthy
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
 * <i>SchwarzTriangleMap</i> contains the Ratio of the Linearly Independent Solution pair corresponding to a
 * given Singularity of the Hyper-geometric 2F1 Function. The References are:
 * 
 * <br><br>
 * 	<ul>
 * 		<li>
 * 			Gessel, I., and D. Stanton (1982): Strange Evaluations of Hyper-geometric Series <i>SIAM Journal
 * 				on Mathematical Analysis</i> <b>13 (2)</b> 295-308
 * 		</li>
 * 		<li>
 * 			Koepf, W (1995): Algorithms for m-fold Hyper-geometric Summation <i>Journal of Symbolic
 * 				Computation</i> <b>20 (4)</b> 399-417
 * 		</li>
 * 		<li>
 * 			Lavoie, J. L., F. Grondin, and A. K. Rathie (1996): Generalization of Whipple�s Theorem on the
 * 				Sum of a (_2^3)F(a,b;c;z) <i>Journal of Computational and Applied Mathematics</i> <b>72</b>
 * 				293-300
 * 		</li>
 * 		<li>
 * 			National Institute of Standards and Technology (2019): Hyper-geometric Function
 * 				https://dlmf.nist.gov/15
 * 		</li>
 * 		<li>
 * 			Wikipedia (2019): Hyper-geometric Function https://en.wikipedia.org/wiki/Hypergeometric_function
 * 		</li>
 * 	</ul>
 *
 *	<br><br>
 *  <ul>
 *		<li><b>Module </b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/ComputationalCore.md">Computational Core Module</a></li>
 *		<li><b>Library</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/FunctionAnalysisLibrary.md">Function Analysis Library</a></li>
 *		<li><b>Project</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/src/main/java/org/drip/specialfunction/README.md">Special Function Implementation Analysis</a></li>
 *		<li><b>Package</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/src/main/java/org/drip/specialfunction/group/README.md">Special Function Singularity Solution Group</a></li>
 *  </ul>
 *
 * @author Lakshmi Krishnamurthy
 */

public class SchwarzTriangleMap
{
	private double _singularity = java.lang.Double.NaN;
	private double _connectionCoefficient = java.lang.Double.NaN;
	private org.drip.function.definition.R1ToR1 _singularitySolution0 = null;
	private org.drip.function.definition.R1ToR1 _singularitySolution1 = null;
	private org.drip.function.definition.R1ToR1 _singularityAsymptoteOrderTerm = null;

	/**
	 * SchwarzTriangleMap Constructor
	 * 
	 * @param singularity The Singularity
	 * @param singularitySolution0 The Singularity Solution 0
	 * @param singularitySolution1 The Singularity Solution 1
	 * @param singularityAsymptoteOrderTerm The Singularity Asymptote Order Term
	 * @param connectionCoefficient The Connection Coefficient
	 * 
	 * @throws java.lang.Exception Thrown if the Inputs are Invalid
	 */

	public SchwarzTriangleMap (
		final double singularity,
		final org.drip.function.definition.R1ToR1 singularitySolution0,
		final org.drip.function.definition.R1ToR1 singularitySolution1,
		final org.drip.function.definition.R1ToR1 singularityAsymptoteOrderTerm,
		final double connectionCoefficient)
		throws java.lang.Exception
	{
		if (java.lang.Double.isNaN (_singularity = singularity) ||
			null == (_singularitySolution0 = singularitySolution0) ||
			null == (_singularitySolution1 = singularitySolution1) ||
			null == (_singularityAsymptoteOrderTerm = singularityAsymptoteOrderTerm) ||
			!org.drip.numerical.common.NumberUtil.IsValid (_connectionCoefficient = connectionCoefficient))
		{
			throw new java.lang.Exception ("SchwarzTriangleMap Constructor => Invalid Inputs");
		}
	}

	/**
	 * Retrieve the Singularity
	 * 
	 * @return The Singularity
	 */

	public double singularity()
	{
		return _singularity;
	}

	/**
	 * Retrieve the Singularity Solution 0
	 * 
	 * @return The Singularity Solution 0
	 */

	public org.drip.function.definition.R1ToR1 singularitySolution0()
	{
		return _singularitySolution0;
	}

	/**
	 * Retrieve the Singularity Solution 1
	 * 
	 * @return The Singularity Solution 1
	 */

	public org.drip.function.definition.R1ToR1 singularitySolution1()
	{
		return _singularitySolution1;
	}

	/**
	 * Retrieve the Singularity Asymptote Order Term
	 * 
	 * @return The Singularity Asymptote Order Term
	 */

	public org.drip.function.definition.R1ToR1 singularityAsymptoteOrderTerm()
	{
		return _singularityAsymptoteOrderTerm;
	}

	/**
	 * Retrieve the Connection Coefficient
	 * 
	 * @return The Connection Coefficient
	 */

	public double connectionCoefficient()
	{
		return _connectionCoefficient;
	}

	/**
	 * Indicate if the Triangle Map is Conformal
	 * 
	 * @return TRUE - The Triangle Map is Conformal
	 */

	public boolean isConformal()
	{
		return 0. <= _connectionCoefficient && 1. > _connectionCoefficient;
	}

	/**
	 * Generate the s-Function corresponding to the Singularity Solution Pair
	 * 
	 * @return The s-Function corresponding to the Singularity Solution Pair
	 */

	public org.drip.function.definition.R1ToR1 sFunction()
	{
		return new org.drip.function.definition.R1ToR1 (null)
		{
			@Override public double evaluate (
				final double z)
				throws java.lang.Exception
			{
				return _singularitySolution1.evaluate (z) / _singularitySolution0.evaluate (z);
			}
		};
	}

	/**
	 * Generate the Schwarz-Christoffel Triangle Vertex
	 * 
	 * @return The Schwarz-Christoffel Triangle Vertex
	 */

	public org.drip.specialfunction.group.SchwarzChristoffelVertex schwarzChristoffelVertex()
	{
		try
		{
			return new org.drip.specialfunction.group.SchwarzChristoffelVertex (
				new org.drip.function.definition.R1ToR1 (null)
				{
					@Override public double evaluate (
						final double z)
						throws java.lang.Exception
					{
						return java.lang.Math.pow (
							_singularityAsymptoteOrderTerm.evaluate (z),
							_connectionCoefficient
						);
					}
				},
				_singularityAsymptoteOrderTerm,
				java.lang.Math.PI * _connectionCoefficient
			);
		}
		catch (java.lang.Exception e)
		{
			e.printStackTrace();
		}

		return null;
	}
}
