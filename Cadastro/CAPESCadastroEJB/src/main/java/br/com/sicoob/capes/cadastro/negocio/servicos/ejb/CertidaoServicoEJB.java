/**
 * 
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
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.PessoaCompartilhamentoDelegate;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.CertidaoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.CertidaoServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.CertidaoDAO;
import br.com.sicoob.capes.comum.negocio.annotation.IntegracaoGedGft;
import br.com.sicoob.capes.integracao.negocio.delegates.CAPESIntegracaoFabricaDelegates;
import br.com.sicoob.capes.integracao.negocio.delegates.GEDIntegracaoDelegate;
import br.com.sicoob.capes.negocio.entidades.vigente.Certidao;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * Implementa as operações do serviço de certidao.
 * 
 * @author juan.damasceno
 */
@Stateless
@Local( { CertidaoServicoLocal.class })
@Remote( { CertidaoServicoRemote.class })
@IntegracaoGedGft
public class CertidaoServicoEJB extends
		EntidadeCadastroServicoEJB<Certidao> implements CertidaoServicoRemote, CertidaoServicoLocal {

	@Inject
	@Default
	protected CertidaoDAO referenciaDao;
	
	/** Objeto de acesso aos dados a PessoaCompartilhamentoDelegate . */
	private PessoaCompartilhamentoDelegate pessoaCompartilhamentoServico = CAPESCadastroFabricaDelegates.getInstance().criarPessoaCompartilhamentoDelegate();
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected CertidaoDAO getDAO() {
		return referenciaDao;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Certidao> listarPorPessoa(PessoaCompartilhamento pessoa) throws BancoobException {
		return getDAO().listarPorPessoa(pessoa);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void validarAlterar(Certidao objeto) throws BancoobException {
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void validarIncluir(Certidao objeto) throws BancoobException {
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void excluirCertidoes(List<Certidao> certidoes) throws BancoobException {
		if(certidoes != null) {
			for (Certidao certidao : certidoes) {
				getDAO().excluir(certidao.getId());
				GEDIntegracaoDelegate gedIntegracaoDelegate = CAPESIntegracaoFabricaDelegates.getInstance().criarGEDIntegracaoDelegate();
				gedIntegracaoDelegate.desassociarDocumentosDossie(certidao.getIdRegistroControlado(), certidao.getPessoa().getPessoa().getTipoPessoa().getCodTipoPessoa());
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */	
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Certidao> listarVencidas(ConsultaDto<Certidao> criterios)
			throws BancoobException {
		
		Certidao filtro = new Certidao();
		filtro.setDataVencimento(new DateTimeDB());
		
		criterios.setFiltro(filtro);
		criterios.setOrdenacao("padrao");
		
		return getDAO().listarVencidas(criterios);		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Certidao incluir(Certidao certidao) throws BancoobException {
		return super.incluir(certidao);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void alterar(Certidao objeto) throws BancoobException {	
		super.alterar(objeto);
		Certidao certidao = obter(objeto.getIdCertidao());
		if(isRegistroVigente(certidao)){
			if(objeto.getIdUsuarioEnvio() != null){
				pessoaCompartilhamentoServico.renovarCadastroAutomatico(objeto, objeto.getPessoaCompartilhamento().getPessoa().getIdPessoa(), objeto.getIdUsuarioEnvio());
			}
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void excluirEntidade(Certidao objeto) throws BancoobException {
		super.excluirEntidade(objeto);
		if(isRegistroVigente(objeto)){
			if(objeto.getIdUsuarioEnvio() != null){
				pessoaCompartilhamentoServico.renovarCadastroAutomatico(objeto, objeto.getPessoaCompartilhamento().getPessoa().getIdPessoa(), objeto.getIdUsuarioEnvio());
			}
		}
	}

}
