package br.com.sicoob.capes.relatorio.negocio.servicos.ejb;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.cadastro.negocio.delegates.BemDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.FichaCadastralNovaDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.PessoaCompartilhamentoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.PessoaInstituicaoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.RelacionamentoPessoaDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.TelefoneDelegate;
import br.com.sicoob.capes.cadastro.negocio.dto.ConsultaAutorizacaoDTO;
import br.com.sicoob.capes.cadastro.negocio.dto.ConsultaDtoCUC;
import br.com.sicoob.capes.cadastro.negocio.enums.TipoAutorizacaoEntidadeEnum;
import br.com.sicoob.capes.cadastro.negocio.enums.TipoConsultaEnum;
import br.com.sicoob.capes.cadastro.negocio.excecao.PessoaNaoEncontradaNoPeriodoException;
import br.com.sicoob.capes.cadastro.negocio.excecao.RegistroNaoEncontradoNegocioException;
import br.com.sicoob.capes.cadastro.negocio.vo.ValoresBensVO;
import br.com.sicoob.capes.cadastro.util.EntidadeCadastroBaseComparator;
import br.com.sicoob.capes.cadastro.util.IntegracaoUtil;
import br.com.sicoob.capes.comum.negocio.enums.TipoClassificacaoBemEnum;
import br.com.sicoob.capes.comum.negocio.enums.TipoPessoaEnum;
import br.com.sicoob.capes.comum.negocio.vo.CamposFichaCadastralVO;
import br.com.sicoob.capes.comum.negocio.vo.EsferaAdministrativaVO;
import br.com.sicoob.capes.comum.negocio.vo.LOCIntegracaoLocalidadeVO;
import br.com.sicoob.capes.comum.negocio.vo.LOCIntegracaoTipoLogradouroVO;
import br.com.sicoob.capes.integracao.negocio.delegates.ADMIntegracaoDelegate;
import br.com.sicoob.capes.integracao.negocio.delegates.CAPESIntegracaoFabricaDelegates;
import br.com.sicoob.capes.integracao.negocio.delegates.LOCIntegracaoDelegate;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;
import br.com.sicoob.capes.negocio.entidades.EnderecoBase;
import br.com.sicoob.capes.negocio.entidades.FonteRendaBase;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.Localidade;
import br.com.sicoob.capes.negocio.entidades.ProdutividadeBase;
import br.com.sicoob.capes.negocio.entidades.ReferenciaBase;
import br.com.sicoob.capes.negocio.entidades.RelacionamentoPessoaBase;
import br.com.sicoob.capes.negocio.entidades.TipoLogradouro;
import br.com.sicoob.capes.negocio.entidades.TipoPessoa;
import br.com.sicoob.capes.negocio.entidades.TransicaoPessoa;
import br.com.sicoob.capes.negocio.entidades.TributacaoBase;
import br.com.sicoob.capes.negocio.entidades.ValidacaoCadastral;
import br.com.sicoob.capes.negocio.entidades.bem.BemImovelAvaliacao;
import br.com.sicoob.capes.negocio.entidades.bem.BemMovel;
import br.com.sicoob.capes.negocio.entidades.bem.BemMovelAvaliacao;
import br.com.sicoob.capes.negocio.entidades.bem.TipoOnusBem;
import br.com.sicoob.capes.negocio.entidades.bem.historico.HistoricoBemPessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.bemantigo.BemBase;
import br.com.sicoob.capes.negocio.entidades.bemantigo.HistoricoBem;
import br.com.sicoob.capes.negocio.entidades.bemantigo.SubTipoBem;
import br.com.sicoob.capes.negocio.entidades.bemantigo.TipoBem;
import br.com.sicoob.capes.negocio.entidades.historico.HistoricoPessoaFisica;
import br.com.sicoob.capes.negocio.entidades.historico.HistoricoProdutividade;
import br.com.sicoob.capes.negocio.entidades.vigente.Certidao;
import br.com.sicoob.capes.negocio.entidades.vigente.Email;
import br.com.sicoob.capes.negocio.entidades.vigente.Endereco;
import br.com.sicoob.capes.negocio.entidades.vigente.FonteRenda;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaFisica;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaInstituicao;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaJuridica;
import br.com.sicoob.capes.negocio.entidades.vigente.Produtividade;
import br.com.sicoob.capes.negocio.entidades.vigente.Produtor;
import br.com.sicoob.capes.negocio.entidades.vigente.Referencia;
import br.com.sicoob.capes.negocio.entidades.vigente.RelacionamentoPessoa;
import br.com.sicoob.capes.negocio.entidades.vigente.Telefone;
import br.com.sicoob.capes.negocio.entidades.vigente.Tributacao;
import br.com.sicoob.capes.relatorio.negocio.relatorios.RelatorioFichaCadastralNova;
import br.com.sicoob.capes.relatorio.negocio.relatorios.RelatorioFichaCadastralNovaPessoaFisica;
import br.com.sicoob.capes.relatorio.negocio.relatorios.RelatorioFichaCadastralNovaPessoaJuridica;
import br.com.sicoob.capes.relatorio.negocio.relatorios.RelatorioFichaCadastralSimples;
import br.com.sicoob.capes.relatorio.negocio.relatorios.RelatorioFichaCadastralSimplesPessoaFisica;
import br.com.sicoob.capes.relatorio.negocio.relatorios.RelatorioFichaCadastralSimplesPessoaJuridica;
import br.com.sicoob.capes.relatorio.negocio.vo.FichaCadastralBemImovelNovoVO;
import br.com.sicoob.capes.relatorio.negocio.vo.FichaCadastralBemMovelNovoVO;
import br.com.sicoob.capes.relatorio.negocio.vo.FichaCadastralBemNovoVO;
import br.com.sicoob.capes.relatorio.negocio.vo.FichaCadastralBemVO;
import br.com.sicoob.capes.relatorio.negocio.vo.FichaCadastralContatoVO;
import br.com.sicoob.capes.relatorio.negocio.vo.FichaCadastralVO;
import br.com.sicoob.capes.relatorio.persistencia.dao.RelatorioBemDAO;
import br.com.sicoob.capes.relatorio.persistencia.dao.RelatorioRelacionamentoDAO;
import br.com.sicoob.capes.relatorio.persistencia.dao.RelatorioValidacaoCadastralDAO;
import br.com.sicoob.relatorio.api.dto.ParametroDTO;
import br.com.sicoob.relatorio.api.dto.RelatorioDadosDTO;
import br.com.sicoob.relatorio.api.dto.RetornoProcessamentoRelatorioDTO;
import br.com.sicoob.relatorio.api.interfaces.IProcessamentoRelatorioServico;

@Stateless
@Remote({ IProcessamentoRelatorioServico.class })
public class RelatorioFichaCadastralNovaServicoEJB extends CAPESRelatorioServicoEJB implements IProcessamentoRelatorioServico {

	/** O atributo fabricaDelegates. */
	private static CAPESCadastroFabricaDelegates fabricaDelegates = CAPESCadastroFabricaDelegates.getInstance();

	/** O atributo capesIntegracaoFabricaDelegates. */
	private static CAPESIntegracaoFabricaDelegates capesIntegracaoFabricaDelegates = CAPESIntegracaoFabricaDelegates.getInstance();

	/** O Atributo BemDelegate **/
	private static BemDelegate bemDelegate = fabricaDelegates.criarBemDelegate();

	/** O Atributo BemDelegate **/
	private static LOCIntegracaoDelegate localidadeDelegate = capesIntegracaoFabricaDelegates.criarLOCIntegracaoDelegate();

	/** A constante REFERENCIA_POR_PESSOA. */
	private static final String REFERENCIA_POR_PESSOA = "PESQUISA_REFERENCIA_POR_PESSOA";

	/** A constante TRIBUTACAO_POR_PESSOA. */
	private static final String TRIBUTACAO_POR_PESSOA = "TRIBUTACAO_POR_PESSOA";

	/** A constante PESQUISA_FONTE_RENDA_POR_PESSOA_COMPARTILHAMENTO. */
	private static final String PESQUISA_FONTE_RENDA_POR_PESSOA_COMPARTILHAMENTO = "PESQUISA_FONTE_RENDA_POR_PESSOA_COMPARTILHAMENTO";

	/** A constante BEM_POR_PESSOA. */
	private static final String BEM_POR_PESSOA = "PESQUISA_BEM_POR_PESSOA";

	/** A constante CERTIDAO_POR_PESSOA. */
	private static final String CERTIDAO_POR_PESSOA = "PESQUISA_CERTIDAO_POR_PESSOA";

	/** A constante PRODUTOR_POR_PESSOA. */
	private static final String PRODUTOR_POR_PESSOA = "PRODUTOR_POR_PESSOA";

	/** A constante ENDERECO_POR_PESSOA_COMPARTILHAMENTO. */
	private static final String ENDERECO_POR_PESSOA_COMPARTILHAMENTO = "PESQUISA_ENDERECO_POR_PESSOA_COMPARTILHAMENTO";

	/** A constante TELEFONE_POR_PESSOA_COMPARTILHAMENTO. */
	private static final String TELEFONE_POR_PESSOA_COMPARTILHAMENTO = "PESQUISA_TELEFONE_POR_PESSOA_COMPARTILHAMENTO";

	/** A constante EMAIL_POR_PESSOA_COMPARTILHAMENTO. */
	private static final String EMAIL_POR_PESSOA_COMPARTILHAMENTO = "PESQUISA_EMAIL_POR_PESSOA_COMPARTILHAMENTO";

	/** A constante CONSULTA_HISTORICO_PESSOA. */
	private static final String CONSULTA_HISTORICO_PESSOA = "CONSULTA_HISTORICO_PESSOA";

	/** A constante PESSOA_INSTITUICAO_POR_PESSOA. */
	private static final String PESSOA_INSTITUICAO_POR_PESSOA = "PESQUISA_PESSOA_INSTITUICAO_POR_PESSOA";

	/** A constante PRODUTIVIDADE_POR_PESSOA. */
	private static final String PRODUTIVIDADE_POR_PESSOA = "PESQUISAR_PRODUTIVIDADE";

	/** A constante PESQUISA_HISTORICO_BEM_IMOVEL_POR_PESSOA. */
	private static final String PESQUISA_HISTORICO_BEM_IMOVEL_NOVO_POR_PESSOA = "PESQUISA_HISTORICO_BEM_IMOVEL_NOVO_POR_PESSOA";

	@Inject
	@Default
	private RelatorioValidacaoCadastralDAO dao;

	/** O atributo dao. */
	@Inject
	@Default
	private RelatorioBemDAO daoBem;

	@Inject
	@Default
	private RelatorioRelacionamentoDAO daoRelacionamento;

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public RetornoProcessamentoRelatorioDTO gerarRelatorio(ParametroDTO dto, RelatorioDadosDTO rDto) throws BancoobException {
		Number codigoTipoPessoa = (Number) dto.getDados().get("codigoTipoPessoa");
		Number idPessoa = (Number) dto.getDados().get("idPessoa");
		Number idPessoaCompartilhamento = (Number) dto.getDados().get("idPessoaCompartilhamento");

		Boolean atual = (Boolean) dto.getDados().get("atual");
		Boolean historicoEm = (Boolean) dto.getDados().get("historicoEm");
		Boolean posicao = (Boolean) dto.getDados().get("posicao");
		Boolean emBranco = (Boolean) dto.getDados().get("emBranco");

		Date data = (Date) dto.getDados().get("data");
		Date dataFim = (Date) dto.getDados().get("dataFim");
		
		CamposFichaCadastralVO vo = new CamposFichaCadastralVO();
		vo.setTipoPessoa(codigoTipoPessoa.intValue());
		vo.setAtual(atual);
		vo.setEmBranco(emBranco);
		vo.setHistoricoEm(historicoEm);
		vo.setPosicao(posicao);
		vo.setData(data);
		vo.setDataFim(dataFim);

		PessoaCompartilhamentoDelegate pessoaCompartilhamentoDelegate = fabricaDelegates.criarPessoaCompartilhamentoDelegate();
		ADMIntegracaoDelegate admIntegracaoDelegate = capesIntegracaoFabricaDelegates.criarADMIntegracaoDelegate();
		
		PessoaCompartilhamento pessoaCompartilhamento = pessoaCompartilhamentoDelegate.obter(idPessoaCompartilhamento.longValue());
		setDatas(vo, pessoaCompartilhamento.getDataInclusaoSistema());

		PessoaCompartilhamento pessoaCompartilhamentoJSON = verificarConsultaEntidadeAutorizacaoJSON(idPessoa.intValue(), TipoAutorizacaoEntidadeEnum.PESSOA, obterTipoConsultaEnum(vo));
		
		if(isEntidadeEmAutorizacao(pessoaCompartilhamentoJSON)){
			pessoaCompartilhamentoJSON.setTransicoes(pessoaCompartilhamento.getTransicoes());
			pessoaCompartilhamento = pessoaCompartilhamentoJSON;
		}			
		
		FichaCadastralVO fichaCadastralVO = new FichaCadastralVO();
		FichaCadastralContatoVO contatoVO = new FichaCadastralContatoVO();
		fichaCadastralVO.setPessoa(pessoaCompartilhamento);

		
		if (pessoaCompartilhamento instanceof PessoaJuridica) {
			PessoaJuridica pessoaJuridica = (PessoaJuridica) pessoaCompartilhamento;
			if (pessoaJuridica.getCodigoEsferaAdministrativa() != null) {
				EsferaAdministrativaVO esferaAdministrativa = admIntegracaoDelegate.obterEsferaAdministrativa(pessoaJuridica.getCodigoEsferaAdministrativa());
				fichaCadastralVO.setDescricaoEsferaAdministrativa(esferaAdministrativa.getDescricao());
			}
		}else{
			PessoaFisica pessoaF = (PessoaFisica)pessoaCompartilhamento;
			fichaCadastralVO.setConjuge(pessoaF.getConjuge() != null ? pessoaF.getConjuge().getPessoa() : null);
			verificaFiliacao(pessoaF,fichaCadastralVO);
		}
		
		List<PessoaCompartilhamento> pessoasCompartilhamento = listarPessoaCompartilhamento(vo,
				pessoaCompartilhamento);
		
		if (pessoasCompartilhamento.isEmpty()) {
			throw new PessoaNaoEncontradaNoPeriodoException();
		}
		
		montarDadosBemNovo(pessoaCompartilhamento.getId(), fichaCadastralVO);
		
		fichaCadastralVO.setDataUltimaAtualizacao(pessoaCompartilhamento.getDataRenovacaoCadastral());
		List<?> listaPessoaCompartilhamentoVO = buscarNaturalidadePessoa(pessoasCompartilhamento);

		if (listaPessoaCompartilhamentoVO != null && !listaPessoaCompartilhamentoVO.isEmpty()) {
			fichaCadastralVO.setPessoasCompartilhamento(listaPessoaCompartilhamentoVO);
		} else {
			fichaCadastralVO.setPessoasCompartilhamento(pessoasCompartilhamento);
		}
		
		fichaCadastralVO.setCertidoes(listarCertidoes(vo, pessoaCompartilhamento));
		List<RelacionamentoPessoaBase> listaRelacionamento = listarRelacionamentos(pessoaCompartilhamento, vo);
		
		fichaCadastralVO.setRelacionamentos(listaRelacionamento);
		
		if(!vo.getAtual()){
			contatoVO.setEmails(listarEmails(pessoaCompartilhamento, vo));
		}
		
		contatoVO.setEnderecos(listarEnderecos(pessoaCompartilhamento, vo));
		contatoVO.setTelefones(listarTelefones(pessoaCompartilhamento, vo));

		fichaCadastralVO.setFontesDeRenda(listarFonteRenda(pessoaCompartilhamento, vo));
		fichaCadastralVO.setReferencias(listarReferencias(vo, pessoaCompartilhamento));
		fichaCadastralVO.setProdutores(listarProdutores(vo, pessoaCompartilhamento));
		fichaCadastralVO.setProdutividades(listarProdutividade(vo, pessoaCompartilhamento));

		@SuppressWarnings("unchecked")
		//@TODO Parse de um BemNovo para um Antigo para aparecer no relatorio.
		List<HistoricoBemPessoaCompartilhamento> historicosBemPessoaCompartilhamento = (List<HistoricoBemPessoaCompartilhamento>) listarBensNovosHistorico(vo, pessoaCompartilhamento);
		obterHistoricoBensNovos(fichaCadastralVO,
				historicosBemPessoaCompartilhamento);
		
		if(!contatoVO.getEnderecos().isEmpty() || !contatoVO.getTelefones().isEmpty() || !contatoVO.getEmails().isEmpty()){
			fichaCadastralVO.getContatos().add(contatoVO);
		}
		
		fichaCadastralVO.setPessoaInstituicao(obterPessoaInstituicao(pessoaCompartilhamento));
		fichaCadastralVO.setTributacoes(listarTributacoes(vo, pessoaCompartilhamento));
		fichaCadastralVO.setTributacao(obterTributacao(fichaCadastralVO.getTributacoes()));
		fichaCadastralVO.setPessoaInstituicoes(listarPessoasInstituicoes(vo, pessoaCompartilhamento));
		
		// instituição
		Instituicao instituicao = obterInstituicao(pessoaCompartilhamento);
		fichaCadastralVO.setIdInstituicao(instituicao.getIdInstituicao());
		fichaCadastralVO.setIdUnidadeInst(instituicao.getIdUnidadeInst());
		
		fichaCadastralVO.setLabelFiltroData(criarNovaLabelFiltroData(vo));
		fichaCadastralVO.setExibirTotalProdutividade(vo.getAtualCompleto() != null ? !vo.getAtualCompleto().booleanValue() : false);
		
		ValidacaoCadastral logins = dao.consultarUltimoEnvioAprovacao(fichaCadastralVO.getPessoa().getId());
		if (logins != null) {
			fichaCadastralVO.setLoginUsuarioAprovacao(logins.getIdUsuarioAprov());
			fichaCadastralVO.setLoginUsuarioEnvioAprovacao(logins.getIdUsuarioEnvio());			
		}
		
		return obterRelatorioFichaCadastral(pessoaCompartilhamento, fichaCadastralVO, vo, rDto);
	}
	
	/**setDatas
	 * Caso as datas seja nulas seta as mesmas.
	 * @param vo
	 * @param dateTimeDB 
	 * @throws NegocioException 
	 */
	private void setDatas(CamposFichaCadastralVO vo, DateTimeDB dataInclusaoSistema) throws NegocioException {
		if((vo.getData() != null && vo.getData().compareTo(dataInclusaoSistema) == -1) && (vo.getDataFim() != null && vo.getDataFim().compareTo(dataInclusaoSistema) == -1)) {
			throw new NegocioException("A data informada é anterior a data de inclusão no sistema (" + formatarData(dataInclusaoSistema) + ")");
		}

		if(vo.getData() == null) {
			vo.setData(dataInclusaoSistema);
		} 
		if(vo.getDataFim() == null) {
			vo.setDataFim(new Date());
		}
	}

	/**
	 * Verificar consulta entidade autorizacao json.
	 *
	 * @param <E>
	 *            o tipo generico
	 * @param idPessoaSelecionada
	 *            o valor de id pessoa selecionada
	 * @param tipoAutorizacao
	 *            o valor de tipo autorizacao
	 * @param tipoConsultaEnum
	 *            o valor de tipo consulta enum
	 * @return E
	 */
	@SuppressWarnings("unchecked")
	private <E> E verificarConsultaEntidadeAutorizacaoJSON(Integer idPessoaSelecionada, TipoAutorizacaoEntidadeEnum tipoAutorizacao, TipoConsultaEnum tipoConsultaEnum) {

		if (tipoConsultaEnum == TipoConsultaEnum.AUTORIZACAO_FICHA_PREVIA) {
			ConsultaAutorizacaoDTO dto = new ConsultaAutorizacaoDTO();
			dto.setIdPessoaSelecionada(idPessoaSelecionada);
			dto.setTipoAutorizacao(tipoAutorizacao);
			FichaCadastralNovaDelegate fichaCadastralDelegate = criarFichaCadastralNovaDelegate();
			return (E) fichaCadastralDelegate.obterEntidadeAutorizacao(dto);
		}
		return null;
	}

	/**
	 * Verifica se eh entidade em autorizacao.
	 *
	 * @param entidade
	 *            o valor de entidade
	 * @return {@code true}, se for entidade em autorizacao
	 */
	private boolean isEntidadeEmAutorizacao(CAPESEntidade<Long> entidade) {
		return entidade != null && entidade.getId() > 0;
	}

	/**
	 * Obtem a {@link Instituicao} a partir das transições da {@code pessoaCompartilhamento}
	 * 
	 * @param pessoaCompartilhamento
	 *            a pessoa de onde serão obtidas as transições para localizar a intituição
	 * @return a instituição
	 */
	private Instituicao obterInstituicao(PessoaCompartilhamento pessoaCompartilhamento) {

		Instituicao instituicao = null;
		Integer idInstUsuario = obterInstituicaoUsuarioRelatorio().getIdInstituicao();
		Iterator<TransicaoPessoa> it = pessoaCompartilhamento.getTransicoes().iterator();
		while (it.hasNext() && (instituicao == null)) {
			Instituicao instTemp = it.next().getInstituicao();
			if (instTemp.getIdInstituicao().equals(idInstUsuario)) {
				instituicao = instTemp;
			}
		}
		return instituicao;
	}

	/**
	 * Formatar data.
	 *
	 * @param data
	 *            o valor de data
	 * @return String
	 */
	private String formatarData(Date data) {
		if (data != null) {
			return new SimpleDateFormat("dd/MM/yyyy").format(data);
		}
		return "";
	}

	private String obterLabelDataEmissaoFichaCadastral(Date dataEmissao, Date dataFim, TipoConsultaEnum tipoConsultaEnum){
		if(tipoConsultaEnum.equals(TipoConsultaEnum.ATUAL)){
			Date dataAtual = new Date();
			return "POSIÇÃO EM: "+formatarData(dataAtual);
		}else if(tipoConsultaEnum.equals(TipoConsultaEnum.PERIODO)){
			return "POSIÇÃO NO PERÍODO DE: "+formatarData(dataEmissao) + " ATÉ " +formatarData(dataFim);
		}else{
			return "HISTÓRICO EM: "+formatarData(dataEmissao);
		}
	}

	/**
	 * Verifica se eh pessoa fisica.
	 *
	 * @param pessoaCompartilhamento
	 *            o valor de pessoa compartilhamento
	 * @return {@code true}, se for pessoa fisica
	 */
	private boolean isPessoaFisica(PessoaCompartilhamento pessoaCompartilhamento) {
		TipoPessoa tipoPessoa = pessoaCompartilhamento.getPessoa().getTipoPessoa();
		Short codTipoPessoa = tipoPessoa.getCodTipoPessoa();

		return TipoPessoaEnum.isPessoaFisica(codTipoPessoa);
	}

	/**
	 * Metodo que monta e cria a lista de ficha cadastral para o bem novo.
	 * 
	 * @param bens
	 * @return List<FichaCadastralBemNovoVO>
	 * @throws BancoobException
	 */
	private List<FichaCadastralBemNovoVO> criarListaBensNovosMoveis(List<br.com.sicoob.capes.negocio.entidades.bem.Bem> bensMoveis, List<FichaCadastralBemNovoVO> listaFichaCadastralBemNovoVO) throws BancoobException {

		FichaCadastralBemNovoVO fichaBem = new FichaCadastralBemNovoVO();
		List<FichaCadastralBemMovelNovoVO> listaFichaCadastralBemMovelNovoVO = new ArrayList<FichaCadastralBemMovelNovoVO>();

		listaFichaCadastralBemNovoVO.add(fichaBem);

		for (br.com.sicoob.capes.negocio.entidades.bem.Bem bemAux : bensMoveis) {
			FichaCadastralBemMovelNovoVO fichaCadastralBemMovelNovoVO = new FichaCadastralBemMovelNovoVO();

			if (bemAux instanceof BemMovelAvaliacao) {
				fichaCadastralBemMovelNovoVO.setBemMovelAvaliacao((BemMovelAvaliacao) bemAux);
				fichaCadastralBemMovelNovoVO.setPessoaAvaliador(obterPessoaCompartilhamento(((BemMovelAvaliacao) bemAux).getIdPessoaCompartilhamentoAvaliador()));
				fichaCadastralBemMovelNovoVO.setTipoOnus(converterTipoOnusBemMovel(((BemMovelAvaliacao) bemAux).getTiposOnus(), bemAux));
				fichaCadastralBemMovelNovoVO.setBemMovel(fichaCadastralBemMovelNovoVO.getBemMovelAvaliacao());
			} else if (bemAux instanceof BemMovel) {
				fichaCadastralBemMovelNovoVO.setBemMovel((BemMovel) bemAux);
			}

			if (bemAux instanceof br.com.sicoob.capes.negocio.entidades.bem.Bem) {
				fichaCadastralBemMovelNovoVO.getBemMovel(bemAux);
			}

			listaFichaCadastralBemMovelNovoVO.add(fichaCadastralBemMovelNovoVO);
		}
		listaFichaCadastralBemNovoVO.get(0).setBensMoveis(listaFichaCadastralBemMovelNovoVO);

		return listaFichaCadastralBemNovoVO;
	}

	/**
	 * Metodo que monta e cria a lista de ficha cadastral para o bem novo.
	 * 
	 * @param bens
	 * @return List<FichaCadastralBemNovoVO>
	 * @throws BancoobException
	 */
	private List<FichaCadastralBemNovoVO> criarListaBensNovosImoves(List<br.com.sicoob.capes.negocio.entidades.bem.Bem> bensImoveis, List<FichaCadastralBemNovoVO> listaFichaCadastralBemNovoVO) throws BancoobException {

		List<FichaCadastralBemImovelNovoVO> listaFichaCadastralBemImovelNovoVO = new ArrayList<FichaCadastralBemImovelNovoVO>();
		FichaCadastralBemNovoVO fichaBem = new FichaCadastralBemNovoVO();
		listaFichaCadastralBemNovoVO.add(fichaBem);

		for (br.com.sicoob.capes.negocio.entidades.bem.Bem bemAux : bensImoveis) {
			FichaCadastralBemImovelNovoVO fichaCadastralBemImovelNovoVO = new FichaCadastralBemImovelNovoVO();

			if (bemAux instanceof br.com.sicoob.capes.negocio.entidades.bem.BemImovelAvaliacaoRural) {
				fichaCadastralBemImovelNovoVO.setAvaliacaoRural((br.com.sicoob.capes.negocio.entidades.bem.BemImovelAvaliacaoRural) bemAux);
				fichaCadastralBemImovelNovoVO.setPessoaAvaliador(obterPessoaCompartilhamento(((BemImovelAvaliacao) bemAux).getIdPessoaCompartilhamentoAvaliador()));
				fichaCadastralBemImovelNovoVO.setTipoOnus(converterTipoOnusBemImovel(((BemImovelAvaliacao) bemAux).getTiposOnus(), bemAux));

			} else if (bemAux instanceof br.com.sicoob.capes.negocio.entidades.bem.BemImovelAvaliacaoUrbano) {
				fichaCadastralBemImovelNovoVO.setAvaliacaoUrbano((br.com.sicoob.capes.negocio.entidades.bem.BemImovelAvaliacaoUrbano) bemAux);
				fichaCadastralBemImovelNovoVO.setPessoaAvaliador(obterPessoaCompartilhamento(((BemImovelAvaliacao) bemAux).getIdPessoaCompartilhamentoAvaliador()));
				fichaCadastralBemImovelNovoVO.setTipoOnus(converterTipoOnusBemImovel(((BemImovelAvaliacao) bemAux).getTiposOnus(), bemAux));
			}

			if (bemAux instanceof br.com.sicoob.capes.negocio.entidades.bem.BemImovel) {
				fichaCadastralBemImovelNovoVO.setBemImovel((br.com.sicoob.capes.negocio.entidades.bem.BemImovel) bemAux);
			}

			if (fichaCadastralBemImovelNovoVO.getBemImovel() != null) {
				if (fichaCadastralBemImovelNovoVO.getBemImovel().getIdLocalidade() != null && fichaCadastralBemImovelNovoVO.getBemImovel().getIdLocalidade().intValue() != 0) {
					fichaCadastralBemImovelNovoVO.setLocalidade(localidadeDelegate.obterLocalidade(fichaCadastralBemImovelNovoVO.getBemImovel().getIdLocalidade()));
				}
				if (fichaCadastralBemImovelNovoVO.getBemImovel().getIdLogradouro() != null && fichaCadastralBemImovelNovoVO.getBemImovel().getIdLogradouro().intValue() != 0) {
					fichaCadastralBemImovelNovoVO.setLocalidade(localidadeDelegate.pesquisarLocalidadePorIdLogradouro(fichaCadastralBemImovelNovoVO.getBemImovel().getIdLogradouro()));
				}
				fichaCadastralBemImovelNovoVO.getRelacionamentoPessoas().addAll(fichaCadastralBemImovelNovoVO.getBemImovel().getRelacionamentoPessoas());
			}

			if (bemAux instanceof br.com.sicoob.capes.negocio.entidades.bem.Bem) {
				fichaCadastralBemImovelNovoVO.getBemImovel(bemAux);
			}

			listaFichaCadastralBemImovelNovoVO.add(fichaCadastralBemImovelNovoVO);
		}
		listaFichaCadastralBemNovoVO.get(0).setBensImoveis(listaFichaCadastralBemImovelNovoVO);
		return listaFichaCadastralBemNovoVO;
	}

	/**
	 * Converte tipoOnusBem de Set para List
	 * 
	 * @param tipoOnus
	 * @param bemAux
	 * @return
	 */
	private List<TipoOnusBem> converterTipoOnusBemMovel(Set<TipoOnusBem> tipoOnus, br.com.sicoob.capes.negocio.entidades.bem.Bem bemAux) {
		List<TipoOnusBem> onus = new ArrayList<TipoOnusBem>();
		HashSet<TipoOnusBem> tt = new HashSet<TipoOnusBem>(((BemMovelAvaliacao) bemAux).getTiposOnus());
		onus.addAll(tt);
		return onus;
	}

	/**
	 * Converte tipoOnusBem de Set para List
	 * 
	 * @param tipoOnus
	 * @param bemAux
	 * @return
	 */
	private List<TipoOnusBem> converterTipoOnusBemImovel(Set<TipoOnusBem> tipoOnus, br.com.sicoob.capes.negocio.entidades.bem.Bem bemAux) {
		List<TipoOnusBem> onus = new ArrayList<TipoOnusBem>();
		HashSet<TipoOnusBem> tt = new HashSet<TipoOnusBem>(((BemImovelAvaliacao) bemAux).getTiposOnus());
		onus.addAll(tt);
		return onus;
	}

	/**
	 * Consultar Pessao compartilhamento por id de pessoa
	 * 
	 * @param idPessoa
	 * @return PessoaCompartilhamento
	 * @throws BancoobException
	 */
	private PessoaCompartilhamento obterPessoaCompartilhamento(Long idPessoa) throws BancoobException {
		PessoaCompartilhamentoDelegate pessoaCompartilhamentoDelegate = fabricaDelegates.criarPessoaCompartilhamentoDelegate();
		return pessoaCompartilhamentoDelegate.obter(idPessoa);
	}

	/**
	 * Listar pessoa compartilhamento.
	 *
	 * @param vo
	 *            o valor de vo
	 * @param pessoaCompartilhamento
	 *            o valor de pessoa compartilhamento
	 * @return List
	 */
	private List<PessoaCompartilhamento> listarPessoaCompartilhamento(CamposFichaCadastralVO vo, PessoaCompartilhamento pessoaCompartilhamento) {
		List<PessoaCompartilhamento> pessoasCompartilhamento = new LinkedList<PessoaCompartilhamento>();

		TipoConsultaEnum tipoConsulta = obterTipoConsultaEnum(vo);
		if (tipoConsulta != TipoConsultaEnum.PERIODO || (tipoConsulta == TipoConsultaEnum.PERIODO && vo.getData().compareTo(pessoaCompartilhamento.getDataInclusaoSistema()) >= 0)) {
			pessoasCompartilhamento.add(pessoaCompartilhamento);
		}

		if (tipoConsulta == TipoConsultaEnum.TUDO || (tipoConsulta == TipoConsultaEnum.PERIODO && vo.getData().compareTo(pessoaCompartilhamento.getDataInclusaoSistema()) >= 0)) {
			ConsultaDtoCUC<PessoaCompartilhamento> dto = new ConsultaDtoCUC<PessoaCompartilhamento>();
			dto.setFiltro(pessoaCompartilhamento);
			copiarPropriedades(vo, dto);
			dto.setNomeConsulta(CONSULTA_HISTORICO_PESSOA);

			FichaCadastralNovaDelegate fichaCadastralDelegate = criarFichaCadastralNovaDelegate();
			pessoasCompartilhamento.addAll(fichaCadastralDelegate.listar(PessoaCompartilhamento.class, dto));
		}

		return pessoasCompartilhamento;
	}

	/**
	 * Listar bens imoveis.
	 *
	 * @param vo
	 *            o valor de vo
	 * @param pessoaCompartilhamento
	 *            o valor de pessoa compartilhamento
	 * @return List
	 * @throws BancoobException
	 *             lança a exceção BancoobException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<?> listarBensNovosHistorico(CamposFichaCadastralVO vo, PessoaCompartilhamento pessoaCompartilhamento) throws BancoobException {

		HistoricoBemPessoaCompartilhamento bem = new HistoricoBemPessoaCompartilhamento();
		bem.setPessoaCompartilhamento(pessoaCompartilhamento);

		ConsultaDtoCUC<HistoricoBemPessoaCompartilhamento> dto = new ConsultaDtoCUC<HistoricoBemPessoaCompartilhamento>();
		dto.setFiltro(bem);
		copiarPropriedades(vo, dto);

		dto.setNomeConsulta(BEM_POR_PESSOA);
		dto.setProcurarPor("POR BEM IMOVEL");

		FichaCadastralNovaDelegate fichaCadastralDelegate = criarFichaCadastralNovaDelegate();
		List<HistoricoBemPessoaCompartilhamento> bens = new ArrayList<HistoricoBemPessoaCompartilhamento>();

		ConsultaDtoCUC<HistoricoBemPessoaCompartilhamento> dtoHistorico = new ConsultaDtoCUC<HistoricoBemPessoaCompartilhamento>();
		dtoHistorico.setFiltro(bem);
		copiarPropriedades(vo, dtoHistorico);

		dtoHistorico.setNomeConsulta(PESQUISA_HISTORICO_BEM_IMOVEL_NOVO_POR_PESSOA);

		bens.addAll(fichaCadastralDelegate.listar(HistoricoBemPessoaCompartilhamento.class, dtoHistorico));
		Collections.sort(bens, new EntidadeCadastroBaseComparator());

		return bens;
	}

	/**
	 * Obter localidade.
	 *
	 * @param idLocalidade
	 *            o valor de id localidade
	 * @return Localidade
	 * @throws BancoobException
	 *             lança a exceção BancoobException
	 */
	private Localidade obterLocalidade(Integer idLocalidade) throws BancoobException {
		LOCIntegracaoDelegate localidadeDelegate = criarLocalidadeDelegate();
		LOCIntegracaoLocalidadeVO localidade = localidadeDelegate.obterLocalidade(idLocalidade);

		// o serviço de localidade retorna uma instância mesmo quando não encontra o registro
		if ((localidade != null) && (localidade.getIdLocalidade() == null)) {
			localidade = null;
		}
		return IntegracaoUtil.converterLocalidade(localidade);
	}

	/**
	 * O método Buscar naturalidade pessoa.
	 *
	 * @param pessoas o valor de pessoas
	 * @throws BancoobException lança a exceção BancoobException
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<?> buscarNaturalidadePessoa(List<?> pessoas) throws BancoobException{
		LOCIntegracaoDelegate localidadeDelegate = criarLocalidadeDelegate();
		List listaPessoaCompartilhamentoVO = new ArrayList();
		
		for (Object pessoaCompartilhamento: pessoas) {
			if (pessoaCompartilhamento instanceof PessoaFisica) {
				PessoaFisica pessoaFisica = (PessoaFisica) pessoaCompartilhamento;
				
				if (pessoaFisica.getIdNaturalidade() != null) {
					LOCIntegracaoLocalidadeVO localidade = localidadeDelegate.obterLocalidade(pessoaFisica.getIdNaturalidade());
					String uf = localidade.getUf() != null ? " - " + localidade.getUf().getSiglaUF() : "";
					pessoaFisica.setDescNaturalidade(localidade.getNome() + uf);
				}
				listaPessoaCompartilhamentoVO.add(pessoaFisica);
				
			} else if (pessoaCompartilhamento instanceof HistoricoPessoaFisica) {
				HistoricoPessoaFisica historicoPessoaFisica = (HistoricoPessoaFisica)pessoaCompartilhamento;
			
				if (historicoPessoaFisica.getIdNaturalidade() != null) {
					LOCIntegracaoLocalidadeVO localidade = localidadeDelegate.obterLocalidade(historicoPessoaFisica.getIdNaturalidade());
					String uf = localidade.getUf() != null ? " - " + localidade.getUf().getSiglaUF() : "";
					historicoPessoaFisica.setDescNaturalidade(localidade.getNome() + uf);
				}
				listaPessoaCompartilhamentoVO.add(historicoPessoaFisica);
			}
		}
		return listaPessoaCompartilhamentoVO;
	}


	/**
	 * Listar tributacoes.
	 *
	 * @param vo
	 *            o valor de vo
	 * @param pessoaCompartilhamento
	 *            o valor de pessoa compartilhamento
	 * @return List
	 */
	private List<Tributacao> listarTributacoes(CamposFichaCadastralVO vo, PessoaCompartilhamento pessoaCompartilhamento) {

		List<Tributacao> result = new ArrayList<Tributacao>();

		Tributacao tributacao = verificarConsultaEntidadeAutorizacaoJSON(pessoaCompartilhamento.getPessoa().getId(), TipoAutorizacaoEntidadeEnum.TRIBUTACAO, obterTipoConsultaEnum(vo));
		if (isEntidadeEmAutorizacao(tributacao)) {
			result.add(tributacao);

		} else {

			FichaCadastralNovaDelegate fichaCadastralDelegate = criarFichaCadastralNovaDelegate();

			ConsultaDtoCUC<Tributacao> consultaDto = new ConsultaDtoCUC<Tributacao>();
			consultaDto.setNomeConsulta(TRIBUTACAO_POR_PESSOA);

			Tributacao filtro = new Tributacao();
			copiarPropriedades(vo, consultaDto);

			filtro.setPessoaCompartilhamento(pessoaCompartilhamento);
			consultaDto.setFiltro(filtro);
			result.addAll(fichaCadastralDelegate.listar(Tributacao.class, consultaDto));
		}

		return result;
	}

	/**
	 * Listar pessoas instituicoes.
	 *
	 * @param vo
	 *            o valor de vo
	 * @param pessoaCompartilhamento
	 *            o valor de pessoa compartilhamento
	 * @return List
	 */
	private List<PessoaInstituicao> listarPessoasInstituicoes(CamposFichaCadastralVO vo, PessoaCompartilhamento pessoaCompartilhamento) {

		PessoaInstituicao pessoaInstituicao = new PessoaInstituicao();
		pessoaInstituicao.setPessoa(pessoaCompartilhamento.getPessoa());
		pessoaInstituicao.setIdInstituicao(obterInstituicaoUsuarioRelatorio().getIdInstituicao());

		ConsultaDtoCUC<PessoaInstituicao> dto = new ConsultaDtoCUC<PessoaInstituicao>();
		dto.setFiltro(pessoaInstituicao);
		dto.setNomeConsulta(PESSOA_INSTITUICAO_POR_PESSOA);
		copiarPropriedades(vo, dto);

		FichaCadastralNovaDelegate fichaCadastralDelegate = criarFichaCadastralNovaDelegate();
		
		return fichaCadastralDelegate.listar(PessoaInstituicao.class, dto);
	}

	/**
	 * Listar referencias.
	 *
	 * @param vo
	 *            o valor de vo
	 * @param pessoa
	 *            o valor de pessoa
	 * @return the list<? extends referencia base>
	 * @throws BancoobException
	 *             lança a exceção BancoobException
	 */
	private List<? extends ReferenciaBase> listarReferencias(CamposFichaCadastralVO vo, PessoaCompartilhamento pessoa) throws BancoobException {

		TelefoneDelegate telefoneDelegate = CAPESCadastroFabricaDelegates.getInstance().criarTelefoneDelegate();
		ConsultaDtoCUC<Referencia> consultaDto = new ConsultaDtoCUC<Referencia>();
		consultaDto.setFiltro(pessoa);
		consultaDto.setNomeConsulta(REFERENCIA_POR_PESSOA);

		copiarPropriedades(vo, consultaDto);

		FichaCadastralNovaDelegate fichaCadastralDelegate = criarFichaCadastralNovaDelegate();

		List<? extends ReferenciaBase> referencias = fichaCadastralDelegate.listar(Referencia.class, consultaDto);
		for (int i = 0; i < referencias.size(); i++) {

			ReferenciaBase referencia = referencias.get(i);

			// busca o primeiro telefone da pessoa de referência para exibir na listagem
			if (referencia.getPessoaReferencia() != null) {
				Telefone telefone = new Telefone();
				telefone.setPessoaCompartilhamento(referencia.getPessoaReferencia());

				ConsultaDto<Telefone> consulta = new ConsultaDto<Telefone>();
				consulta.setFiltro(telefone);
				List<Telefone> telefones = telefoneDelegate.listar(consulta);
				if (!telefones.isEmpty()) {
					telefone = telefones.get(0);
					referencia.setDdd(obterDDD(telefone));
					referencia.setTelefone(telefone.getTelefone());
				}
			}
		}
		return referencias;
	}

	/**
	 * Obter ddd.
	 *
	 * @param telefone
	 *            o valor de telefone
	 * @return Short
	 */
	private Short obterDDD(Telefone telefone) {
		Short numDDD = null;
		String ddd = telefone.getDdd();
		if (StringUtils.isNotBlank(ddd) && StringUtils.isNumericSpace(ddd)) {
			numDDD = Short.valueOf(telefone.getDdd());
		}
		return numDDD;
	}

	/**
	 * Listar certidoes.
	 *
	 * @param vo
	 *            o valor de vo
	 * @param pessoa
	 *            o valor de pessoa
	 * @return List
	 * @throws BancoobException
	 *             lança a exceção BancoobException
	 */
	private List<Certidao> listarCertidoes(CamposFichaCadastralVO vo, PessoaCompartilhamento pessoa) throws BancoobException {
		FichaCadastralNovaDelegate fichaCadastralDelegate = criarFichaCadastralNovaDelegate();

		ConsultaDtoCUC<Certidao> consultaDto = new ConsultaDtoCUC<Certidao>();
		consultaDto.setNomeConsulta(CERTIDAO_POR_PESSOA);

		Certidao filtro = new Certidao();
		copiarPropriedades(vo, consultaDto);

		filtro.setPessoa(pessoa);
		consultaDto.setFiltro(filtro);

		return fichaCadastralDelegate.listar(Certidao.class, consultaDto);
	}

	/**
	 * Listar produtores.
	 *
	 * @param vo
	 *            o valor de vo
	 * @param pessoa
	 *            o valor de pessoa
	 * @return List
	 * @throws BancoobException
	 *             lança a exceção BancoobException
	 */
	private List<Produtor> listarProdutores(CamposFichaCadastralVO vo, PessoaCompartilhamento pessoa) throws BancoobException {

		List<Produtor> result = new ArrayList<Produtor>();
		;

		Produtor produtor = verificarConsultaEntidadeAutorizacaoJSON(pessoa.getPessoa().getId(), TipoAutorizacaoEntidadeEnum.PRODUTOR, obterTipoConsultaEnum(vo));
		if (isEntidadeEmAutorizacao(produtor)) {
			result.add(produtor);

		} else {

			FichaCadastralNovaDelegate fichaCadastralDelegate = criarFichaCadastralNovaDelegate();

			ConsultaDtoCUC<Certidao> consultaDto = new ConsultaDtoCUC<Certidao>();
			consultaDto.setNomeConsulta(PRODUTOR_POR_PESSOA);

			Produtor filtro = new Produtor();
			copiarPropriedades(vo, consultaDto);

			filtro.setPessoaCompartilhamento(pessoa);
			consultaDto.setFiltro(filtro);
			result.addAll(fichaCadastralDelegate.listar(Produtor.class, consultaDto));
		}

		return result;
	}

	/**
	 * Listar produtividade.
	 *
	 * @param vo o valor de vo
	 * @param pessoa o valor de pessoa
	 * @return the list<? extends produtividade base>
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private List<? extends ProdutividadeBase> listarProdutividade(CamposFichaCadastralVO vo,
			PessoaCompartilhamento pessoa) throws BancoobException {
		FichaCadastralNovaDelegate delegate = criarFichaCadastralNovaDelegate();
		
		ConsultaDtoCUC<Produtividade> consultaDto = new ConsultaDtoCUC<Produtividade>();
		consultaDto.setNomeConsulta(PRODUTIVIDADE_POR_PESSOA);
		
		Produtividade filtro = new Produtividade();
		copiarPropriedades(vo, consultaDto);

		filtro.setPessoaCompartilhamento(pessoa);
		consultaDto.setFiltro(filtro);

		List<? extends ProdutividadeBase> produtividades = delegate.listar(Produtividade.class,
				consultaDto);
		
		List<ProdutividadeBase> produtividadesRemove =  new ArrayList<ProdutividadeBase>();
		for (ProdutividadeBase produtividade : produtividades) {
			if (produtividade instanceof HistoricoProdutividade) {
				HistoricoProdutividade historico = (HistoricoProdutividade) produtividade;
				if (!historico.getDataHoraInicio().after(vo.getData()) && !historico.getDataHoraFim().after(vo.getData())) {
						produtividadesRemove.add(produtividade);
				}
			}
		}
		produtividades.removeAll(produtividadesRemove);
		return produtividades;
	}

	/**
	 * O método Copiar propriedades.
	 *
	 * @param vo o valor de vo
	 * @param consultaDto o valor de consulta dto
	 */
	private void copiarPropriedades(CamposFichaCadastralVO vo,
			ConsultaDtoCUC<?> consultaDto) {
		consultaDto.setTipoConsulta(vo.getHistoricoEm()!= null && vo.getHistoricoEm() == true ? obterNovoTipoConsultaEnum(vo) : obterTipoConsultaEnum(vo));
		consultaDto.setData(new DateTimeDB(vo.getData().getTime()));
		consultaDto.setDataFim(new DateTimeDB(vo.getDataFim().getTime()));
	}

	/**
	 * Obter tipo consulta enum.
	 *
	 * @param vo
	 *            o valor de vo
	 * @return TipoConsultaEnum
	 */
	private TipoConsultaEnum obterTipoConsultaEnum(CamposFichaCadastralVO vo) {
		TipoConsultaEnum tipoConsultaEnum = null;
		if (vo.getPosicao() != null && vo.getPosicao().booleanValue() == true) {
			tipoConsultaEnum = TipoConsultaEnum.PERIODO;
		} else if (vo.getAtual() != null && vo.getAtual().booleanValue() == true) {
			tipoConsultaEnum = TipoConsultaEnum.ATUAL;
		} else if (vo.getHistorico() != null && vo.getHistorico().booleanValue() == true) {
			tipoConsultaEnum = TipoConsultaEnum.TUDO;
		} else if (vo.getAutorizacao() != null && vo.getAutorizacao().booleanValue() == true) {
			tipoConsultaEnum = TipoConsultaEnum.AUTORIZACAO_FICHA_PREVIA;
		}
		return tipoConsultaEnum;
	}

	/**
	 * Listar telefones.
	 *
	 * @param pessoa
	 *            o valor de pessoa
	 * @param vo
	 *            o valor de vo
	 * @return List
	 * @throws BancoobException
	 *             lança a exceção BancoobException
	 */
	private List<Telefone> listarTelefones(PessoaCompartilhamento pessoa, CamposFichaCadastralVO vo) throws BancoobException {

		ConsultaDtoCUC<Telefone> criterios = new ConsultaDtoCUC<Telefone>();
		Telefone filtro = new Telefone();
		filtro.setPessoaCompartilhamento(pessoa);
		criterios.setFiltro(filtro);
		criterios.setNomeConsulta(TELEFONE_POR_PESSOA_COMPARTILHAMENTO);
		copiarPropriedades(vo, criterios);

		FichaCadastralNovaDelegate fichaCadastralDelegate = criarFichaCadastralNovaDelegate();

		return fichaCadastralDelegate.listar(Telefone.class, criterios);
	}

	/**
	 * Criar ficha cadastral delegate.
	 *
	 * @return FichaCadastralDelegate
	 */
	private FichaCadastralNovaDelegate criarFichaCadastralNovaDelegate() {
		return fabricaDelegates.criarFichaCadastralNovaDelegate();
	}

	/**
	 * Obter pessoa instituicao.
	 *
	 * @param pessoa
	 *            o valor de pessoa
	 * @return PessoaInstituicao
	 * @throws RegistroNaoEncontradoNegocioException
	 *             lança a exceção RegistroNaoEncontradoNegocioException
	 */
	private PessoaInstituicao obterPessoaInstituicao(PessoaCompartilhamento pessoa) throws RegistroNaoEncontradoNegocioException {

		PessoaInstituicaoDelegate pessoaInstituicaoDelegate = fabricaDelegates.criarPessoaInstituicaoDelegate();

		PessoaInstituicao pessoaInstituicaoFiltro = new PessoaInstituicao();
		pessoaInstituicaoFiltro.setPessoa(pessoa.getPessoa());
		pessoaInstituicaoFiltro.setIdInstituicao(obterInstituicaoUsuarioRelatorio().getIdInstituicao());
		PessoaInstituicao pessoaInstituicao = null;

		try {
			pessoaInstituicao = pessoaInstituicaoDelegate.obterPorPessoaInstituicao(pessoaInstituicaoFiltro);
		} catch (RegistroNaoEncontradoNegocioException e) {
		}

		return pessoaInstituicao;
	}

	/**
	 * Obter tributacao.
	 *
	 * @param tributacoes
	 *            o valor de tributacoes
	 * @return Tributacao
	 * @throws BancoobException
	 *             lança a exceção BancoobException
	 */
	private TributacaoBase obterTributacao(List<Tributacao> tributacoes) throws BancoobException {
		if (tributacoes != null && tributacoes.size() == 1) {
			return tributacoes.get(0);
		}
		return null;
	}

	/**
	 * Listar fonte renda.
	 *
	 * @param pessoa
	 *            o valor de pessoa
	 * @param vo
	 *            o valor de vo
	 * @return List
	 * @throws BancoobException
	 *             lança a exceção BancoobException
	 */
	private List<FonteRenda> listarFonteRenda(PessoaCompartilhamento pessoa, CamposFichaCadastralVO vo) throws BancoobException {

		FonteRenda fonteRenda = new FonteRenda();
		fonteRenda.setPessoaCompartilhamento(pessoa);

		ConsultaDtoCUC<FonteRenda> criterios = new ConsultaDtoCUC<FonteRenda>();
		criterios.setFiltro(fonteRenda);
		criterios.setNomeConsulta(PESQUISA_FONTE_RENDA_POR_PESSOA_COMPARTILHAMENTO);
		copiarPropriedades(vo, criterios);

		FichaCadastralNovaDelegate fichaCadastralDelegate = criarFichaCadastralNovaDelegate();

		return fichaCadastralDelegate.listar(FonteRenda.class, criterios);
	}

	/**
	 * Listar enderecos.
	 *
	 * @param pessoa
	 *            o valor de pessoa
	 * @param vo
	 *            o valor de vo
	 * @return List
	 * @throws BancoobException
	 *             lança a exceção BancoobException
	 */
	private List<EnderecoBase> listarEnderecos(PessoaCompartilhamento pessoa, CamposFichaCadastralVO vo) throws BancoobException {

		final Map<Integer, String> tiposLogradouro = criarMapaTipoLogradouro();
		Endereco filtro = new Endereco();
		filtro.setPessoaCompartilhamento(pessoa);

		ConsultaDtoCUC<Endereco> criterios = new ConsultaDtoCUC<Endereco>();
		criterios.setFiltro(filtro);
		criterios.setNomeConsulta(ENDERECO_POR_PESSOA_COMPARTILHAMENTO);
		copiarPropriedades(vo, criterios);

		FichaCadastralNovaDelegate fichaCadastralDelegate = criarFichaCadastralNovaDelegate();

		List<EnderecoBase> lista = new ArrayList<EnderecoBase>(fichaCadastralDelegate.listar(Endereco.class, criterios));

		for (EnderecoBase endereco : lista) {
			Localidade localidade = obterLocalidade(endereco.getLocalidade().getIdLocalidade());
			endereco.setLocalidade(localidade);

			TipoLogradouro tipoLogradouro = endereco.getTipoLogradouro();
			if (tipoLogradouro != null) {
				tipoLogradouro.setNome(tiposLogradouro.get(tipoLogradouro.getIdTipoLogradouro()));
			}
		}

		return lista;
	}

	/**
	 * Criar mapa tipo logradouro.
	 *
	 * @return Map
	 * @throws BancoobException
	 *             lança a exceção BancoobException
	 */
	private Map<Integer, String> criarMapaTipoLogradouro() throws BancoobException {
		LOCIntegracaoDelegate delegate = criarLocalidadeDelegate();

		Map<Integer, String> mapa = new HashMap<Integer, String>();
		for (LOCIntegracaoTipoLogradouroVO tipoLograduro : delegate.listarTiposLogradouro()) {
			mapa.put(tipoLograduro.getIdTipoLogradouro(), tipoLograduro.getNome());
		}
		return mapa;
	}

	/**
	 * Criar localidade delegate.
	 *
	 * @return LOCIntegracaoDelegate
	 */
	private LOCIntegracaoDelegate criarLocalidadeDelegate() {
		return CAPESIntegracaoFabricaDelegates.getInstance().criarLOCIntegracaoDelegate();
	}

	/**
	 * Listar emails.
	 *
	 * @param pessoa
	 *            o valor de pessoa
	 * @param vo
	 *            o valor de vo
	 * @return List
	 * @throws BancoobException
	 *             lança a exceção BancoobException
	 */
	private List<Email> listarEmails(PessoaCompartilhamento pessoa, CamposFichaCadastralVO vo) throws BancoobException {
		ConsultaDtoCUC<Email> criterios = new ConsultaDtoCUC<Email>();

		Email filtro = new Email();
		filtro.setPessoaCompartilhamento(pessoa);
		criterios.setFiltro(filtro);
		criterios.setNomeConsulta(EMAIL_POR_PESSOA_COMPARTILHAMENTO);
		copiarPropriedades(vo, criterios);

		FichaCadastralNovaDelegate fichaCadastralDelegate = criarFichaCadastralNovaDelegate();

		return fichaCadastralDelegate.listar(Email.class, criterios);
	}

	/**
	 * Listar relacionamentos.
	 *
	 * @param pessoa
	 *            o valor de pessoa
	 * @param vo
	 *            o valor de vo
	 * @return List
	 * @throws BancoobException
	 *             lança a exceção BancoobException
	 */
	private List<RelacionamentoPessoaBase> listarRelacionamentos(PessoaCompartilhamento pessoa, CamposFichaCadastralVO vo) throws BancoobException {

		RelacionamentoPessoa filtro = new RelacionamentoPessoa();
		filtro.setPessoa(pessoa.getPessoa());
		filtro.setIdInstituicao(obterInstituicaoUsuarioRelatorio().getIdInstituicao());
		filtro.setPessoaCompartilhamento(pessoa);

		ConsultaDtoCUC<RelacionamentoPessoa> criterios = new ConsultaDtoCUC<RelacionamentoPessoa>();
		criterios.setFiltro(filtro);
		criterios.setOrdenacao("dataHoraInicio");
		criterios.setNomeConsulta("PESQUISAR_RELACIONAMENTOS_CEDIDOS");
		copiarPropriedades(vo, criterios);

		return obterListaRelacionamentos(criterios);
	}

	private List<RelacionamentoPessoaBase> obterListaRelacionamentos(ConsultaDtoCUC<RelacionamentoPessoa> criterios) throws BancoobException {
		RelacionamentoPessoaDelegate delegate = fabricaDelegates.criarRelacionamentoPessoaDelegate();
		List<RelacionamentoPessoaBase> retorno = new ArrayList<RelacionamentoPessoaBase>();
		if (criterios.getTipoConsulta().equals(TipoConsultaEnum.ATUAL)) {
			retorno.addAll(daoRelacionamento.pesquisarRelacionamentosVigentesPorFiltro((RelacionamentoPessoa) criterios.getFiltro()));
		} else if (criterios.getTipoConsulta().equals(TipoConsultaEnum.PERIODO)) {
			retorno.addAll(delegate.listarHistoricoEspecifico(criterios));
		} else if (criterios.getTipoConsulta().equals(TipoConsultaEnum.TUDO)) {
			criterios.setData(null);
			retorno.addAll(delegate.pesquisarRelacionamentosVigentesPorFiltro((RelacionamentoPessoa) criterios.getFiltro()));
			retorno.addAll(delegate.listarHistoricoEspecifico(criterios));
		}
		return retorno;
	}

	/**
	 * Monta dados do Bem novo para o relatorio de historico da ficha
	 * 
	 * @param idPessoa
	 * @param fichaCadastralVO
	 * @throws BancoobException
	 */
	private void montarDadosBemNovo(Long idPessoa, FichaCadastralVO fichaCadastralVO) throws BancoobException {
		List<br.com.sicoob.capes.negocio.entidades.bem.Bem> bemNovoMovel = bemDelegate.obterBensPorPessoaCompartilhamento(idPessoa, TipoClassificacaoBemEnum.BEM_MOVEL.getCodigo());
		List<br.com.sicoob.capes.negocio.entidades.bem.Bem> bemNovoImovel = bemDelegate.obterBensImoveisPorPessoaCompartilhamento(idPessoa);
		List<FichaCadastralBemNovoVO> listaFichaCadastralBemNovoVO = new ArrayList<FichaCadastralBemNovoVO>();
		criarListaBensNovosImoves(bemNovoImovel, listaFichaCadastralBemNovoVO);
		criarListaBensNovosMoveis(bemNovoMovel, listaFichaCadastralBemNovoVO);
		listaFichaCadastralBemNovoVO.get(0).setBensDTO(fichaCadastralVO.getBensDTO());
		fichaCadastralVO.setBensNovos(listaFichaCadastralBemNovoVO);
	}

	private void obterHistoricoBensNovos(FichaCadastralVO fichaCadastralVO, List<HistoricoBemPessoaCompartilhamento> historicosBemPessoaCompartilhamento) throws BancoobException {
		List<FichaCadastralBemVO> bensImoveisDTO = new ArrayList<FichaCadastralBemVO>();
		FichaCadastralBemVO f = new FichaCadastralBemVO();
		f.setBens(new ArrayList<BemBase>());
		for (HistoricoBemPessoaCompartilhamento hist : historicosBemPessoaCompartilhamento) {
			HistoricoBem b = new HistoricoBem();
			b.setDataHoraInicio(hist.getDataHoraInicio());
			b.setDescricao(hist.getBem().getDescricao());
			b.setValorAtualMercado(hist.getBem().getValor());
			b.setDataHoraFim(hist.getDataHoraFim());
			SubTipoBem sub = new SubTipoBem();
			TipoBem t = new TipoBem();
			if (hist.getBem().getTipoClassificacaoBem() != null) {
				t.setDescricao(hist.getBem().getTipoClassificacaoBem().getDescricao());
			}
			sub.setTipoBem(t);
			String descricao = "";
			br.com.sicoob.capes.negocio.entidades.bem.Bem bem = hist.getBem();
			if (bem instanceof br.com.sicoob.capes.negocio.entidades.bem.BemImovel) {
				br.com.sicoob.capes.negocio.entidades.bem.BemImovel bemR = (br.com.sicoob.capes.negocio.entidades.bem.BemImovel) bemDelegate.obter(bem.getId());
				descricao = bemR.getTipoBem().getDescricao();
			} else {
				if (bem.getTipoClassificacaoBem() != null) {
					br.com.sicoob.capes.negocio.entidades.bem.BemMovel bemR = (br.com.sicoob.capes.negocio.entidades.bem.BemMovel) bemDelegate.obter(bem.getId());
					descricao = bemR.getTipoBem().getDescricao();
				}
			}
			sub.setDescricao(descricao);
			b.setSubTipo(sub);
			f.getBens().add(b);
		}
		if (f.getBens().size() > 0) {
			bensImoveisDTO.add(f);
			fichaCadastralVO.getBensDTO().addAll(bensImoveisDTO);
		}

	}

	private void verificaFiliacao(PessoaFisica pessoaF, FichaCadastralVO fichaCadastralVO) {
		String nomeMae = StringUtils.trim(pessoaF.getNomeMae());
		String nomePai = StringUtils.trim(pessoaF.getNomePai());
		String filiacao = "";

		// FIXME: bruno.carneiro - REMOVER APÓS ENTRADA EM PRODUÇÃO... CORREÇÃO DA MSG.
		// A mensagem estava com a acentação incorreta.
		String NAO_INFORMADO = "NÃO INFORMADO";
		String NAO_INFORMADO_DOCUMENTO_IDENTIFICACAO = "NÃO INFORMADO NO DOCUMENTO DE IDENTIFICAÇÃO";

		boolean paiMaePreenchidos = (!StringUtils.isEmpty(nomeMae) && !StringUtils.isEmpty(nomePai));

		if (paiMaePreenchidos) {
			if (nomeMae.startsWith(NAO_INFORMADO) && nomePai.startsWith(NAO_INFORMADO)) {
				filiacao = NAO_INFORMADO_DOCUMENTO_IDENTIFICACAO;
			} else {
				filiacao = nomePai.concat(" e ").concat(nomeMae);
			}
		} else if (!StringUtils.isEmpty(nomePai)) {
			filiacao = nomePai;
		} else if (!StringUtils.isEmpty(nomeMae)) {
			filiacao = nomeMae;
		}

		fichaCadastralVO.setFiliacao(filiacao);
	}

	private String criarNovaLabelFiltroData(CamposFichaCadastralVO vo) {
		TipoConsultaEnum tipoConsultaEnum = obterNovoTipoConsultaEnum(vo);
		String labelFiltroData = null;
		if (tipoConsultaEnum != null) {
			labelFiltroData = obterLabelDataEmissaoFichaCadastral(vo.getData(),vo.getDataFim(),tipoConsultaEnum);
		}
		return labelFiltroData;
	}

	private TipoConsultaEnum obterNovoTipoConsultaEnum(CamposFichaCadastralVO vo) {
		TipoConsultaEnum tipoConsultaEnum = null;
		if (vo.getAtual() != null && vo.getAtual().booleanValue() == true) {
			tipoConsultaEnum = TipoConsultaEnum.ATUAL;
		} else if (vo.getHistoricoEm() != null && vo.getHistoricoEm().booleanValue() == true && vo.getData() == null) {
			tipoConsultaEnum = TipoConsultaEnum.TUDO;
		} else if (vo.getHistoricoEm() != null && vo.getHistoricoEm().booleanValue() == true) {
			tipoConsultaEnum = TipoConsultaEnum.PERIODO;
		}
		return tipoConsultaEnum;
	}

	private RetornoProcessamentoRelatorioDTO obterRelatorioFichaCadastral(PessoaCompartilhamento pessoaCompartilhamento, FichaCadastralVO fichaCadastralVO, CamposFichaCadastralVO vo, RelatorioDadosDTO rDto) throws BancoobException {

		if (vo.getAtual()) {
			fichaCadastralVO.setTotalFontesDeRendaMensal(calculaTotalFonteDeRendasMensal(fichaCadastralVO.getFontesDeRenda()));
			fichaCadastralVO.setTotalParticipacao(calculaTotalDasParticipacoes(pessoaCompartilhamento.getId()));
			return geraFichaSimples(pessoaCompartilhamento, fichaCadastralVO, rDto);
		}

		if (vo.getHistoricoEm()) {
			fichaCadastralVO.setTotalParticipacao(calculaTotalDasParticipacoes(pessoaCompartilhamento.getId()));
			fichaCadastralVO.setTotalPatrimonio(calculaTotalDoPatrimonio(fichaCadastralVO.getBensDTO()));
			return geraFichaCompleta(pessoaCompartilhamento, fichaCadastralVO, rDto);
		}

		return null;

	}

	private RetornoProcessamentoRelatorioDTO geraFichaSimples(PessoaCompartilhamento pessoaCompartilhamento, FichaCadastralVO fichaCadastralVO, RelatorioDadosDTO rDto) throws BancoobException {
		RelatorioFichaCadastralSimples fichaSimples = null;

		if (isPessoaFisica(pessoaCompartilhamento)) {
			fichaSimples = new RelatorioFichaCadastralSimplesPessoaFisica(fichaCadastralVO);
		} else {
			fichaSimples = new RelatorioFichaCadastralSimplesPessoaJuridica(fichaCadastralVO);
		}

		return fichaSimples.gerarRelatorio(rDto);
	}

	private RetornoProcessamentoRelatorioDTO geraFichaCompleta(PessoaCompartilhamento pessoaCompartilhamento, FichaCadastralVO fichaCadastralVO, RelatorioDadosDTO rDto) throws BancoobException {
		RelatorioFichaCadastralNova relatorioFichaCadastral = null;

		if (isPessoaFisica(pessoaCompartilhamento)) {
			relatorioFichaCadastral = new RelatorioFichaCadastralNovaPessoaFisica(fichaCadastralVO);
		} else {
			relatorioFichaCadastral = new RelatorioFichaCadastralNovaPessoaJuridica(fichaCadastralVO);
		}

		return relatorioFichaCadastral.gerarRelatorio(rDto);
	}

	private BigDecimal calculaTotalDasParticipacoes(Long idPessoaCompartilhamento) throws BancoobException {
		BigDecimal total = BigDecimal.ZERO;
		ValoresBensVO valore = daoBem.obterValoresBensPessoa(idPessoaCompartilhamento);

		if (valore.getValorTotalDeclarado() != null) {
			total = valore.getValorTotalDeclarado();
		}

		return total;
	}

	private BigDecimal calculaTotalDoPatrimonio(List<FichaCadastralBemVO> bensDTO) {
		BigDecimal total = BigDecimal.ZERO;
		if (bensDTO != null && !bensDTO.isEmpty()) {
			for (FichaCadastralBemVO bem : bensDTO) {
				if (!(bem.getBens().get(0) instanceof HistoricoBem)) {
					BigDecimal result = bem.getBens().get(0).getValorAtualMercado();
					total = total.add(result);
				}
			}
		}
		return total;
	}

	private BigDecimal calculaTotalFonteDeRendasMensal(List<FonteRenda> fontesDeRenda) {
		BigDecimal total = BigDecimal.ZERO;
		if (fontesDeRenda != null && !fontesDeRenda.isEmpty()) {
			for (FonteRendaBase fonteRenda : fontesDeRenda) {
				BigDecimal result = fonteRenda.getValorReceitaBrutaMensal();
				total = total.add(result);
			}
		}
		return total;
	}

}
