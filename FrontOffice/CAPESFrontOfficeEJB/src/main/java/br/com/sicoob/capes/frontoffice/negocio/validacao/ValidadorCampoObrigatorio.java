/*
 * SICOOB
 * 
 * ValidadorCampoObrigatorio.java(br.com.sicoob.capes.frontoffice.negocio.validacao.ValidadorCampoObrigatorio)
 */
package br.com.sicoob.capes.frontoffice.negocio.validacao;

import java.util.Collection;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.frontoffice.negocio.excecao.CampoObrigatorioException;

/**
 * The Class ValidadorCampoObrigatorio.
 * 
 * @param <O>
 *            the generic type
 */
public class ValidadorCampoObrigatorio<O extends Object> extends ValidadorAbstract {

	/** O atributo valor. */
	private O valor;

	/**
	 * Cria uma nova instância de validador campo obrigatorio.
	 * 
	 * @param nomeCampo
	 *            the nome campo
	 * @param valor
	 *            the valor
	 */
	public ValidadorCampoObrigatorio(String nomeCampo, O valor) {

		super(nomeCampo);
		this.valor = valor;
	}

	/** 
	 * {@inheritDoc}
	 */
	public final void validar() throws BancoobException {
		if (!isValido()) {
			throw new CampoObrigatorioException(getNomeCampo());
		}
	}

	/**
	 * Verifica se é valido.
	 * 
	 * @return true, se for valido
	 */
	@SuppressWarnings("rawtypes")
	protected boolean isValido() {
		boolean valido = valor != null;
		if (valido) {
			if (valor instanceof String) {
				valido = StringUtils.isNotBlank((String) valor);
			} else if (valor instanceof Collection) { 
				valido = CollectionUtils.isNotEmpty((Collection) valor);
			} else if (valor instanceof Map) {
				valido = MapUtils.isNotEmpty((Map) valor);
			}
		}
		return valido;
	}
}
