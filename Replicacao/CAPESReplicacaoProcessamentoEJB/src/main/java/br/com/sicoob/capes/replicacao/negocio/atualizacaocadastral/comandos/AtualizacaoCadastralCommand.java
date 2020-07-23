/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.atualizacaocadastral.comandos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.legado.interfaces.Replicavel;

/**
 * Comando para as atualiza��es cadastrais.
 * @author Erico.Junior
 */
public interface AtualizacaoCadastralCommand<R extends Replicavel> {

	/**
	 * Executa o comando.
	 * @param replicavel A entidade para a atualiza��o cadastral.
	 * @param instituicao A institui��o onde ser� atualizado o cadastro.
	 * @throws BancoobException Caso ocorra alguma exce��o na atualiza��.
	 */
	void executar(R replicavel, Instituicao instituicao) 
			throws BancoobException;
}
