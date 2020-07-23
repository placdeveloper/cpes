/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.capes.cadastro.negocio.vo.ConsultaAlteracaoDocumentoVO;
import br.com.sicoob.capes.cadastro.persistencia.dao.AlterarDocumentoPessoaDAO;

/**
 * @author erico.junior
 * 
 */
public class AlterarDocumentoPessoaDAOImpl extends CAPESCadastroDao
		implements AlterarDocumentoPessoaDAO {

	/**
	 * {@inheritDoc}
	 */
	public List<ConsultaAlteracaoDocumentoVO> consultarVinculosDocumento(
			String cpfCnpj) throws BancoobException {
		
		Connection conx = null;
		ResultSet rset = null;
		Comando comando = null;
		List<ConsultaAlteracaoDocumentoVO> lista = new ArrayList<ConsultaAlteracaoDocumentoVO>();

		try {

			conx = estabelecerConexao();
			comando = getComando("LISTAR_VINCULOS_CPF_CNPJ");
			comando.adicionarVariavel("cpfCnpj", cpfCnpj);
			comando.configurar();
			
			rset = comando.executarConsulta(conx);
			ConsultaAlteracaoDocumentoVO dto = null;

			while (rset.next()) {
				dto = new ConsultaAlteracaoDocumentoVO();
				dto.setCodigoCompartilhamento(rset.getShort("CODCOMPARTILHAMENTOCADASTRO"));
				dto.setDescricaoCompartilhamento(rset.getString("DESCCOMPARTILHAMENTOCADASTRO"));
				dto.setCpfCnpj(rset.getString("NUMCPFCNPJ"));
				dto.setIdInstituicao(rset.getInt("IDINSTITUICAO"));
				dto.setIdUnidadeInst(rset.getInt("IDUNIDADEINST"));
				dto.setIdPessoa(rset.getInt("idPessoa"));
				dto.setNomePessoaLegado(rset.getString("NOMEPESSOALEGADO"));
				lista.add(dto);
			}

		} catch (SQLException excecao) {
			throw new PersistenciaException(excecao);
		} finally {
			fecharResultSet(rset);
			fecharComando(comando);
			fecharConexao(conx);
		}

		return lista;
	}
	
	
}
