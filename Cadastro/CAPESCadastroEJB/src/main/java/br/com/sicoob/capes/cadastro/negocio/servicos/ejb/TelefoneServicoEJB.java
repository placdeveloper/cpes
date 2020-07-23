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
import br.com.sicoob.capes.cadastro.negocio.dominio.validacao.ValidacaoTelefone;
import br.com.sicoob.capes.cadastro.negocio.excecao.RegistroJaCadastradoException;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TelefoneServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TelefoneServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.TelefoneDAO;
import br.com.sicoob.capes.comum.negocio.annotation.IgnorarAutorizar;
import br.com.sicoob.capes.negocio.entidades.vigente.Telefone;

/**
 * Serviço para os telefones
 * 
 * @author erico.junior
 */
@Stateless
@Local({ TelefoneServicoLocal.class })
@Remote({ TelefoneServicoRemote.class })
public class TelefoneServicoEJB extends CAPESCadastroCrudServicoEJB<Telefone> implements TelefoneServicoRemote, TelefoneServicoLocal {

	@Inject
	@Default
	protected TelefoneDAO telefoneDAO;	
	
	/** Objeto de acesso aos dados a PessoaCompartilhamentoDelegate . */
	private PessoaCompartilhamentoDelegate pessoaCompartilhamentoServico = CAPESCadastroFabricaDelegates.getInstance().criarPessoaCompartilhamentoDelegate();
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TelefoneDAO getDAO() {
		return telefoneDAO;
	}
		
	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Telefone> listar(ConsultaDto<Telefone> criterios) throws BancoobException {
		return super.listar(criterios);
	}

	/**
	 * {@inheritDoc}
	 */
	@IgnorarAutorizar
	public Telefone importar(Telefone telefone) throws BancoobException {
		validar(telefone);
		return getDAO().incluir(telefone);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@IgnorarAutorizar
	public void alterar(Telefone objeto) throws BancoobException {
		objeto.setIdUsuarioEnvio(obterUsuario().getLogin());
		validar(objeto);
		super.alterar(objeto);
		pessoaCompartilhamentoServico.renovarCadastroAutomatico(null, objeto.getPessoaCompartilhamento().getPessoa().getIdPessoa(), obterUsuario().getLogin());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void excluirEntidade(Telefone objeto) throws BancoobException {
		super.excluirEntidade(objeto);
		pessoaCompartilhamentoServico.renovarCadastroAutomatico(null, objeto.getPessoaCompartilhamento().getPessoa().getIdPessoa(), obterUsuario().getLogin());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Telefone incluir(Telefone objeto) throws BancoobException {
		objeto.setIdUsuarioEnvio(obterUsuario().getLogin());
		validar(objeto);
		Telefone telefone = super.incluir(objeto);
		pessoaCompartilhamentoServico.renovarCadastroAutomatico(null, telefone.getPessoaCompartilhamento().getPessoa().getIdPessoa(), obterUsuario().getLogin());
		
		return telefone;
	}
	
	/**
	 * O método Validar.
	 *
	 * @param entidade o valor de entidade
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void validar(Telefone entidade) throws BancoobException {
		ValidacaoTelefone validacao = new ValidacaoTelefone();
		validacao.validar(entidade);
		isTelefoneRepetido(entidade);
	}
	
	/**
	 * O método Checks if is telefone repetido.
	 *
	 * @param novoTel o valor de novo tel
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void isTelefoneRepetido(Telefone novoTel) throws BancoobException {
		if (getDAO().isTelefoneRepetido(novoTel)) {
			throw new RegistroJaCadastradoException();
		}
	}
    
}