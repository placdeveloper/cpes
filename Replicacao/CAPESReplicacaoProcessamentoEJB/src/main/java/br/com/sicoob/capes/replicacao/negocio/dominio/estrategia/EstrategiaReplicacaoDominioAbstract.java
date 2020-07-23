/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.dominio.estrategia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroCrudDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.interfaces.DominioReplicavel;
import br.com.sicoob.capes.negocio.entidades.legado.EntidadeDominioReplicavel;
import br.com.sicoob.capes.replicacao.negocio.delegates.EntidadeDominioReplicavelDelegate;
import br.com.sicoob.capes.replicacao.negocio.enums.DominioReplicavelEnum;

/**
 * Superclasse para as estrat�gias de replica��o de dom�nio.
 * 
 * @author Erico.Junior
 */
public abstract class EstrategiaReplicacaoDominioAbstract<T extends DominioReplicavel, 
		E extends EntidadeDominioReplicavel<?>>	implements EstrategiaReplicacaoDominio {

	/** O atributo PROD_LAB. */
	private static String PROD_LAB = "9162";

	/** O atributo dominioReplicavelEnum. */
	private DominioReplicavelEnum dominioReplicavelEnum;

	/**
	 * Instancia um novo EstrategiaReplicacaoDominioAbstract.
	 *
	 * @param tabela o valor de tabela
	 */
	public EstrategiaReplicacaoDominioAbstract(DominioReplicavelEnum tabela) {
		dominioReplicavelEnum = tabela;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public void replicar() throws BancoobException {

		Class<T> classe = (Class<T>) dominioReplicavelEnum.getEntidade();
		List<T> listaCadastro = listar(classe);
		List<E> listaCadastroConvertida = converterLista(listaCadastro);
		Map<Serializable, E> mapaLegado = recuperarMapaLegado();

		atualizar(listaCadastroConvertida, mapaLegado);
	}

	/**
	 * Atualiza o legado a partir da lista do cadastro �nico.
	 * @param listaCadastroConvertida A lista do cadastro �nico convertida em objetos do legado.
	 * @param mapaLegado A tabela do legado representada em um mapa.
	 * @throws BancoobException Caso ocorra alguma exce��o.
	 */
	private void atualizar(List<E> listaCadastroConvertida, Map<Serializable, E> mapaLegado) 
		throws BancoobException {

		List<E> itensAlteracao = new ArrayList<E>();
		List<E> itensInclusao = new ArrayList<E>();
		
		for (E novaEntidade : listaCadastroConvertida) {
			
			Serializable chave = novaEntidade.getIdSQL();
			if(mapaLegado.containsKey(chave)) {
				E entidadeLegado = mapaLegado.get(chave);
				if(!entidadeLegado.equals(novaEntidade)) {
					itensAlteracao.add(novaEntidade);
				}
			} else {
				itensInclusao.add(novaEntidade);
			}
		}

		if(!itensAlteracao.isEmpty() || !itensInclusao.isEmpty()) {
			Instituicao instituicao = obterInstituicaoProdLab();
			obterDelegate().atualizar(itensInclusao, itensAlteracao, instituicao.getIdInstituicao());
		}
	}

	/**
	 * Converte a lista do cadastro �nico em objetos do legado.
	 * @param listaCadastro A lista do cadastro �nico.
	 * @return a lista do cadastro �nico convertida em objetos do legado.
	 */
	private List<E> converterLista(List<T> listaCadastro) {

		List<E> lista = new ArrayList<E>();
		E entidadeLegado = null;
		for (T entidadeCadastro : listaCadastro) {
			entidadeLegado = converter(entidadeCadastro);
			lista.add(entidadeLegado);
		}

		return lista;
	}

	/**
	 * Lista todos os registros da entidade replic�vel informada.
	 * 
	 * @param classe
	 *            A classe da entidade da qual se deseja os registros.
	 * @return lista com todos os registros da entidade replic�vel informada.
	 * @throws BancoobException
	 *             Caso ocorra alguma exce��o.
	 */
	@SuppressWarnings("unchecked")
	protected List<T> listar(Class<T> classe) throws BancoobException {

		CAPESCadastroFabricaDelegates fabrica = 
				CAPESCadastroFabricaDelegates.getInstance();
		CAPESCadastroCrudDelegate<?, ?> delegate = fabrica.criarDelegate(classe);
		return (List<T>) delegate.listar();
	}

	/**
	 * Recupera os dados do legado.
	 * @return A tabela do legado representada em um mapa.
	 * @throws BancoobException  Caso ocorra alguma exce��o.
	 */
	protected Map<Serializable, E> recuperarMapaLegado() throws BancoobException {

		Instituicao instituicao = obterInstituicaoProdLab();
		List<E> lista = (List<E>) obterDelegate().listar(instituicao.getIdInstituicao());

		Map<Serializable, E> mapa = new HashMap<Serializable, E>();
		for (E entidadeLegado : lista) {
			mapa.put(entidadeLegado.getIdSQL(), entidadeLegado);
		}
		
		return mapa;
	}

	/**
	 * Recupera a institui��o com o n�mero do PROD_LAB.
	 * 
	 * @return a institui��o com o n�mero do PROD_LAB.
	 */
	protected Instituicao obterInstituicaoProdLab() {
		Instituicao instituicao = new Instituicao();
		instituicao.setNumero(PROD_LAB);
		return instituicao;
	}

	/**
	 * Obter delegate.
	 *
	 * @return EntidadeDominioReplicavelDelegate
	 */
	protected abstract EntidadeDominioReplicavelDelegate<E, ?> obterDelegate();

	/**
	 * Converter.
	 *
	 * @param entidadeCadastro o valor de entidade cadastro
	 * @return E
	 */
	protected abstract E converter(T entidadeCadastro);

}
