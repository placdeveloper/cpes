package br.com.sicoob.capes.cadastro.negocio.dominio.replicacao.facade;

import java.util.HashMap;
import java.util.Map;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.comum.negocio.enums.TipoAtualizacaoCadastralEnum;
import br.com.sicoob.capes.negocio.entidades.vigente.Email;

/**
 * A Classe AtualizacaoEmailFachada.
 */
public class AtualizacaoEmailFachada extends AtualizacaoCadastralFachada<Email> {
	
	/** A constante MAPA_ACAO. */
	private static final Map<TipoAtualizacaoCadastralEnum, TipoAtualizacaoCadastralEnum> MAPA_ACAO;
	
	static {
		MAPA_ACAO = new HashMap<TipoAtualizacaoCadastralEnum, TipoAtualizacaoCadastralEnum>();
		MAPA_ACAO.put(TipoAtualizacaoCadastralEnum.OPERACAO_ALTERACAO,
				TipoAtualizacaoCadastralEnum.OPERACAO_ALTERACAO_EMAIL_PESSOA);
		MAPA_ACAO.put(TipoAtualizacaoCadastralEnum.OPERACAO_INCLUSAO,
				TipoAtualizacaoCadastralEnum.OPERACAO_INCLUSAO_EMAIL_PESSOA);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void executarAtualizacao(Email entidade, final TipoAtualizacaoCadastralEnum tipoAtualizacao)
			throws BancoobException {
		
		TipoAtualizacaoCadastralEnum tipo = MAPA_ACAO.get(tipoAtualizacao);
		if (tipo != null) {
			super.executarAtualizacao(entidade, tipo);
		}
	}

	
}
