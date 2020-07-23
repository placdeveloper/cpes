package br.com.sicoob.capes.processamento.persistencia.transformador.exportacaoarquivo;

import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;

/**
 * A Classe TransformadorRegistroAbstrato.
 */
public abstract class TransformadorRegistroAbstrato {

	/**
	 * O método Fechar result set.
	 *
	 * @param rs o valor de rs
	 */
	protected void fecharResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				SicoobLoggerPadrao.getInstance(getClass()).erro(e, e.getMessage());
			}
		}
	}
}