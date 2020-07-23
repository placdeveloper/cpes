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
 * Fachada utilizada para a replica��o das anota��es no cadastro da pessoa no legado.
 *  
 * @author Erico.Junior
 */
public class AtualizacaoAnotacaoFachada extends AtualizacaoCadastralFachada<Anotacao> {

	/**
	 * Executa a atualiza��o das anota��es no cadastro da pessoa no legado.
	 * @param anotacao A anota��o que ser� verificada se reflete uma atualiza��o no cadastro da 
	 * pessoa no legado ou n�o.
	 * @throws BancoobException Caso ocorra algum erro no envio da replica��o.
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
