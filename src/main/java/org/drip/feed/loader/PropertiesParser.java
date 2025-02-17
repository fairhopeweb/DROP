
package org.drip.feed.loader;

/*
 * -*- mode: java; tab-width: 4; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 */

/*!
 * Copyright (C) 2022 Lakshmi Krishnamurthy
 * Copyright (C) 2021 Lakshmi Krishnamurthy
 * Copyright (C) 2020 Lakshmi Krishnamurthy
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
 * <i>PropertiesParser</i> contains the functionality to load the Field/Value Sets from the Field=Value Format.
 *
 *	<br><br>
 *  <ul>
 *		<li><b>Module </b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/ComputationalCore.md">Computational Core Module</a></li>
 *		<li><b>Library</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/ComputationSupportLibrary.md">Computation Support</a></li>
 *		<li><b>Project</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/src/main/java/org/drip/feed/README.md">Load, Transform, and compute Target Metrics across Feeds</a></li>
 *		<li><b>Package</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/src/main/java/org/drip/feed/loader/README.md">Reference/Market Data Feed Loader</a></li>
 *  </ul>
 * 
 * @author Lakshmi Krishnamurthy
 */

public class PropertiesParser
{
	private java.util.Map<java.lang.String, java.lang.String> _valueMap =
		new org.drip.analytics.support.CaseInsensitiveHashMap<java.lang.String>();

	public PropertiesParser (
		final java.lang.String fileName)
		throws java.lang.Exception
	{
		java.lang.String strCSVLine = "";

		@SuppressWarnings ("resource") java.io.BufferedReader brCSV = new java.io.BufferedReader (
			new java.io.FileReader (
				fileName
			)
		);

		while (null != (strCSVLine = brCSV.readLine()))
		{
			if (null == strCSVLine || (strCSVLine = strCSVLine.trim()).isEmpty() ||
				strCSVLine.startsWith (
					"#"
				)
			)
			{
				break;
			}

			java.lang.String[] astrValue = org.drip.service.common.StringUtil.Split (
				strCSVLine,
				"="
			);

			if (null != astrValue && 2 == astrValue.length)
			{
				_valueMap.put (
					astrValue[0],
					astrValue[1]
				);
			}
		}
	}

	/**
	 * Retrieve the Map of Property Value
	 * 
	 * @return Map of Property Value
	 */

	public java.util.Map<java.lang.String, java.lang.String> valueMap()
	{
		return _valueMap;
	}

	/**
	 * Extract the Named Value as a String
	 * 
	 * @param name The Name
	 * 
	 * @return The Named Value as a String
	 */

	public java.lang.String stringValue (
		final java.lang.String name)
	{
		return null == name || !_valueMap.containsKey (
			name.trim()
		) ? null : _valueMap.get (
			name.trim()
		);
	}

	/**
	 * Extract the Named Value as a Double
	 * 
	 * @param name The Name
	 * 
	 * @return The Named Value as a Double
	 * 
	 * @throws java.lang.Exception Thrown if the Inputs are Invalid
	 */

	public double doubleValue (
		final java.lang.String name)
		throws java.lang.Exception
	{
		if (null == name ||
			!_valueMap.containsKey (
				name.trim()
			)
		)
		{
			throw new java.lang.Exception (
				"PropertiesParser::doubleValue => Cannot Extract"
			);
		}

		java.lang.String stringValue = _valueMap.get (
			name.trim()
		);

		try
		{
			return java.lang.Double.parseDouble (
				stringValue
			);
		}
		catch (java.lang.Exception e)
		{
		}

		throw new java.lang.Exception (
			"PropertiesParser::doubleValue => Cannot Extract"
		);
	}

	/**
	 * Extract the Named Value as a Integer
	 * 
	 * @param name The Name
	 * 
	 * @return The Named Value as a Integer
	 * 
	 * @throws java.lang.Exception Thrown if the Inputs are Invalid
	 */

	public int integerValue (
		final java.lang.String name)
		throws java.lang.Exception
	{
		if (null == name ||
			!_valueMap.containsKey (
				name.trim()
			)
		)
		{
			throw new java.lang.Exception (
				"PropertiesParser::integerValue => Cannot Extract"
			);
		}

		java.lang.String stringValue = _valueMap.get (
			name.trim()
		);

		try
		{
			return java.lang.Integer.parseInt (
				stringValue
			);
		}
		catch (java.lang.Exception e)
		{
		}

		throw new java.lang.Exception (
			"PropertiesParser::integerValue => Cannot Extract"
		);
	}
}
