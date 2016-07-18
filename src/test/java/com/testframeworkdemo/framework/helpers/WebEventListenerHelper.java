package com.testframeworkdemo.framework.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebEventListenerHelper implements WebDriverEventListener {
	
	private static final Logger LOG = LoggerFactory.getLogger(WebEventListenerHelper.class);

	public void beforeNavigateTo(String url, WebDriver driver) {
		LOG.debug("Carregando a URL: ", url);
	}

	public void afterNavigateTo(String url, WebDriver driver) {
		LOG.debug("Carregado a URL: ", url);
	}

	public void beforeChangeValueOf(WebElement element, WebDriver driver) {
		LOG.debug("Valor do elemento antes de alteração: ", element);
	}

	public void afterChangeValueOf(WebElement element, WebDriver driver) {
		LOG.debug("Valor do elemento alterado para: ", element);
	}

	public void beforeClickOn(WebElement element, WebDriver driver) {
		LOG.debug("Aguardando o elemento para clicar: ", element);
	}

	public void afterClickOn(WebElement element, WebDriver driver) {
		LOG.debug("Cliquei no elemento", element);
	}

	public void beforeNavigateBack(WebDriver driver) {
		LOG.debug("Navegar para a pagina anterior");
	}

	public void afterNavigateBack(WebDriver driver) {
		LOG.debug("Navegado para a pagina anterior");
	}

	public void beforeNavigateForward(WebDriver driver) {
		LOG.debug("Navegar para a proxima pagina");
	}

	public void afterNavigateForward(WebDriver driver) {
		LOG.debug("Navegado para a proxima pagina");
	}

	public void onException(Throwable error, WebDriver driver) {
		LOG.error("Ocorreu o erro: ", error);
		System.err.println("Ocorreu o erro: " + error);
	}

	public void beforeFindBy(By by, WebElement element, WebDriver driver) {
		LOG.debug("Tentando encontrar o elemento por: ", by);
	}

	public void afterFindBy(By by, WebElement element, WebDriver driver) {
		LOG.debug("Encontrado o elemento por: ", by);
	}

	/*
	 * metodos que nao sao substituidos
	 */
	public void beforeScript(String script, WebDriver driver) {
	}

	public void afterScript(String script, WebDriver driver) {
	}
}
