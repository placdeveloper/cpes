/**
 * 
 */
package br.com.sicoob.capes.replicacao.persistencia.dao.impl;

import javax.persistence.Query;

import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.capes.negocio.entidades.legado.Certidao;
import br.com.sicoob.capes.negocio.entidades.legado.Pessoa;
import br.com.sicoob.capes.replicacao.persistencia.dao.CertidaoDAO;
import br.com.sicoob.capes.replicacao.persistencia.dao.EntidadeReplicavelDao;

/**
 * Dao utilizada para as certidoes.
 * 
 * @author juan.damasceno
 */
public class CertidaoDAOImpl extends EntidadeReplicavelDao<Certidao> implements
		CertidaoDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISA_CERTIDAO_PESSOA";
	
	/** A constante NOME_COMANDO_CONSULTAR_IDDB2. */
	private static final String NOME_COMANDO_CONSULTAR_IDDB2 = "CONSULTA_CERTIDAO_POR_ID_DB2";

	/**
	 * Instancia um novo CertidaoDAOImpl.
	 */
	public CertidaoDAOImpl() {
		super(Certidao.class, NOME_COMANDO_PESQUISAR, NOME_COMANDO_CONSULTAR_IDDB2);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Short obterMaxSequencialPorPessoa(Pessoa pessoa) {
		
		Comando comando = getComando("CONSULTA_MAX_SEQ_CERTIDAO");
		comando.adicionarVariavel("pessoa", pessoa);
		comando.configurar();
		Query query = criarQuery(comando);
		
		Short max = (Short) query.getSingleResult();
		
		if (max == null) {
			max = 0;
		}
		
		max++;
		
		return max;
	}
}