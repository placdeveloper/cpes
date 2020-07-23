package br.com.sicoob.capes.cadastro.util;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;

import br.com.sicoob.capes.negocio.entidades.interfaces.Historico;
import br.com.sicoob.capes.negocio.entidades.interfaces.Vigente;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;

/**
 * A Classe EntidadeCadastroBaseComparator.
 *
 * @param <E> o tipo generico
 */
public class EntidadeCadastroBaseComparator<E extends CAPESEntidade<?>> 
	implements Comparator<E>, Serializable {

	/** Serial UID.*/
	private static final long serialVersionUID = 4360307852779789996L;

	/**
	 * {@inheritDoc}
	 */
	public int compare(E o1, E o2) {

		Long id1 = getId(o1);
		Long id2 = getId(o2);		
		
		int retorno = Long.valueOf(id1 - id2).intValue();
		
		if (retorno == 0) {
			retorno = getDataInicio(o2).compareTo(getDataInicio(o1));
		}
		
		return retorno;
		
	}

	/**
	 * Recupera o valor de id.
	 *
	 * @param objeto o valor de objeto
	 * @return o valor de id
	 */
	private Long getId(E objeto) {
		Long id1 = null;
		if (objeto instanceof Historico) {
			id1 = ((Number) ((Historico) objeto).getIdEntidadeVigente()).longValue();
		} else {
			id1 = ((Number) (objeto.getId())).longValue();
		}
		return id1;
	}
	
	/**
	 * Recupera o valor de dataInicio.
	 *
	 * @param objeto o valor de objeto
	 * @return o valor de dataInicio
	 */
	private Date getDataInicio(E objeto) {
		Date data = null;
		if (objeto instanceof Historico) {
			data = ((Historico) objeto).getDataHoraInicio();
		} else if (objeto instanceof Vigente){
			data = ((Vigente) objeto).getDataHoraInicio();
		}
		
		return data;
	}
}