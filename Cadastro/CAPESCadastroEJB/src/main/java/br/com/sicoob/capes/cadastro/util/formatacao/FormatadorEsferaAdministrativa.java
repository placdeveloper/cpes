package br.com.sicoob.capes.cadastro.util.formatacao;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.util.EsferaAdministrativaUtil;
import br.com.sicoob.capes.comum.util.formatacao.FormatadorBase;

/**
 * A Classe FormatadorEsferaAdministrativa.
 */
public class FormatadorEsferaAdministrativa extends FormatadorBase<Short> {

	
	/**
	 * {@inheritDoc}
	 */
	protected String doFormat(Short valor, String mascara) throws BancoobException {
		if (valor != null) {
			String nomeEsfera = EsferaAdministrativaUtil
					.obterNomeEsferaAdministrativa(Integer.valueOf(valor));
			return nomeEsfera != null ? nomeEsfera : "";
		}
		return "";
	}
}
