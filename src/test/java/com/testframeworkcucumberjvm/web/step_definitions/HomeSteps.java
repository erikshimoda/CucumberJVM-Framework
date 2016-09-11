package com.testframeworkcucumberjvm.web.step_definitions;

import com.testframeworkcucumberjvm.web.page_object.HomePage;

import cucumber.api.java.pt.Quando;

public class HomeSteps {
	private HomePage homePage;

	public HomeSteps(HomePage homePage) {
		this.homePage = homePage;
	}

	@Quando("^eu clico na opcao Abra sua Conta$")
	public void eu_clico_na_opcao_Abra_sua_Conta() throws Throwable {
		homePage.abrirSuaConta();
		Thread.sleep(1000);
	}
}
