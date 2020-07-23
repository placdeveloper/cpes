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
	 * Obt�m o arquivo de exporta��o de acordo com a data do movimento e o destino da exporta��o.
	 * @param destino
	 * @param dataMovimento
	 * @return {@code ArquivoExportacaoVO} o arquivo da exporta��o.
	 * @throws BancoobException
	 */
	ArquivoExportacaoVO obterArquivoExportacao(DestinoExportacao destino, DateTimeDB dataMovimento) throws BancoobException;
	
	/**
	 * Obt�m o arquivo de exporta��o de acordo com a data do movimento e o destino da exporta��o.
	 * @param destino
	 * @param dataMovimento
	 * @return {@code ArquivoExportacaoVO} o arquivo da exporta��o.
	 * @throws BancoobException
	 */
	ArquivoExportacaoVO obterArquivoExportacao(DestinoExportacao destino, DateTimeDB dataMovimento, boolean fechamentoAgendado) throws BancoobException;

}