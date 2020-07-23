/*
 * SICOOB
 * 
 * BemImovelServicoEJB.java(br.com.sicoob.capes.cadastro.negocio.servicos.ejb.BemImovelServicoEJB)
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.ejb.bemantigo;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.servicos.bemantigo.BemImovelAntigoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.bemantigo.BemImovelAntigoServicoRemote;
import br.com.sicoob.capes.cadastro.negocio.servicos.ejb.EntidadeCadastroServicoEJB;
import br.com.sicoob.capes.cadastro.persistencia.dao.bemantigo.BemImovelAntigoDAO;
import br.com.sicoob.capes.comum.negocio.annotation.IntegracaoGedGft;
import br.com.sicoob.capes.negocio.entidades.bemantigo.BemImovel;

/**
 * Implementa as operacoes do servico de BemImovel.
 * 
 * @author Erico.Junior
 */
@Stateless
@Local({BemImovelAntigoServicoLocal.class})
@Remote({BemImovelAntigoServicoRemote.class})
@IntegracaoGedGft
public class BemImovelAntigoServicoEJB extends EntidadeCadastroServicoEJB<BemImovel> implements
	BemImovelAntigoServicoRemote, BemImovelAntigoServicoLocal {
	
	@Inject
	@Default
	protected BemImovelAntigoDAO bemImovelDAO;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected BemImovelAntigoDAO getDAO() {
		return bemImovelDAO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void validarIncluir(BemImovel fonte) throws BancoobException {

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void validarAlterar(BemImovel fonte) throws BancoobException {

	}
	
}
