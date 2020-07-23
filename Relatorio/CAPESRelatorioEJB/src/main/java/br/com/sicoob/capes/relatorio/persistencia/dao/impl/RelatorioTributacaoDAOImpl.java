package br.com.sicoob.capes.relatorio.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.capes.relatorio.negocio.vo.RelatorioTributacaoVO;
import br.com.sicoob.capes.relatorio.persistencia.dao.RelatorioTributacaoDAO;

/**
 * @author Rodrigo.Chaves
 */
public class RelatorioTributacaoDAOImpl extends CAPESRelatorioDao implements RelatorioTributacaoDAO {
	
	/**
	 * {@inheritDoc}
	 */
	public List<RelatorioTributacaoVO> pesquisar(ConsultaDto<RelatorioTributacaoVO> dto) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		List<RelatorioTributacaoVO> listaRetorno = new ArrayList<RelatorioTributacaoVO>();

		try {
			conexao = estabelecerConexao();
			comando = getComando("CONSULTA_RELATORIO_TRIBUTACAO");
			comando.adicionarVariavel("criterios", dto);
			comando.configurar();
			
			getLogger().debug("Executando a consulta do relatorio de tributacao");
			getLogger().debug(comando.getSqlEmUso());
			
			rs = comando.executarConsulta(conexao);

			while (rs.next()) {
				RelatorioTributacaoVO vo = new RelatorioTributacaoVO();
				
				vo.setNumeroCentral(rs.getString("numeroCentral"));
				vo.setNumeroCooperativa(rs.getString("numeroCooperativa"));
				vo.setIdUnidadeInst(rs.getInt("idUnidadeInst"));
				vo.setNomePessoa(rs.getString("nomePessoa"));
				vo.setCpfCnpj(rs.getString("cpfCnpj"));
				vo.setCobraIR(rs.getBoolean("cobraIR"));
				vo.setCobraIOF(rs.getBoolean("cobraIOF"));
				vo.setCodigoOcupacao(rs.getString("codigoOcupacao"));
				vo.setDescOcupacao(rs.getString("descOcupacao"));
				
				listaRetorno.add(vo);
			}
			
		} catch (SQLException e) {
			getLogger().erro(e, "Erro ao executar a consulta do relatorio de tributacao");
			throw new BancoobException(e);
		} finally {
			fecharResultSet(rs);
			fecharComando(comando);
			fecharConexao(conexao);
		}
		
		return listaRetorno;
	}
}