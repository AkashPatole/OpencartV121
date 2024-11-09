package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
  
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException
	{
		String path=".\\testData\\OpenCart_LoginData.xlsx";
		 
		ExcelUtility xutil=new ExcelUtility(path);
		
		int totalRows=xutil.getRowCount("sheet1");
		int totalColumns=xutil.getCellCount("sheet1", 1);
		
		String logindata[][] =new String[totalRows][totalColumns];
		
		for(int i=1;i<=totalRows;i++)//1 //read the Data from xl stroing in 2d array
		{
			for(int j=0;j<totalColumns;j++)//0 i is rows j is col
			{
				logindata[i-1][j]=xutil.getCellData("sheet1", i, j); //1,0
			}
		}
		return logindata;
	}
}
