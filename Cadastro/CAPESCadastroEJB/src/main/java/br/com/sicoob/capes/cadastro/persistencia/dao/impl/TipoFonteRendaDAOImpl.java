/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.negocio.entidades.TipoFonteRenda;
import br.com.sicoob.capes.negocio.entidades.TipoPessoa;
import br.com.sicoob.capes.cadastro.persistencia.dao.TipoFonteRendaDAO;

/**
 * Dao para os tipos de fonte de renda
 * 
 * @author Erico.Junior
 */
public class TipoFonteRendaDAOImpl extends CAPESCadastroCrudDominioDao<TipoFonteRenda>
		implements TipoFonteRendaDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISA_TIPO_FONTE_RENDA";

	/**
	 * Instancia um novo TipoFonteRendaDAOImpl.
	 */
	public TipoFonteRendaDAOImpl() {
		super(TipoFonteRenda.class, NOME_COMANDO_PESQUISAR, "PESQUISAR_PROXIMO_CODIGO_TIPO_FONTE_RENDA");
	}

	/**
	 * Lista os tipos de fonte de renda associados ao tipo da pessoa.
	 * @param tipoPessoa O tipo da pessoa utilizado na consulta.
	 * @return Lista dos tipos de fontes de renda associados ao tipo da pessoa
	 * @throws BancoobException 
	 */
	public List<TipoFonteRenda> listarPorTipoPessoa(TipoPessoa tipoPessoa) throws BancoobException {
		ConsultaDto<TipoFonteRenda> criterios = new ConsultaDto<TipoFonteRenda>();
		criterios.setFiltro(tipoPessoa);
		return super.listar(criterios);
	}

}
