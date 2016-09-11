package com.testframeworkcucumberjvm.mobile.screen_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.testframeworkcucumberjvm.framework.AndroidObject;
import com.testframeworkcucumberjvm.framework.helpers.AndroidDriverHelper;

public class RankingPage extends AndroidObject {

	private By labelNomeId = By.id("rownome");
	private By labelIdadeId = By.id("rowidade");
	private By labelPontosId = By.id("rowpontos");
	private By labelTempoId = By.id("rowtempo");
	private By labelQiId = By.id("rowqi");

	public RankingPage(AndroidDriverHelper androidDriver) {
		super(androidDriver);
	}

	public WebElement retornarLabelNomeId() {
		return waitForClickabilityOf(labelNomeId);
	}

	public WebElement retornarLabelIdadeId() {
		return waitForClickabilityOf(labelIdadeId);
	}

	public WebElement retornarLabelPontosrId() {
		return waitForClickabilityOf(labelPontosId);
	}

	public WebElement retornarLabelTempoId() {
		return waitForVisibilityOf(labelTempoId);
	}

	public WebElement retornarLabelQiId() {
		return waitForVisibilityOf(labelQiId);
	}
}