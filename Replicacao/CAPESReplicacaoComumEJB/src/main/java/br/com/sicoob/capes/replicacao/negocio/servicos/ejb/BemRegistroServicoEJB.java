/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.servicos.ejb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.comum.util.ReflexaoUtils;
import br.com.sicoob.capes.negocio.entidades.legado.Bem;
import br.com.sicoob.capes.negocio.entidades.legado.BemPosse;
import br.com.sicoob.capes.negocio.entidades.legado.BemRegistro;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.BemRegistroServicoLocal;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.BemRegistroServicoRemote;
import br.com.sicoob.capes.replicacao.persistencia.dao.BemRegistroDAO;
import br.com.sicoob.capes.replicacao.persistencia.dao.EntidadeReplicavelDaoIF;

/**
 * Serviço utilizado na replicação de bem posse.
 * @author Juan.Damasceno
 */
@Stateless
@Local( { BemRegistroServicoLocal.class })
@Remote( { BemRegistroServicoRemote.class })
public class BemRegistroServicoEJB extends EntidadeReplicavelServicoEJB<BemRegistro> 
	implements BemRegistroServicoRemote, BemRegistroServicoLocal {

	@Inject
	@Default
	private transient BemRegistroDAO bemRegistroDAO;
	
	/**
	 * {@inheritDoc}
	 */
	public Short obterMaxSequencial(Bem bem) {
		return bemRegistroDAO.obterMaxSequencial(bem);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected EntidadeReplicavelDaoIF<BemRegistro> getDAO() {
		return bemRegistroDAO;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void alterar(BemRegistro objeto, Integer idInstituicao) throws BancoobException {

		getLogger().debug("Alterando " + objeto.getClass() + ", idSQL: " + objeto.getIdSQL() + ", idDB2:" + objeto.getIdDB2());
		super.alterar(objeto, idInstituicao);
		
		//CASOS DUPLICADOS		
		List<Serializable> idsSQL = obterIdsSQL(objeto, idInstituicao);
		idsSQL.remove(objeto.getBemRegistroPK());
			
		for (Serializable idSQL : idsSQL) {
			BemRegistro entidadeDestino = obter(idSQL);
			ReflexaoUtils.copiarPropriedades(entidadeDestino, objeto, "id");//, "bensOnus", "bensPosse", "bensRegistro");
			super.alterar(entidadeDestino, idInstituicao);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void excluir(BemRegistro objeto, Integer idInstituicao) throws BancoobException {

		Serializable idSQL = obterIdSQL(objeto, idInstituicao);
		if (idSQL != null) {
			objeto.setIdSQL(idSQL);
			getLogger().debug("Excluindo " + objeto.getClass() + ", idSQL: " + idSQL + ", idDB2:" + objeto.getIdDB2());
			super.excluir(objeto, idInstituicao);
		}
		
		//CASOS DUPLICADOS		
		List<Serializable> idsSQL = obterIdsSQL(objeto, idInstituicao);
		idsSQL.remove(idSQL);
			
		for (Serializable id : idsSQL) {
			BemRegistro entidade = obter(idSQL);
			getLogger().debug("Excluindo " + objeto.getClass() + ", idSQL: " + id + ", idDB2:" + entidade.getIdDB2());
			super.excluir(entidade, idInstituicao);
		}
		
	}

}
