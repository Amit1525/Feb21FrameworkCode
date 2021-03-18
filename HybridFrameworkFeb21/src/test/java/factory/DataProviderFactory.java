package factory;

import dataprovider.ConfigDataProvider;
import dataprovider.ExcelDataProvider;

public class DataProviderFactory {
	
	
	
	public static  ExcelDataProvider getExcel() {
		
		ExcelDataProvider excel = new ExcelDataProvider();
		return excel;
	}
	
		
	public static ConfigDataProvider getConfig() {
		
		ConfigDataProvider config = new ConfigDataProvider();
		return config;
	}

}
