/*
 * SICOOB
 * 
 * ValidacaoCadastralErroDAOImpl.java(br.com.sicoob.capes.cadastro.persistencia.dao.impl.ValidacaoCadastralErroDAOImpl)
 */
package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.cadastro.negocio.vo.ValidacaoCadastralVO;
import br.com.sicoob.capes.cadastro.persistencia.dao.ValidacaoCadastralErroDAO;
import br.com.sicoob.capes.comum.negocio.enums.TipoRegraValidacaoCadastralEnum;
import br.com.sicoob.capes.negocio.entidades.ValidacaoCadastralErro;
import br.com.sicoob.capes.negocio.entidades.ValidacaoCadastralRegra;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * The Class ValidacaoCadastralErroDAOImpl.
 */
public class ValidacaoCadastralErroDAOImpl extends
        ValidacaoCadastralBaseDAO<ValidacaoCadastralErro> implements ValidacaoCadastralErroDAO {

	/** A constante COMANDO_OBTER_PENDENCIAS_PESSOA_RELACIONADA. */
	private static final String COMANDO_OBTER_PENDENCIAS_PESSOA_RELACIONADA = "OBTER_PENDENCIAS_PESSOA_RELACIONADA";
	
	/**
	 * Cria uma nova instância de validacao cadastral erro dao impl.
	 */
	public ValidacaoCadastralErroDAOImpl() {

		super(ValidacaoCadastralErro.class, "CONSULTAR_ERROS_POR_FILTRO");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deletarErrosPessoa(Long idPessoaCompartilhamento) throws BancoobException {

		Comando comando = null;
		try {
			comando = getComando("DELETAR_ERROS_PESSOA");
			comando.adicionarVariavel("idPessoaCompartilhamento", idPessoaCompartilhamento);
			comando.configurar();

			int deletados = comando.criarQuery(getEntityManager()).executeUpdate();

			getLogger().debug("Validacao cadastral: ", String.valueOf(deletados), " erro(s) deletado(s) da pessoa ",
					String.valueOf(idPessoaCompartilhamento));
		} finally {
			fecharComando(comando);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deletarErrosRegra(Short codigoRegra) throws BancoobException {
		
		Comando comando = null;
		try {
			comando = getComando("DELETAR_ERROS_REGRA");
			comando.adicionarVariavel("codigoRegra", codigoRegra);
			comando.configurar();
			
			int deletados = comando.criarQuery(getEntityManager()).executeUpdate();
			
			getLogger().debug("Validacao cadastral: ", String.valueOf(deletados), " erro(s) deletado(s) da regra ",
					String.valueOf(codigoRegra));
		} finally {
			fecharComando(comando);
		}
	}
	
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void deletarErros(Short codigoRegra, DateTimeDB dataValidacao) throws BancoobException {
		
		Comando comando = null;
		try {
			comando = getComando("DELETAR_ERROS");
			comando.adicionarVariavel("codigoRegra", codigoRegra);
			comando.adicionarVariavel("dataValidacao", dataValidacao);
			comando.configurar();
			int deletados = comando.criarQuery(getEntityManager()).executeUpdate();
			getLogger().debug("Validacao cadastral: ", String.valueOf(deletados),
			        " erro(s) deletado(s)");
		} finally {
			fecharComando(comando);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<ValidacaoCadastralVO> listarFalhasRegrasValidacaoCadastral(Long idPessoaCompartilhamento, Integer idInstituicao, TipoRegraValidacaoCadastralEnum tipoRegra)
			throws BancoobException {
	
		Comando comando = null;
		try {
			comando = getComando("LISTAR_FALHAS_REGRAS_VALIDACAO_CADASTRAL");
			comando.adicionarVariavel("idPessoaCompartilhamento", idPessoaCompartilhamento);
			comando.adicionarVariavel("idInstituicao", idInstituicao);
			comando.adicionarVariavel("tipoRegra", tipoRegra != null ? tipoRegra.getCodigo() : null);
			comando.configurar();
			return comando.criarQuery(getEntityManager()).getResultList();
		} finally {
			fecharComando(comando);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<ValidacaoCadastralVO> listarFalhasRegrasValidacaoCadastral(Long idPessoaCompartilhamento, Integer idInstituicao, TipoRegraValidacaoCadastralEnum tipoRegra, Short codPerfilCadastro)
			throws BancoobException {
	
		Comando comando = null;
		try {
			comando = getComando("LISTAR_FALHAS_REGRAS_VALIDACAO_CADASTRAL");
			comando.adicionarVariavel("idPessoaCompartilhamento", idPessoaCompartilhamento);
			comando.adicionarVariavel("idInstituicao", idInstituicao);
			comando.adicionarVariavel("tipoRegra", tipoRegra != null ? tipoRegra.getCodigo() : null);
			comando.adicionarVariavel("codigoPerfilCadastro", codPerfilCadastro);
			
			comando.configurar();
			return comando.criarQuery(getEntityManager()).getResultList();
		} finally {
			fecharComando(comando);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isPossuiRegraCadastralRestritiva(Long idPessoaCompartilhamento, Integer idInstituicao, Short codigoPerfilCadastro) throws BancoobException {
		Comando comando = null;
		try {
			comando = getComando("VERIFICA_SE_POSSUI_REGRAS_CADASTRAIS_RESTRITIVA");
			comando.adicionarVariavel("idPessoaCompartilhamento", idPessoaCompartilhamento);
			comando.adicionarVariavel("idInstituicao", idInstituicao);
			comando.adicionarVariavel("codigoPerfilCadastro", codigoPerfilCadastro);
			comando.configurar();
			Long quantidadeRestricoes = (Long) comando.criarQuery(getEntityManager()).getSingleResult();
			return quantidadeRestricoes != 0;
		} finally {
			fecharComando(comando);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isExisteErro(Short codigoRegra) throws BancoobException {
		
		if (codigoRegra == null) {
			throw new IllegalArgumentException("codigoRegra");
		}
		
		ValidacaoCadastralRegra regra = new ValidacaoCadastralRegra();
		regra.setCodigoRegra(codigoRegra);
		
		ValidacaoCadastralErro filtro = new ValidacaoCadastralErro();
		filtro.setRegra(regra);
		
		ConsultaDto<ValidacaoCadastralErro> criterios = new ConsultaDto<ValidacaoCadastralErro>();
		criterios.setFiltro(filtro);
		
		Long qtd = pesquisarNumeroRegistros(criterios, getNomeComandoPesquisar(), true);
		
		return (qtd != null) && (qtd > 0);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<String> listarPendenciasPessoaRelacionada(PessoaCompartilhamento pessoa, Integer idInstituicao) throws BancoobException {
		Connection conexao = null;
		ResultSet rset = null;
		Comando comando = null;
		List<String> lista = new ArrayList<String>();
		try {
			conexao = estabelecerConexao();
			comando = getComando(COMANDO_OBTER_PENDENCIAS_PESSOA_RELACIONADA);
			comando.adicionarVariavel("idPessoaCompartilhamento", pessoa.getId());
			comando.adicionarVariavel("idInstituicao", idInstituicao);
			comando.configurar();

			rset = comando.executarConsulta(conexao);
			while (rset.next()) {
				lista.add(rset.getString("MENSAGEM"));
			}
		} catch (SQLException excecao) {
			throw new PersistenciaException(excecao);
		} finally {
			fecharResultSet(rset);
			fecharConexao(conexao);
			fecharComando(comando);
		}
		return lista;
	}
}