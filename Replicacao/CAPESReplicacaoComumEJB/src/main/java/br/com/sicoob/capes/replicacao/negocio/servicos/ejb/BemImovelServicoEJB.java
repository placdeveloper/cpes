/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.servicos.ejb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.comum.util.ReflexaoUtils;
import br.com.sicoob.capes.negocio.entidades.legado.BemImovel;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.BemImovelServicoLocal;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.BemImovelServicoRemote;
import br.com.sicoob.capes.replicacao.persistencia.dao.BemImovelDAO;
import br.com.sicoob.capes.replicacao.persistencia.dao.EntidadeReplicavelDaoIF;

/**
 * Serviço utilizado para replicação dos bens.
 * 
 * @author juan.damasceno
 */
@Stateless
@Local( { BemImovelServicoLocal.class })
@Remote( { BemImovelServicoRemote.class })
public class BemImovelServicoEJB extends EntidadeReplicavelServicoEJB<BemImovel> implements BemImovelServicoRemote, BemImovelServicoLocal {

	@Inject
	@Default
	private transient BemImovelDAO bemDAO;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected EntidadeReplicavelDaoIF<BemImovel> getDAO() {
		return bemDAO;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void excluir(BemImovel imovel, Integer idInstituicao)
			throws BancoobException {

		imovel.setDataInativacao(new Date());
		super.alterar(imovel, idInstituicao);	
		
		//CASOS DUPLICADOS		
		List<Serializable> idsSQL = obterIdsSQL(imovel, idInstituicao);
		idsSQL.remove(imovel.getId());
			
		for (Serializable idSQL : idsSQL) {
			BemImovel entidadeDuplicada = obter(idSQL);
			entidadeDuplicada.setDataInativacao(new Date());
			super.alterar(entidadeDuplicada, idInstituicao);
		}
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void alterar(BemImovel objeto, Integer idInstituicao) throws BancoobException {
		getLogger().debug("Alterando " + objeto.getClass() + ", idSQL: " + objeto.getIdSQL() + ", idDB2:" + objeto.getIdDB2());
		super.alterar(objeto, idInstituicao);
		
		//CASOS DUPLICADOS		
		List<Serializable> idsSQL = obterIdsSQL(objeto, idInstituicao);
		idsSQL.remove(objeto.getId());
			
		for (Serializable idSQL : idsSQL) {
			BemImovel entidadeDestino = obter(idSQL);
			ReflexaoUtils.copiarPropriedades(entidadeDestino, objeto, "id", "bensOnus", "bensPosse", "bensRegistro");
			super.alterar(entidadeDestino, idInstituicao);
		}
	}
}
