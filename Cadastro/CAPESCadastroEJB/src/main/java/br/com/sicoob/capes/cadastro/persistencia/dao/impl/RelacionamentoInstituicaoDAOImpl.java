/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.capes.cadastro.negocio.dto.RelacionamentoInstituicaoDTO;
import br.com.sicoob.capes.cadastro.persistencia.dao.RelacionamentoInstituicaoDAO;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * DAO utilizado para manter o relacionamento das pessoas com as instituições.
 * 
 * @author Erico.Junior
 */
public class RelacionamentoInstituicaoDAOImpl extends CAPESCadastroDao implements
		RelacionamentoInstituicaoDAO {

	/**
	 * {@inheritDoc}
	 */
	public List<RelacionamentoInstituicaoDTO> listarRelacionamentoInstituicao(PessoaCompartilhamento pessoa) {

		Connection conx = null;
		ResultSet rset = null;
		Comando comando = null;
		List<RelacionamentoInstituicaoDTO> lista = new ArrayList<RelacionamentoInstituicaoDTO>();

		try {

			conx = estabelecerConexao();
			comando = getComando("LISTAR_TRANSICAO_PESSOA_INSTITUICAO_RESPONSAVEL");
			
			comando.adicionarVariavel("idPessoaCompartilhamento", pessoa.getIdPessoaCompartilhamento()); 
			comando.configurar();
			
			rset = comando.executarConsulta(conx);

			while (rset.next()) {

				Instituicao instituicao = new Instituicao();
				instituicao.setIdInstituicao(rset.getInt("IDINSTITUICAO"));
				instituicao.setIdUnidadeInst(rset.getInt("IDUNIDADEINST"));

				RelacionamentoInstituicaoDTO dto = new RelacionamentoInstituicaoDTO();
				dto.setInstituicao(instituicao);

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
