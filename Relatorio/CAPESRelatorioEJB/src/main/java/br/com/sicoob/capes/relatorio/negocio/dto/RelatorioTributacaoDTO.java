package br.com.sicoob.capes.relatorio.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.capes.cadastro.negocio.vo.CentralSingularVO;
import br.com.sicoob.capes.negocio.entidades.Funcionario;
import br.com.sicoob.capes.negocio.entidades.Nucleo;

/**
 * A Classe RelatorioTributacaoDTO.
 */
public class RelatorioTributacaoDTO extends BancoobDto {

    /** A constante serialVersionUID. */
    private static final long serialVersionUID = 3117478456581031278L;
    
	/** O atributo central. */
	private CentralSingularVO central;
	
	/** O atributo singular. */
	private CentralSingularVO singular;
	
	/** O atributo idUnidadeInst. */
	private Integer idUnidadeInst;
	
	/** O atributo nucleo. */
	private Nucleo nucleo;
	
	/** O atributo funcionario. */
	private Funcionario funcionario;
	
	/** O atributo cobraIOF. */
	private Boolean cobraIOF;
	
	/** O atributo cobraIR. */
	private Boolean cobraIR;

	/**
	 * @return the idCentral
	 */
	public CentralSingularVO getCentral() {
		return central;
	}

	/**
	 * @param central
	 *            the idCentral to set
	 */
	public void setCentral(CentralSingularVO central) {
		this.central = central;
	}

	/**
	 * @return the idInstituicao
	 */
	public CentralSingularVO getSingular() {
		return singular;
	}

	/**
	 * @param singular
	 *            the idInstituicao to set
	 */
	public void setSingular(CentralSingularVO singular) {
		this.singular = singular;
	}

	/**
	 * @return the idUnidadeInst
	 */
	public Integer getIdUnidadeInst() {
		return idUnidadeInst;
	}

	/**
	 * @param idUnidadeInst
	 *            the idUnidadeInst to set
	 */
	public void setIdUnidadeInst(Integer idUnidadeInst) {
		this.idUnidadeInst = idUnidadeInst;
	}

	/**
     * @return the idNucleo
     */
    public Nucleo getNucleo() {
    	return nucleo;
    }

	/**
     * @param nucleo the idNucleo to set
     */
    public void setNucleo(Nucleo nucleo) {
    	this.nucleo = nucleo;
    }

	/**
	 * @return the idFuncionario
	 */
	public Funcionario getFuncionario() {
		return funcionario;
	}

	/**
	 * @param funcionario
	 *            the idFuncionario to set
	 */
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	/**
	 * @return the cobraIOF
	 */
	public Boolean getCobraIOF() {
		return cobraIOF;
	}

	/**
	 * @param cobraIOF
	 *            the cobraIOF to set
	 */
	public void setCobraIOF(Boolean cobraIOF) {
		this.cobraIOF = cobraIOF;
	}

	/**
	 * @return the cobraIR
	 */
	public Boolean getCobraIR() {
		return cobraIR;
	}

	/**
	 * @param cobraIR
	 *            the cobraIR to set
	 */
	public void setCobraIR(Boolean cobraIR) {
		this.cobraIR = cobraIR;
	}

}
