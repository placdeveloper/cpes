/*
 * SICOOB
 * 
 * IdadeUtil.java(br.com.sicoob.capes.comum.util.IdadeUtil)
 */
package br.com.sicoob.capes.comum.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import br.com.bancoob.util.DataUtil;

/**
 * @author Erico.Junior
 *
 */
public final class IdadeUtil {

	/** A Constante DIAS_NO_ANO. */
	private static final BigDecimal DIAS_NO_ANO = new BigDecimal("365.25");
	
	/** A Constante MAIOR_IDADE. */
	private static final Integer MAIOR_IDADE = 18;
	
	/**
	 * Cria uma nova instância de idade util.
	 */
	private IdadeUtil() {
	}
	
	/**
	 * Obter idade.
	 * 
	 * @param dataNascimento
	 *            the data nascimento
	 * @return int
	 */
	public static int obterIdade(Date dataNascimento) {

		Date hoje = DataUtil.obterDataAtual();
		long diasVida = DataUtil.calcularDiasEntreDatas(dataNascimento, hoje);
		
		BigDecimal totalDias = new BigDecimal(diasVida);
		BigDecimal idade = totalDias.divide(DIAS_NO_ANO, RoundingMode.FLOOR);

		return idade.intValue();
	}	
	
	/**
	 * Verifica se é menor idade.
	 * 
	 * @param dataNascimento
	 *            the data nascimento
	 * @return true, se for menor idade
	 */
	public static boolean isMenorIdade(Date dataNascimento) {
		
		int idade = obterIdade(dataNascimento);
		return idade < MAIOR_IDADE;
	}
}
