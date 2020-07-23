package br.com.sicoob.capes.comum.arquivos.teste.util;

import org.apache.commons.lang.math.RandomUtils;

import br.com.sicoob.capes.comum.negocio.enums.TipoPessoaEnum;

/**
 * A Classe CpfCnpjUtil.
 */
public class CpfCnpjUtil {

	/** A constante pesoCPF. */
	private static final int[] pesoCPF = { 11, 10, 9, 8, 7, 6, 5, 4, 3, 2 };
	
	/** A constante pesoCNPJ. */
	private static final int[] pesoCNPJ = { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };
	
	/** Constante para o tamanho do cpf. */
	private static final int TAMANHO_CPF = 11;
	
	/** Constante para o tamanho do cpf. */
	private static final int TAMANHO_CNPJ = 14;

	/**
	 * Instancia um novo CpfCnpjUtil.
	 */
	private CpfCnpjUtil() {

	}

	/**
	 * Gerar cpf cnpj.
	 *
	 * @param codTipoPessoa o valor de cod tipo pessoa
	 * @return String
	 */
	public static String gerarCpfCnpj(Short codTipoPessoa) {

		int length = TipoPessoaEnum.isPessoaFisica(codTipoPessoa.shortValue()) ? TAMANHO_CPF : TAMANHO_CNPJ;

		StringBuilder cpfCnpj = new StringBuilder(length);
		for (int i = 0; i < (length - 2); i++) {
			cpfCnpj.append(RandomUtils.nextInt(10));
		}
		cpfCnpj.append(gerarDV(codTipoPessoa, cpfCnpj.toString()));
		return cpfCnpj.toString();
	}

	/**
	 * Gerar dv.
	 *
	 * @param codTipoPessoa o valor de cod tipo pessoa
	 * @param cpfCnpj o valor de cpf cnpj
	 * @return String
	 */
	private static String gerarDV(Short codTipoPessoa, String cpfCnpj) {
		String dv = null;
		if (TipoPessoaEnum.isPessoaFisica(codTipoPessoa.shortValue())) {
			dv = gerarDvCPF(cpfCnpj);
		} else {
			dv = gerarDvCNPJ(cpfCnpj);
		}
		return dv;
	}

	/**
	 * Calcular digito.
	 *
	 * @param str o valor de str
	 * @param peso o valor de peso
	 * @return int
	 */
	private static int calcularDigito(String str, int[] peso) {

		int soma = 0;
		for (int indice = str.length() - 1, digito; indice >= 0; indice--) {
			digito = Integer.parseInt(str.substring(indice, indice + 1));
			soma += digito * peso[peso.length - str.length() + indice];
		}
		soma = 11 - soma % 11;
		return soma > 9 ? 0 : soma;
	}

	/**
	 * Gerar dv cpf.
	 *
	 * @param cpf o valor de cpf
	 * @return String
	 */
	private static String gerarDvCPF(String cpf) {

		Integer digito1 = calcularDigito(cpf.substring(0, 9), pesoCPF);
		Integer digito2 = calcularDigito(cpf.substring(0, 9) + digito1, pesoCPF);
		return digito1.toString() + digito2.toString();
	}

	/**
	 * Gerar dv cnpj.
	 *
	 * @param cnpj o valor de cnpj
	 * @return String
	 */
	private static String gerarDvCNPJ(String cnpj) {

		Integer digito1 = calcularDigito(cnpj.substring(0, 12), pesoCNPJ);
		Integer digito2 = calcularDigito(cnpj.substring(0, 12) + digito1, pesoCNPJ);
		return digito1.toString() + digito2.toString();
	}

}
