package br.com.sicoob.capes.negocio.entidades;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * A Classe TipoDestinoExibicaoTipoMensagem.
 */
@Entity
@Table(name="TIPODESTINOEXIBICAOTIPOMENSAGEM",schema="CLI")
public class TipoDestinoExibicaoTipoMensagem implements Serializable {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 5519152726666393423L;

	/** O atributo destinoExibicaoTipoMensagemPK. */
	@EmbeddedId
	private TipoDestinoExibicaoTipoMensagemPK destinoExibicaoTipoMensagemPK;
	
	/** O atributo tipoMensagem. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CODTIPOMENSAGEM", referencedColumnName="CODTIPOMENSAGEM", insertable=false, updatable=false)
	private TipoMensagem tipoMensagem;


	/** O atributo tipoDestinoExibicao. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CODTIPODESTINOEXIBICAO", referencedColumnName="CODTIPODESTINOEXIBICAO", insertable=false, updatable=false)
	private TipoDestinoExibicao tipoDestinoExibicao;
	
	/**
	 * Recupera o valor de tipoMensagem.
	 *
	 * @return o valor de tipoMensagem
	 */
	public TipoMensagem getTipoMensagem() {
		return tipoMensagem;
	}


	/**
	 * Define o valor de tipoMensagem.
	 *
	 * @param tipoMensagem o novo valor de tipoMensagem
	 */
	public void setTipoMensagem(TipoMensagem tipoMensagem) {
		this.tipoMensagem = tipoMensagem;
	}


	/**
	 * Recupera o valor de tipoDestinoExibicao.
	 *
	 * @return o valor de tipoDestinoExibicao
	 */
	public TipoDestinoExibicao getTipoDestinoExibicao() {
		return tipoDestinoExibicao;
	}


	/**
	 * Define o valor de tipoDestinoExibicao.
	 *
	 * @param tipoDestinoExibicao o novo valor de tipoDestinoExibicao
	 */
	public void setTipoDestinoExibicao(TipoDestinoExibicao tipoDestinoExibicao) {
		this.tipoDestinoExibicao = tipoDestinoExibicao;
	}


	/**
	 * Recupera o valor de destinoExibicaoTipoMensagemPK.
	 *
	 * @return o valor de destinoExibicaoTipoMensagemPK
	 */
	public TipoDestinoExibicaoTipoMensagemPK getDestinoExibicaoTipoMensagemPK() {
		return destinoExibicaoTipoMensagemPK;
	}


	/**
	 * Define o valor de destinoExibicaoTipoMensagemPK.
	 *
	 * @param destinoExibicaoTipoMensagemPK o novo valor de destinoExibicaoTipoMensagemPK
	 */
	public void setDestinoExibicaoTipoMensagemPK(
			TipoDestinoExibicaoTipoMensagemPK destinoExibicaoTipoMensagemPK) {
		this.destinoExibicaoTipoMensagemPK = destinoExibicaoTipoMensagemPK;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((destinoExibicaoTipoMensagemPK == null) ? 0 : destinoExibicaoTipoMensagemPK.hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		TipoDestinoExibicaoTipoMensagem other = (TipoDestinoExibicaoTipoMensagem) obj;
		if (destinoExibicaoTipoMensagemPK == null) {
			if (other.destinoExibicaoTipoMensagemPK != null) {
				return false;
			}
		} else if (!destinoExibicaoTipoMensagemPK.equals(other.destinoExibicaoTipoMensagemPK)) {
			return false;
		}
		return true;
	}
	
}