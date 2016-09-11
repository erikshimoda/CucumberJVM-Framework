package com.testframeworkcucumberjvm.framework.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.testframeworkcucumberjvm.framework.helpers.WebDriverHelper;

public class Evidencia  {
	
	private static final Logger LOG = LoggerFactory.getLogger(Evidencia.class);

	protected WebDriver webDriver;
	
    protected Evidencia() {
        this.webDriver = WebDriverHelper.getSharedDriver();
    }
	
	public void gerarEvidencia() {
		WebDriver wd = new Augmenter().augment(webDriver);
		File arquivo = ((TakesScreenshot) wd).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(arquivo, new File("src/test/resources/erro.png"));
		} catch (IOException e) {
			LOG.error("Ao gerar a evidencia, ocorreu o erro: ", e);
		}
	}
}
