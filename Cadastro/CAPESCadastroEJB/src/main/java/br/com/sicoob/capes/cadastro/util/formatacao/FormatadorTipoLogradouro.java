// 04/04/2013 - 08:36:35
package br.com.sicoob.capes.cadastro.util.formatacao;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.comum.negocio.vo.LOCIntegracaoTipoLogradouroVO;
import br.com.sicoob.capes.comum.util.formatacao.FormatadorBase;
import br.com.sicoob.capes.integracao.negocio.delegates.CAPESIntegracaoFabricaDelegates;

/**
 * @author Rodrigo.Chaves
 */
public class FormatadorTipoLogradouro extends FormatadorBase<Integer> {


	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected String doFormat(Integer valor, String mascara) throws BancoobException {
		
		CAPESIntegracaoFabricaDelegates fabrica =
				CAPESIntegracaoFabricaDelegates.getInstance();
		
		LOCIntegracaoTipoLogradouroVO tipoLogradouro =
				fabrica.criarLOCIntegracaoDelegate().obterTipoLogradouro(valor.shortValue());
		
		return tipoLogradouro == null ? "" : tipoLogradouro.getNome();
	}

}
