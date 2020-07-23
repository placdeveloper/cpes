/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.dominio.validacao;

import org.apache.commons.lang.StringUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.validacao.Validacao;
import br.com.bancoob.validacao.ValidacaoCnpj;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaJuridica;

/**
 * Classe utilizada na valida��o dos dados de entrada de pessoa jur�dica.
 */
public class ValidacaoCadastroPessoaJuridica extends ValidacaoCadastroPessoa<PessoaJuridica> {

	/**
	 * Instancia um novo ValidacaoCadastroPessoaJuridica.
	 *
	 * @param instituicao o valor de instituicao
	 */
	public ValidacaoCadastroPessoaJuridica(Instituicao instituicao) {
		super(instituicao);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getNomeAtributoDocumento() {
		return "CNPJ";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getNomeAtributoNome() {
		return "Raz�o Social";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getNomeAtributoNomeCompleto() {
		return "Raz�o Social Completa";
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void validarObrigatoriedadeCondicionalEspecifica(PessoaJuridica pessoa)
			throws BancoobException {

		if (pessoa.getDataConstituicao() == null)  {
			adicionarFalha("Data de constitui��o");		
		}

		if (pessoa.getCodigoEsferaAdministrativa() == null) {
			adicionarFalha("Esfera administrativa");
		}

		if (StringUtils.isBlank(pessoa.getNumeroRegistroJuntaComercial())) {
			adicionarFalha("N�mero de registro em �rg�o competente");
		}

		if (pessoa.getDataRegistroJuntaComercial() == null) {
			adicionarFalha("Data de registro em �rg�o competente");
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Validacao obterValidacaoDocumento(String cpfCnpj) {
		return new ValidacaoCnpj(cpfCnpj, "", getNomeAtributoDocumento());
	}
}