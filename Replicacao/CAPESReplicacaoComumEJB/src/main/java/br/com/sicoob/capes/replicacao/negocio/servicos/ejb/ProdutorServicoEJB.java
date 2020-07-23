/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.legado.Produtor;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.ProdutorServicoLocal;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.ProdutorServicoRemote;
import br.com.sicoob.capes.replicacao.persistencia.dao.EntidadeReplicavelDaoIF;
import br.com.sicoob.capes.replicacao.persistencia.dao.ProdutorDAO;

/**
 * Serviço utilizado para replicação de produtores.
 * 
 * @author Erico.Junior
 */
@Stateless
@Local( { ProdutorServicoLocal.class })
@Remote( { ProdutorServicoRemote.class })
public class ProdutorServicoEJB extends EntidadeReplicavelServicoEJB<Produtor> implements
		ProdutorServicoRemote, ProdutorServicoLocal {

	@Inject
	@Default
	private transient ProdutorDAO produtorDAO;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected EntidadeReplicavelDaoIF<Produtor> getDAO() {
		return produtorDAO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void excluir(Produtor objeto, Integer idInstituicao) throws BancoobException {
		Integer numeroCooperativa = obterNumeroCooperativa(idInstituicao);
		getDAO().excluir(objeto.getIdSQL(), numeroCooperativa);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void alterar(Produtor objeto, Integer idInstituicao) throws BancoobException {
		persistir(objeto, idInstituicao);
	}
	
	/**
	 * Persistir.
	 *
	 * @param objeto o valor de objeto
	 * @param idInstituicao o valor de id instituicao
	 * @return Produtor
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private Produtor persistir(Produtor objeto, Integer idInstituicao) throws BancoobException {
		
		Produtor existente = obter(objeto.getIdSQL(), idInstituicao);
		
		if(existente == null) {
			existente = super.incluir(objeto, idInstituicao);			
		} else {
			Integer numeroCooperativa = obterNumeroCooperativa(idInstituicao);
			getDAO().alterar(objeto, numeroCooperativa);
		}
		
		return existente;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Produtor incluir(Produtor objeto, Integer idInstituicao)
			throws BancoobException {
		return persistir(objeto, idInstituicao);
	}
	
}
