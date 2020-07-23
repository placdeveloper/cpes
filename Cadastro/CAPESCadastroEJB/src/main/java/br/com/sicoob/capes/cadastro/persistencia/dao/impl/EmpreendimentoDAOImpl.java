/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import javax.persistence.Query;

import org.apache.commons.lang.math.NumberUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.capes.cadastro.persistencia.dao.EmpreendimentoDAO;
import br.com.sicoob.capes.negocio.entidades.Empreendimento;

/**
 * @author erico.junior
 * 
 */
public class EmpreendimentoDAOImpl extends
		CAPESCadastroCrudDao<Empreendimento> implements
		EmpreendimentoDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISA_EMPREENDIMENTO";

	/** A constante PESQUISA_ID_EMPREENDIMENTO. */
	private static final String PESQUISA_ID_EMPREENDIMENTO = "PESQUISA_ID_EMPREENDIMENTO";

	/**
	 * Instancia um novo EmpreendimentoDAOImpl.
	 */
	public EmpreendimentoDAOImpl() {
		super(Empreendimento.class, NOME_COMANDO_PESQUISAR);
	}

	/**
	 * {@inheritDoc}
	 */
	public Integer pesquisarProximoCodigo() throws BancoobException {

		Integer id = null;

		Comando comando = getComando(PESQUISA_ID_EMPREENDIMENTO);
		comando.configurar();
		try {
			Query query = comando.criarQuery(getEntityManager());
			id = (Integer) query.getSingleResult();
		}  finally {
			fecharComando(comando);
		}
		return id == null ? NumberUtils.INTEGER_ONE : id;
	}

}
