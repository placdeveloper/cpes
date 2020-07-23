package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.sicoob.capes.cadastro.negocio.servicos.GrupoTipoAnotacaoServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.GrupoTipoAnotacao;

public class GrupoTipoAnotacaoDelegate extends CAPESCadastroCrudDelegate<GrupoTipoAnotacao, GrupoTipoAnotacaoServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected GrupoTipoAnotacaoServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarGrupoTipoAnotacaoServico();
	}

}
