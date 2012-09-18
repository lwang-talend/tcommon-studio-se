package org.talend.mdm;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;


public class Login extends Base{
	protected WebDriver driver;
	
	@BeforeClass
	@Parameters({"url", "root", "testlink.id", "testlink.porject"})
	public void initWebdriver(String url, String root, String testlinkId, String testlinkProject , ITestContext context){
		System.setProperty("testlink.id", testlinkId);
		System.setProperty("testlink.porject", testlinkProject);
		
		URL file = Login.class.getClassLoader().getResource("org/talend/mdm/resources");
		PropertyConfigurator.configure( file.getPath() + "/log4j.properties" );
		
		if(null == System.getProperty("webdriver.browser") || "".equals(System.getProperty("webdriver.browser").trim()) || System.getProperty("webdriver.browser").trim().contains("webdriver.browser")) {
			driver = this.setFirefox();
		} else{
			
			try {
				driver = this.setWebDriver(Browser.valueOf(System.getProperty("webdriver.browser").trim()));
			} catch (Exception e) {
				logger.info("Doesn't not support the browser of - " + System.getProperty("webdriver.browser").trim() + ", will use firefox!");
				driver = this.setFirefox();
			}
		}
	
		// Old code for firefox
/**		
		logger.info("webdriver.firefox.bin.path = " + System.getProperty("webdriver.firefox.bin.path").trim());
		if(null == System.getProperty("webdriver.firefox.bin.path") || "".equals(System.getProperty("webdriver.firefox.bin.path").trim()) || System.getProperty("webdriver.firefox.bin.path").trim().contains("webdriver.firefox.bin.path")) {
		} else{
			System.setProperty("webdriver.firefox.bin", System.getProperty("webdriver.firefox.bin.path").trim());
		}
		
	    FirefoxProfile firefoxProfile = new FirefoxProfile();
	    firefoxProfile.setPreference("browser.download.folderList",2);
	    firefoxProfile.setPreference("browser.download.manager.showWhenStarting",false);
	    firefoxProfile.setPreference("browser.download.dir", this.getAbsoluteFolderPath("org/talend/mdm/download"));
	    firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk","text/csv, application/vnd.ms-excel, application/zip, application/pdf");
	    
	    firefoxProfile.setPreference("dom.max_script_run_time", 0);
	    firefoxProfile.setPreference("dom.max_chrome_script_run_time", 0);

//	    firefoxProfile.setPreference("native_events_enabled", false);
	    firefoxProfile.setPreference("webdriver_enable_native_events", false);
	    
	    
//	    firefoxProfile.setEnableNativeEvents(true);
//	    
	    logger.info("setEnableNativeEvents-" + firefoxProfile.areNativeEventsEnabled());
//	    firefoxProfile.setEnableNativeEvents(false);
//	    
//	    logger.info("setEnableNativeEvents-" + firefoxProfile.areNativeEventsEnabled());
	    
	    driver = new FirefoxDriver(firefoxProfile);
**/
		
	    //set driver time out with TimeUnit
	    driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
	    logger.info("Set Firefox Driver with Profile");
		
//		driver = new FirefoxDriver();
		logger.info("URL - " +url + root);
		
		
		driver.get(url + root);
		super.setDriver(driver);
		windowMaximize();
		
		onTestListener(context, Login.class.getClassLoader().getResource("org/talend/mdm/download").getPath());
	}
	
	@BeforeMethod
	@Parameters( { "user.name", "user.password", "message" })
	public void login(String userName, String userPassword, String message) {
		this.login(userName, userPassword);
		
//		if (this.isTextPresent(message)) {
//			this.getElementById("idLoginForceLogoutButton").click();
//			this.getElementById("idLoginButton").click();
//			logger.info("Force login TAC");
//		}
		
		if(this.isTextPresent(message)) {
			this.getElementByLinkText("Force user to logout").click();
			this.login(userName, userPassword);
		} else {
			logger.info("Force Login MDM");
		}
	}

	public void login(String userName, String userPassword) {

		WebElement userE = this.getElementByName(locator.getString("id.login.username"));
		userE.clear();
		userE.sendKeys(userName);
		WebElement passwordE = this.getElementByName(locator.getString("id.login.password"));
		passwordE.clear();
		passwordE.sendKeys(userPassword);
		this.getElementByName("login").click();
		logger.info("Login MDM");
	}
	
	
	@AfterMethod
	public void logout() {
		
		logger.info("Click MDM logout button");
		this.getElementByXpath("//button[text()='Logout']").click();
		logger.info("Logout MDM");
	}

	@AfterClass
	public void killBroswer() {
		driver.quit();
		logger.info("WebDriver Quit");
	}
	
	@AfterSuite
	public void generateXmlReport(){
		Results result = new Results();
		
		result.crateXmlFile(result.getResults(failedTestCases), this.getAbsoluteFolderPath("org/talend/mdm/download")+ "/Failed.xml");
		result.crateXmlFile(result.getResults(successTestCases), this.getAbsoluteFolderPath("org/talend/mdm/download")+ "/Succes.xml");
	}
}