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
import br.com.sicoob.capes.cadastro.negocio.delegates.EnderecoDelegate;
import br.com.sicoob.capes.cadastro.negocio.proxies.EnderecoProxy;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.vigente.Endereco;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * Replica os endereços de uma pessoa para outra na cópia do cadastro para outro grupo de compartilhamento
 * @author erico.junior
 */
public final class ReplicacaoEnderecos {

	/** O atributo delegate. */
	private EnderecoDelegate delegate = 
			CAPESCadastroFabricaDelegates.getInstance().criarEnderecoDelegate();
		
	/**
	 * O método Executar.
	 *
	 * @param origem o valor de origem
	 * @param destino o valor de destino
	 * @param instituicao o valor de instituicao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public void executar(PessoaCompartilhamento origem, PessoaCompartilhamento destino, Instituicao instituicao) 
			throws BancoobException{
		
		List<EnderecoProxy> lista = listar(origem);
		
		if(lista != null && !lista.isEmpty()) {

			for (EnderecoProxy proxy : lista) {

				Endereco novoEndereco = obterNovaEntidade(proxy.getIdEndereco(), destino);
				novoEndereco = obterDelegate().incluir(novoEndereco, instituicao);

				if(proxy.getPadraoCorrespondencia()) {
					novoEndereco.setVerificarAutorizacao(false);
					delegate.tornarPadraoCorrespondencia(novoEndereco, instituicao);
				}
			}
		}
	}
	
	/**
	 * Listar.
	 *
	 * @param pessoa o valor de pessoa
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	protected List<EnderecoProxy> listar(PessoaCompartilhamento pessoa)
			throws BancoobException {
		Endereco endereco = new Endereco();
		endereco.setPessoaCompartilhamento(pessoa);
		ConsultaDto<Endereco> consulta = new ConsultaDto<Endereco>();
		consulta.setFiltro(endereco);
		return obterDelegate().listarEnderecos(consulta);
	}

	/**
	 * Obter delegate.
	 *
	 * @return EnderecoDelegate
	 */
	protected EnderecoDelegate obterDelegate() {
		return delegate;
	}

	/**
	 * Obter nova entidade.
	 *
	 * @param idEndereco o valor de id endereco
	 * @param pessoa o valor de pessoa
	 * @return Endereco
	 * @throws BancoobException lança a exceção BancoobException
	 */
	protected Endereco obterNovaEntidade(Long idEndereco, PessoaCompartilhamento pessoa) throws BancoobException {
		
		Endereco entidade = obterDelegate().obter(idEndereco);
		Endereco novaEntidade = new Endereco();
		
		try {
			BeanUtils.copyProperties(novaEntidade, entidade);
			novaEntidade.setPessoaCompartilhamento(pessoa);
			novaEntidade.setIdEndereco(null);
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
