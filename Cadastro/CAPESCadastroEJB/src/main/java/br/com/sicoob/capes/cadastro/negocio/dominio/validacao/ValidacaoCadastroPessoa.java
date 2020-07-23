/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.dominio.validacao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.delegates.AnotacaoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.EnderecoCorrespondenciaDelegate;
import br.com.sicoob.capes.cadastro.negocio.excecao.CadastroIrregularReceitaException;
import br.com.sicoob.capes.cadastro.negocio.excecao.CampoDaPessoaNaoInformadoException;
import br.com.sicoob.capes.cadastro.negocio.excecao.CampoNaoInformadoException;
import br.com.sicoob.capes.negocio.entidades.EnderecoCorrespondencia;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * Validação para Pessoas.
 * 
 * @author juan.damasceno
 */
public abstract class ValidacaoCadastroPessoa<P extends PessoaCompartilhamento> implements Validacao<P> {

	/** A constante TIPO_PESSOA. */
	private static final String TIPO_PESSOA = "Tipo Pessoa";
	
	/** A constante DATA_INCLUSAO. */
	protected static final String DATA_INCLUSAO = "Data da Inclusão no Sistema";
	
	/** A constante DATA_ATUAL. */
	protected static final String DATA_ATUAL = "Data Atual";

	/**
	 * Recupera o valor de nomeAtributoNome.
	 *
	 * @return o valor de nomeAtributoNome
	 */
	protected abstract String getNomeAtributoNome();
	
	/**
	 * Recupera o valor de nomeAtributoNomeCompleto.
	 *
	 * @return o valor de nomeAtributoNomeCompleto
	 */
	protected abstract String getNomeAtributoNomeCompleto();
	
	/**
	 * Recupera o valor de nomeAtributoDocumento.
	 *
	 * @return o valor de nomeAtributoDocumento
	 */
	protected abstract String getNomeAtributoDocumento();

	/** O atributo instituicao. */
	private Instituicao instituicao;
	
	/** O atributo falhas. */
	private List<String> falhas;
	
	/** O atributo isAlteracaoInstituicao. */
	private boolean isAlteracaoInstituicao = false;
	
	/**
	 * Instancia um novo ValidacaoCadastroPessoa.
	 *
	 * @param instituicao o valor de instituicao
	 */
	public ValidacaoCadastroPessoa(Instituicao instituicao) {
		this.instituicao = instituicao;
	}
	
	/**
	 * O método Validar.
	 *
	 * @param pessoa o valor de pessoa
	 * @param isAlteracao o valor de is alteracao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public void validar(PessoaCompartilhamento pessoa, boolean isAlteracao) throws BancoobException {
		this.isAlteracaoInstituicao = isAlteracao;
		validar(pessoa);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public void validar(PessoaCompartilhamento pessoa) throws BancoobException {

		P pessoaTipada = (P) pessoa;
		
		if(!isAlteracaoInstituicao && !isCadastroReceitaRegular(pessoa)){
			throw new CadastroIrregularReceitaException();
		}

		validarObrigatoriedadeComum(pessoa);
		validarObrigatoriedadeCondicionalEspecifica(pessoaTipada);
		if (!getFalhas().isEmpty()) {
			throw new CampoDaPessoaNaoInformadoException(getFalhas().toArray(
					new String[getFalhas().size()]));
		}
	}

	/**
	 * Valida a obrigatoriedade dos campos da pessoa.
	 * 
	 * @param pessoa
	 *            A pessoa a ser verificada.
	 * @throws BancoobException 
	 */
	private void validarObrigatoriedadeComum(PessoaCompartilhamento pessoaCompartilhamento)	throws BancoobException {
		
		if (pessoaCompartilhamento.getPessoa().getTipoPessoa() == null) {
			adicionarFalha(TIPO_PESSOA);
		}
		if (StringUtils.isBlank(pessoaCompartilhamento.getNomePessoa())) {
			adicionarFalha(getNomeAtributoNome());
		}
		if (StringUtils.isBlank(pessoaCompartilhamento.getNomeCompleto())) {
			adicionarFalha(getNomeAtributoNomeCompleto());
		}
		if (StringUtils.isBlank(pessoaCompartilhamento.getPessoa().getCpfCnpj())) {
			adicionarFalha(getNomeAtributoDocumento());
		}
		if (pessoaCompartilhamento.getAtividadeEconomica() == null) {
			adicionarFalha("Atividade econômica");
		}

		EnderecoCorrespondencia endereco = consultarEnderecoCorrespondencia(pessoaCompartilhamento);

		if (endereco == null) {
			adicionarFalha("Endereço de correspondência");
		}
		
	}

	/**
	 * Consultar endereco correspondencia.
	 *
	 * @param pessoa o valor de pessoa
	 * @return EnderecoCorrespondencia
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private EnderecoCorrespondencia consultarEnderecoCorrespondencia(
			PessoaCompartilhamento pessoa) throws BancoobException {
		EnderecoCorrespondenciaDelegate enderecoCorrespondenciaDelegate = 
				CAPESCadastroFabricaDelegates.getInstance().criarEnderecoCorrespondenciaDelegate();
		
		return enderecoCorrespondenciaDelegate.consultar(pessoa, getInstituicao());
	}
	
	/**
	 * Verifica se eh cadastro receita regular.
	 *
	 * @param pessoaCompartilhamento o valor de pessoa compartilhamento
	 * @return {@code true}, se for cadastro receita regular
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private boolean isCadastroReceitaRegular(PessoaCompartilhamento pessoaCompartilhamento) throws BancoobException {
		AnotacaoDelegate anotacaoDelegate = CAPESCadastroFabricaDelegates.getInstance().criarAnotacaoDelegate();
		return anotacaoDelegate.isCadastroReceitaRegular(pessoaCompartilhamento);
	}
	
	/**
	 * Valida a obrigatoriedade condicional dos campos da pessoa.
	 * 
	 * @param pessoa
	 *            A pessoa a ser verificada.
	 * @throws CampoNaoInformadoException
	 *             Caso algum atributo obrigatório não tenha sido informado.
	 * @throws BancoobException 
	 */
	protected abstract void validarObrigatoriedadeCondicionalEspecifica(P pessoa)
			throws CampoNaoInformadoException, BancoobException;	
	
	/**
	 * Recupera a validação a ser utilizada para o documento dependendo do tipo de pessoa.
	 * @param cpfCnpj O número do documento a ser validado.
	 * @return a validação a ser utilizada para o documento dependendo do tipo de pessoa.
	 */
	protected abstract br.com.bancoob.validacao.Validacao obterValidacaoDocumento(String cpfCnpj);

	/**
	 * Recupera o valor de instituicao.
	 *
	 * @return o valor de instituicao
	 */
	protected Instituicao getInstituicao() {
		return this.instituicao;
	}
	
	/**
	 * Recupera o valor de falhas.
	 *
	 * @return o valor de falhas
	 */
	protected List<String> getFalhas() {
		if (falhas == null) {
			falhas = new ArrayList<String>();
		}
		return falhas;
	}
	
	/**
	 * O método Adicionar falha.
	 *
	 * @param nomeCampo o valor de nome campo
	 */
	protected void adicionarFalha(String nomeCampo) {
		getFalhas().add(nomeCampo);
	}
}
