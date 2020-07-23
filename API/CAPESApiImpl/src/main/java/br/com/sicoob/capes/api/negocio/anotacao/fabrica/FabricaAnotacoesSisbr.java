/*
 * SICOOB
 * 
 * FabricaAnotacoesSisbr.java(br.com.sicoob.capes.api.negocio.anotacao.fabrica.FabricaAnotacoesSisbr)
 */
package br.com.sicoob.capes.api.negocio.anotacao.fabrica;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.sicoob.capes.api.negocio.anotacao.builder.AnotacaoBuilder;
import br.com.sicoob.capes.api.negocio.anotacao.handler.sisbr.AnotacaoAvalistaInadimplencia90DiasHandler;
import br.com.sicoob.capes.api.negocio.anotacao.handler.sisbr.AnotacaoAvalistaOperacaoPrejuizoHandler;
import br.com.sicoob.capes.api.negocio.anotacao.handler.sisbr.AnotacaoInadimplencia15DiasHandler;
import br.com.sicoob.capes.api.negocio.anotacao.handler.sisbr.AnotacaoInadimplencia30DiasHandler;
import br.com.sicoob.capes.api.negocio.anotacao.handler.sisbr.AnotacaoInadimplencia90DiasHandler;
import br.com.sicoob.capes.api.negocio.anotacao.handler.sisbr.AnotacaoOperacaoTransferidaPrejuizoHandler;
import br.com.sicoob.capes.api.negocio.anotacao.handler.sisbr.AnotacaoSisbrHandler;
import br.com.sicoob.capes.comum.negocio.dto.AnotacaoSisbrDTO;
import br.com.sicoob.capes.comum.negocio.enums.OrigemInformacaoEnum;
import br.com.sicoob.capes.negocio.entidades.Anotacao;
import br.com.sicoob.capes.negocio.entidades.OrigemInformacao;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * Fábrica de anotações para o SISBR.
 * 
 * @author Erico.Junior
 */
public final class FabricaAnotacoesSisbr {

	/** O atributo instance. */
	private static FabricaAnotacoesSisbr instance = new FabricaAnotacoesSisbr();

	/**
	 * Cria a fábrica de anotações do SISBR.
	 * 
	 * @return a fábrica de anotações do sisbr.
	 */
	public static FabricaAnotacoesSisbr getInstance() {
		return instance;
	}
	
	/**
	 * Construtor da fábrica de anotações para o SISBR.
	 */
	private FabricaAnotacoesSisbr() {
		super();
	}
	
	/**
	 * Cria as anotações.
	 * @param objetoConsulta O objeto com o retorno da consulta.
	 * @param pessoa A pessoa para a anotação.
	 * @param idConsultaExterna O identificador da consulta externa.
	 * @return A lista de anotações.
	 */
	public List<Anotacao> criarAnotacoes(AnotacaoSisbrDTO objetoConsulta, PessoaCompartilhamento pessoa) {

		Set<AnotacaoBuilder> builders = obterAnotacoesBuilder(objetoConsulta);
		if (builders == null || builders.isEmpty()) {
			builders = new HashSet<AnotacaoBuilder>();
		}

		Date dataAtual = new Date();
		List<Anotacao> anotacoes = new ArrayList<Anotacao>();
		
		for (AnotacaoBuilder anotacaoBuilder : builders) {
			Anotacao anotacao = anotacaoBuilder.criarAnotacao(dataAtual);
			anotacao.setPessoaCompartilhamento(pessoa);
			anotacao.setUsuarioInclusao(null);

			OrigemInformacao origemInformacao = new OrigemInformacao();
			origemInformacao.setId(OrigemInformacaoEnum.SISBR.getIdOrigemInformacao());

			anotacao.setOrigemInformacao(origemInformacao);
			anotacoes.add(anotacao);
		}
		
		return anotacoes;
	}	
	
	/**
	 * Obter anotacoes builder.
	 * 
	 * @param objetoConsulta
	 *            the objeto consulta
	 * @return sets the
	 */
	protected Set<AnotacaoBuilder> obterAnotacoesBuilder(AnotacaoSisbrDTO objetoConsulta) {

		AnotacaoSisbrDTO dto = new AnotacaoSisbrDTO();
		if (objetoConsulta != null) {
			dto = objetoConsulta;
		}

		
		AnotacaoSisbrHandler handler6 = new AnotacaoInadimplencia15DiasHandler(dto);
		AnotacaoSisbrHandler handler5 = new AnotacaoInadimplencia30DiasHandler(handler6, dto);
		AnotacaoSisbrHandler handler4 = new AnotacaoInadimplencia90DiasHandler(handler5, dto);
		AnotacaoSisbrHandler handler3 =new AnotacaoOperacaoTransferidaPrejuizoHandler(handler4, dto);
		AnotacaoSisbrHandler handler2 =new AnotacaoAvalistaInadimplencia90DiasHandler(handler3, dto);
		AnotacaoSisbrHandler handler1 = new AnotacaoAvalistaOperacaoPrejuizoHandler(handler2, dto);
		return handler1.obterAnotacoesBuilder();
	}
	
}
