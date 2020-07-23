/* 
 * Sicoob
 * TipoEnderecoServicoEJB.java 
 * Criado em: 05/05/2011
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoEnderecoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoEnderecoServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.CAPESCadastroCrudDaoIF;
import br.com.sicoob.capes.cadastro.persistencia.dao.TipoEnderecoDAO;
import br.com.sicoob.capes.negocio.entidades.TipoEndereco;

/**
 * Implementa as operações do serviço de tipo de endereço
 * 
 * 05/05/2011
 * 
 * @author Rodrigo.Chaves
 * 
 */
@Stateless
@Local( { TipoEnderecoServicoLocal.class })
@Remote( { TipoEnderecoServicoRemote.class })
public class TipoEnderecoServicoEJB extends
		CAPESCadastroCrudDominioServicoEJB<TipoEndereco> implements
		TipoEnderecoServicoRemote, TipoEnderecoServicoLocal {

	@Inject
	@Default
	protected TipoEnderecoDAO tipoEnderecoDAO;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected CAPESCadastroCrudDaoIF<TipoEndereco> getDAO() {
		return this.tipoEnderecoDAO;
	}

    /**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<TipoEndereco> listarTipoEnderecoIncluindoAmbos(
			ConsultaDto<TipoEndereco> criterios) throws BancoobException {
		return tipoEnderecoDAO.listarTipoEnderecoIncluindoAmbos(criterios);
	}

}
