package br.com.sicoob.capes.processamento.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.capes.processamento.negocio.vo.CooperativaVO;
import br.com.sicoob.capes.processamento.persistencia.dao.CAPESProcessamentoDao;
import br.com.sicoob.capes.processamento.persistencia.dao.CooperativaDao;

/**
 * A Classe CooperativaDaoImpl.
 */
public class CooperativaDaoImpl extends CAPESProcessamentoDao implements CooperativaDao {

	/**
	 * {@inheritDoc}
	 */
	public List<CooperativaVO> obterPacsBancoob() throws BancoobException {
		Connection conx = null;
		ResultSet rset = null;
		Comando comando = null;
		List<CooperativaVO> retorno = new ArrayList<CooperativaVO>();

		try {
			conx = estabelecerConexao(1);
			comando = getComando("OBTER_PACS_BANCOOB");
			comando.configurar();

			rset = comando.executarConsulta(conx);

			while (rset.next()) {
				CooperativaVO cooperativa = new CooperativaVO();
				cooperativa.setIdInstituicao(rset.getInt("IDINSTITUICAO"));
				cooperativa.setIdUnidadeInst(rset.getInt("IDUNIDADEINST"));
				cooperativa.setNumeroCooperativa(rset.getInt("NUMCOOPERATIVA"));
				cooperativa.setNumeroPac(rset.getInt("NUMPAC"));
				
				retorno.add(cooperativa);
			}

		} catch (SQLException excecao) {
			throw new PersistenciaException(excecao);
		} finally {
			fecharComando(comando);
			fecharResultSet(rset);
			fecharConexao(conx);
		}
		return retorno;
	}

}