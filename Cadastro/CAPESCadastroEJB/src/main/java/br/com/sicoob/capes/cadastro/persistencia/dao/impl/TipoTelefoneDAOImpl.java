/* 
 * Sicoob
 * TipoTelefoneDAOImpl.java 
 * Criado em: 05/05/2011
 */
package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.negocio.entidades.TipoTelefone;
import br.com.sicoob.capes.cadastro.persistencia.dao.TipoTelefoneDAO;

/**
 * DAO para os tipos de telefone
 * 
 * 05/05/2011
 * 
 * @author Rodrigo.Chaves
 * 
 */
public class TipoTelefoneDAOImpl extends CAPESCadastroCrudDominioDao<TipoTelefone>
		implements TipoTelefoneDAO {
	
	/** A constante PESQUISAR_TIPO_TELEFONE_INCLUINDO_AMBOS. */
	private static final String PESQUISAR_TIPO_TELEFONE_INCLUINDO_AMBOS = "PESQUISAR_TIPO_TELEFONE_INCLUINDO_AMBOS";

	/**
	 * Construtor
	 */
	public TipoTelefoneDAOImpl() {
		super(TipoTelefone.class, "PESQUISAR_TIPO_TELEFONE",
				"PESQUISAR_PROXIMO_CODIGO_TIPO_TELEFONE");
	}

	public List<TipoTelefone> listarTipoTelefoneIncluindoAmbos(
			ConsultaDto<TipoTelefone> criterios) throws BancoobException {
		return super.pesquisarLista(TipoTelefone.class, criterios,
				PESQUISAR_TIPO_TELEFONE_INCLUINDO_AMBOS);
	}
}
