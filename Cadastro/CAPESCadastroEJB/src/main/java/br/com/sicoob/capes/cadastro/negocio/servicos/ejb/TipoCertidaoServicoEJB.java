/* 
 * Sicoob
 * TipoCertidaoServicoEJB.java 
 * Criado em: 12/07/2011
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.excecao.CampoNaoInformadoException;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoCertidaoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoCertidaoServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.TipoCertidaoDAO;
import br.com.sicoob.capes.negocio.entidades.TipoCertidao;

/**
 * Servico para {@link TipoCertidao}
 * 
 * 12/07/2011
 * 
 * @author Rodrigo.Chaves
 */
@Stateless
@Local(TipoCertidaoServicoLocal.class)
@Remote(TipoCertidaoServicoRemote.class)
public class TipoCertidaoServicoEJB extends
		CAPESCadastroCrudDominioServicoEJB<TipoCertidao> implements
		TipoCertidaoServicoRemote, TipoCertidaoServicoLocal {
	
	@Inject
	@Default
	protected TipoCertidaoDAO tipoCertidaoDAO;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TipoCertidaoDAO getDAO() {
		return this.tipoCertidaoDAO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void validarIncluir(TipoCertidao objeto) throws BancoobException {
		
		super.validarIncluir(objeto);
		if (objeto.getCodigo() == null) {
			throw new CampoNaoInformadoException("Código");
		}
		
		if (StringUtils.isEmpty(objeto.getSigla())) {
			throw new CampoNaoInformadoException("Sigla");
		}
		
		if (StringUtils.isEmpty(objeto.getNome())) {
			throw new CampoNaoInformadoException("Nome");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void validarAlterar(TipoCertidao objeto) throws BancoobException {
		
		super.validarAlterar(objeto);
		if (StringUtils.isEmpty(objeto.getSigla())) {
			throw new CampoNaoInformadoException("Sigla");
		}
		
		if (StringUtils.isEmpty(objeto.getNome())) {
			throw new CampoNaoInformadoException("Nome");
		}
	}

}