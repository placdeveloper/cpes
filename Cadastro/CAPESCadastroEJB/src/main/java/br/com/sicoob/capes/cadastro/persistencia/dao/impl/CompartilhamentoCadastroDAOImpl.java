/* 
 * Sicoob
 * TipoPessoaDAOImpl.java 
 * Criado em: 30/06/2011
 */
package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.ViolacaoIntegridadeException;
import br.com.sicoob.capes.cadastro.negocio.excecao.CAPESCadastroNegocioException;
import br.com.sicoob.capes.cadastro.persistencia.dao.CompartilhamentoCadastroDAO;
import br.com.sicoob.capes.negocio.entidades.CompartilhamentoCadastro;

/**
 * 30/06/2011
 * 
 * @author Rodrigo.Chaves
 */
public class CompartilhamentoCadastroDAOImpl extends CAPESCadastroCrudDominioDao<CompartilhamentoCadastro> implements
	CompartilhamentoCadastroDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "";
	
	/** A constante NOME_COMANDO_PESQUISAR_PROXIMO_CODIGO. */
	private static final String NOME_COMANDO_PESQUISAR_PROXIMO_CODIGO = "PESQUISAR_PROXIMO_CODIGO_COMPARTILHAMENTO_CADASTRO";
	
	/**
	 * Construtor
	 * 
	 * @param clazz
	 * @param nomeComandoPesquisar
	 */
	public CompartilhamentoCadastroDAOImpl() {
		super(CompartilhamentoCadastro.class, NOME_COMANDO_PESQUISAR,NOME_COMANDO_PESQUISAR_PROXIMO_CODIGO);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<CompartilhamentoCadastro> listar(ConsultaDto<CompartilhamentoCadastro> criterios) throws BancoobException {
		throw new UnsupportedOperationException();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ConsultaDto<CompartilhamentoCadastro> pesquisar(ConsultaDto<CompartilhamentoCadastro> criterios)
			throws BancoobException {
		throw new UnsupportedOperationException();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void habilitarIntegracaoSRF(Integer codCompartilhamentoCadastro, boolean habilitarIntegracaoSRF) throws BancoobException {
		Comando comando = null;
		Connection conn = null;
		try {
			comando = getComando("HABILITAR_INTEGRACAO_SRF_COMPARTILHAMENTO_CADASTRO");
			comando.adicionarVariavel("habilitarIntegracao", habilitarIntegracaoSRF);
			comando.adicionarVariavel("codCompartilhamentoCadastro", codCompartilhamentoCadastro);
			comando.configurar();

			conn = estabelecerConexao();
			comando.executarAtualizacao(conn);
		} finally {
			fecharConexao(conn);
			fecharComando(comando);
		}
	}
	
	@Override
	public void excluir(Serializable chave) throws BancoobException {
		try {
			super.excluir(chave);
		} catch (ViolacaoIntegridadeException e) {
			throw new CAPESCadastroNegocioException("O grupo não pode ser excluído pois, existem informações vinculados à ele.");
		}
	}

}