/*
 * SICOOB
 * 
 * PessoaIDGenerator.java(br.com.sicoob.capes.negocio.entidades.legado.id.PessoaIDGenerator)
 */
package br.com.sicoob.capes.negocio.entidades.legado.id;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;

/**
 * Gerador de identificador de pessoas.
 * 
 * @author Erico.Junior
 */
public class PessoaIDGenerator implements IdentifierGenerator {

	/** A Constante SQL. */
	private static final String SQL = "select dbo.FN_CALCPROXNUMPESSOA(1)";

	/**
	 * @see org.hibernate.id.IdentifierGenerator
	 *      #generate(org.hibernate.engine.SessionImplementor, java.lang.Object)
	 */
	public Serializable generate(SessionImplementor session, Object arg1) throws HibernateException {
		
		Integer numero = null;
		Statement stmt = null;
		ResultSet rset = null;
		try {
			stmt = session.connection().createStatement();
			rset = stmt.executeQuery(SQL);
			
			if (rset.next()) {
				numero = Integer.valueOf(rset.getInt(1));
			}
			
		} catch (SQLException e) {
			throw new HibernateException(e);
		}finally{
			fecharStatement(stmt);
			fecharResultSet(rset);
		}
		return numero;
	}
	
	/**
	 * Fechar result set.
	 * 
	 * @param rs
	 *            the rs
	 */
	private void fecharResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				SicoobLoggerPadrao.getInstance(PessoaIDGenerator.class).erro(e, "Erro no fechamento do resultSet");
			}
		}
	}
	
	/**
	 * Fechar statement.
	 * 
	 * @param stmt
	 *            the stmt
	 */
	private void fecharStatement(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				SicoobLoggerPadrao.getInstance(PessoaIDGenerator.class).erro(e, "Erro no fechamento do Statement");
			}
		}
	}

}
