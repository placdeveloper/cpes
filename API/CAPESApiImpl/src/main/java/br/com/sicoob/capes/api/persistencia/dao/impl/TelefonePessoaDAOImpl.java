package br.com.sicoob.capes.api.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.capes.api.negocio.vo.TelefonePessoaVO;
import br.com.sicoob.capes.api.persistencia.dao.TelefonePessoaDAO;

/**
 * A Classe TelefonePessoaDAOImpl.
 */
public class TelefonePessoaDAOImpl extends CAPESApiDAO<TelefonePessoaVO> implements TelefonePessoaDAO {

	/**
	 * Instancia um novo TelefonePessoaDAOImpl.
	 */
	public TelefonePessoaDAOImpl() {
		super("CONSULTAR_TELEFONE_PESSOA");
    }
	
	@Override
	protected String obterNomeComandoPesquisar() throws BancoobException {
		return "PESQUISAR_TELEFONE_PESSOA";
	}
	
	@Override
	protected String obterNomeComandoQuantidadePesquisar() throws BancoobException {
		return "PESQUISAR_QUANTIDADE_TELEFONE_PESSOA";
	}

	public List<TelefonePessoaVO> listarTelefonePessoaPorIdPessoaLegadoIdInstituicao(
			Integer idPessoaLegado, Integer idInstituicao)
			throws BancoobException {
		Connection con = null;
		ResultSet rset = null;
		Comando comando = null;
		List<TelefonePessoaVO> retorno = new ArrayList<TelefonePessoaVO>();

		try {
			con = estabelecerConexao();
			comando = getComando("CONSULTAR_TELEFONE_PESSOA_POR_IDPESSOALEGADO_E_IDINSITITUICAO");
			comando.adicionarVariavel(ID_PESSOA_LEGADO, idPessoaLegado);
			comando.adicionarVariavel(ID_INSTITUICAO, idInstituicao);
			comando.configurar();

			rset = comando.executarConsulta(con);

			while (rset.next()) {
				TelefonePessoaVO vo = new TelefonePessoaVO();
				vo.setIdTelefone(rset.getLong("IDTELEFONEPESSOA"));
				vo.setDataHoraInicio(rset.getDate("DATAHORAINICIO"));
				vo.setDdd(rset.getString("NUMDDD"));
				vo.setTelefone(rset.getString("NUMTELEFONE"));
				vo.setRamal(rset.getString("NUMRAMAL"));
				vo.setCodigoTipoTelefone(rset.getShort("CODTIPOTELEFONE"));
				vo.setDescObservacao(rset.getString("DESCOBSERVACAO"));
				retorno.add(vo);
			}
		} catch (SQLException excecao) {
			throw new PersistenciaException(excecao);
		} finally {
			fecharResultSet(rset);
			fecharConexao(con);
			fecharComando(comando);
		}

		return retorno;
	}
	
}