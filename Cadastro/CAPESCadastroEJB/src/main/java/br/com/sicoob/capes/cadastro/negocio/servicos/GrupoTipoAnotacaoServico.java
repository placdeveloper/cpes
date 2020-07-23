package br.com.sicoob.capes.cadastro.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.GrupoTipoAnotacao;
import br.com.sicoob.capes.negocio.entidades.TipoAnotacao;

/**
 * A interface com os serviços de grupo tipo anotação
 * 
 * @author Bruno.Carneiro
 */
public interface GrupoTipoAnotacaoServico extends CAPESCadastroCrudServico<GrupoTipoAnotacao> {

	/**
	 * Obtém os grupos de tipo de anotação por idTipoAnotacao
	 * 
	 * @param idTipoAnotacao
	 * @return
	 * @throws BancoobException
	 */
	List<GrupoTipoAnotacao> obterGruposPorTipoAnotacao(Integer idTipoAnotacao) throws BancoobException;
	
	/**
	 * Obtém os grupos de tipo de anotação por tipos de anotações.
	 * 
	 * @param tiposAnotacao
	 * @return
	 * @throws BancoobException
	 */
	List<GrupoTipoAnotacao> obterGruposPorTiposAnotacao(List<TipoAnotacao> tiposAnotacao) throws BancoobException;

}