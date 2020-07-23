package br.com.sicoob.capes.replicacao.negocio.servicos.command;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.util.DataUtil;
import br.com.sicoob.capes.cadastro.negocio.delegates.PessoaCompartilhamentoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.PessoaDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.ResponsavelCadastroDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.TransicaoPessoaDelegate;
import br.com.sicoob.capes.cadastro.negocio.dominio.pessoa.FabricaPessoaCompartilhamento;
import br.com.sicoob.capes.comum.negocio.contexto.InformacoesUsuarioCAPES;
import br.com.sicoob.capes.comum.negocio.enums.TipoPerfilCadastroEnum;
import br.com.sicoob.capes.negocio.entidades.CompartilhamentoCadastro;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.PerfilCadastro;
import br.com.sicoob.capes.negocio.entidades.Pessoa;
import br.com.sicoob.capes.negocio.entidades.TipoPessoa;
import br.com.sicoob.capes.negocio.entidades.TransicaoPessoa;
import br.com.sicoob.capes.negocio.entidades.legado.TransicaoReplicacao;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.replicacao.negocio.delegates.TransicaoReplicacaoDelegate;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;

/**
 * Classe que efetua a replicação.
 * @author Juan.Damasceno
 *
 */
public abstract class AbstractReplicacaoCommand implements ReplicacaoCommand {

	/** O atributo logger. */
	private ISicoobLogger logger = SicoobLoggerPadrao.getInstance(getClass());

	/** O atributo pessoaDelegate. */
	private PessoaDelegate pessoaDelegate;
	
	/** O atributo pessoaCompartilhamentoDelegate. */
	private PessoaCompartilhamentoDelegate pessoaCompartilhamentoDelegate;
	
	/** O atributo transicaoPessoaDelegate. */
	private TransicaoPessoaDelegate transicaoPessoaDelegate;
	
	/** O atributo transicaoReplicacaoDelegate. */
	private TransicaoReplicacaoDelegate transicaoReplicacaoDelegate;
	
	/** O atributo responsavelCadastroDelegate. */
	private ResponsavelCadastroDelegate responsavelCadastroDelegate;

	/**
	 * Método que implementa a lógica da replicação.
	 * @param transicaoReplicacao a instancia de TransicaoReplicacao que será replicada na base do 
	 * DB2
	 * @param e a exceção lançada durante a replicação.
	 */
	protected abstract void doExecute(TransicaoReplicacao transicaoReplicacao)
			throws BancoobException;
	
	/**
	 * Método que executa a tarefa do command, delega a lógica de replicação chamando o 
	 * {@link #doExecute(TransicaoReplicacao)}, é reponsável por marcar o registro replicado como  
	 * já processado e efetua o tratamento das exceções.   
	 * @param transicaoReplicacao a instancia de TransicaoReplicacao que será replicada na base do 
	 * DB2.
	 */
	public void execute(TransicaoReplicacao transicaoReplicacao) {

		logger.info("Replicando dados do CPF/CNPJ: ", transicaoReplicacao.getCpfCnpj(),
				", Instituicao: ", transicaoReplicacao.getIdInstituicao().toString()
				,", Tipo operacao: ", transicaoReplicacao.getTipoOperacao().toString());

		try {
			atribuirCompartilhamentoCadastro(transicaoReplicacao);
			doExecute(transicaoReplicacao);
		} catch (BancoobException e) {
			registrarErro(transicaoReplicacao, e);
		}
		atualizar(transicaoReplicacao);
	}

	/**
	 * O método Atribuir compartilhamento cadastro.
	 *
	 * @param transicaoReplicacao o valor de transicao replicacao
	 */
	private void atribuirCompartilhamentoCadastro(TransicaoReplicacao transicaoReplicacao) {
		
		Short codCompartilhamentoCadastro = transicaoReplicacao.getCodCompartilhamentoCadastro();
		InformacoesUsuarioCAPES informacoesUsuarioCAPES =
				new InformacoesUsuarioCAPES(codCompartilhamentoCadastro, transicaoReplicacao
						.getIdInstituicao().toString());
		InformacoesUsuarioCAPES.setInstance(informacoesUsuarioCAPES);
		
		logger.info("Replicando transicao do Compartilhamento Cadastro ",
				codCompartilhamentoCadastro.toString());
	}

	/**
	 * Registra o erro que possa ocorrer.
	 * @param transicaoReplicacao a entidade que não pôde ser replicada.
	 * @param e a exceção lançada durante a replicação.
	 */
	private void registrarErro(TransicaoReplicacao transicaoReplicacao, Throwable e) {
		logger.erro(e, "Erro replicando dados da pessoa " + transicaoReplicacao.getIdPessoa() 
				+ ", tipo operacao: " + transicaoReplicacao.getTipoOperacao());
		
		transicaoReplicacao.setDescricaoErro(e.getMessage());
	}

	/**
	 * Atualiza a data de processamento de TransicaoReplicacao e chama o TransicaoReplicacaoDelegate 
	 * para salvar as alterações na entidade.
	 * @param transicaoReplicacao a instancia de TransicaoReplicacao que será atualizada.
	 */
	private void atualizar(
			TransicaoReplicacao transicaoReplicacao)  {
		try {
			transicaoReplicacao.setDataProcessamento(DataUtil.obterDataAtual());
			getTransicaoReplicacaoDelegate().alterar(transicaoReplicacao);
		} catch (BancoobException e) {
			logger.erro(e, "Erro atualizando TransicaoReplicacao.");
		}
	}

	/**
	 * Cria uma instancia de TransicaoPessoa.
	 * @param transicaoReplicacao instancia de TransicaoReplicacao.
	 * @param pessoa instancia Pessoa.
	 * @return uma instancia de TransicaoPessoa.
	 */
	protected TransicaoPessoa criarTransicaoPessoa(
			TransicaoReplicacao transicaoReplicacao, PessoaCompartilhamento pessoa) {
		TransicaoPessoa transicaoPessoa = new TransicaoPessoa();

		Instituicao instituicao = new Instituicao();
		instituicao.setIdInstituicao(transicaoReplicacao.getIdInstituicao());
		instituicao.setIdUnidadeInst(transicaoReplicacao.getIdUnidade());

		transicaoPessoa.setDataHoraIntegracao(DataUtil.obterDataAtual());
		transicaoPessoa.setIdPessoaLegado(transicaoReplicacao.getIdPessoa());
		transicaoPessoa.setPessoaCompartilhamento(pessoa);
		transicaoPessoa.setInstituicao(instituicao);
		transicaoPessoa.setNomePessoaLegado(transicaoReplicacao.getNome());

		return transicaoPessoa;
	}

	/**
	 * Cria uma instância de Pessoa dependendo do tipo da pessoa replicada na entidade 
	 * TransicaoReplicacao. 
	 * @param transicaoReplicacao instância de TransicaoReplicacao usada para criar a Pessoa.
	 * @param pessoa 
	 * @return uma instância de PessoaFisica caso o tipoPessoa de transicaoReplicacao seja 0, 
	 * PessoaJuridica caso o tipoPessoa seja 1.
	 */
	protected PessoaCompartilhamento criarPessoaCompartilhamento(TransicaoReplicacao transicaoReplicacao, Pessoa pessoa) {

		FabricaPessoaCompartilhamento fabrica = FabricaPessoaCompartilhamento.obterFabricaPessoaCompartilhamento(
				transicaoReplicacao.getTipoPessoa());
		PessoaCompartilhamento pessoaCompartilhamento = fabrica.criarPessoaCompartilhamento(); 

		CompartilhamentoCadastro compartilhamentoCadastro = new CompartilhamentoCadastro();
		compartilhamentoCadastro.setCodigo(transicaoReplicacao.getCodCompartilhamentoCadastro());
		pessoaCompartilhamento.setCompartilhamento(compartilhamentoCadastro);
		pessoaCompartilhamento.setNomePessoa(transicaoReplicacao.getNome());
		pessoaCompartilhamento.setNomeCompleto(transicaoReplicacao.getNome());
		pessoaCompartilhamento.setPessoa(pessoa);
		
		PerfilCadastro perfilCadastro = new PerfilCadastro();
		perfilCadastro.setCodigo(TipoPerfilCadastroEnum.SIMPLES.getCodigo());
		pessoaCompartilhamento.setPerfilCadastro(perfilCadastro);
		
		return pessoaCompartilhamento;
	}
	
	
	/**
	 * Cria uma instância de TipoPessoa
	 * @param transicaoReplicacao usada para criar o TipoPessoa
	 * @return uma instância de TipoPessoa
	 */
	protected TipoPessoa criarTipoPessoa(TransicaoReplicacao transicaoReplicacao) {
		TipoPessoa tipoPessoa = new TipoPessoa();
		tipoPessoa.setCodTipoPessoa(transicaoReplicacao.getTipoPessoa());
		return tipoPessoa;
	}

	/**
	 * Lista a transicaoPessoa usando IdInstituicao, IdPessoa e IdUnidade como filtro.
	 * 
	 * @param transicaoReplicacao entidade de onde o filtro será extraído
	 * @return uma lista de TransicaoPessoa. 
	 */
	protected List<TransicaoPessoa> listaTransicoesPessoa(TransicaoReplicacao transicaoReplicacao) {
		
		TransicaoPessoa transicaoPessoa = new TransicaoPessoa();
		
		Instituicao instituicao = new Instituicao();
		instituicao.setIdInstituicao(transicaoReplicacao.getIdInstituicao());
		
		transicaoPessoa.setInstituicao(instituicao);
		transicaoPessoa.setIdPessoaLegado(transicaoReplicacao.getIdPessoa());
	
		return getTransicaoPessoaDelegate().listar(transicaoPessoa);
	}
	
	/**
	 * @param pessoaDelegate the pessoaDelegate to set
	 */
	public void setPessoaDelegate(PessoaDelegate pessoaDelegate) {
		this.pessoaDelegate = pessoaDelegate;
	}

	/**
	 * @return the pessoaDelegate
	 */
	public PessoaDelegate getPessoaDelegate() {
		return pessoaDelegate;
	}

	/**
	 * @return the transicaoPessoaDelegate
	 */
	public TransicaoPessoaDelegate getTransicaoPessoaDelegate() {
		return transicaoPessoaDelegate;
	}

	/**
	 * @return the transicaoReplicacaoDelegate
	 */
	public TransicaoReplicacaoDelegate getTransicaoReplicacaoDelegate() {
		return transicaoReplicacaoDelegate;
	}

	/**
	 * @param transicaoPessoaDelegate the transicaoPessoaDelegate to set
	 */
	public void setTransicaoPessoaDelegate(
			TransicaoPessoaDelegate transicaoPessoaDelegate) {
		this.transicaoPessoaDelegate = transicaoPessoaDelegate;
	}

	/**
	 * @param transicaoReplicacaoDelegate the transicaoReplicacaoDelegate to set
	 */
	public void setTransicaoReplicacaoDelegate(
			TransicaoReplicacaoDelegate transicaoReplicacaoDelegate) {
		this.transicaoReplicacaoDelegate = transicaoReplicacaoDelegate;
	}

	/**
	 * @return the responsavelCadastroDelegate
	 */
	public ResponsavelCadastroDelegate getResponsavelCadastroDelegate() {
		return responsavelCadastroDelegate;
	}

	/**
	 * Recupera o valor de pessoaCompartilhamentoDelegate.
	 *
	 * @return o valor de pessoaCompartilhamentoDelegate
	 */
	public PessoaCompartilhamentoDelegate getPessoaCompartilhamentoDelegate() {
		return pessoaCompartilhamentoDelegate;
	}

	/**
	 * Define o valor de pessoaCompartilhamentoDelegate.
	 *
	 * @param pessoaCompartilhamentoDelegate o novo valor de pessoaCompartilhamentoDelegate
	 */
	public void setPessoaCompartilhamentoDelegate(
			PessoaCompartilhamentoDelegate pessoaCompartilhamentoDelegate) {
		this.pessoaCompartilhamentoDelegate = pessoaCompartilhamentoDelegate;
	}

	/**
	 * @param responsavelCadastroDelegate the responsavelCadastroDelegate to set
	 */
	public void setResponsavelCadastroDelegate(
			ResponsavelCadastroDelegate responsavelCadastroDelegate) {
		this.responsavelCadastroDelegate = responsavelCadastroDelegate;
	}

	/**
	 * Recupera o valor de logger.
	 *
	 * @return o valor de logger
	 */
	protected ISicoobLogger getLogger() {
		return logger;
	}
	
}