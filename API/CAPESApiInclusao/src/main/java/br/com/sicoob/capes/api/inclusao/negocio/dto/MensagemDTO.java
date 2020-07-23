package br.com.sicoob.capes.api.inclusao.negocio.dto;

import java.util.Date;

/**
 * Classe com as informações relacionadas à mensagem da pessoa.
 * 
 * @author bruno.carneiro
 */
public class MensagemDTO extends RegistroInclusaoDTO<Long> {
	private static final long serialVersionUID = 3065590470980148243L;
	
	private Long idMensagem;
	private Short codigoTipoMensagem;
	private Short codigoTipoDestinoExibicao;
	private String mensagem;
	private Date dataValidade;

	public Long getIdMensagem() {
		return idMensagem;
	}

	public void setIdMensagem(Long idMensagem) {
		this.idMensagem = idMensagem;
	}

	public Short getCodigoTipoMensagem() {
		return codigoTipoMensagem;
	}

	public void setCodigoTipoMensagem(Short codigoTipoMensagem) {
		this.codigoTipoMensagem = codigoTipoMensagem;
	}

	public Short getCodigoTipoDestinoExibicao() {
		return codigoTipoDestinoExibicao;
	}

	public void setCodigoTipoDestinoExibicao(Short codigoTipoDestinoExibicao) {
		this.codigoTipoDestinoExibicao = codigoTipoDestinoExibicao;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Date getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(Date dataValidade) {
		this.dataValidade = dataValidade;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long getId() {
		return getIdMensagem();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Long id) {
		setIdMensagem(id);
	}

}