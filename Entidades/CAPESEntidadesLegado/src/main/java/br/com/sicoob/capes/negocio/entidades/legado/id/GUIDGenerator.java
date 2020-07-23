/*
 * SICOOB
 * 
 * GUIDGenerator.java(br.com.sicoob.capes.negocio.entidades.legado.id.GUIDGenerator)
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
 * Gerador de identificador GUID.
 * 
 * @author Erico.Junior
 */
public class GUIDGenerator implements IdentifierGenerator {

	/** A Constante SQL_NEW_ID. */
	private static final String SQL_NEW_ID = "select NEWID()";

	/**
	 * @see org.hibernate.id.IdentifierGenerator
	 *      #generate(org.hibernate.engine.SessionImplementor, java.lang.Object)
	 */
	public Serializable generate(SessionImplementor session, Object arg1) {
		String uid = null;
		Statement stmt = null;
		ResultSet rset = null;

		try {

			stmt = session.connection().createStatement();
			rset = stmt.executeQuery(SQL_NEW_ID);
			if (rset.next()) {
				uid = rset.getString(1);
			}

		} catch (SQLException e) {
			throw new HibernateException(e);
		}finally{
			fecharStatement(stmt);
			fecharResultSet(rset);
		}
		
		return uid;
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
				SicoobLoggerPadrao.getInstance(GUIDGenerator.class).erro(e, "Erro no fechamento do resultSet");
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
				SicoobLoggerPadrao.getInstance(GUIDGenerator.class).erro(e, "Erro no fechamento do Statement");
			}
		}
	}

}