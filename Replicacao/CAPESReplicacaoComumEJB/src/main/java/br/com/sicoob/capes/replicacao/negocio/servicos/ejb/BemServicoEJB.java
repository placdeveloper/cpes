/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.servicos.ejb;

import java.io.Serializable;
import java.util.Date;
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
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.BemServicoLocal;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.BemServicoRemote;
import br.com.sicoob.capes.replicacao.persistencia.dao.BemDAO;
import br.com.sicoob.capes.replicacao.persistencia.dao.EntidadeReplicavelDaoIF;

/**
 * Serviço utilizado para replicação dos bens.
 * 
 * @author Erico.Junior
 */
@Stateless
@Local( { BemServicoLocal.class })
@Remote( { BemServicoRemote.class })
public class BemServicoEJB extends EntidadeReplicavelServicoEJB<Bem> implements BemServicoRemote, BemServicoLocal {

	@Inject
	@Default
	private transient BemDAO bemDAO;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected EntidadeReplicavelDaoIF<Bem> getDAO() {
		return bemDAO;
	}
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void excluir(Bem bem, Integer idInstituicao)
			throws BancoobException {

		bem.setDataInativacao(new Date());
		super.alterar(bem, idInstituicao);	
		
		//CASOS DUPLICADOS	
		List<Serializable> idsSQL = obterIdsSQL(bem, idInstituicao);
		idsSQL.remove(bem.getId());
			
		for (Serializable idSQL : idsSQL) {
			Bem entidadeDuplicada = obter(idSQL);
			entidadeDuplicada.setDataInativacao(new Date());
			super.alterar(entidadeDuplicada, idInstituicao);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void alterar(Bem objeto, Integer idInstituicao) throws BancoobException {

		getLogger().debug("Alterando " + objeto.getClass() + ", idSQL: " + objeto.getIdSQL() + ", idDB2:" + objeto.getIdDB2());
		super.alterar(objeto, idInstituicao);
		
		//CASOS DUPLICADOS		
		List<Serializable> idsSQL = obterIdsSQL(objeto, idInstituicao);
		idsSQL.remove(objeto.getId());
			
		for (Serializable idSQL : idsSQL) {
			Bem entidadeDestino = obter(idSQL);
			ReflexaoUtils.copiarPropriedades(entidadeDestino, objeto, "id", "bensOnus", "bensPosse", "bensRegistro");
			super.alterar(entidadeDestino, idInstituicao);
		}
	}

}
