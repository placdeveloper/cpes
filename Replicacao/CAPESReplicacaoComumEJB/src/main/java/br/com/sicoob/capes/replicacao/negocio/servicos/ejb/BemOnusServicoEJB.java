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
import br.com.sicoob.capes.negocio.entidades.legado.BemImovel;
import br.com.sicoob.capes.negocio.entidades.legado.BemOnus;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.BemOnusServicoLocal;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.BemOnusServicoRemote;
import br.com.sicoob.capes.replicacao.persistencia.dao.BemOnusDAO;
import br.com.sicoob.capes.replicacao.persistencia.dao.EntidadeReplicavelDaoIF;

/**
 * Serviço utilizado na replicação de bem posse.
 * @author juan.damasceno
 */
@Stateless
@Local( { BemOnusServicoLocal.class })
@Remote( { BemOnusServicoRemote.class })
public class BemOnusServicoEJB extends EntidadeReplicavelServicoEJB<BemOnus> implements
		BemOnusServicoRemote, BemOnusServicoLocal {

	@Inject
	@Default
	private transient BemOnusDAO bemOnusDAO;
	
	/**
	 * {@inheritDoc}
	 */
	public Short obterMaxSequencialPorPessoa(Bem bem) {
		return bemOnusDAO.obterMaxSequencialPorPessoa(bem);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected EntidadeReplicavelDaoIF<BemOnus> getDAO() {
		return bemOnusDAO;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void alterar(BemOnus objeto, Integer idInstituicao) throws BancoobException {

		getLogger().debug("Alterando " + objeto.getClass() + ", idSQL: " + objeto.getIdSQL() + ", idDB2:" + objeto.getIdDB2());
		super.alterar(objeto, idInstituicao);
		
		//CASOS DUPLICADOS		
		List<Serializable> idsSQL = obterIdsSQL(objeto, idInstituicao);
		idsSQL.remove(objeto.getBemOnusPK());
			
		for (Serializable idSQL : idsSQL) {
			BemOnus entidadeDestino = obter(idSQL);
			ReflexaoUtils.copiarPropriedades(entidadeDestino, objeto, "id");//, "bensOnus", "bensPosse", "bensRegistro");
			super.alterar(entidadeDestino, idInstituicao);
		}
	}

}
