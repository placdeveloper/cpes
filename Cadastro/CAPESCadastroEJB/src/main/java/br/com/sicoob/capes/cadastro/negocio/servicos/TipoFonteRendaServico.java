/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.TipoFonteRenda;
import br.com.sicoob.capes.negocio.entidades.TipoPessoa;

/**
 * Define as operações do serviço de manipulação de tipo de fontes de renda. 
 * @author Erico.Junior
 */
public interface TipoFonteRendaServico extends
		CAPESCadastroCrudDominioServico<TipoFonteRenda> {

	/**
	 * Lista os tipos de fonte de renda associados ao tipo da pessoa.
	 * @param tipoPessoa O tipo da pessoa utilizado na consulta.
	 * @return Lista dos tipos de fontes de renda associados ao tipo da pessoa
	 */
	List<TipoFonteRenda> listarPorTipoPessoa(TipoPessoa tipoPessoa) throws BancoobException;
}
