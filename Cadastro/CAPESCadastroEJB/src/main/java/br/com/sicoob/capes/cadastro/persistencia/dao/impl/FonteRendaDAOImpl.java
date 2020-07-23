/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.negocio.entidades.vigente.FonteRenda;
import br.com.sicoob.capes.cadastro.persistencia.dao.FonteRendaDAO;

/**
 * Classe que implementa a persistencia de FonteRenda. 
 * @author Erico.Junior
 */
public class FonteRendaDAOImpl extends EntidadeCadastroDao<FonteRenda> implements
		FonteRendaDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISA_FONTE_RENDA_POR_PESSOA_COMPARTILHAMENTO";
	
	/** A constante LISTAR_VENCIDAS. */
	private static final String LISTAR_VENCIDAS = "LISTAR_RENDAS_VENCIDAS";
	
	/**
	 * Instancia um novo FonteRendaDAOImpl.
	 */
	public FonteRendaDAOImpl() {
		super(FonteRenda.class, NOME_COMANDO_PESQUISAR);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<FonteRenda> listarVencidas(ConsultaDto<FonteRenda> criterios)
			throws BancoobException {		
		return pesquisarLista(getClasse(), criterios, LISTAR_VENCIDAS);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void alterar(FonteRenda objeto) throws BancoobException {
		try {
			getEntityManager().merge(objeto);
			getEntityManager().flush();
		} catch (IllegalArgumentException e) {
			throw new BancoobException("msg.erro.alterar.nao.existe", e);
		}
	}

}
