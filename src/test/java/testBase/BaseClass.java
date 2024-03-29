package testBase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
public static WebDriver driver;
public Logger logger;
public ResourceBundle rb;
	@BeforeClass(groups= {"Master","Sanity","Regresion"})
	@Parameters("browser")
	public void setup(String br) {
		
	    rb= ResourceBundle.getBundle("config");
		logger= LogManager.getLogger(this.getClass());
		
		if(br.equalsIgnoreCase("chrome"))
		{
			/* ChromeOptions op= new ChromeOptions();
			op.setExperimentalOption("excludeswitches",new String[]{"enable-automation"});
		driver= new ChromeDriver(op);
		*/
			driver= new ChromeDriver();
		}
		else
			if(br.equalsIgnoreCase("edge")) {
				driver= new EdgeDriver();
			}
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(rb.getString("appURL"));
		logger.info("launching browser");
		driver.manage().window().maximize();
		
		
	}
	
	
	
 	@AfterClass(groups= {"Master","Sanity","Regression"})
	public void teardown() {
		driver.close();
		logger.info("closing application");
	} 
	
	public String randomString() {
		String generatedString= RandomStringUtils.randomAlphabetic(3);
		return generatedString;
	}
	
	public String randomNumeric() {
		String generatednumeric= RandomStringUtils.randomNumeric(3);
		return generatednumeric;
	}
	
	public String randomAlphaNumeric() {
		String generatedalphanumeric= RandomStringUtils.randomAlphanumeric(6);
		return generatedalphanumeric;
	}
	
	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";

		try {
			FileUtils.copyFile(source, new File(destination));
		} catch (Exception e) {
			e.getMessage();
		}
		return destination;

	}
}
