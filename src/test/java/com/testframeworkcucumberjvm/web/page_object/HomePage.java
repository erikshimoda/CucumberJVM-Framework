package com.testframeworkcucumberjvm.web.page_object;

import org.openqa.selenium.By;

import com.testframeworkcucumberjvm.framework.PageObject;

public class HomePage extends PageObject {
    
	private By abraSuaContaClass = By.className("conta");
    
	public void abrirSuaConta() {
		waitForExpectedElement(abraSuaContaClass).click();
		switchToLandingPage();
	}
}