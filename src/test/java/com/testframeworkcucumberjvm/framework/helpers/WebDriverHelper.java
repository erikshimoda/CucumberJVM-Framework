package com.testframeworkcucumberjvm.framework.helpers;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebDriverHelper  {
	private static final Logger LOG = LoggerFactory
			.getLogger(WebDriverHelper.class);
	private static EventFiringWebDriver SHARED_DRIVER = null;
	private static EventFiringWebDriver efd;
	public static WebDriver webDriver;
	// private static WebDriver WEBDRIVER = null;
	private static final Thread CLOSE_THREAD = new Thread() {

		@Override
		public void run() {
			SHARED_DRIVER.quit();
		}
	};

	private static String BROWSER;
	private static String PLATAFORMA;
	private static String DRIVER_ROOT_DIR;
	private static String SELENIUM_HOST;
	private static String SELENIUM_PORTA;
	private static String SELENIUM_REMOTE_URL;
	private static Dimension BROWSER_WINDOW_SIZE;
	private static String BROWSER_WINDOW_WIDTH;
	private static String BROWSER_WINDOW_HEIGHT;
	private static String BROWSER_IE_VERSAO;
	private static String SAUCE_ADVISOR;
	private static String SAUCE_RECORD_VIDEO;
	private static String SAUCE_SCREENSHOT;

	static {
		// PropertiesLoader.PropertiesLoader("ambiente.properties");
		// PropertiesLoader.loadRunConfigProperties("environment.properties");
        PropertiesLoader.loadRunConfigProps("/environment.properties");

//		PropertiesLoader.loadRunConfigProps();

//		SELENIUM_HOST = PropertiesLoader.getValor("webdriver.host");
		
        SELENIUM_HOST = PropertiesLoader.getValor("webdriver.host");
		SELENIUM_PORTA = PropertiesLoader.getValor("webdriver.porta");
		PLATAFORMA = PropertiesLoader.getValor("plataforma");
		BROWSER = PropertiesLoader.getValor("browser");
		BROWSER_IE_VERSAO = PropertiesLoader.getValor("browser.ie.versao");
		BROWSER_WINDOW_WIDTH = PropertiesLoader.getValor("browser.width");
		BROWSER_WINDOW_HEIGHT = PropertiesLoader.getValor("browser.height");

		DRIVER_ROOT_DIR = PropertiesLoader.getValor("driver.root.dir");

		SAUCE_ADVISOR = PropertiesLoader.getValor("sauce.advisor");
		SAUCE_RECORD_VIDEO = PropertiesLoader.getValor("record.video");
		SAUCE_SCREENSHOT = PropertiesLoader.getValor("record.screenshots");

		// if (!DRIVER_ROOT_DIR.equals("DEFAULT_PATH")) {
		// System.setProperty("webdriver.chrome.driver", getDriverPath());
		// System.setProperty("webdriver.ie.driver", getDriverPath());
		// System.setProperty("phantomjs.binary.path", getDriverPath());
		// }

		try {
			switch (BROWSER.toLowerCase()) {
			case ("chrome"):
				startChromeDriver();
				break;
			case ("firefox"):
				startFireFoxDriver();
				break;
			case ("iexplore"):
				startIEDriver();
				break;
			case ("phantomjs"):
				startPhantomJsDriver();
				break;
			case ("sauce"):
				startSauceDriver();
				break;
			default:
				throw new IllegalArgumentException("Browser " + BROWSER
						+ " or PLATAFORMA " + PLATAFORMA
						+ " type not supported");
			}

		} catch (IllegalStateException e) {
			LOG.error("FIX path for driver.root.dir in pom.xml "
					+ DRIVER_ROOT_DIR + " Browser parameter " + BROWSER
					+ " PLATAFORMA parameter " + PLATAFORMA
					+ " type not supported");
		}
		Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
	}

	public WebDriverHelper() {
		super();
	}

	// private static String getDriverPath() {
	// DRIVER_PATH = Props.getProp("driver.root.dir");
	// return DRIVER_PATH;
	// }

	private static void startIEDriver() {
		DesiredCapabilities capabilities = getInternetExploreDesiredCapabilities();
		if (SELENIUM_HOST == null || SELENIUM_HOST.isEmpty()) {
			webDriver = new InternetExplorerDriver(capabilities);
			efd = new EventFiringWebDriver(webDriver);
			efd.register(new WebEventListenerHelper());
			// SHARED_DRIVER = new InternetExplorerDriver(capabilities);
			SHARED_DRIVER = efd;
		} else {
			try {
				SHARED_DRIVER = getRemoteWebDriver(capabilities);
			} catch (MalformedURLException e) {
				LOG.error(SELENIUM_REMOTE_URL + " Error " + e.getMessage());
			}
		}
		SHARED_DRIVER.manage().deleteAllCookies();
		configWindowSize(BROWSER_WINDOW_WIDTH, BROWSER_WINDOW_HEIGHT);
	}

	private static void startFireFoxDriver() {
		DesiredCapabilities capabilities = getFireFoxDesiredCapabilities();
		if (SELENIUM_HOST == null || SELENIUM_HOST.isEmpty()) {
			webDriver =  new FirefoxDriver();
			efd = new EventFiringWebDriver(webDriver);
			efd.register(new WebEventListenerHelper());
			// SHARED_DRIVER = new FirefoxDriver();
			SHARED_DRIVER = efd;
		} else {
			try {
				SHARED_DRIVER = getRemoteWebDriver(capabilities);
			} catch (MalformedURLException e) {
				LOG.error(SELENIUM_REMOTE_URL + " Error " + e.getMessage());
			}
		}
		SHARED_DRIVER.manage().deleteAllCookies();
		configWindowSize(BROWSER_WINDOW_WIDTH, BROWSER_WINDOW_HEIGHT);
	}

	private static void startPhantomJsDriver() {
		DesiredCapabilities capabilities = getPhantomJsCapabilities();
////		if (SELENIUM_HOST == null || SELENIUM_HOST.isEmpty())
//			SHARED_DRIVER = new PhantomJSDriver(capabilities);
//		else {
//			try {
//				SHARED_DRIVER = getRemoteWebDriver(capabilities);
//			} catch (MalformedURLException e) {
//				LOG.error(SELENIUM_REMOTE_URL + " Error " + e.getMessage());
//			}
//		}
		configWindowSize(BROWSER_WINDOW_WIDTH, BROWSER_WINDOW_HEIGHT);
	}

	private static void startSauceDriver() {
		DesiredCapabilities capabilities = getSauceCapabilities();
//		try {
//			SHARED_DRIVER = new RemoteWebDriver(
//					new URL(
//							"http://username-string:access-key-string@ondemand.saucelabs.com:80/wd/hub"),
//					capabilities);
//		} catch (MalformedURLException e) {
//			LOG.error(" Error Sauce Url " + e.getMessage());
//		}
	}

	private static void startChromeDriver() {
		DesiredCapabilities capabilities = getChromeDesiredCapabilities();

		if (SELENIUM_HOST == null || SELENIUM_HOST.isEmpty()) {
			webDriver =  new ChromeDriver(
					ChromeDriverService.createDefaultService(), capabilities);
			efd = new EventFiringWebDriver(webDriver);
			efd.register(new WebEventListenerHelper());
//			SHARED_DRIVER = new ChromeDriver(
//					ChromeDriverService.createDefaultService(), capabilities);
			SHARED_DRIVER = efd;
		} else {
			try {
				SHARED_DRIVER = getRemoteWebDriver(capabilities);
			} catch (MalformedURLException e) {
				LOG.error(SELENIUM_REMOTE_URL, " Error ", e.getMessage());
			}
		}
		SHARED_DRIVER.manage().deleteAllCookies();
		configWindowSize(BROWSER_WINDOW_WIDTH, BROWSER_WINDOW_HEIGHT);
	}

	private static void configWindowSize(String width, String height) {
		if (width != null && height != null && !width.isEmpty()
				&& !height.isEmpty()) {
			int x = Integer.parseInt(width);
			int y = Integer.parseInt(height);
			BROWSER_WINDOW_SIZE = new Dimension(x, y);
			SHARED_DRIVER.manage().window().setSize(BROWSER_WINDOW_SIZE);
		} else {
			SHARED_DRIVER.manage().window().maximize();
		}
	}

	private static DesiredCapabilities getChromeDesiredCapabilities() {

		LoggingPreferences logs = new LoggingPreferences();
		logs.enable(LogType.DRIVER, Level.OFF);
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(CapabilityType.LOGGING_PREFS, logs);

		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--disable-web-security");
		chromeOptions.addArguments("--test-type");
		capabilities.setCapability("chrome.verbose", false);

		capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
		return capabilities;
	}

	private static DesiredCapabilities getFireFoxDesiredCapabilities() {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		capabilities.setBrowserName("firefox");

		capabilities.setCapability("disable-restore-session-state", true);
		return capabilities;

	}

	private static DesiredCapabilities getInternetExploreDesiredCapabilities() {
		LoggingPreferences logs = new LoggingPreferences();
		logs.enable(LogType.DRIVER, Level.OFF);
		DesiredCapabilities capabilities = DesiredCapabilities
				.internetExplorer();
		capabilities.setCapability(CapabilityType.LOGGING_PREFS, logs);
		capabilities
				.setCapability(
						InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
						true);
		capabilities.setVersion(BROWSER_IE_VERSAO);
		return capabilities;
	}

	private static DesiredCapabilities getPhantomJsCapabilities() {
		LoggingPreferences logs = new LoggingPreferences();
		logs.enable(LogType.DRIVER, Level.OFF);
		DesiredCapabilities capabilities = DesiredCapabilities.phantomjs();
		capabilities.setCapability(CapabilityType.LOGGING_PREFS, logs);
		// capabilities
		// .setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
		// getDriverPath());
		return capabilities;
	}

	private static DesiredCapabilities getSauceCapabilities() {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("browserName", BROWSER);
		capabilities.setCapability("PLATAFORMA", PLATAFORMA);
		capabilities.setCapability("sauce-advisor", SAUCE_ADVISOR);
		capabilities.setCapability("record-video", SAUCE_RECORD_VIDEO);
		capabilities.setCapability("record-screenshots", SAUCE_SCREENSHOT);
		return capabilities;
	}

	private static EventFiringWebDriver getRemoteWebDriver(
			DesiredCapabilities capabilities) throws MalformedURLException {
		SELENIUM_REMOTE_URL = "http://" + SELENIUM_HOST + ":" + SELENIUM_PORTA
				+ "/wd/hub";
		LOG.info(SELENIUM_REMOTE_URL + " Checking Selenium Remote URL");
		RemoteWebDriver rwd = new RemoteWebDriver(new URL(SELENIUM_REMOTE_URL),
				(capabilities));
		efd = new EventFiringWebDriver(rwd);
		efd.register(new WebEventListenerHelper());
		return efd;
	}

	public static WebDriver getSharedDriver() {
		return SHARED_DRIVER;
	}

	public static void resizeBrowserWindow(Dimension dimension) {
		getSharedDriver().manage().window().setSize(dimension);
	}

//	public void close() {
//		if (Thread.currentThread() != CLOSE_THREAD) {
//			throw new UnsupportedOperationException(
//					"VOCE NAO DEVE ENCERRAR O WEBDRIVER, POIS O MESMO E COMPARTILHADO! AGUARDE QUE SERA FECHADO AUTOMATICAMENTE!");
//		}
//		super.close();
//	}
}