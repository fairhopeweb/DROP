
package org.drip.sample.bcbs;

import org.drip.capital.bcbs.HighQualityLiquidAsset;
import org.drip.capital.bcbs.HighQualityLiquidAssetSettings;
import org.drip.capital.bcbs.HighQualityLiquidAssetStandard;
import org.drip.service.common.FormatUtil;
import org.drip.service.env.EnvManager;

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
 * <i>HighQualityLiquidAssetCompliance</i> illustrates the Basel III/Jurisdictional Compliance Checks
 * 	associated with High Quality Liquid Assets. The References are:
 * 
 * <br><br>
 * 	<ul>
 * 		<li>
 * 			Basel Committee on Banking Supervision (2017): Basel III Leverage Ratio Framework and Disclosure
 * 				Requirements https://www.bis.org/publ/bcbs270.pdf
 * 		</li>
 * 		<li>
 * 			Central Banking (2013): Fed and FDIC agree 6% Leverage Ratio for US SIFIs
 * 				https://www.centralbanking.com/central-banking/news/2280726/fed-and-fdic-agree-6-leverage-ratio-for-us-sifis
 * 		</li>
 * 		<li>
 * 			European Banking Agency (2013): Implementing Basel III in Europe: CRD IV Package
 * 				https://eba.europa.eu/regulation-and-policy/implementing-basel-iii-europe
 * 		</li>
 * 		<li>
 * 			Federal Reserve (2013): Liquidity Coverage Ratio � Liquidity Risk Measurements, Standards, and
 * 				Monitoring
 * 				https://web.archive.org/web/20131102074614/http:/www.federalreserve.gov/FR_notice_lcr_20131024.pdf
 * 		</li>
 * 		<li>
 * 			Wikipedia (2018): Basel III https://en.wikipedia.org/wiki/Basel_III
 * 		</li>
 * 	</ul>
 *
 *	<br><br>
 *  <ul>
 *		<li><b>Module </b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/PortfolioCore.md">Portfolio Core Module</a></li>
 *		<li><b>Library</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/CapitalAnalyticsLibrary.md">Capital Analytics</a></li>
 *		<li><b>Project</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/src/main/java/org/drip/sample/README.md">DROP API Construction and Usage</a></li>
 *		<li><b>Package</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/src/main/java/org/drip/sample/bcbs/README.md">BCBS/Jurisdictional Capital/Leverage Compliance Checks</a></li>
 *  </ul>
 * 
 * @author Lakshmi Krishnamurthy
 */

public class HighQualityLiquidAssetCompliance
{

	public static final void main (
		final String[] argumentArray)
		throws Exception
	{
		EnvManager.InitEnv ("");

		HighQualityLiquidAssetStandard hqlaStandardFed = HighQualityLiquidAssetStandard.FederalReserve();

		HighQualityLiquidAssetSettings hqlaSettings =
			HighQualityLiquidAssetSettings.FederalReserveStandard();

		double level1 = 60.;
		double level2A = 25.;
		double level2B = 15.;

		HighQualityLiquidAsset hqla = new HighQualityLiquidAsset (
			level1,
			level2A,
			level2B
		);

		System.out.println ("\t|-------------------------|");

		System.out.println ("\t| HQLA Compliance Ratios  |");

		System.out.println ("\t|-------------------------|");

		System.out.println ("\t| Level 2 Ratio  => " +
			FormatUtil.FormatDouble (hqlaStandardFed.level2Ratio(), 1, 2, 1.) + " |"
		);

		System.out.println ("\t| Level 2B Ratio => " +
			FormatUtil.FormatDouble (hqlaStandardFed.level2BRatio(), 1, 2, 1.) + " |"
		);

		System.out.println ("\t|-------------------------|");

		System.out.println();

		System.out.println ("\t|-------------------------------|");

		System.out.println ("\t|       HQLA Fed Settings       |");

		System.out.println ("\t|-------------------------------|");

		System.out.println ("\t| Level 1 Hair Cut     => " +
			FormatUtil.FormatDouble (hqlaSettings.level1Haircut(), 1, 2, 1.) + " |"
		);

		System.out.println ("\t| Level 1 Risk Weight  => " +
			FormatUtil.FormatDouble (hqlaSettings.level1RiskWeight(), 1, 2, 1.) + " |"
		);

		System.out.println ("\t| Level 2A Hair Cut    => " +
			FormatUtil.FormatDouble (hqlaSettings.level2AHaircut(), 1, 2, 1.) + " |"
		);

		System.out.println ("\t| Level 2A Risk Weight => " +
			FormatUtil.FormatDouble (hqlaSettings.level2ARiskWeight(), 1, 2, 1.) + " |"
		);

		System.out.println ("\t| Level 2B Hair Cut    => " +
			FormatUtil.FormatDouble (hqlaSettings.level2BHaircut(), 1, 2, 1.) + " |"
		);

		System.out.println ("\t| Level 2B Risk Weight => " +
			FormatUtil.FormatDouble (hqlaSettings.level2BRiskWeight(), 1, 2, 1.) + " |"
		);

		System.out.println ("\t|-------------------------------|");

		System.out.println();

		System.out.println ("\t|-------------------------------|");

		System.out.println ("\t|    HQLA Composite Metrics     |");

		System.out.println ("\t|-------------------------------|");

		System.out.println ("\t| Level 1             => " +
			FormatUtil.FormatDouble (hqla.level1(), 3, 1, 1.) + " |"
		);

		System.out.println ("\t| Level 2A            => " +
			FormatUtil.FormatDouble (hqla.level2A(), 3, 1, 1.) + " |"
		);

		System.out.println ("\t| Level 2B            => " +
			FormatUtil.FormatDouble (hqla.level2B(), 3, 1, 1.) + " |"
		);

		System.out.println ("\t| Total               => " +
			FormatUtil.FormatDouble (hqla.total(), 3, 1, 1.) + " |"
		);

		System.out.println ("\t| Level 2 Ratio       => " +
			FormatUtil.FormatDouble (hqla.level2Ratio(), 1, 2, 1.) + "  |"
		);

		System.out.println ("\t| Level 2B Ratio      => " +
			FormatUtil.FormatDouble (hqla.level2BRatio(), 1, 2, 1.) + "  |"
		);

		System.out.println ("\t| Fed HQLA Compliant  =>  " + hqla.isCompliant (hqlaStandardFed) + "  |");

		System.out.println ("\t| HQLA Adjusted Total => " +
			FormatUtil.FormatDouble (hqla.totalRiskWeightAndHaircut (hqlaSettings), 3, 1, 1.) + " |"
		);

		System.out.println ("\t|-------------------------------|");

		EnvManager.TerminateEnv();
	}
}
