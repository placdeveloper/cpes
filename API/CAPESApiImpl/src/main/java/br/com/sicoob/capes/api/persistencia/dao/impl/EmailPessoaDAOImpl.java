package br.com.sicoob.capes.api.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.capes.api.negocio.vo.EmailPessoaVO;
import br.com.sicoob.capes.api.persistencia.dao.EmailPessoaDAO;

/**
 * A Classe EmailPessoaDAOImpl.
 */
public class EmailPessoaDAOImpl extends CAPESApiDAO<EmailPessoaVO> implements EmailPessoaDAO {

	/**
	 * Instancia um novo EmailPessoaDAOImpl.
	 */
	public EmailPessoaDAOImpl() {
		super("CONSULTAR_EMAIL_PESSOA");
	}

	@Override
	protected String obterNomeComandoPesquisar() throws BancoobException {
		return "PESQUISAR_EMAIL_PESSOA";
	}

	@Override
	protected String obterNomeComandoQuantidadePesquisar() throws BancoobException {
		return "PESQUISAR_QUANTIDADE_EMAIL_PESSOA";
	}

	public List<EmailPessoaVO> listarEmaisPessoaPorIdPessoaLegadoIdInstituicao(
			Integer idPessoaLegado, Integer idInstituicao)
			throws BancoobException {
		Connection con = null;
		ResultSet rset = null;
		Comando comando = null;
		List<EmailPessoaVO> retorno = new ArrayList<EmailPessoaVO>();

		try {
			con = estabelecerConexao();
			comando = getComando("CONSULTAR_EMAIL_PESSOA_POR_IDPESSOALEGADO_E_IDINSITITUICAO");
			comando.adicionarVariavel(ID_PESSOA_LEGADO, idPessoaLegado);
			comando.adicionarVariavel(ID_INSTITUICAO, idInstituicao);
			comando.configurar();

			rset = comando.executarConsulta(con);

			while (rset.next()) {
				EmailPessoaVO vo = new EmailPessoaVO();
				vo.setIdEmail(rset.getLong("IDEMAILPESSOA"));
				vo.setCodigoTipoEmail(rset.getShort("CODTIPOEMAIL"));
				vo.setDataHoraInicio(rset.getDate("DATAHORAINICIO"));
				vo.setDescricaoEmail(rset.getString("DESCEMAIL"));
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