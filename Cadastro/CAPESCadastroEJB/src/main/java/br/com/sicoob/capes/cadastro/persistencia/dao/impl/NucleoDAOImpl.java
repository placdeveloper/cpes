/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.exception.ConstraintViolationException;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.capes.cadastro.negocio.excecao.ExclusaoNucleoAssociadoException;
import br.com.sicoob.capes.cadastro.persistencia.dao.NucleoDAO;
import br.com.sicoob.capes.negocio.entidades.Nucleo;

/**
 * Classe que implementa a persistencia de Nucleo. 
 * @author juan.damasceno
 */
public class NucleoDAOImpl extends CAPESCadastroCrudDao<Nucleo> implements
		NucleoDAO {


	/**
	 * Instancia um novo NucleoDAOImpl.
	 */
	public NucleoDAOImpl() {
		super(Nucleo.class, "PESQUISA_NUCLEO_POR_INSTITUICAO");
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Integer pesquisarProximoCodigo(Integer idInstituicao) throws BancoobException {
		Comando comando = null;
		Connection conx = null;
		ResultSet rs = null;
		try {
			Integer codigo = null;
			comando = getComando("PESQUISAR_PROXIMO_CODIGO_NUCLEO");
			comando.adicionarVariavel("idInstituicao", idInstituicao);
			comando.configurar();
			
			conx = estabelecerConexao();
			rs = comando.executarConsulta(conx);
			if (rs.next()) {
				codigo = rs.getInt(1);
			}
			rs.close();
			return codigo;
		} catch (SQLException e) {
			throw new PersistenciaException("Erro ao recuperar dados.", e);
		} finally {
			fecharResultSet(rs);
			fecharComando(comando);
			fecharConexao(conx);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void excluir(Serializable chave) throws BancoobException {
		try {
			super.excluir(chave);
		} catch (ConstraintViolationException e) {
			throw new ExclusaoNucleoAssociadoException(e);
		}
	}
}