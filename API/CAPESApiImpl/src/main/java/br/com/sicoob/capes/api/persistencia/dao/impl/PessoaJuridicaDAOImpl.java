package br.com.sicoob.capes.api.persistencia.dao.impl;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.capes.api.negocio.vo.PessoaJuridicaVO;
import br.com.sicoob.capes.api.persistencia.dao.PessoaJuridicaDAO;

/**
 * A Classe PessoaJuridicaDAOImpl.
 */
public class PessoaJuridicaDAOImpl extends CAPESApiDAO<PessoaJuridicaVO> implements PessoaJuridicaDAO {

	/**
	 * Instancia um novo PessoaJuridicaDAOImpl.
	 */
	public PessoaJuridicaDAOImpl() {
		super("PESQUISAR_PESSOA_JURIDICA");
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<PessoaJuridicaVO> obterMatrizFiliais(String raizCNPJ, Integer idInstituicao) throws BancoobException {
		Comando comando = null;
		try {
			comando = getComando("OBTER_MATRIZ_FILIAIS");
			comando.adicionarVariavel("raizCNPJ", raizCNPJ);
			comando.adicionarVariavel("idInstituicao", idInstituicao);
			comando.configurar();

			return criarQuery(comando).getResultList();
		} finally {
			fecharComando(comando);
		}
	}
}