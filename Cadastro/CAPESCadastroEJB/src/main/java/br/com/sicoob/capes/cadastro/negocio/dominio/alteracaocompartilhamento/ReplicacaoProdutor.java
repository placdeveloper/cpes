/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.dominio.alteracaocompartilhamento;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.excecao.BancoobRuntimeException;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.ProdutorDelegate;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.Produtor;

/**
 * @author erico.junior
 *
 */
public class ReplicacaoProdutor extends AbstractReplicacaoCadastro<Produtor> {

	/** O atributo delegate. */
	private ProdutorDelegate delegate = 
			CAPESCadastroFabricaDelegates.getInstance().criarProdutorDelegate();
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected List<Produtor> listar(PessoaCompartilhamento pessoa)
			throws BancoobException {

		List<Produtor> lista = new ArrayList<Produtor>();
		Produtor produtor = delegate.obter(pessoa.getIdPessoaCompartilhamento());

		if(produtor!= null) {
			lista.add(produtor);
		}
		return lista;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ProdutorDelegate obterDelegate() {
		return delegate;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Produtor obterNovaEntidade(Produtor entidade,
			PessoaCompartilhamento pessoa) {
		
		Produtor novaEntidade = new Produtor();
		
		try {
			BeanUtils.copyProperties(novaEntidade, entidade);
			novaEntidade.setPessoaCompartilhamento(pessoa);
			novaEntidade.setIdPessoaCompartilhamento(pessoa.getIdPessoaCompartilhamento());
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
