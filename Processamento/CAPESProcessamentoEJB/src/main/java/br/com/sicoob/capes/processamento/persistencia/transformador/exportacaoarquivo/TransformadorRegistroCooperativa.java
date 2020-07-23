package br.com.sicoob.capes.processamento.persistencia.transformador.exportacaoarquivo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.capes.processamento.negocio.dominio.exportacaoarquivo.vo.RegistroCooperativaVO;
import br.com.sicoob.capes.processamento.persistencia.transformador.TranformadorResultado;

/**
 * A Classe TransformadorRegistroCooperativa.
 */
public class TransformadorRegistroCooperativa extends TransformadorRegistroAbstrato implements TranformadorResultado<RegistroCooperativaVO> {

	/**
	 * {@inheritDoc}
	 */
	public Collection<RegistroCooperativaVO> transformar(ResultSet rs) throws BancoobException {
		List<RegistroCooperativaVO> resultado = new ArrayList<RegistroCooperativaVO>();

		try {
			while (rs.next()) {
				RegistroCooperativaVO vo = new RegistroCooperativaVO();

				vo.setIdPessoaCompartilhamento(rs.getLong("IDPESSOACOMPARTILHAMENTO"));
				vo.setNumero(rs.getString("NUMCOOPERATIVA"));
				vo.setSigla(rs.getString("DESCSIGLACOOP"));
				vo.setNome(rs.getString("DESCNOMECOOP"));
				vo.setPac(rs.getInt("NUMPAC"));
				vo.setResponsavelCadastro(rs.getBoolean("BOLRESPONSAVELCADASTRO"));

				resultado.add(vo);
			}
		} catch (SQLException excecao) {
			throw new PersistenciaException(excecao);
		} finally {
			fecharResultSet(rs);
		}
		return resultado;
	}

}