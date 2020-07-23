/*
 * SICOOB
 * 
 * InclusaoClienteBeneficiarioINSS.java(br.com.sicoob.capes.processamento.negocio.dominio.inss.InclusaoClienteBeneficiarioINSS)
 */
package br.com.sicoob.capes.processamento.negocio.dominio.inss;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.FuncionarioDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.NucleoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.PerfilTarifarioDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.PessoaInstituicaoDelegate;
import br.com.sicoob.capes.cadastro.negocio.excecao.RegistroNaoEncontradoNegocioException;
import br.com.sicoob.capes.negocio.entidades.Funcionario;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.Nucleo;
import br.com.sicoob.capes.negocio.entidades.PerfilTarifario;
import br.com.sicoob.capes.negocio.entidades.pk.PerfilTarifarioPK;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaFisica;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaInstituicao;
import br.com.sicoob.capes.processamento.negocio.dto.BeneficiarioDTO;
import br.com.sicoob.capes.processamento.negocio.excecao.ClienteException;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;

/**
 * @author Erico.Junior
 *
 */
public final class InclusaoClienteBeneficiarioINSS {

	/** A Constante CODIGO_PERFIL_TARIFARIO. */
	private static final int CODIGO_PERFIL_TARIFARIO = 11;

	/** O atributo instance. */
	private static InclusaoClienteBeneficiarioINSS instance = new InclusaoClienteBeneficiarioINSS();
	
	/** O atributo fabrica. */
	private final transient CAPESCadastroFabricaDelegates fabrica = 
			CAPESCadastroFabricaDelegates.getInstance();
	
	/** O atributo pessoa instituicao delegate. */
	private final transient PessoaInstituicaoDelegate pessoaInstituicaoDelegate = fabrica.criarPessoaInstituicaoDelegate();
	
	/** O atributo funcionario delegate. */
	private final transient FuncionarioDelegate funcionarioDelegate = fabrica.criarFuncionarioDelegate();
	
	/** O atributo nucleo delegate. */
	private final transient NucleoDelegate nucleoDelegate = fabrica.criarNucleoDelegate();
	
	/** O atributo perfil delegate. */
	private final transient PerfilTarifarioDelegate perfilDelegate = fabrica.criarPerfilTarifarioDelegate();
	
	/**
	 * Recupera uma inst�ncia �nica de InclusaoClienteBeneficiarioINSS.
	 * 
	 * @return uma int�ncia �nica InclusaoClienteBeneficiarioINSS
	 */
	public static InclusaoClienteBeneficiarioINSS getInstance() {
		return instance;
	}

	/**
	 * Cria uma nova inst�ncia de inclusao cliente beneficiario inss.
	 */
	private InclusaoClienteBeneficiarioINSS() {
	}
	
	/**
	 * Inclui o benefici�rio como cliente, caso ainda n�o esteja cadastrado como tal.
	 * @param dto A dto com os dados do benefici�rio.
	 * @param pessoa A pessoa cadastrada no capes.
	 * @throws ClienteException Caso ocorra alguma exce��o.
	 */
	public void incluir(BeneficiarioDTO dto, PessoaFisica pessoaFisica, DateTimeDB dataProduto)throws ClienteException {

		try {
			Instituicao instituicao = INSSUtil.obterInstituicao(dto);

			if(!existePessoaInstituicao(pessoaFisica, instituicao)) {
				incluirPessoaInstituicao(pessoaFisica, instituicao, dataProduto);
			}
		} catch (BancoobException e) {
			throw new ClienteException(e);
		}
	}
	

	/**
	 * Inclui uma pessoa institui��o.
	 * @param pessoaFisica A pessoa f�sica que vai ser inclu�da.
	 * @param instituicao A institui��o.
	 * @param dataProduto A data do produto.
	 * @throws BancoobException Caso ocorra alguma exce��o.
	 */
	private void incluirPessoaInstituicao(PessoaFisica pessoaFisica, Instituicao instituicao, DateTimeDB dataProduto) 
			throws BancoobException {
		
		Funcionario gerente = recuperarGerente(instituicao);
		PerfilTarifario perfil = obterPerfilTarifario(instituicao.getIdInstituicao());
		
		PessoaInstituicao pessoaInstituicao = new PessoaInstituicao();
		pessoaInstituicao.setPessoa(pessoaFisica.getPessoa());
		pessoaInstituicao.setIdInstituicao(instituicao.getIdInstituicao());
		pessoaInstituicao.setIdUnidadeInst(instituicao.getIdUnidadeInst());
		pessoaInstituicao.setFuncionario(gerente);
		pessoaInstituicao.setPerfilTarifario(perfil);
		pessoaInstituicao.setParecerGerencia("N�O INFORMADO");
		pessoaInstituicao.setEmiteAvisoLancamento(false);
		pessoaInstituicao.setNucleo(recuperarNucleo(instituicao));
		pessoaInstituicao.setDataEnquadramentoRisco(dataProduto);
		pessoaInstituicao.setNivelRisco("AA");
		pessoaInstituicao.setMotivoRisco("N�O INFORMADO");
		pessoaInstituicao.setGravarHistorico(false);
		pessoaInstituicaoDelegate.incluir(pessoaInstituicao, instituicao, false);
	}	
	
	/**
	 * //vi.IdPerfilTarifario=11; 
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException 
	 */
	private PerfilTarifario obterPerfilTarifario(Integer idInstituicao) throws BancoobException {

		PerfilTarifarioPK chave = new PerfilTarifarioPK();
		chave.setCodPerfilTarifario(CODIGO_PERFIL_TARIFARIO);
		chave.setIdInstituicao(idInstituicao);

		return perfilDelegate.obter(chave);
	}
	
	/**
	 * Recupera um gerente para a institui��o.
	 * @param instituicao A institui��o da qual se deseja um gerente.
	 * @return um gerente para a institui��o.
	 */
	private Funcionario recuperarGerente(Instituicao instituicao) {
		
		List<Funcionario> funcionarios = funcionarioDelegate.listarGerentesAtivos(instituicao);
		
		Funcionario gerente = null;
		if(funcionarios != null && !funcionarios.isEmpty()) {
			gerente = funcionarios.get(0);
		}

		return gerente;
	}
	
	/**
	 * Recupera o n�cleo "Nenhum" na institui��o.
	 * @param instituicao A institui��o da qual se deseja um n�cleo.
	 * @return um n�cleo para a institui��o.
	 * @throws BancoobException Caso ocorra alguma exce��o.
	 */
	private Nucleo recuperarNucleo(Instituicao instituicao) throws BancoobException {
		return nucleoDelegate.consultarNucleoNenhum(instituicao);
	}
	
	/**
	 * Verifica se o benefici�rio j� � cliente.
	 * @param pessoaFisica A pessoa a ser verificada.
	 * @param instituicao A institui��o na qual a pessoa ser� verificada se � cliente.
	 * @return boolean indicando se a pessoa � cliente na institui��o ou n�o.
	 */
	private boolean existePessoaInstituicao(PessoaFisica pessoaFisica, Instituicao instituicao) {
		
		PessoaInstituicao pessoaInstituicao = null;
		
		try {
		
			PessoaInstituicao filtro = new PessoaInstituicao();
			filtro.setIdInstituicao(instituicao.getIdInstituicao());
			filtro.setIdUnidadeInst(instituicao.getIdUnidadeInst());
			filtro.setPessoa(pessoaFisica.getPessoa());

			pessoaInstituicao = pessoaInstituicaoDelegate.obterPorPessoaInstituicao(filtro);
		} catch (RegistroNaoEncontradoNegocioException e) {
			SicoobLoggerPadrao.getInstance(this.getClass()).info("A pessoa nao � cliente na instituicao.");
		}
		
		return pessoaInstituicao != null;
	}

}
