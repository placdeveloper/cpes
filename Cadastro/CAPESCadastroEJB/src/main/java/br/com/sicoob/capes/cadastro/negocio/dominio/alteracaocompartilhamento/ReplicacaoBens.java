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
import br.com.sicoob.capes.cadastro.negocio.delegates.bemantigo.BemAntigoDelegate;
import br.com.sicoob.capes.negocio.entidades.bemantigo.Bem;
import br.com.sicoob.capes.negocio.entidades.bemantigo.BemImovel;
import br.com.sicoob.capes.negocio.entidades.bemantigo.BemOnus;
import br.com.sicoob.capes.negocio.entidades.bemantigo.BemPosse;
import br.com.sicoob.capes.negocio.entidades.bemantigo.BemRegistro;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * @author erico.junior
 *
 */
public class ReplicacaoBens extends AbstractReplicacaoCadastro<Bem> {

	/** O atributo delegate. */
	private BemAntigoDelegate delegate = 
			CAPESCadastroFabricaDelegates.getInstance().criarBemAntigoDelegate();

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected List<Bem> listar(PessoaCompartilhamento pessoa)
			throws BancoobException {
		return obterDelegate().listarPorPessoa(pessoa);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected BemAntigoDelegate obterDelegate() {
		return delegate;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Bem obterNovaEntidade(Bem entidade, PessoaCompartilhamento pessoa) {
		
		Bem novaEntidade = null;
		
		try {

			if(entidade instanceof BemImovel) {
				novaEntidade = new BemImovel();
			} else {
				novaEntidade = new Bem();
			}
		
			BeanUtils.copyProperties(novaEntidade, entidade);
			novaEntidade.setPessoaCompartilhamento(pessoa);
			novaEntidade.setIdBem(null);
			novaEntidade.setGravarHistorico(false);
			novaEntidade.setBensOnus(criarListaOnus(novaEntidade, entidade));
			novaEntidade.setBensPosse(criarListaPosse(novaEntidade, entidade));
			novaEntidade.setBensRegistro(criarListaRegistro(novaEntidade, entidade));
			novaEntidade.setVerificarAutorizacao(false);
		} catch (IllegalAccessException e) {
			throw new BancoobRuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new BancoobRuntimeException(e);
		}
		
		return novaEntidade;
	}
	
	/**
	 * Criar lista onus.
	 *
	 * @param novoBem o valor de novo bem
	 * @param bem o valor de bem
	 * @return List
	 * @throws IllegalAccessException lança a exceção IllegalAccessException
	 * @throws InvocationTargetException lança a exceção InvocationTargetException
	 */
	private List<BemOnus> criarListaOnus(Bem novoBem, Bem bem) throws IllegalAccessException, InvocationTargetException {
		
		List<BemOnus> retorno = new ArrayList<BemOnus>();
		List<BemOnus> onus = bem.getBensOnus();
		
		if(onus != null && !onus.isEmpty()) {
			BemOnus novaEntidade = null;
			for (BemOnus bemOnus : onus) {
				novaEntidade = new BemOnus();
				BeanUtils.copyProperties(novaEntidade, bemOnus);
				novaEntidade.setIdBemOnus(null);
				novaEntidade.setBem(novoBem);	
				retorno.add(novaEntidade);
			}
		}
		return retorno;
	}
	
	/**
	 * Criar lista posse.
	 *
	 * @param novoBem o valor de novo bem
	 * @param bem o valor de bem
	 * @return List
	 * @throws IllegalAccessException lança a exceção IllegalAccessException
	 * @throws InvocationTargetException lança a exceção InvocationTargetException
	 */
	private List<BemPosse> criarListaPosse(Bem novoBem, Bem bem) 
			throws IllegalAccessException, InvocationTargetException {
		
		List<BemPosse> retorno = new ArrayList<BemPosse>();
		List<BemPosse> posses = bem.getBensPosse();
		
		if(posses != null && !posses.isEmpty()) {
			BemPosse novaEntidade = null;
			for (BemPosse bemPosse : posses) {
				novaEntidade = new BemPosse();
				BeanUtils.copyProperties(novaEntidade, bemPosse);
				novaEntidade.setIdBemPosse(null);
				novaEntidade.setBem(novoBem);	
				retorno.add(novaEntidade);
			}
		}

		return retorno;
	}
	
	/**
	 * Criar lista registro.
	 *
	 * @param novoBem o valor de novo bem
	 * @param bem o valor de bem
	 * @return List
	 * @throws IllegalAccessException lança a exceção IllegalAccessException
	 * @throws InvocationTargetException lança a exceção InvocationTargetException
	 */
	private List<BemRegistro> criarListaRegistro(Bem novoBem, Bem bem) 
			throws IllegalAccessException, InvocationTargetException {
		
		List<BemRegistro> retorno = new ArrayList<BemRegistro>();
		List<BemRegistro> registros = bem.getBensRegistro();
		
		if(registros != null && !registros.isEmpty()) {
			BemRegistro novaEntidade = null;
			for (BemRegistro bemRegistro : registros) {
				novaEntidade = new BemRegistro();
				BeanUtils.copyProperties(novaEntidade, bemRegistro);
				novaEntidade.setIdBemRegistro(null);
				novaEntidade.setBem(novoBem);
				retorno.add(novaEntidade);
			}
		}

		return retorno;
	}	
}
