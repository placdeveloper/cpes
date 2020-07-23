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
import br.com.sicoob.capes.cadastro.negocio.delegates.FonteRendaDelegate;
import br.com.sicoob.capes.negocio.entidades.vigente.FonteRenda;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * @author erico.junior
 *
 */
public class ReplicacaoFonteRenda extends AbstractReplicacaoCadastro<FonteRenda> {


	/** O atributo delegate. */
	private FonteRendaDelegate delegate = 
			CAPESCadastroFabricaDelegates.getInstance().criarFonteRendaDelegate();
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected List<FonteRenda> listar(PessoaCompartilhamento pessoa)
			throws BancoobException {
		
		FonteRenda filtro = new FonteRenda();
		filtro.setPessoaCompartilhamento(pessoa);

		ConsultaDto<FonteRenda> criterios = new ConsultaDto<FonteRenda>();
		criterios.setFiltro(filtro);
		return delegate.listar(criterios);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected FonteRendaDelegate obterDelegate() {
		return delegate;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected FonteRenda obterNovaEntidade(FonteRenda entidade,
			PessoaCompartilhamento pessoa) {
		
		FonteRenda novaEntidade = new FonteRenda();
		
		try {
			BeanUtils.copyProperties(novaEntidade, entidade);
			novaEntidade.setPessoaCompartilhamento(pessoa);
			novaEntidade.setIdFonteRenda(null);
			novaEntidade.setGravarHistorico(false);
			novaEntidade.setPessoaEmpregador(null);
			novaEntidade.setVerificarAutorizacao(false);
		} catch (IllegalAccessException e) {
			throw new BancoobRuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new BancoobRuntimeException(e);
		}
		
		return novaEntidade;
	}

}
