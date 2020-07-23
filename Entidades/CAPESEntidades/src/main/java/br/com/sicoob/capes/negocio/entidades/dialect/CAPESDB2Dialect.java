package br.com.sicoob.capes.negocio.entidades.dialect;

import org.hibernate.dialect.DB2Dialect;

/**
 * Classe criada para solucionar um erro no hibernate 5, 
 * onde ao usar uma {@code @Formula} o hibernate tentava colocar 
 * um alias em uma função do banco.
 * 
 * <p>
 * Ex: pessoaComp_198290.coalesce();
 * </p>
 *
 * @author Bruno.Carneiro
 */
public class CAPESDB2Dialect extends DB2Dialect {

	/**
	 * Método construtor.
	 */
	public CAPESDB2Dialect() {
		super();
		registerKeyword("coalesce");
	}

}