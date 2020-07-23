/* 
 * Sicoob
 * CAPESCadastroCrudDominioDao.java 
 * Criado em: 10/05/2011
 */
package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.persistence.EntityExistsException;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.capes.cadastro.persistencia.dao.CAPESCadastroCrudDominioDaoIF;
import br.com.sicoob.capes.cadastro.persistencia.excecao.EntidadeExistenteException;
import br.com.sicoob.capes.cadastro.persistencia.excecao.ValorInformadoInvalidoException;
import br.com.sicoob.capes.comum.util.Constantes;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;

/**
 * 10/05/2011
 * 
 * @author Rodrigo.Chaves
 */
public abstract class CAPESCadastroCrudDominioDao<T extends CAPESEntidade<Short>>
		extends CAPESCadastroCrudDao<T> implements
		CAPESCadastroCrudDominioDaoIF<T> {

	/** O atributo nomeComandoPesquisarProximoCodigo. */
	private String nomeComandoPesquisarProximoCodigo;

	/**
	 * Construtor
	 * 
	 * @param clazz
	 * @param nomeComandoPesquisar
	 */
	public CAPESCadastroCrudDominioDao(Class<T> clazz, String nomeComandoPesquisar,
			String nomeComandoPesquisarProximoCodigo) {
		super(clazz, nomeComandoPesquisar);
		this.nomeComandoPesquisarProximoCodigo = nomeComandoPesquisarProximoCodigo;
	}

	/**
     * @param datasource
     * @param arquivoQueries
     * @param nomeColecaoComandos
     * @param clazz
     * @param nomeComandoPesquisar
     * @param nomeComandoPesquisarProximoCodigo
     */
    public CAPESCadastroCrudDominioDao(String arquivoQueries,
            String nomeColecaoComandos, Class<T> clazz, String nomeComandoPesquisar,
			String nomeComandoPesquisarProximoCodigo) {
    	
	    super(Constantes.Persistencia.DATASOURCE_CAPES, arquivoQueries, 
	    		nomeColecaoComandos, clazz, nomeComandoPesquisar);
	    this.nomeComandoPesquisarProximoCodigo = nomeComandoPesquisarProximoCodigo;
    }

	/**
	 * {@inheritDoc}
	 */
	public Short pesquisarProximoCodigo() throws BancoobException {
		Comando comando = null;
		Connection conx = null;
		ResultSet rs = null;
		try {
			Short codigo = null;
			comando = getComando(this.nomeComandoPesquisarProximoCodigo);
			conx = estabelecerConexao();
			rs = comando.executarConsulta(conx);
			if (rs.next()) {
				codigo = rs.getShort(1);
				if (codigo.compareTo(Short.MAX_VALUE) >= 0) {
					codigo = null;
				}
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
	public T incluir(T objeto) throws BancoobException {
		
		if (objeto.getId().compareTo(Short.MAX_VALUE) >= 0) {
			throw new ValorInformadoInvalidoException("código", objeto.getId());
		}
		
		try {
			return super.incluir(objeto);
		} catch (EntityExistsException e) {
			throw new EntidadeExistenteException(e);
		}
	}

}
