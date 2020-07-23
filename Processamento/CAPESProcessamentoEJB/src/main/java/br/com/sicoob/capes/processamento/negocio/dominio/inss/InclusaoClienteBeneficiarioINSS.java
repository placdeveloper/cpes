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
	 * Recupera uma instância única de InclusaoClienteBeneficiarioINSS.
	 * 
	 * @return uma intância única InclusaoClienteBeneficiarioINSS
	 */
	public static InclusaoClienteBeneficiarioINSS getInstance() {
		return instance;
	}

	/**
	 * Cria uma nova instância de inclusao cliente beneficiario inss.
	 */
	private InclusaoClienteBeneficiarioINSS() {
	}
	
	/**
	 * Inclui o beneficiário como cliente, caso ainda não esteja cadastrado como tal.
	 * @param dto A dto com os dados do beneficiário.
	 * @param pessoa A pessoa cadastrada no capes.
	 * @throws ClienteException Caso ocorra alguma exceção.
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
	 * Inclui uma pessoa instituição.
	 * @param pessoaFisica A pessoa física que vai ser incluída.
	 * @param instituicao A instituição.
	 * @param dataProduto A data do produto.
	 * @throws BancoobException Caso ocorra alguma exceção.
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
		pessoaInstituicao.setParecerGerencia("NÃO INFORMADO");
		pessoaInstituicao.setEmiteAvisoLancamento(false);
		pessoaInstituicao.setNucleo(recuperarNucleo(instituicao));
		pessoaInstituicao.setDataEnquadramentoRisco(dataProduto);
		pessoaInstituicao.setNivelRisco("AA");
		pessoaInstituicao.setMotivoRisco("NÃO INFORMADO");
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
	 * Recupera um gerente para a instituição.
	 * @param instituicao A instituição da qual se deseja um gerente.
	 * @return um gerente para a instituição.
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
	 * Recupera o núcleo "Nenhum" na instituição.
	 * @param instituicao A instituição da qual se deseja um núcleo.
	 * @return um núcleo para a instituição.
	 * @throws BancoobException Caso ocorra alguma exceção.
	 */
	private Nucleo recuperarNucleo(Instituicao instituicao) throws BancoobException {
		return nucleoDelegate.consultarNucleoNenhum(instituicao);
	}
	
	/**
	 * Verifica se o beneficiário já é cliente.
	 * @param pessoaFisica A pessoa a ser verificada.
	 * @param instituicao A instituição na qual a pessoa será verificada se é cliente.
	 * @return boolean indicando se a pessoa é cliente na instituição ou não.
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
			SicoobLoggerPadrao.getInstance(this.getClass()).info("A pessoa nao é cliente na instituicao.");
		}
		
		return pessoaInstituicao != null;
	}

}
