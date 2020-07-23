/**
 * 
 */
package br.com.sicoob.capes.replicacao.persistencia.dao.impl;

import java.io.Serializable;

import javax.persistence.Query;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.capes.negocio.entidades.legado.Bem;
import br.com.sicoob.capes.negocio.entidades.legado.BemOnus;
import br.com.sicoob.capes.negocio.entidades.legado.pk.BemOnusPK;
import br.com.sicoob.capes.persistencia.CAPESEntidadesLegadoDatasource;
import br.com.sicoob.capes.replicacao.persistencia.dao.BemOnusDAO;
import br.com.sicoob.capes.replicacao.persistencia.dao.EntidadeReplicavelDao;

/**
 * Dao utilizada para os onus do bem.
 * 
 * @author juan.damasceno
 */
public class BemOnusDAOImpl extends EntidadeReplicavelDao<BemOnus> implements
		BemOnusDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "";
	
	/** A constante NOME_COMANDO_CONSULTAR_IDDB2. */
	private static final String NOME_COMANDO_CONSULTAR_IDDB2 = "CONSULTAR_BEM_ONUS_IDDB2";
	
	/** A constante CONSULTA_BEM_ONUS_POR_ID. */
	private static final String CONSULTA_BEM_ONUS_POR_ID = "CONSULTA_BEM_ONUS_POR_ID";
	
	/**
	 * Instancia um novo BemOnusDAOImpl.
	 */
	public BemOnusDAOImpl() {
		super(BemOnus.class, NOME_COMANDO_PESQUISAR, NOME_COMANDO_CONSULTAR_IDDB2);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public BemOnus obter(Serializable chave, Integer numeroCooperativa)
			throws BancoobException {
		CAPESEntidadesLegadoDatasource.definirNumeroCooperativa(numeroCooperativa);
		
		BemOnus retorno = null;
		
		if(chave instanceof BemOnusPK){
			BemOnusPK bemOnusPK = (BemOnusPK) chave;
			
			Comando comando = getComando(CONSULTA_BEM_ONUS_POR_ID);
			comando.adicionarVariavel("idBem", "'"+bemOnusPK.getBem().getId()+"'");
			comando.adicionarVariavel("seqBemOnus", bemOnusPK.getSeqBemOnus());
			
			comando.configurar();
			
			Query query = comando.criarQuery(getEntityManager());
			
			retorno = (BemOnus) query.getSingleResult();
		}
		
		return retorno;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Short obterMaxSequencialPorPessoa(Bem bem) {
		
		Comando comando = getComando("CONSULTA_MAX_SEQ_ONUS");
		comando.adicionarVariavel("bem", bem);
		comando.configurar();
		Query query = criarQuery(comando);
		
		Integer max = (Integer) query.getSingleResult();
		
		if (max == null) {
			max = 0;
		}
		
		return max.shortValue();
	}
}