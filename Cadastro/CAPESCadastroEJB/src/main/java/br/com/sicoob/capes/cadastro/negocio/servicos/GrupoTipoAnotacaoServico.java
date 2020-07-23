package br.com.sicoob.capes.cadastro.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.GrupoTipoAnotacao;
import br.com.sicoob.capes.negocio.entidades.TipoAnotacao;

/**
 * A interface com os servi�os de grupo tipo anota��o
 * 
 * @author Bruno.Carneiro
 */
public interface GrupoTipoAnotacaoServico extends CAPESCadastroCrudServico<GrupoTipoAnotacao> {

	/**
	 * Obt�m os grupos de tipo de anota��o por idTipoAnotacao
	 * 
	 * @param idTipoAnotacao
	 * @return
	 * @throws BancoobException
	 */
	List<GrupoTipoAnotacao> obterGruposPorTipoAnotacao(Integer idTipoAnotacao) throws BancoobException;
	
	/**
	 * Obt�m os grupos de tipo de anota��o por tipos de anota��es.
	 * 
	 * @param tiposAnotacao
	 * @return
	 * @throws BancoobException
	 */
	List<GrupoTipoAnotacao> obterGruposPorTiposAnotacao(List<TipoAnotacao> tiposAnotacao) throws BancoobException;

}