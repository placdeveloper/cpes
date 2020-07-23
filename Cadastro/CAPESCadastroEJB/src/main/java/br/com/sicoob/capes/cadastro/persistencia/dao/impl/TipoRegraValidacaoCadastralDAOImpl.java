package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import br.com.sicoob.capes.cadastro.persistencia.dao.TipoRegraValidacaoCadastralDAO;
import br.com.sicoob.capes.negocio.entidades.TipoRegraValidacaoCadastral;

/**
 * DAO para acessoa aos dados da entidade {@code TipoRegraValidacaoCadastral}
 * 
 * @author Rodrigo.Chaves
 * @see TipoRegraValidacaoCadastral
 */
public class TipoRegraValidacaoCadastralDAOImpl extends
        ValidacaoCadastralBaseDAO<TipoRegraValidacaoCadastral> implements
        TipoRegraValidacaoCadastralDAO {

	/**
	 * Construtor
	 */
	public TipoRegraValidacaoCadastralDAOImpl() {
		super(TipoRegraValidacaoCadastral.class,
		        "PESQUISAR_TIPO_REGRA_VALIDACAO_CADASTRAL_POR_FILTRO");
	}
}
