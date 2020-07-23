package br.com.sicoob.capes.processamento.persistencia.transformador.exportacaoarquivo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.capes.processamento.negocio.dominio.exportacaoarquivo.vo.RegistroEnderecoVO;
import br.com.sicoob.capes.processamento.persistencia.transformador.TranformadorResultado;

/**
 * A Classe TransformadorRegistroEndereco.
 */
public class TransformadorRegistroEndereco extends TransformadorRegistroAbstrato implements TranformadorResultado<RegistroEnderecoVO> {

	/**
	 * {@inheritDoc}
	 */
	public Collection<RegistroEnderecoVO> transformar(ResultSet rs) throws BancoobException {
		List<RegistroEnderecoVO> resultado = new ArrayList<RegistroEnderecoVO>();

		try {
			while (rs.next()) {
				RegistroEnderecoVO vo = new RegistroEnderecoVO();

				vo.setIdPessoaCompartilhamento(rs.getLong("IDPESSOACOMPARTILHAMENTO"));

				vo.setTipoEndereco(rs.getShort("CODTIPOENDERECO"));
				vo.setTipoLogradouro(rs.getInt("IDTIPOLOGRADOURO"));
				vo.setLogradouro(rs.getString("DESCLOGRADOURO"));
				vo.setNumero(rs.getString("DESCNUMERO"));
				vo.setComplemento(rs.getString("DESCCOMPLEMENTO"));
				vo.setBairro(rs.getString("NOMEBAIRRO"));
				vo.setMunicipio(rs.getString("NOMELOCALIDADE"));
				vo.setUf(rs.getString("SIGLAUF"));
				vo.setCep(rs.getString("CODCEP"));
				
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