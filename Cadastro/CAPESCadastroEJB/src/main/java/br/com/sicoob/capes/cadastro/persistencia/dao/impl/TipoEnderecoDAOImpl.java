/* 
 * Sicoob
 * TipoEnderecoDAOImpl.java 
 * Criado em: 05/05/2011
 */
package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.persistencia.dao.TipoEnderecoDAO;
import br.com.sicoob.capes.negocio.entidades.TipoEndereco;

/**
 * DAO para os tipos de endereço
 * 
 * 05/05/2011
 * 
 * @author Rodrigo.Chaves
 * 
 */
public class TipoEnderecoDAOImpl extends CAPESCadastroCrudDominioDao<TipoEndereco>
		implements TipoEnderecoDAO {
	
	/** A constante PESQUISAR_TIPO_ENDERECO_INCLUINDO_AMBOS. */
	private static final String PESQUISAR_TIPO_ENDERECO_INCLUINDO_AMBOS = "PESQUISAR_TIPO_ENDERECO_INCLUINDO_AMBOS";

	/**
	 * Construtor
	 */
	public TipoEnderecoDAOImpl() {
		super(TipoEndereco.class, "PESQUISAR_TIPO_ENDERECO",
				"PESQUISAR_PROXIMO_CODIGO_TIPO_ENDERECO");
	}

	public List<TipoEndereco> listarTipoEnderecoIncluindoAmbos(
			ConsultaDto<TipoEndereco> criterios) throws BancoobException {
		return super.pesquisarLista(TipoEndereco.class, criterios,
				PESQUISAR_TIPO_ENDERECO_INCLUINDO_AMBOS);

	}

}
