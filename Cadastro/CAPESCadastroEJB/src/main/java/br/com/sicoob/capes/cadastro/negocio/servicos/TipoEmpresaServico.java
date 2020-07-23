/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.TipoEmpresa;
import br.com.sicoob.capes.negocio.entidades.vigente.FonteRenda;

/**
 * Servi�o para os tipos de empresa.
 * 
 * @author Erico.Junior
 */
public interface TipoEmpresaServico extends CAPESCadastroCrudDominioServico<TipoEmpresa> {

	TipoEmpresa obterTipoEmpresaPorFaixaRendaAnual(FonteRenda fonteRenda) throws BancoobException;
}
