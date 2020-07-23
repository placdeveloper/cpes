package br.com.sicoob.capes.negocio.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * A Classe TipoDestinoExibicaoTipoMensagemPK.
 */
@Embeddable
public class TipoDestinoExibicaoTipoMensagemPK implements Serializable{

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -4050190646453222903L;
	
	/** O atributo codigoTipoMensagem. */
	@Column(name="CODTIPOMENSAGEM",insertable=true,updatable=true)
	private Short codigoTipoMensagem;
	
	/** O atributo codigoTipoDestinoExibicao. */
	@Column(name="CODTIPODESTINOEXIBICAO",insertable=true,updatable=true)
	private Short codigoTipoDestinoExibicao;

	/**
	 * Recupera o valor de codigoTipoMensagem.
	 *
	 * @return o valor de codigoTipoMensagem
	 */
	public Short getCodigoTipoMensagem() {
		return codigoTipoMensagem;
	}

	/**
	 * Define o valor de codigoTipoMensagem.
	 *
	 * @param codigoTipoMensagem o novo valor de codigoTipoMensagem
	 */
	public void setCodigoTipoMensagem(Short codigoTipoMensagem) {
		this.codigoTipoMensagem = codigoTipoMensagem;
	}

	/**
	 * Recupera o valor de codigoTipoDestinoExibicao.
	 *
	 * @return o valor de codigoTipoDestinoExibicao
	 */
	public Short getCodigoTipoDestinoExibicao() {
		return codigoTipoDestinoExibicao;
	}

	/**
	 * Define o valor de codigoTipoDestinoExibicao.
	 *
	 * @param codigoTipoDestinoExibicao o novo valor de codigoTipoDestinoExibicao
	 */
	public void setCodigoTipoDestinoExibicao(Short codigoTipoDestinoExibicao) {
		this.codigoTipoDestinoExibicao = codigoTipoDestinoExibicao;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoTipoDestinoExibicao == null) ? 0 : codigoTipoDestinoExibicao.hashCode());
		result = prime * result + ((codigoTipoMensagem == null) ? 0 : codigoTipoMensagem.hashCode());
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
		TipoDestinoExibicaoTipoMensagemPK other = (TipoDestinoExibicaoTipoMensagemPK) obj;
		if (codigoTipoDestinoExibicao == null) {
			if (other.codigoTipoDestinoExibicao != null) {
				return false;
			}
		} else if (!codigoTipoDestinoExibicao.equals(other.codigoTipoDestinoExibicao)) {
			return false;
		}
		if (codigoTipoMensagem == null) {
			if (other.codigoTipoMensagem != null) {
				return false;
			}
		} else if (!codigoTipoMensagem.equals(other.codigoTipoMensagem)) {
			return false;
		}
		return true;
	}

}