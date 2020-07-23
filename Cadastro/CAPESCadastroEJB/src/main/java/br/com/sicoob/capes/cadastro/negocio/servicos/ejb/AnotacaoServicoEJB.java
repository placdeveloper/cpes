/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import org.apache.commons.lang.math.NumberUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.bancoob.util.DataUtil;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.PessoaCompartilhamentoDelegate;
import br.com.sicoob.capes.cadastro.negocio.dto.ConsultaAnotacaoDTO;
import br.com.sicoob.capes.cadastro.negocio.dto.ConsultaAnotacaoSisbrDTO;
import br.com.sicoob.capes.cadastro.negocio.dto.ConsultaSumarioAnotacaoDTO;
import br.com.sicoob.capes.cadastro.negocio.enums.CategoriaAnotacaoEnum;
import br.com.sicoob.capes.cadastro.negocio.enums.TipoAplicacaoEnum;
import br.com.sicoob.capes.cadastro.negocio.enums.TipoBaixaEnum;
import br.com.sicoob.capes.cadastro.negocio.enums.TipoCapturaEnum;
import br.com.sicoob.capes.cadastro.negocio.excecao.AnotacaoJaBaixadaException;
import br.com.sicoob.capes.cadastro.negocio.excecao.AnotacaoJaExistenteException;
import br.com.sicoob.capes.cadastro.negocio.excecao.AnotacaoJaFlexibilizadaException;
import br.com.sicoob.capes.cadastro.negocio.excecao.AnotacaoRegistraQuantidadeException;
import br.com.sicoob.capes.cadastro.negocio.excecao.AnotacaoRegistraValorException;
import br.com.sicoob.capes.cadastro.negocio.excecao.BaixaAnotacaoAutomaticaException;
import br.com.sicoob.capes.cadastro.negocio.excecao.CampoNaoInformadoException;
import br.com.sicoob.capes.cadastro.negocio.excecao.DataOcorrenciaException;
import br.com.sicoob.capes.cadastro.negocio.excecao.FlexibilizacaoAnotacaoException;
import br.com.sicoob.capes.cadastro.negocio.excecao.LimitarUsoAnotacaoCooperativaException;
import br.com.sicoob.capes.cadastro.negocio.excecao.TipoAnotacaoInativoException;
import br.com.sicoob.capes.cadastro.negocio.excecao.TipoAnotacaoPossuiRestricaoCooperativaException;
import br.com.sicoob.capes.cadastro.negocio.excecao.TipoAplicacaoPessoaException;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.AnotacaoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.AnotacaoServicoRemote;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.GrupoTipoAnotacaoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.PessoaServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoAnotacaoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.vo.SumarioAnotacaoVO;
import br.com.sicoob.capes.cadastro.persistencia.dao.AnotacaoDAO;
import br.com.sicoob.capes.cadastro.persistencia.dao.ObservacaoAnotacaoDAO;
import br.com.sicoob.capes.comum.negocio.dto.AnotacaoExternaDTO;
import br.com.sicoob.capes.comum.negocio.enums.OrigemInformacaoEnum;
import br.com.sicoob.capes.comum.negocio.enums.TipoAnotacaoReplicavelEnum;
import br.com.sicoob.capes.comum.negocio.enums.TipoPessoaEnum;
import br.com.sicoob.capes.comum.util.ReflexaoUtils;
import br.com.sicoob.capes.negocio.entidades.Anotacao;
import br.com.sicoob.capes.negocio.entidades.AnotacaoSisbr;
import br.com.sicoob.capes.negocio.entidades.CategoriaAnotacao;
import br.com.sicoob.capes.negocio.entidades.GrupoTipoAnotacao;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.OrigemInformacao;
import br.com.sicoob.capes.negocio.entidades.Pessoa;
import br.com.sicoob.capes.negocio.entidades.TipoAnotacao;
import br.com.sicoob.capes.negocio.entidades.TipoBaixa;
import br.com.sicoob.capes.negocio.entidades.TipoCaptura;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * Implementa as operacoes do servico de anotacoes.
 * 
 * @author Erico.Junior
 */
@Stateless
@Local( { AnotacaoServicoLocal.class })
@Remote( { AnotacaoServicoRemote.class })
public class AnotacaoServicoEJB extends CAPESCadastroCrudServicoEJB<Anotacao>
		implements AnotacaoServicoRemote, AnotacaoServicoLocal {

	@Inject
	@Default
	private AnotacaoDAO anotacaoDao;
	
	@Inject
	@Default
	private ObservacaoAnotacaoDAO observacaoAnotacaoDAO;

	/** O atributo servicoTipoAnotacao. */
	@EJB(mappedName = "capes/cadastro/TipoAnotacaoServico")
	private TipoAnotacaoServicoLocal servicoTipoAnotacao;

	/** O atributo servicoPessoa. */
	@EJB(mappedName = "capes/cadastro/PessoaServico")
	private PessoaServicoLocal servicoPessoa;
	
	@EJB
	private GrupoTipoAnotacaoServicoLocal servicoGrupoTipoAnotacao;
	
//	@EJB(mappedName = "capes/cadastro/PessoaCompartilhamentoServico")
//	private PessoaCompartilhamentoServicoLocal servicoPessoaCompartilhamento;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected AnotacaoDAO getDAO() {
		return anotacaoDao;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void flexibilizar(Anotacao anotacao) throws BancoobException {
		Anotacao anotacaoPersistida = obter(anotacao.getIdAnotacao());
		
		limitarCooperativa(anotacaoPersistida);
		cooperativaPodeUtilizarTipoAnotacao(anotacaoPersistida);

		if (Boolean.TRUE.equals(anotacaoPersistida.getFlexibilidade())) {
			throw new AnotacaoJaFlexibilizadaException();
		}

		if (!isCategoriaImpeditivaRelativa(anotacaoPersistida)) {
			throw new FlexibilizacaoAnotacaoException();
		}
		
		TipoAnotacao tipoAnotacao = anotacao.getTipoAnotacao();
		if (deveAnotarFilial(tipoAnotacao, anotacaoPersistida)) {
			PessoaCompartilhamentoDelegate pessoaCompartilhamentoDelegate = CAPESCadastroFabricaDelegates.getInstance().criarPessoaCompartilhamentoDelegate();
			PessoaCompartilhamento pessoaCompartilhamento = pessoaCompartilhamentoDelegate.obter(anotacao.getPessoaCompartilhamento().getId());
			List<PessoaCompartilhamento> pessoas = pessoaCompartilhamentoDelegate.consultarFiliais(pessoaCompartilhamento.getCodCompartilhamentoCadastro(), pessoaCompartilhamento.getPessoa().getCpfCnpj());
			flexibilizarAnotacoesFiliais(obterIdsFiliais(pessoas), tipoAnotacao, new DateTimeDB(), recuperarLoginSemDominio(), true);
			return;
		}

		anotacaoPersistida.setDataHoraAlteracao(new DateTimeDB());
		anotacaoPersistida.setUsuarioAlteracao(recuperarLoginSemDominio());
		anotacaoPersistida.setFlexibilidade(true);
		anotacaoDao.alterar(anotacaoPersistida);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ConsultaSumarioAnotacaoDTO pesquisarSumarioAnotacao(PessoaCompartilhamento pessoa)
			throws BancoobException {
		
		if(pessoa == null || pessoa.getIdPessoaCompartilhamento() == null) {
			throw new CampoNaoInformadoException("Pessoa");			
		}

		List<SumarioAnotacaoVO> listaSumario = anotacaoDao.listarSumarioAnotacoesVigentes(pessoa);

		ConsultaSumarioAnotacaoDTO dto = new ConsultaSumarioAnotacaoDTO();
		dto.setResultado(listaSumario);
		return dto;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Anotacao> listarAnotacoesPorFiltro(ConsultaAnotacaoDTO filtro) {
		return anotacaoDao.listarAnotacoesPorFiltro(filtro);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<AnotacaoSisbr> listarAnotacoesSisbr(ConsultaAnotacaoSisbrDTO criterios) {
		return anotacaoDao.listarAnotacoesSisbr(criterios);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void alterar(Anotacao anotacao) throws BancoobException {
		throw new UnsupportedOperationException();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void excluir(Serializable chave) throws BancoobException {
		throw new UnsupportedOperationException();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Anotacao incluir(Anotacao anotacao) throws BancoobException {
		anotacao.setDataHoraAnotacao(new DateTimeDB());
		anotacao.setUsuarioInclusao(recuperarLoginSemDominio());
		anotacao.setInstituicao(obterInstituicaoUsuarioLogado());
		OrigemInformacao origemInformacao = new OrigemInformacao();
		origemInformacao.setId(OrigemInformacaoEnum.INTERNA.getIdOrigemInformacao());
		anotacao.setOrigemInformacao(origemInformacao);
		return incluirAnotacao(anotacao, Boolean.TRUE);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Anotacao> incluir(List<Anotacao> anotacoes) throws BancoobException {

		getLogger().debug("Incluindo anotacoes automaticas");
		List<Anotacao> retorno = new ArrayList<Anotacao>();
		if (anotacoes != null && !anotacoes.isEmpty()) {
			for (Anotacao anotacao : anotacoes) {
				Anotacao a = incluirAnotacao(anotacao, Boolean.FALSE);
				retorno.add(a);
			}
		}

		return retorno;
	}

	/**
	 * Inclui a anotacao informada, definindo alguns atributos a partir da
	 * origem da informacao.
	 * 
	 * @param anotacao
	 *            A anotacao a ser incluida
	 * @param plataformaAtendimento
	 *            Se a anotacao for incluida pela plataforma de atendimento, o
	 *            sitema devera validar se a cooperativa possui alguma restricao
	 *            para o tipo de cooperativa informada.
	 * @return A anotacao incluida.
	 * @throws BancoobException
	 *             Caso ocorra alguma excecao.
	 */
	@Override
	public Anotacao incluirAnotacao(Anotacao anotacao, boolean plataformaAtendimento) throws BancoobException {
		if (plataformaAtendimento) {
			cooperativaPodeUtilizarTipoAnotacao(anotacao);
		}

		Integer codigo = anotacao.getTipoAnotacao().getCodTipoAnotacao();
		TipoAnotacao tipoAnotacao = servicoTipoAnotacao.obter(codigo);

		validarDataOcorrencia(anotacao);

		validarTipoAnotacaoAtivo(tipoAnotacao);
		validarRegistraValor(tipoAnotacao, anotacao.getValor());
		validarRegistraQuantidade(tipoAnotacao, anotacao.getQuantidade());
		
		if (deveAnotarFilial(tipoAnotacao, anotacao)) {
			return tratarFiliais(anotacao, tipoAnotacao);
		}
		
		PessoaCompartilhamento pessoa = anotacao.getPessoaCompartilhamento();
		validarAplicacaoTipoPessoa(pessoa.getPessoa(), tipoAnotacao);
		validarTipoReplicavel(anotacao, pessoa);
		validarObservacaoAnotacao(anotacao);

		return incluirAnotacao(anotacao);
	}
	
	/**
	 * O método Validar observacao anotacao.
	 *
	 * @param anotacao o valor de anotacao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void validarObservacaoAnotacao(Anotacao anotacao) throws BancoobException {
		
		if(anotacao.getCodigoTipoExposicao() != null){
			anotacao.setObservacaoAnotacao(observacaoAnotacaoDAO.obter(anotacao.getCodigoTipoExposicao()));
		}
		
	}

	/**
	 * Incluir anotacao.
	 *
	 * @param anotacao o valor de anotacao
	 * @return Anotacao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private Anotacao incluirAnotacao(Anotacao anotacao) throws BancoobException {
		baixarAnotacoesGrupoTipoAnotacao(anotacao.getTipoAnotacao().getId(), anotacao.getPessoaCompartilhamento().getId());
		anotacao.setFlexibilidade(false);
		anotacao.setValor(tratarValor(anotacao.getValor()));
		return super.incluir(anotacao);
	}
	
	/**
	 * Ao incluir uma anotação pertencente ao grupo de irregularidades de CPF,
	 * as demais anotações do grupo deverão ser baixadas.
	 * 
	 * @param idPessoaCompartilhamento
	 * @throws BancoobException
	 */
	private void baixarAnotacoesGrupoTipoAnotacao(Integer idTipoAnotacao, Long idPessoaCompartilhamento) throws BancoobException {
		List<Anotacao> anotacoes = new ArrayList<Anotacao>();
		List<GrupoTipoAnotacao> gruposTipoAnotacao = servicoGrupoTipoAnotacao.obterGruposPorTipoAnotacao(idTipoAnotacao);
		
		if(gruposTipoAnotacao != null) {
			for (GrupoTipoAnotacao grupo : gruposTipoAnotacao) {
				List<Anotacao> retornoAnotacoes = obterAnotacoesPorGrupoTipoAnotacao(idPessoaCompartilhamento, grupo.getId());
				if(retornoAnotacoes != null && retornoAnotacoes.size() > 0) {
					anotacoes.addAll(retornoAnotacoes);
				}
			}

			for (Anotacao anotacao : anotacoes) {
				baixarAnotacao(anotacao);
			}
		}
	}
	
	/**
	 * Valida se o tipo da anotacao e replicavel. 
	 * Para os tipos replicaveis pode existir apenas uma anotacao vigente.  
	 * @param tipoAnotacao O tipo a ser verificado. 
	 * @throws AnotacaoJaExistenteException Caso ja exista uma anotacao vigente para este tipo.
	 */
	private void validarTipoReplicavel(Anotacao anotacao, PessoaCompartilhamento pessoa) throws AnotacaoJaExistenteException {
		Integer codigoTipoAnotacao = anotacao.getTipoAnotacao().getCodTipoAnotacao();
		OrigemInformacao origemInformacao = anotacao.getOrigemInformacao();

		if (TipoAnotacaoReplicavelEnum.valueOf(codigoTipoAnotacao) != null) {
			List<Anotacao> lista = listarAnotacoesVigentes(pessoa);
			for (Anotacao anot : lista) {
				if (anot.getTipoAnotacao().getCodTipoAnotacao().equals(codigoTipoAnotacao)
						&& origemInformacao.getId().equals(anot.getOrigemInformacao().getId())) {
					throw new AnotacaoJaExistenteException();
				}
			}
		}
	}
	
	/**
	 * Verifica se o tipo de anotacoes esta ativo, caso esteja inativo uma
	 * excecao sera lancada.
	 * 
	 * @param tipoAnotacao
	 *            O tipo de anotacao.
	 * @throws TipoAnotacaoInativoException
	 *             Caso o tipo de anotacao esteja inativo.
	 */
	private void validarTipoAnotacaoAtivo(TipoAnotacao tipoAnotacao)
			throws TipoAnotacaoInativoException {

		if (Boolean.FALSE.equals(tipoAnotacao.getAtivo())) {
			throw new TipoAnotacaoInativoException();
		}
	}

	/**
	 * Verifica se o tipo de anotacao registra valor e se o valor e nulo ou
	 * zero.
	 * 
	 * @param tipoAnotacao
	 *            O tipo de anotacao
	 * @param valor
	 *            O valor a ser verificado
	 * @throws AnotacaoRegistraValorException
	 *             Caso o tipo de anotacao registre valor e este seja nulo ou
	 *             zero.
	 */
	private void validarRegistraValor(TipoAnotacao tipoAnotacao, BigDecimal valor)
			throws AnotacaoRegistraValorException {

		if (Boolean.TRUE.equals(tipoAnotacao.getRegistraValor())
				&& (valor == null || BigDecimal.ZERO.equals(valor))) {
				throw new AnotacaoRegistraValorException();
		}
	}
	
	/**
	 * Verifica se o tipo de anotacao registra quantidade e se o a quantidade eh nula ou
	 * zero.
	 * 
	 * @param tipoAnotacao
	 *            O tipo de anotacao
	 * @param quantidade
	 *            A quantidade a ser verificada
	 * @throws AnotacaoRegistraQuantidadeException
	 *             Caso o tipo de anotacao registre quantidade e este seja nulo ou
	 *             zero.
	 */
	private void validarRegistraQuantidade(TipoAnotacao tipoAnotacao, Short quantidade)
			throws AnotacaoRegistraQuantidadeException {
		
		if (Boolean.TRUE.equals(tipoAnotacao.getRegistraQuantidade())
				&& (quantidade == null || NumberUtils.SHORT_ZERO.equals(quantidade))) {
			throw new AnotacaoRegistraQuantidadeException();
		}
	}

	/**
	 * Valida a data da ocorrencia, ela deve ser menor ou igual a data da
	 * anotacao.
	 * 
	 * @param anotacao
	 *            A anotacao.
	 * @throws DataOcorrenciaException
	 *             Caso a data da ocorrencia seja superior a data da anotacao.
	 */
	private void validarDataOcorrencia(Anotacao anotacao) throws DataOcorrenciaException {

		Date dataAnotacaoZeroHora = DataUtil.configurarPrimeiraDataIntervalo(anotacao
				.getDataHoraAnotacao());
		Date dataOcorrenciaZeroHora = DataUtil.configurarPrimeiraDataIntervalo(anotacao
				.getDataHoraOcorrencia());

		if (dataOcorrenciaZeroHora.after(dataAnotacaoZeroHora)) {
			throw new DataOcorrenciaException();
		}
	}

	/**
	 * Valida se o tipo de anotacao informado pode ser aplicado a Pessoa
	 * informado pelo tipo. Ex: Pessoa Fisica ou Pessoa Juridica.
	 * 
	 * @param pessoa
	 *            A pessoa a qual esta sendo incluída a anotacao.
	 * @param tipoAnotacao
	 *            O tipo de anotacao que esta sendo incluido.
	 * @throws BancoobException
	 *             Caso ocorra alguma excecao.
	 */
	private void validarAplicacaoTipoPessoa(Pessoa pessoa, 
			TipoAnotacao tipoAnotacao)throws BancoobException {

		Short idAplicacao = tipoAnotacao.getIdTipoAplicacao();
		if (!TipoAplicacaoEnum.AMBAS.getIdTipoAplicacao().equals(idAplicacao)) {

			boolean isValido = true;
			String descricao = "";
			if (servicoPessoa.isPessoaFisica(pessoa)) {
				isValido = TipoAplicacaoEnum.PESSOA_FISICA.getIdTipoAplicacao().equals(idAplicacao);
				descricao = TipoAplicacaoEnum.PESSOA_FISICA.getDescricao();
			} else {
				isValido = TipoAplicacaoEnum.PESSOA_JURIDICA.getIdTipoAplicacao().equals(idAplicacao);
				descricao = TipoAplicacaoEnum.PESSOA_JURIDICA.getDescricao();
			}

			if (!isValido) {
				throw new TipoAplicacaoPessoaException(descricao);
			}
		}
	}
	
	@Override
	public void baixarAnotacao(Anotacao anotacao) throws BancoobException {
		baixarAnotacao(anotacao, recuperarLoginSemDominio());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void baixarAnotacao(Anotacao anotacao, String usuarioOperacao) throws BancoobException {
		Anotacao anotacaoPersistida = obter(anotacao.getIdAnotacao());
		limitarCooperativa(anotacaoPersistida);

		if (anotacaoPersistida.getTipoBaixa() != null) {
			throw new AnotacaoJaBaixadaException();
		}

		if (!isTipoCapturaManual(anotacaoPersistida)) {
			throw new BaixaAnotacaoAutomaticaException();
		}
		
		if(isTipoCapturaManual(anotacaoPersistida) && !isOrigemInterna(anotacaoPersistida)) {
			throw new BaixaAnotacaoAutomaticaException();
		}

		TipoBaixa tipoBaixa = obterTipoBaixa(TipoBaixaEnum.BAIXA_MANUAL);
		baixarAnotacao(anotacaoPersistida, tipoBaixa, new DateTimeDB(), usuarioOperacao, Boolean.TRUE);
	}

	/**
	 * Verifica se a origem da informação é interna.
	 * 
	 * @param anotacaoPersistida
	 * @return
	 */
	private boolean isOrigemInterna(Anotacao anotacaoPersistida) {
		if (anotacaoPersistida != null && anotacaoPersistida.getOrigemInformacao() != null) {
			OrigemInformacao origemInformacao = anotacaoPersistida.getOrigemInformacao();
			return OrigemInformacaoEnum.INTERNA.getIdOrigemInformacao().equals(origemInformacao.getIdOrigemInfo());
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void baixarAnotacoes(List<Anotacao> anotacoes) throws BancoobException {
		baixarAnotacoesAutomaticas(anotacoes, TipoBaixaEnum.BAIXA_AUTOMATICA_NOVA_CONSULTA);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Anotacao> baixarAnotacoesAutomaticas(List<Anotacao> anotacoes, 
			TipoBaixaEnum tipoBaixaEnum) throws BancoobException {

		getLogger().debug("Baixando anotacoes automaticas com o tipo: "  + tipoBaixaEnum.getDescricao());
		
		if (anotacoes != null && !anotacoes.isEmpty()) {
			DateTimeDB dataAtual = new DateTimeDB();
			TipoBaixa tipoBaixa = obterTipoBaixa(tipoBaixaEnum);
			for (Anotacao anotacao : anotacoes) {
				baixarAnotacao(anotacao, tipoBaixa, dataAtual, null, Boolean.FALSE);
			}
		}
		
		return anotacoes;
	}
	
	/**
	 * Executa a baixa da anotacao com os atributos informados.
	 * 
	 * @param anotacao
	 *            A anotacao a ser baixada.
	 * @param tipoBaixa
	 *            O tipo da baixa.
	 * @param dataHoraBaixa
	 *            A data e hora da baixa.
	 * @param usuarioBaixa
	 *            O usuario que esta fazendo a baixa.
	 * @throws BancoobException
	 *             Caso ocorra alguma excecao.
	 */
	private void baixarAnotacao(Anotacao anotacao, TipoBaixa tipoBaixa, DateTimeDB dataHoraBaixa, String usuarioBaixa, boolean plataformaAtendimento) throws BancoobException {
		getLogger().debug("Baixando a anotacao: " + anotacao.getIdAnotacao());

		if (plataformaAtendimento) {
			cooperativaPodeUtilizarTipoAnotacao(anotacao);
		}
		
		TipoAnotacao tipoAnotacao = anotacao.getTipoAnotacao();
		if (deveAnotarFilial(tipoAnotacao, anotacao)) {
			PessoaCompartilhamentoDelegate pessoaCompartilhamentoDelegate = CAPESCadastroFabricaDelegates.getInstance().criarPessoaCompartilhamentoDelegate();
			PessoaCompartilhamento pessoaCompartilhamento = pessoaCompartilhamentoDelegate.obter(anotacao.getPessoaCompartilhamento().getId());
			List<PessoaCompartilhamento> pessoas = pessoaCompartilhamentoDelegate.consultarFiliais(pessoaCompartilhamento.getCodCompartilhamentoCadastro(), pessoaCompartilhamento.getPessoa().getCpfCnpj());
			baixarAnotacoesFiliais(obterIdsFiliais(pessoas), tipoAnotacao, tipoBaixa, dataHoraBaixa, usuarioBaixa);
			return;
		}

		anotacao.setUsuarioAlteracao(usuarioBaixa);
		anotacao.setDataHoraBaixa(dataHoraBaixa);
		anotacao.setTipoBaixa(tipoBaixa);
		super.alterar(anotacao);
	}

	/**
	 * Verifica se a categoria da anotacao e "Impeditiva relativa" ou nao.
	 * 
	 * @param anotacao
	 *            A anotacao a ser verificada.
	 * @return se a categoria da anotacao e "Impeditiva relativa" ou nao.
	 */
	private boolean isCategoriaImpeditivaRelativa(Anotacao anotacao) {
		CategoriaAnotacao categoria = anotacao.getTipoAnotacao().getCategoriaAnotacao();
		Short codigo = categoria.getIdCategoriaAnotacao();
		return CategoriaAnotacaoEnum.IMPEDITIVA_RELATIVA.getIdCategoriaAnotacao().equals(codigo);
	}

	/**
	 * Verifica se o tipo de captura da anotacao e "Manual" ou nao.
	 * 
	 * @param anotacao
	 *            A anotacao a ser verificada.
	 * @return se o tipo de captura da anotacao e "Manual" ou nao.
	 */
	private boolean isTipoCapturaManual(Anotacao anotacao) {
		TipoCaptura tipoCaptura = anotacao.getTipoAnotacao().getTipoCaptura();
		Short codigo = tipoCaptura.getIdTipoCaptura();
		return TipoCapturaEnum.MANUAL.getCodigo().equals(codigo);
	}

	/**
	 * Monta um TipoBaixa a partir do enum informado.
	 * 
	 * @param tipoBaixa
	 *            O enum com o tipo da baixa.
	 * @return A entidade TipoBaixa.
	 */
	private TipoBaixa obterTipoBaixa(TipoBaixaEnum tipoBaixa) {
		TipoBaixa tipo = new TipoBaixa();
		tipo.setIdTipoBaixa(tipoBaixa.getIdTipoBaixa());
		return tipo;
	}

	/**
	 * Lista as anotacoes vigentes para a pessoa informada.
	 * 
	 * @param pessoa
	 *            A pessoa a ser consultada.
	 * @return Lista de anotacoes vigentes para a pessoa informada
	 */
	private List<Anotacao> listarAnotacoesVigentes(PessoaCompartilhamento pessoa) {

		Anotacao anotacao = new Anotacao();
		anotacao.setPessoaCompartilhamento(pessoa);

		ConsultaAnotacaoDTO filtro = new ConsultaAnotacaoDTO();
		filtro.setFiltro(anotacao);
		filtro.setAnotacoesBaixadas(false);

		return anotacaoDao.listarAnotacoesPorFiltro(filtro);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Anotacao> listarAnotacoesParaRelatorio(ConsultaAnotacaoDTO criterios)
			throws BancoobException {
		return getDAO().listarAnotacoesParaRelatorio(criterios);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void baixarAnotacoesExternas(PessoaCompartilhamento pessoaCompartilhamento,
			OrigemInformacao origemInformacao, AnotacaoExternaDTO dto) throws BancoobException {

		DateTimeDB dataHoraBaixa = new DateTimeDB();
		List<Anotacao> anotacoes = getDAO().obterAnotacoesExternasBaixa(pessoaCompartilhamento, origemInformacao, dto);
		for (Anotacao anotacao : anotacoes) {
			anotacao.setTipoBaixa(new TipoBaixa(TipoBaixaEnum.BAIXA_AUTOMATICA_NOVA_CONSULTA.getIdTipoBaixa()));
			anotacao.setDataHoraBaixa(dataHoraBaixa);
		}
		getDAO().alterar(anotacoes);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Anotacao importar(Anotacao anotacao) throws BancoobException {
		return incluirAnotacao(anotacao, Boolean.FALSE);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isCadastroReceitaRegular(PessoaCompartilhamento pessoaCompartilhamento) throws BancoobException {
		return getDAO().isCadastroReceitaRegular(pessoaCompartilhamento);
	}

	/**
	 * Define o valor de anotacaoDao.
	 *
	 * @param anotacaoDao o novo valor de anotacaoDao
	 */
	protected void setAnotacaoDao(AnotacaoDAO anotacaoDao) {
		this.anotacaoDao = anotacaoDao;
	}

	/**
	 * Define o valor de servicoTipoAnotacao.
	 *
	 * @param servicoTipoAnotacao o novo valor de servicoTipoAnotacao
	 */
	protected void setServicoTipoAnotacao(TipoAnotacaoServicoLocal servicoTipoAnotacao) {
		this.servicoTipoAnotacao = servicoTipoAnotacao;
	}
	
	protected void setServicoGrupoTipoAnotacao(GrupoTipoAnotacaoServicoLocal servicoGrupoTipoAnotacao) {
		this.servicoGrupoTipoAnotacao = servicoGrupoTipoAnotacao;
	}

	/**
	 * Define o valor de servicoPessoa.
	 *
	 * @param servicoPessoa o novo valor de servicoPessoa
	 */
	protected void setServicoPessoa(PessoaServicoLocal servicoPessoa) {
		this.servicoPessoa = servicoPessoa;
	}
	
	/**
	 * Verifica se a cooperativa pode utilizar o tipo da anotação informada.
	 * 
	 * @param anotacao
	 *            A anotação utilizada.
	 * @throws BancoobException
	 */
	private void cooperativaPodeUtilizarTipoAnotacao(Anotacao anotacao) throws BancoobException {
		if (anotacao.getTipoAnotacao() != null && TipoCapturaEnum.MANUAL.getCodigo().equals(anotacao.getTipoAnotacao().getTipoCaptura().getIdTipoCaptura()) && anotacao.getTipoAnotacao().getValidaUsoAnotacao() != null && anotacao.getTipoAnotacao().getValidaUsoAnotacao()) {
			if (!getDAO().cooperativaPodeUtilizarTipoAnotacao(anotacao.getTipoAnotacao(), anotacao.getInstituicao().getIdInstituicao())) {
				throw new TipoAnotacaoPossuiRestricaoCooperativaException();
			}
		}
	}
	
	/**
	 * Após verificar se a anotação deve anotar também as suas filiais, o método
	 * executa a ação, baixando as anotações anteriores e incluindo as novas
	 * para cada filial da instituição informada.
	 * 
	 * @param anotacao
	 * @return
	 * @throws BancoobException
	 */
	private Anotacao tratarFiliais(Anotacao anotacao, TipoAnotacao tipoAnotacao) throws BancoobException {
		PessoaCompartilhamentoDelegate pessoaCompartilhamentoDelegate = CAPESCadastroFabricaDelegates.getInstance().criarPessoaCompartilhamentoDelegate();
		PessoaCompartilhamento pessoaCompartilhamento = pessoaCompartilhamentoDelegate.obter(anotacao.getPessoaCompartilhamento().getId());
		List<PessoaCompartilhamento> pessoas = pessoaCompartilhamentoDelegate.consultarFiliais(pessoaCompartilhamento.getCodCompartilhamentoCadastro(), pessoaCompartilhamento.getPessoa().getCpfCnpj());
		baixarAnotacoesFiliais(obterIdsFiliais(pessoas), tipoAnotacao, obterTipoBaixa(TipoBaixaEnum.BAIXA_AUTOMATICA_NOVA_CONSULTA), new DateTimeDB(), null);
		Anotacao retorno = null;
		for (PessoaCompartilhamento pessoa : pessoas) {
			Anotacao novaAnotacao = criarAnotacaoInclusao(anotacao);
			novaAnotacao.setPessoaCompartilhamento(pessoa);
			validarAplicacaoTipoPessoa(pessoa.getPessoa(), tipoAnotacao);
			validarTipoReplicavel(anotacao, pessoa);
			novaAnotacao = incluirAnotacao(novaAnotacao);
			if(pessoaCompartilhamento.getId().equals(novaAnotacao.getPessoaCompartilhamento().getId())) {
				retorno = novaAnotacao;
			}
		}
		return retorno;
	}
	
	/**
	 * Criar anotacao inclusao.
	 *
	 * @param anotacao o valor de anotacao
	 * @return Anotacao
	 */
	private Anotacao criarAnotacaoInclusao(Anotacao anotacao) {
		Anotacao retorno = new Anotacao();
		ReflexaoUtils.copiarPropriedades(retorno, anotacao, "id", "idAnotacao", "pessoaCompartilhamento");
		return retorno;
	}

	/**
	 * O método Baixar anotacoes filiais.
	 *
	 * @param filiais o valor de filiais
	 * @param tipoAnotacao o valor de tipo anotacao
	 * @param tipoBaixa o valor de tipo baixa
	 * @param dataHoraBaixa o valor de data hora baixa
	 * @param usuarioBaixa o valor de usuario baixa
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void baixarAnotacoesFiliais(List<Long> filiais, TipoAnotacao tipoAnotacao, TipoBaixa tipoBaixa, DateTimeDB dataHoraBaixa, String usuarioBaixa) throws BancoobException {
		if (filiais != null && !filiais.isEmpty()) {
			getDAO().baixarAnotacoesFiliais(filiais, tipoAnotacao, tipoBaixa, dataHoraBaixa, usuarioBaixa);
		}
	}

	/**
	 * Obter ids filiais.
	 *
	 * @param pessoas o valor de pessoas
	 * @return List
	 */
	private List<Long> obterIdsFiliais(List<PessoaCompartilhamento> pessoas) {
		List<Long> retorno = null;
		if (pessoas != null && !pessoas.isEmpty()) {
			retorno = new ArrayList<Long>();
			for (PessoaCompartilhamento pessoa : pessoas) {
				retorno.add(pessoa.getId());
			}
		}
		return retorno;
	}
	
	/**
	 * O método Flexibilizar anotacoes filiais.
	 *
	 * @param filiais o valor de filiais
	 * @param tipoAnotacao o valor de tipo anotacao
	 * @param dataAlteracao o valor de data alteracao
	 * @param usuarioAlteracao o valor de usuario alteracao
	 * @param flexibilizar o valor de flexibilizar
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void flexibilizarAnotacoesFiliais(List<Long> filiais, TipoAnotacao tipoAnotacao, DateTimeDB dataAlteracao, String usuarioAlteracao, boolean flexibilizar) throws BancoobException {
		getDAO().flexibilizarAnotacoesFiliais(filiais, tipoAnotacao, dataAlteracao, usuarioAlteracao, flexibilizar);
	}

	/**
	 * Verifica se o tipo da anotação é para pessoa jurídica e foi marcada para
	 * anotar as suas filiais.
	 * 
	 * @param tipoAnotacao
	 * @param anotacao
	 * @return se deve anotar as filiais.
	 */
	private boolean deveAnotarFilial(TipoAnotacao tipoAnotacao, Anotacao anotacao) {
		if (TipoAplicacaoEnum.PESSOA_JURIDICA.getIdTipoAplicacao().equals(tipoAnotacao.getIdTipoAplicacao()) || TipoAplicacaoEnum.AMBAS.getIdTipoAplicacao().equals(tipoAnotacao.getIdTipoAplicacao())) {
			if (tipoAnotacao.getAnotarFilial() && TipoPessoaEnum.isPessoaJuridica(anotacao.getPessoaCompartilhamento().getPessoa().getTipoPessoa().getCodTipoPessoa())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Verifica se o tipo da anotação é compartilhada. Se caso a anotação não
	 * for compartilhada, quem poderá alterá-la (Baixar e flexibilizar), será
	 * somente a cooperativa que a incluiu.
	 * 
	 * @param anotacao a ser verificada.
	 * @throws BancoobException
	 */
	private void limitarCooperativa(Anotacao anotacao) throws BancoobException {
		Instituicao instituicao = obterInstituicaoUsuarioLogadoThreadSafe();
		
		// Verifica se existe uma instituição do usuário logado, caso não tenha, podemos ignorar a validação abaixo por se tratar de um fechamento.
		if(instituicao != null) {
			if (anotacao != null && anotacao.getTipoAnotacao() != null && anotacao.getInstituicao() != null && anotacao.getInstituicao().getIdInstituicao() != null) {
				if (!anotacao.getTipoAnotacao().getCompartilhar()) {
					if (!instituicao.getIdInstituicao().equals(anotacao.getInstituicao().getIdInstituicao())) {
						throw new LimitarUsoAnotacaoCooperativaException();
					}
				}
			}
		}
	}

	/**
	 * Baixa anotacoes automaticas da pessoa conforme o tipo.
	 * @param obter
	 * @param pessoa
	 */
	@Override
	public void baixarAnotacaoPessoaPorTipo(TipoAnotacao tipoAnotacao, PessoaCompartilhamento pessoa) throws BancoobException {
		ConsultaAnotacaoDTO consulta = new ConsultaAnotacaoDTO();
		Anotacao filtro = new Anotacao();
		filtro.setTipoAnotacao(tipoAnotacao);
		filtro.setPessoaCompartilhamento(pessoa);
		consulta.setFiltro(filtro);
		
		List<Anotacao> lista = getDAO().listarAnotacoesPorFiltro(consulta);
		this.baixarAnotacoesAutomaticas(lista, TipoBaixaEnum.BAIXA_AUTOMATICA_SISBR);
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public ConsultaDto<Anotacao> obterAnotacoesVencidasPorGrupoTipoAnotacao(Date dataLimite, Short codigoGrupoTipoAnotacao, int paginaAtual, int tamanhoPagina) throws BancoobException {
		return getDAO().obterAnotacoesVencidasPorGrupoTipoAnotacao(dataLimite, codigoGrupoTipoAnotacao, paginaAtual, tamanhoPagina);
	}
	
	/**
	 * Obtém as anotações por grupo tipo anotação
	 * @param idPessoaCompartilhamento
	 * @param codigoGrupoTipoAnotacao
	 * @return
	 * @throws BancoobException
	 */
	private List<Anotacao> obterAnotacoesPorGrupoTipoAnotacao(Long idPessoaCompartilhamento, Short codigoGrupoTipoAnotacao) throws BancoobException {
		return getDAO().obterAnotacoesPorGrupoTipoAnotacao(idPessoaCompartilhamento, codigoGrupoTipoAnotacao);
	}
	
	@Override
	public void baixarAnotacaoSemValidar(Anotacao anotacao) throws BancoobException {
		getLogger().debug("Baixando a anotacao sem realizar validações: " + anotacao.getIdAnotacao());
		Anotacao anotacaoPersistida = obter(anotacao.getIdAnotacao());
		if (anotacaoPersistida.getTipoBaixa() != null) {
			throw new AnotacaoJaBaixadaException();
		}
		TipoBaixa tipoBaixa = obterTipoBaixa(TipoBaixaEnum.BAIXA_MANUAL);
		baixarAnotacao(anotacaoPersistida, tipoBaixa, new DateTimeDB(), recuperarLoginSemDominio(), false);
	}

}