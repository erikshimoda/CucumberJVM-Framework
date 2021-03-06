package com.testframeworkcucumberjvm.web.step_definitions;

import java.util.HashMap;
import java.util.List;

import com.testframeworkcucumberjvm.framework.helpers.DadosHelper;
import com.testframeworkcucumberjvm.web.page_object.AbrirContaCorrentePage;

import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;

public class AbrirContaCorrenteSteps {
	
    private AbrirContaCorrentePage abrirContaCorrentePage;
    public List<HashMap<String,String>> datamap;

    public AbrirContaCorrenteSteps(AbrirContaCorrentePage abrirContaCorrentePage) {
        this.abrirContaCorrentePage = abrirContaCorrentePage;
//        StringBuilder dir = new StringBuilder();
//        dir.append(this.getClass().getClassLoader().getResourceAsStream(properties));
        
//     	this.getClass().getClassLoader().getResourceAsStream(properties);
        datamap = DadosHelper.dados(System.getProperty("user.dir")+"//src//test//resources//massaDeTestes/MassaDeTestes.xlsx","TestData");
     	
    }
    
    @Quando("^preencho o nome completo com a linha \"(.*?)\" da Massa de Testes$")
    public void preencho_o_nome_completo_com_a_linha_da_Massa_de_Testes(String linha) {
        int indice = Integer.parseInt(linha)-1;
//        for(HashMap<String, String> h:datamap)
//        {
//            System.out.println(h.keySet());
//            System.out.println(h.values());
//        }
		abrirContaCorrentePage.retornarInputNomeName().sendKeys(datamap.get(indice).get("Nome"));
    }

    @Quando("^preencho o nome da mae com a linha \"(.*?)\" da Massa de Testes$")
    public void preencho_o_nome_da_mae_com_a_linha_da_Massa_de_Testes(String linha) {
        int indice = Integer.parseInt(linha)-1;
		abrirContaCorrentePage.retornarInputNomeMaeName().sendKeys(datamap.get(indice).get("NomeMae"));
    }

	@Quando("^escolho a opcao Eu Trabalho$")
	public void escolho_a_opcao_Eu_Trabalho() throws Throwable {
		abrirContaCorrentePage.retornarOpcaoEuTrabalhoId().click();
	}

	@Quando("^escolho a opcao Para Voce$")
	public void escolho_a_opcao_para_voce() throws Throwable {
		abrirContaCorrentePage.retornarOpcaoParaVoceId().click();
		Thread.sleep(1000);
	}

	@Quando("^preencho o nome completo \"(.*?)\"$")
	public void preencho_o_nome_completo(String nome) throws Throwable {
		abrirContaCorrentePage.retornarInputNomeName().sendKeys(nome);
		Thread.sleep(1000);
	}

	@Quando("^preencho com o cpf \"(.*?)\"$")
	public void preencho_com_o_cpf(String cpf) throws Throwable {
		abrirContaCorrentePage.retornarInputCPFId().sendKeys(cpf);
		Thread.sleep(1000);
	}

	@Quando("^preencho a data de nascimento \"(.*?)\"$")
	public void preencho_a_data_de_nascimento(String dtNascimento) throws Throwable {
		abrirContaCorrentePage.retornarInputDtNascimentoId().sendKeys(dtNascimento);
		Thread.sleep(1000);
	}

	@Quando("^preencho o e-mail \"(.*?)\"$")
	public void preencho_o_e_mail(String email) throws Throwable {
		abrirContaCorrentePage.retornarInputEmailName().sendKeys(email);
		Thread.sleep(1000);
	}

	@Quando("^seleciono o sexo Masculino$")
	public void seleciono_o_sexo_Masculino() throws Throwable {
		abrirContaCorrentePage.retornarSelectSexoName().sendKeys("M");
		Thread.sleep(1000);
	}

	@Quando("^seleciono o sexo Feminino$")
	public void seleciono_o_sexo_Feminino() throws Throwable {
		abrirContaCorrentePage.retornarSelectSexoName().sendKeys("F");
		Thread.sleep(1000);
	}
	
	@Quando("^preencho o nome da mae \"(.*?)\"$")
	public void preencho_o_nome_da_mae(String nomeMae) throws Throwable {
		abrirContaCorrentePage.retornarInputNomeMaeName().sendKeys(nomeMae);
		Thread.sleep(1000);
	}

	@Quando("^preencho o celular \"(.*?)\"$")
	public void preencho_o_celular(String telefoneCelular) throws Throwable {
		abrirContaCorrentePage.retornarInputCelularId().sendKeys(telefoneCelular);
		Thread.sleep(1000);
	}

	@Quando("^preencho o telefone fixo \"(.*?)\"$")
	public void preencho_o_telefone_fixo(String telefoneResidencial) throws Throwable {
		abrirContaCorrentePage.retornarInputResidencialId().sendKeys(telefoneResidencial);
		Thread.sleep(1000);
	}
	
	@Entao("^eu informo a renda$")
	public void eu_informo_a_renda() throws Throwable {
		abrirContaCorrentePage.retornarSliderRendaId().click();
		Thread.sleep(1000);
	}

	@Quando("^clico no botao Continuar$")
	public void clico_no_botao_Continuar() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
	}

	@Entao("^eu visualizo o formulario de Endereco$")
	public void eu_visualizo_o_formulario_de_Endereco() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
	}

	@Entao("^eu preencho o cep \"(.*?)\"$")
	public void eu_preencho_o_cep(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
	}

	@Entao("^eu clico no botao Continuar$")
	public void eu_clico_no_botao_Continuar() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
	}

	@Entao("^eu visualizo um alerta de campo obrigatorio para o numero com a mensagem \"(.*?)\"$")
	public void eu_visualizo_um_alerta_de_campo_obrigatorio_para_o_numero_com_a_mensagem(
			String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
	}
}
