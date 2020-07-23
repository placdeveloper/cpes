/*
 * SICOOB
 * 
 * InclusaoBeneficiarioINSS.java(br.com.sicoob.capes.processamento.negocio.dominio.inss.InclusaoBeneficiarioINSS)
 */
package br.com.sicoob.capes.processamento.negocio.dominio.inss;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaFisica;
import br.com.sicoob.capes.processamento.negocio.dominio.inss.cor.AlteracaoNomeMaeBeneficiarioHandler;
import br.com.sicoob.capes.processamento.negocio.dominio.inss.cor.AtualizacaoCadastroBeneficiarioHandler;
import br.com.sicoob.capes.processamento.negocio.dominio.inss.cor.BeneficiarioINSSAbstractHandler;
import br.com.sicoob.capes.processamento.negocio.dominio.inss.cor.InclusaoNovoBeneficiarioHandler;
import br.com.sicoob.capes.processamento.negocio.dominio.inss.cor.ReplicacaoBeneficiarioHandler;
import br.com.sicoob.capes.processamento.negocio.dto.RequisicaoBeneficiarioDTO;

/**
 * @author Erico.Junior
 * 
 */
public final class InclusaoBeneficiarioINSS {

	/** O atributo instance. */
	private static InclusaoBeneficiarioINSS instance = new InclusaoBeneficiarioINSS();
	
	/** O atributo cadeia inclusao. */
	private transient BeneficiarioINSSAbstractHandler cadeiaInclusao;
	
	/**
	 * Recupera uma instância única de InclusaoBeneficiarioINSS.
	 * 
	 * @return uma intância única InclusaoBeneficiarioINSS
	 */
	public static InclusaoBeneficiarioINSS getInstance() {
		return instance;
	}

	/**
	 * Cria uma nova instância de inclusao beneficiario inss.
	 */
	private InclusaoBeneficiarioINSS() {

		InclusaoNovoBeneficiarioHandler primeiro = new InclusaoNovoBeneficiarioHandler();
		ReplicacaoBeneficiarioHandler segundo = new ReplicacaoBeneficiarioHandler();
		AtualizacaoCadastroBeneficiarioHandler terceiro = new AtualizacaoCadastroBeneficiarioHandler(); 
		AlteracaoNomeMaeBeneficiarioHandler quarto = new AlteracaoNomeMaeBeneficiarioHandler();
		
		primeiro.setProximo(segundo);
		segundo.setProximo(terceiro);
		terceiro.setProximo(quarto);
		
		this.cadeiaInclusao = primeiro;
	}
	
	/**
	 * Incluir.
	 * 
	 * @param requisicao
	 *            the requisicao
	 * @return pessoa fisica
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	public PessoaFisica incluir(RequisicaoBeneficiarioDTO requisicao) throws BancoobException {
		return cadeiaInclusao.processarRequisicao(requisicao);
	}
	
}
