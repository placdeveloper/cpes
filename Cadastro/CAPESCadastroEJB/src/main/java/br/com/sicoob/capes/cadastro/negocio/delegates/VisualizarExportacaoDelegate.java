package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.cadastro.negocio.servicos.VisualizarExportacaoServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.cadastro.negocio.vo.ArquivoExportacaoVO;
import br.com.sicoob.capes.negocio.entidades.DestinoExportacao;

/**
 * A Classe VisualizarExportacaoDelegate.
 */
public class VisualizarExportacaoDelegate extends CAPESCadastroDelegate<VisualizarExportacaoServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected VisualizarExportacaoServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarVisualizarExportacaoServico();
	}
	
	/**
	 * Obter arquivo exportacao.
	 *
	 * @param idDestino o valor de id destino
	 * @param dataMovimento o valor de data movimento
	 * @return ArquivoExportacaoVO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public ArquivoExportacaoVO obterArquivoExportacao(DestinoExportacao destino, DateTimeDB dataMovimento) throws BancoobException {
		return getServico().obterArquivoExportacao(destino, dataMovimento);
	}

	/**
	 * Obter arquivo exportacao.
	 *
	 * @param idDestino o valor de id destino
	 * @param dataMovimento o valor de data movimento
	 * @param fechamentoAgendado o valor de fechamento agendado
	 * @return ArquivoExportacaoVO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public ArquivoExportacaoVO obterArquivoExportacao(DestinoExportacao destino, DateTimeDB dataMovimento, boolean fechamentoAgendado) throws BancoobException {
		return getServico().obterArquivoExportacao(destino, dataMovimento, fechamentoAgendado);
	}
}