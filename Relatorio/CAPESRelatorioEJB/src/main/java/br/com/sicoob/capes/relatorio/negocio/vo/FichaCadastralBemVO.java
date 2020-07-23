package br.com.sicoob.capes.relatorio.negocio.vo;

import java.util.List;

import br.com.sicoob.capes.negocio.entidades.bemantigo.BemBase;
import br.com.sicoob.capes.negocio.entidades.bemantigo.BemOnus;
import br.com.sicoob.capes.negocio.entidades.bemantigo.BemPosse;
import br.com.sicoob.capes.negocio.entidades.bemantigo.BemRegistro;
import br.com.sicoob.capes.negocio.entidades.bemantigo.TipoBem;

/**
 * A Classe FichaCadastralBemVO.
 */
public class FichaCadastralBemVO implements Comparable<FichaCadastralBemVO>  {

	/** O atributo bens. */
	private List<BemBase> bens;
	
	/** O atributo bensOnus. */
	private List<BemOnus> bensOnus;
	
	/** O atributo bensPosse. */
	private List<BemPosse> bensPosse;
	
	/** O atributo bensRegistro. */
	private List<BemRegistro> bensRegistro;
	
	/** O atributo tipoBem. */
	private TipoBem tipoBem;
	
	/**
	 * Recupera o valor de tipoBem.
	 *
	 * @return o valor de tipoBem
	 */
	public TipoBem getTipoBem() {
		return tipoBem;
	}
	
	/**
	 * Define o valor de tipoBem.
	 *
	 * @param tipoBem o novo valor de tipoBem
	 */
	public void setTipoBem(TipoBem tipoBem) {
		this.tipoBem = tipoBem;
	}
	
	/**
	 * Recupera o valor de bens.
	 *
	 * @return o valor de bens
	 */
	public List<BemBase> getBens() {
		return bens;
	}
	
	/**
	 * Define o valor de bens.
	 *
	 * @param bens o novo valor de bens
	 */
	public void setBens(List<BemBase> bens) {
		this.bens = bens;
	}
	
	/**
	 * Recupera o valor de bensOnus.
	 *
	 * @return o valor de bensOnus
	 */
	public List<BemOnus> getBensOnus() {
		return bensOnus;
	}
	
	/**
	 * Define o valor de bensOnus.
	 *
	 * @param bensOnus o novo valor de bensOnus
	 */
	public void setBensOnus(List<BemOnus> bensOnus) {
		this.bensOnus = bensOnus;
	}
	
	/**
	 * Recupera o valor de bensPosse.
	 *
	 * @return o valor de bensPosse
	 */
	public List<BemPosse> getBensPosse() {
		return bensPosse;
	}
	
	/**
	 * Define o valor de bensPosse.
	 *
	 * @param bensPosse o novo valor de bensPosse
	 */
	public void setBensPosse(List<BemPosse> bensPosse) {
		this.bensPosse = bensPosse;
	}
	
	/**
	 * Recupera o valor de bensRegistro.
	 *
	 * @return o valor de bensRegistro
	 */
	public List<BemRegistro> getBensRegistro() {
		return bensRegistro;
	}
	
	/**
	 * Define o valor de bensRegistro.
	 *
	 * @param bensRegistro o novo valor de bensRegistro
	 */
	public void setBensRegistro(List<BemRegistro> bensRegistro) {
		this.bensRegistro = bensRegistro;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public int compareTo(FichaCadastralBemVO objeto) {
		return this.getTipoBem().getCodigo() - objeto.getTipoBem().getCodigo();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bens == null) ? 0 : bens.hashCode());
		result = prime * result + ((bensOnus == null) ? 0 : bensOnus.hashCode());
		result = prime * result	+ ((bensPosse == null) ? 0 : bensPosse.hashCode());
		result = prime * result	+ ((bensRegistro == null) ? 0 : bensRegistro.hashCode());
		result = prime * result + ((tipoBem == null) ? 0 : tipoBem.hashCode());
		return result;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj){
			return true;
		}
		if (obj == null){
			return false;
		}
		if (getClass() != obj.getClass()){
			return false;
		}
		FichaCadastralBemVO other = (FichaCadastralBemVO) obj;
		if (bens == null) {
			if (other.bens != null){
				return false;
			}
		} else if (!bens.equals(other.bens)){
			return false;
		}
		if (bensOnus == null) {
			if (other.bensOnus != null){
				return false;
			}
		} else if (!bensOnus.equals(other.bensOnus)){
			return false;
		}
		if (bensPosse == null) {
			if (other.bensPosse != null){
				return false;
			}
		} else if (!bensPosse.equals(other.bensPosse)){
			return false;
		}
		if (bensRegistro == null) {
			if (other.bensRegistro != null){
				return false;
			}
		} else if (!bensRegistro.equals(other.bensRegistro)){
			return false;
		}
		if (tipoBem == null) {
			if (other.tipoBem != null){
				return false;
			}
		} else if (!tipoBem.equals(other.tipoBem)){
			return false;
		}
		return true;
	}

}