package com.testframeworkcucumberjvm.mobile.screen_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.testframeworkcucumberjvm.framework.AndroidScreenObject;
import com.testframeworkcucumberjvm.framework.helpers.AndroidDriverHelper;

public class MainPage extends AndroidScreenObject {

	private By opcaoInformacoesGeraisId = By.id("btninfo");
	private By opcaoIniciarAvalicaoId = By.id("btniniciar");
	private By opcaoSignificadoId = By.id("btnsignificado");
	private By opcaoRankingId = By.id("btnranking");
	
	public MainPage(AndroidDriverHelper androidDriver) {
		super(androidDriver);
	}

	public WebElement retornarOpcaoInformacoesGeraisId() {
		return waitForClickabilityOf(opcaoInformacoesGeraisId);
	}

	public WebElement retornarOpcaoIniciarAvaliacaoId() {
		return waitForClickabilityOf(opcaoIniciarAvalicaoId);
	}

	public WebElement retornarOpcaoSignificadoId() {
		return waitForClickabilityOf(opcaoSignificadoId);
	}

	public WebElement retornarOpcaoRankingId() {
		return waitForClickabilityOf(opcaoRankingId);
	}
	
	public void clicoNoVoltar() {
		androidDriver.navigate().back();
	}
}