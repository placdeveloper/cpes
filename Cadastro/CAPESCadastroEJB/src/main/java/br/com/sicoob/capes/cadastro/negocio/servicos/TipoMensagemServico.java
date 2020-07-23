package br.com.sicoob.capes.cadastro.negocio.servicos;

import java.util.List;

import br.com.sicoob.capes.negocio.entidades.TipoMensagem;

/**
 * A Interface TipoMensagemServico.
 */
public interface TipoMensagemServico extends CAPESCadastroCrudServico<TipoMensagem> {

	/**
	 * Lista de tipo mensagens do tipo destino exibicao.
	 *
	 * @param codTipoDestinoExibicao o valor de cod tipo destino exibicao
	 * @return List
	 */
	List<TipoMensagem> listaDeTipoMensagensDoTipoDestinoExibicao(Short codTipoDestinoExibicao);

}