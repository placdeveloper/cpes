/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import org.apache.commons.beanutils.BeanUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.excecao.BancoobRuntimeException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.cadastro.negocio.dominio.alteracaocompartilhamento.CadastroNovoCompartilhamentoMediator;
import br.com.sicoob.capes.cadastro.negocio.dominio.pessoa.FabricaPessoaCompartilhamento;
import br.com.sicoob.capes.cadastro.negocio.dominio.validacao.ValidacaoPessoa;
import br.com.sicoob.capes.cadastro.negocio.excecao.CadastroJaExisteInstituicaoException;
import br.com.sicoob.capes.cadastro.negocio.excecao.RegistroNaoEncontradoNegocioException;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.LogCompartilhamentoCadastroServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.PessoaCompartilhamentoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.ReplicacaoCadastroServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.ReplicacaoCadastroServicoRemote;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.ResponsavelCadastroServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TransicaoPessoaServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.vo.DadosAlteracaoGrupoVO;
import br.com.sicoob.capes.comum.negocio.enums.GrupoCompartilhamentoEnum;
import br.com.sicoob.capes.comum.negocio.vo.InstituicaoVO;
import br.com.sicoob.capes.comum.util.Constantes;
import br.com.sicoob.capes.integracao.negocio.delegates.CAPESIntegracaoFabricaDelegates;
import br.com.sicoob.capes.integracao.negocio.delegates.SCIIntegracaoDelegate;
import br.com.sicoob.capes.negocio.entidades.CompartilhamentoCadastro;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.Pessoa;
import br.com.sicoob.capes.negocio.entidades.TipoPessoa;
import br.com.sicoob.capes.negocio.entidades.TransicaoPessoa;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaFisica;
import br.com.sicoob.capes.negocio.entidades.vigente.ResponsavelCadastro;
import br.com.sicoob.capes.replicacao.negocio.delegates.CAPESReplicacaoComumFabricaDelegates;
import br.com.sicoob.capes.replicacao.negocio.delegates.ReplicacaoCadastroDelegate;
import br.com.sicoob.capes.replicacao.negocio.dto.ReplicacaoCadastroDTO;

/**
 * @author erico.junior
 * 
 */
@Stateless
@Local( { ReplicacaoCadastroServicoLocal.class })
@Remote( { ReplicacaoCadastroServicoRemote.class })
public class ReplicacaoCadastroServicoEJB extends CAPESCadastroServicoEJB implements
		ReplicacaoCadastroServicoRemote, ReplicacaoCadastroServicoLocal {

	/** O atributo logCompartilhamentoServico. */
	@EJB(mappedName = "capes/cadastro/LogCompartilhamentoCadastroServico") 
	private LogCompartilhamentoCadastroServicoLocal logCompartilhamentoServico;	
	
	/** O atributo pessoaCompartilhamentoServico. */
	@EJB(mappedName = "capes/cadastro/PessoaCompartilhamentoServico") 
	private PessoaCompartilhamentoServicoLocal pessoaCompartilhamentoServico;	
	
	/** O atributo transicaoPessoaServico. */
	@EJB(mappedName = "capes/cadastro/TransicaoPessoaServico") 
	private TransicaoPessoaServicoLocal transicaoPessoaServico;
	
	/** O atributo responsavelServico. */
	@EJB(mappedName = "capes/cadastro/ResponsavelCadastroServico") 
	private ResponsavelCadastroServicoLocal responsavelServico;
	
	/**
	 * {@inheritDoc}
	 */
	public PessoaCompartilhamento iniciarRelacionamentoInstituicao(PessoaCompartilhamento pessoa) throws BancoobException {
		return iniciarRelacionamentoInstituicao(pessoa.getIdPessoaCompartilhamento());
	}
	
	/**
	 * {@inheritDoc}
	 */
	public PessoaCompartilhamento iniciarRelacionamentoInstituicao(Long idPessoaCompartilhamento) throws BancoobException {
		Instituicao instituicao = obterInstituicaoUsuarioLogado();
		PessoaCompartilhamento novaPessoa = iniciarRelacionamentoInstituicao(idPessoaCompartilhamento, instituicao);

		logCompartilhamentoServico.compartilhar(novaPessoa);
		return novaPessoa;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public PessoaCompartilhamento iniciarRelacionamentoInstituicao(PessoaCompartilhamento pessoa, Integer numeroCooperativa, Integer unidadeInstituicao) throws BancoobException {
		Instituicao instituicao = null;
		if (numeroCooperativa != null && unidadeInstituicao != null) {
			SCIIntegracaoDelegate sciDelegate = CAPESIntegracaoFabricaDelegates.getInstance().criarSCIIntegracaoDelegate();
			InstituicaoVO instituicaoInformada = sciDelegate.obterInstituicaoPorNumeroCooperativa(numeroCooperativa);

			instituicao = new Instituicao();
			instituicao.setIdInstituicao(instituicaoInformada.getIdInstituicao());
			instituicao.setNumero(instituicaoInformada.getNumero().toString());
			instituicao.setIdUnidadeInst(unidadeInstituicao);
		} else {
			instituicao = obterInstituicaoUsuarioLogado();
		}

		PessoaCompartilhamento novaPessoa = iniciarRelacionamentoInstituicao(pessoa, instituicao);
		logCompartilhamentoServico.compartilhar(novaPessoa);
		return novaPessoa;
	}

	/**
	 * {@inheritDoc}
	 */
	@Deprecated
	public PessoaCompartilhamento iniciarRelacionamentoBancoob(PessoaCompartilhamento pessoa) throws BancoobException {

		PessoaCompartilhamento pessoaIncluida = null;
		Instituicao bancoob = recuperarInstituicaoBancoob();

		PessoaCompartilhamento pessoaSicoob = recuperarPessoaSicoob(pessoa);
		
		if(pessoaSicoob != null) {
			pessoaIncluida = iniciarRelacionamentoInstituicao(pessoaSicoob, bancoob);
		} else {

			PessoaCompartilhamento origem = pessoaCompartilhamentoServico.obter(pessoa.getIdPessoaCompartilhamento());
			PessoaCompartilhamento novaPessoa = copiarAtributos(origem); 
			pessoaIncluida = pessoaCompartilhamentoServico.incluir(novaPessoa, new DateTimeDB(), bancoob);

			CadastroNovoCompartilhamentoMediator mediator = 
					new CadastroNovoCompartilhamentoMediator(pessoa, pessoaIncluida, bancoob);
			mediator.copiarCadastro();
		}
		
		return pessoaIncluida;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public PessoaCompartilhamento incluirRelacionamentoBancoob(PessoaCompartilhamento pessoa) throws BancoobException {
		Integer codigoCompatilhamento = pessoaCompartilhamentoServico.pesquisarCodigoCompartilhamento(obterInstituicaoUsuarioLogado());
		PessoaCompartilhamento pessoaIncluida = null;
		if (codigoCompatilhamento.equals(Constantes.Comum.COD_GRUPO_SICOOB)) {
			Instituicao insituicaoBancoob =  recuperarInstituicaoBancoob();
			pessoaIncluida = iniciarRelacionamentoInstituicao(pessoa, insituicaoBancoob);
		}
		return pessoaIncluida;

	}
	
	/**
	 * Copiar atributos.
	 *
	 * @param origem o valor de origem
	 * @return PessoaCompartilhamento
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private PessoaCompartilhamento copiarAtributos(PessoaCompartilhamento origem) throws BancoobException {
		TipoPessoa tipo = origem.getPessoa().getTipoPessoa();
		FabricaPessoaCompartilhamento fabrica = FabricaPessoaCompartilhamento.obterFabricaPessoaCompartilhamento(tipo);
		PessoaCompartilhamento novaPessoa = fabrica.criarPessoaCompartilhamento();
		
		try {
			BeanUtils.copyProperties(novaPessoa, origem);
			novaPessoa.setIdPessoaCompartilhamento(null);
			novaPessoa.setTransicoes(null);
		} catch (IllegalAccessException e) {
			throw new BancoobRuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new BancoobRuntimeException(e);
		}
		
		return novaPessoa;
	}	
	
	/**
	 * Recuperar instituicao bancoob.
	 *
	 * @return Instituicao
	 */
	private Instituicao recuperarInstituicaoBancoob() {
		
		Instituicao bancoob = new Instituicao();
		bancoob.setIdInstituicao(Constantes.Comum.ID_INSTITUICAO_BANCOOB);
		bancoob.setIdUnidadeInst(Constantes.Comum.ID_UNIDADEINST_AGENCIA_SEDE);
		return bancoob;
	}
	
	/**
	 * Verifica se a pessoa existe no grupo de compartilhamento Sicoob.
	 * @param pessoa A pessoa a ser verificada.
	 * @return se a pessoa existe no grupo de compartilhamento Sicoob.
	 * @throws BancoobException Caso ocorra alguma exceção.
	 */
	private PessoaCompartilhamento recuperarPessoaSicoob(PessoaCompartilhamento pessoa) 
			throws BancoobException {
				
		return pessoaCompartilhamentoServico.consultar(
				pessoa.getPessoa(), obterCompartilhamentoSicoob());
	}

	/**
	 * Obter compartilhamento sicoob.
	 *
	 * @return CompartilhamentoCadastro
	 */
	private CompartilhamentoCadastro obterCompartilhamentoSicoob() {
		CompartilhamentoCadastro compartilhamento = new CompartilhamentoCadastro();
		compartilhamento.setCodigo(GrupoCompartilhamentoEnum.SICOOB.getCodigo());
		return compartilhamento;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public PessoaCompartilhamento iniciarRelacionamentoInstituicao(PessoaCompartilhamento pessoa, Instituicao destino) throws BancoobException {
		return iniciarRelacionamentoInstituicao(pessoa.getIdPessoaCompartilhamento(), destino);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public PessoaCompartilhamento iniciarRelacionamentoInstituicao(Long idPessoaCompartilhamento, Instituicao destino) throws BancoobException {
		getLogger().info("idPessoaCompartilhamento: " + idPessoaCompartilhamento + " iniciando relacionamento na instituicao: " + destino.getIdInstituicao());

		PessoaCompartilhamento pessoaPersistente = pessoaCompartilhamentoServico.obter(idPessoaCompartilhamento);
		validarIniciarRelacionamento(pessoaPersistente);
		Integer idPessoaLegado = replicarCadastro(pessoaPersistente, destino);
		alterarResponsavelCadastro(pessoaPersistente, destino);

		getLogger().debug("Iniciando o relacionamento com a instituicao:", String.valueOf(destino.getIdInstituicao()), " Incluindo o idPessoaLegado: ",
				String.valueOf(idPessoaLegado), " para o idPessoaCompartilhamento:", String.valueOf(idPessoaCompartilhamento));

		incluirTransicao(pessoaPersistente, new Date(), idPessoaLegado, destino);
		return pessoaPersistente;
	}
	
	/**
	 * O método Validar iniciar relacionamento.
	 *
	 * @param pessoaCompartilhamento o valor de pessoa compartilhamento
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void validarIniciarRelacionamento(PessoaCompartilhamento pessoaCompartilhamento) throws BancoobException {
		Pessoa pessoa = pessoaCompartilhamento.getPessoa();
		
		ValidacaoPessoa validacao = new ValidacaoPessoa();
		validacao.validar(pessoa);
	}
	
	/**
	 * Replica o cadastro da pessoa informada na cooperativa do usuário logado.
	 * 
	 * @param pessoa
	 *            A pessoa que será replicada.
	 * @return O identificador da pessoa no legado.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção.
	 */
	private Integer replicarCadastro(PessoaCompartilhamento pessoa, Instituicao destino)
			throws BancoobException {
		
		Set<TransicaoPessoa> transicoes = pessoa.getTransicoes();
		
		TransicaoPessoa transicao = recuperarTransicao(transicoes, destino);
		Instituicao instituicaoOrigem = transicao.getInstituicao();

		ReplicacaoCadastroDTO replicacao = new ReplicacaoCadastroDTO();
		replicacao.setIdInstituicaoOrigem(instituicaoOrigem.getIdInstituicao());
		replicacao.setNumPessoaOrigem(transicao.getIdPessoaLegado());

		ReplicacaoCadastroDelegate delegate = 
				CAPESReplicacaoComumFabricaDelegates.getInstance().criarReplicacaoCadastroDelegate(); 
		return delegate.replicarCadastro(replicacao, destino.getIdInstituicao());
	}
	
	/**
	 * Recupera a transição que deverá ser utilizada na replicação e valida se já existe uma 
	 * transição para a pessoa na instituição de destino.
	 * @param transicoes As transições da pessoa.
	 * @param destino A instituição de destino da replicação do cadastro.
	 * @return a transição que deverá ser utilizada na replicação 
	 * @throws BancoobException, CadastroJaExisteInstituicaoException 
	 */
	private TransicaoPessoa recuperarTransicao(Set<TransicaoPessoa> transicoes, 
			Instituicao destino) throws CadastroJaExisteInstituicaoException {
	
		Integer idDestino = destino.getIdInstituicao();
		TransicaoPessoa transicaoOrigem = null; 
		
		for (TransicaoPessoa transicao : transicoes) {
			if (idDestino.equals(transicao.getInstituicao().getIdInstituicao())) {
				throw new CadastroJaExisteInstituicaoException();
			}
			transicaoOrigem = transicao;
		}

		return transicaoOrigem;
	}

	/**
	 * Inclui uma transição para a pessoa informada na instituição do usuário
	 * logado.
	 * 
	 * @param pessoa
	 *            A pessoa que terá uma transição com a instituição.
	 * @param dataIntegracao
	 *            A data de integração.
	 * @param idPessoaLegado
	 *            O identificador da pessoa no sistema legado.
	 * @param instituicao
	 *            A instituição a ser incluída na transição.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção.
	 */
	private void incluirTransicao(PessoaCompartilhamento pessoa, Date dataIntegracao, Integer idPessoaLegado,
			Instituicao instituicao) throws BancoobException {

		TransicaoPessoa transicao = new TransicaoPessoa();
		transicao.setDataHoraIntegracao(dataIntegracao);
		transicao.setIdPessoaLegado(idPessoaLegado);
		transicao.setInstituicao(instituicao);
		transicao.setNomePessoaLegado(pessoa.getNomePessoa());
		transicao.setPessoaCompartilhamento(pessoa);
		transicaoPessoaServico.incluir(transicao);
	}

	/**
	 * Se a pessoa física possui transição apenas com o bancoob, devemos alterar o responsável pelo cadastro 
	 * para a nova instituição.
	 * @param pessoa A pessoa no compartilhamento.
	 * @param instituicao A nova instituição.
	 * @throws BancoobException Caso ocorra alguma exceção.
	 */
	private void alterarResponsavelCadastro(PessoaCompartilhamento pessoa, Instituicao instituicao) 
			throws BancoobException {
		
		if((pessoa instanceof PessoaFisica) && (possuiTransicaoApenasBancoob(pessoa))) {

			ResponsavelCadastro responsavel = recuperarResponsavelCadastro(pessoa);
			if(responsavel != null) {
				responsavelServico.alterar(responsavel, instituicao);
			} else {
				responsavel = new ResponsavelCadastro();
				responsavel.setIdInstituicao(instituicao.getIdInstituicao());
				responsavel.setPessoa(pessoa);
				responsavelServico.incluir(responsavel);
			} 		
		}
	}
	
	/**
	 * Possui transicao apenas bancoob.
	 *
	 * @param pessoaCompartilhamento o valor de pessoa compartilhamento
	 * @return {@code true}, em caso de sucesso
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private boolean possuiTransicaoApenasBancoob(PessoaCompartilhamento pessoaCompartilhamento) throws BancoobException{
		
		boolean possui = false;
		List<TransicaoPessoa> transicoes = transicaoPessoaServico.listar(pessoaCompartilhamento);
		
		if(transicoes != null && transicoes.size() == 1) {
			TransicaoPessoa transicao = transicoes.get(0);
			Integer idInstituicao = transicao.getInstituicao().getIdInstituicao();
			possui = Constantes.Comum.ID_INSTITUICAO_BANCOOB.equals(idInstituicao);
		}
		
		getLogger().info("Possui relacionamento apenas bancoob: " + possui);		
		return possui;
	}
	
	/**
	 * Recuperar responsavel cadastro.
	 *
	 * @param pessoa o valor de pessoa
	 * @return ResponsavelCadastro
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private ResponsavelCadastro recuperarResponsavelCadastro(PessoaCompartilhamento pessoa) throws BancoobException {
		
		ResponsavelCadastro responsavel = null; 
		try {
			responsavel = responsavelServico.consultar(pessoa);
		} catch (RegistroNaoEncontradoNegocioException e) {
			responsavel = null;
		}
		
		return responsavel;
	}	

	/**
	 * {@inheritDoc}
	 */
	public void atualizarPessoaAlteracaoGrupo(DadosAlteracaoGrupoVO vo) throws BancoobException {
		
		Integer idPessoaOrigem = vo.getIdPessoaLegadoOrigem();
		Integer idPessoaDestino = vo.getIdPessoaLegadoDestino();
		Integer idInstituicaoOrigem = vo.getIdInstituicaoOrigem();
		Integer idInstituicaoDestino = vo.getIdInstituicaoDestino();
		
		ReplicacaoCadastroDelegate delegate = CAPESReplicacaoComumFabricaDelegates.getInstance()
				.criarReplicacaoCadastroDelegate();
		delegate.atualizarPessoaAlteracaoGrupo(idPessoaOrigem, idPessoaDestino,
				idInstituicaoOrigem, idInstituicaoDestino);
	}
}
