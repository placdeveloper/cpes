/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.dominio.validacao;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.excecao.CampoNaoInformadoException;
import br.com.sicoob.capes.negocio.entidades.EntidadeCadastroBase;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * Superclasse para as validações das entidades do cadastro da pessoa.
 * 
 * @author Erico.Junior
 */
public abstract class ValidacaoEntidadeCadastroAbstract<E extends EntidadeCadastroBase>
		implements Validacao<E> {

	/**
	 * {@inheritDoc}
	 */
	public void validar(E entidade) throws BancoobException {

		validarObrigatoriedadePessoa(entidade);
		validarCamposObrigatorios(entidade);
		validarNegocio(entidade);
	}

	
	/**
	 * Valida se a pessoa informada.
	 * @param entidade A entidade do cadastro que está sendo validada.
	 * @throws CampoNaoInformadoException Caso a pessoa não tenha sido informada.
	 */
	protected void validarObrigatoriedadePessoa(E entidade) throws CampoNaoInformadoException {

		PessoaCompartilhamento pessoa = entidade.getPessoaCompartilhamento();
		if (pessoa == null || pessoa.getIdPessoaCompartilhamento() == null) {
			throw new CampoNaoInformadoException("Pessoa");
		}
	}

	/**
	 * Valida os dados da entidade. 
	 * @param entidade A entidade que está sendo validada.
	 * @throws CampoNaoInformadoException Caso algum campo obrigatório não tenha sido preenchido.
	 */
	protected abstract void validarCamposObrigatorios(E entidade) throws BancoobException;

	/**
	 * Valida o negócio relacionado a entidade. 
	 * @param entidade A entidade que está sendo validada.
	 * @throws BancoobException Caso ocorra alguma exceção.
	 */
	protected abstract void validarNegocio(E entidade) throws BancoobException;
	
}
