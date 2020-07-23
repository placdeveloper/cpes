/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.dominio.alteracaocompartilhamento;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.excecao.BancoobRuntimeException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.EmailDelegate;
import br.com.sicoob.capes.negocio.entidades.vigente.Email;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * @author erico.junior
 *
 */
public class ReplicacaoEmails extends AbstractReplicacaoCadastro<Email>{

	/** O atributo delegate. */
	private EmailDelegate delegate = 
			CAPESCadastroFabricaDelegates.getInstance().criarEmailDelegate();
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected List<Email> listar(PessoaCompartilhamento pessoa)
			throws BancoobException {
		
		Email Email = new Email();
		Email.setPessoaCompartilhamento(pessoa);
		ConsultaDto<Email> consulta = new ConsultaDto<Email>();
		consulta.setFiltro(Email);
		return delegate.listar(consulta);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected EmailDelegate obterDelegate() {
		return delegate;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Email obterNovaEntidade(Email entidade,
			PessoaCompartilhamento pessoa) {
		
		Email novaEntidade = new Email();
		
		try {
			BeanUtils.copyProperties(novaEntidade, entidade);
			novaEntidade.setPessoaCompartilhamento(pessoa);
			novaEntidade.setIdEmail(null);
			novaEntidade.setGravarHistorico(false);
		} catch (IllegalAccessException e) {
			throw new BancoobRuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new BancoobRuntimeException(e);
		}
		
		return novaEntidade;
	}

}
