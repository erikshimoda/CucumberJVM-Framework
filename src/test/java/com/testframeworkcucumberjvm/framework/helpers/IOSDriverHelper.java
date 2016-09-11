package com.testframeworkcucumberjvm.framework.helpers;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.assertj.core.util.Strings;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class IOSDriverHelper extends EventFiringWebDriver {
	private static final Logger LOG = LoggerFactory.getLogger(IOSDriverHelper.class);

	private static String APPIUM_HOST;
	private static String APPIUM_PORT;
	private static String PLATFORM;
	private static String PLATFORM_VERSION;
	private static String BUNDLE_NAME;
	private static String DEVICE_NAME;
	private static String APP_DIR;
	private static String APP_FILE;
	private static String UDID;
	private static String IOS_SERVER_URL;
	private static String IOS_CONTEXT_WEBVIEW;

	private static AppiumDriver<WebElement> SHARED_IOS_DRIVER = null;

	private static final Thread CLOSE_THREAD = new Thread() {
		@Override
		public void run() {
			SHARED_IOS_DRIVER.quit();
		}
	};

	static {
		// Props.loadRunConfigProps(RUN_CONFIG_PROPERTIES);

		PropertiesLoader.loadRunConfigProps("/enviroment.properties");

		APPIUM_HOST = PropertiesLoader.getValor("ios.host");
		APPIUM_PORT = PropertiesLoader.getValor("ios.host.port");
		PLATFORM = PropertiesLoader.getValor("ios.platform");
		PLATFORM_VERSION = PropertiesLoader.getValor("ios.platform.version");
		DEVICE_NAME = PropertiesLoader.getValor("ios.device.name");
		APP_DIR = PropertiesLoader.getValor("ios.app.dir");
		APP_FILE = PropertiesLoader.getValor("ios.file");
		UDID = PropertiesLoader.getValor("ios.uuid");
		IOS_CONTEXT_WEBVIEW = PropertiesLoader.getValor("ios.context.webview").toLowerCase();

		SHARED_IOS_DRIVER = startAppiumDriver();
		Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
	}

	public IOSDriverHelper() {
		super(SHARED_IOS_DRIVER);
	}

	private static AppiumDriver<WebElement> startAppiumDriver() {
		DesiredCapabilities capabilities = getAppiumDesiredCapabilities();
		try {
			StringBuilder url = new StringBuilder();
			url.append("http://");
			url.append(APPIUM_HOST);
			url.append(":");
			url.append(APPIUM_PORT);
			url.append("/wd/hub");

			IOS_SERVER_URL = url.toString();

			SHARED_IOS_DRIVER = new IOSDriver<>(new URL(IOS_SERVER_URL), capabilities);
		} catch (MalformedURLException e) {
			StringBuilder errorMessage = new StringBuilder();
			errorMessage.append("An error ocurred when instantiate the IOS driver on server, please check: ");
			errorMessage.append(IOS_SERVER_URL);
			LOG.error(errorMessage.toString(), e);
		}
		return SHARED_IOS_DRIVER;
	}

	private static DesiredCapabilities getAppiumDesiredCapabilities() {
		DesiredCapabilities capabilities = new DesiredCapabilities();

		capabilities.setCapability(MobileCapabilityType.AUTO_WEBVIEW, IOS_CONTEXT_WEBVIEW);
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, DEVICE_NAME);
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, PLATFORM);
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, PLATFORM_VERSION);

		if (!Strings.isNullOrEmpty(APP_DIR) && !Strings.isNullOrEmpty(APP_FILE)) {
			File appDir = new File(APP_DIR);
			File app = new File(appDir, APP_FILE);
			capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		} else {
			capabilities.setCapability(MobileCapabilityType.APP, BUNDLE_NAME);
		}

		if (!Strings.isNullOrEmpty(UDID)) {
			capabilities.setCapability(MobileCapabilityType.UDID, UDID);

		}

		return capabilities;
	}

	@Override
	public void close() {
		if (Thread.currentThread() != CLOSE_THREAD) {
			throw new UnsupportedOperationException(
					"YOU SHOULD NOT TERMINATE THE DRIVER, WAIT THAT WILL AUTOMATICALLY CLOSED BY JVM!");
		}
		super.close();
	}

	public static AppiumDriver<WebElement> getSharedIOSDriver() {
		return SHARED_IOS_DRIVER;
	}
}
