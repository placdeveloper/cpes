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
	 * Obt�m o pr�ximo n�mero dispon�vel para o sequencial do arquivo.
	 * 
	 * @param destino
	 *            O destino da exporta��o
	 * @return O n�mero sequencial
	 * @throws BancoobException
	 */
	Short obterNumeroSequencial(DestinoExportacao destino) throws BancoobException;

}