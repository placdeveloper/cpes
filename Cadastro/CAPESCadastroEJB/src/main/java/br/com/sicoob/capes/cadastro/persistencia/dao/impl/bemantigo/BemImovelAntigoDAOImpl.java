package br.com.sicoob.capes.cadastro.persistencia.dao.impl.bemantigo;

import java.io.Serializable;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.persistencia.dao.bemantigo.BemImovelAntigoDAO;
import br.com.sicoob.capes.cadastro.persistencia.dao.impl.EntidadeCadastroDao;
import br.com.sicoob.capes.negocio.entidades.bemantigo.BemImovel;

/**
 * Classe que implementa a persistencia de BemImovel.
 * 
 * @author Juan.Damasceno
 *
 */
public class BemImovelAntigoDAOImpl extends EntidadeCadastroDao<BemImovel> implements BemImovelAntigoDAO {
	
	/**
	 * Instancia um novo BemImovelDAOImpl.
	 */
	public BemImovelAntigoDAOImpl() {
		super(BemImovel.class, null);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public BemImovel obter(Serializable chave) throws BancoobException {
		BemImovel bemImovel = super.obter(chave);
		
		if(bemImovel != null) {
			bemImovel.getBensOnus().size(); 
			bemImovel.getBensPosse().size();
			bemImovel.getBensRegistro().size();
		}
		
		return bemImovel;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void marcarEmAlteracao(String tableName, String nomeColunaId,
			Long id, Integer idInstituicaoAtualizacao) throws BancoobException {
		
		super.marcarEmAlteracao("CLI.BEMPESSOA", nomeColunaId, id,
				idInstituicaoAtualizacao);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void alterar(BemImovel objeto) throws BancoobException {

		BemImovel bemPersistent = obter(objeto.getId());

//		Session session = (Session) getEntityManager().getDelegate();
//		List<BemOnus> bensOnus = bemPersistent.getBensOnus();
//		List<BemPosse> bensPosse = bemPersistent.getBensPosse();
//		List<BemRegistro> bensRegistro = bemPersistent.getBensRegistro();

//		session.evict(bensOnus);
//		bensOnus.clear();
//		bensOnus.addAll(objeto.getBensOnus());
//
//		session.evict(bensPosse);
//		bensPosse.clear();
//		bensPosse.addAll(objeto.getBensPosse());
//
//		session.evict(bensRegistro);
//		bensRegistro.clear();
//		bensRegistro.addAll(objeto.getBensRegistro());

//		ReflexaoUtils.copiarPropriedades(bemPersistent, objeto, "bensOnus", "bensPosse", "bensRegistro");
		
		substituirColecoesPersistentes(bemPersistent, objeto, "bensOnus", "bensPosse", "bensRegistro");

		super.alterar(bemPersistent);
	}
	
}