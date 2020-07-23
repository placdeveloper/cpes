package br.com.sicoob.capes.cadastro.persistencia.dao;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.DestinoExportacao;
import br.com.sicoob.capes.negocio.entidades.Exportacao;

/**
 * @since 1.2.1.0
 * @author rodrigo.chaves
 */
public interface ExportacaoDAO extends CAPESCadastroCrudDaoIF<Exportacao> {

	/**
	 * Obtém o próximo número disponível para o sequencial do arquivo.
	 * 
	 * @param destino
	 *            O destino da exportação
	 * @return O número sequencial
	 * @throws BancoobException
	 */
	Short obterNumeroSequencial(DestinoExportacao destino) throws BancoobException;

}