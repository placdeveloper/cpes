package br.com.sicoob.capes.relatorio.negocio.servicos.ejb;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.FichaCadastralDelegate;
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
import br.com.sicoob.capes.cadastro.util.EntidadeCadastroBaseComparator;
import br.com.sicoob.capes.cadastro.util.IntegracaoUtil;
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
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.Localidade;
import br.com.sicoob.capes.negocio.entidades.ProdutividadeBase;
import br.com.sicoob.capes.negocio.entidades.ReferenciaBase;
import br.com.sicoob.capes.negocio.entidades.RelacionamentoPessoaBase;
import br.com.sicoob.capes.negocio.entidades.TipoLogradouro;
import br.com.sicoob.capes.negocio.entidades.TipoPessoa;
import br.com.sicoob.capes.negocio.entidades.TransicaoPessoa;
import br.com.sicoob.capes.negocio.entidades.TributacaoBase;
import br.com.sicoob.capes.negocio.entidades.bemantigo.Bem;
import br.com.sicoob.capes.negocio.entidades.bemantigo.BemBase;
import br.com.sicoob.capes.negocio.entidades.bemantigo.BemImovel;
import br.com.sicoob.capes.negocio.entidades.bemantigo.BemOnus;
import br.com.sicoob.capes.negocio.entidades.bemantigo.BemPosse;
import br.com.sicoob.capes.negocio.entidades.bemantigo.BemRegistro;
import br.com.sicoob.capes.negocio.entidades.bemantigo.HistoricoBem;
import br.com.sicoob.capes.negocio.entidades.bemantigo.HistoricoBemImovel;
import br.com.sicoob.capes.negocio.entidades.bemantigo.TipoBem;
import br.com.sicoob.capes.negocio.entidades.historico.HistoricoProdutividade;
import br.com.sicoob.capes.negocio.entidades.vigente.Certidao;
import br.com.sicoob.capes.negocio.entidades.vigente.Email;
import br.com.sicoob.capes.negocio.entidades.vigente.Endereco;
import br.com.sicoob.capes.negocio.entidades.vigente.FonteRenda;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaInstituicao;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaJuridica;
import br.com.sicoob.capes.negocio.entidades.vigente.Produtividade;
import br.com.sicoob.capes.negocio.entidades.vigente.Produtor;
import br.com.sicoob.capes.negocio.entidades.vigente.Referencia;
import br.com.sicoob.capes.negocio.entidades.vigente.RelacionamentoPessoa;
import br.com.sicoob.capes.negocio.entidades.vigente.Telefone;
import br.com.sicoob.capes.negocio.entidades.vigente.Tributacao;
import br.com.sicoob.capes.relatorio.negocio.relatorios.RelatorioFichaCadastral;
import br.com.sicoob.capes.relatorio.negocio.relatorios.RelatorioFichaCadastralPessoaFisica;
import br.com.sicoob.capes.relatorio.negocio.relatorios.RelatorioFichaCadastralPessoaJuridica;
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

/**
 * EJB contendo servicos relacionados a RelatorioFichaCadastral.
 */
@Stateless
@Remote({ IProcessamentoRelatorioServico.class })
public class RelatorioFichaCadastralServicoEJB extends CAPESRelatorioServicoEJB implements IProcessamentoRelatorioServico {
	
	/** A constante REFERENCIA_POR_PESSOA. */
	private static final String REFERENCIA_POR_PESSOA = "PESQUISA_REFERENCIA_POR_PESSOA";
	
	/** A constante TRIBUTACAO_POR_PESSOA. */
	private static final String TRIBUTACAO_POR_PESSOA = "TRIBUTACAO_POR_PESSOA";
	
	/** A constante PESQUISA_FONTE_RENDA_POR_PESSOA_COMPARTILHAMENTO. */
	private static final String PESQUISA_FONTE_RENDA_POR_PESSOA_COMPARTILHAMENTO = "PESQUISA_FONTE_RENDA_POR_PESSOA_COMPARTILHAMENTO";
	
	/** A constante BEM_POR_PESSOA. */
	private static final String BEM_POR_PESSOA = "PESQUISA_BEM_POR_PESSOA";
	
	/** A constante PESQUISA_HISTORICO_BEM_IMOVEL_POR_PESSOA. */
	private static final String PESQUISA_HISTORICO_BEM_IMOVEL_POR_PESSOA = "PESQUISA_HISTORICO_BEM_IMOVEL_POR_PESSOA";
	
	/** A constante BEM_DIFERENTE_DE_IMOVEL_POR_PESSOA. */
	private static final String BEM_DIFERENTE_DE_IMOVEL_POR_PESSOA = "PESQUISA_BEM_DIFERENTE_DE_IMOVEL_POR_PESSOA";
	
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
	
	/** A constante DEPENDENCIA_POR_BEM. */
	private static final String DEPENDENCIA_POR_BEM = "DEPENDENCIA_POR_BEM";
	
	/** O atributo fabricaDelegates. */
	private static CAPESCadastroFabricaDelegates fabricaDelegates = CAPESCadastroFabricaDelegates
			.getInstance();
	
	/** O atributo capesIntegracaoFabricaDelegates. */
	private static CAPESIntegracaoFabricaDelegates capesIntegracaoFabricaDelegates = CAPESIntegracaoFabricaDelegates.getInstance();
	
	@Inject
	@Default
	private RelatorioValidacaoCadastralDAO dao;

	@Inject
	@Default
	private RelatorioBemDAO daoBem;

	@Inject
	@Default
	private RelatorioRelacionamentoDAO daoRelacionamento;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public RetornoProcessamentoRelatorioDTO gerarRelatorio(ParametroDTO dto, RelatorioDadosDTO rDto) throws BancoobException {
		Number idPessoa = (Number) dto.getDados().get("idPessoa");
		Number idPessoaCompartilhamento = (Number) dto.getDados().get("idPessoaCompartilhamento");
		Number codigoTipoPessoa = (Number) dto.getDados().get("codigoTipoPessoa");
		Boolean autorizacao = (Boolean) dto.getDados().get("autorizacao");

		Boolean atual = (Boolean) dto.getDados().get("atual");
		Boolean historico = (Boolean) dto.getDados().get("historico");
		Boolean posicao = (Boolean) dto.getDados().get("posicao");

		Boolean bens = (Boolean) dto.getDados().get("bens");
		Boolean certidoes = (Boolean) dto.getDados().get("certidoes");
		Boolean emails = (Boolean) dto.getDados().get("emails");
		Boolean enderecos = (Boolean) dto.getDados().get("enderecos");
		Boolean produtores = (Boolean) dto.getDados().get("produtores");
		Boolean referencias = (Boolean) dto.getDados().get("referencias");
		Boolean fontesDeRenda = (Boolean) dto.getDados().get("fontesDeRenda");
		Boolean telefones = (Boolean) dto.getDados().get("telefones");
		Boolean relacionamentos = (Boolean) dto.getDados().get("relacionamentos");
		
		Date data = (Date) dto.getDados().get("data");
		Date dataFim = (Date) dto.getDados().get("dataFim");
		
		CamposFichaCadastralVO vo = new CamposFichaCadastralVO();
		vo.setTipoPessoa(codigoTipoPessoa != null ? codigoTipoPessoa.intValue() : null);
		vo.setAutorizacao(autorizacao);
		vo.setAtual(atual);
		vo.setHistorico(historico);
		vo.setPosicao(posicao);
		vo.setData(data);
		vo.setDataFim(dataFim);
		
		vo.setBens(bens);
		vo.setCertidoes(certidoes);
		vo.setEmails(emails);
		vo.setEnderecos(enderecos);
		vo.setProdutores(produtores);
		vo.setReferencias(referencias);
		vo.setFontesDeRenda(fontesDeRenda);
		vo.setTelefones(telefones);
		vo.setRelacionamentos(relacionamentos);

		if (autorizacao != null && autorizacao.booleanValue()) {
			vo.setBens(true);
			vo.setCertidoes(true);
			vo.setEmails(true);
			vo.setEnderecos(true);
			vo.setProdutores(true);
			vo.setReferencias(true);
			vo.setFontesDeRenda(true);
			vo.setTelefones(true);
			vo.setRelacionamentos(true);
		}

		if (vo.getData() == null) {
			vo.setData(new DateTimeDB());
		}

		PessoaCompartilhamentoDelegate pessoaCompartilhamentoDelegate = fabricaDelegates.criarPessoaCompartilhamentoDelegate();
		ADMIntegracaoDelegate admIntegracaoDelegate = capesIntegracaoFabricaDelegates.criarADMIntegracaoDelegate();
		
		PessoaCompartilhamento pessoaCompartilhamento = pessoaCompartilhamentoDelegate
				.obter(idPessoaCompartilhamento.longValue());
		PessoaCompartilhamento pessoaCompartilhamentoJSON = verificarConsultaEntidadeAutorizacaoJSON(idPessoa.intValue(), TipoAutorizacaoEntidadeEnum.PESSOA, 
				obterTipoConsultaEnum(vo));
		
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
		}

		List<PessoaCompartilhamento> pessoasCompartilhamento = listarPessoaCompartilhamento(vo,
				pessoaCompartilhamento);
		
		if (pessoasCompartilhamento.isEmpty()) {
			throw new PessoaNaoEncontradaNoPeriodoException();
		}
		
		fichaCadastralVO.setDataUltimaAtualizacao(pessoaCompartilhamento.getDataRenovacaoCadastral());
//		buscarNaturalidadePessoa(pessoasCompartilhamento);		
		fichaCadastralVO.setPessoasCompartilhamento(pessoasCompartilhamento);
		
		if (vo.getCertidoes()) {
			fichaCadastralVO.setCertidoes(listarCertidoes(vo, pessoaCompartilhamento));
		}
		if (vo.getRelacionamentos()) {
			fichaCadastralVO.setRelacionamentos(listarRelacionamentos(pessoaCompartilhamento, vo));
		}
		if (vo.getEmails()) {
			contatoVO.setEmails(listarEmails(pessoaCompartilhamento, vo));
		}
		if (vo.getEnderecos()) {
			contatoVO.setEnderecos(listarEnderecos(pessoaCompartilhamento, vo));
		}
		if (vo.getFontesDeRenda()) {
			fichaCadastralVO.setFontesDeRenda(listarFonteRenda(pessoaCompartilhamento, vo));
		}
		if (vo.getTelefones()) {
			contatoVO.setTelefones(listarTelefones(pessoaCompartilhamento, vo));
		}
		if (vo.getReferencias()) {
			fichaCadastralVO.setReferencias(listarReferencias(vo, pessoaCompartilhamento));
		}
		if (vo.getProdutores()) {
			fichaCadastralVO.setProdutores(listarProdutores(vo, pessoaCompartilhamento));
			fichaCadastralVO.setProdutividades(listarProdutividade(vo, pessoaCompartilhamento));
		}
		if (vo.getBens()) {
			List<BemBase> bensImoveis = (List<BemBase>) listarBensImoveis(vo, pessoaCompartilhamento);
			List<FichaCadastralBemVO> bensImoveisDTO = criarListaBens(vo, bensImoveis);
			
			List<BemBase> bensOutros = (List<BemBase>) listarBensOutros(vo, pessoaCompartilhamento);
			List<FichaCadastralBemVO> bensOutrosDTO = criarListaBens(vo, bensOutros);
			
			fichaCadastralVO.getBensDTO().addAll(bensImoveisDTO);
			fichaCadastralVO.getBensDTO().addAll(bensOutrosDTO);
		}
		
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
		fichaCadastralVO.setExibirTotalProdutividade(vo.getHistorico() != null ? !vo.getHistorico().booleanValue() : false);
		
		RelatorioFichaCadastral relatorioFichaCadastral = null;		
		relatorioFichaCadastral = obterRelatorioFichaCadastral(pessoaCompartilhamento, fichaCadastralVO);
		
		return relatorioFichaCadastral.gerarRelatorio(rDto);
	}


	/**
	 * Verificar consulta entidade autorizacao json.
	 *
	 * @param <E> o tipo generico
	 * @param idPessoaSelecionada o valor de id pessoa selecionada
	 * @param tipoAutorizacao o valor de tipo autorizacao
	 * @param tipoConsultaEnum o valor de tipo consulta enum
	 * @return E
	 */
	@SuppressWarnings("unchecked")
	private <E> E verificarConsultaEntidadeAutorizacaoJSON(Integer idPessoaSelecionada,
			TipoAutorizacaoEntidadeEnum tipoAutorizacao, TipoConsultaEnum tipoConsultaEnum) {
		
		if (tipoConsultaEnum == TipoConsultaEnum.AUTORIZACAO_FICHA_PREVIA) {
			ConsultaAutorizacaoDTO dto = new ConsultaAutorizacaoDTO();
			dto.setIdPessoaSelecionada(idPessoaSelecionada);
			dto.setTipoAutorizacao(tipoAutorizacao);
			FichaCadastralDelegate fichaCadastralDelegate = criarFichaCadastralDelegate();
			return (E)fichaCadastralDelegate.obterEntidadeAutorizacao(dto);
		}
		return null;
	}

	/**
	 * Verifica se eh entidade em autorizacao.
	 *
	 * @param entidade o valor de entidade
	 * @return {@code true}, se for entidade em autorizacao
	 */
	private boolean isEntidadeEmAutorizacao(
			CAPESEntidade<Long> entidade) {
		return entidade != null
				&& entidade.getId() > 0;
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
	 * @param data o valor de data
	 * @return String
	 */
	private String formatarData(Date data) {
		if(data != null) {
			return new SimpleDateFormat("dd/MM/yyyy").format(data);
		}
		return "";
	}
	
	private String obterLabelDataEmissaoFichaCadastral(Date dataEmissao, TipoConsultaEnum tipoConsultaEnum){
		if(tipoConsultaEnum.equals(TipoConsultaEnum.ATUAL)){
			Date dataAtual = new Date();
			return "POSIÇÃO EM: "+formatarData(dataAtual);
		}else{
			return "HISTÓRICO EM: "+formatarData(dataEmissao);
		}
	}
	
	/**
	 * Obter relatorio ficha cadastral.
	 *
	 * @param pessoaCompartilhamento o valor de pessoa compartilhamento
	 * @param fichaCadastralVO o valor de ficha cadastral vo
	 * @return RelatorioFichaCadastral
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private RelatorioFichaCadastral obterRelatorioFichaCadastral(
			PessoaCompartilhamento pessoaCompartilhamento,
			FichaCadastralVO fichaCadastralVO) throws BancoobException {
		
		RelatorioFichaCadastral relatorioFichaCadastral = null;
		
		if (isPessoaFisica(pessoaCompartilhamento)) {
			relatorioFichaCadastral = 
				new RelatorioFichaCadastralPessoaFisica(fichaCadastralVO);
		} else {
			relatorioFichaCadastral = 
				new RelatorioFichaCadastralPessoaJuridica(fichaCadastralVO);
		}
		return relatorioFichaCadastral;
	}
	
	/**
	 * Verifica se eh pessoa fisica.
	 *
	 * @param pessoaCompartilhamento o valor de pessoa compartilhamento
	 * @return {@code true}, se for pessoa fisica
	 */
	private boolean isPessoaFisica(PessoaCompartilhamento pessoaCompartilhamento){
		TipoPessoa tipoPessoa = pessoaCompartilhamento.getPessoa().getTipoPessoa();
		Short codTipoPessoa = tipoPessoa.getCodTipoPessoa();
		
		return TipoPessoaEnum.isPessoaFisica(codTipoPessoa);
	}

	/**
	 * Criar lista bens.
	 *
	 * @param vo o valor de vo
	 * @param bens o valor de bens
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private List<FichaCadastralBemVO> criarListaBens(
			CamposFichaCadastralVO vo, List<BemBase> bens)
			throws BancoobException {
		Map<Long, List<BemBase>> mapBens = criarMapaBensPorIdVigente(bens);
		return criarListaBensPorIdVigente(vo, mapBens);
	}

	/**
	 * Criar lista bens por id vigente.
	 *
	 * @param vo o valor de vo
	 * @param mapBens o valor de map bens
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private List<FichaCadastralBemVO> criarListaBensPorIdVigente(
			CamposFichaCadastralVO vo, Map<Long, List<BemBase>> mapBens)
			throws BancoobException {
		List<FichaCadastralBemVO> bensImoveisDTO = new LinkedList<FichaCadastralBemVO>();  
		
		for(Map.Entry<Long, List<BemBase>> entry : mapBens.entrySet()) {
			Bem bem = new Bem();
			bem.setIdBem(entry.getKey());
			
			FichaCadastralBemVO dto = new FichaCadastralBemVO();
			
			List<BemBase> lista = entry.getValue();
			dto.setBens(lista);
			dto.setBensOnus(listarDependenciaDoBem(BemOnus.class, bem, vo));
			dto.setBensRegistro(listarDependenciaDoBem(BemRegistro.class, bem, vo));
			dto.setBensPosse(listarDependenciaDoBem(BemPosse.class, bem, vo));
			TipoBem tipoBem = obterTipoBem(lista);
			dto.setTipoBem(tipoBem);
			
			bensImoveisDTO.add(dto);
		}
		
		Collections.sort(bensImoveisDTO);
		
		return bensImoveisDTO;
	}
	
	/**
	 * Obter tipo bem.
	 *
	 * @param lista o valor de lista
	 * @return TipoBem
	 */
	private TipoBem obterTipoBem(List<BemBase> lista) {
		BemBase bemPessoa = lista.get(0);
		TipoBem tipoBem = null;
		
		if (bemPessoa != null) {
			tipoBem = bemPessoa.getSubTipo().getTipoBem();
		}
		
		return  tipoBem;
	}

	/**
	 * Criar mapa bens por id vigente.
	 *
	 * @param bensImoveis o valor de bens imoveis
	 * @return Map
	 */
	private Map<Long, List<BemBase>> criarMapaBensPorIdVigente(
			List<BemBase> bensImoveis) {
		Map<Long, List<BemBase>> mapBens = new HashMap<Long, List<BemBase>>();

		for (BemBase bem : bensImoveis) {

			Long id = null;

			if (bem instanceof Bem) {
				id = ((Bem) bem).getId();
			} else if (bem instanceof HistoricoBem) {
				id = ((HistoricoBem) bem).getIdEntidadeVigente().longValue();
			}

			List<BemBase> bens = mapBens.get(id);

			if (bens == null) {
				bens = new LinkedList<BemBase>();
			}

			bens.add(bem);
			mapBens.put(id, bens);
		}
		return mapBens;
	}
	
	/**
	 * Listar pessoa compartilhamento.
	 *
	 * @param vo o valor de vo
	 * @param pessoaCompartilhamento o valor de pessoa compartilhamento
	 * @return List
	 */
	private List<PessoaCompartilhamento> listarPessoaCompartilhamento(CamposFichaCadastralVO vo,
			PessoaCompartilhamento pessoaCompartilhamento) {
		List<PessoaCompartilhamento> pessoasCompartilhamento = new LinkedList<PessoaCompartilhamento>();
		
		TipoConsultaEnum tipoConsulta = obterTipoConsultaEnum(vo);
		if (tipoConsulta != TipoConsultaEnum.PERIODO				
				|| (tipoConsulta == TipoConsultaEnum.PERIODO
					&& vo.getData().compareTo(pessoaCompartilhamento.getDataInclusaoSistema()) >= 0)) {
			pessoasCompartilhamento.add(pessoaCompartilhamento);
		} 
		
		if (tipoConsulta == TipoConsultaEnum.TUDO 
				|| (tipoConsulta == TipoConsultaEnum.PERIODO
					&& vo.getData().compareTo(pessoaCompartilhamento.getDataInclusaoSistema()) >= 0)) {
			ConsultaDtoCUC<PessoaCompartilhamento> dto = new ConsultaDtoCUC<PessoaCompartilhamento>();
			dto.setFiltro(pessoaCompartilhamento); 
			copiarPropriedades(vo, dto);
			dto.setNomeConsulta(CONSULTA_HISTORICO_PESSOA);
			
			FichaCadastralDelegate fichaCadastralDelegate = criarFichaCadastralDelegate();
			pessoasCompartilhamento.addAll(fichaCadastralDelegate.listar(PessoaCompartilhamento.class, dto));
		}

		return pessoasCompartilhamento;
	}
	
	/**
	 * Listar bens imoveis.
	 *
	 * @param vo o valor de vo
	 * @param pessoaCompartilhamento o valor de pessoa compartilhamento
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<?> listarBensImoveis(CamposFichaCadastralVO vo,
			PessoaCompartilhamento pessoaCompartilhamento) throws BancoobException {

		BemImovel bem = new BemImovel();
		bem.setPessoaCompartilhamento(pessoaCompartilhamento);
		
		ConsultaDtoCUC<BemImovel> dto = new ConsultaDtoCUC<BemImovel>();
		dto.setFiltro(bem);
		copiarPropriedades(vo, dto);
		
		dto.setNomeConsulta(BEM_POR_PESSOA);
		dto.setProcurarPor("POR BEM IMOVEL");
		
		FichaCadastralDelegate fichaCadastralDelegate = criarFichaCadastralDelegate();
		List<BemImovel> bens = fichaCadastralDelegate.listar(BemImovel.class, dto);
		
		if (dto.getTipoConsulta() == TipoConsultaEnum.TUDO 
				|| dto.getTipoConsulta() == TipoConsultaEnum.PERIODO) {
		
			ConsultaDtoCUC<BemImovel> dtoHistorico = new ConsultaDtoCUC<BemImovel>();
			dtoHistorico.setFiltro(bem);
			copiarPropriedades(vo, dtoHistorico);
			
			dtoHistorico.setNomeConsulta(PESQUISA_HISTORICO_BEM_IMOVEL_POR_PESSOA);
			
			bens.addAll(fichaCadastralDelegate.listar(BemImovel.class, dtoHistorico));
			Collections.sort(bens, new EntidadeCadastroBaseComparator());
		}
		
		buscarLocalidades(bens);
		
		return bens;
	}

	/**
	 * O método Buscar localidades.
	 *
	 * @param bens o valor de bens
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void buscarLocalidades(List<?> bens) throws BancoobException {
		
		Localidade localidade = null;
		
		for (Object bemImovel: bens) {
			
			if (bemImovel instanceof HistoricoBemImovel) {
				
				HistoricoBemImovel historicoBemImovel = (HistoricoBemImovel) bemImovel;
				
				if (historicoBemImovel.getIdLocalidadeComarca() !=  null) {
					localidade = obterLocalidade(historicoBemImovel.getIdLocalidadeComarca());
					if (localidade != null) {
						historicoBemImovel.setComarca(localidade.getNome());
						historicoBemImovel.setSiglaUFComarca(localidade.getUf().getSiglaUF());
					}
				}
				
				if (historicoBemImovel.getIdLocalidadeImovel() !=  null) {
					localidade = obterLocalidade(historicoBemImovel.getIdLocalidadeImovel());
					if (localidade != null) {
						historicoBemImovel.setMunicipio(localidade.getNome());
						historicoBemImovel.setSiglaUFMunicipio(localidade.getUf().getSiglaUF());
					}
				}
			} else if (bemImovel instanceof BemImovel) {
				
				BemImovel bemImovelVigente  = (BemImovel) bemImovel;
				
				if (bemImovelVigente.getIdLocalidadeComarca() !=  null) {
					localidade = obterLocalidade(bemImovelVigente.getIdLocalidadeComarca());
					if (localidade != null) {
						bemImovelVigente.setComarca(localidade.getNome());
						bemImovelVigente.setSiglaUFComarca(localidade.getUf().getSiglaUF());
					}
				}
				
				if (bemImovelVigente.getIdLocalidadeImovel() !=  null) {
					localidade = obterLocalidade(bemImovelVigente.getIdLocalidadeImovel());
					if (localidade != null) {
						bemImovelVigente.setMunicipio(localidade.getNome());
						bemImovelVigente.setSiglaUFMunicipio(localidade.getUf().getSiglaUF());
					}
				}
			}
		}
	}

	/**
	 * Obter localidade.
	 *
	 * @param idLocalidade o valor de id localidade
	 * @return Localidade
	 * @throws BancoobException lança a exceção BancoobException
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
	 * Listar bens outros.
	 *
	 * @param vo o valor de vo
	 * @param pessoaCompartilhamento o valor de pessoa compartilhamento
	 * @return List
	 */
	private List<?> listarBensOutros(CamposFichaCadastralVO vo,
			PessoaCompartilhamento pessoaCompartilhamento) {

		Bem bem = new Bem();
		bem.setPessoaCompartilhamento(pessoaCompartilhamento);
		
		ConsultaDtoCUC<Bem> dto = new ConsultaDtoCUC<Bem>();
		dto.setFiltro(bem);
		dto.setNomeConsulta(BEM_DIFERENTE_DE_IMOVEL_POR_PESSOA);
		copiarPropriedades(vo, dto);
		
		FichaCadastralDelegate fichaCadastralDelegate = criarFichaCadastralDelegate();
		return fichaCadastralDelegate.listar(Bem.class, dto);
	}

	/**
	 * Listar tributacoes.
	 *
	 * @param vo o valor de vo
	 * @param pessoaCompartilhamento o valor de pessoa compartilhamento
	 * @return List
	 */
	private List<Tributacao> listarTributacoes(CamposFichaCadastralVO vo,
			PessoaCompartilhamento pessoaCompartilhamento) {
		
		List<Tributacao> result = new ArrayList<Tributacao>();
		
		Tributacao tributacao = verificarConsultaEntidadeAutorizacaoJSON(pessoaCompartilhamento.getPessoa()
				.getId(), TipoAutorizacaoEntidadeEnum.TRIBUTACAO, obterTipoConsultaEnum(vo));
		if(isEntidadeEmAutorizacao(tributacao)){			
			result.add(tributacao);
			
		}else{
			
			FichaCadastralDelegate delegate = criarFichaCadastralDelegate();
			
			ConsultaDtoCUC<Tributacao> consultaDto = new ConsultaDtoCUC<Tributacao>();
			consultaDto.setNomeConsulta(TRIBUTACAO_POR_PESSOA);
			
			Tributacao filtro = new Tributacao();
			copiarPropriedades(vo, consultaDto);
			
			filtro.setPessoaCompartilhamento(pessoaCompartilhamento);
			consultaDto.setFiltro(filtro);
			result.addAll(delegate.listar(Tributacao.class, consultaDto));
		}
		
		return result;
	}
	
	/**
	 * Listar pessoas instituicoes.
	 *
	 * @param vo o valor de vo
	 * @param pessoaCompartilhamento o valor de pessoa compartilhamento
	 * @return List
	 */
	private List<PessoaInstituicao> listarPessoasInstituicoes(CamposFichaCadastralVO vo,
			PessoaCompartilhamento pessoaCompartilhamento) {

		PessoaInstituicao pessoaInstituicao = new PessoaInstituicao();
		pessoaInstituicao.setPessoa(pessoaCompartilhamento.getPessoa());
		pessoaInstituicao.setIdInstituicao(obterInstituicaoUsuarioRelatorio().getIdInstituicao());
		
		ConsultaDtoCUC<PessoaInstituicao> dto = new ConsultaDtoCUC<PessoaInstituicao>();
		dto.setFiltro(pessoaInstituicao);
		dto.setNomeConsulta(PESSOA_INSTITUICAO_POR_PESSOA);
		copiarPropriedades(vo, dto);
		
		FichaCadastralDelegate fichaCadastralDelegate = criarFichaCadastralDelegate();
		return fichaCadastralDelegate.listar(PessoaInstituicao.class, dto);
	}
	
	
	/**
	 * Listar referencias.
	 *
	 * @param vo o valor de vo
	 * @param pessoa o valor de pessoa
	 * @return the list<? extends referencia base>
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private List<? extends ReferenciaBase> listarReferencias(CamposFichaCadastralVO vo, PessoaCompartilhamento pessoa) throws BancoobException {
		
		TelefoneDelegate telefoneDelegate = CAPESCadastroFabricaDelegates
				.getInstance().criarTelefoneDelegate();
		ConsultaDtoCUC<Referencia> consultaDto = new ConsultaDtoCUC<Referencia>();
		consultaDto.setFiltro(pessoa);
		consultaDto.setNomeConsulta(REFERENCIA_POR_PESSOA);
		
		copiarPropriedades(vo, consultaDto);
		
		FichaCadastralDelegate fichaCadastralDelegate = criarFichaCadastralDelegate();
		
		List<? extends ReferenciaBase> referencias = fichaCadastralDelegate.listar(Referencia.class, consultaDto);	
		for(int i =0; i < referencias.size(); i++) {
			
			ReferenciaBase referencia = referencias.get(i);
			
			// busca o primeiro telefone da pessoa de referência para exibir na listagem
			if(referencia.getPessoaReferencia() != null){
				Telefone telefone = new Telefone();
				telefone.setPessoaCompartilhamento(referencia.getPessoaReferencia());

				ConsultaDto<Telefone> consulta = new ConsultaDto<Telefone>();
				consulta.setFiltro(telefone);
				List<Telefone> telefones = telefoneDelegate.listar(consulta);
				if(!telefones.isEmpty()){
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
	 * @param telefone o valor de telefone
	 * @return Short
	 */
	private Short obterDDD(Telefone telefone) {
		Short numDDD = null;
		String ddd = telefone.getDdd();
		if(StringUtils.isNotBlank(ddd) &&  StringUtils.isNumericSpace(ddd)) {
			numDDD = Short.valueOf(telefone.getDdd()); 
		}
		return numDDD;
	}

	/**
	 * Listar certidoes.
	 *
	 * @param vo o valor de vo
	 * @param pessoa o valor de pessoa
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private List<Certidao> listarCertidoes(CamposFichaCadastralVO vo, PessoaCompartilhamento pessoa) throws BancoobException {
		FichaCadastralDelegate delegate = criarFichaCadastralDelegate();

		ConsultaDtoCUC<Certidao> consultaDto = new ConsultaDtoCUC<Certidao>();
		consultaDto.setNomeConsulta(CERTIDAO_POR_PESSOA);
		
		Certidao filtro = new Certidao();
		copiarPropriedades(vo, consultaDto);

		filtro.setPessoa(pessoa);
		consultaDto.setFiltro(filtro);

		return delegate.listar(Certidao.class, consultaDto);
	}
	
	/**
	 * Listar produtores.
	 *
	 * @param vo o valor de vo
	 * @param pessoa o valor de pessoa
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private List<Produtor> listarProdutores(CamposFichaCadastralVO vo, PessoaCompartilhamento pessoa) throws BancoobException {
		
		List<Produtor> result = new ArrayList<Produtor>();;
		
		Produtor produtor = verificarConsultaEntidadeAutorizacaoJSON(pessoa.getPessoa()
				.getId(), TipoAutorizacaoEntidadeEnum.PRODUTOR,
				obterTipoConsultaEnum(vo));
		if(isEntidadeEmAutorizacao(produtor)){			
			result.add(produtor);
			
		}else{
			
			FichaCadastralDelegate delegate = criarFichaCadastralDelegate();
			
			ConsultaDtoCUC<Certidao> consultaDto = new ConsultaDtoCUC<Certidao>();
			consultaDto.setNomeConsulta(PRODUTOR_POR_PESSOA);
			
			Produtor filtro = new Produtor();
			copiarPropriedades(vo, consultaDto);
			
			filtro.setPessoaCompartilhamento(pessoa);
			consultaDto.setFiltro(filtro);
			result.addAll(delegate.listar(Produtor.class, consultaDto));
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
		FichaCadastralDelegate delegate = criarFichaCadastralDelegate();
		
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
		consultaDto.setTipoConsulta(vo.getHistoricoEm() != null && vo.getHistoricoEm().booleanValue() == true ? obterNovoTipoConsultaEnum(vo) : obterTipoConsultaEnum(vo));
		consultaDto.setData(new DateTimeDB(vo.getData().getTime()));
	}
	
	/**
	 * Obter tipo consulta enum.
	 *
	 * @param vo o valor de vo
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
	 * @param pessoa o valor de pessoa
	 * @param vo o valor de vo
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private List<Telefone> listarTelefones(PessoaCompartilhamento pessoa, CamposFichaCadastralVO vo)
			throws BancoobException {
		
		ConsultaDtoCUC<Telefone> criterios = new ConsultaDtoCUC<Telefone>();
		Telefone filtro = new Telefone();
		filtro.setPessoaCompartilhamento(pessoa);
		criterios.setFiltro(filtro);
		criterios.setNomeConsulta(TELEFONE_POR_PESSOA_COMPARTILHAMENTO);
		copiarPropriedades(vo, criterios);
		
		FichaCadastralDelegate fichaCadastralDelegate = criarFichaCadastralDelegate();
		
		return fichaCadastralDelegate.listar(Telefone.class, criterios);
	}

	/**
	 * Criar ficha cadastral delegate.
	 *
	 * @return FichaCadastralDelegate
	 */
	private FichaCadastralDelegate criarFichaCadastralDelegate() {
		return fabricaDelegates.criarFichaCadastralDelegate();
	}

	/**
	 * Obter pessoa instituicao.
	 *
	 * @param pessoa o valor de pessoa
	 * @return PessoaInstituicao
	 * @throws RegistroNaoEncontradoNegocioException lança a exceção RegistroNaoEncontradoNegocioException
	 */
	private PessoaInstituicao obterPessoaInstituicao(PessoaCompartilhamento pessoa)
			throws RegistroNaoEncontradoNegocioException {
		
		PessoaInstituicaoDelegate pessoaInstituicaoDelegate = fabricaDelegates.criarPessoaInstituicaoDelegate();
		
		PessoaInstituicao pessoaInstituicaoFiltro = new PessoaInstituicao();
		pessoaInstituicaoFiltro.setPessoa(pessoa.getPessoa());
		pessoaInstituicaoFiltro.setIdInstituicao(obterInstituicaoUsuarioRelatorio().getIdInstituicao());
		PessoaInstituicao pessoaInstituicao = null;

		try {
			pessoaInstituicao = 
					pessoaInstituicaoDelegate.obterPorPessoaInstituicao(pessoaInstituicaoFiltro);
		} catch (RegistroNaoEncontradoNegocioException e) {}

		return pessoaInstituicao;
	}
	
	/**
	 * Obter tributacao.
	 *
	 * @param tributacoes o valor de tributacoes
	 * @return Tributacao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private TributacaoBase obterTributacao(List<Tributacao> tributacoes) throws BancoobException {
		if(tributacoes != null
				&& tributacoes.size() == 1){
			return tributacoes.get(0);
		}
		return null;
	}

	/**
	 * Listar fonte renda.
	 *
	 * @param pessoa o valor de pessoa
	 * @param vo o valor de vo
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private List<FonteRenda> listarFonteRenda(PessoaCompartilhamento pessoa, CamposFichaCadastralVO vo)
			throws BancoobException {
		
		FonteRenda fonteRenda = new FonteRenda();
		fonteRenda.setPessoaCompartilhamento(pessoa);
		
		ConsultaDtoCUC<FonteRenda> criterios = new ConsultaDtoCUC<FonteRenda>();
		criterios.setFiltro(fonteRenda);
		criterios.setNomeConsulta(PESQUISA_FONTE_RENDA_POR_PESSOA_COMPARTILHAMENTO);
		copiarPropriedades(vo, criterios);
		
		FichaCadastralDelegate fichaCadastralDelegate = criarFichaCadastralDelegate();
		
		return fichaCadastralDelegate.listar(FonteRenda.class, criterios);
	}

	/**
	 * Listar enderecos.
	 *
	 * @param pessoa o valor de pessoa
	 * @param vo o valor de vo
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private List<EnderecoBase> listarEnderecos(PessoaCompartilhamento pessoa, CamposFichaCadastralVO vo)
			throws BancoobException {

		final Map<Integer, String> tiposLogradouro = criarMapaTipoLogradouro();
		Endereco filtro = new Endereco();
		filtro.setPessoaCompartilhamento(pessoa);

		ConsultaDtoCUC<Endereco> criterios = new ConsultaDtoCUC<Endereco>();
		criterios.setFiltro(filtro);
		criterios.setNomeConsulta(ENDERECO_POR_PESSOA_COMPARTILHAMENTO);
		copiarPropriedades(vo, criterios);
		
		FichaCadastralDelegate fichaCadastralDelegate = criarFichaCadastralDelegate();

		List<EnderecoBase> lista = new ArrayList<EnderecoBase>(fichaCadastralDelegate.listar(
				Endereco.class, criterios));

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
	 * @throws BancoobException lança a exceção BancoobException
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
	 * Listar dependencia do bem.
	 *
	 * @param <T> o tipo generico
	 * @param clazz o valor de clazz
	 * @param bem o valor de bem
	 * @param vo o valor de vo
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private <T> List<T> listarDependenciaDoBem(Class<T> clazz, Bem bem, CamposFichaCadastralVO vo)
			throws BancoobException {

		ConsultaDtoCUC<T> criterios = new ConsultaDtoCUC<T>();
		criterios.setFiltro(bem);
		criterios.setNomeConsulta(DEPENDENCIA_POR_BEM);
		copiarPropriedades(vo, criterios);
		
		FichaCadastralDelegate fichaCadastralDelegate = criarFichaCadastralDelegate();

		return fichaCadastralDelegate.listar(clazz, criterios);
	}

	/**
	 * Listar emails.
	 *
	 * @param pessoa o valor de pessoa
	 * @param vo o valor de vo
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private List<Email> listarEmails(PessoaCompartilhamento pessoa, CamposFichaCadastralVO vo) throws BancoobException {
		ConsultaDtoCUC<Email> criterios = new ConsultaDtoCUC<Email>();

		Email filtro = new Email();
		filtro.setPessoaCompartilhamento(pessoa);
		criterios.setFiltro(filtro);
		criterios.setNomeConsulta(EMAIL_POR_PESSOA_COMPARTILHAMENTO);
		copiarPropriedades(vo, criterios);
		
		FichaCadastralDelegate fichaCadastralDelegate  = criarFichaCadastralDelegate();

		return fichaCadastralDelegate.listar(Email.class,criterios);
	}

	/**
	 * Listar relacionamentos.
	 *
	 * @param pessoa o valor de pessoa
	 * @param vo o valor de vo
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private List<RelacionamentoPessoaBase> listarRelacionamentos(PessoaCompartilhamento pessoa, CamposFichaCadastralVO vo)
			throws BancoobException {

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
	
	private List<RelacionamentoPessoaBase> obterListaRelacionamentos(ConsultaDtoCUC<RelacionamentoPessoa> criterios)throws BancoobException {
		RelacionamentoPessoaDelegate delegate = fabricaDelegates.criarRelacionamentoPessoaDelegate();
		List<RelacionamentoPessoaBase> retorno = new ArrayList<RelacionamentoPessoaBase>();		
		if(criterios.getTipoConsulta().equals(TipoConsultaEnum.ATUAL)){
			retorno.addAll(daoRelacionamento.pesquisarRelacionamentosVigentesPorFiltro((RelacionamentoPessoa)criterios.getFiltro()));
		}else if(criterios.getTipoConsulta().equals(TipoConsultaEnum.PERIODO)){
			retorno.addAll(delegate.listarHistoricoEspecifico(criterios));
		}else if(criterios.getTipoConsulta().equals(TipoConsultaEnum.TUDO)){
			criterios.setData(null);
			retorno.addAll(delegate.pesquisarRelacionamentosVigentesPorFiltro((RelacionamentoPessoa)criterios.getFiltro()));
			retorno.addAll(delegate.listarHistoricoEspecifico(criterios));
		}
		return retorno;
	}
	
	private String criarNovaLabelFiltroData(CamposFichaCadastralVO vo) {
		TipoConsultaEnum tipoConsultaEnum = obterTipoConsultaEnum(vo);
		String labelFiltroData = null;
		if (tipoConsultaEnum != null) {
			labelFiltroData = obterLabelDataEmissaoFichaCadastral(vo.getData(),tipoConsultaEnum);
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

}
