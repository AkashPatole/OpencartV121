package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	public static WebDriver driver; // static : cuz used if diff class
	public Logger logger; // Log4j
	public Properties p;
    
	
	@BeforeClass(groups = { "master", "sanity", "regression" })
	@Parameters({"browser", "os" })
	public void setup(String browser,String os) throws IOException, URISyntaxException {
		logger = LogManager.getLogger(this.getClass());// default path src/test/resources
         
		//Read Config File
		FileReader file = new FileReader("./src/test/resources/config.properties");
		p = new Properties();
		p.load(file);
		
		
		// selenium grid

		if (p.getProperty("execution_env").equalsIgnoreCase("remote")) {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			// os
			if (os.equalsIgnoreCase("windows")) {
				capabilities.setPlatform(Platform.WIN11);
			} else if (os.equalsIgnoreCase("mac")) {
				capabilities.setPlatform(Platform.MAC);
			} else {
				System.out.println("No Matching os");
				return;
			}

			// browser
			switch (browser.toLowerCase()) {
			case "chrome":
				capabilities.setBrowserName("chrome");
				break;
			case "edge":
				capabilities.setBrowserName("MicrosoftEdge");
				break;
			default:
				System.out.println("Invalid Browser");
				return;
			}
                    URI uri=new URI("http://192.168.0.103:4444/wd/hub");
			driver = new RemoteWebDriver(uri.toURL(), capabilities);
		}
            System.out.println("before local: "+p.getProperty("execution_env"));
		
            //Local Environment
		if (p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
         System.out.println(p.getProperty("execution_env"));
			switch (browser.toLowerCase()) {
			case "chrome":
				driver = new ChromeDriver();
				break;
			case "edge":
				driver = new EdgeDriver();
				break;
			default:
				System.out.println("Invalid Browser");
				return;
			}
		}

		
		
		
	/*	switch (br.toLowerCase()) {
		case "chrome":
			driver = new ChromeDriver();
			break;
		case "edge":
			driver = new EdgeDriver();
			break;
		default:
			System.out.println("Invalid Browser");
			return;
		}   */

	
		// InputStream file =
		// getClass().getClassLoader().getResourceAsStream("config.properties");

		driver.manage().deleteAllCookies();
		// driver.get("https://tutorialsninja.com/demo/");
		driver.get(p.getProperty("appUrl"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.manage().window().maximize();

	}

	@AfterClass(groups = { "master", "sanity", "regression" })
	public void tearDown() {
		driver.quit();
	}

	@SuppressWarnings("deprecation")
	public String randomAlphabate() {
		return RandomStringUtils.randomAlphabetic(5);
	}

	@SuppressWarnings("deprecation")
	public String randomNumeric() {
		return RandomStringUtils.randomNumeric(10);
	}

	public String randomAlphaNumeric() {
		String alpha = RandomStringUtils.randomAlphabetic(5);
		String num = RandomStringUtils.randomNumeric(5);
		return (alpha + num);
	}

	public String captureScreen(String tname) throws IOException {
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

		String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile = new File(targetFilePath);
		sourceFile.renameTo(targetFile);

		return targetFilePath;
	}
}
