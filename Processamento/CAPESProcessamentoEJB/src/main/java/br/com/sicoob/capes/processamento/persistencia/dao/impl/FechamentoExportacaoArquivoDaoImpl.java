package br.com.sicoob.capes.processamento.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.excecao.BancoobRuntimeException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.comum.negocio.enums.TipoInformacaoEnum;
import br.com.sicoob.capes.comum.util.Constantes;
import br.com.sicoob.capes.negocio.entidades.TipoInformacao;
import br.com.sicoob.capes.processamento.negocio.dominio.exportacaoarquivo.vo.RegistroDadosArquivoBaseVO;
import br.com.sicoob.capes.processamento.negocio.vo.CooperativaVO;
import br.com.sicoob.capes.processamento.persistencia.dao.CAPESProcessamentoDao;
import br.com.sicoob.capes.processamento.persistencia.dao.FechamentoExportacaoArquivoDao;
import br.com.sicoob.capes.processamento.persistencia.dao.enums.ComplementoTipoInformacaoEnum;
import br.com.sicoob.capes.processamento.persistencia.transformador.TranformadorResultado;

/**
 * @since 1.2.1.0
 * @author rodrigo.chaves
 * 
 */
public class FechamentoExportacaoArquivoDaoImpl extends CAPESProcessamentoDao implements FechamentoExportacaoArquivoDao {

	/** A constante NOME_COLECAO_COMANDOS. */
	private static final String NOME_COLECAO_COMANDOS = "comandos-exportacao.arquivo";
	
	/** A constante ARQUIVO_QUERIES. */
	private static final String ARQUIVO_QUERIES = "capes.exportacao.arquivo.queries.xml";
	
	/** A constante COMANDOS. */
	private static final Map<TipoInformacaoEnum, String> COMANDOS;

	static {
		COMANDOS = new HashMap<TipoInformacaoEnum, String>();
		COMANDOS.put(TipoInformacaoEnum.PESSOA, 		"PESQUISA_PESSOA");
		COMANDOS.put(TipoInformacaoEnum.ENDERECO, 		"PESQUISA_ENDERECO");
		COMANDOS.put(TipoInformacaoEnum.TELEFONE, 		"PESQUISA_TELEFONE");
		COMANDOS.put(TipoInformacaoEnum.EMAIL, 			"PESQUISA_EMAIL");
		COMANDOS.put(TipoInformacaoEnum.FONTE_RENDA, 	"PESQUISA_RENDA");
		COMANDOS.put(TipoInformacaoEnum.BEM, 			"PESQUISA_BEM");
		COMANDOS.put(TipoInformacaoEnum.PAC, 			"PESQUISA_COOPERATIVA");
	}

	/**
	 * Construtor
	 */
	public FechamentoExportacaoArquivoDaoImpl() {
		super(Constantes.Persistencia.DATASOURCE_CAPES, ARQUIVO_QUERIES, NOME_COLECAO_COMANDOS);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<RegistroDadosArquivoBaseVO> pesquisarDadosExportacao(TipoInformacaoEnum tipoInformacao, DateTimeDB dataMovimento) throws BancoobException {
		return pesquisarDadosExportacao(tipoInformacao, dataMovimento, null);
	}
	
	/**
	 * define o manager padrão para o dao.
	 * @param manager
	 */
	@Override
	@PersistenceContext(unitName = "emCapes")
	public void setEntityManager(EntityManager manager) {
		super.setEntityManager(manager);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<RegistroDadosArquivoBaseVO> pesquisarDadosExportacao(TipoInformacaoEnum tipoInformacao, DateTimeDB dataMovimento, List<TipoInformacao> listaTipos) throws BancoobException {
		List<RegistroDadosArquivoBaseVO> resultado = new ArrayList<RegistroDadosArquivoBaseVO>();
		ComplementoTipoInformacaoEnum complementoTipoInformacaoEnum = ComplementoTipoInformacaoEnum.valueOf(tipoInformacao);

		if (complementoTipoInformacaoEnum != null) {
			if (ComplementoTipoInformacaoEnum.TipoQuery.HQL.equals(complementoTipoInformacaoEnum.getTipoQuery())) {
				resultado = obterResultadoHQL(tipoInformacao, dataMovimento);
			} else if (ComplementoTipoInformacaoEnum.TipoQuery.SQL.equals(complementoTipoInformacaoEnum.getTipoQuery())) {
				resultado = obterResultadoSQL(tipoInformacao, dataMovimento, listaTipos);
			}
		}
		
		return resultado;
	}
	
	/**
	 * Obter resultado hql.
	 *
	 * @param tipoInformacao o valor de tipo informacao
	 * @param dataMovimento o valor de data movimento
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	@SuppressWarnings("unchecked")
	private List<RegistroDadosArquivoBaseVO> obterResultadoHQL(TipoInformacaoEnum tipoInformacao, DateTimeDB dataMovimento) throws BancoobException {
		Query query = null;
		List<RegistroDadosArquivoBaseVO> resultado = new ArrayList<RegistroDadosArquivoBaseVO>();

		Comando comando = getComando(COMANDOS.get(tipoInformacao));
		comando.adicionarVariavel("dataMovimento", dataMovimento);
		comando.configurar();

		query = criarQuery(comando);
		query.setParameter("parametro0", dataMovimento);
		resultado.addAll(query.getResultList());

		return resultado;
	}
	
	/**
	 * Obter resultado sql.
	 *
	 * @param tipoInformacao o valor de tipo informacao
	 * @param dataMovimento o valor de data movimento
	 * @param listaTipos o valor de lista tipos
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	@SuppressWarnings("unchecked")
	private List<RegistroDadosArquivoBaseVO> obterResultadoSQL(TipoInformacaoEnum tipoInformacao, DateTimeDB dataMovimento, List<TipoInformacao> listaTipos) throws BancoobException {
		ResultSet rset = null;
		Connection con = null;
		Comando comando = null;
		List<RegistroDadosArquivoBaseVO> retorno = new ArrayList<RegistroDadosArquivoBaseVO>();
		ComplementoTipoInformacaoEnum complementoTipoInformacaoEnum = ComplementoTipoInformacaoEnum.valueOf(tipoInformacao);

		if (complementoTipoInformacaoEnum != null) {
			try {
				con = estabelecerConexao();
				comando = getComando(COMANDOS.get(tipoInformacao));
				comando.adicionarVariavel("dataMovimento", dataMovimento);
				
				if(listaTipos != null && !listaTipos.isEmpty() && TipoInformacaoEnum.PESSOA.equals(tipoInformacao)){
					comando.adicionarVariavel("complementoFrom", obterComplementoFrom(listaTipos));
					comando.adicionarVariavel("complementoWhere", obterComplementoWhere(listaTipos));
				}
				
				comando.configurar();

				getLogger().debug(comando.getSqlEmUso());
				rset = comando.executarConsulta(con);

				TranformadorResultado<?> transformador = complementoTipoInformacaoEnum.getTransformador().newInstance();
				retorno = (List<RegistroDadosArquivoBaseVO>) transformador.transformar(rset);
			} catch (InstantiationException e) {
				throw new BancoobRuntimeException(e);
			} catch (IllegalAccessException e) {
				throw new BancoobRuntimeException(e);
			} finally {
				fecharResultSet(rset);
				fecharConexao(con);
				fecharComando(comando);
			}
		}

		return retorno;
	}
	
	/**
	 * Obtém os complementos do 'FROM' da consulta de pessoa, de acordo com o os
	 * tipos de informação que estão selecionados no destino para saber qual
	 * pessoa deverá ser trazida, evitando trazer, por exemplo, um endereço sem
	 * a pessoa relacionada.
	 * 
	 * @param listaTipos
	 *            Os tipos selecionados no destino da exportação
	 * @return {@code String}
	 * 			  O complemento da cláusula 'FROM' da consulta de pessoa
	 */
	private String obterComplementoFrom(List<TipoInformacao> listaTipos) {
		StringBuilder sb = new StringBuilder(); 
		for (TipoInformacao tipoInformacao : listaTipos) {
			TipoInformacaoEnum tipoEnum = TipoInformacaoEnum.valueOf(tipoInformacao.getCodigo());
			sb.append(ComplementoTipoInformacaoEnum.valueOf(tipoEnum).getComplementoFrom());
		}
		return sb.toString();
	}
	
	/**
	 * Obtém os complementos do 'WHERE' da consulta de pessoa, de acordo com o
	 * os tipos de informação que estão selecionados no destino para saber qual
	 * pessoa deverá ser trazida, evitando trazer, por exemplo, um endereço sem
	 * a pessoa relacionada.
	 * 
	 * @param listaTipos
	 *            Os tipos selecionados no destino da exportação
	 * @return {@code String} 
	 * 			  O complemento da cláusula 'WHERE' da consulta de pessoa
	 */
	private String obterComplementoWhere(List<TipoInformacao> listaTipos) {
		StringBuilder sb = new StringBuilder(); 
		for (TipoInformacao tipoInformacao : listaTipos) {
			TipoInformacaoEnum tipoEnum = TipoInformacaoEnum.valueOf(tipoInformacao.getCodigo());
			sb.append(ComplementoTipoInformacaoEnum.valueOf(tipoEnum).getComplementoWhere());
		}
		return sb.toString();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void criarTabelaTemporariaPacBancoob() throws BancoobException {
		Comando comando = null;
		try {
			comando = getComando("CRIAR_TABELA_TEMPORARIA_PACS_BANCOOB");
			comando.configurar();
			
			getLogger().debug(comando.getSqlEmUso());

			Query query = getEntityManager().createNativeQuery(comando.getSqlEmUso());
			query.executeUpdate();
		} finally {
			fecharComando(comando);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deletarTabelaTemporariaPacBancoob() throws BancoobException{
		Comando comando = null;
		try {
			comando = getComando("DELETAR_TABELA_TEMPORARIA_PACS_BANCOOB");
			comando.configurar();
			
			getLogger().debug(comando.getSqlEmUso());

			Query query = getEntityManager().createNativeQuery(comando.getSqlEmUso());
			query.executeUpdate();
		} finally {
			fecharComando(comando);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void inserirPacsTabelaTemporaria(List<CooperativaVO> cooperativas) throws BancoobException {
		Comando comando = null;
		try {
			comando = getComando("INSERIR_TABELA_TEMPORARIA_PACS_BANCOOB");
			comando.configurar();
			
			for(CooperativaVO cooperativa : cooperativas){
				Query query = getEntityManager().createNativeQuery(comando.getSqlEmUso());
				query.setParameter(1, cooperativa.getIdInstituicao());
				query.setParameter(2, cooperativa.getIdUnidadeInst());
				query.setParameter(3, cooperativa.getNumeroCooperativa());
				query.setParameter(4, cooperativa.getNumeroPac());
				query.executeUpdate();
			}
		} finally {
			fecharComando(comando);
		}
	}

}