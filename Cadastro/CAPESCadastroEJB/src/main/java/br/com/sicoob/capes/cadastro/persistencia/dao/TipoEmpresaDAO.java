/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.TipoEmpresa;
import br.com.sicoob.capes.negocio.entidades.vigente.FonteRenda;

/**
 * DAO para os tipos de empresa.
 * 
 * @author Erico.Junior
 */
public interface TipoEmpresaDAO extends CAPESCadastroCrudDominioDaoIF<TipoEmpresa> {

	TipoEmpresa obterTipoEmpresaPorFaixaRendaAnual(FonteRenda fonteRenda) throws BancoobException;
}
