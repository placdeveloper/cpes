/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.capes.cadastro.persistencia.dao.TipoEmpresaDAO;
import br.com.sicoob.capes.negocio.entidades.TipoEmpresa;
import br.com.sicoob.capes.negocio.entidades.vigente.FonteRenda;

/**
 * DAO utilizado para os tipos de empresa.
 * 
 * @author Erico.Junior
 */
public class TipoEmpresaDAOImpl extends CAPESCadastroCrudDominioDao<TipoEmpresa> implements
		TipoEmpresaDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISAR_TIPO_EMPRESA";
	
	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String COMANDO_PESQUISAR_TIPO_EMPRESA_POR_RENDA_ANUAL = "PESQUISAR_TIPO_EMPRESA_POR_FAIXA_DE_RENDA_ANUAL";
	
	/** A constante COMANDO_PESQUISAR_PROXIMO_CODIGO. */
	private static final String COMANDO_PESQUISAR_PROXIMO_CODIGO = "PESQUISAR_PROXIMO_CODIGO_TIPO_EMPRESA";

	/**
	 * Construtor do DAO.
	 */
	public TipoEmpresaDAOImpl() {
		super(TipoEmpresa.class, NOME_COMANDO_PESQUISAR, COMANDO_PESQUISAR_PROXIMO_CODIGO);
	}

	public TipoEmpresa obterTipoEmpresaPorFaixaRendaAnual(FonteRenda fonteRenda) throws BancoobException {
		Comando comando = null;	
		Connection conexao = null;
		ResultSet rs = null;
		
		TipoEmpresa tipoEmpresa = null;
		
		try{
			conexao = estabelecerConexao();
			comando = getComando(COMANDO_PESQUISAR_TIPO_EMPRESA_POR_RENDA_ANUAL);
			comando.adicionarVariavel("rendaAnual", calculaRendaAnual(fonteRenda));
			comando.adicionarVariavel("isSimplesNacional", fonteRenda.getBolSimplesNacional());
			comando.configurar();
			rs = comando.executarConsulta(conexao);
			
			if(rs.next()){
				tipoEmpresa = new TipoEmpresa();
				tipoEmpresa.setCodigo(rs.getShort("CODTIPOEMPRESA"));
				tipoEmpresa.setDescricao(rs.getString("DESTIPOEMPRESA"));
				tipoEmpresa.setValorLimiteInferior(rs.getBigDecimal("VALORLIMITEINFERIORFATURAMENTO"));
				tipoEmpresa.setValorLimiteSuperior(rs.getBigDecimal("VALORLIMITESUPERIORFATURAMENTO"));
				tipoEmpresa.setAtivo(rs.getBoolean("BOLATIVO"));
				tipoEmpresa.setIsSimplesNacional(rs.getBoolean("BOLHABILITASIMPLESNACIONAL"));
				tipoEmpresa.setPossuiAtivoSuperior(rs.getBoolean("BOLHABILITAPOSSUIATIVO"));
			}
			
		} catch (SQLException e) {
			throw new BancoobException(e);
		} finally {
			fecharResultSet(rs);
			fecharComando(comando);
			fecharConexao(conexao);
		}
		
		return tipoEmpresa;
	}
	
	private BigDecimal calculaRendaAnual(FonteRenda fonteRenda){
    	return fonteRenda.getValorReceitaBrutaMensal().multiply(new BigDecimal(12.0));
    }

}