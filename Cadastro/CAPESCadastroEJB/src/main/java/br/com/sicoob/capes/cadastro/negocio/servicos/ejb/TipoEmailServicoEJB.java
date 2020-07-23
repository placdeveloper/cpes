/* 
 * Sicoob
 * TipoEmailServicoEJB.java 
 * Criado em: 05/05/2011
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoEmailServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoEmailServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.CAPESCadastroCrudDaoIF;
import br.com.sicoob.capes.cadastro.persistencia.dao.TipoEmailDAO;
import br.com.sicoob.capes.negocio.entidades.TipoEmail;

/**
 * Implementa as operações do serviço de tipo de e-mail
 * 
 * 05/05/2011
 * 
 * @author Rodrigo.Chaves
 * 
 */
@Stateless
@Local( { TipoEmailServicoLocal.class })
@Remote( { TipoEmailServicoRemote.class })
public class TipoEmailServicoEJB extends CAPESCadastroCrudDominioServicoEJB<TipoEmail>
		implements TipoEmailServicoRemote, TipoEmailServicoLocal {

	@Inject
	@Default
	protected TipoEmailDAO tipoEmailDAO;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected CAPESCadastroCrudDaoIF<TipoEmail> getDAO() {
		return this.tipoEmailDAO;
	}

	public List<TipoEmail> listarTipoEmailIncluindoAmbos(
			ConsultaDto<TipoEmail> criterios) throws BancoobException {
		return tipoEmailDAO.listarTipoEmailIncluindoAmbos(criterios);
	}

}
