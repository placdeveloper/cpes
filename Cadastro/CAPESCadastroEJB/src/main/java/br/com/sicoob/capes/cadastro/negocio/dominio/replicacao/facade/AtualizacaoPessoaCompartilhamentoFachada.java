package br.com.sicoob.capes.cadastro.negocio.dominio.replicacao.facade;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.dto.AvalFinanceiraDTO;
import br.com.sicoob.capes.comum.negocio.enums.TipoAtualizacaoCadastralEnum;

/**
 * A Classe AtualizacaoPessoaCompartilhamentoFachada.
 */
public class AtualizacaoPessoaCompartilhamentoFachada extends AtualizacaoCadastralFachada<AvalFinanceiraDTO> {
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void executarAtualizacao(AvalFinanceiraDTO entidade,
			TipoAtualizacaoCadastralEnum tipoAtualizacao)
			throws BancoobException {
		super.executarAtualizacao(entidade, TipoAtualizacaoCadastralEnum.OPERACAO_ALTERACAO_AVAL_FINANCEIRA);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean isEntidadeReplicavel(AvalFinanceiraDTO entidade) {
		return true;
	}

}
