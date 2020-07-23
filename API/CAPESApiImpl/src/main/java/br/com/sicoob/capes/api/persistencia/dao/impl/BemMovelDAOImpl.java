package br.com.sicoob.capes.api.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.negocio.vo.BancoobVo;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.capes.api.negocio.filtros.FiltroConsultaAPIAbstrato;
import br.com.sicoob.capes.api.negocio.vo.BemMovelVO;
import br.com.sicoob.capes.api.persistencia.dao.BemMovelDAO;

/**
 * Classe com as operações de bens móveis.
 * 
 * @author Bruno.Carneiro
 */
public class BemMovelDAOImpl extends CAPESApiDAO<BemMovelVO> implements BemMovelDAO {

	/**
	 * Construtor
	 */
	public BemMovelDAOImpl() {
		super("PESQUISAR_BEM_MOVEL");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<BemMovelVO> obterPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao, boolean avancado, boolean avaliacao, Short codigoTipoBem) throws BancoobException {
		Connection conx = null;
		ResultSet rset = null;
		Comando comando = null;
		List<BemMovelVO> listaRetorno = new ArrayList<BemMovelVO>();

		try {
			getLogger().debug("CAPESApiDAO obterPorPessoaInstituicao - idPessoa:" + idPessoa + " idInstituicao:" + idInstituicao);

			comando = getComando(getNomeComando());
			comando.adicionarVariavel(ID_PESSOA, idPessoa);
			comando.adicionarVariavel(ID_INSTITUICAO, idInstituicao);
			comando.adicionarVariavel("avancado", avancado);
			comando.adicionarVariavel("avaliacao", avaliacao);
			comando.adicionarVariavel("codigoTipoBem", codigoTipoBem);
			comando.configurar();

			getLogger().debug(comando.getSqlEmUso());

			conx = estabelecerConexao();
			rset = comando.executarConsulta(conx);

			while (rset.next()) {
				BemMovelVO bem = preencherBemMovelProprietario(rset);
				listaRetorno.add(bem);
			}

			getLogger().debug("CAPESApiDAO obterPorPessoaInstituicao - Retorno:" + listaRetorno);
			return listaRetorno;
		} catch (SQLException e) {
			throw new PersistenciaException(e);
		} finally {
			fecharResultSet(rset);
			fecharConexao(conx);
			fecharComando(comando);
		}
	}

	@Override
	public BemMovelVO obterPorIdBem(Long idBem) throws BancoobException {
		Connection conx = null;
		ResultSet rset = null;
		Comando comando = null;
		BemMovelVO bem = null;

		try {
			getLogger().debug("CAPESApiDAO obterPorIdBem - idBem:" + idBem);

			comando = getComando(getNomeComando());
			comando.adicionarVariavel("idBem", idBem);
			comando.configurar();

			getLogger().debug(comando.getSqlEmUso());

			conx = estabelecerConexao();
			rset = comando.executarConsulta(conx);

			while (rset.next()) {
				bem = preencherBemMovel(rset);
			}

			getLogger().debug("CAPESApiDAO obterPorIdBem - Retorno:" + bem);
			return bem;
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
	@SuppressWarnings("unchecked")
	@Override
	public <K extends BancoobVo> List<K> pesquisar(ConsultaDto<? extends FiltroConsultaAPIAbstrato> criterios) throws BancoobException {
		Connection conx = null;
		ResultSet rset = null;
		Comando comando = null;
		List<BemMovelVO> retorno = new ArrayList<BemMovelVO>();

		try {

			comando = getComando("PESQUISAR_BEM_MOVEL_POR_FILTRO");
			comando.adicionarVariavel(CRITERIOS, criterios);
			comando.configurar();

			getLogger().debug(comando.getSqlEmUso());

			conx = estabelecerConexao();
			rset = comando.executarConsulta(conx);

			while (rset.next()) {
				retorno.add(preencherBemMovel(rset));
			}

			return (List<K>) retorno;
		} catch (SQLException e) {
			throw new PersistenciaException(e);
		} finally {
			fecharResultSet(rset);
			fecharConexao(conx);
			fecharComando(comando);
		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public <K extends BancoobVo> ConsultaDto<K> pesquisarPaginado(ConsultaDto<? extends FiltroConsultaAPIAbstrato> criterios) throws BancoobException {
		Connection conx = null;
		ResultSet rset = null;
		Comando comando = null;
		Comando comandoQuantidade = null;

		ConsultaDto<K> consultaDto = new ConsultaDto<K>();
		consultaDto.setTamanhoPagina(criterios.getTamanhoPagina());
		consultaDto.setPagina(criterios.getPagina());

		List<K> lista = new ArrayList<K>();

		try {

			comando = getComando("PESQUISAR_BEM_MOVEL_POR_FILTRO_PAGINADO");
			comando.adicionarVariavel(CRITERIOS, criterios);
			comando.adicionarVariavel("linhaInicial", obterValorInicialLinha(criterios));
			comando.adicionarVariavel("linhaFinal", obterValorFinalLinha(criterios));
			comando.configurar();

			getLogger().debug(comando.getSqlEmUso());

			conx = estabelecerConexao();
			rset = comando.executarConsulta(conx);

			while (rset.next()) {
				lista.add((K) preencherBemMovel(rset));
			}

			consultaDto.setResultado(lista);

			comandoQuantidade = getComando("CONTAR_BEM_MOVEL_POR_FILTRO");
			comandoQuantidade.adicionarVariavel(CRITERIOS, criterios);
			comandoQuantidade.configurar();
			Query queryQuantidade = comandoQuantidade.criarNativeQuery(getEntityManager());
			int quantidade = ((Number) queryQuantidade.getSingleResult()).intValue();

			consultaDto.setTotalRegistros(quantidade);

			return consultaDto;
		} catch (SQLException e) {
			throw new PersistenciaException(e);
		} finally {
			fecharResultSet(rset);
			fecharConexao(conx);
			fecharComando(comando);
		}
	}
	
	private Integer obterValorInicialLinha(ConsultaDto<? extends FiltroConsultaAPIAbstrato> criterios) {
		return criterios.getPagina() == 0 ? 0 : (criterios.getPagina() * criterios.getTamanhoPagina()) + 1;
	}
	
	private Integer obterValorFinalLinha(ConsultaDto<? extends FiltroConsultaAPIAbstrato> criterios) {
		return criterios.getPagina() == 0 ? criterios.getTamanhoPagina() : (criterios.getPagina() * criterios.getTamanhoPagina()) * 2;
	}

	/**
	 * Preencher bem móvel
	 * 
	 * @param rset
	 * @return
	 * @throws SQLException
	 */
	private BemMovelVO preencherBemMovel(ResultSet rset) throws SQLException {
		BemMovelVO bem = new BemMovelVO();

		// BEM
		bem.setIdBem(rset.getLong("IDBEM"));
		bem.setCodigoTipoClassificacaoBem(rset.getShort("CODTIPOCLASSIFICACAOBEM"));
		bem.setDescricaoTipoClassificacaoBem(rset.getString("DESCTIPOCLASSIFICACAOBEM"));
		bem.setInterno(rset.getBoolean("BOLBEMINTERNO"));
		bem.setDescricao(rset.getString("DESCBEM"));
		bem.setValor(rset.getBigDecimal("VALORBEM"));
		bem.setValorNaoInformado(rset.getBoolean("BOLVALORBEMNAOINFORMADO"));
		bem.setMesRenovacaoSeguro(rset.getShort("MESRENOVACAOSEGURO"));
		bem.setEmGarantia(rset.getBoolean("EM_GARANTIA"));
		bem.setIdUsuarioAprovacao(rset.getString("IDUSUARIOAPROV"));

		// BEM MÓVEL
		bem.setCodigoTipoBem(rset.getShort("CODTIPOBEMMOVEL"));
		bem.setDescricaoTipoBem(rset.getString("DESCTIPOBEMMOVEL"));
		bem.setCodigoTipoChassi(rset.getShort("CODTIPOCHASSIBEM"));
		bem.setDescricaoTipoChassi(rset.getString("DESCTIPOCHASSIBEM"));
		bem.setNumeroChassi(rset.getString("NUMCHASSI"));
		bem.setRenavam(rset.getString("NUMRENAVAN"));
		bem.setPlaca(rset.getString("DESCPLACA"));
		bem.setUf(rset.getString("SIGLAUF"));
		bem.setAnoFabricacaoAutomovel(rset.getShort("ANOFABRICACAOAUTOMOVEL"));
		bem.setAnoModeloAutomovel(rset.getShort("ANOMODELOAUTOMOVEL"));
		bem.setInscricaoEmbarcacao(rset.getString("DESCINCRICAOEMBARCACAO"));
		bem.setAnoConstrucaoEmbarcacao(rset.getShort("ANOCONSTRUCAOEMBARCACAO"));
		bem.setMatriculaAeronave(rset.getString("DESCMATRICULAAERONAVE"));
		bem.setAnoConstrucaoAeronave(rset.getShort("ANOCONSTRUCAOAERONAVE"));
		bem.setCodigoTipoCorAutomovel(rset.getShort("CODTIPOCORAUTOMOVEL"));
		bem.setDescricaoTipoCorAutomovel(rset.getString("DESCTIPOCORAUTOMOVEL"));
		bem.setValorCompraVenda(rset.getBigDecimal("VALORCOMPRAVENDA"));
		bem.setDataCompraVenda(rset.getDate("DATACOMPRAVENDA"));
		bem.setValorAvaliacao(rset.getBigDecimal("VALORAVALIACAO"));
		bem.setDataAvaliacao(rset.getDate("DATAAVALIACAO"));
		bem.setProcessoAquisicao(rset.getBoolean("BOLEMPROCESSOAQUISICAO"));
		bem.setIdPessoaAvaliador(rset.getInt("IDPESSOAAVALIADOR"));
		bem.setCodigoTipoEstadoConservacao(rset.getShort("CODTIPOESTADOCONSERVACAOBEM"));
		bem.setDescricaoTipoEstadoConservacao(rset.getString("DESCTIPOESTADOCONSERVACAOBEM"));
		return bem;
	}
	
	private BemMovelVO preencherBemMovelProprietario(ResultSet rset) throws SQLException {
		BemMovelVO bem = preencherBemMovel(rset);
		bem.setPercentualProprietario(rset.getBigDecimal("PERCPROPRIETARIO"));
		return bem;
	}
}