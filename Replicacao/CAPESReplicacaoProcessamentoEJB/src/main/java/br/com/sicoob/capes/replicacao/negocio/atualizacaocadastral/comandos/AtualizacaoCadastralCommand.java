/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.atualizacaocadastral.comandos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.legado.interfaces.Replicavel;

/**
 * Comando para as atualizações cadastrais.
 * @author Erico.Junior
 */
public interface AtualizacaoCadastralCommand<R extends Replicavel> {

	/**
	 * Executa o comando.
	 * @param replicavel A entidade para a atualização cadastral.
	 * @param instituicao A instituição onde será atualizado o cadastro.
	 * @throws BancoobException Caso ocorra alguma exceção na atualizaçã.
	 */
	void executar(R replicavel, Instituicao instituicao) 
			throws BancoobException;
}
