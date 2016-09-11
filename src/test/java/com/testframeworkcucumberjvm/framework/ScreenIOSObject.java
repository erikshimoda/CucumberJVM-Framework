package com.testframeworkcucumberjvm.framework;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.testframeworkcucumberjvm.framework.helpers.AndroidDriverHelper;
import com.testframeworkcucumberjvm.framework.helpers.IOSDriverHelper;

import io.appium.java_client.AppiumDriver;

public abstract class ScreenIOSObject {

	private static final Logger LOG = LoggerFactory.getLogger(ScreenIOSObject.class);

	private static final long DRIVER_WAIT_TIME = 30;
	public static AppiumDriver<WebElement> iosDriver;

	public ScreenIOSObject(AndroidDriverHelper androidDriver) {
		ScreenIOSObject.iosDriver = IOSDriverHelper.getSharedIOSDriver();
	}

	private static void changeToNativeContext() {
		int contextNum = ContextEnum.NATIVEAPP.getContext();

		iosDriver.context((String) iosDriver.getContextHandles().toArray()[contextNum]);
	}

	private static void changeToWebViewContext() {
		int contextNum = ContextEnum.WEBVIEW.getContext();

		iosDriver.context((String) iosDriver.getContextHandles().toArray()[contextNum]);
	}

	public Point getCoordinates(By element) {
		return waitForVisibilityOf(element).getLocation();
	}

	public void enterKeys(String value) {
		iosDriver.getKeyboard().pressKey(value);
	}

	public void enterKeyGo() {
		iosDriver.getKeyboard().pressKey(Keys.ENTER);
	}

	public void tapOnScreen(WebElement element, int fingers, int duration) {
		iosDriver.tap(fingers, element, duration);
	}

	public void tapOnScreen(Point coordinates, int fingers, int duration) {
		iosDriver.tap(fingers, coordinates.getX(), coordinates.getY(), duration);
	}

	public void swipe(int startx, int starty, int endx, int endy, int duration) {
		iosDriver.swipe(startx, starty, endx, endy, duration);
	}

	public static WebElement waitForVisibilityOf(final By by) {
		try {
			WebDriverWait wait = new WebDriverWait(iosDriver, DRIVER_WAIT_TIME);
			return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		} catch (TimeoutException e) {
			LOG.info(e.getMessage());
			return null;
		}
	}

	public static WebElement waitForClickabilityOf(final By by) {
		try {
			WebDriverWait wait = new WebDriverWait(iosDriver, DRIVER_WAIT_TIME);
			return wait.until(ExpectedConditions.elementToBeClickable(by));
		} catch (TimeoutException e) {
			LOG.info(e.getMessage());
			return null;
		}
	}

	public static void scrollPageUp() {
		JavascriptExecutor js = (JavascriptExecutor) iosDriver;
		HashMap<String, Double> swipeObject = new HashMap<String, Double>();
		swipeObject.put("startX", 0.50);
		swipeObject.put("startY", 0.95);
		swipeObject.put("endX", 0.50);
		swipeObject.put("endY", 0.01);
		swipeObject.put("duration", 3.0);
		js.executeScript("mobile: swipe", swipeObject);
	}

	public void swipeLeftToRight() {
		JavascriptExecutor js = (JavascriptExecutor) iosDriver;
		HashMap<String, Double> swipeObject = new HashMap<String, Double>();
		swipeObject.put("startX", 0.01);
		swipeObject.put("startY", 0.5);
		swipeObject.put("endX", 0.9);
		swipeObject.put("endY", 0.6);
		swipeObject.put("duration", 3.0);
		js.executeScript("mobile: swipe", swipeObject);
	}

	public void swipeRightToLeft() {
		JavascriptExecutor js = (JavascriptExecutor) iosDriver;
		HashMap<String, Double> swipeObject = new HashMap<String, Double>();
		swipeObject.put("startX", 0.9);
		swipeObject.put("startY", 0.5);
		swipeObject.put("endX", 0.01);
		swipeObject.put("endY", 0.5);
		swipeObject.put("duration", 3.0);
		js.executeScript("mobile: swipe", swipeObject);
	}

	public void swipeFirstCarouselFromRightToLeft() {
		JavascriptExecutor js = (JavascriptExecutor) iosDriver;
		HashMap<String, Double> swipeObject = new HashMap<String, Double>();
		swipeObject.put("startX", 0.9);
		swipeObject.put("startY", 0.2);
		swipeObject.put("endX", 0.01);
		swipeObject.put("endY", 0.2);
		swipeObject.put("duration", 3.0);
		js.executeScript("mobile: swipe", swipeObject);
	}

	public void performTapAction(WebElement elementToTap) {
		JavascriptExecutor js = (JavascriptExecutor) iosDriver;
		HashMap<String, Double> tapObject = new HashMap<String, Double>();
		tapObject.put("x", (double) 360); // in pixels from left
		tapObject.put("y", (double) 170); // in pixels from top
		tapObject.put("element", Double.valueOf(((RemoteWebElement) elementToTap).getId()));
		js.executeScript("mobile: tap", tapObject);
	}
}