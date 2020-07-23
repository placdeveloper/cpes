/*
 * SICOOB
 * 
 * ValidacaoCadastralRegraDAOImpl.java(br.com.sicoob.capes.cadastro.persistencia.dao.impl.ValidacaoCadastralRegraDAOImpl)
 */
package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.cadastro.persistencia.dao.ValidacaoCadastralRegraDAO;
import br.com.sicoob.capes.negocio.entidades.ValidacaoCadastralRegra;

/**
 * The Class ValidacaoCadastralRegraDAOImpl.
 */
public class ValidacaoCadastralRegraDAOImpl extends
        CAPESCadastroCrudDominioDao<ValidacaoCadastralRegra> implements ValidacaoCadastralRegraDAO {

	/**
	 * Cria uma nova instância de validacao cadastral regra dao impl.
	 */
	public ValidacaoCadastralRegraDAOImpl() {

		super(ValidacaoCadastralBaseDAO.ARQUIVO_QUERIES,
		        ValidacaoCadastralBaseDAO.NOME_COLECAO_COMANDOS, ValidacaoCadastralRegra.class,
		        "PESQUISAR_VALIDACAO_CADASTRAL_REGRAS_POR_FILTRO", 
		        "PESQUISAR_PROXIMO_CODIGO_REGRA_VALIDACAO");
	}

	/**
	 * {@inheritDoc}
	 */
	public void executarRegra(ValidacaoCadastralRegra regra, Long idPessoaCompartilhamento,
	        DateTimeDB dataValidacao) throws BancoobException {

		Comando comando = null;
		try {
			comando = getComando("COMANDO_REALIZAR_VALIDACAO");
			comando.adicionarVariavel("sql", escape(regra.getQuery()));
			comando.adicionarVariavel("idPessoaCompartilhamento", idPessoaCompartilhamento);
			comando.adicionarVariavel("dataValidacao", dataValidacao);
			comando.configurar();

			getLogger().debug("Validacao cadastral: Executando regra#",
			        String.valueOf(regra.getCodigoRegra()), "[", comando.getSqlEmUso(),
			        ", idPessoaCompartilhamento#", String.valueOf(idPessoaCompartilhamento), "]");

			Query query = comando.criarNativeQuery(getEntityManager());
			int erros = query.executeUpdate();
			getLogger().info("Validacao cadastral: A regra#", String.valueOf(regra.getCodigoRegra()), " gerou ", String.valueOf(erros), " erro(s).");
		} finally {
			fecharComando(comando);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void executarRegra(ValidacaoCadastralRegra regra) throws BancoobException {
		executarRegra(regra, null, null);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void executarRegraCategoriaCadastro() throws BancoobException {

		Comando comando = null;
		try {
			comando = getComando("COMANDO_REALIZAR_VALIDACAO_CATEGORIA_CADASTRO");
			comando.configurar();

			getLogger().debug("Regra de categoria do cadastro: Executando...");

			Query query = comando.criarNativeQuery(getEntityManager());
			int rows = query.executeUpdate();
			getLogger().info("Regra de categoria do cadastro: gerou ", String.valueOf(rows), " rows.");
		} finally {
			fecharComando(comando);
		}
	}


	/** 
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<ValidacaoCadastralRegra> listarRegrasExecutaveis() throws BancoobException {
		
		Comando comando = null;
		try {
			comando = getComando("LISTAR_REGRAS_EXECUTAVEIS");
			comando.configurar();
			return comando.criarQuery(getEntityManager()).getResultList();
		} finally {
			fecharComando(comando);
		}
	}
	
	/** 
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<ValidacaoCadastralRegra> listarRegrasExecutaveis(Short ordem) throws BancoobException {
		
		Comando comando = null;
		try {
			comando = getComando("LISTAR_REGRAS_EXECUTAVEIS_PERFIL_CADASTRAL");
			comando.adicionarVariavel("ordem", ordem);
			comando.configurar();
			return comando.criarQuery(getEntityManager()).getResultList();
		} finally {
			fecharComando(comando);
		}
	}
	/**
	 * Realiza "escape" na query, para remover comandos de INSERT, UPDATE e/ou DELETE. Apenas o SELECT é permitido.
	 * 
	 * @param query
	 *            a query
	 * @return A query sem os comandos não permitidos;
	 */
	private String escape(String query) {

		String escape = query;
		escape = StringUtils.replace(escape, "INSERT", "--INSERT");
		escape = StringUtils.replace(escape, "DELETE", "--DELETE");
		escape = StringUtils.replace(escape, "UPDATE", "--UPDATE");
		return escape;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<ValidacaoCadastralRegra> listarRegrasExecutaveisRevalidacao() throws BancoobException {
		Comando comando = null;
		try {
			comando = getComando("LISTAR_REGRAS_EXECUTAVEIS_REVALIDACAO");
			comando.configurar();
			return comando.criarQuery(getEntityManager()).getResultList();
		} finally {
			fecharComando(comando);
		}
	}
}