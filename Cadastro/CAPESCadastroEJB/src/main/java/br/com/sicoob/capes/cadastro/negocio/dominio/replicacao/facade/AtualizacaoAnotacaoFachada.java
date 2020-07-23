/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.dominio.replicacao.facade;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.comum.negocio.enums.TipoAnotacaoReplicavelEnum;
import br.com.sicoob.capes.comum.negocio.enums.TipoAtualizacaoCadastralEnum;
import br.com.sicoob.capes.negocio.entidades.Anotacao;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaFisica;

/**
 * Fachada utilizada para a replicação das anotações no cadastro da pessoa no legado.
 *  
 * @author Erico.Junior
 */
public class AtualizacaoAnotacaoFachada extends AtualizacaoCadastralFachada<Anotacao> {

	/**
	 * Executa a atualização das anotações no cadastro da pessoa no legado.
	 * @param anotacao A anotação que será verificada se reflete uma atualização no cadastro da 
	 * pessoa no legado ou não.
	 * @throws BancoobException Caso ocorra algum erro no envio da replicação.
	 */
	@Override
	public void executarAtualizacao(Anotacao anotacao, TipoAtualizacaoCadastralEnum tipoAtualizacao)
			throws BancoobException {
		
		Integer codigo = anotacao.getTipoAnotacao().getCodTipoAnotacao();
		if ((anotacao.getPessoaCompartilhamento() instanceof PessoaFisica)
				&& (TipoAnotacaoReplicavelEnum.valueOf(codigo) != null)) {
			super.executarAtualizacao(anotacao, TipoAtualizacaoCadastralEnum.OPERACAO_ALTERACAO);
		}
	}

	
}
