/* 
 * Sicoob
 * TipoEmailDAOImpl.java 
 * Criado em: 05/05/2011
 */
package br.com.sicoob.capes.cadastro.persistencia.dao.impl;


import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.persistencia.dao.TipoEmailDAO;
import br.com.sicoob.capes.negocio.entidades.TipoEmail;

/**
 * DAO para os tipos de e-mail
 * 
 * 05/05/2011
 * 
 * @author Rodrigo.Chaves
 * 
 */
public class TipoEmailDAOImpl extends CAPESCadastroCrudDominioDao<TipoEmail> implements
		TipoEmailDAO {
	
	/** A constante PESQUISAR_TIPO_EMAIL_INCLUINDO_AMBOS. */
	private static final String PESQUISAR_TIPO_EMAIL_INCLUINDO_AMBOS = "PESQUISAR_TIPO_EMAIL_INCLUINDO_AMBOS";
	
	/**
	 * Construtor
	 * 
	 * @param clazz
	 * @param nomeComandoPesquisar
	 */
	public TipoEmailDAOImpl() {
		super(TipoEmail.class, "PESQUISAR_TIPO_EMAIL", "PESQUISAR_PROXIMO_CODIGO_TIPO_EMAIL");
	}

	public List<TipoEmail> listarTipoEmailIncluindoAmbos(
			ConsultaDto<TipoEmail> criterios) throws BancoobException {
		return super.pesquisarLista(TipoEmail.class, criterios, PESQUISAR_TIPO_EMAIL_INCLUINDO_AMBOS);
	}
}
