package br.com.sicoob.capes.cadastro.util.formatacao;

import java.util.Set;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.comum.util.formatacao.FormatadorBase;
import br.com.sicoob.capes.negocio.entidades.Cidadania;

/**
 * A Classe FormatadorCidadania.
 */
public class FormatadorCidadania extends FormatadorBase<Set<Cidadania>> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String doFormat(Set<Cidadania> listaCidadania, String mascara) throws BancoobException {
		String retono = "";
		if(listaCidadania != null && !listaCidadania.isEmpty()) {
			StringBuilder formatado = new StringBuilder();
			for (Cidadania cidadania : listaCidadania) {
				formatado.append(cidadania.getNacionalidade().getDescricao() + "/");
			}
			retono = formatado.toString();
			retono = retono.substring(0, retono.length() - 1);
		}
		return retono;
	}
}
