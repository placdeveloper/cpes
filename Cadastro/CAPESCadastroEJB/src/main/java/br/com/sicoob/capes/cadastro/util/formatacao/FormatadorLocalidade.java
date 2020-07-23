package br.com.sicoob.capes.cadastro.util.formatacao;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.util.IntegracaoUtil;
import br.com.sicoob.capes.comum.util.formatacao.FormatadorBase;
import br.com.sicoob.capes.integracao.negocio.delegates.CAPESIntegracaoFabricaDelegates;
import br.com.sicoob.capes.integracao.negocio.delegates.LOCIntegracaoDelegate;
import br.com.sicoob.capes.negocio.entidades.Localidade;
import br.com.sicoob.capes.negocio.entidades.UF;

/**
 * A Classe FormatadorLocalidade.
 */
public class FormatadorLocalidade extends FormatadorBase<Integer> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String doFormat(Integer valor, String mascara) throws BancoobException {
		String retorno = "";
		if (valor != null && !NumberUtils.INTEGER_ZERO.equals(valor)) {
			StringBuilder formatado = new StringBuilder();
			LOCIntegracaoDelegate locDelegate = CAPESIntegracaoFabricaDelegates.getInstance().criarLOCIntegracaoDelegate();
			Localidade localidade = IntegracaoUtil.converterLocalidade(locDelegate.obterLocalidade(valor));
			if (localidade != null) {
				formatado.append(localidade.getNome());
				UF uf = localidade.getUf();
				if ((uf != null) && StringUtils.isNotBlank(uf.getSiglaUF())) {
					formatado.append("-").append(uf.getSiglaUF());
				}
			}
			retorno = formatado.toString();
		}
		return retorno;
	}

}