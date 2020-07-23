/* 
 * Sicoob
 * RegistroRelacionamentoServicoEJB.java 
 * Criado em: 05/10/2011
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.RegistroRelacionamentoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.RegistroRelacionamentoServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.RegistroRelacionamentoDAO;
import br.com.sicoob.capes.comum.negocio.annotation.IntegracaoGedGft;
import br.com.sicoob.capes.negocio.entidades.vigente.RegistroRelacionamento;

/**
 * TODO javadoc
 *
 * 05/10/2011
 * @author Rodrigo.Chaves
 */
@Stateless
@Local(RegistroRelacionamentoServicoLocal.class)
@Remote(RegistroRelacionamentoServicoRemote.class)
@IntegracaoGedGft
public class RegistroRelacionamentoServicoEJB extends
		EntidadeCadastroServicoEJB<RegistroRelacionamento> implements
		RegistroRelacionamentoServicoRemote, RegistroRelacionamentoServicoLocal {

	@Inject
	@Default
	private RegistroRelacionamentoDAO registroRelacionamentoDAO;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected RegistroRelacionamentoDAO getDAO() {
		return this.registroRelacionamentoDAO;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void validarIncluir(RegistroRelacionamento fonte) throws BancoobException {
		
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public void validarAlterar(RegistroRelacionamento fonte) throws BancoobException {
		
	}

}
