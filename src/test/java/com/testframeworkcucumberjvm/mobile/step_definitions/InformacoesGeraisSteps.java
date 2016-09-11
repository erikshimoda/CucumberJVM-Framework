package com.testframeworkcucumberjvm.mobile.step_definitions;

import static org.assertj.core.api.Assertions.assertThat;

import com.testframeworkcucumberjvm.mobile.screen_object.InformacoesGeraisPage;

import cucumber.api.PendingException;
import cucumber.api.java.pt.Então;

public class InformacoesGeraisSteps {
	private InformacoesGeraisPage informacoesGeraisPage;

	public InformacoesGeraisSteps(InformacoesGeraisPage informacoesGeraisPage) {
		this.informacoesGeraisPage = informacoesGeraisPage;
	}

	@Então("^eu visualizo o Título \"(.*?)\"$")
	public void eu_visualizo_o_Título(String titulo) {
		String texto = informacoesGeraisPage.retornarLabelInformacoesGeraisId()
				.getText();
		assertThat(titulo).contains(texto);
	}
}
