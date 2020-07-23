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
import br.com.sicoob.capes.cadastro.negocio.delegates.ReferenciaDelegate;
import br.com.sicoob.capes.negocio.entidades.ReferenciaBancaria;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.Referencia;

/**
 * @author erico.junior
 *
 */
public class ReplicacaoReferencias extends AbstractReplicacaoCadastro<Referencia> {

	/** O atributo delegate. */
	private ReferenciaDelegate delegate = 
			CAPESCadastroFabricaDelegates.getInstance().criarReferenciaDelegate();
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected List<Referencia> listar(PessoaCompartilhamento pessoa) throws BancoobException {
		ConsultaDto<Referencia> criterios = new ConsultaDto<Referencia>();
		Referencia filtro = new Referencia();
		filtro.setPessoa(pessoa);
		criterios.setFiltro(filtro);
		return obterDelegate().listar(criterios);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ReferenciaDelegate obterDelegate() {
		return delegate;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Referencia obterNovaEntidade(Referencia entidade,
			PessoaCompartilhamento pessoa) {
		
		Referencia novaEntidade = null;
		
		try {
			
			if(entidade instanceof ReferenciaBancaria) {
				novaEntidade = new ReferenciaBancaria();
			} else {
				novaEntidade = new Referencia();
			}
			BeanUtils.copyProperties(novaEntidade, entidade);
			novaEntidade.setPessoa(pessoa);
			novaEntidade.setIdReferenciaPessoa(null);
			novaEntidade.setGravarHistorico(false);
			novaEntidade.setPessoaReferencia(null);
		} catch (IllegalAccessException e) {
			throw new BancoobRuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new BancoobRuntimeException(e);
		}
		
		return novaEntidade;
	}
}
