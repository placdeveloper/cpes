package br.com.sicoob.capes.cadastro.persistencia.dao;

import java.util.List;

import br.com.sicoob.capes.negocio.entidades.TipoMensagem;

/**
 * A Interface TipoMensagemDAO.
 */
public interface TipoMensagemDAO extends CAPESCadastroCrudDaoIF<TipoMensagem> {

	/**
	 * Lista de tipo mensagens do tipo destino exibicao.
	 *
	 * @param codTipoDestinoExibicao o valor de cod tipo destino exibicao
	 * @return List
	 */
	List<TipoMensagem> listaDeTipoMensagensDoTipoDestinoExibicao(Short codTipoDestinoExibicao);

}
