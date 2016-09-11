package com.testframeworkcucumberjvm.mobile.screen_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.testframeworkcucumberjvm.framework.AndroidScreenObject;
import com.testframeworkcucumberjvm.framework.helpers.AndroidDriverHelper;

public class InformacoesGeraisPage extends AndroidScreenObject {

	private By labelInformacoesGeraisId = By
			.xpath("//android.widget.GridView/android.view.View[1]/android.view.View/android.view.View");

	public InformacoesGeraisPage(AndroidDriverHelper androidDriver) {
		super(androidDriver);
	}

	public WebElement retornarLabelInformacoesGeraisId() {
		return waitForClickabilityOf(labelInformacoesGeraisId);
	}
}