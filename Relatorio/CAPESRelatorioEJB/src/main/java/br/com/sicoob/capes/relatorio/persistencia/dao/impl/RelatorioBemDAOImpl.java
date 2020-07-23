package br.com.sicoob.capes.relatorio.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.capes.cadastro.negocio.vo.ValoresBensVO;
import br.com.sicoob.capes.relatorio.persistencia.dao.RelatorioBemDAO;

public class RelatorioBemDAOImpl  extends CAPESRelatorioDao implements RelatorioBemDAO {

	/**
	 * {@inheritDoc}
	 */
	public ValoresBensVO obterValoresBensPessoa(Long idPessoaCompartilhamento)
			throws BancoobException {
		Connection conx = null;
		ResultSet rset = null;
		Comando comando = null;

		ValoresBensVO retorno = null;

		try {
			conx = estabelecerConexao();
			comando = getComando("OBTER_VALORES_BEM_PESSOA");
			comando.adicionarVariavel("idPessoaCompartilhamento", idPessoaCompartilhamento);
			comando.configurar();

			rset = comando.executarConsulta(conx);

			if (rset.next()) {
				retorno = new ValoresBensVO();
				retorno.setValorTotalAvaliado(rset.getBigDecimal("TOTAL_AVALIADO"));
				retorno.setValorTotalDeclarado(rset.getBigDecimal("TOTAL_DECLARADO"));
				retorno.setValorTotalImovelAvaliado(rset.getBigDecimal("TOTAL_IMOVEL_AVALIADO"));
				retorno.setValorTotalImovelDeclarado(rset.getBigDecimal("TOTAL_IMOVEL_DECLARADO"));
				retorno.setValorTotalMovelAvaliado(rset.getBigDecimal("TOTAL_MOVEL_AVALIADO"));
				retorno.setValorTotalMovelDeclarado(rset.getBigDecimal("TOTAL_MOVEL_DECLARADO"));
			}
		} catch (SQLException excecao) {
			throw new PersistenciaException(excecao);
		} finally {
			fecharResultSet(rset);
			fecharConexao(conx);
			fecharComando(comando);
		}

		return retorno;
	}

	
}
