package com.testframeworkcucumberjvm.web.step_definitions;

import java.net.URL;

import org.openqa.selenium.WebDriver;

import com.testframeworkcucumberjvm.framework.helpers.WebDriverHelper;

import cucumber.api.java.pt.Dado;

public class ContextoSteps {
	public static WebDriver webDriver;
    private static URL basePath;


	public ContextoSteps (WebDriverHelper webDriver) {
		ContextoSteps.webDriver = WebDriverHelper.getSharedDriver();
	}

	@Dado("que eu acesso \"([^\"]*)\"$")
	public void queEuAcesso(String url) {
//		PropertiesLoader.loadRunConfigProperties("environment.properties");
//        basePath = new URL(PropertiesLoader.getProp("site.url"));

//		String teste = PropertiesLoader.getValor("site.url");
		webDriver.get(url);
	}
}
