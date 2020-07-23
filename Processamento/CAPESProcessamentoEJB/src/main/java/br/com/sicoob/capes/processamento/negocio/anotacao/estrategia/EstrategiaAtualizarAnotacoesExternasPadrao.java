/*
 * SICOOB
 * 
 * EstrategiaAtualizarAnotacoesExternasPadrao.java(br.com.sicoob.capes.processamento.negocio.anotacao.estrategia.EstrategiaAtualizarAnotacoesExternasPadrao)
 */
package br.com.sicoob.capes.processamento.negocio.anotacao.estrategia;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.math.NumberUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.cadastro.negocio.delegates.AnotacaoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.GrupoCompartilhamentoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.MapaTipoAnotacaoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.PessoaCompartilhamentoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.TipoConsultaOrigemDelegate;
import br.com.sicoob.capes.cadastro.negocio.dto.ConsultaAnotacaoDTO;
import br.com.sicoob.capes.comum.negocio.dto.AnotacaoExternaDTO;
import br.com.sicoob.capes.comum.negocio.dto.AnotacaoInformacaoDTO;
import br.com.sicoob.capes.negocio.entidades.Anotacao;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.MapaTipoAnotacao;
import br.com.sicoob.capes.negocio.entidades.OrigemInformacao;
import br.com.sicoob.capes.negocio.entidades.TipoAnotacao;
import br.com.sicoob.capes.negocio.entidades.TipoConsultaOrigem;
import br.com.sicoob.capes.negocio.entidades.vigente.GrupoCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;

/**
 * Estratégia de AtualizarAnotacoesExternasPadrao.
 */
public class EstrategiaAtualizarAnotacoesExternasPadrao implements
		EstrategiaAtualizarAnotacoesExternas {

	/** O atributo anotacao delegate. */
	private final transient AnotacaoDelegate anotacaoDelegate = CAPESCadastroFabricaDelegates
			.getInstance().criarAnotacaoDelegate();

	/**
	 * {@inheritDoc}
	 */
	public final void executarAtualizacaoAnotacaoExterna(
			OrigemInformacao origemInformacao, AnotacaoExternaDTO dto)
			throws BancoobException {

		List<PessoaCompartilhamento> pessoas = obterPessoas(dto);
		if (validarPessoas(pessoas)) {
			atualizarAnotacoes(origemInformacao, dto, pessoas);
		} else {
			getLogger().alerta(
					"Nao foi possivel atualizar as anotacoes da pessoa ",
					dto.toString());
		}
	}

	/**
	 * Obtem as pessoas para atualizacao de anotacoes.
	 * 
	 * @param dto
	 *            o DTO contendo os dados das anotacoes
	 * @return lista de pessoas
	 * @throws BancoobException
	 * @see PessoaCompartilhamento
	 */
	protected List<PessoaCompartilhamento> obterPessoas(AnotacaoExternaDTO dto) throws BancoobException {

		PessoaCompartilhamentoDelegate delegate = CAPESCadastroFabricaDelegates.getInstance().criarPessoaCompartilhamentoDelegate();

		GrupoCompartilhamentoDelegate grupoCompartilhamentoDelegate = CAPESCadastroFabricaDelegates.getInstance().criarGrupoCompartilhamentoDelegate();

		PessoaCompartilhamento pessoa = null;

		if (verificarCnpjRaiz(dto)) {
			//Caso seja informado apenas a raiz do cnpj
			GrupoCompartilhamento obterPorInstituicao = grupoCompartilhamentoDelegate.obterPorInstituicao(dto.getIdInstituicao());
			List<PessoaCompartilhamento> consultarFiliais = delegate.consultarFiliais(obterPorInstituicao.getCompartilhamentoCadastro().getCodigo(),
					dto.getNumCpfCnpj());
			pessoa = consultarFiliais.get(0);
		} else {
			pessoa = delegate.consultarPessoaPorDocumento(dto.getIdInstituicao(), dto.getNumCpfCnpj());
		}
		return Arrays.asList(pessoa);
	}

	/**
	 * Verificar cnpj raiz.
	 *
	 * @param dto o valor de dto
	 * @return {@code true}, em caso de sucesso
	 */
	private boolean verificarCnpjRaiz(AnotacaoExternaDTO dto) {
		return dto.getNumCpfCnpj().length() == 8;
	}

	/**
	 * Valida a pessoa.
	 * 
	 * @param pessoas
	 *            a pessoa
	 * @return true, se estiver preenchida.
	 */
	protected boolean validarPessoas(List<PessoaCompartilhamento> pessoas) {

		boolean valido = (pessoas != null) && !pessoas.isEmpty();
		if (valido) {
			for (PessoaCompartilhamento pessoa : pessoas) {
				valido &= (pessoa.getIdPessoaCompartilhamento() != null);
			}
		}
		return valido;
	}

	/**
	 * Atualizar anotacoes de pessoas.
	 * 
	 * @param origemInformacao
	 *            the origem informacao
	 * @param dto
	 *            the dto
	 * @param pessoas
	 *            as pessoas que serao atualizadas
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	protected void atualizarAnotacoes(OrigemInformacao origemInformacao,
			AnotacaoExternaDTO dto, List<PessoaCompartilhamento> pessoas)
			throws BancoobException {

		for (PessoaCompartilhamento pessoa : pessoas) {
			List<Anotacao> anotacoesVigentes = consultarAnotacoesVigentes(
					pessoa, origemInformacao);
			if (podeAtualizar(dto, anotacoesVigentes)) {
				anotacaoDelegate.baixarAnotacoesExternas(pessoa,
						origemInformacao, dto);
				List<Anotacao> anotacoes = criarAnotacoes(pessoa,
						origemInformacao, dto);
				if (CollectionUtils.isNotEmpty(anotacoes)) {
					anotacaoDelegate.incluir(anotacoes);
				}
			}
		}
	}

	/**
	 * Verifica se pode atualizar as anotações para a pessoa com o resultado da
	 * consulta externa.
	 * 
	 * @param dto
	 *            o resultado da consulta externa.
	 * @param anotacoesVigentes
	 *            As anotações vigente para a origem informada.
	 * @return boolean indicando se as anotações para a origem da consulta
	 *         externa podem ser atualizadas ou não.
	 */
	protected boolean podeAtualizar(AnotacaoExternaDTO dto,
			List<Anotacao> anotacoesVigentes) {

		return true;
	}

	/**
	 * Consulta as anotações vigentes para a pessoa na origem da informação.
	 * 
	 * @param pessoa
	 *            A pessoa utilizada na consulta.
	 * @param origem
	 *            A origem da informação utilizada na consulta.
	 * @return as anotações vigentes para a pessoa na origem da informação.
	 */
	protected List<Anotacao> consultarAnotacoesVigentes(
			PessoaCompartilhamento pessoa, OrigemInformacao origemInformacao) {

		ConsultaAnotacaoDTO filtro = obterFiltroAnotacoesVigentes(pessoa,
				origemInformacao);
		return anotacaoDelegate.listarAnotacoesPorFiltro(filtro);
	}

	/**
	 * Monta o filtro a partir da pessoa e da origem da informação informados.
	 * 
	 * @param pessoa
	 *            A pessoa para o filtro.
	 * @param origemInformacao
	 *            A origem da informação.
	 * @return O filtro para consulta de anotações.
	 */
	protected ConsultaAnotacaoDTO obterFiltroAnotacoesVigentes(
			PessoaCompartilhamento pessoa, OrigemInformacao origemInformacao) {

		Anotacao anotacao = new Anotacao();
		anotacao.setPessoaCompartilhamento(pessoa);
		anotacao.setOrigemInformacao(origemInformacao);

		ConsultaAnotacaoDTO filtro = new ConsultaAnotacaoDTO();
		filtro.setFiltro(anotacao);
		filtro.setAnotacoesBaixadas(false);

		return filtro;
	}

	/**
	 * 
	 * @param pessoaCompartilhamento
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	protected List<Anotacao> criarAnotacoes(
			PessoaCompartilhamento pessoaCompartilhamento,
			OrigemInformacao origemInformacao, AnotacaoExternaDTO dto)
			throws BancoobException {

		List<Anotacao> anotacoes = new ArrayList<Anotacao>();
		List<AnotacaoInformacaoDTO> informacoes = dto.getListaAnotacoes();
		if ((informacoes != null) && !informacoes.isEmpty()) {
			for (AnotacaoInformacaoDTO informacao : informacoes) {
				CollectionUtils.addIgnoreNull(anotacoes,
				        gerarAnotacao(pessoaCompartilhamento, origemInformacao, dto, informacao));
			}
		} 
		
		if (anotacoes.isEmpty()) {
			anotacoes.add(gerarNadaConsta(pessoaCompartilhamento, origemInformacao, dto));
		}
		return anotacoes;
	}

	/**
	 * Gerar nada consta.
	 *
	 * @param pessoaCompartilhamento o valor de pessoa compartilhamento
	 * @param origemInformacao o valor de origem informacao
	 * @param dto o valor de dto
	 * @return Anotacao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	protected Anotacao gerarNadaConsta(PessoaCompartilhamento pessoaCompartilhamento,
            OrigemInformacao origemInformacao, AnotacaoExternaDTO dto) throws BancoobException {
		
		TipoAnotacao tipoAnotacao = new TipoAnotacao(TipoAnotacao.NADA_CONSTA);
		TipoConsultaOrigem tipoConsulta = obterTipoConsultaOrigem(dto.getNomeTipoConsulta());
		DateTimeDB dataHoraOcorrencia = obterDataOcorrenciaNadaConsta(origemInformacao, dto);
		return criarAnotacao(pessoaCompartilhamento, origemInformacao, dto,
		        dataHoraOcorrencia, NumberUtils.SHORT_ZERO, BigDecimal.ZERO, tipoAnotacao,
		        tipoConsulta);
    }

	/**
	 * Obter data ocorrencia nada consta.
	 * 
	 * @param origem
	 *            the origem
	 * @param dto
	 *            the dto
	 * @return date time db
	 */
	protected DateTimeDB obterDataOcorrenciaNadaConsta(OrigemInformacao origem,
			AnotacaoExternaDTO dto) {
		return new DateTimeDB();
	}

	/**
	 * Gerar anotacao.
	 * 
	 * @param pessoaCompartilhamento
	 *            the pessoa compartilhamento
	 * @param origemInformacao
	 *            the origem informacao
	 * @param dto
	 *            the dto
	 * @param informacaoAnotacao
	 *            the informacao anotacao
	 * @return anotacao ou <code>null</code> caso o {@code MapaTipoAnotacao} nao
	 *         seja localizado
	 * @see MapaTipoAnotacao
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	private Anotacao gerarAnotacao(
			PessoaCompartilhamento pessoaCompartilhamento,
			OrigemInformacao origemInformacao, AnotacaoExternaDTO dto,
			AnotacaoInformacaoDTO informacaoAnotacao) throws BancoobException {

		Anotacao anotacao = null;
		String codigoTipoOrigemInformacao = informacaoAnotacao
				.getCodigoTipoOrigemInformacao();
		String nomeTipoConsulta = dto.getNomeTipoConsulta();
		MapaTipoAnotacao mapa = obterMapaTipoAnotacao(
				codigoTipoOrigemInformacao, nomeTipoConsulta);
		if (mapa != null) {
			TipoAnotacao tipoAnotacao = mapa.getTipoAnotacao();
			getLogger().debug(
					"Mapa de tipo de anotacao localizado: " + tipoAnotacao);

			Date dataOcorrencia = informacaoAnotacao.getDataOcorrencia();
			dataOcorrencia = validarDataOcorrencia(dataOcorrencia);
			DateTimeDB dataHoraOcorrencia = new DateTimeDB(
					dataOcorrencia.getTime());
			Short quantidade = informacaoAnotacao.getQuantidade().shortValue();
			BigDecimal valor = informacaoAnotacao.getValor();
			anotacao = criarAnotacao(pessoaCompartilhamento, origemInformacao,
					dto, dataHoraOcorrencia, quantidade, valor, tipoAnotacao,
					mapa.getTipoConsultaOrigem());
		}
		return anotacao;
	}

	/**
	 * Validar data ocorrencia.
	 *
	 * @param dataOcorrencia o valor de data ocorrencia
	 * @return Date
	 */
	private Date validarDataOcorrencia(Date dataOcorrencia) {
		if (dataOcorrencia == null) {
			dataOcorrencia = new Date();
		}
		return dataOcorrencia;
	}

	/**
	 * Obter tipo consulta origem.
	 * 
	 * @param nomeTipoConsulta
	 *            the nome tipo consulta
	 * @return tipo consulta origem
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	protected TipoConsultaOrigem obterTipoConsultaOrigem(String nomeTipoConsulta)
			throws BancoobException {

		TipoConsultaOrigemDelegate delegate = CAPESCadastroFabricaDelegates
				.getInstance().criarTipoConsultaOrigemDelegate();

		TipoConsultaOrigem filtro = new TipoConsultaOrigem();
		filtro.setNomeTipoConsultaOrigem(nomeTipoConsulta);

		ConsultaDto<TipoConsultaOrigem> criterios = new ConsultaDto<TipoConsultaOrigem>();
		criterios.setFiltro(filtro);
		List<TipoConsultaOrigem> tipos = delegate.listar(criterios);
		return CollectionUtils.isNotEmpty(tipos) ? tipos.get(0) : null;
	}

	/**
	 * Criar anotacao.
	 *
	 * @param pessoaCompartilhamento o valor de pessoa compartilhamento
	 * @param origemInformacao o valor de origem informacao
	 * @param dto o valor de dto
	 * @param dataHoraOcorrencia o valor de data hora ocorrencia
	 * @param quantidade o valor de quantidade
	 * @param valor o valor de valor
	 * @param tipoAnotacao o valor de tipo anotacao
	 * @param tipoConsulta o valor de tipo consulta
	 * @return Anotacao
	 */
	private Anotacao criarAnotacao(
			PessoaCompartilhamento pessoaCompartilhamento,
			OrigemInformacao origemInformacao, AnotacaoExternaDTO dto,
			DateTimeDB dataHoraOcorrencia, Short quantidade, BigDecimal valor,
			TipoAnotacao tipoAnotacao, TipoConsultaOrigem tipoConsulta) {

		Instituicao instituicao = new Instituicao();
		instituicao.setIdInstituicao(dto.getIdInstituicao());
		instituicao.setIdUnidadeInst(dto.getIdUnidadeInst());

		Anotacao anotacao = new Anotacao();
		anotacao.setPessoaCompartilhamento(pessoaCompartilhamento);
		anotacao.setDataHoraAnotacao(new DateTimeDB());
		anotacao.setDataHoraOcorrencia(dataHoraOcorrencia);
		anotacao.setDescObservacao(dto.getObservacao());
		anotacao.setIdConsultaExterna(dto.getIdConsulta());
		anotacao.setInstituicao(instituicao);
		anotacao.setOrigemInformacao(origemInformacao);
		anotacao.setQuantidade(quantidade);
		anotacao.setTipoAnotacao(tipoAnotacao);
		anotacao.setTipoConsultaOrigem(tipoConsulta);
		anotacao.setValor(valor);
		return anotacao;
	}

	/**
	 * 
	 * @param idTipoOrigem
	 * @param nomeTipoConsulta
	 * @param nomeOrigemInfo
	 * @return
	 * @throws BancoobException
	 */
	private MapaTipoAnotacao obterMapaTipoAnotacao(
			String codigoTipoOrigemInformacao, String nomeTipoConsulta)
			throws BancoobException {

		getLogger().debug("Obtendo tipo de anotacao: ",
				"codigoTipoOrigemInformacao(", codigoTipoOrigemInformacao,
				"), nomeTipoConsultaOrigem(", nomeTipoConsulta + ")");

		TipoConsultaOrigem tipoConsultaOrigem = new TipoConsultaOrigem();
		tipoConsultaOrigem.setNomeTipoConsultaOrigem(nomeTipoConsulta);

		MapaTipoAnotacao filtro = new MapaTipoAnotacao();
		filtro.setCodigoTipoOrigemInformacao(codigoTipoOrigemInformacao);
		filtro.setTipoConsultaOrigem(tipoConsultaOrigem);

		MapaTipoAnotacaoDelegate mapaTipoAnotacaoDelegate = CAPESCadastroFabricaDelegates
				.getInstance().criarMapaTipoAnotacaoDelegate();
		MapaTipoAnotacao mapa = mapaTipoAnotacaoDelegate
				.obterTipoAnotacaoAnotacaoExterna(filtro);
		if (mapa != null) {
			TipoAnotacao tipoAnotacao = mapa.getTipoAnotacao();
			getLogger().debug(
					"Mapa de tipo de anotacao localizado: " + tipoAnotacao);
		} else {
			getLogger()
					.alerta("Anotacao nao gerada. Mapa de tipo de anotacao nao localizado: ",
							"codigoTipoOrigemInformacao(",
							codigoTipoOrigemInformacao,
							"), nomeTipoConsultaOrigem(",
							nomeTipoConsulta + ")");
		}
		return mapa;
	}

	/**
	 * Recupera logger.
	 * 
	 * @return logger
	 */
	protected ISicoobLogger getLogger() {

		return SicoobLoggerPadrao.getInstance(getClass());
	}
}