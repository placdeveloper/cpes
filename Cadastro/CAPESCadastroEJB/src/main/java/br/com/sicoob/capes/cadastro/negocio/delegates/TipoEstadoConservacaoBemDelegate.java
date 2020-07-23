package br.com.sicoob.capes.cadastro.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.servicos.TipoEstadoConservacaoBemServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.bem.TipoEstadoConservacaoBem;

/**
 * Delegate com os servi�os de tipo estado de conserva��o do bem.
 *
 * @author Bruno.Carneiro
 */
public class TipoEstadoConservacaoBemDelegate extends CAPESCadastroCrudDelegate<TipoEstadoConservacaoBem, TipoEstadoConservacaoBemServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TipoEstadoConservacaoBemServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarTipoEstadoConservacaoBemServico();
	}

	/**
	 * Obt�m os tipos de estado de conserva��o por tipo de bem.
	 * 
	 * @param bemImovel
	 * @return
	 * @throws BancoobException
	 */
	public List<TipoEstadoConservacaoBem> listar(boolean bemImovel) throws BancoobException {
		return getServico().listar(bemImovel);
	}
}