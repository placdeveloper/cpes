/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.persistencia.dao.MensagemDAO;
import br.com.sicoob.capes.negocio.entidades.Mensagem;

/**
 * Classe que implementa a persistencia de Mensagem.
 * 
 * @author Juan.Damasceno
 */
public class MensagemDAOImpl extends CAPESCadastroCrudDao<Mensagem> implements MensagemDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISA_MENSAGEM_POR_PESSOA";

	/** A constante LISTAR_VENCIDAS. */
	private static final String LISTAR_VENCIDAS = "LISTAR_MENSAGENS_VENCIDAS";

	/**
	 * Instancia um novo MensagemDAOImpl.
	 */
	public MensagemDAOImpl() {
		super(Mensagem.class, NOME_COMANDO_PESQUISAR);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Mensagem> listarVencidas(ConsultaDto<Mensagem> criterios) throws BancoobException {
		return pesquisarLista(getClasse(), criterios, LISTAR_VENCIDAS);
	}

}