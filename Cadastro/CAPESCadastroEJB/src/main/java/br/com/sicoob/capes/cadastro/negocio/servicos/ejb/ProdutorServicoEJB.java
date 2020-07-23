/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.PessoaCompartilhamentoDelegate;
import br.com.sicoob.capes.cadastro.negocio.dominio.validacao.Validacao;
import br.com.sicoob.capes.cadastro.negocio.dominio.validacao.ValidacaoCnae;
import br.com.sicoob.capes.cadastro.negocio.dominio.validacao.ValidacaoProdutor;
import br.com.sicoob.capes.cadastro.negocio.excecao.ViolacaoIntegridadeProdutorException;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.ProdutividadeServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.ProdutorServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.ProdutorServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.ProdutorDAO;
import br.com.sicoob.capes.comum.negocio.annotation.Autorizar;
import br.com.sicoob.capes.comum.negocio.annotation.IntegracaoGedGft;
import br.com.sicoob.capes.comum.negocio.enums.TipoOperacaoEnum;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.Produtor;

/**
 * Servico utilizado para produtor
 * @author Erico.Junior
 */
@Stateless
@Local({ ProdutorServicoLocal.class })
@Remote({ ProdutorServicoRemote.class })
@IntegracaoGedGft
public class ProdutorServicoEJB extends EntidadeCadastroServicoEJB<Produtor>
		implements ProdutorServicoRemote, ProdutorServicoLocal {

	@Inject
	@Default
	private ProdutorDAO dao;
	
	/** O atributo produtividadeServico. */
	@EJB(mappedName = "capes/cadastro/ProdutividadeServico")
	private ProdutividadeServicoLocal produtividadeServico;
	
	/** Objeto de acesso aos dados a PessoaCompartilhamentoDelegate . */
	private PessoaCompartilhamentoDelegate pessoaCompartilhamentoServico = CAPESCadastroFabricaDelegates.getInstance().criarPessoaCompartilhamentoDelegate();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void validarIncluir(Produtor objeto) throws BancoobException {
		validarProdutor(objeto);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void validarAlterar(Produtor objeto) throws BancoobException {
		validarProdutor(objeto);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void validarExcluir(Produtor objeto) throws BancoobException {
		validarExclusaoProdutor(objeto);
	}
	
	/**
	 * O método Validar produtor.
	 *
	 * @param produtor o valor de produtor
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void validarProdutor(Produtor produtor) throws BancoobException {
		Validacao<Produtor> validacao = new ValidacaoProdutor();
		validacao.validar(produtor);
	}
	
	/**
	 * O método Validar exclusao produtor.
	 *
	 * @param produtor o valor de produtor
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void validarExclusaoProdutor(Produtor produtor) throws BancoobException{
		if(produtividadeServico.produtorPossuiDependencia(produtor)){
			throw new ViolacaoIntegridadeProdutorException();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ProdutorDAO getDAO() {
		return dao;
	}

	/**
	 * {@inheritDoc}
	 */
	public void validarInclusaoProdutor(PessoaCompartilhamento pessoa) throws BancoobException {
		ValidacaoCnae validacao = new ValidacaoCnae();
		validacao.validar(pessoa);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Autorizar(atualizacao = TipoOperacaoEnum.I)
	public Produtor incluir(Produtor objeto) throws BancoobException {
		Produtor produtor = obter(objeto.getIdPessoaCompartilhamento());

		if (produtor == null) {
			objeto.setGravarHistorico(false);
			produtor = getDAO().incluir(objeto);
		}
		Produtor produtorBD = obter(produtor.getId());
		if (isRegistroVigente(produtorBD)) {
			if (produtor.getIdUsuarioEnvio() != null) {
				pessoaCompartilhamentoServico.renovarCadastroAutomatico(produtor, produtor.getPessoaCompartilhamento().getPessoa().getIdPessoa(), produtor.getIdUsuarioEnvio());
			}
		}
		return produtor;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void excluirEntidade(Produtor objeto) throws BancoobException {
		validarExcluir(objeto);
		Produtor produtor = obter(objeto.getId());
		produtor.setIdInstituicaoAtualizacao(objeto.getIdInstituicaoAtualizacao());
		super.excluirEntidade(objeto);
		if (isRegistroVigente(produtor)) {
			if (objeto.getIdUsuarioEnvio() != null) {
				pessoaCompartilhamentoServico.renovarCadastroAutomatico(produtor, objeto.getPessoaCompartilhamento().getPessoa().getIdPessoa(), objeto.getIdUsuarioEnvio());
			}
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void alterar(Produtor objeto)	throws BancoobException {
		super.alterar(objeto);
		Produtor endereco = obter(objeto.getId());
		if (isRegistroVigente(endereco)) {
			if (objeto.getIdUsuarioEnvio() != null) {
				pessoaCompartilhamentoServico.renovarCadastroAutomatico(objeto, objeto.getPessoaCompartilhamento().getPessoa().getIdPessoa(), objeto.getIdUsuarioEnvio());
			}
		}
	}
}
