package br.com.sicoob.capes.cadastro.negocio.vo;

import java.math.BigDecimal;

import br.com.bancoob.negocio.vo.BancoobVo;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

public class ProprietarioBemVO extends BancoobVo {
	private static final long serialVersionUID = -7469138746345577284L;

	private Long idBemPessoaCompartilhamento;
	private Long idPessoaCompartilhamento;
	private PessoaCompartilhamento pessoaCompartilhamento;
	private String cpfCnpj;
	private String nome;
	private Short codigoTipoPessoa;
	private BigDecimal porcentagem;
	private BigDecimal areaTotal;
	private BigDecimal areaPosse;
	private String tipoRelacionamento;
	private Boolean marcadoExclusao = Boolean.FALSE;

	public ProprietarioBemVO() {

	}

	public ProprietarioBemVO(Long idBemPessoaCompartilhamento, Long idPessoaCompartilhamento, PessoaCompartilhamento pessoaCompartilhamento, String cpfCnpj, Short codigoTipoPessoa, String nome, BigDecimal porcentagem, Boolean marcadoExclusao) {
		this(idBemPessoaCompartilhamento, idPessoaCompartilhamento, pessoaCompartilhamento, cpfCnpj, codigoTipoPessoa, nome, porcentagem, null, null, null, marcadoExclusao);
	}

	public ProprietarioBemVO(String cpfCnpj, Short codigoTipoPessoa, String nome, BigDecimal areaPosse, BigDecimal areaTotal, String tipoRelacionamento) {
		this(null, null, null, cpfCnpj, codigoTipoPessoa, nome, null, areaPosse, areaTotal, tipoRelacionamento, Boolean.FALSE);
	}
	
	public ProprietarioBemVO(Long idBemPessoaCompartilhamento, Long idPessoaCompartilhamento, PessoaCompartilhamento pessoaCompartilhamento, String cpfCnpj, Short codigoTipoPessoa, String nome, BigDecimal porcentagem, BigDecimal areaPosse, BigDecimal areaTotal, String tipoRelacionamento, Boolean marcadoExclusao) {
		super();
		this.idBemPessoaCompartilhamento = idBemPessoaCompartilhamento;
		this.idPessoaCompartilhamento = idPessoaCompartilhamento;
		this.pessoaCompartilhamento = pessoaCompartilhamento;
		this.cpfCnpj = cpfCnpj;
		this.codigoTipoPessoa = codigoTipoPessoa;
		this.nome = nome;
		this.porcentagem = porcentagem;
		this.areaTotal = areaTotal;
		this.areaPosse = areaPosse;
		this.tipoRelacionamento = tipoRelacionamento;
		this.marcadoExclusao = marcadoExclusao;
	}

	public Long getIdBemPessoaCompartilhamento() {
		return idBemPessoaCompartilhamento;
	}

	public void setIdBemPessoaCompartilhamento(Long idBemPessoaCompartilhamento) {
		this.idBemPessoaCompartilhamento = idBemPessoaCompartilhamento;
	}

	public Long getIdPessoaCompartilhamento() {
		return idPessoaCompartilhamento;
	}

	public void setIdPessoaCompartilhamento(Long idPessoaCompartilhamento) {
		this.idPessoaCompartilhamento = idPessoaCompartilhamento;
	}
	
	public PessoaCompartilhamento getPessoaCompartilhamento() {
		return pessoaCompartilhamento;
	}

	public void setPessoaCompartilhamento(PessoaCompartilhamento pessoaCompartilhamento) {
		this.pessoaCompartilhamento = pessoaCompartilhamento;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Short getCodigoTipoPessoa() {
		return codigoTipoPessoa;
	}

	public void setCodigoTipoPessoa(Short codigoTipoPessoa) {
		this.codigoTipoPessoa = codigoTipoPessoa;
	}
	
	public BigDecimal getAreaTotal() {
		return areaTotal;
	}

	public void setAreaTotal(BigDecimal areaTotal) {
		this.areaTotal = areaTotal;
	}

	public BigDecimal getAreaPosse() {
		return areaPosse;
	}

	public void setAreaPosse(BigDecimal areaPosse) {
		this.areaPosse = areaPosse;
	}

	public String getTipoRelacionamento() {
		return tipoRelacionamento;
	}

	public void setTipoRelacionamento(String tipoRelacionamento) {
		this.tipoRelacionamento = tipoRelacionamento;
	}

	public Boolean getMarcadoExclusao() {
		return marcadoExclusao;
	}

	public void setMarcadoExclusao(Boolean marcadoExclusao) {
		this.marcadoExclusao = marcadoExclusao;
	}

	public BigDecimal getPorcentagem() {
		return porcentagem;
	}

	public void setPorcentagem(BigDecimal porcentagem) {
		this.porcentagem = porcentagem;
	}

}