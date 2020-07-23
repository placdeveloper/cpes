package br.com.sicoob.capes.api.negocio.vo;

import java.math.BigDecimal;

import br.com.bancoob.negocio.vo.BancoobVo;

public class BemProprietarioVO extends BancoobVo {
	private static final long serialVersionUID = 7926193123188000429L;

	private Integer idPessoa;
	private String cpfCnpj;
	private String nomePessoa;
	private Short codigoTipoPessoa;
	private BigDecimal percentualProprietario;
	private BigDecimal areaPosse;
	
	public BemProprietarioVO() {
		
	}
	
	public BemProprietarioVO(Integer idPessoa, String cpfCnpj, String nomePessoa, Short codigoTipoPessoa, BigDecimal percentualProprietario) {
		this(idPessoa, cpfCnpj, nomePessoa, codigoTipoPessoa, percentualProprietario, null);
	}
	
	public BemProprietarioVO(Integer idPessoa, String cpfCnpj, String nomePessoa, Short codigoTipoPessoa, BigDecimal percentualProprietario, BigDecimal areaPosse) {
		super();
		this.idPessoa = idPessoa;
		this.cpfCnpj = cpfCnpj;
		this.nomePessoa = nomePessoa;
		this.codigoTipoPessoa = codigoTipoPessoa;
		this.percentualProprietario = percentualProprietario;
		this.areaPosse = areaPosse;
	}

	public Integer getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(Integer idPessoa) {
		this.idPessoa = idPessoa;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getNomePessoa() {
		return nomePessoa;
	}

	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}

	public Short getCodigoTipoPessoa() {
		return codigoTipoPessoa;
	}

	public void setCodigoTipoPessoa(Short codigoTipoPessoa) {
		this.codigoTipoPessoa = codigoTipoPessoa;
	}

	public BigDecimal getPercentualProprietario() {
		return percentualProprietario;
	}

	public void setPercentualProprietario(BigDecimal percentualProprietario) {
		this.percentualProprietario = percentualProprietario;
	}

	public BigDecimal getAreaPosse() {
		return areaPosse;
	}

	public void setAreaPosse(BigDecimal areaPosse) {
		this.areaPosse = areaPosse;
	}
}