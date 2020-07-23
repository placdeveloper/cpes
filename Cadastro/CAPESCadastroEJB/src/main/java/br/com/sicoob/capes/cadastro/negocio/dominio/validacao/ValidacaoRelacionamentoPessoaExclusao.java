package br.com.sicoob.capes.cadastro.negocio.dominio.validacao;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.vigente.RelacionamentoPessoa;

/**
 * A Classe ValidacaoRelacionamentoPessoaExclusao.
 */
public class ValidacaoRelacionamentoPessoaExclusao extends ValidacaoRelacionamentoPessoa {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void validar(RelacionamentoPessoa entidade) throws BancoobException {
		validarProdutoBancoob(entidade);
	}
}