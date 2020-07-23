/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.dominio.alteracaocompartilhamento;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.excecao.BancoobRuntimeException;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.CertidaoDelegate;
import br.com.sicoob.capes.negocio.entidades.vigente.Certidao;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * @author erico.junior
 *
 */
public class ReplicacaoCertidao extends AbstractReplicacaoCadastro<Certidao> {

	/** O atributo delegate. */
	private CertidaoDelegate delegate = 
			CAPESCadastroFabricaDelegates.getInstance().criarCertidaoDelegate();
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected List<Certidao> listar(PessoaCompartilhamento pessoa)
			throws BancoobException {
		return delegate.listarPorPessoa(pessoa);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected CertidaoDelegate obterDelegate() {
		return delegate;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Certidao obterNovaEntidade(Certidao entidade,
			PessoaCompartilhamento pessoa) {
		
		Certidao novaEntidade = new Certidao();
		
		try {
			BeanUtils.copyProperties(novaEntidade, entidade);
			novaEntidade.setPessoa(pessoa);
			novaEntidade.setIdCertidao(null);
			novaEntidade.setGravarHistorico(false);
			novaEntidade.setVerificarAutorizacao(false);
		} catch (IllegalAccessException e) {
			throw new BancoobRuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new BancoobRuntimeException(e);
		}
		
		return novaEntidade;
	}

}
