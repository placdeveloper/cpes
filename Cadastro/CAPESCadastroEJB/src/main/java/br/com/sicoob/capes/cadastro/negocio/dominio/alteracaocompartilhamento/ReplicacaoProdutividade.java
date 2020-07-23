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
import br.com.sicoob.capes.cadastro.negocio.delegates.ProdutividadeDelegate;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.Produtividade;

/**
 * @author erico.junior
 *
 */
public class ReplicacaoProdutividade extends AbstractReplicacaoCadastro<Produtividade> {

	/** O atributo delegate. */
	private ProdutividadeDelegate delegate = 
			CAPESCadastroFabricaDelegates.getInstance().criarProdutividadeDelegate();
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected List<Produtividade> listar(PessoaCompartilhamento pessoa)
			throws BancoobException {
		return obterDelegate().listarProdutividadesEmAberto(pessoa);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ProdutividadeDelegate obterDelegate() {
		return delegate;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Produtividade obterNovaEntidade(Produtividade entidade,
			PessoaCompartilhamento pessoa) {
		
		Produtividade novaEntidade = new Produtividade();
		
		try {
			BeanUtils.copyProperties(novaEntidade, entidade);
			novaEntidade.setPessoaCompartilhamento(pessoa);
			novaEntidade.setIdProdutividade(null);
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
