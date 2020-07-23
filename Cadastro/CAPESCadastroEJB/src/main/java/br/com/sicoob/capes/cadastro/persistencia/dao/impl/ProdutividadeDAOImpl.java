/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.capes.cadastro.persistencia.dao.ProdutividadeDAO;
import br.com.sicoob.capes.negocio.entidades.bem.BemImovel;
import br.com.sicoob.capes.negocio.entidades.vigente.Produtividade;
import br.com.sicoob.capes.negocio.entidades.vigente.Produtor;

/**
 * @author Erico.Junior
 * 
 */
public class ProdutividadeDAOImpl extends EntidadeCadastroDao<Produtividade> implements
		ProdutividadeDAO {

	/** A constante COMANDO_PESQUISAR. */
	private static final String COMANDO_PESQUISAR = "PESQUISAR_PRODUTIVIDADE";

	/**
	 * Instancia um novo ProdutividadeDAOImpl.
	 */
	public ProdutividadeDAOImpl() {
		super(Produtividade.class, COMANDO_PESQUISAR);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Produtividade obter(Serializable chave) throws BancoobException {
		Produtividade produtividade = super.obter(chave);
		if (produtividade != null) {
			Hibernate.initialize(produtividade.getBemImovel());
		}
		return produtividade;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<String> listarProdutividadesAssociadas(BemImovel bem, List<Long> idsPessoaCompartilhamento) throws BancoobException {
		Comando comando = null;
		Connection conn = null;
		List<String> retorno = new ArrayList<String>();

		try {
			comando = getComando("PESQUISAR_PRODUTIVIDADE_POR_IMOVEL");
			comando.adicionarVariavel("idBem", bem.getId());
			comando.adicionarVariavel("idsPessoaCompartilhamento", idsPessoaCompartilhamento);
			comando.configurar();

			conn = estabelecerConexao();
			ResultSet rset = comando.executarConsulta(conn);
			while (rset.next()) {
				retorno.add(rset.getString("NOMEPESSOA"));
			}

		} catch (SQLException e) {
			throw new PersistenciaException(e);
		} finally {
			fecharConexao(conn);
			fecharComando(comando);
		}

		return retorno;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean produtorPossuiDependencia(Produtor produtor) throws BancoobException {
		ConsultaDto<Produtividade> criterios = new ConsultaDto<Produtividade>();
		criterios.setFiltro(produtor);
		return pesquisarNumeroRegistros(criterios, COMANDO_PESQUISAR, Boolean.TRUE) > 0;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void alterar(Produtividade objeto) throws BancoobException {
		try {
			getEntityManager().merge(objeto);
			getEntityManager().flush();
		} catch (IllegalArgumentException e) {
			throw new BancoobException("msg.erro.alterar.nao.existe", e);
		}
	}
	
	public List<String> listarProdutividadesPorIdBem(Long idBem) throws BancoobException {
		Comando comando = null;
		Connection conn = null;
		List<String> retorno = new ArrayList<String>();

		try {
			comando = getComando("PESQUISAR_PRODUTIVIDADE_POR_ID_BEM");
			comando.adicionarVariavel("idBem", idBem);
			comando.configurar();

			conn = estabelecerConexao();
			ResultSet rset = comando.executarConsulta(conn);
			while (rset.next()) {
				retorno.add(rset.getString("NOMEPESSOA"));
			}

		} catch (SQLException e) {
			throw new PersistenciaException(e);
		} finally {
			fecharConexao(conn);
			fecharComando(comando);
		}

		return retorno;
	}

}