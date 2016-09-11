package com.testframeworkcucumberjvm.framework.helpers;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.assertj.core.util.Strings;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AndroidDriverHelper extends EventFiringWebDriver {
	private static final Logger LOG = LoggerFactory.getLogger(AndroidDriverHelper.class);

	private static String APPIUM_HOST;
	private static String APPIUM_PORT;
	private static String ANDROID_PLATFORM;
	private static String ANDROID_PLATFORM_VERSION;
	private static String BUNDLE_NAME;
	private static String ANDROID_DEVICE_NAME;
	private static String ANDROID_APP_DIR;
	private static String ANDROID_APP_FILE;
	private static String UDID;
	private static String ANDROID_SERVER_URL;
	private static String ANDROID_CONTEXT_WEBVIEW;

	private static AppiumDriver<WebElement> SHARED_ANDROID_DRIVER = null;

	private static final Thread CLOSE_THREAD = new Thread() {
		@Override
		public void run() {
			SHARED_ANDROID_DRIVER.quit();
		}
	};

	static {
		PropertiesLoader.loadRunConfigProps("/enviroment.properties");

		APPIUM_HOST = PropertiesLoader.getValor("ios.host");
		APPIUM_PORT = PropertiesLoader.getValor("ios.host.port");
		ANDROID_PLATFORM = PropertiesLoader.getValor("ios.platform");
		ANDROID_PLATFORM_VERSION = PropertiesLoader.getValor("ios.platform.version");
		ANDROID_DEVICE_NAME = PropertiesLoader.getValor("ios.device.name");
		ANDROID_APP_DIR = PropertiesLoader.getValor("ios.app.dir");
		ANDROID_APP_FILE = PropertiesLoader.getValor("ios.file");
		UDID = PropertiesLoader.getValor("ios.uuid");
		ANDROID_CONTEXT_WEBVIEW = PropertiesLoader.getValor("ios.context.webview").toLowerCase();

		SHARED_ANDROID_DRIVER = startAppiumDriver();
		Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
	}

	public AndroidDriverHelper() {
		super(SHARED_ANDROID_DRIVER);
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

			ANDROID_SERVER_URL = url.toString();

			SHARED_ANDROID_DRIVER = new AndroidDriver<>(new URL(ANDROID_SERVER_URL), capabilities);
		} catch (MalformedURLException e) {
			StringBuilder errorMessage = new StringBuilder();
			errorMessage.append("An error ocurred when instantiate the IOS driver on server, please check: ");
			errorMessage.append(ANDROID_SERVER_URL);
			LOG.error(errorMessage.toString(), e);
		}
		return SHARED_ANDROID_DRIVER;
	}

	private static DesiredCapabilities getAppiumDesiredCapabilities() {
		DesiredCapabilities capabilities = new DesiredCapabilities();

		capabilities.setCapability(MobileCapabilityType.AUTO_WEBVIEW, ANDROID_CONTEXT_WEBVIEW);
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, ANDROID_DEVICE_NAME);
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, ANDROID_PLATFORM);
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, ANDROID_PLATFORM_VERSION);

		if (!Strings.isNullOrEmpty(ANDROID_APP_DIR) && !Strings.isNullOrEmpty(ANDROID_APP_FILE)) {
			File appDir = new File(ANDROID_APP_DIR);
			File app = new File(appDir, ANDROID_APP_FILE);
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
					"VOCE NAO DEVE ENCERRAR O WEBDRIVER, POIS O MESMO E COMPARTILHADO! AGUARDE QUE SERA FECHADO AUTOMATICAMENTE!");
		}
		super.close();
	}

	public static AppiumDriver<WebElement> getSharedAndroidDriver() {
		return SHARED_ANDROID_DRIVER;
	}
}
