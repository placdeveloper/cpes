/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.dominio.replicacao.facade;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.comum.negocio.enums.TipoAtualizacaoCadastralEnum;
import br.com.sicoob.capes.negocio.entidades.GrupoEconomico;
import br.com.sicoob.capes.negocio.entidades.Instituicao;

/**
 * @author Rodrigo.Chaves
 * 09/02/2012
 */
public class GrupoEconomicoFachada extends AtualizacaoCadastralFachada<GrupoEconomico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void executarAtualizacao(GrupoEconomico entidade,
			TipoAtualizacaoCadastralEnum acao) throws BancoobException {
		
		Instituicao instituicao = new Instituicao();
		instituicao.setIdInstituicao(entidade.getIdInstituicao());
		
		salvarAtualizacaoCadastral(entidade, instituicao, null, acao);
	}
}
