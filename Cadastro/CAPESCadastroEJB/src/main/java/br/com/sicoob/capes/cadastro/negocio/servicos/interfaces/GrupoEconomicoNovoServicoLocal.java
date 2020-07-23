package br.com.sicoob.capes.cadastro.negocio.servicos.interfaces;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.servicos.GrupoEconomicoNovoServico;
import br.com.sicoob.capes.negocio.entidades.GrupoEconomicoNovo;
import br.com.sicoob.capes.negocio.entidades.vigente.RelacionamentoPessoa;

/**
 * A interface local para {@link GrupoEconomicoNovoServico}
 * @author Paulo.Stoppa
 *
 */
public interface GrupoEconomicoNovoServicoLocal extends GrupoEconomicoNovoServico {
	
	/**
	 * Inclui ou altera um grupo econômico
	 * @param relacionamento
	 * @throws BancoobException
	 */
	GrupoEconomicoNovo gravar(RelacionamentoPessoa relacionamento) throws BancoobException;
	
	/**
	 * Exclui um grupo econômico
	 * @param relacionamento
	 * @throws BancoobException
	 */
	void excluir(RelacionamentoPessoa relacionamento) throws BancoobException;

}