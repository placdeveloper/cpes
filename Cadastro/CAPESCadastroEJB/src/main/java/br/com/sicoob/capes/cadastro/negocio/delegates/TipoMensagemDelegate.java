package br.com.sicoob.capes.cadastro.negocio.delegates;

import java.util.List;

import br.com.sicoob.capes.cadastro.negocio.servicos.TipoMensagemServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.TipoMensagem;

/**
 * A Classe TipoMensagemDelegate.
 */
public class TipoMensagemDelegate extends CAPESCadastroCrudDelegate<TipoMensagem, TipoMensagemServico>  {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TipoMensagemServico localizarServico() {
		return  CAPESCadastroServiceLocator.getInstance().localizarTipoMensagemServico();
	}
	
	/**
	 * Lista de tipo mensagens do tipo destino exibicao.
	 *
	 * @param codTipoDestinoExibicao o valor de cod tipo destino exibicao
	 * @return List
	 */
	public List<TipoMensagem> listaDeTipoMensagensDoTipoDestinoExibicao(Short codTipoDestinoExibicao){
		return getServico().listaDeTipoMensagensDoTipoDestinoExibicao(codTipoDestinoExibicao);
	}

}
