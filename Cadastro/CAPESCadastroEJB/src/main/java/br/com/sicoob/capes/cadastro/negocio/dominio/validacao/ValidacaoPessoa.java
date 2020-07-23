/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.dominio.validacao;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.validacao.ResultadoValidacao;
import br.com.bancoob.validacao.ValidacaoCnpj;
import br.com.bancoob.validacao.ValidacaoCpf;
import br.com.sicoob.capes.cadastro.negocio.excecao.FormatoInvalidoException;
import br.com.sicoob.capes.comum.negocio.enums.TipoPessoaEnum;
import br.com.sicoob.capes.negocio.entidades.Pessoa;
import br.com.sicoob.capes.negocio.entidades.TipoPessoa;

/**
 * Validação para Pessoas.
 * 
 * @author erico.junior
 */
public class ValidacaoPessoa implements Validacao<Pessoa> {

	/**
	 * {@inheritDoc}
	 */
	public void validar(Pessoa pessoa) throws BancoobException {

		validarCpfCnpj(pessoa.getCpfCnpj(), pessoa.getTipoPessoa());
		
	}

	/**
	 * Valida se o cpf ou cnpj informado é válido.
	 * 
	 * @param cpfCnpj
	 *            O cpf ou cnpj para validação
	 * @param tipoPessoa
	 *            O tipo da pessoa para o documento.
	 * @throws FormatoInvalidoException
	 *             Caso o documento seja inválido.
	 */
	private void validarCpfCnpj(String cpfCnpj, TipoPessoa tipoPessoa)
			throws FormatoInvalidoException {

		br.com.bancoob.validacao.Validacao validacao = null;

		if (TipoPessoaEnum.isPessoaFisica(tipoPessoa.getCodTipoPessoa())) {
			validacao = new ValidacaoCpf(cpfCnpj, "", "CPF");
		} else {
			validacao = new ValidacaoCnpj(cpfCnpj, "", "CNPJ");
		}

		ResultadoValidacao resultado = validacao.executar();
		if (!resultado.isValido()) {
			throw new FormatoInvalidoException(resultado.getParametrosMensagem()[0]);
		}

	}
	
}
