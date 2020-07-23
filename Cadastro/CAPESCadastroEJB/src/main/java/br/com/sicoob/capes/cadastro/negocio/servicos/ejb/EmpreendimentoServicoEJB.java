/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.sicoob.capes.cadastro.negocio.dominio.replicacao.facade.EmpreendimentoFachada;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.EmpreendimentoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.EmpreendimentoServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.EmpreendimentoDAO;
import br.com.sicoob.capes.negocio.entidades.Empreendimento;

/**
 * 
 * @author erico.junior,diego.rezende
 */
@Stateless
@Local( { EmpreendimentoServicoLocal.class })
@Remote( { EmpreendimentoServicoRemote.class })
public class EmpreendimentoServicoEJB extends
		CAPESCadastroCrudServicoEJB<Empreendimento> implements EmpreendimentoServicoRemote, EmpreendimentoServicoLocal {

	@Inject
	@Default
	private EmpreendimentoDAO dao;
	
	private EmpreendimentoFachada empreendimentoFacade = new EmpreendimentoFachada();

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected EmpreendimentoDAO getDAO() {
		return dao;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Empreendimento incluir(Empreendimento empreendimento)
			throws BancoobException {
		validarInclusao(empreendimento);
		Empreendimento empreendimentoBanco = super.incluir(empreendimento);
		replicarEmpreendimento(empreendimentoBanco);
		return empreendimentoBanco;
	}

	/**
	 * O método Validar inclusao.
	 *
	 * @param empreendimento o valor de empreendimento
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void validarInclusao(Empreendimento empreendimento) throws BancoobException {
		Empreendimento obter = obter(empreendimento.getCodigo());
		if (obter != null) {
			throw new NegocioException("MN119");
		}
	}

	private void replicarEmpreendimento(Empreendimento empreendimentoBanco) throws BancoobException {
		getReplicacao().replicarEmpreendimento(empreendimentoBanco);
	} 

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void alterar(Empreendimento empreendimento) throws BancoobException {
		super.alterar(empreendimento);
		replicarEmpreendimento(empreendimento);
	}
	
	/**
	 * Recupera o valor de replicacao.
	 *
	 * @return o valor de replicacao
	 */
	private EmpreendimentoFachada getReplicacao() {
		return empreendimentoFacade;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer pesquisarProximoCodigo() throws BancoobException {
		return getDAO().pesquisarProximoCodigo();
	}

	/**
	 * Define o valor de dao.
	 *
	 * @param dao o novo valor de dao
	 */
	protected void setDao(EmpreendimentoDAO dao) {
		this.dao = dao;
	}

	protected void setEmpreendimentoFacade(EmpreendimentoFachada empreendimentoFacade) {
		this.empreendimentoFacade = empreendimentoFacade;
	}

}
