package br.com.sicoob.capes.cadastro.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.cadastro.negocio.vo.ArquivoExportacaoVO;
import br.com.sicoob.capes.negocio.entidades.DestinoExportacao;

/**
 * A Interface VisualizarExportacaoServico.
 */
public interface VisualizarExportacaoServico extends CAPESCadastroServico {

	/**
	 * Obtém o arquivo de exportação de acordo com a data do movimento e o destino da exportação.
	 * @param destino
	 * @param dataMovimento
	 * @return {@code ArquivoExportacaoVO} o arquivo da exportação.
	 * @throws BancoobException
	 */
	ArquivoExportacaoVO obterArquivoExportacao(DestinoExportacao destino, DateTimeDB dataMovimento) throws BancoobException;
	
	/**
	 * Obtém o arquivo de exportação de acordo com a data do movimento e o destino da exportação.
	 * @param destino
	 * @param dataMovimento
	 * @return {@code ArquivoExportacaoVO} o arquivo da exportação.
	 * @throws BancoobException
	 */
	ArquivoExportacaoVO obterArquivoExportacao(DestinoExportacao destino, DateTimeDB dataMovimento, boolean fechamentoAgendado) throws BancoobException;

}