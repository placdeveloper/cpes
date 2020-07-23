package br.com.sicoob.capes.cadastro.util.formatacao;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.comum.util.formatacao.FormatadorBooleano;

/**
 * Formatador para a forma de cálculo da fonte de renda
 * 
 * @see br.com.sicoob.capes.negocio.entidades.vigente.FonteRenda#getBolRendaFixa()
 * 
 * @author Rodrigo.Chaves
 */
public class FormatadorFormaCalculo extends FormatadorBooleano {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String doFormat(Boolean valor, String mascara) throws BancoobException {
		
		return valor ? "FIXA" : "VARIÁVEL";
	}

}
