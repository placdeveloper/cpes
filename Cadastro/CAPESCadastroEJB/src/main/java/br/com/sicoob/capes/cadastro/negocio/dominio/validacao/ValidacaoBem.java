package br.com.sicoob.capes.cadastro.negocio.dominio.validacao;

import java.math.BigDecimal;

import org.apache.commons.collections.CollectionUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.excecao.CampoNaoInformadoException;
import br.com.sicoob.capes.cadastro.negocio.excecao.PercentualInvalidoException;
import br.com.sicoob.capes.negocio.entidades.bem.Bem;
import br.com.sicoob.capes.negocio.entidades.bem.BemPessoaCompartilhamento;

/**
 * Classe base para as classes de valida��o do {@code Bem}.
 * 
 * @author bruno.carneiro
 * 
 * @param <T>
 */
public abstract class ValidacaoBem<E extends Bem> extends ValidacaoEntidadeCadastroAbstract<E> {
	
	protected static final int TAMANHO_DESCRICAO = 1000;
	protected final boolean alteracao;
	
	/**
	 * M�todo construtror
	 */
	public ValidacaoBem() {
		this(false);
	}
	
	/**
	 * M�todo construtror
	 */
	public ValidacaoBem(boolean alteracao) {
		this.alteracao = alteracao;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void validar(E entidade) throws BancoobException {
		super.validar(entidade);
		validarPercentualPropriedade(entidade);
	}

	/**
	 * M�todo sobreescrito pois, nesse momento da valida��o o bem ainda n�o
	 * possui relacionamentos.
	 */
	@Override
	protected void validarObrigatoriedadePessoa(E entidade) throws CampoNaoInformadoException {
		return;
	}

	/**
	 * Valida se o bem possui 100% de posse.
	 * 
	 * @param entidade
	 * @throws BancoobException
	 */
	protected void validarPercentualPropriedade(E entidade) throws BancoobException {
		BigDecimal total = BigDecimal.ZERO.setScale(2);
		BigDecimal zero = BigDecimal.ZERO.setScale(2);

		if (CollectionUtils.isNotEmpty(entidade.getProprietarios())) {
			for (BemPessoaCompartilhamento bemPessoaCompartilhamento : entidade.getProprietarios()) {
				if (bemPessoaCompartilhamento.getPercentualProprietario() != null) {
					total = total.add(bemPessoaCompartilhamento.getPercentualProprietario());
				}
			}
		}

		// Se o bem for avan�ado, dever� possuir 100% de posse.
		if (isBemAvancado(entidade)) {
			if (!total.equals(zero) && (total.compareTo(new BigDecimal("100.00")) < 0 || total.compareTo(new BigDecimal("100.00")) > 0)) {
				throw new PercentualInvalidoException("propriet�rios", "igual a 100%");
			}
		// Se o bem n�o for avan�ado, a posse poder� ser menor que 100%
		} else if (!total.equals(zero) && total.compareTo(new BigDecimal("100.00")) > 0) {
			throw new PercentualInvalidoException("propriet�rios", "igual a 100%");
		}
	}
	
	/**
	 * Valida se o bem � do tipo avan�ado.
	 * 
	 * @param entidade
	 * @return
	 */
	protected abstract boolean isBemAvancado(E entidade);
	
}