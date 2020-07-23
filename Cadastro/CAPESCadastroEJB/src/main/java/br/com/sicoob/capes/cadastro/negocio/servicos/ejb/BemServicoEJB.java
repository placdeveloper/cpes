package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.InformacaoUtilizadaDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.PessoaCompartilhamentoDelegate;
import br.com.sicoob.capes.cadastro.negocio.dominio.validacao.ValidacaoBem;
import br.com.sicoob.capes.cadastro.negocio.dominio.validacao.ValidacaoBemImovel;
import br.com.sicoob.capes.cadastro.negocio.dominio.validacao.ValidacaoBemMovel;
import br.com.sicoob.capes.cadastro.negocio.enums.ParametroEnum;
import br.com.sicoob.capes.cadastro.negocio.enums.TipoBemEnum;
import br.com.sicoob.capes.cadastro.negocio.enums.TipoRelacionamentoBemImovelEnum;
import br.com.sicoob.capes.cadastro.negocio.excecao.BemImovelProprietaroParticipanteProdutividadeException;
import br.com.sicoob.capes.cadastro.negocio.excecao.BemUtilizadoException;
import br.com.sicoob.capes.cadastro.negocio.excecao.CAPESCadastroNegocioException;
import br.com.sicoob.capes.cadastro.negocio.excecao.GrupoCompartilhamentoInexistenteException;
import br.com.sicoob.capes.cadastro.negocio.excecao.RegistroJaCadastradoException;
import br.com.sicoob.capes.cadastro.negocio.servicos.bemantigo.BemAntigoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.BemPessoaCompartilhamentoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.BemServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.BemServicoRemote;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.GrupoCompartilhamentoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.ParametroServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.ProdutividadeServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.ReplicacaoCadastroServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TransicaoPessoaServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.vo.ParametroVO;
import br.com.sicoob.capes.cadastro.negocio.vo.PosseBemVO;
import br.com.sicoob.capes.cadastro.negocio.vo.ProprietarioBemVO;
import br.com.sicoob.capes.cadastro.negocio.vo.ValoresBensVO;
import br.com.sicoob.capes.cadastro.persistencia.dao.BemDAO;
import br.com.sicoob.capes.comum.negocio.annotation.IgnorarAutorizar;
import br.com.sicoob.capes.comum.negocio.annotation.IntegracaoGedGft;
import br.com.sicoob.capes.comum.negocio.contexto.InformacoesUsuarioCAPES;
import br.com.sicoob.capes.comum.negocio.enums.TipoClassificacaoBemEnum;
import br.com.sicoob.capes.comum.negocio.enums.TipoInformacaoEnum;
import br.com.sicoob.capes.comum.negocio.enums.TipoLocalizacaoBemImovelEnum;
import br.com.sicoob.capes.comum.negocio.vo.LOCIntegracaoLocalidadeVO;
import br.com.sicoob.capes.comum.util.ReflexaoUtils;
import br.com.sicoob.capes.integracao.negocio.delegates.CAPESIntegracaoFabricaDelegates;
import br.com.sicoob.capes.integracao.negocio.delegates.LOCIntegracaoDelegate;
import br.com.sicoob.capes.negocio.entidades.CompartilhamentoCadastro;
import br.com.sicoob.capes.negocio.entidades.InformacaoUtilizada;
import br.com.sicoob.capes.negocio.entidades.bem.Bem;
import br.com.sicoob.capes.negocio.entidades.bem.BemImovel;
import br.com.sicoob.capes.negocio.entidades.bem.BemImovelAvaliacao;
import br.com.sicoob.capes.negocio.entidades.bem.BemImovelTipoRelacionamentoPessoa;
import br.com.sicoob.capes.negocio.entidades.bem.BemMovel;
import br.com.sicoob.capes.negocio.entidades.bem.BemMovelAvaliacao;
import br.com.sicoob.capes.negocio.entidades.bem.BemPessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.bem.TipoClassificacaoBem;
import br.com.sicoob.capes.negocio.entidades.bemantigo.BemOnus;
import br.com.sicoob.capes.negocio.entidades.bemantigo.BemPosse;
import br.com.sicoob.capes.negocio.entidades.bemantigo.BemRegistro;
import br.com.sicoob.capes.negocio.entidades.bemantigo.SituacaoBem;
import br.com.sicoob.capes.negocio.entidades.bemantigo.SubTipoBem;
import br.com.sicoob.capes.negocio.entidades.bemantigo.TipoBem;
import br.com.sicoob.capes.negocio.entidades.bemantigo.TipoPosseBem;
import br.com.sicoob.capes.negocio.entidades.pk.InformacaoUtilizadaPK;
import br.com.sicoob.capes.negocio.entidades.vigente.GrupoCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * Classe base para os serviços de {@code Bem}
 * 
 * @author bruno.carneiro
 */
@Stateless
@Local(BemServicoLocal.class)
@Remote(BemServicoRemote.class)
@IntegracaoGedGft
public class BemServicoEJB extends EntidadeCadastroServicoEJB<Bem> implements BemServicoLocal, BemServicoRemote {
	
	@Inject
	@Default
	private BemDAO bemDAO;

	@EJB
	private GrupoCompartilhamentoServicoLocal grupoCompartilhamentoServico;

	@EJB
	private BemPessoaCompartilhamentoServicoLocal bemPessoaCompartilhamentoServico;
	
	@EJB
	private BemAntigoServicoLocal bemAntigoServico;
	
	@EJB
	private ProdutividadeServicoLocal servicoProdutividade;
	
	@EJB
	private TransicaoPessoaServicoLocal servicoTransicao;
	
	@EJB
	private ReplicacaoCadastroServicoLocal servicoReplicacao;
	
	@EJB
	private ParametroServicoLocal parametroServico;
	
	/** Objeto de acesso aos dados a PessoaCompartilhamentoDelegate . */
	private PessoaCompartilhamentoDelegate pessoaCompartilhamentoServico = CAPESCadastroFabricaDelegates.getInstance().criarPessoaCompartilhamentoDelegate();

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected BemDAO getDAO() {
		return bemDAO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Bem incluir(Bem objeto) throws BancoobException {
		String idInstituicao = obterUsuario().getIdInstituicao();
		CompartilhamentoCadastro compartilhamentoCadastro = obterCompartilhamentoCadastro(Integer.valueOf(idInstituicao));
		objeto.setCompartilhamentoCadastro(compartilhamentoCadastro);
		objeto = super.incluir(objeto);
		
		if(isRegistroVigente(objeto)) {
			removerBensInternos(objeto);
			bemPessoaCompartilhamentoServico.desmarcarResponsaveisBem(objeto.getId());
			incluirBemAntigo(objeto);
		}
		
		return objeto;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void alterar(Bem objeto) throws BancoobException {
		super.alterar(objeto);
		
		Bem bem = obter(objeto.getId());
		if(isRegistroVigente(bem)) {
			removerBensInternos(objeto);
			bemPessoaCompartilhamentoServico.excluirCompartilhamentosMarcados(objeto);
			bemPessoaCompartilhamentoServico.desmarcarResponsaveisBem(objeto.getId());
			alterarBemAntigo(objeto);
			for(BemPessoaCompartilhamento bemPessoaCompartilhamento : bem.getProprietarios()){
				if(objeto.getIdUsuarioEnvio() != null){
					pessoaCompartilhamentoServico.renovarCadastroAutomatico(bemPessoaCompartilhamento.getBem(), bemPessoaCompartilhamento.getPessoaCompartilhamento().getPessoa().getIdPessoa(), objeto.getIdUsuarioEnvio());
				}
			}
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void validarIncluir(Bem objeto) throws BancoobException {
		validarInclusao(objeto);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void validarAlterar(Bem objeto) throws BancoobException {
		validarAlteracao(objeto);
		verificarInformacaoUtilizada(objeto);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void validarExcluir(Serializable chave) throws BancoobException {
		super.validarExcluir(chave);
		validarInformacaoUtilizada((Long) chave);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@IgnorarAutorizar
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public PosseBemVO obterPossesBem(Long idBem, TipoClassificacaoBem tipoClassificacaoBem) throws BancoobException {
		PosseBemVO retorno = new PosseBemVO();
		retorno.setProprietarios(obterProprietariosBem(idBem));

		if (TipoClassificacaoBemEnum.BEM_IMOVEL.getCodigo().equals(tipoClassificacaoBem.getCodigo())) {
			retorno.setParticipantes(getDAO().obterParticipantesBemImovel(idBem));
		}
		
		return retorno;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Bem> obterBensPorPessoaCompartilhamento(Long idPessoaCompartilhamento, Short tipoClassificacao)  throws BancoobException {
		return getDAO().obterBensPorPessoaCompartilhamento(idPessoaCompartilhamento, tipoClassificacao);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Bem> obterBensImoveisPorPessoaCompartilhamento(Long idPessoaCompartilhamento)  throws BancoobException {
		return getDAO().obterBensImoveisPorPessoaCompartilhamento(idPessoaCompartilhamento);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@IgnorarAutorizar
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<ProprietarioBemVO> obterProprietariosBem(Long idBem) throws BancoobException {
		return getDAO().obterProprietariosBem(idBem);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@IgnorarAutorizar
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ValoresBensVO obterValoresBensPessoa(Long idPessoaCompartilhamento) throws BancoobException {
		return getDAO().obterValoresBensPessoa(idPessoaCompartilhamento);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@IgnorarAutorizar
	public void adicionarBemSemPatrimonio(PessoaCompartilhamento pessoaCompartilhamento) throws BancoobException {
		Bem bem = obterBemSemPatrimonio();
		
		validarInclusaoBemInterno(bem.getId(), pessoaCompartilhamento.getId());

		BemPessoaCompartilhamento bemPessoaCompartilhamento = new BemPessoaCompartilhamento();
		bemPessoaCompartilhamento.setBem(bem);
		bemPessoaCompartilhamento.setPessoaCompartilhamento(pessoaCompartilhamento);
		bemPessoaCompartilhamento.setPercentualProprietario(BigDecimal.ZERO);
		bemPessoaCompartilhamentoServico.incluirSemAutorizacao(bemPessoaCompartilhamento);
		
		bemAntigoServico.criarRegistroSemPatrimonio(pessoaCompartilhamento.getId(), bem.getId());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@IgnorarAutorizar
	public void adicionarBemRecusouInformar(PessoaCompartilhamento pessoaCompartilhamento) throws BancoobException {
		Bem bem = obterBemRecusouInformar();

		validarInclusaoBemInterno(bem.getId(), pessoaCompartilhamento.getId());

		BemPessoaCompartilhamento bemPessoaCompartilhamento = new BemPessoaCompartilhamento();
		bemPessoaCompartilhamento.setBem(bem);
		bemPessoaCompartilhamento.setPessoaCompartilhamento(pessoaCompartilhamento);
		bemPessoaCompartilhamento.setPercentualProprietario(BigDecimal.ZERO);

		bemPessoaCompartilhamentoServico.incluirSemAutorizacao(bemPessoaCompartilhamento);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public TipoClassificacaoBemEnum obterTipoClassificacaoBem(Long idBem) throws BancoobException {
		Short codigoTipoClassificacaoBem = getDAO().obterTipoClassificacaoBem(idBem);
		return TipoClassificacaoBemEnum.obterPorCodigo(codigoTipoClassificacaoBem);
	}
	
	/**
	 * Obtém o bem interno 'SEM PATRIMÔNIO'.
	 * 
	 * @return
	 * @throws BancoobException
	 */
	private Bem obterBemSemPatrimonio() throws BancoobException {
		CompartilhamentoCadastro compartilhamentoCadastro = obterCompartilhamentoCadastro();
		Long idBem = getDAO().obterIdBemSemPatrimonio(compartilhamentoCadastro.getCodigo());
		Bem bem = new Bem();
		bem.setId(idBem);
		return bem;
	}

	/**
	 * Obtém o bem interno 'RECUSOU-SE A INFORMAR'.
	 * 
	 * @return
	 * @throws BancoobException
	 */
	private Bem obterBemRecusouInformar() throws BancoobException {
		CompartilhamentoCadastro compartilhamentoCadastro = obterCompartilhamentoCadastro();
		Long idBem = getDAO().obterIdBemRecusouSeInformar(compartilhamentoCadastro.getCodigo());
		Bem bem = new Bem();
		bem.setId(idBem);
		return bem;
	}

	/**
	 * Método para validação da inclusão do bem interno.
	 * 
	 * @throws BancoobException
	 */
	private void validarInclusaoBemInterno(Long idBem, Long idPessoaCompartilhamento) throws BancoobException {
		boolean possuiBemInterno = verificarExistenciaBemInterno(idBem, idPessoaCompartilhamento);

		boolean possuiBensCadastrados = bemPessoaCompartilhamentoServico.pessoaPossuiBensCadastrados(idPessoaCompartilhamento);
		if (possuiBensCadastrados) {
			throw new CAPESCadastroNegocioException(
					"Não é permitido cadastrar as opções Não Possui Patrimônio ou Recusou-se a Informar, com bem vinculado ao cadastro");
		}

		if (possuiBemInterno) {
			throw new RegistroJaCadastradoException();
		}
	}
	
	/**
	 * Obtém o compartilhamento do cadastro do usuário logado.
	 * 
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	private CompartilhamentoCadastro obterCompartilhamentoCadastro() throws BancoobException {
		String idInstituicao = obterUsuario().getIdInstituicao();
		GrupoCompartilhamento grupoCompartilhamento = grupoCompartilhamentoServico.obterPorInstituicao(Integer.valueOf(idInstituicao));

		if (grupoCompartilhamento == null) {
			throw new GrupoCompartilhamentoInexistenteException();
		}

		return grupoCompartilhamento.getCompartilhamentoCadastro();
	}

	/**
	 * Valida se a informação está sendo utilizada por outro sistema.
	 * 
	 * @param idBem
	 * @throws BancoobException
	 */
	private void validarInformacaoUtilizada(Long idBem) throws BancoobException {
		InformacaoUtilizadaDelegate informacaoUtilizadaDelegate = CAPESCadastroFabricaDelegates.getInstance().criarInformacaoUtilizadaDelegate();
		List<String> sistemasUtilizandoInformacao = informacaoUtilizadaDelegate.listarSistemasUsandoInformacao(TipoInformacaoEnum.BEM.getCodigo(), idBem);

		if (sistemasUtilizandoInformacao != null && sistemasUtilizandoInformacao.size() > 0) {
			throw new BemUtilizadoException(sistemasUtilizandoInformacao.toArray());
		}
	}
	
	/**
	 * Remove os bens internos dos proprietários do bem.
	 * 
	 * @param entidade
	 * @throws BancoobException
	 */
	private void removerBensInternos(Bem entidade) throws BancoobException {
		for (BemPessoaCompartilhamento bpc : entidade.getProprietarios()) {
			if (!bpc.getMarcadoExclusao()) {
				BemPessoaCompartilhamento bemPessoaInterno = bemPessoaCompartilhamentoServico.obterBemPessoaCompartilhamentoInternoIdPessoaCompartilhamento(bpc.getIdPessoaCompartilhamento());
				if (bemPessoaInterno != null) {
					bemPessoaCompartilhamentoServico.excluir(bemPessoaInterno.getIdBem(), bemPessoaInterno.getIdBemPessoaCompartilhamento(), true);
				}
			}
		}
	}
	
	/**
	 * Verifica se a informação está sendo utilizada por outro sistema.
	 * 
	 * @param objeto
	 * @throws BancoobException
	 */
	private void verificarInformacaoUtilizada(Bem objeto) throws BancoobException {
		getLogger().debug("Verificando se o bem esta sendo utilizado por outro sistema");
		// Verifica se os proprietários foram alterados
		if (verificarTrocaProprietarios(objeto)) {
			getLogger().debug("O bem teve os proprietarios alterados.");
			validarInformacaoUtilizada(objeto.getIdBem());
			// Caso não tenham sido alterados e a informação esteja sendo utilizada por outro sistema
			// Deixaremos as informações de AVALIAÇÃO serem alteradas.
		} else {
			InformacaoUtilizadaDelegate informacaoUtilizadaDelegate = CAPESCadastroFabricaDelegates.getInstance().criarInformacaoUtilizadaDelegate();
			List<String> sistemasUtilizandoInformacao = informacaoUtilizadaDelegate.listarSistemasUsandoInformacao(TipoInformacaoEnum.BEM.getCodigo(),
					objeto.getIdBem());
			
			if (CollectionUtils.isNotEmpty(sistemasUtilizandoInformacao)) {
				getLogger().debug("Sistemas utilizando a informacao: ", ReflexaoUtils.juntarInformacoes(sistemasUtilizandoInformacao.toArray(), ", "));
				Bem bem = obter(objeto.getId());

				ReflexaoUtils.copiarPropriedadesIgnorandoDiferencas(objeto, bem, "proprietarios", "idInstituicaoAtualizacao", "idRegistroControlado",
						"valorCompraVenda", "dataCompraVenda", "valorAvaliacao", "dataAvaliacao", "processoAquisicao", "idPessoaCompartilhamentoAvaliador",
						"tiposOnus", "benfeitoria", "valorBenfeitoria", "tipoImplantacaoBemImovel", "tipoEstadoConservacao", "tipoPadraoAcabamentoBemImovel",
						"tipoServicoCondominialBemImovel", "areaPrivativa", "areaTerreno", "areaTestada", "quantidadeDormitorios", "quantidadeVagasGaragem");
			}
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void desbloquearBem(Long idBem) throws BancoobException {
		getDAO().inserirHistoricoDesbloqueioBem(idBem, obterUsuario().getLogin());
		
		//TODO: Bruno.Carneiro trocar para um query delete?
		// Cria o filtro para a pesquisa dos sistemas que bloquearam o registro
		ConsultaDto<InformacaoUtilizada> criterios = new ConsultaDto<InformacaoUtilizada>();
		InformacaoUtilizadaPK pk = new InformacaoUtilizadaPK();
		pk.setCodigoInformacao(idBem);
		pk.setCodigoTipoInformacao(TipoInformacaoEnum.BEM.getCodigo());
		InformacaoUtilizada informacaoUtilizada = new InformacaoUtilizada();
		informacaoUtilizada.setPk(pk);
		criterios.setFiltro(informacaoUtilizada);

		// Obtém a informação dos sistemas utilizando o bem.
		InformacaoUtilizadaDelegate informacaoUtilizadaDelegate = CAPESCadastroFabricaDelegates.getInstance().criarInformacaoUtilizadaDelegate();
		List<InformacaoUtilizada> lista = informacaoUtilizadaDelegate.listar(criterios);

		// Exclui cada um deles.
		if (lista != null && lista.size() > 0) {
			for (InformacaoUtilizada info : lista) {
				informacaoUtilizadaDelegate.excluirEntidade(info);
			}
		}
	}

	/**
	 * Verifica se os proprietários foram substituídos.
	 * 
	 * @param objeto
	 * @throws BancoobException
	 */
	private boolean verificarTrocaProprietarios(Bem objeto) throws BancoobException {
		getLogger().debug("Verificando a alteracao dos proprietarios do bem: ", String.valueOf(objeto.getId()));
		Bem bem = obter(objeto.getId());

		boolean mesmaColecao = true;

		List<Long> listaMarcadosExclusao = obterListaPessoasMarcadasExclusao(objeto);
		if (CollectionUtils.isNotEmpty(listaMarcadosExclusao)) {
			getLogger().debug("Existem pessoas marcadas para exclusao");
			mesmaColecao = false;
		} else {
			mesmaColecao = ReflexaoUtils.isMesmaColecao(bem.getProprietarios(), objeto.getProprietarios());
		}

		getLogger().debug("Houve troca de proprietarios: ", String.valueOf(!mesmaColecao));

		return !mesmaColecao;
	}

	/**
	 * Recupera as pessoas que estão marcadas para a exclusão.
	 * 
	 * @param objeto
	 * @return
	 */
	private List<Long> obterListaPessoasMarcadasExclusao(Bem objeto) {
		List<Long> lista = new ArrayList<Long>();
		for (BemPessoaCompartilhamento bpc : objeto.getProprietarios()) {
			if (bpc.getMarcadoExclusao() != null && bpc.getMarcadoExclusao()) {
				lista.add(bpc.getIdPessoaCompartilhamento());
			}
		}
		return lista;
	}

	/**
	 * Obtém o grupo de compartilhamento do cadastro a partir da instituição do
	 * usuário logado.
	 * 
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	private CompartilhamentoCadastro obterCompartilhamentoCadastro(Integer idInstituicao) throws BancoobException {
		GrupoCompartilhamento grupoCompartilhamento = grupoCompartilhamentoServico.obterPorInstituicao(idInstituicao);

		if (grupoCompartilhamento == null) {
			throw new GrupoCompartilhamentoInexistenteException();
		}
		return grupoCompartilhamento.getCompartilhamentoCadastro();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<BemImovelTipoRelacionamentoPessoa> obterRelacionamentosBemImovel(Long idBem) throws BancoobException {
		return getDAO().obterRelacionamentosBemImovel(idBem);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void validarInclusao(Bem bem) throws BancoobException {
		ValidacaoBem validacao = null;
		if (bem instanceof BemImovel) {
			validacao = new ValidacaoBemImovel();
		} else if (bem instanceof BemMovel) {
			validacao = new ValidacaoBemMovel();
		}
		if (validacao != null) {
			validacao.validar(bem);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void validarAlteracao(Bem bem) throws BancoobException {
		ValidacaoBem validacao = null;
		if (bem instanceof BemImovel) {
			validacao = new ValidacaoBemImovel(true);
		} else if (bem instanceof BemMovel) {
			validacao = new ValidacaoBemMovel(true);
		}
		if (validacao != null) {
			validacao.validar(bem);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<BemImovel> obterImoveisRurais(Long idPessoaCompartilhamento) throws BancoobException {
		return getDAO().obterImoveisRurais(idPessoaCompartilhamento);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean verificarExistenciaBemInterno(Long idBem, Long idPessoaCompartilhamento) throws BancoobException {
		return getDAO().verificarExistenciaBemInterno(idBem, idPessoaCompartilhamento);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean verificarBemEmGarantia(Long idBem) throws BancoobException {
		return getDAO().verificarBemEmGarantia(idBem);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void verificarCompartilhamentoAssociados(Long idBem) throws BancoobException {
		Bem bem = obter(idBem);

		if (bem != null) {
			if (bem.getProprietarios() != null && bem.getProprietarios().size() > 0) {
				for (BemPessoaCompartilhamento bpc : bem.getProprietarios()) {
					if (!existeTransicao(bpc.getIdPessoaCompartilhamento())) {
						servicoReplicacao.iniciarRelacionamentoInstituicao(bpc.getPessoaCompartilhamento());
					}
				}
			}

			if (bem instanceof BemImovel) {
				BemImovel bemImovel = (BemImovel) bem;

				Long idPessoaCompartilhamentoCartorio = bemImovel.getIdPessoaCompartilhamentoCartorio();
				if (idPessoaCompartilhamentoCartorio != null) {
					if (!existeTransicao(idPessoaCompartilhamentoCartorio)) {
						servicoReplicacao.iniciarRelacionamentoInstituicao(idPessoaCompartilhamentoCartorio);
					}
				}

				List<BemImovelTipoRelacionamentoPessoa> participantes = obterRelacionamentosBemImovel(idBem);
				if (participantes != null && participantes.size() > 0) {
					for (BemImovelTipoRelacionamentoPessoa bitrp : participantes) {
						if (!existeTransicao(bitrp.getIdPessoaCompartilhamento())) {
							servicoReplicacao.iniciarRelacionamentoInstituicao(bitrp.getIdPessoaCompartilhamento());
						}
					}
				}
			}

			if (bem instanceof BemMovelAvaliacao) {
				BemMovelAvaliacao bemMovelaAvaliacao = (BemMovelAvaliacao) bem;
				Long idPessoaCompartilhamentoAvaliador = bemMovelaAvaliacao.getIdPessoaCompartilhamentoAvaliador();
				if (idPessoaCompartilhamentoAvaliador != null) {
					if (!existeTransicao(idPessoaCompartilhamentoAvaliador)) {
						servicoReplicacao.iniciarRelacionamentoInstituicao(idPessoaCompartilhamentoAvaliador);
					}
				}
			}

			if (bem instanceof BemImovelAvaliacao) {
				BemImovelAvaliacao bemImovelAvaliacao = (BemImovelAvaliacao) bem;
				Long idPessoaCompartilhamentoAvaliador = bemImovelAvaliacao.getIdPessoaCompartilhamentoAvaliador();
				if (idPessoaCompartilhamentoAvaliador != null) {
					if (!existeTransicao(idPessoaCompartilhamentoAvaliador)) {
						servicoReplicacao.iniciarRelacionamentoInstituicao(idPessoaCompartilhamentoAvaliador);
					}
				}
			}
		}
	}
	
	/**
	 * Verifica se a pessoa possui transição na cooperativa.
	 * 
	 * @param idPessoaCompartilhamento
	 * @return
	 * @throws BancoobException
	 */
	private boolean existeTransicao(Long idPessoaCompartilhamento) throws BancoobException {
		return servicoTransicao.obterTransicaoPorPessoaCompartilhamentoInstituicao(idPessoaCompartilhamento, obterInstituicaoUsuarioLogado().getIdInstituicao()) != null ? true	: false;
	}
	
	// FIXME bruno.carneiro: remover após adaptação dos produtos.
	
	/**
	 * Realiza a inclusão dos registros antigos de bem.
	 * 
	 * @param bem
	 * @throws BancoobException
	 */
	private void incluirBemAntigo(Bem bem) throws BancoobException {
		if(!verificaProcessoAquisicao(bem)) {
			List<br.com.sicoob.capes.negocio.entidades.bemantigo.Bem> listaInclusaoBensAntigos = obterListaInclusaoBensAntigos(bem);
			incluirBensAntigos(listaInclusaoBensAntigos);
		}
	}
	
	/**
	 * Realiza a alteração dos registros antigos de bem.
	 * 
	 * @param objeto
	 * @throws BancoobException
	 */
	private void alterarBemAntigo(Bem objeto) throws BancoobException {
		if(!verificaProcessoAquisicao(objeto)) {
			Bem bem = obter(objeto.getId());
			
			// obtém a lista dos bens antigos à partir do identificador do bem novo.
			List<br.com.sicoob.capes.negocio.entidades.bemantigo.Bem> listaAlteracao = obterListaAlteracaoBensAntigos(bem);
			List<br.com.sicoob.capes.negocio.entidades.bemantigo.Bem> listaInclusao = obterListaInclusaoBensAntigos(bem);
			List<br.com.sicoob.capes.negocio.entidades.bemantigo.Bem> listaRemover = obterListaExclusaoBensAntigos(bem);
			
			List<br.com.sicoob.capes.negocio.entidades.bemantigo.Bem> listaAlterar = (List<br.com.sicoob.capes.negocio.entidades.bemantigo.Bem>) 
					ReflexaoUtils.intersecaoColecao(listaAlteracao, listaInclusao, "idPessoaCompartilhamento");
			List<br.com.sicoob.capes.negocio.entidades.bemantigo.Bem> listaIncluir = (List<br.com.sicoob.capes.negocio.entidades.bemantigo.Bem>) 
					ReflexaoUtils.subtrairColecao(listaInclusao, listaAlteracao, "idPessoaCompartilhamento");
//			List<br.com.sicoob.capes.negocio.entidades.bemantigo.Bem> listaRemover = (List<br.com.sicoob.capes.negocio.entidades.bemantigo.Bem>) 
//					ReflexaoUtils.subtrairColecao(listaAlteracao, listaInclusao, "idPessoaCompartilhamento");
			
			incluirBensAntigos(listaIncluir);
			alterarBensAntigos(listaAlterar);
			excluirBensAntigos(listaRemover);
		} else {
			List<br.com.sicoob.capes.negocio.entidades.bemantigo.Bem> listaRemover = obterListaExclusaoEmAquisicao(objeto);
			excluirBensAntigos(listaRemover);
		}
	}
	
	/**
	 * Verifica se o bem está em processo de aquisição.
	 * 
	 * @param bem
	 *            o bem a ser verificado
	 * @return {@code boolean} se está ou não em processo de aquisição
	 */
	private boolean verificaProcessoAquisicao(Bem bem) {
		Boolean processoAquisicao = Boolean.FALSE;

		if (ReflexaoUtils.objetoPossuiAtributo(bem, "processoAquisicao")) {
			processoAquisicao = (Boolean) ReflexaoUtils.getValorAtributo(bem, "processoAquisicao");
		}

		return processoAquisicao;
	}
	
	/**
	 * Inclui os bens antigos.
	 * 
	 * @param listaBensAntigos
	 * @throws BancoobException
	 */
	private void incluirBensAntigos(List<br.com.sicoob.capes.negocio.entidades.bemantigo.Bem> listaBensAntigos) throws BancoobException {
		if (listaBensAntigos != null && !listaBensAntigos.isEmpty()) {
			for (br.com.sicoob.capes.negocio.entidades.bemantigo.Bem bemAntigo : listaBensAntigos) {
				bemAntigoServico.incluir(bemAntigo);
			}
		}
	}

	/**
	 * Altera os bens antigos.
	 * 
	 * @param listaBensAntigos
	 * @throws BancoobException
	 */
	private void alterarBensAntigos(List<br.com.sicoob.capes.negocio.entidades.bemantigo.Bem> listaBensAntigos) throws BancoobException {
		if (listaBensAntigos != null && !listaBensAntigos.isEmpty()) {
			for (br.com.sicoob.capes.negocio.entidades.bemantigo.Bem bemAntigo : listaBensAntigos) {
				bemAntigoServico.alterar(bemAntigo);
			}
		}
	}

	/**
	 * Exclui os bens antigos.
	 * 
	 * @param listaBensAntigos
	 * @throws BancoobException
	 */
	private void excluirBensAntigos(List<br.com.sicoob.capes.negocio.entidades.bemantigo.Bem> listaBensAntigos) throws BancoobException {
		if (listaBensAntigos != null && !listaBensAntigos.isEmpty()) {
			for (br.com.sicoob.capes.negocio.entidades.bemantigo.Bem bemAntigo : listaBensAntigos) {
				validarExclusao(bemAntigo.getIdBem());
				bemAntigoServico.excluirEntidade(bemAntigo);
			}
		}
	}

	/**
	 * Obtém a lista dos bens antigos para inclusão
	 * 
	 * @param bem
	 * @return
	 * @throws BancoobException
	 */
	private List<br.com.sicoob.capes.negocio.entidades.bemantigo.Bem> obterListaInclusaoBensAntigos(Bem bem) throws BancoobException {
		List<br.com.sicoob.capes.negocio.entidades.bemantigo.Bem> retorno = new ArrayList<br.com.sicoob.capes.negocio.entidades.bemantigo.Bem>();
		for (BemPessoaCompartilhamento bpc : bem.getProprietarios()) {
			if (!bpc.getMarcadoExclusao()) {
				retorno.add(obterBem(null, bpc));
			}
		}
		return retorno;
	}
	
	/**
	 * Obtém a lista dos bens antigos para alteração
	 * 
	 * @param bem
	 * @return
	 * @throws BancoobException
	 */
	private List<br.com.sicoob.capes.negocio.entidades.bemantigo.Bem> obterListaAlteracaoBensAntigos(Bem bem) throws BancoobException {
		List<br.com.sicoob.capes.negocio.entidades.bemantigo.Bem> retorno = new ArrayList<br.com.sicoob.capes.negocio.entidades.bemantigo.Bem>();
		for (BemPessoaCompartilhamento bpc : bem.getProprietarios()) {
			if (!bpc.getMarcadoExclusao()) {
				List<br.com.sicoob.capes.negocio.entidades.bemantigo.Bem> listaBensAntigos = bemAntigoServico.obterPorIdBemNovo(bpc.getPessoaCompartilhamento(), bem.getId());
				for (br.com.sicoob.capes.negocio.entidades.bemantigo.Bem bemAntigo : listaBensAntigos) {
					bemAntigoServico.removerObjetoSessao(bemAntigo);
					retorno.add(obterBem(bemAntigo, bpc));
				}
			}
		}
		return retorno;
	}
	
	/**
	 * Obtém a lista dos bens a serem excluidos.
	 * 
	 * @param bem
	 * @return
	 * @throws BancoobException
	 */
	private List<br.com.sicoob.capes.negocio.entidades.bemantigo.Bem> obterListaExclusaoBensAntigos(Bem bem) throws BancoobException {
		List<br.com.sicoob.capes.negocio.entidades.bemantigo.Bem> retorno = new ArrayList<br.com.sicoob.capes.negocio.entidades.bemantigo.Bem>();
		for (BemPessoaCompartilhamento bpc : bem.getProprietarios()) {
			if (bpc.getMarcadoExclusao()) {
				List<br.com.sicoob.capes.negocio.entidades.bemantigo.Bem> listaBensAntigos = bemAntigoServico.obterPorIdBemNovo(bpc.getPessoaCompartilhamento(), bem.getId());
				retorno.addAll(listaBensAntigos);
			}
		}
		return retorno;
	}
	
	/**
	 * Obtém os bens para remoção por estar em aquisição.
	 * 
	 * @param bem
	 * @return
	 * @throws BancoobException
	 */
	private List<br.com.sicoob.capes.negocio.entidades.bemantigo.Bem> obterListaExclusaoEmAquisicao(Bem bem) throws BancoobException {
		List<br.com.sicoob.capes.negocio.entidades.bemantigo.Bem> retorno = new ArrayList<br.com.sicoob.capes.negocio.entidades.bemantigo.Bem>();
		List<br.com.sicoob.capes.negocio.entidades.bemantigo.Bem> listaBensAntigos = bemAntigoServico.obterPorIdBemNovo(null, bem.getId());
		for (BemPessoaCompartilhamento bpc : bem.getProprietarios()) {
			for (br.com.sicoob.capes.negocio.entidades.bemantigo.Bem bemAntigo : listaBensAntigos) {
				if (bpc.getIdPessoaCompartilhamento().equals(bemAntigo.getIdPessoaCompartilhamento())) {
					retorno.add(bemAntigo);
				}
			}
		}
		return retorno;
	}
	
	/**
	 * Obtém as informações do bem antigo.
	 * 
	 * @param bemAntigo
	 * @param bpc
	 * @return
	 * @throws BancoobException
	 */
	private br.com.sicoob.capes.negocio.entidades.bemantigo.Bem obterBem(br.com.sicoob.capes.negocio.entidades.bemantigo.Bem bemAntigo, BemPessoaCompartilhamento bpc) throws BancoobException {
		Bem bem = bpc.getBem();

		if (bemAntigo == null) {
			if (TipoClassificacaoBemEnum.BEM_IMOVEL.getCodigo().equals(bem.getTipoClassificacaoBem().getCodigo())) {
				bemAntigo = new br.com.sicoob.capes.negocio.entidades.bemantigo.BemImovel();
			} else {
				bemAntigo = new br.com.sicoob.capes.negocio.entidades.bemantigo.Bem();
			}

			bemAntigo.setIdPessoaCompartilhamento(bpc.getIdPessoaCompartilhamento());
			bemAntigo.setPessoaCompartilhamento(bpc.getPessoaCompartilhamento());
		}

		if (TipoClassificacaoBemEnum.BEM_IMOVEL.getCodigo().equals(bem.getTipoClassificacaoBem().getCodigo())) {
			br.com.sicoob.capes.negocio.entidades.bemantigo.BemImovel bemImovelAntigo = (br.com.sicoob.capes.negocio.entidades.bemantigo.BemImovel) bemAntigo;
			BemImovel bemImovel = (BemImovel) bem;
			preencherInformacoesImovel(bemImovelAntigo, bemImovel);
		} else {
			bemAntigo.setBensPosse(new ArrayList<BemPosse>());
			bemAntigo.setBensRegistro(new ArrayList<BemRegistro>());
		}

		bemAntigo.setIdBemNovo(bem.getId());
		bemAntigo.setDescricao(bem.getDescricao());
		bemAntigo.setPercentual(bpc.getPercentualProprietario());
		bemAntigo.setSubTipo(obterSubtipo(bem));
		bemAntigo.setValorAtualMercado(bem.getValor());
		bemAntigo.setIdUsuarioAprovacao(bem.getIdUsuarioAprovacao());
		bemAntigo.setVerificarAutorizacao(Boolean.FALSE);
		bemAntigo.setSituacao(obterSituacaoBem(bem));
		bemAntigo.setBensOnus(new ArrayList<BemOnus>());

		return bemAntigo;
	}

	/**
	 * Preenche as informações do bem imóvel.
	 * 
	 * @param bemImovelAntigo
	 * @param bemImovel
	 * @throws BancoobException
	 */
	private void preencherInformacoesImovel(br.com.sicoob.capes.negocio.entidades.bemantigo.BemImovel bemImovelAntigo, BemImovel bemImovel)	throws BancoobException {
		TipoLocalizacaoBemImovelEnum tipoLocalizacao = TipoLocalizacaoBemImovelEnum.obterPorCodigo(bemImovel.getTipoLocalizacaoBem().getCodigo());
		bemImovelAntigo.setUnidadeMedida(bemImovel.getUnidadeMedida());
		bemImovelAntigo.setArea(bemImovel.getAreaTotal());
		bemImovelAntigo.setDenominacao(bemImovel.getDenominacao());
		preencherInformacoesMunicipio(bemImovelAntigo, bemImovel.getIdLocalidade());
		bemImovelAntigo.setCodLocalizacao(TipoLocalizacaoBemImovelEnum.RURAL.equals(tipoLocalizacao) ? "R" : "U");
		bemImovelAntigo.setBensPosse(obterPosses(bemImovelAntigo, bemImovel));
		bemImovelAntigo.setBensRegistro(obterRegistros(bemImovelAntigo, bemImovel));
	}

	/**
	 * Preenche as informações de município
	 * 
	 * @param bemImovelAntigo
	 * @param idLogradouro
	 * @throws BancoobException
	 */
	private void preencherInformacoesMunicipio(br.com.sicoob.capes.negocio.entidades.bemantigo.BemImovel bemImovelAntigo, Integer idLocalidade) throws BancoobException {
		LOCIntegracaoDelegate delegate = CAPESIntegracaoFabricaDelegates.getInstance().criarLOCIntegracaoDelegate();
		LOCIntegracaoLocalidadeVO integracaoLocalidadeVO = delegate.obterLocalidade(idLocalidade);
		bemImovelAntigo.setIdLocalidadeImovel(idLocalidade);
		bemImovelAntigo.setMunicipio(integracaoLocalidadeVO.getNome());
		bemImovelAntigo.setSiglaUFMunicipio(integracaoLocalidadeVO.getUf().getSiglaUF());
	}
	
	/**
	 * Obtém as posses do bem antigo.
	 * 
	 * @param bemImovelAntigo
	 * @param bemImovel
	 * @return
	 * @throws BancoobException
	 */
	private List<BemPosse> obterPosses(br.com.sicoob.capes.negocio.entidades.bemantigo.BemImovel bemImovelAntigo, BemImovel bemImovel) throws BancoobException {
		List<BemPosse> posses = new ArrayList<BemPosse>();

		if(CollectionUtils.isNotEmpty(bemImovel.getRelacionamentoPessoas())){
			for (BemImovelTipoRelacionamentoPessoa rel : bemImovel.getRelacionamentoPessoas()) {
				BemPosse posse = new BemPosse();
				posse.setArea(rel.getAreaPosse());
				posse.setBem(bemImovelAntigo);
				posse.setTipoPosseBem(obterTipoPosse(rel.getTipoRelacionamento().getCodigo()));
				posse.setDataHoraInicio(new DateTimeDB());
				posse.setIdPessoaCompartilhamentoParceiro(rel.getIdPessoaCompartilhamento());
				posses.add(posse);
			}
		}

		return posses;
	}
	
	/**
	 * Obtém os registros do bem
	 * 
	 * @param bemImovelAntigo
	 * @param bemImovel
	 * @return
	 */
	private List<BemRegistro> obterRegistros(br.com.sicoob.capes.negocio.entidades.bemantigo.BemImovel bemImovelAntigo, BemImovel bemImovel) {
		List<BemRegistro> registros = new ArrayList<BemRegistro>();

		if (StringUtils.isNotBlank(bemImovel.getMatricula()) || StringUtils.isNotBlank(bemImovel.getIncra())) {
			BemRegistro bemRegistro = new BemRegistro();
			bemRegistro.setBem(bemImovelAntigo);
			bemRegistro.setMatriculaRegistro(bemImovel.getMatricula());
			bemRegistro.setIncra(bemImovel.getIncra());
			bemRegistro.setDataHoraInicio(new DateTimeDB());
			registros.add(bemRegistro);
		}

		return registros;
	}

	/**
	 * Faz o cálculo da área total.
	 * 
	 * @param areaTotal
	 * @param porcentagem
	 * @return
	 */
	public static BigDecimal calcularArea(BigDecimal areaTotal, BigDecimal porcentagem) {
		return areaTotal.multiply(porcentagem).divide(new BigDecimal(100));
	}
	
	/**
	 * Obtém o tipo de posse do bem antigo.
	 * 
	 * @param codigo
	 * @return
	 */
	private TipoPosseBem obterTipoPosse(Short codigo) {
		TipoPosseBem tipoPosse = new TipoPosseBem();
		tipoPosse.setCodigo(MAPA_TIPO_POSSE.get(codigo));
		return tipoPosse;
	}
	
	/**
	 * Obtém o subtipo do bem antigo.
	 * 
	 * @param bem
	 * @return
	 */
	private SubTipoBem obterSubtipo(Bem bem) {
		Short codigoTipoBem = null;
		if (bem instanceof BemImovel) {
			codigoTipoBem = ((BemImovel) bem).getTipoBem().getCodigo();
		} else {
			codigoTipoBem = ((BemMovel) bem).getTipoBem().getCodigo();
		}

		SubTipoBem subTipoBem = new SubTipoBem();
		subTipoBem.setCodigo(MAPA_SUBTIPO_BEM.get(codigoTipoBem));
		subTipoBem.setTipoBem(obterTipoBem(bem.getTipoClassificacaoBem().getCodigo()));

		return subTipoBem;
	}
	
	/**
	 * Obtém o tipo do bem antigo
	 * 
	 * @param codigoClassificacaoBem
	 * @return
	 */
	private TipoBem obterTipoBem(Short codigoClassificacaoBem) {
		TipoBem tipoBem = new TipoBem();
		tipoBem.setCodigo(MAPA_TIPO_BEM.get(codigoClassificacaoBem));
		return tipoBem;
	}
	
	/**
	 * Obtém a situação do bem antigo
	 * 
	 * @param bem
	 * @return
	 */
	private SituacaoBem obterSituacaoBem(Bem bem) {
		SituacaoBem situacaoBem = new SituacaoBem();
		situacaoBem.setCodigo(MAPA_SITUACAO_BEM.get((short) 1));
		return situacaoBem;
	}
	
	private void validarExclusao(Long idBem) throws BancoobException {
		Integer idInstituicao = Integer.valueOf(InformacoesUsuarioCAPES.getInstance().getIdInstituicao());
		ParametroVO parametroVO = parametroServico.obterParametro(ParametroEnum.VALIDAR_EXCLUSAO_BEM_ANTIGO.getCodigo(), idInstituicao);
		Boolean habilitado = BooleanUtils.toBoolean(parametroVO != null ? parametroVO.getValor() : null);
		if (habilitado) {
			List<String> produtividades = servicoProdutividade.listarProdutividadesPorIdBem(idBem);
			if (produtividades != null && !produtividades.isEmpty()) {
				throw new BemImovelProprietaroParticipanteProdutividadeException(
						StringUtils.join(produtividades.toArray(), ", "));
			}
		}
	}
	
	/** CONFIGURAÇÕES PARA O BEM ANTIGO **/
	private static Map<Short, Short> MAPA_TIPO_POSSE;
	private static Map<Short, Short> MAPA_TIPO_BEM;
	private static Map<Short, Short> MAPA_SUBTIPO_BEM;
	private static Map<Short, Short> MAPA_SITUACAO_BEM;
	static {
		MAPA_TIPO_POSSE = new HashMap<Short, Short>();
		MAPA_TIPO_POSSE.put(TipoRelacionamentoBemImovelEnum.USUFRATARIO.getCodigo(), (short) 6);
		MAPA_TIPO_POSSE.put(TipoRelacionamentoBemImovelEnum.ARRENDATARIO.getCodigo(), (short) 2);
		MAPA_TIPO_POSSE.put(TipoRelacionamentoBemImovelEnum.COMODATARIO.getCodigo(), (short) 4);
		MAPA_TIPO_POSSE.put(TipoRelacionamentoBemImovelEnum.PARCEIRO.getCodigo(), (short) 5);
		
		MAPA_TIPO_BEM = new HashMap<Short, Short>();
		MAPA_TIPO_BEM.put(TipoClassificacaoBemEnum.BEM_IMOVEL.getCodigo(), TipoBemEnum.IMOVEL.getIdTipoBem());
		MAPA_TIPO_BEM.put(TipoClassificacaoBemEnum.BEM_MOVEL.getCodigo(), TipoBemEnum.MOVEL.getIdTipoBem());
		
		// SUBTIPOBEM MÓVEL
		MAPA_SUBTIPO_BEM = new HashMap<Short, Short>();
		MAPA_SUBTIPO_BEM.put((short) 1, (short) 162);
		MAPA_SUBTIPO_BEM.put((short) 2, (short) 23);
		MAPA_SUBTIPO_BEM.put((short) 3, (short) 22);
		MAPA_SUBTIPO_BEM.put((short) 4, (short) 169);
		MAPA_SUBTIPO_BEM.put((short) 5, (short) 40);
		
		// SUBTIPOBEM IMÓVEL
		MAPA_SUBTIPO_BEM.put((short) 100, (short) 12);
		MAPA_SUBTIPO_BEM.put((short) 101, (short) 11);
		MAPA_SUBTIPO_BEM.put((short) 102, (short) 2);
		MAPA_SUBTIPO_BEM.put((short) 103, (short) 170);
		MAPA_SUBTIPO_BEM.put((short) 104, (short) 13);
		MAPA_SUBTIPO_BEM.put((short) 105, (short) 18);
		MAPA_SUBTIPO_BEM.put((short) 106, (short) 15);
		MAPA_SUBTIPO_BEM.put((short) 107, (short) 14);

		// SITUAÇÃO BEM
		MAPA_SITUACAO_BEM = new HashMap<Short, Short>();
		// NESSE CASO, SÓ SERÁ CADASTRADO A SITUAÇÃO 'LIVRE'
		MAPA_SITUACAO_BEM.put((short) 1, (short) 1);
	}
	
}