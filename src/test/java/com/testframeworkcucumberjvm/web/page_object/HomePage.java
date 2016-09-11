package com.testframeworkdemo.page_object.web;

import org.openqa.selenium.By;

import com.testframeworkdemo.framework.PageObject;

public class HomePage extends PageObject {
    
	private By abraSuaContaClass = By.className("conta");
    
	public void abrirSuaConta() {
		waitForExpectedElement(abraSuaContaClass).click();
		switchToLandingPage();
	}
}