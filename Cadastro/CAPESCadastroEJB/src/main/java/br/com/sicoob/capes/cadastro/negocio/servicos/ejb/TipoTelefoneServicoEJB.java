/* 
 * Sicoob
 * TipoTelefoneServicoEJB.java 
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
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoTelefoneServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoTelefoneServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.CAPESCadastroCrudDaoIF;
import br.com.sicoob.capes.cadastro.persistencia.dao.TipoTelefoneDAO;
import br.com.sicoob.capes.negocio.entidades.TipoTelefone;

/**
 * Implementa as operacoes do servico de tipo de telefone
 * 
 * 05/05/2011
 * 
 * @author Rodrigo.Chaves
 * 
 */
@Stateless
@Local( { TipoTelefoneServicoLocal.class })
@Remote( { TipoTelefoneServicoRemote.class })
public class TipoTelefoneServicoEJB extends
		CAPESCadastroCrudDominioServicoEJB<TipoTelefone> implements
		TipoTelefoneServicoRemote, TipoTelefoneServicoLocal {

	@Inject
	@Default
	protected TipoTelefoneDAO tipoTelefoneDAO;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected CAPESCadastroCrudDaoIF<TipoTelefone> getDAO() {
		return this.tipoTelefoneDAO;
	}

    /**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<TipoTelefone> listarTipoTelefoneIncluindoAmbos(
			ConsultaDto<TipoTelefone> criterios) throws BancoobException {
		return tipoTelefoneDAO.listarTipoTelefoneIncluindoAmbos(criterios);
	}

}
