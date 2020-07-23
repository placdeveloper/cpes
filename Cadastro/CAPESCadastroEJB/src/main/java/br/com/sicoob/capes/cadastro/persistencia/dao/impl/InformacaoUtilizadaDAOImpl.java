package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.capes.cadastro.persistencia.dao.InformacaoUtilizadaDAO;
import br.com.sicoob.capes.negocio.entidades.InformacaoUtilizada;

/**
 * A Classe InformacaoUtilizadaDAOImpl.
 */
public class InformacaoUtilizadaDAOImpl extends CAPESCadastroCrudDao<InformacaoUtilizada> implements InformacaoUtilizadaDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "OBTER_INFORMACAO_UTILIZADA";
	
	/**
	 * Instancia um novo InformacaoUtilizadaDAOImpl.
	 */
	public InformacaoUtilizadaDAOImpl() {
		super(InformacaoUtilizada.class, NOME_COMANDO_PESQUISAR);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<String> listarSistemasUsandoInformacao(Short codigoTipoInformacao, Long codigoInformacao) throws BancoobException {
		Comando comando = null;
		try {
			comando = getComando("CONSULTAR_INFORMACAO_UTILIZADA");
			comando.adicionarVariavel("codigoTipoInformacao", codigoTipoInformacao);
			comando.adicionarVariavel("codigoInformacao", codigoInformacao);
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
	public void excluirEntidade(InformacaoUtilizada objeto) throws BancoobException {
		InformacaoUtilizada objetoPersistente = obter(objeto.getId());
		if (objetoPersistente != null) {
			getEntityManager().remove(getEntityManager().getReference(objeto.getClass(), objeto.getId()));
		}
	}
}