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
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.PessoaCompartilhamentoDelegate;
import br.com.sicoob.capes.cadastro.negocio.dominio.validacao.ValidacaoEmail;
import br.com.sicoob.capes.cadastro.negocio.excecao.RegistroJaCadastradoException;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.EmailServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.EmailServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.EmailDAO;
import br.com.sicoob.capes.comum.negocio.annotation.IgnorarAutorizar;
import br.com.sicoob.capes.negocio.entidades.vigente.Email;

/**
 * Serviço para os emails
 * 
 * @author erico.junior
 */
@Stateless
@Local({ EmailServicoLocal.class })
@Remote({ EmailServicoRemote.class })
public class EmailServicoEJB extends CAPESCadastroCrudServicoEJB<Email> implements EmailServicoRemote, EmailServicoLocal {

	@Inject
	@Default
	protected EmailDAO emailDAO;

	/** Objeto de acesso aos dados a PessoaCompartilhamentoDelegate . */
	private PessoaCompartilhamentoDelegate pessoaCompartilhamentoServico = CAPESCadastroFabricaDelegates.getInstance().criarPessoaCompartilhamentoDelegate();
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected EmailDAO getDAO() {
		return emailDAO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Email> listar(ConsultaDto<Email> criterios) throws BancoobException {
		return super.listar(criterios);
	}

	/**
	 * {@inheritDoc}
	 */
	public Email importar(Email email) throws BancoobException {
		validar(email);
		return getDAO().incluir(email);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void alterar(Email objeto) throws BancoobException {
		objeto.setIdUsuarioEnvio(obterUsuario().getLogin());
		validar(objeto);
		super.alterar(objeto);
		pessoaCompartilhamentoServico.renovarCadastroAutomatico(null, objeto.getPessoaCompartilhamento().getPessoa().getIdPessoa(), obterUsuario().getLogin());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@IgnorarAutorizar
	public Email incluir(Email objeto) throws BancoobException {
		objeto.setIdUsuarioEnvio(obterUsuario().getLogin());
		validar(objeto);
		Email email = super.incluir(objeto);
		pessoaCompartilhamentoServico.renovarCadastroAutomatico(null, objeto.getPessoaCompartilhamento().getPessoa().getIdPessoa(), obterUsuario().getLogin());
		
		return email;
	}
	
	/**
	 * @see br.com.bancoob.negocio.servicos.ejb.BancoobCrudServicoEJB#excluir(java.io.Serializable)
	 * @param chave A chave do registro que será excluído.
	 */
	@Override
	public void excluirEntidade(Email objeto) throws BancoobException  {
		super.excluirEntidade(objeto);
		pessoaCompartilhamentoServico.renovarCadastroAutomatico(null, objeto.getPessoaCompartilhamento().getPessoa().getIdPessoa(), obterUsuario().getLogin());
	}
	
	/**
	 * O método Validar.
	 *
	 * @param entidade o valor de entidade
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void validar(Email entidade) throws BancoobException {
		ValidacaoEmail validacao = new ValidacaoEmail();
		validacao.validar(entidade);
		isEmailRepetido(entidade);
	}
	
	/**
	 * Verifica se o email já existe.
	 * 
	 * @param novoEmail
	 * @throws BancoobException
	 */
	private void isEmailRepetido(Email novoEmail) throws BancoobException {
		if (getDAO().isEmailRepetido(novoEmail)) {
			throw new RegistroJaCadastradoException();
		}
	}

}