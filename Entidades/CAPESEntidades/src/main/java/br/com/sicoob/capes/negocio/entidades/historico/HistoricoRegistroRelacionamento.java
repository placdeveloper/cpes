/*
 * SICOOB
 * 
 * HistoricoRegistroRelacionamento.java(br.com.sicoob.capes.negocio.entidades.historico.HistoricoRegistroRelacionamento)
 */
package br.com.sicoob.capes.negocio.entidades.historico;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.sicoob.capes.negocio.entidades.DadosRegistroRelacionamento;

/**
 * Entidade que representa o histório do registro de relacionamento de pessoas
 *
 * 24/08/2011
 * @author Rodrigo.Chaves
 */
@Entity
@Table(schema = "CLI", name = "HISTREGISTRORELACIONAMENTO")
public class HistoricoRegistroRelacionamento extends HistoricoRelacionamentoPessoa {

	/** Serial UID */
	private static final long serialVersionUID = -8477715423494655906L;
	
	/** O atributo dados registro. */
	@Embedded
	private DadosRegistroRelacionamento dadosRegistro;

	/**
	 * Recupera dados registro.
	 * 
	 * @return dados registro
	 */
	public DadosRegistroRelacionamento getDadosRegistro() {
		return dadosRegistro;
	}

	/**
	 * Preenche dados registro.
	 * 
	 * @param dadosRegistro
	 *            o novo dados registro
	 */
	public void setDadosRegistro(DadosRegistroRelacionamento dadosRegistro) {
		this.dadosRegistro = dadosRegistro;
	}
	
}
