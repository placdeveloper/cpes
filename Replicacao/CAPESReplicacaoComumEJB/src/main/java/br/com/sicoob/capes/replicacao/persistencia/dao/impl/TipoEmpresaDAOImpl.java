package br.com.sicoob.capes.replicacao.persistencia.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.capes.negocio.entidades.legado.FonteRenda;
import br.com.sicoob.capes.negocio.entidades.legado.TipoEmpresa;
import br.com.sicoob.capes.replicacao.persistencia.dao.CAPESReplicacaoComumCrudDao;
import br.com.sicoob.capes.replicacao.persistencia.dao.TipoEmpresaDAO;

public class TipoEmpresaDAOImpl extends CAPESReplicacaoComumCrudDao<TipoEmpresa> implements TipoEmpresaDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "";
	private static final String COMANDO_PESQUISAR_TIPO_EMPRESA_POR_RENDA_ANUAL = "PESQUISAR_TIPO_EMPRESA_POR_FAIXA_DE_RENDA_ANUAL";
	
	public TipoEmpresaDAOImpl() {
		super(TipoEmpresa.class, NOME_COMANDO_PESQUISAR);
	}

	public TipoEmpresa obterTipoEmpresaPorFaixaRendaAnual(FonteRenda renda) throws BancoobException {
		Comando comando = null;	
		Connection conexao = null;
		ResultSet rs = null;
		
		TipoEmpresa tipoEmpresa = null;
		
		try{
			conexao = estabelecerConexao();
			comando = getComando(COMANDO_PESQUISAR_TIPO_EMPRESA_POR_RENDA_ANUAL);
			comando.adicionarVariavel("rendaAnual", calculaRendaAnual(renda));
			comando.adicionarVariavel("isSimplesNacional", renda.getBolSimplesNacional());
			comando.configurar();
			rs = comando.executarConsulta(conexao);
			
			if(rs.next()){
				tipoEmpresa = new TipoEmpresa();
				tipoEmpresa.setCodigo(rs.getShort("CODTIPOEMPRESA"));
				tipoEmpresa.setDescricao(rs.getString("DESCTIPOEMPRESA"));
				tipoEmpresa.setValorLimiteInferior(rs.getBigDecimal("VALORLIMITEINFERIORFATURAMENTO"));
				tipoEmpresa.setValorLimiteSuperior(rs.getBigDecimal("VALORLIMITESUPERIORFATURAMENTO"));
			}
			
		} catch (SQLException e) {
			throw new PersistenciaException(e);
		} finally {
			fecharResultSet(rs);
			fecharComando(comando);
			fecharConexao(conexao);
		}
		
		return tipoEmpresa;
	}

	
	private BigDecimal calculaRendaAnual(FonteRenda fonteRenda){
    	return fonteRenda.getReceitaBrutaMensal().multiply(new BigDecimal(12.0));
    }
}
