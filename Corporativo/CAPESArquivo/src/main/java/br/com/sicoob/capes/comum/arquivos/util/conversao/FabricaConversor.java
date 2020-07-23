package br.com.sicoob.capes.comum.arquivos.util.conversao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.comum.arquivos.excecao.UsoIncorretoApiException;
import br.com.sicoob.capes.comum.util.ReflexaoUtils;

/**
 * F�brica utilizada para obter um conversor do tipo correto dependendo do tipo de dado informado.
 * 
 * Criado em 22/7/2014
 * 
 * @author rodrigo.chaves
 */
public final class FabricaConversor {

	/** O atributo instancia. */
	private static FabricaConversor instancia;

	/** O atributo mapeamentoConversores. */
	private Map<Class<?>, Class<?>> mapeamentoConversores;

	/**
	 * Essa classe implementa o padr�o <code>Singleton</code> e n�o pode ser instanciada.
	 * 
	 * @see #getInstancia()
	 */
	private FabricaConversor() {

		mapeamentoConversores = new HashMap<Class<?>, Class<?>>();

		// Datas
		mapeamentoConversores.put(Date.class, ConversorData.class);
		mapeamentoConversores.put(DateTimeDB.class, ConversorData.class);

		// N�meros
		mapeamentoConversores.put(Short.class, ConversorNumero.class);
		mapeamentoConversores.put(Short.TYPE, ConversorNumero.class);
		mapeamentoConversores.put(Long.class, ConversorNumero.class);
		mapeamentoConversores.put(Long.TYPE, ConversorNumero.class);
		mapeamentoConversores.put(Integer.class, ConversorNumero.class);
		mapeamentoConversores.put(Integer.TYPE, ConversorNumero.class);
		mapeamentoConversores.put(Double.class, ConversorNumero.class);
		mapeamentoConversores.put(Double.TYPE, ConversorNumero.class);
		mapeamentoConversores.put(BigDecimal.class, ConversorNumero.class);

		// Outros
		mapeamentoConversores.put(String.class, ConversorString.class);
		mapeamentoConversores.put(Character.class, ConversorCaracter.class);
		mapeamentoConversores.put(Character.TYPE, ConversorCaracter.class);
		mapeamentoConversores.put(Boolean.class, ConversorBoolean.class);
		mapeamentoConversores.put(Boolean.TYPE, ConversorBoolean.class);
	}

	/**
	 * Obt�m a inst�ncia da f�brica de conversores.
	 * 
	 * @return FabricaConversor - Inst�ncia da f�brica.
	 */
	public static synchronized FabricaConversor getInstancia() {

		if (instancia == null) {
			instancia = new FabricaConversor();
		}
		return instancia;
	}

	/**
	 * Obt�m o conversor do tipo de dado informado.
	 * 
	 * @param <T>
	 *            - Classe do objeto para qual se deseja obter o conversor.
	 * @param tipoDado
	 *            - Tipo do dado a ser convertido.
	 * @return Conversor - conversor do dado informado ou <code>null</code> caso o dado n�o seja suportado pelo
	 *         componente.
	 */
	@SuppressWarnings("unchecked")
	public <T> Conversor<T> getConversor(Class<T> tipoDado) {

		Class<Conversor<T>> classe = (Class<Conversor<T>>) mapeamentoConversores.get(tipoDado);
		if (classe == null) {
			throw new UsoIncorretoApiException("exception.conversao.tipo", tipoDado.getName());
		}
		Conversor<T> conversor = ReflexaoUtils.instanciar(classe);
		conversor.setTipoDado(tipoDado);
		return conversor;
	}

}