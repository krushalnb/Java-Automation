package com.automation.init;

import java.io.File;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.IResultMap;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.internal.Utils;

import com.automation.indexpage.HomeIndexPage;
import com.automation.verification.HomeVerificationPage;
import com.automation.init.Common;

public class SeleniumInit {

	public String suiteName = "";
	public String testName = "";
	/* Minimum requirement for test configur ation */
	protected String testUrl; // Test url
	protected String seleniumHub; // Selenium hub IP
	protected String seleniumHubPort; // Selenium hub port
	protected String targetBrowser; // Target browser
	protected static String test_data_folder_path = null;
	public static String currentWindowHandle = ""; // Get Current Window handle
	public static String browserName = "";
	public static String osName = "";
	public static String browserVersion = "";
	public static String browsernm = "";
	public static String cleanFlag = "";
	public static String datetime = "";
	public static String suiteNameBS;
//	public String dataStreamName = "";

	/*
	 * Object initialization
	 */
	public HomeIndexPage homeIndexPage;
	public HomeVerificationPage homeVerificationPage;

	protected static String screenshot_folder_path = null;
	public static String currentTest; // current running test

	protected static Logger logger = Logger.getLogger("testing");
	// protected RemoteWebDriver driver;
	public WebDriver driver;

	

	/**
	 * Fetches current Date time.
	 *
	 */
	@BeforeSuite(alwaysRun = true)
	public void setCurrentDateTime(ITestContext testContext) {

		suiteNameBS = "[" + TestData.readPropertiesFile("Resources/config.properties", "testurl") + "] "
				+ testContext.getSuite().getName() + " [" + TestData.getCurrentDateTime() + "]";

		log(suiteNameBS);
	}

	/**
	 * Fetches suite-configuration from XML suite file.
	 * 
	 * @param testContext
	 */

	@BeforeTest(alwaysRun = true)
	public void fetchSuiteConfiguration(ITestContext testContext) {
		// testUrl = testContext.getCurrentXmlTest().getParameter("selenium.url");
		testUrl = TestData.readPropertiesFile("Resources/config.properties", "testurl");
		/* System.out.println("======" + testUrl + "========="); */
		seleniumHub = testContext.getCurrentXmlTest().getParameter("selenium.host");
		seleniumHubPort = testContext.getCurrentXmlTest().getParameter("selenium.port");
		targetBrowser = TestData.readPropertiesFile("Resources/config.properties", "Browser");
		browsernm = targetBrowser;
		System.out.println("======" + browsernm + "=========");
		System.out.println("======" + testUrl + "=========");
	}

	/**
	 * WebDriver initialization
	 * 
	 * @return WebDriver object
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws ParseException
	 */
	@BeforeMethod(alwaysRun = true)
	public void setUp(Method method, ITestContext testContext, ITestResult testResult)
			throws IOException, InterruptedException, ParseException {
		
		currentTest = method.getName(); // get Name of current test.
		String SCREENSHOT_FOLDER_NAME = "screenshots";
		String TESTDATA_FOLDER_NAME = "test_data";

		test_data_folder_path = new File(TESTDATA_FOLDER_NAME).getAbsolutePath();
		screenshot_folder_path = new File(SCREENSHOT_FOLDER_NAME).getAbsolutePath();

		DesiredCapabilities capability = null;
		// System.out.println("before ff loop............");
		if (targetBrowser == null || targetBrowser.contains("firefox")) {
			//FirefoxProfile profile = new FirefoxProfile();
			System.setProperty("webdriver.gecko.driver",
					"D:\\Eclipse Workspace\\AutomationPractice\\Driver\\geckodriver.exe");

			/*
			 * profile.setPreference("dom.max_chrome_script_run_time", "999");
			 * profile.setPreference("dom.max_script_run_time", "999");
			 * profile.setPreference("browser.download.folderList", 2); //
			 * profile.setPreference("browser.download.dir", path);
			 * profile.setPreference("browser.helperApps.neverAsk.openFile",
			 * "text/csv,application/x-msexcel,application/excel,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml"
			 * ); profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
			 * "text/csv,application/x-msexcel,application/excel,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml"
			 * ); profile.setPreference("browser.download.manager.showWhenStarting", false);
			 * profile.setPreference("browser.download,manager.focusWhenStarting", false);
			 * // profile.setPreference("browser.download.useDownloadDir",true);
			 * profile.setPreference("browser.helperApps.alwaysAsk.force", false);
			 * profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
			 * profile.setPreference("browser.download.manager.closeWhenDone", false);
			 * profile.setPreference("browser.download.manager.showAlertOnComplete", false);
			 * profile.setPreference("browser.download.manager.useWindow", false);
			 * profile.setPreference("browser.download.manager.showWhenStarting", false);
			 * 
			 * profile.setPreference(
			 * "services.sync.prefs.sync.browser.download.manager.showWhenStarting", false);
			 * profile.setPreference("pdfjs.disabled", true);
			 * 
			 * profile.setAcceptUntrustedCertificates(true);
			 * profile.setPreference("security.OCSP.enabled", 0);
			 * profile.setEnableNativeEvents(false);
			 * profile.setPreference("network.http.use-cache", false);
			 * 
			 * profile.setPreference("gfx.direct2d.disabled", true);
			 * profile.setPreference("layers.acceleration.disabled", true);
			 */

			capability = DesiredCapabilities.firefox();
			//capability.setJavascriptEnabled(true);
			capability.setCapability("merionette", true);

			//capability.setCapability(FirefoxDriver.PROFILE, profile);
			browserName = capability.getBrowserName();
			//osName = System.getProperty("os.name");
			browserVersion = capability.getVersion().toString();
			driver = new FirefoxDriver(capability);
			
			System.out.println("=========" + "firefox Driver " + "==========");

		} else if (targetBrowser.contains("ie8")) {

			capability = DesiredCapabilities.internetExplorer();
			capability.setPlatform(Platform.ANY);
			capability.setBrowserName("internet explorer");
			// capability.setVersion("8.0");
			capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			capability.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
			capability.setJavascriptEnabled(true);
			browserName = capability.getBrowserName();
			osName = capability.getPlatform().name();
			browserVersion = capability.getVersion();
		} else if (targetBrowser.contains("ie9")) {
			capability = DesiredCapabilities.internetExplorer();
			capability.setBrowserName("internet explorer");
			capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			capability.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
			capability.setJavascriptEnabled(true);
			browserName = capability.getBrowserName();
			osName = capability.getPlatform().name();
			browserVersion = capability.getVersion();
		} else if (targetBrowser.contains("ie11")) {
			capability = DesiredCapabilities.internetExplorer();
			System.setProperty("webdriver.ie.driver",
					"D:\\Eclipse Workspace\\AutomationPractice\\Driver\\chromedriver.exe");

			capability.setBrowserName("internet explorer");
			capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			capability.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
			capability.setJavascriptEnabled(true);
			browserName = capability.getVersion();
			osName = capability.getPlatform().getCurrent().name();
			browserVersion = capability.getVersion();

			// driver = new RemoteWebDriver(remote_grid, capability);

		} else if (targetBrowser.contains("opera")) {
			capability = DesiredCapabilities.opera();
			System.setProperty("webdriver.opera.driver",
					"C:/Users/KQSPL_R/Desktop/Automation Driver/operadriver_win32/operadriver.exe");

			capability.setJavascriptEnabled(true);
			browserName = capability.getVersion();
			osName = capability.getPlatform().getCurrent().name();
			browserVersion = capability.getVersion();

			driver = new OperaDriver(capability);

		} else if (targetBrowser.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("no-sandbox");
			// options.addArguments("--start-maximized");
			capability = DesiredCapabilities.chrome();
			File chromeDriver = new File("chromedriver.exe");
			String chromedriverPath = "D:\\Eclipse Workspace\\AutomationPractice\\Driver\\chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", chromedriverPath);

			String downloadFilepath = new File("./DownloadFalkonrystuffs").getAbsolutePath();
			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("download.default_directory", downloadFilepath);

			// System.out.println("driver path :: --->"+chromeDriver.getAbsolutePath());
			// driver = new RemoteWebDriver(new
			// URL("http://localhost:4444/wd/hub"), capability);
			capability.setCapability(ChromeOptions.CAPABILITY, options);
			options.setExperimentalOption("prefs", chromePrefs);

			capability.setBrowserName("chrome");
			capability.setJavascriptEnabled(true);
			browserName = capability.getVersion();
			osName = capability.getPlatform().name();
			browserVersion = capability.getVersion();
			options.addArguments("disable-geolocation");

			// driver = new RemoteWebDriver(remote_grid, capability);

			driver = new ChromeDriver(options);

		} else if (targetBrowser.contains("safari")) {

			// System.setProperty("webdriver.safari.driver","/Users/jesus/Desktop/SafariDriver.safariextz");
			// driver = new SafariDriver();
			SafariDriver profile = new SafariDriver();

			capability = DesiredCapabilities.safari();
			capability.setJavascriptEnabled(true);
			capability.setBrowserName("safari");
			browserName = capability.getBrowserName();
			osName = capability.getPlatform().name();
			browserVersion = capability.getVersion();
			// capability.setCapability(SafariDriver.CLEAN_SESSION_CAPABILITY,
			// profile);
			this.driver = new SafariDriver(capability);
		} else if (targetBrowser.contains("saucelabs")) {

			String USERNAME = "nishilpatel81";
			String AUTOMATE_KEY = "8cfc1add-3f9e-48c2-9de4-f5db1379e253";
			String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@ondemand.saucelabs.com:80/wd/hub";

			DesiredCapabilities caps = DesiredCapabilities.chrome();
			caps.setCapability("platform", "Windows 8.1");
			caps.setCapability("version", "latest");
			driver = new RemoteWebDriver(new URL(URL), caps);
		} else if (targetBrowser.contains("browserstack")) {

			// String USERNAME = TestData.getValue("BSUsername");
			// String AUTOMATE_KEY = TestData.getValue("BSKey");

			String URL = "https://" + "prashantparekh1" + ":" + "s5sZBXDmjviG9p9vvz54"
					+ "@hub-cloud.browserstack.com/wd/hub";

			DesiredCapabilities caps = new DesiredCapabilities();

			try {
				String bb = testContext.getCurrentXmlTest().getParameter("browserstack.browser");
				if (bb.contains("chrome")) {
					caps.setCapability("os", "Windows");
					caps.setCapability("os_version", "10");
					caps.setCapability("browser", "Chrome");
					caps.setCapability("browser_version", "74.0");
					caps.setCapability("browserstack.local", "false");
					caps.setCapability("browserstack.selenium_version", "3.0.1");

					ChromeOptions options = new ChromeOptions();

					String downloadFilepath = new File("./DownloadFalkonrystuffs").getAbsolutePath();
					// System.out.println("-----------------------------------------------------------------------------");
					// System.out.println("-----------------Download Path: "+downloadFilepath+"
					// ----------------------- ");
					// System.out.println("-----------------------------------------------------------------------------");

					HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
					chromePrefs.put("download.default_directory", downloadFilepath);

					options.setExperimentalOption("prefs", chromePrefs);

				} else if (bb.contains("firefox")) {
					caps.setCapability("browser", "Firefox");
					caps.setCapability("browser_version", "58.0");
					caps.setCapability("browserstack.selenium_version", "3.10.0");
					caps.setCapability("os", "Windows");
					caps.setCapability("os_version", "8.1");
				} else if (bb.contains("ie11")) {
					caps.setCapability("browser", "IE");
					caps.setCapability("browser_version", "11.0");
					caps.setCapability("browserstack.selenium_version", "3.10.0");
					caps.setCapability("os", "Windows");
					caps.setCapability("os_version", "8.1");
				} else if (bb.contains("edge")) {
					caps.setCapability("browser", "Edge");
					caps.setCapability("browser_version", "16.0");
					caps.setCapability("os", "Windows");
					caps.setCapability("os_version", "10");
					caps.setCapability("browserstack.selenium_version", "3.10.0");
				}
			} catch (Exception e) {

			}

			caps.setCapability("resolution", "1920x1080");
			caps.setCapability("browserstack.debug", "true");

			caps.setCapability("browserstack.timezone", "Kolkata");
			caps.setCapability("project", "Falkonry");

			caps.setCapability("build", suiteNameBS);
			caps.setCapability("name", testContext.getName());
			// caps.setCapability("browserstack.local", "true");
			caps.setCapability("acceptSslCerts", "true");
			caps.setCapability("acceptInsecureCerts", "true");
			caps.setCapability("browserstack.networkLogs", "false");

			driver = new RemoteWebDriver(new URL(URL), caps);

		}

		suiteName = testContext.getSuite().getName();

		// driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.get(testUrl);
		System.out.println("TestData URL :: " + testUrl);
		driver.manage().window().maximize();

		currentWindowHandle = driver.getWindowHandle();
		System.out.println("Current Window Handle ID : --->>" + currentWindowHandle);

		suiteName = testContext.getSuite().getName();
		System.out.println("Current Xml Suite is:---->" + suiteName);

		/*
		 * loginIndexPage = new LoginIndexPage(driver); loginVerificationPage = new
		 * LoginVerificationPage(driver);
		 */
		homeIndexPage = new HomeIndexPage(driver);
		homeVerificationPage = new HomeVerificationPage(driver);

		System.err.println("Seession " + ((RemoteWebDriver) driver).getSessionId());
		log("Seession " + ((RemoteWebDriver) driver).getSessionId());

		// dsname.put(((RemoteWebDriver)driver).getSessionId().toString(),
		// dataStreamName);

	}

	/**
	 * Log For Failure Test Exception.
	 * 
	 * @param tests
	 */
	private void getShortException(IResultMap tests) {

		for (ITestResult result : tests.getAllResults()) {

			Throwable exception = result.getThrowable();
			List<String> msgs = Reporter.getOutput(result);
			boolean hasReporterOutput = msgs.size() > 0;
			boolean hasThrowable = exception != null;
			if (hasThrowable) {
				boolean wantsMinimalOutput = result.getStatus() == ITestResult.SUCCESS;
				if (hasReporterOutput) {
					log("<h3>" + (wantsMinimalOutput ? "Expected Exception" : "Failure Reason:") + "</h3>");
				}

				// Getting first line of the stack trace
				String str = Utils.stackTrace(exception, true)[0];
				Scanner scanner = new Scanner(str);
				String firstLine = scanner.nextLine();
				log(firstLine);
			}
		}
	}

	/**
	 * After Method
	 * 
	 * @param testResult
	 */

	@AfterMethod(alwaysRun = true)
	public void tearDown(ITestResult testResult) {

		ITestContext ex = testResult.getTestContext();

		try {
			testName = testResult.getName();
			if (!testResult.isSuccess()) {

				/* Print test result to Jenkins Console */
				markFail();
				System.out.println();
				System.out.println("TEST FAILED - " + testName);
				System.out.println();
				System.out.println("ERROR MESSAGE: " + testResult.getThrowable());
				System.out.println("\n");
				Reporter.setCurrentTestResult(testResult);

				/* Make a screenshot for test that failed */
				String screenshotName = Common.getCurrentTimeStampString() + testName;
				Reporter.log("<br> <b>Please look to the screenshot - </b>");
				Common.makeScreenshot(driver, screenshotName);
				Reporter.log("<hr size='4px' noshade color='red'>");
				// Reporter.log(testResult.getThrowable().getMessage());
				getShortException(ex.getFailedTests());
			} else {
				try {
					/* Reporter.log("<hr size='4px' noshade color='green'>"); */
					Common.pause(5);
				} catch (Exception e) {
					log("<br></br> Not able to perform");
				}

				System.out.println("TEST PASSED - " + testName + "\n"); // Print
				Reporter.setCurrentTestResult(testResult);
				Reporter.log("<hr size='4px' noshade color='green'>");
				markPass();
			}

			/*
			 * final File folder = new File("C:/Downloads_new"); File files[] =
			 * folder.listFiles();
			 * 
			 * if (files.length > 0) { for (File f : files) { if (f.delete()) { System.out
			 * .println("file deleted From Downloads_new folder"); } }
			 * 
			 * }
			 */

			System.out.println("here is test status--------------------" + testResult.getStatus());

			// driver.manage().deleteAllCookies();

			// driver.close();
			driver.quit();

		} catch (Throwable throwable) {
			System.out.println("message from tear down" + throwable.getMessage());
			// driver.manage().deleteAllCookies();

			// driver.close();
			driver.quit();
		}
	}

	/**
	 * Log given message to Reporter output.
	 * 
	 * @param msg Message/Log to be reported.
	 */
	public static void log(String msg) {
		System.out.println(msg);
		Reporter.log("<br></br>" + msg);
	}

	public static void logList(ArrayList<String> msg) {
		System.out.println(msg);
		Reporter.log("<br></br>" + msg);
	}

	public static void testDescription(String msg) {
		System.out.println(msg);
		Reporter.log("<strong> <h4 style=\"color:DarkViolet\"> " + "Testcase Description: " + msg + "</h4> </strong>");
	}

	public static void testcaseId(String msg) {
		System.out.println(msg);
		Reporter.log("<strong> <h4 style=\"color:DarkViolet\"> " + "Test Case ID: " + msg + "</h4> </strong>");
	}

	public static void logverification(int i, String msg) {
		System.out.println(msg);
		Reporter.log("<br></br><b style=\"color:OrangeRed \"> Expected Result-" + i + ": </b><b>" + msg + "</b>");
	}

	public void markPass() throws URISyntaxException, UnsupportedEncodingException, IOException {
		URI uri = new URI("https://tarpan2:BAwprphx24rUp2N6BrbP@api.browserstack.com/automate/sessions/"
				+ ((RemoteWebDriver) driver).getSessionId().toString() + ".json");
		HttpPut putRequest = new HttpPut(uri);

		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add((new BasicNameValuePair("status", "PASSED")));
		nameValuePairs.add((new BasicNameValuePair("reason", "")));
		putRequest.setEntity(new UrlEncodedFormEntity(nameValuePairs));

		HttpClientBuilder.create().build().execute(putRequest);
	}

	public void markFail() throws URISyntaxException, UnsupportedEncodingException, IOException {
		URI uri = new URI("https://tarpan2:BAwprphx24rUp2N6BrbP@api.browserstack.com/automate/sessions/"
				+ ((RemoteWebDriver) driver).getSessionId().toString() + ".json");
		HttpPut putRequest = new HttpPut(uri);

		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add((new BasicNameValuePair("status", "FAILED")));
		nameValuePairs.add((new BasicNameValuePair("reason", "")));
		putRequest.setEntity(new UrlEncodedFormEntity(nameValuePairs));

		HttpClientBuilder.create().build().execute(putRequest);
	}

	public void logStatus(String Status) throws UnsupportedEncodingException, URISyntaxException, IOException {
		System.out.println(Status);

		if (Status.equalsIgnoreCase("Pass")) {
			log(" <Strong><font color=#008000><b> &#10004 PASS</b></font></strong>");
			/*
			 * if(browsernm.contains("browserstack")){ markPass(); }
			 */
		} else if (Status.equalsIgnoreCase("Fail")) {
			log("<br><Strong><font color=#FF0000><b>&#10008 FAIL</b></font></strong></br>");
			/*
			 * if(browsernm.contains("browserstack")){ markFail(); }
			 */
			/* Make a screenshot for test that failed */
			String screenshotName = Common.getCurrentTimeStampString() + currentTest;
			Reporter.log("<br> <b>Please look to the screenshot - </b>");
			Common.makeScreenshot(driver, screenshotName);
		}
	}

}
