package br.com.sicoob.capes.cadastro.util.formatacao;

import java.util.Set;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.comum.util.formatacao.FormatadorBase;
import br.com.sicoob.capes.negocio.entidades.EnderecoFiscal;

/**
 * A Classe FormatadorEnderecoFiscal.
 */
public class FormatadorEnderecoFiscal extends FormatadorBase<Set<EnderecoFiscal>> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String doFormat(Set<EnderecoFiscal> listaEnderecoFiscal, String mascara) throws BancoobException {
		String retono = "";
		if(listaEnderecoFiscal != null && !listaEnderecoFiscal.isEmpty()) {
			StringBuilder formatado = new StringBuilder();
			for (EnderecoFiscal cidadania : listaEnderecoFiscal) {
				formatado.append(cidadania.getNacionalidade().getDescricao() + "/");
			}
			retono = formatado.toString();
			retono = retono.substring(0, retono.length() - 1);
		}
		return retono;
	}

}
