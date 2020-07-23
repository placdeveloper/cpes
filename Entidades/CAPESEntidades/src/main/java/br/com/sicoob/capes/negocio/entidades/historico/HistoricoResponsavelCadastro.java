/*
 * SICOOB
 * 
 * HistoricoResponsavelCadastro.java(br.com.sicoob.capes.negocio.entidades.historico.HistoricoResponsavelCadastro)
 */
package br.com.sicoob.capes.negocio.entidades.historico;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.sicoob.capes.negocio.entidades.ResponsavelCadastroBase;
import br.com.sicoob.capes.negocio.entidades.interfaces.Historico;

/**
 * The Class HistoricoResponsavelCadastro.
 */
@Entity
@Table(name="HISTINSTITUICAORESPONSAVELCADASTRO", schema="CLI")
public class HistoricoResponsavelCadastro extends ResponsavelCadastroBase implements Historico {

	/** Serial UID.*/
	private static final long serialVersionUID = 4864993724316144454L;

	/** O atributo data hora fim. */
	private Date dataHoraFim;
	
	/** O atributo id usuario exclusao. */
	@Column(name = "IDUSUARIOEXCL", length = 40)
	private String idUsuarioExclusao;

	/**
	 * @return the dataHoraFim
	 */
	public Date getDataHoraFim() {
		return dataHoraFim;
	}

	/**
	 * @param dataHoraFim the dataHoraFim to set
	 */
	public void setDataHoraFim(Date dataHoraFim) {
		this.dataHoraFim = dataHoraFim;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Long getId() {
		return getIdPessoaCompartilhamento();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Long id) {
		setIdPessoaCompartilhamento(id);
	}

	/** 
	 * {@inheritDoc}
	 */
	public Serializable getIdEntidadeVigente() {
		return getIdPessoaCompartilhamento();
	}

	/**
	 * Recupera id usuario exclusao.
	 * 
	 * @return id usuario exclusao
	 */
	public String getIdUsuarioExclusao() {
		return idUsuarioExclusao;
	}

	/** 
	 * {@inheritDoc}
	 */
	public void setIdUsuarioExclusao(String idUsuarioExclusao) {
		this.idUsuarioExclusao = idUsuarioExclusao;
	}

}
