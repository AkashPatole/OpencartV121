package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;


// Add Listner tag in xml file for reports
public class ExtentReportManager implements ITestListener {

	public ExtentSparkReporter sparkReporter; // UI of the report
	public ExtentReports extent; // populate common info on the report
	public ExtentTest test; // creating test case entries in the report and update status of the test
							// methods
	String repName;

	public void onStart(ITestContext context) {

		/*
		 * SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss"); Date dt=new
		 * Date(); String currentdatetimestamp=df.format(dt);
		 */

		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); // time Stamp for every test
																							// record
		repName = "Test-Report-" + timeStamp + ".html";
		sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);// speciflocation of report
		sparkReporter.config().setDocumentTitle("Automation Report");// Title of The report
		sparkReporter.config().setReportName("Functional Testing");// name of the Report
		sparkReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);

		extent.setSystemInfo("Applocation Name", "OpenCart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Customers");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		String browser = context.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser name", browser);

		String os = context.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating Sytem", os);// from xml parameter Value

		List<String> includeGroups = context.getCurrentXmlTest().getIncludedGroups();// groups in xml group tags
		if (!includeGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includeGroups.toString());
		}
	}

	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());// create a new entery in report
		test.assignCategory(result.getMethod().getGroups());// to Display groups in reports
		test.log(Status.PASS, "Test case Passed is:" + result.getName());// update status pass/fail/skipped

	}

	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());// create a new entery in report
		test.log(Status.FAIL, "Test case failed is:" + result.getName());// update status pass/fail/skipped
		test.log(Status.INFO, result.getThrowable().getMessage());
		try {

			String imgPath = new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getClass().getName());// create a new entery in report
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, "Test case Skipped is:" + result.getName());// update status pass/fail/skipped
		test.log(Status.INFO, result.getThrowable().getMessage());
	}

	public void onFinish(ITestContext context) {
		extent.flush();// Writes test information from the started reporters to their output

		// open Report Automatically
		String pathOfExtentReport = System.getProperty("user.dir") + "\\reports\\" + repName;
		File extentreport = new File(pathOfExtentReport);

		try {
			Desktop.getDesktop().browse(extentreport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Create and Send Email serach the code
		
		/*  try {
		  
		  @SuppressWarnings("deprecation") 
		  URL url=new URL("file:///" + System.getProperty("user.dir")+"\\reports\\"+repName); 
		  //apace.commons.email
		  ImageHtmlEmail email= new ImageHtmlEmail();
		  
		  email.setDataSourceResolver(new DataSourceUrlResolver(url));
		  email.setHostName("smpt.googleemail.com");
		  email.setSmtpPort(465);
		  email.setAuthenticator(new DefaultAuthenticator("pavanoltrainning@gmail.com", "password"));
		  email.setSSLOnConnect(true);
		  email.setFrom("pavanoltraining@gmail.com");//sender
		  email.setSubject("Test Results");
		  email.setMsg("plz find Attached Report...");
		  email.addTo("receiver email id");
		  email.attach(url, "Extent report", "please check report");
		  email.send();
		  }
		  catch(Exception e)
		  { e.printStackTrace(); }  */
		 
	}

}
