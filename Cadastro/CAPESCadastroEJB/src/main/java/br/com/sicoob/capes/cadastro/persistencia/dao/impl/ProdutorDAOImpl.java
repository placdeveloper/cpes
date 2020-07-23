/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.excecao.ItemExcluidoException;
import br.com.sicoob.capes.cadastro.persistencia.dao.ProdutorDAO;
import br.com.sicoob.capes.negocio.entidades.vigente.Produtor;

/**
 * @author Erico.Junior
 * 
 */
public class ProdutorDAOImpl extends EntidadeCadastroDao<Produtor> implements ProdutorDAO {

	/** A constante COMANDO_PESQUISAR. */
	private static final String COMANDO_PESQUISAR = "";

	/**
	 * Instancia um novo ProdutorDAOImpl.
	 */
	public ProdutorDAOImpl() {
		super(Produtor.class, COMANDO_PESQUISAR);
	}

	
	@Override
	public void excluirEntidade(Produtor objeto) throws BancoobException {

		Produtor objetoPersistente = obter(objeto.getId());

		if (objetoPersistente == null) {
			throw new ItemExcluidoException();
		}

		objetoPersistente.setGravarHistorico(objeto.getGravarHistorico());
		objetoPersistente.setIdInstituicaoAtualizacao(objeto.getIdInstituicaoAtualizacao());
		objetoPersistente.setIdRegistroControlado(objeto.getIdRegistroControlado());
		objetoPersistente.setVerificarAutorizacao(objeto.getVerificarAutorizacao());

		Produtor produtorPersist = getEntityManager().getReference(objeto.getClass(), objeto.getId());
		produtorPersist.setIdUsuarioAprovacao(objeto.getIdUsuarioAprovacao());
		produtorPersist.setIdUsuarioEnvio(objeto.getIdUsuarioEnvio());
		produtorPersist.setDataHoraInicio(objetoPersistente.getDataHoraInicio());
		getEntityManager().remove(produtorPersist);
		getEntityManager().flush();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void alterar(Produtor objeto) throws BancoobException {
		try {
			getEntityManager().merge(objeto);
			getEntityManager().flush();
		} catch (IllegalArgumentException e) {
			throw new BancoobException("msg.erro.alterar.nao.existe", e);
		}
	}
}
