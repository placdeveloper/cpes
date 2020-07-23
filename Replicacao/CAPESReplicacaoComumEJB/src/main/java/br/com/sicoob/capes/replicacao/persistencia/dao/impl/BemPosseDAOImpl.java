/**
 * 
 */
package br.com.sicoob.capes.replicacao.persistencia.dao.impl;

import java.io.Serializable;

import javax.persistence.Query;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.capes.negocio.entidades.legado.Bem;
import br.com.sicoob.capes.negocio.entidades.legado.BemPosse;
import br.com.sicoob.capes.negocio.entidades.legado.pk.BemPossePK;
import br.com.sicoob.capes.persistencia.CAPESEntidadesLegadoDatasource;
import br.com.sicoob.capes.replicacao.persistencia.dao.BemPosseDAO;
import br.com.sicoob.capes.replicacao.persistencia.dao.EntidadeReplicavelDao;

/**
 * Dao utilizada para as referências das pessoas.
 * 
 * @author Erico.Junior
 */
public class BemPosseDAOImpl extends EntidadeReplicavelDao<BemPosse> implements
		BemPosseDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "";
	
	/** A constante NOME_COMANDO_CONSULTAR_IDDB2. */
	private static final String NOME_COMANDO_CONSULTAR_IDDB2 = "CONSULTAR_BEM_POSSE_IDDB2";
	
	/** A constante CONSULTA_BEM_POSSE_POR_ID. */
	private static final String CONSULTA_BEM_POSSE_POR_ID = "CONSULTA_BEM_POSSE_POR_ID";

	/**
	 * Instancia um novo BemPosseDAOImpl.
	 */
	public BemPosseDAOImpl() {
		super(BemPosse.class, NOME_COMANDO_PESQUISAR, NOME_COMANDO_CONSULTAR_IDDB2);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public BemPosse obter(Serializable chave, Integer numeroCooperativa)
			throws BancoobException {
		CAPESEntidadesLegadoDatasource.definirNumeroCooperativa(numeroCooperativa);
		
		BemPosse retorno = null;
		
		if(chave instanceof BemPossePK){
			BemPossePK pk = (BemPossePK) chave;
			
			Comando comando = getComando(CONSULTA_BEM_POSSE_POR_ID);
			comando.adicionarVariavel("idBem", pk.getBem().getId());
			comando.adicionarVariavel("seqBemPosse", pk.getSeqBemPosse());
			
			comando.configurar();
			
			Query query = comando.criarQuery(getEntityManager());
			
			retorno = (BemPosse) query.getSingleResult();
		}
		
		return retorno;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Short obterMaxSequencial(Bem bem) {
		
		Comando comando = getComando("CONSULTA_MAX_SEQ_POSSE");
		comando.adicionarVariavel("bem", bem);
		comando.configurar();
		Query query = criarQuery(comando);
		
		Short max = (Short) query.getSingleResult();
		if (max == null) {
			max = 0;
		}
		return max;
	}
}