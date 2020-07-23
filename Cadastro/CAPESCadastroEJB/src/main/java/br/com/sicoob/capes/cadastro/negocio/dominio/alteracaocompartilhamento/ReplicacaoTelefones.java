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
import br.com.sicoob.capes.cadastro.negocio.delegates.TelefoneDelegate;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.Telefone;

/**
 * @author erico.junior
 */
public class ReplicacaoTelefones extends AbstractReplicacaoCadastro<Telefone> {

	/** O atributo delegate. */
	private TelefoneDelegate delegate = 
			CAPESCadastroFabricaDelegates.getInstance().criarTelefoneDelegate();

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected List<Telefone> listar(PessoaCompartilhamento pessoa)
			throws BancoobException {

		Telefone Telefone = new Telefone();
		Telefone.setPessoaCompartilhamento(pessoa);

		ConsultaDto<Telefone> consulta = new ConsultaDto<Telefone>();
		consulta.setFiltro(Telefone);
		return obterDelegate().listar(consulta);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TelefoneDelegate obterDelegate() {
		return delegate;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Telefone obterNovaEntidade(Telefone entidade, PessoaCompartilhamento pessoa) {
		
		Telefone novaEntidade = new Telefone();
		
		try {
			BeanUtils.copyProperties(novaEntidade, entidade);
			novaEntidade.setPessoaCompartilhamento(pessoa);
			novaEntidade.setIdTelefonePessoa(null);
			novaEntidade.setGravarHistorico(false);
		} catch (IllegalAccessException e) {
			throw new BancoobRuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new BancoobRuntimeException(e);
		}
		
		return novaEntidade;
	}
}
