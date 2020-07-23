package br.com.sicoob.capes.relatorio.persistencia.conversor;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.commons.lang.ArrayUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.excecao.BancoobRuntimeException;
import br.com.sicoob.capes.comum.util.ReflexaoUtils;

/**
 * @author Rodrigo.Chaves
 * 
 * @param <C>
 *            a Classe do objeto que sera criado
 */
public class ConversorAlias<C> implements ConversorResultado<C> {

	/** O atributo classe. */
	private Class<C> classe;
	
	/** O atributo descritores. */
	private PropertyDescriptor[] descritores;

	/**
	 * Instancia um novo ConversorAlias.
	 *
	 * @param alias o valor de alias
	 */
	@SuppressWarnings("unchecked")
	public ConversorAlias(String... alias) {

		if (alias == null) {
			throw new IllegalArgumentException("alias");
		}

		try {
			this.classe = (Class<C>) ReflexaoUtils.obterParametroGenerico(getClass());
		} catch (ClassCastException e) {
			throw new PersistenceException("Falha ao obter a classe para conversao: "
			        + "o tipo generico nao foi declarado", e);
		}

		this.descritores = new PropertyDescriptor[alias.length];
		for (PropertyDescriptor descritor : ReflexaoUtils.getPropertyDescriptors(classe)) {
			int index = ArrayUtils.indexOf(alias, descritor.getName());
			if (index != -1) {
				this.descritores[index] = descritor;
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public C converterTupla(Object[] tupla) throws BancoobException {

		C objeto = null;
		if ((tupla != null) && (tupla.length == this.descritores.length)) {
			objeto = ReflexaoUtils.construirObjetoPorClasse(this.classe);
			try {
				for (int i = 0; i < tupla.length; i++) {
					this.descritores[i].getWriteMethod().invoke(objeto, tupla[i]);
				}
			} catch (IllegalArgumentException e) {
				throw new BancoobRuntimeException(e);
			} catch (IllegalAccessException e) {
				throw new BancoobRuntimeException(e);
			} catch (InvocationTargetException e) {
				throw new BancoobRuntimeException(e);
			}
		}
		return objeto;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<C> tratarLista(List<C> lista) throws BancoobException {
		return lista;
	}

}
