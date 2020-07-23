/**
 * 
 */
package br.com.sicoob.capes.replicacao.persistencia.dao.impl;

import java.io.Serializable;

import javax.persistence.Query;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.capes.negocio.entidades.legado.Bem;
import br.com.sicoob.capes.negocio.entidades.legado.BemRegistro;
import br.com.sicoob.capes.negocio.entidades.legado.pk.BemRegistroPK;
import br.com.sicoob.capes.persistencia.CAPESEntidadesLegadoDatasource;
import br.com.sicoob.capes.replicacao.persistencia.dao.BemRegistroDAO;
import br.com.sicoob.capes.replicacao.persistencia.dao.EntidadeReplicavelDao;

/**
 * Dao utilizada para as referências das pessoas.
 * 
 * @author juan.damasceno
 */
public class BemRegistroDAOImpl extends EntidadeReplicavelDao<BemRegistro> implements
		BemRegistroDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "";
	
	/** A constante NOME_COMANDO_CONSULTAR_IDDB2. */
	private static final String NOME_COMANDO_CONSULTAR_IDDB2 = "CONSULTAR_BEM_REGISTRO_IDDB2";
	
	/** A constante CONSULTA_BEM_REGISTRO_POR_ID. */
	private static final String CONSULTA_BEM_REGISTRO_POR_ID = "CONSULTA_BEM_REGISTRO_POR_ID";

	/**
	 * Instancia um novo BemRegistroDAOImpl.
	 */
	public BemRegistroDAOImpl() {
		super(BemRegistro.class, NOME_COMANDO_PESQUISAR, NOME_COMANDO_CONSULTAR_IDDB2);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BemRegistro obter(Serializable chave, Integer numeroCooperativa)
			throws BancoobException {
		CAPESEntidadesLegadoDatasource.definirNumeroCooperativa(numeroCooperativa);
		
		BemRegistro retorno = null;
		
		if(chave instanceof BemRegistroPK){
			BemRegistroPK bemRegistroPK = (BemRegistroPK) chave;
			
			Comando comando = getComando(CONSULTA_BEM_REGISTRO_POR_ID);
			comando.adicionarVariavel("idBem", bemRegistroPK.getBem().getId());
			comando.adicionarVariavel("seqBemRegistro", bemRegistroPK.getSeqBemRegistro());
			
			comando.configurar();
			
			Query query = comando.criarQuery(getEntityManager());
			
			retorno = (BemRegistro) query.getSingleResult();
		}
		
		return retorno;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Short obterMaxSequencial(Bem bem) {
		
		Comando comando = getComando("CONSULTA_MAX_SEQ_REGISTRO");
		comando.adicionarVariavel("bem", bem);
		comando.configurar();
		Query query = criarQuery(comando);
		
		Short max = (Short) query.getSingleResult();
		if (max == null){
			max = 0;
		}
		return max;
	}
}