/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.servicos.ejb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.excecao.BancoobRuntimeException;
import br.com.sicoob.capes.negocio.entidades.legado.EntidadeReplicavel;
import br.com.sicoob.capes.replicacao.persistencia.dao.EntidadeReplicavelDaoIF;

/**
 * Superclasse para os serviços das entidades replicáveis no cadastro do
 * cliente.
 * 
 * @author erico.junior
 */
public abstract class EntidadeReplicavelServicoEJB<T extends EntidadeReplicavel<? extends Serializable>>
		extends CAPESReplicacaoComumCrudServicoEJB<T> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected abstract EntidadeReplicavelDaoIF<T> getDAO();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void alterar(T objeto, Integer idInstituicao) throws BancoobException {

		Serializable idSQL = obterIdSQL(objeto, idInstituicao);
		if (idSQL != null) {
			objeto.setIdSQL(idSQL);
			getLogger().debug("Alterando " + objeto.getClass() + ", idSQL: " + idSQL + ", idDB2:" + objeto.getIdDB2());
			super.alterar(objeto, idInstituicao);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void excluir(T objeto, Integer idInstituicao) throws BancoobException {

		Serializable idSQL = obterIdSQL(objeto, idInstituicao);
		if (idSQL != null) {
			objeto.setIdSQL(idSQL);
			getLogger().debug("Excluindo " + objeto.getClass() + ", idSQL: " + idSQL + ", idDB2:" + objeto.getIdDB2());
			super.excluir(objeto, idInstituicao);
		}
	}
	
	/**
	 * Obter por id d b2.
	 *
	 * @param entidade o valor de entidade
	 * @param idInstituicao o valor de id instituicao
	 * @return T
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public T obterPorIdDB2(T entidade, Integer idInstituicao) throws BancoobException {
		
		getLogger().debug("Obtendo entidade por idDB2: ", entidade.getClass().getName(), "(",
				String.valueOf(entidade.getIdDB2()), ")");
		
		T retorno = getDAO().obterPorIdDB2(entidade.getIdDB2(), obterNumeroCooperativa(idInstituicao));
		
		if(retorno == null) {
			throw new BancoobRuntimeException("Registro não encontrado por idDB2: "
					+ entidade.getClass().getName() + "(" + entidade.getIdDB2() + ")");
		}
		
		getLogger().debug("Entidade obtida por idDB2: ", entidade.getClass().getName(), "(",
				String.valueOf(entidade.getIdDB2()), ")");
		return retorno;
	}
	
	/**
	 * Recupera o identificador da entidade no sql a partir do identificador
	 * correspondente no DB2.
	 * 
	 * @param objeto
	 *            O objeto do qual se deseja o identificador no SQLServer.
	 * @param instituicao
	 *            A instituição.
	 * @return
	 * @throws BancoobException
	 */
	protected Serializable obterIdSQL(T objeto, Integer idInstituicao) throws BancoobException {

		Serializable idSQL = objeto.getIdSQL();
		Serializable idDB2 = objeto.getIdDB2();

		if ((idSQL == null) && (idDB2 != null)) {

			Integer numeroCooperativa = obterNumeroCooperativa(idInstituicao);
			T encontrada = getDAO().obterPorIdDB2(idDB2, numeroCooperativa);

			if (encontrada != null) {
				idSQL = encontrada.getIdSQL();
			}
		}

		getLogger().info("id da Entidade de replicação = " + idSQL);
		return idSQL;
	}
	
	/**
	 * Recupera o identificador da entidade no sql a partir do identificador
	 * correspondente no DB2.
	 * 
	 * @param objeto
	 *            O objeto do qual se deseja o identificador no SQLServer.
	 * @param instituicao
	 *            A instituição.
	 * @return
	 * @throws BancoobException
	 */
	protected List<Serializable> obterIdsSQL(T objeto, Integer idInstituicao) throws BancoobException {

		List<Serializable> listIdsSQL = new  ArrayList<Serializable>();
		Serializable idDB2 = objeto.getIdDB2();

		Integer numeroCooperativa = obterNumeroCooperativa(idInstituicao);
		List<T> encontradas = getDAO().obterPorIdsDB2(idDB2, numeroCooperativa);

		if (encontradas != null && !encontradas.isEmpty()) {
			for (T encontrada : encontradas) {
				listIdsSQL.add(encontrada.getIdSQL());
			}
		}

		for (Serializable serializable : listIdsSQL) {
			getLogger().info("ids da Entidade de replicação = " + serializable);
		}
		
		return listIdsSQL;
	}
	
	/**
	 * Obter por id d b2.
	 *
	 * @param entidade o valor de entidade
	 * @param idInstituicao o valor de id instituicao
	 * @return T
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public List<T> obterPorIdsDB2(T entidade, Integer idInstituicao) throws BancoobException {
		
		getLogger().debug("Obtendo entidade por idDB2: ", entidade.getClass().getName(), "(",
				String.valueOf(entidade.getIdDB2()), ")");
		
		List<T> retorno = getDAO().obterPorIdsDB2(entidade.getIdDB2(), obterNumeroCooperativa(idInstituicao));
		
		if(retorno == null) {
			throw new BancoobRuntimeException("Registro não encontrado por idDB2: "
					+ entidade.getClass().getName() + "(" + entidade.getIdDB2() + ")");
		}
		
		getLogger().debug("Entidade obtida por idDB2: ", entidade.getClass().getName(), "(",
				String.valueOf(entidade.getIdDB2()), ")");
		
		return retorno;
	}
	

}
