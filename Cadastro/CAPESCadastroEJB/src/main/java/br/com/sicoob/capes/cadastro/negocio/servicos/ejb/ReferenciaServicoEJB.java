/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import java.io.Serializable;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.PessoaCompartilhamentoDelegate;
import br.com.sicoob.capes.cadastro.negocio.excecao.AgenciaInexistenteException;
import br.com.sicoob.capes.cadastro.negocio.excecao.BancoInexistenteException;
import br.com.sicoob.capes.cadastro.negocio.excecao.PessoaReferenciaDiferenteException;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.ReferenciaServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.ReferenciaServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.ReferenciaDAO;
import br.com.sicoob.capes.comum.negocio.vo.BancoCafVO;
import br.com.sicoob.capes.integracao.negocio.delegates.ADMIntegracaoDelegate;
import br.com.sicoob.capes.integracao.negocio.delegates.CAPESIntegracaoFabricaDelegates;
import br.com.sicoob.capes.negocio.entidades.ReferenciaBancaria;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.Referencia;

/**
 * Implementa as operações do serviço de referencia.
 * 
 * @author juan.damasceno
 */
@Stateless
@Local({ ReferenciaServicoLocal.class })
@Remote({ ReferenciaServicoRemote.class })
public class ReferenciaServicoEJB extends CAPESCadastroCrudServicoEJB<Referencia> implements ReferenciaServicoRemote, ReferenciaServicoLocal {

	/** A constante TIPO_REFERENCIA_BANCARIA. */
	private static final int TIPO_REFERENCIA_BANCARIA = 0;

	@Inject
	@Default
	protected ReferenciaDAO referenciaDao;
	
	/** Objeto de acesso aos dados a PessoaCompartilhamentoDelegate . */
	private PessoaCompartilhamentoDelegate pessoaCompartilhamentoServico = CAPESCadastroFabricaDelegates.getInstance().criarPessoaCompartilhamentoDelegate();

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ReferenciaDAO getDAO() {
		return referenciaDao;
	}

	/**
	 * O método Validar.
	 *
	 * @param objeto o valor de objeto
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void validar(Referencia objeto) throws BancoobException {
		if (objeto instanceof ReferenciaBancaria && objeto.getTipoReferencia().getCodigo() == TIPO_REFERENCIA_BANCARIA) {
			validarBanco((ReferenciaBancaria) objeto);
			validarAgencia((ReferenciaBancaria) objeto);
		}

		validarPessoaReferencia(objeto);
	}

	/**
	 * O método Validar pessoa referencia.
	 *
	 * @param objeto o valor de objeto
	 * @throws PessoaReferenciaDiferenteException lança a exceção PessoaReferenciaDiferenteException
	 */
	private void validarPessoaReferencia(Referencia objeto) throws PessoaReferenciaDiferenteException {
		PessoaCompartilhamento pessoaReferencia = objeto.getPessoaReferencia();
		PessoaCompartilhamento pessoa = objeto.getPessoaCompartilhamento();
		if (pessoaReferencia != null && pessoaReferencia.getId().longValue() == pessoa.getId().longValue()) {
			throw new PessoaReferenciaDiferenteException();
		}
	}

	/**
	 * O método Validar banco.
	 *
	 * @param referenciaBancaria o valor de referencia bancaria
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public void validarBanco(ReferenciaBancaria referenciaBancaria) throws BancoobException {
		String numBanco = referenciaBancaria.getNumeroBanco();
		BancoCafVO banco = obterBanco(Short.parseShort(numBanco));

		if (banco == null) {
			throw new BancoInexistenteException();
		}
	}

	/**
	 * Obter banco.
	 *
	 * @param numBanco o valor de num banco
	 * @return BancoCafVO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private BancoCafVO obterBanco(Short numBanco) throws BancoobException {
		ADMIntegracaoDelegate admIntegracaoDelegate = CAPESIntegracaoFabricaDelegates.getInstance().criarADMIntegracaoDelegate();
		return admIntegracaoDelegate.obterBancoCaf(numBanco);
	}

	/**
	 * O método Validar agencia.
	 *
	 * @param referenciaBancaria o valor de referencia bancaria
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public void validarAgencia(ReferenciaBancaria referenciaBancaria) throws BancoobException {
		Integer numAgencia = Integer.parseInt(referenciaBancaria.getNumeroAgencia());
		Integer numBanco = Integer.parseInt(referenciaBancaria.getNumeroBanco());
		
		ADMIntegracaoDelegate admIntegracaoDelegate = CAPESIntegracaoFabricaDelegates.getInstance().criarADMIntegracaoDelegate();
		boolean existeAgencia = admIntegracaoDelegate.verificarAgenciaCaf(numAgencia, numBanco);

		if (!existeAgencia) {
			throw new AgenciaInexistenteException();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public Referencia importar(Referencia referencia) throws BancoobException {
		validar(referencia);
		return getDAO().incluir(referencia);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void alterar(Referencia objeto) throws BancoobException {
		objeto.setIdUsuarioEnvio(obterUsuario().getLogin());
		validar(objeto);
		super.alterar(objeto);
		pessoaCompartilhamentoServico.renovarCadastroAutomatico(null, objeto.getPessoaCompartilhamento().getPessoa().getIdPessoa(), obterUsuario().getLogin());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void excluir(Serializable chave) throws BancoobException {
		super.excluir(chave);
	}		

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void excluirEntidade(Referencia objeto) throws BancoobException {
		super.excluirEntidade(objeto);
		pessoaCompartilhamentoServico.renovarCadastroAutomatico(null, objeto.getPessoaCompartilhamento().getPessoa().getIdPessoa(), obterUsuario().getLogin());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Referencia incluir(Referencia objeto) throws BancoobException {
		objeto.setIdUsuarioEnvio(obterUsuario().getLogin());
		validar(objeto);
		Referencia retorno = super.incluir(objeto);
		pessoaCompartilhamentoServico.renovarCadastroAutomatico(null, objeto.getPessoaCompartilhamento().getPessoa().getIdPessoa(), obterUsuario().getLogin());
		
		return retorno;
	}

}