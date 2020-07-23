/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.enums;

import br.com.sicoob.capes.comum.negocio.enums.ListaLegadoEnum;
import br.com.sicoob.capes.negocio.entidades.Nacionalidade;
import br.com.sicoob.capes.negocio.entidades.TipoEndereco;
import br.com.sicoob.capes.negocio.entidades.TipoFonteRenda;
import br.com.sicoob.capes.negocio.entidades.TipoReferencia;
import br.com.sicoob.capes.negocio.entidades.UnidadeMedida;
import br.com.sicoob.capes.negocio.entidades.bemantigo.SituacaoBem;
import br.com.sicoob.capes.negocio.entidades.bemantigo.TipoBem;
import br.com.sicoob.capes.negocio.entidades.bemantigo.TipoPosseBem;
import br.com.sicoob.capes.negocio.entidades.interfaces.DominioReplicavel;
import br.com.sicoob.capes.negocio.entidades.interfaces.DominioReplicavelLista;

/**
 * Enum para os domínios replicáveis.
 * @author Erico.Junior
 */
public enum DominioReplicavelEnum {

	// Dominíos que estão em lista no legado.
	/** O atributo TIPO_ENDERECO. */
	TIPO_ENDERECO(ListaLegadoEnum.TIPO_ENDERECO, true, TipoEndereco.class),
	
	/** O atributo TIPO_POSSE_BEM. */
	TIPO_POSSE_BEM(ListaLegadoEnum.TIPO_POSSE_BEM, true, TipoPosseBem.class),
	
	/** O atributo TIPO_REFERENCIA. */
	TIPO_REFERENCIA(ListaLegadoEnum.TIPO_REFERENCIA, true, TipoReferencia.class),
	
	/** O atributo SITUACAO_BEM. */
	SITUACAO_BEM(ListaLegadoEnum.SITUACAO_BEM, true, SituacaoBem.class),
	
	/** O atributo TIPO_FONTE_RENDA. */
	TIPO_FONTE_RENDA(ListaLegadoEnum.TIPO_FONTE_RENDA, true, TipoFonteRenda.class),
	
	/** O atributo NACIONALIDADE. */
	NACIONALIDADE(ListaLegadoEnum.NACIONALIDADE, true, Nacionalidade.class),
	// Dominíos que não estão em lista no legado.
	/** O atributo UNIDADE_MEDIDA. */
	UNIDADE_MEDIDA(UnidadeMedida.class),
	
	/** O atributo TIPO_BEM. */
	TIPO_BEM(TipoBem.class);
	
	/** O atributo listaLegadoEnum. */
	private ListaLegadoEnum listaLegadoEnum;
	
	/** O atributo lista. */
	private boolean lista;
	
	/** O atributo entidade. */
	private Class<? extends DominioReplicavel> entidade;

	/**
	 * Construtor do enum para os domínios replicáveis.
	 * @param listaLegadoEnum O identificador da lista, se for lista.
	 * @param lista boolean indicando se o domínio é representado no SQL em Lista.
	 * @param classeEntidade A classe da entidade.
	 */
	private DominioReplicavelEnum(ListaLegadoEnum listaLegadoEnum, boolean lista, 
			Class<? extends DominioReplicavelLista> classeEntidade) {
		this(classeEntidade);
		this.listaLegadoEnum = listaLegadoEnum;
		this.lista = lista;
	}

	/**
	 * Construtor do enum para os domínios replicáveis.
	 * @param classeEntidade A classe da entidade.
	 */
	private DominioReplicavelEnum(Class<? extends DominioReplicavel> classeEntidade) {
		entidade = classeEntidade;
	}

	/**
	 * @return the idLista
	 */
	public Integer getIdLista() {
		return listaLegadoEnum.getIdLista();
	}

	/**
	 * @return the lista
	 */
	public boolean isLista() {
		return lista;
	}

	/**
	 * @return the entidade
	 */
	public Class<? extends DominioReplicavel> getEntidade() {
		return entidade;
	}
	
}
