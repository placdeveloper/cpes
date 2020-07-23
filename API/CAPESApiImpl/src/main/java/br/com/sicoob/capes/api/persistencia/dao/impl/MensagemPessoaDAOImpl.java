package br.com.sicoob.capes.api.persistencia.dao.impl;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.capes.api.negocio.vo.MensagemPessoaVO;
import br.com.sicoob.capes.api.persistencia.dao.MensagemPessoaDAO;

/**
 * A Classe MensagemPessoaDAOImpl.
 */
public class MensagemPessoaDAOImpl extends CAPESApiDAO<MensagemPessoaVO> implements MensagemPessoaDAO {

	/** A constante CODIGO_TIPO_MENSAGEM. */
	private static final String CODIGO_TIPO_MENSAGEM = "codigoTipoMensagem";

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<MensagemPessoaVO> obterMensagensAtivasPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao, Short codigoTipoMensagem)
			throws BancoobException {
		Comando comando = null;
		try {
			comando = getComando("PESQUISAR_MENSAGENS_ATIVAS_PESSOA");
			comando.adicionarVariavel(ID_PESSOA, idPessoa);
			comando.adicionarVariavel(ID_INSTITUICAO, idInstituicao);
			comando.adicionarVariavel(CODIGO_TIPO_MENSAGEM, codigoTipoMensagem);
			comando.configurar();

			return criarQuery(comando).getResultList();
		} finally {
			fecharComando(comando);
		}
	}

}