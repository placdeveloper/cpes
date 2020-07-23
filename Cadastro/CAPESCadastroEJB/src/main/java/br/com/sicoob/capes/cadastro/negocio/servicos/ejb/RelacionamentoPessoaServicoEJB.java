/* 
inc * Sicoob
 * RelacionamentoPessoaServicoEJB.java 
 * Criado em: 24/08/2011
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.time.DateUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.CompartilhamentoCadastroDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.PessoaCompartilhamentoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.RelacionamentoPessoaDelegate;
import br.com.sicoob.capes.cadastro.negocio.dominio.validacao.ValidacaoAlteracaoRelacionamentoPessoa;
import br.com.sicoob.capes.cadastro.negocio.dominio.validacao.ValidacaoRelacionamentoPessoa;
import br.com.sicoob.capes.cadastro.negocio.dominio.validacao.ValidacaoRelacionamentoPessoaExclusao;
import br.com.sicoob.capes.cadastro.negocio.dto.ConsultaDtoCUC;
import br.com.sicoob.capes.cadastro.negocio.enums.ParametroEnum;
import br.com.sicoob.capes.cadastro.negocio.excecao.PendenciaPessoaRelacionadaException;
import br.com.sicoob.capes.cadastro.negocio.excecao.ProdutoNaoEncontradoException;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.AutorizacaoCadastroServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.GrupoEconomicoNovoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.ParametroServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.PessoaServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.RelacionamentoPessoaServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.RelacionamentoPessoaServicoRemote;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TransicaoPessoaServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.ValidacaoCadastralErroServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.ValidacaoCadastralRegraServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.vo.ParametroVO;
import br.com.sicoob.capes.cadastro.persistencia.dao.RelacionamentoPessoaDAO;
import br.com.sicoob.capes.comum.negocio.annotation.Autorizar;
import br.com.sicoob.capes.comum.negocio.annotation.IntegracaoGedGft;
import br.com.sicoob.capes.comum.negocio.contexto.InformacoesUsuarioCAPES;
import br.com.sicoob.capes.comum.negocio.enums.TipoOperacaoEnum;
import br.com.sicoob.capes.comum.negocio.vo.ProdutoInstituicaoVO;
import br.com.sicoob.capes.comum.util.ReflexaoUtils;
import br.com.sicoob.capes.integracao.negocio.delegates.ADMIntegracaoDelegate;
import br.com.sicoob.capes.integracao.negocio.delegates.CAPESIntegracaoFabricaDelegates;
import br.com.sicoob.capes.negocio.entidades.CompartilhamentoCadastro;
import br.com.sicoob.capes.negocio.entidades.Pessoa;
import br.com.sicoob.capes.negocio.entidades.RelacionamentoPessoaBase;
import br.com.sicoob.capes.negocio.entidades.TipoPoderRelacionamento;
import br.com.sicoob.capes.negocio.entidades.TipoRelacionamentoPessoa;
import br.com.sicoob.capes.negocio.entidades.TransicaoPessoa;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.RegistroRelacionamento;
import br.com.sicoob.capes.negocio.entidades.vigente.RelacionamentoPessoa;

/**
 * Serviço EJB para {@link RelacionamentoPessoa}
 * 
 * 24/08/2011
 * 
 * @author Rodrigo.Chaves
 */
@Stateless
@Local(RelacionamentoPessoaServicoLocal.class)
@Remote(RelacionamentoPessoaServicoRemote.class)
@IntegracaoGedGft
public class RelacionamentoPessoaServicoEJB extends
		EntidadeCadastroServicoEJB<RelacionamentoPessoa> implements
		RelacionamentoPessoaServicoRemote, RelacionamentoPessoaServicoLocal {
	
	/** A constante PODERES. */
	private static final String PODERES = "poderes";

	@Inject
	@Default
	private RelacionamentoPessoaDAO relacionamentoPessoaDAO;

	/** O atributo pessoaServico. */
	@EJB(mappedName = "capes/cadastro/PessoaServico")
	private PessoaServicoLocal pessoaServico;
	
	/** O atributo autorizacaoCadastroServico. */
	@EJB(mappedName = "capes/cadastro/AutorizacaoCadastroServico")
	private AutorizacaoCadastroServicoLocal autorizacaoCadastroServico;
	
	/** O atributo relacionamentoPessoaDelegate. */
	private RelacionamentoPessoaDelegate relacionamentoPessoaDelegate = 
			CAPESCadastroFabricaDelegates.getInstance().criarRelacionamentoPessoaDelegate();
	
	/** O atributo validacaoCadastralErroServicoLocal. */
	@EJB(mappedName = "capes/cadastro/ValidacaoCadastralErroServico")
	private ValidacaoCadastralErroServicoLocal validacaoCadastralErroServicoLocal;
	
	/** O atributo transicaoPessoaServico. */
	@EJB(mappedName = "capes/cadastro/TransicaoPessoaServico")
	private TransicaoPessoaServicoLocal transicaoPessoaServico;
	
	/** O atributo transicaoPessoaServico. */
	@EJB(mappedName = "capes/cadastro/ValidacaoCadastralRegraServico")
	private ValidacaoCadastralRegraServicoLocal validacaoCadastralRegraServico;
	
	@EJB
	private GrupoEconomicoNovoServicoLocal grupoEconomicoServico;
	
	@EJB
	private ParametroServicoLocal parametroServico;
	
	/** Objeto de acesso aos dados a PessoaCompartilhamentoDelegate . */
	private PessoaCompartilhamentoDelegate pessoaCompartilhamentoServico = CAPESCadastroFabricaDelegates.getInstance().criarPessoaCompartilhamentoDelegate();
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void excluirEntidade(RelacionamentoPessoa relacionamento) throws BancoobException {
		validarExcluir(relacionamento);
		if (relacionamento.getRelacionamentoReverso() != null && !isEntidadeVigente(relacionamento)) {
			relacionamento.setRelacionamentoReverso(null);
			getDAO().alterar(relacionamento);
		}
		
		excluirGrupoEconomico(relacionamento);
		excluir(relacionamento.getId());
		
		if(isRegistroVigente(relacionamento)){
			if(relacionamento.getIdUsuarioEnvio() != null){
				pessoaCompartilhamentoServico.renovarCadastroAutomatico(relacionamento, relacionamento.getPessoaCompartilhamento().getPessoa().getIdPessoa(), relacionamento.getIdUsuarioEnvio());
			}
		}
	}
	
	/**
	 * Verifica se eh entidade vigente.
	 *
	 * @param entidade o valor de entidade
	 * @return {@code true}, se for entidade vigente
	 */
	private boolean isEntidadeVigente(RelacionamentoPessoa entidade){
		return (entidade != null && entidade.getDataHoraInicio() != null && entidade.getIdInstituicaoAtualizacao() == null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void alterar(RelacionamentoPessoa relacionamento)
			throws BancoobException {

		validarAlterar(relacionamento);
//		RelacionamentoPessoa relacionamentoPersistente = obter(relacionamento.getId());
//		relacionamento.setPessoaRelacionada(pessoaServico.obter(relacionamento.getPessoaRelacionada().getId()));
//		ReflexaoUtils.copiarPropriedades(relacionamentoPersistente, relacionamento, "dataInicio", "id", "idRelacionamento", "pessoa", "tipoRelacionamento", PODERES);
//		copiarPoderes(relacionamentoPersistente, relacionamento);
		getDAO().alterar(relacionamento);
		
		RelacionamentoPessoa relacionamentoPessoa = obter(relacionamento.getId());
		if(isRegistroVigente(relacionamentoPessoa)){
			if(relacionamento.getIdUsuarioEnvio() != null){
				pessoaCompartilhamentoServico.renovarCadastroAutomatico(relacionamento, relacionamento.getPessoaCompartilhamento().getPessoa().getIdPessoa(), relacionamento.getIdUsuarioEnvio());
			}
			tratarGrupoEconomico(relacionamento);
		}
	}

//	/**
//	 * O método Copiar poderes.
//	 *
//	 * @param destino o valor de destino
//	 * @param origem o valor de origem
//	 * @throws BancoobException lança a exceção BancoobException
//	 */
//	private void copiarPoderes(RelacionamentoPessoa destino, RelacionamentoPessoa origem)
//			throws BancoobException {
//		if (destino.getPoderes() != null) {
//			getDAO().substituirColecoesPersistentes(destino, origem, PODERES);
//		} else {
//			destino.setPoderes(origem.getPoderes());
//		}
//	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void validarAlterar(final RelacionamentoPessoa relacionamento)
			throws BancoobException {
		new ValidacaoAlteracaoRelacionamentoPessoa().validar(relacionamento);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void validarIncluir(final RelacionamentoPessoa relacionamento)
			throws BancoobException {
		new ValidacaoRelacionamentoPessoa().validar(relacionamento);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void validarExcluir(RelacionamentoPessoa objeto) throws BancoobException {
		new ValidacaoRelacionamentoPessoaExclusao().validar(objeto);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RelacionamentoPessoa incluir(RelacionamentoPessoa relacionamento)
			throws BancoobException {
		return incluir(relacionamento, Boolean.FALSE);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Autorizar(atualizacao = TipoOperacaoEnum.I)
	public RelacionamentoPessoa incluir(RelacionamentoPessoa relacionamento, Boolean incluirReverso)
			throws BancoobException {
		validarIncluir(relacionamento);
		if(!isCompartilhamentoUtilizandoGedGft()) {
			tratarDataCCS(relacionamento);
		}
		relacionamento = getDAO().incluir(relacionamento);
		if(incluirReverso){
			gerarRelacionamentoReverso(relacionamento);
		}
		
		if (isEntidadeVigente(relacionamento)) {
			tratarGrupoEconomico(relacionamento);
		}
		
		return relacionamento;
	}
	
	/**
	 * O método Tratar data ccs.
	 *
	 * @param rel o valor de rel
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void tratarDataCCS(RelacionamentoPessoa rel) throws BancoobException {
		if (rel.getTipoRelacionamento().getHabilitaEnvioCCS()) {
			Date dataCCS = relacionamentoPessoaDelegate.obterDataMovimentoCCS(rel.getIdInstituicao());
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(dataCCS);
			rel.setDataInicioRelacionamento(new DateTimeDB(calendar.getTimeInMillis()));
		} else {
			rel.setDataInicioRelacionamento(new DateTimeDB());
		}
	}
	
	/**
	 * Verifica o {@link CompartilhamentoCadastro#getUtilizaGedGft()} para
	 * determinar se no compartilhamento do usuário logado utiliza-se GED/GFT
	 * 
	 * @return {@code true} caso o {@link CompartilhamentoCadastro} utilize
	 *         GED/GFT
	 */
	private boolean isCompartilhamentoUtilizandoGedGft() throws BancoobException {

		CompartilhamentoCadastroDelegate delegate =
				CAPESCadastroFabricaDelegates.getInstance()
						.criarCompartilhamentoCadastroDelegate();

		InformacoesUsuarioCAPES usuario = InformacoesUsuarioCAPES.getInstance();
		if (usuario == null) {
			throw new UnsupportedOperationException(
					"O InformacoesUsuarioCAPES não foi instanciado!");
		}
		Short codigo = usuario.getCodigoCompartilhamento();
		CompartilhamentoCadastro compartilhamento = delegate.obter(codigo);

		return compartilhamento.getUtilizaGedGft() != null && compartilhamento.getUtilizaGedGft();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<RelacionamentoPessoa> pesquisarRelacionamentosSemelhantes(
			Pessoa pessoa, Pessoa pessoaRelacionada,
			TipoRelacionamentoPessoa tipoRelacionamento, Integer idInstituicao) {

		return getDAO().pesquisarRelacionamentosSemelhantes(pessoa,
				pessoaRelacionada, tipoRelacionamento, idInstituicao);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void gerarRelacionamentoReverso(RelacionamentoPessoa relacionamento)
			throws BancoobException {
		
		if(relacionamento.getTipoRelacionamento().getRelacionamentoReverso() != null){
			TipoRelacionamentoPessoa tipoRelacionamentoReverso = relacionamento
					.getTipoRelacionamento().getRelacionamentoReverso();
			Pessoa pessoaRelacionada = pessoaServico.obter(relacionamento.getPessoaRelacionada()
					.getIdPessoa());
			relacionamento.setPessoaRelacionada(pessoaRelacionada);
			
			RelacionamentoPessoa reverso = instanciarRelacionamento(relacionamento);
			ReflexaoUtils.copiarPropriedades(reverso, relacionamento, "id",
					"idRelacionamento", "pessoa", "pessoaRelacionada", PODERES,
					"tipoRelacionamento");
			reverso.setPessoa(pessoaRelacionada);
			reverso.setPessoaCompartilhamento(pessoaRelacionada.getPessoaCompartilhamento());
			reverso.setPessoaRelacionada(relacionamento.getPessoa() != null ? relacionamento.getPessoa() : relacionamento.getPessoaCompartilhamento().getPessoa());//Ficando null
			reverso.setRelacionamentoReverso(relacionamento);
			
			if (relacionamento.getPoderes() != null) {
				reverso.setPoderes(new HashSet<TipoPoderRelacionamento>(relacionamento.getPoderes()));
			}
			reverso.setTipoRelacionamento(tipoRelacionamentoReverso);
			reverso = getDAO().incluir(reverso);
						
			getDAO().atualizarRelacionamentoReverso(relacionamento.getIdRelacionamento(),
					reverso.getIdRelacionamento());
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void alterarRelacionamentoReverso(RelacionamentoPessoa relacionamento) throws BancoobException {
		if (relacionamento.getTipoRelacionamento().getRelacionamentoReverso() != null) {
			RelacionamentoPessoa reverso = relacionamento.getRelacionamentoReverso();
			if (reverso != null) {
//				ReflexaoUtils.copiarPropriedades(reverso, relacionamento, "id", "idRelacionamento", 
//						"pessoa", "pessoaRelacionada", "tipoRelacionamento", "dataInicio", PODERES, 
//						"relacionamentoReverso");
//				reverso.setPessoa(relacionamento.getPessoaRelacionada());
//				reverso.setTipoRelacionamento(relacionamento.getTipoRelacionamento()
//						.getRelacionamentoReverso());
//				copiarPoderes(reverso, relacionamento);
				getDAO().alterar(reverso);
			}
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void excluirRelacionamentoReverso(RelacionamentoPessoa relacionamento) throws BancoobException{
		if (relacionamento.getTipoRelacionamento().getRelacionamentoReverso() != null && isEntidadeVigente(relacionamento)) {
			RelacionamentoPessoa reverso = relacionamento.getRelacionamentoReverso();
			if (reverso != null) {
				excluir(reverso.getId());
			}
		}
	}

	/**
	 * Instanciar relacionamento.
	 *
	 * @param relacionamento o valor de relacionamento
	 * @return RelacionamentoPessoa
	 */
	private RelacionamentoPessoa instanciarRelacionamento(RelacionamentoPessoa relacionamento) {
		
		RelacionamentoPessoa retorno;
		if (relacionamento instanceof RegistroRelacionamento) {
			retorno = new RegistroRelacionamento();
		} else {
			retorno = new RelacionamentoPessoa();
		}
		return retorno;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<RelacionamentoPessoa> pesquisarRelacionamentosCedidos(
			Integer idPessoa, Integer idInstituicao) throws BancoobException {

		RelacionamentoPessoa filtro = new RelacionamentoPessoa();
		filtro.setPessoa(new Pessoa());
		filtro.getPessoa().setIdPessoa(idPessoa);
		filtro.setIdInstituicao(idInstituicao);

		ConsultaDto<RelacionamentoPessoa> criterios = new ConsultaDto<RelacionamentoPessoa>();
		criterios.setFiltro(filtro);

		return pesquisar(criterios).getResultado();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ConsultaDto<RelacionamentoPessoa> pesquisarRelacionamentosExercidos(
			ConsultaDto<RelacionamentoPessoa> criterios) {

		return getDAO().pesquisarRelacionamentosExercidos(criterios);
	}

	/**
	 * @param relacionamento
	 *            o relacionamento do qual se deseja obter o reverso
	 * @return o relacionamento reverso ou <code>null</code>
	 * @see RelacionamentoPessoaDAO#obterRelacionamentoReverso(RelacionamentoPessoa)
	 */
	public RelacionamentoPessoa obterRelacionamentoReverso(
			RelacionamentoPessoa relacionamento) {
		return relacionamentoPessoaDAO
				.obterRelacionamentoReverso(relacionamento);
	}

	/**
	 * Obtém a data atual do movimento do produto CADASTRO DE CLIENTES DO SFN -
	 * CCS (ID 41)
	 * 
	 * @return {@link RetornoDTO} com a data atual do movimento
	 * @see #ID_PRODUTO_CCS
	 */
	@Override
	public Date obterDataMovimentoCCS(Integer idInstituicao) throws BancoobException {
		
		ADMIntegracaoDelegate admIntegracaoDelegate = CAPESIntegracaoFabricaDelegates.getInstance().criarADMIntegracaoDelegate();

		List<ProdutoInstituicaoVO> listaProdutoInstituicao = admIntegracaoDelegate.obterProdutosInstituicao();
		if ((listaProdutoInstituicao == null) || listaProdutoInstituicao.isEmpty()) {
			throw new ProdutoNaoEncontradoException("data do movimento", "CADASTRO DE CLIENTES DO SFN - CCS");
		}
		
		ProdutoInstituicaoVO produto = listaProdutoInstituicao.get(0);
		Date dataAtual = DateUtils.truncate(new Date(), Calendar.DATE);
		Date dataMovimento = DateUtils.truncate(produto.getDataAtualMovimento(), Calendar.DATE);
		if (dataAtual.compareTo(dataMovimento) > 0) {
			dataMovimento = produto.getDataProximoMovimento();
		}
		return new DateTimeDB(dataMovimento.getTime());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected RelacionamentoPessoaDAO getDAO() {
		return this.relacionamentoPessoaDAO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ConsultaDto<RelacionamentoPessoa> recuperarRelacionamentosIncorporacao(
			ConsultaDto<RelacionamentoPessoa> criterios)
			throws BancoobException {
		
		return getDAO().recuperarRelacionamentosIncorporacao(criterios);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public RelacionamentoPessoa obter(Serializable chave) throws BancoobException {
		RelacionamentoPessoa relacionamento = super.obter(chave);
		if (relacionamento != null) {
			relacionamento
					.setDocumentosComprobatorios(autorizacaoCadastroServico
							.obterDocumentosComprobatorios(relacionamento));
		}
		return relacionamento;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<RelacionamentoPessoa> pesquisarRelacionamentosVigentesPorFiltro(RelacionamentoPessoa relacionamento) throws BancoobException {
		return getDAO().pesquisarRelacionamentosVigentesPorFiltro(relacionamento);
	}

	@Override
	public void verificaPendenciasPessoaRelacionada(RelacionamentoPessoa relacionamento) throws BancoobException {
		if (relacionamento.getTipoRelacionamento().getHabilitaVerificacaoPendencia()) {
			List<String> listaPendencias = listarPendenciasPessoaRelacionada(relacionamento);
			if (listaPendencias != null && listaPendencias.size() > 0) {
				PessoaCompartilhamento pessoaRelacionada = CAPESCadastroFabricaDelegates.getInstance().criarPessoaCompartilhamentoDelegate().consultarPorIdPessoaInstituicao(relacionamento.getPessoaRelacionada().getIdPessoa(), obterInstituicaoUsuarioLogado().getIdInstituicao());
				validacaoCadastralRegraServico.revalidarCadastro(pessoaRelacionada.getIdPessoaCompartilhamento());
				listaPendencias = listarPendenciasPessoaRelacionada(relacionamento);
				if (listaPendencias != null && listaPendencias.size() > 0) {
					throw new PendenciaPessoaRelacionadaException(listaPendencias.toArray());
				}
			}
		}
	}
	
	private List<String> listarPendenciasPessoaRelacionada(RelacionamentoPessoa relacionamento) throws BancoobException {
		return validacaoCadastralErroServicoLocal
				.listarPendenciasPessoaRelacionada(
						relacionamento.getPessoaRelacionada().getPessoaCompartilhamento(),
						obterInstituicaoUsuarioLogado().getIdInstituicao());
	}
	
	@Override
	public Boolean validarTransicaoPessoaRelacionada(RelacionamentoPessoa relacionamento) throws BancoobException{
		TransicaoPessoa transicao = transicaoPessoaServico.obterTransicaoPorPessoaInstituicao(relacionamento.getPessoaRelacionada(), relacionamento.getIdInstituicao());
		if(transicao == null){
			return false;
		}
		return true;
	}

	@Override
	public List<RelacionamentoPessoaBase> listarHistoricoEspecifico(
			ConsultaDtoCUC<RelacionamentoPessoa> criterios) throws BancoobException {
		return getDAO().listarHistoricoEspecifico(criterios);
	}
	
	/**
	 * 
	 * @param relacionamento
	 * @throws BancoobException
	 */
	private void tratarGrupoEconomico(RelacionamentoPessoa relacionamento) throws BancoobException {
		Integer idInstituicao = Integer.valueOf(InformacoesUsuarioCAPES.getInstance().getIdInstituicao());
		ParametroVO parametroVO = parametroServico.obterParametro(ParametroEnum.CRIACAO_GRUPO_ECONOMICO_AUTOMATICO.getCodigo(), idInstituicao);
		Boolean habilitado = BooleanUtils.toBoolean(parametroVO.getValor());
		if (habilitado) {
			relacionamento.setPessoaCompartilhamento(pessoaCompartilhamentoServico.obter(relacionamento.getPessoaCompartilhamento().getId()));
			relacionamento.setPessoaRelacionada(pessoaServico.obter(relacionamento.getPessoaRelacionada().getId()));
			grupoEconomicoServico.gravar(relacionamento);
		}
	}
	
	/**
	 * 
	 * @param relacionamento
	 * @throws BancoobException
	 */
	private void excluirGrupoEconomico(RelacionamentoPessoa relacionamento) throws BancoobException {
		Integer idInstituicao = Integer.valueOf(InformacoesUsuarioCAPES.getInstance().getIdInstituicao());
		ParametroVO parametroVO = parametroServico.obterParametro(ParametroEnum.CRIACAO_GRUPO_ECONOMICO_AUTOMATICO.getCodigo(), idInstituicao);
		Boolean habilitado = BooleanUtils.toBoolean(parametroVO.getValor());
		if (habilitado) {
			grupoEconomicoServico.excluir(relacionamento);
		}
	}
	
}