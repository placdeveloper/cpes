package br.com.sicoob.capes.api.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.capes.api.negocio.vo.BemPessoaVO;
import br.com.sicoob.capes.api.negocio.vo.BemPosseVO;
import br.com.sicoob.capes.api.persistencia.dao.BemPessoaDAO;

/**
 * A Classe BemPessoaDAOImpl.
 */
public class BemPessoaDAOImpl extends CAPESApiDAO<BemPessoaVO> implements BemPessoaDAO {

	/**
	 * Instancia um novo BemPessoaDAOImpl.
	 */
	public BemPessoaDAOImpl() {
		super("PESQUISAR_BEM_PESSOA");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<BemPosseVO> obterPosses(Long idBem) throws BancoobException {
		Comando comando = null;
		try {
			comando = getComando("PESQUISAR_POSSES_BEM");
			comando.adicionarVariavel("idBem", idBem);
			comando.configurar();

			return criarQuery(comando).getResultList();
		} finally {
			fecharComando(comando);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<BemPessoaVO> obterPorPessoaInstituicaoParceiro(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		Connection conx = null;
		ResultSet rset = null;
		Comando comando = null;
		List<BemPessoaVO> retorno = new ArrayList<BemPessoaVO>();

		try {
			comando = getComando("OBTER_BENS_ANTIGOS_PROPRIETARIO_E_PARTICIPANTE");
			comando.adicionarVariavel("idPessoa", idPessoa);
			comando.adicionarVariavel("idInstituicao", idInstituicao);
			comando.configurar();

			getLogger().debug(comando.getSqlEmUso());

			conx = estabelecerConexao();
			rset = comando.executarConsulta(conx);

			while (rset.next()) {
				BemPessoaVO bem = new BemPessoaVO();
				bem.setAreaImovel(rset.getBigDecimal("QTDAREAIMOVEL"));
				bem.setBenfeitoriaImovel(rset.getString("DESCBENFEITORIA"));
				bem.setCodigoSituacaoBem(rset.getShort("CODSITUACAOBEMPESSOA"));
				bem.setCodigoSubTipoBem(rset.getShort("CODSUBTIPOBEM"));
				bem.setCodigoTipoBem(rset.getShort("CODTIPOBEM"));
				bem.setCodigoUnidadeMedidaImovel(rset.getShort("CODUNIDADEMEDIDA"));
				bem.setCodLocalizacaoImovel(rset.getString("CODLOCALIZACAO"));
				bem.setComarcaImovel(rset.getString("DESCCOMARCA"));
				bem.setDataHoraInicio(rset.getDate("DATAHORAINICIO"));
				bem.setDataVencimentoSeguro(rset.getDate("DATAVENCIMENTOSEGURO"));
				bem.setDenominacaoImovel(rset.getString("DESCDENOMINACAO"));
				bem.setDescricaoBem(rset.getString("DESCBEMPESSOA"));
				bem.setDescricaoSituacaoBem(rset.getString("DESCSITUACAOBEMPESSOA"));
				bem.setDescricaoSubTipoBem(rset.getString("DESCSUBTIPOBEM"));
				bem.setDescricaoTipoBem(rset.getString("DESCTIPOBEM"));
				bem.setDescricaoUnidadeMedidaImovel(rset.getString("DESCUNIDADEMEDIDA"));
				bem.setIdBem(rset.getLong("IDBEMPESSOA"));
				bem.setIdLocalidadeComarcaImovel(rset.getInt("IDLOCALIDADECOMARCA"));
				bem.setIdLocalidadeImovel(rset.getInt("IDLOCALIDADEIMOVEL"));
				bem.setIdPessoa(rset.getInt("IDPESSOA"));
				bem.setPercentual(rset.getBigDecimal("PERCPROPRIEDADE"));
				bem.setMunicipioImovel(rset.getString("DESCMUNICIPIOIMOVEL"));
				bem.setNomeSeguradora(rset.getString("NOMESEGURADORA"));
				bem.setSiglaUFComarcaImovel(rset.getString("SIGLAUFCOMARCA"));
				bem.setSiglaUFMunicipioImovel(rset.getString("SIGLAUFMUNICIPIOIMOVEL"));
				bem.setSiglaUnidadeMedidaImovel(rset.getString("SIGLAUNIDADEMEDIDA"));
				bem.setValorAtualMercado(rset.getBigDecimal("VALORATUALMERCADO"));
				bem.setValorBenfeitoriaImovel(rset.getBigDecimal("VALORBENFEITORIA"));
				bem.setValorPropriedade(rset.getBigDecimal("VALORPROPRIEDADE"));
				bem.setValorSeguro(rset.getBigDecimal("VALORSEGURO"));
				
				retorno.add(bem);
			}
			
			return retorno;
		} catch (SQLException e) {
			throw new PersistenciaException(e);
		} finally {
			fecharResultSet(rset);
			fecharConexao(conx);
			fecharComando(comando);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long obterIdBemNovo(Long idBemPessoa) throws BancoobException {
		Connection conx = null;
		ResultSet rset = null;
		Comando comando = null;
		Long retorno = null;

		try {
			comando = getComando("OBTER_ID_BEM_NOVO");
			comando.adicionarVariavel("idBemPessoa", idBemPessoa);
			comando.configurar();

			getLogger().debug(comando.getSqlEmUso());

			conx = estabelecerConexao();
			rset = comando.executarConsulta(conx);

			if (rset.next()) {
				retorno = rset.getLong("IDBEMNOVO");
			}

			return retorno;
		} catch (SQLException e) {
			throw new PersistenciaException(e);
		} finally {
			fecharResultSet(rset);
			fecharConexao(conx);
			fecharComando(comando);
		}
	}
}