/*
 * SICOOB
 * 
 * BemImovel.java(br.com.sicoob.capes.negocio.entidades.bemantigo.BemImovel)
 */
package br.com.sicoob.capes.negocio.entidades.bemantigo;

import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.sicoob.capes.comum.negocio.annotation.NaoVerificarGestorResponsavel;
import br.com.sicoob.capes.negocio.entidades.interfaces.Vigente;

/**
 * The Class BemImovel.
 */
@Entity(name = "BEMIMOVELANTIGO")
@Table(name = "BEMPESSOAIMOVEL", schema = "CLI")

@NaoVerificarGestorResponsavel
public class BemImovel extends BemImovelBase implements Vigente {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}