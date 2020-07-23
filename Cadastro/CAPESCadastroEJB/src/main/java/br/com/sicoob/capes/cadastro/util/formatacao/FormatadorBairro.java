package br.com.sicoob.capes.cadastro.util.formatacao;

import org.apache.commons.lang.math.NumberUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.comum.negocio.vo.LocalidadeVO;
import br.com.sicoob.capes.comum.util.formatacao.FormatadorBase;
import br.com.sicoob.capes.integracao.negocio.delegates.CAPESIntegracaoFabricaDelegates;
import br.com.sicoob.capes.integracao.negocio.delegates.LOCIntegracaoDelegate;

public class FormatadorBairro extends FormatadorBase<Integer> {

	@Override
	protected String doFormat(Integer valor, String mascara) throws BancoobException {
		String retorno = "";

		if (valor != null && !NumberUtils.INTEGER_ZERO.equals(valor)) {
			LOCIntegracaoDelegate delegate = CAPESIntegracaoFabricaDelegates.getInstance().criarLOCIntegracaoDelegate();
			LocalidadeVO localidade = delegate.pesquisarLocalidadePorIdLogradouro(valor);

			if (localidade != null) {
				retorno = localidade.getNomeBairro();
			}
		}
		return retorno;
	}
}