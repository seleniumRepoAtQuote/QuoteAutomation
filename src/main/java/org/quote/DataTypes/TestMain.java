package org.quote.DataTypes;

import java.io.IOException;

import org.quote.LoadData.LoadDataFromExcel;

public class TestMain {

	public static void main(String[] args) throws IOException {
		
		
		LoadDataFromExcel dataFromExcel = new LoadDataFromExcel();
		//ArrayList<PlatformConfig> platFormsList = dataFromExcel.populatePlatformAndBrowsers();
		//System.out.println(platFormsList.size());
	//	System.out.println(dataFromExcel.populateURLs().size());
	//	System.out.println(dataFromExcel.poplulateCBTdetails().toString());
		System.out.println(dataFromExcel.populateMdaConfigs("autoinsurance").toString());
	}


}
