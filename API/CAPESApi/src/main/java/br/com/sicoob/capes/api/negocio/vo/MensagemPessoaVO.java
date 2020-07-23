/*
 * SICOOB
 * 
 * MensagemPessoaVO.java(br.com.sicoob.capes.api.negocio.vo.MensagemPessoaVO)
 */
package br.com.sicoob.capes.api.negocio.vo;

import java.util.Date;


/**
 * @author Lucas.Borges
 */
public class MensagemPessoaVO extends AbstractPessoaVO {

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = -7876781000636860625L;

	/** O atributo id mensagem. */
	private Long idMensagem;

	/** O atributo mensagem. */
	private String mensagem;

	/** O atributo data cadastro. */
	private Date dataHoraInicio;

	/** O atributo data validade. */
	private Date dataValidade;

	/** O atributo codigo destino exibicao. */
	private Short codigoDestinoExibicao;
	
	/** O atributo idPessoa. */
	private Integer idPessoa;
	
	/** O atributo idInstituicao. */
	private Integer idInstituicao;
	
	/** O atributo loginUsuarioOperacao. */
	private String loginUsuarioOperacao;
	
	/** O atributo numCpf. */
	private String numCpf;
	
	/** O atributo codigoTipoMensagem. */
	private Short codigoTipoMensagem;

	/**
	 * Cria uma nova instância de mensagem pessoa vo.
	 */
	public MensagemPessoaVO() {

	}
	
	/**
	 * Cria uma nova instância de mensagem pessoa vo.
	 * 
	 * @param idMensagem
	 *            the id mensagem
	 * @param mensagem
	 *            the mensagem
	 * @param dataCadastro
	 *            the data cadastro
	 * @param dataValidade
	 *            the data validade
	 * @param codigoDestinoExibicao
	 *            the codigo destino exibicao
	 */
	public MensagemPessoaVO(Long idMensagem, String mensagem, Date dataHoraInicio, Date dataValidade, Short codigoDestinoExibicao, Short codigoTipoMensagem) {
		this.idMensagem = idMensagem;
		this.mensagem = mensagem;
		this.dataHoraInicio = dataHoraInicio;
		this.dataValidade = dataValidade;
		this.codigoDestinoExibicao = codigoDestinoExibicao;
		this.codigoTipoMensagem = codigoTipoMensagem;
		
	}
	
	public MensagemPessoaVO(Long idMensagem, String mensagem, Date dataHoraInicio, Date dataValidade, Short codigoDestinoExibicao, Short codigoTipoMensagem, Integer idPessoa, String loginUsuarioOperacao) {
		this(idMensagem, mensagem, dataHoraInicio, dataValidade, codigoDestinoExibicao, codigoTipoMensagem);
		this.idPessoa = idPessoa;
		this.loginUsuarioOperacao = loginUsuarioOperacao;
	}
	
	/**
	 * Recupera id mensagem.
	 * 
	 * @return id mensagem
	 */
	public Long getIdMensagem() {
		return idMensagem;
	}

	/**
	 * Preenche id mensagem.
	 * 
	 * @param idMensagem
	 *            o novo id mensagem
	 */
	public void setIdMensagem(Long idMensagem) {
		this.idMensagem = idMensagem;
	}

	/**
	 * Recupera mensagem.
	 * 
	 * @return mensagem
	 */
	public String getMensagem() {
		return mensagem;
	}

	/**
	 * Preenche mensagem.
	 * 
	 * @param mensagem
	 *            o novo mensagem
	 */
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	/**
	 * Recupera data cadastro.
	 * 
	 * @return data cadastro
	 */
	public Date getDataCadastro() {
		return dataHoraInicio;
	}

	/**
	 * Preenche data cadastro.
	 * 
	 * @param dataCadastro
	 *            o novo data cadastro
	 */
	public void setDataCadastro(Date dataHoraInicio) {
		this.dataHoraInicio = dataHoraInicio;
	}

	/**
	 * Recupera data validade.
	 * 
	 * @return data validade
	 */
	public Date getDataValidade() {
		return dataValidade;
	}

	/**
	 * Preenche data validade.
	 * 
	 * @param dataValidade
	 *            o novo data validade
	 */
	public void setDataValidade(Date dataValidade) {
		this.dataValidade = dataValidade;
	}

	/**
	 * Recupera codigo destino exibicao.
	 * 
	 * @return codigo destino exibicao
	 */
	public Short getCodigoDestinoExibicao() {
		return codigoDestinoExibicao;
	}

	/**
	 * Preenche codigo destino exibicao.
	 * 
	 * @param codigoDestinoExibicao
	 *            o novo codigo destino exibicao
	 */
	public void setCodigoDestinoExibicao(Short tipoDestinoExibicao) {
		this.codigoDestinoExibicao = tipoDestinoExibicao;
	}

	/**
	 * @return the idPessoa
	 */
	public Integer getIdPessoa() {
		return idPessoa;
	}

	/**
	 * @param idPessoa the idPessoa to set
	 */
	public void setIdPessoa(Integer idPessoa) {
		this.idPessoa = idPessoa;
	}

	/**
	 * @return the idInstituicao
	 */
	public Integer getIdInstituicao() {
		return idInstituicao;
	}

	/**
	 * @param idInstituicao the idInstituicao to set
	 */
	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	/**
	 * @return the loginUsuarioOperacao
	 */
	public String getLoginUsuarioOperacao() {
		return loginUsuarioOperacao;
	}

	/**
	 * @param loginUsuarioOperacao the loginUsuarioOperacao to set
	 */
	public void setLoginUsuarioOperacao(String loginUsuarioOperacao) {
		this.loginUsuarioOperacao = loginUsuarioOperacao;
	}

	/**
	 * @return the numCpf
	 */
	public String getNumCpf() {
		return numCpf;
	}

	/**
	 * @param numCpf the numCpf to set
	 */
	public void setNumCpf(String numCpf) {
		this.numCpf = numCpf;
	}

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
	 * @param tipoMensagem o novo valor de codigoTipoMensagem
	 */
	public void setCodigoTipoMensagem(Short tipoMensagem) {
		this.codigoTipoMensagem = tipoMensagem;
	}
}