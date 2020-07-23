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
import br.com.sicoob.capes.api.negocio.vo.BemImovelParticipanteVO;
import br.com.sicoob.capes.api.negocio.vo.BemImovelVO;
import br.com.sicoob.capes.api.persistencia.dao.BemImovelDAO;

/**
 * Classe com as operações de bens imóveis.
 * 
 * @author Bruno.Carneiro
 */
public class BemImovelDAOImpl extends CAPESApiDAO<BemImovelVO> implements BemImovelDAO {

	/**
	 * Contrutor
	 */
	public BemImovelDAOImpl() {
		super("PESQUISAR_BEM_IMOVEL");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<BemImovelVO> obterPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao, boolean avancado, boolean avaliacao, Short codigoTipoBem) throws BancoobException {
		Connection conx = null;
		ResultSet rset = null;
		Comando comando = null;
		List<BemImovelVO> listaRetorno = new ArrayList<BemImovelVO>();

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
				BemImovelVO bem = preencherBemImovelProprietario(rset);
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
	public BemImovelVO obterPorIdBem(Long idBem) throws BancoobException {
		Connection conx = null;
		ResultSet rset = null;
		Comando comando = null;
		BemImovelVO bem = null;

		try {
			getLogger().debug("CAPESApiDAO obterPorIdBem - idBem:" + idBem);

			comando = getComando(getNomeComando());
			comando.adicionarVariavel("idBem", idBem);
			comando.configurar();

			getLogger().debug(comando.getSqlEmUso());

			conx = estabelecerConexao();
			rset = comando.executarConsulta(conx);

			if (rset.next()) {
				bem = preencherBemImovel(rset);
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
		List<BemImovelVO> retorno = new ArrayList<BemImovelVO>();

		try {

			comando = getComando("PESQUISAR_BEM_IMOVEL_POR_FILTRO");
			comando.adicionarVariavel(CRITERIOS, criterios);
			comando.configurar();

			getLogger().debug(comando.getSqlEmUso());

			conx = estabelecerConexao();
			rset = comando.executarConsulta(conx);

			while (rset.next()) {
				retorno.add(preencherBemImovel(rset));
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

			comando = getComando("PESQUISAR_BEM_IMOVEL_POR_FILTRO_PAGINADO");
			comando.adicionarVariavel(CRITERIOS, criterios);
			comando.adicionarVariavel("linhaInicial", obterValorInicialLinha(criterios));
			comando.adicionarVariavel("linhaFinal", obterValorFinalLinha(criterios));
			comando.configurar();

			getLogger().debug(comando.getSqlEmUso());

			conx = estabelecerConexao();
			rset = comando.executarConsulta(conx);

			while (rset.next()) {
				lista.add((K) preencherBemImovel(rset));
			}

			consultaDto.setResultado(lista);

			comandoQuantidade = getComando("CONTAR_BEM_IMOVEL_POR_FILTRO");
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
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<BemImovelParticipanteVO> obterParticipantes(Long idBem) throws BancoobException {
		Comando comando = null;
		try {
			comando = getComando("OBTER_PARTICIPANTES_BEM_IMOVEL");
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
	public List<BemImovelVO> obterTodosBensAssociados(Integer idPessoa, Integer idInstituicao) {
		Connection conx = null;
		ResultSet rset = null;
		Comando comando = null;
		List<BemImovelVO> listaRetorno = new ArrayList<BemImovelVO>();

		try {
			getLogger().debug("CAPESApiDAO obterTodosBensAssociados - idPessoa:" + idPessoa + " idInstituicao:" + idInstituicao);

			comando = getComando("OBTER_TODOS_OS_BENS_ASSOCIADOS");
			comando.adicionarVariavel(ID_PESSOA, idPessoa);
			comando.adicionarVariavel(ID_INSTITUICAO, idInstituicao);
			comando.configurar();

			getLogger().debug(comando.getSqlEmUso());

			conx = estabelecerConexao();
			rset = comando.executarConsulta(conx);

			while (rset.next()) {
				BemImovelVO bem = preencherBemImovel(rset);
				listaRetorno.add(bem);
			}

			getLogger().debug("CAPESApiDAO obterTodosBensAssociados - Retorno:" + listaRetorno);
			return listaRetorno;
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

	private BemImovelVO preencherBemImovel(ResultSet rset) throws SQLException {
		BemImovelVO bem = new BemImovelVO();
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

		// Bem imóvel
		bem.setInformacoesGerais(rset.getString("DESCINFORMACAOGERAL"));
		bem.setDenominacao(rset.getString("DESCDENOMINACAO"));
		bem.setNumero(rset.getString("DESCNUMERO"));
		bem.setComplemento(rset.getString("DESCCOMPLEMENTO"));
		bem.setIdLocalidade(rset.getInt("IDLOCALIDADE"));
		bem.setIdLogradouro(rset.getInt("IDLOGRADOURO"));
		bem.setDescricaoRoteiro(rset.getString("DESCROTEIRO"));
		bem.setLatitude(rset.getBigDecimal("VALORLATITUDE"));
		bem.setLongitude(rset.getBigDecimal("VALORLONGITUDE"));
		bem.setAreaTotal(rset.getBigDecimal("AREATOTALIMOVEL"));
		bem.setIdPessoaCartorio(rset.getLong("IDPESSOACARTORIO"));
		bem.setMatricula(rset.getString("DESCMATRICULA"));
		bem.setNirf(rset.getString("DESCNIRF"));
		bem.setIncra(rset.getString("DESCINCRA"));
		bem.setCodigoTipoBem(rset.getShort("CODTIPOBEMIMOVEL"));
		bem.setDescricaoTipoBem(rset.getString("DESCTIPOBEMIMOVEL"));
		bem.setCodigoUnidadeMedida(rset.getShort("CODUNIDADEMEDIDA"));
		bem.setDescricaoUnidadeMedida(rset.getString("DESCUNIDADEMEDIDA"));
		bem.setCodigoTipoLocalizacaoBem(rset.getShort("CODTIPOLOCALIZACAOBEM"));
		bem.setDescricaoTipoLocalizacaoBem(rset.getString("DESCTIPOLOCALIZACAOBEM"));
		bem.setCodigoTipoUsoBem(rset.getShort("CODTIPOUSOBEM"));
		bem.setDescricaoTipoUsoBem(rset.getString("DESCTIPOUSOBEM"));
		bem.setCodigoTipoImplantacaoBemImovel(rset.getShort("CODTIPOIMPLANTACAOBEMIMOVEL"));
		bem.setDescricaoTipoImplantacaoBemImovel(rset.getString("DESCTIPOIMPLANTACAOBEMIMOVEL"));
		bem.setCodigoTipoEstadoConservacao(rset.getShort("CODTIPOESTADOCONSERVACAOBEM"));
		bem.setDescricaoTipoEstadoConservacao(rset.getString("DESCTIPOESTADOCONSERVACAOBEM"));
		bem.setCodigoTipoPadraoAcabamentoBemImovel(rset.getShort("CODTIPOPADRAOACABAMENTOBEMIMOVEL"));
		bem.setDescricaoTipoPadraoAcabamentoBemImovel(rset.getString("DESCTIPOPADRAOACABAMENTOBEMIMOVEL"));
		bem.setCodigoTipoServicoCondominialBemImovel(rset.getShort("CODTIPOSERVICOCONDOMINIALBEMIMOVEL"));
		bem.setDescricaoTipoServicoCondominialBemImovel(rset.getString("DESCTIPOSERVICOCONDOMINIALBEMIMOVEL"));
		bem.setAreaPrivativa(rset.getBigDecimal("AREAPRIVATIVA"));
		bem.setAreaTerreno(rset.getBigDecimal("AREATERRENO"));
		bem.setAreaTestada(rset.getBigDecimal("AREATESTADA"));
		bem.setQuantidadeDormitorios(rset.getShort("QTDDORMITORIO"));
		bem.setQuantidadeVagasGaragem(rset.getShort("QTDVAGAGARAGEM"));
		bem.setBenfeitoria(rset.getString("DESCBENFEITORIA"));
		bem.setValorBenfeitoria(rset.getBigDecimal("VALORBENFEITORIA"));
		bem.setValorCompraVenda(rset.getBigDecimal("VALORCOMPRAVENDA"));
		bem.setDataCompraVenda(rset.getDate("DATACOMPRAVENDA"));
		bem.setValorAvaliacao(rset.getBigDecimal("VALORAVALIACAO"));
		bem.setDataAvaliacao(rset.getDate("DATAAVALIACAO"));
		bem.setProcessoAquisicao(rset.getBoolean("BOLEMPROCESSOAQUISICAO"));
		bem.setIdPessoaAvaliador(rset.getInt("IDPESSOAAVALIADOR"));
		
		return bem;
	}
	
	private BemImovelVO preencherBemImovelProprietario(ResultSet rset) throws SQLException {
		BemImovelVO bem = preencherBemImovel(rset);
		bem.setPercentualProprietario(rset.getBigDecimal("PERCPROPRIETARIO"));
		return bem;
	}

}