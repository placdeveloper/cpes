/*
 * SICOOB
 * 
 * ValidacaoCadastralDAOImpl.java(br.com.sicoob.capes.cadastro.persistencia.dao.impl.ValidacaoCadastralDAOImpl)
 */
package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import javax.persistence.Query;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.cadastro.persistencia.dao.ValidacaoCadastralDAO;
import br.com.sicoob.capes.negocio.entidades.ValidacaoCadastral;

/**
 * The Class ValidacaoCadastralDAOImpl.
 */
public class ValidacaoCadastralDAOImpl extends ValidacaoCadastralBaseDAO<ValidacaoCadastral>
        implements ValidacaoCadastralDAO {

	private static final String NOTIFICAR_CADASTROS = "NOTIFICAR_CADASTROS";
	private static final String REMOVER_NOTIFICACAO_CADASTROS = "REMOVER_NOTIFICACAO_CADASTROS";
	private static final long ANOTACAO_36_MESES_SEM_RENOVACAO_CADASTRO = Long.valueOf(519);

	/**
	 * Cria uma nova instância de validacao cadastral dao impl.
	 */
	public ValidacaoCadastralDAOImpl() {
		super(ValidacaoCadastral.class, "");
	}

	/**
	 * {@inheritDoc}
	 */
	public void atualizarDataUltimaValidacao(Long idPessoaCompartilhamento, DateTimeDB data)
	        throws BancoobException {

		Comando comando = null;
		try {
			comando = getComando("ATUALIZAR_DATA_ULTIMA_VALIDACAO");
			comando.adicionarVariavel("idPessoaCompartilhamento", idPessoaCompartilhamento);
			comando.adicionarVariavel("dataValidacao", data);
			comando.configurar();

			int atualizados = criarQuery(comando).executeUpdate();
			getLogger().info("Validacao cadastral: A data da ultima validacao foi atualizada: ",
			        String.valueOf(atualizados), " validacoes cadastral(is).");
		} finally {
			fecharComando(comando);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void verificarPendenciasPrazoRenovacaoCadastro() throws BancoobException {
		Comando comando = null;
		try {
			comando = getComando(NOTIFICAR_CADASTROS);
			comando.configurar();

			Query query = getEntityManager().createNativeQuery(comando.getSqlEmUso());
			query.setParameter(1, ANOTACAO_36_MESES_SEM_RENOVACAO_CADASTRO);
			int notificados = query.executeUpdate();
			getLogger().info(String.valueOf(notificados), ": cadastros alertados com mais de 36 meses sem renovar.");
			this.removerAnotacaoCadastro();
		} finally {
			fecharComando(comando);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	private void removerAnotacaoCadastro() throws BancoobException {
		Comando comando = null;
		try {
			comando = getComando(REMOVER_NOTIFICACAO_CADASTROS);
			comando.configurar();
			
			Query query = getEntityManager().createNativeQuery(comando.getSqlEmUso());
			query.setParameter(1, ANOTACAO_36_MESES_SEM_RENOVACAO_CADASTRO);
			
			int notificados = query.executeUpdate();
			getLogger().info(String.valueOf(notificados), ": anotações removidas com mais de 36 meses sem renovar.");
			
		} finally {
			fecharComando(comando);
		}
	}
}