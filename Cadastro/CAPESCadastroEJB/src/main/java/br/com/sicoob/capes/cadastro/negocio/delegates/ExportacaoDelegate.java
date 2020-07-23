package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.servicos.ExportacaoServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.DestinoExportacao;
import br.com.sicoob.capes.negocio.entidades.Exportacao;

/**
 * @since 1.2.1.0
 * @author rodrigo.chaves
 */
public class ExportacaoDelegate extends CAPESCadastroCrudDelegate<Exportacao, ExportacaoServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ExportacaoServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarExportacaoServico();
	}

	/**
	 * Obter numero sequencial.
	 *
	 * @param destino o valor de destino
	 * @return Short
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public Short obterNumeroSequencial(DestinoExportacao destino) throws BancoobException {
		return getServico().obterNumeroSequencial(destino);
	}

}