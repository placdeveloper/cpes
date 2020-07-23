/*
 * SICOOB
 * 
 * ValidacaoCadastralRegraServicoEJB.java(br.com.sicoob.capes.cadastro.negocio.servicos.ejb.ValidacaoCadastralRegraServicoEJB)
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.ValidacaoCadastralDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.ValidacaoCadastralErroDelegate;
import br.com.sicoob.capes.cadastro.negocio.excecao.ViolacaoIntegridadeRegraValidacaoException;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.ValidacaoCadastralRegraServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.ValidacaoCadastralRegraServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.ValidacaoCadastralRegraDAO;
import br.com.sicoob.capes.negocio.entidades.PerfilCadastro;
import br.com.sicoob.capes.negocio.entidades.ValidacaoCadastralRegra;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * EJB contendo servicos relacionados a ValidacaoCadastralRegra.
 */
/**
 * @author Isaac.Pessoa
 *
 */
@Stateless
@Local(ValidacaoCadastralRegraServicoLocal.class)
@Remote(ValidacaoCadastralRegraServicoRemote.class)
public class ValidacaoCadastralRegraServicoEJB extends CAPESCadastroCrudDominioServicoEJB<ValidacaoCadastralRegra> implements
		ValidacaoCadastralRegraServicoLocal, ValidacaoCadastralRegraServicoRemote {

	@Inject
	@Default
	protected ValidacaoCadastralRegraDAO dao;

	/** O atributo fabrica. */
	private CAPESCadastroFabricaDelegates fabrica = CAPESCadastroFabricaDelegates.getInstance();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void revalidarCadastro(Long idPessoaCompartilhamento) throws BancoobException {
		ValidacaoCadastralDelegate validacaoDelegate = fabrica.criarValidacaoCadastralDelegate();
		ValidacaoCadastralErroDelegate erroDelegate = fabrica.criarValidacaoCadastralErroDelegate();

		validacaoDelegate.atualizarDataUltimaValidacao(idPessoaCompartilhamento);
		erroDelegate.deletarErrosPessoa(idPessoaCompartilhamento);

		executarValidas(idPessoaCompartilhamento, null);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void revalidarCadastro(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		PessoaCompartilhamento pessoaCompartilhamento = CAPESCadastroFabricaDelegates.getInstance().criarPessoaCompartilhamentoDelegate().consultarPorIdPessoaInstituicao(idPessoa, idInstituicao);
		if(pessoaCompartilhamento != null) {
			revalidarCadastro(pessoaCompartilhamento.getId());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void revalidarCadastroPerfilCadastro(Long idPessoaCompartilhamento) throws BancoobException {
		PessoaCompartilhamento pessoaCompartilhamento = fabrica.criarPessoaCompartilhamentoDelegate().obter(idPessoaCompartilhamento);
		revalidarCadastroPerfilCadastro(pessoaCompartilhamento.getId(), pessoaCompartilhamento.getPerfilCadastro().getOrdem());
	}

	/**
	 * Realiza a validacao cadastral baseado no idcompartilhamento de {@link PessoaCompartilhamento} e ordem da {@link PerfilCadastro}
	 * 
	 * @param idPessoaCompartilhamento
	 * @param ordem
	 * @throws BancoobException
	 */
	@Override
	public void revalidarCadastroPerfilCadastro(Long idPessoaCompartilhamento, Short ordem) throws BancoobException {
		ValidacaoCadastralDelegate validacaoDelegate = fabrica.criarValidacaoCadastralDelegate();
		ValidacaoCadastralErroDelegate erroDelegate = fabrica.criarValidacaoCadastralErroDelegate();

		validacaoDelegate.atualizarDataUltimaValidacao(idPessoaCompartilhamento);
		erroDelegate.deletarErrosPessoa(idPessoaCompartilhamento);

		executarValidacaoPerfilCadastro(idPessoaCompartilhamento, null, ordem);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ValidacaoCadastralRegra> listarRegrasExecutaveis() throws BancoobException {
		return getDAO().listarRegrasExecutaveis();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ValidacaoCadastralRegra> listarRegrasExecutaveis(Short ordem) throws BancoobException {
		return getDAO().listarRegrasExecutaveis(ordem);
	}
	
	/**
	 * {@inheritDoc}
	 */
	private List<ValidacaoCadastralRegra> listarRegrasExecutaveisRevalidacao() throws BancoobException {
		return getDAO().listarRegrasExecutaveisRevalidacao();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void validar(ValidacaoCadastralRegra regra, DateTimeDB dataValidacao) throws BancoobException {
		ValidacaoCadastralErroDelegate erroDelegate = fabrica.criarValidacaoCadastralErroDelegate();
		erroDelegate.deletarErros(regra.getCodigoRegra(), dataValidacao);
		getDAO().executarRegra(regra, null, dataValidacao);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void validarCategoriaCadastro() throws BancoobException {
		getDAO().executarRegraCategoriaCadastro();
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void validar(ValidacaoCadastralRegra regra) throws BancoobException {
		ValidacaoCadastralErroDelegate erroDelegate = fabrica.criarValidacaoCadastralErroDelegate();
		erroDelegate.deletarErrosRegra(regra.getCodigoRegra());
		if (regra.getAtivo()) {
			getDAO().executarRegra(regra);
		}
	}

	/**
	 * Executa <b>todas</b> as regras validas (que estejam ativas e possuam
	 * query) para a pessoa informada. (Revalidação do cadastro - Componente).
	 * 
	 * @param idPessoaCompartilhamento
	 *            <b>Opcional</b>. O ID da pessoa
	 * @param dataValidacao
	 *            a data de validacao
	 * @throws BancoobException
	 */
	private void executarValidas(Long idPessoaCompartilhamento, DateTimeDB dataValidacao) throws BancoobException {
		for (ValidacaoCadastralRegra regra : listarRegrasExecutaveisRevalidacao()) {
			getDAO().executarRegra(regra, idPessoaCompartilhamento, dataValidacao);
		}
	}
	

	/**
	 * Executa <b>todas</b> as regras validas (que estejam ativas e possuam
	 * query) para a pessoa informada baseada na sua categoria de cadastro e ordem. (Revalidação do cadastro - Componente).
	 * 
	 * 
	 * @param idPessoaCompartilhamento
	 * 			prospeccao futura
	 * 
	 * @param dataValidacao
	 * 			a data de validacao - prospeccao futura
	 * 
	 * @param codPerfilCadastro
	 * 
	 * @param ordemPerfilCadastro
	 * 
	 * @throws BancoobException
	 */
	private void executarValidacaoPerfilCadastro(Long idPessoaCompartilhamento, DateTimeDB dataValidacao, Short ordemPerfilCadastro) throws BancoobException {
		for (ValidacaoCadastralRegra regra : listarRegrasExecutaveis(ordemPerfilCadastro)) {
			getDAO().executarRegra(regra, idPessoaCompartilhamento, dataValidacao);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void excluir(Serializable chave) throws BancoobException {

		if (fabrica.criarValidacaoCadastralErroDelegate().isExisteErro((Short) chave)) {
			throw new ViolacaoIntegridadeRegraValidacaoException();
		}
		super.excluir(chave);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ValidacaoCadastralRegraDAO getDAO() {
		return dao;
	}

}