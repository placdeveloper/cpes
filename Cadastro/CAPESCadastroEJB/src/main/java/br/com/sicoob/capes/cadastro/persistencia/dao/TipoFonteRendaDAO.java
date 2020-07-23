/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.TipoFonteRenda;
import br.com.sicoob.capes.negocio.entidades.TipoPessoa;

/**
 * Interface para o DAO de tipo de fonte de renda. 
 * @author Erico.Junior 
 */
public interface TipoFonteRendaDAO extends CAPESCadastroCrudDaoIF<TipoFonteRenda> {

	/**
	 * Lista os tipos de fonte de renda associados ao tipo da pessoa.
	 * @param tipoPessoa O tipo da pessoa utilizado na consulta.
	 * @return Lista dos tipos de fontes de renda associados ao tipo da pessoa
	 */
	List<TipoFonteRenda> listarPorTipoPessoa(TipoPessoa tipoPessoa) throws BancoobException;
}
