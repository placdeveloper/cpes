/* 
 * Sicoob
 * ConsultaAutorizacaoDTO.java 
 * Criado em: 03/02/2011
 */
package br.com.sicoob.capes.cadastro.negocio.dto;

import java.util.Set;

import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.negocio.enums.TipoAutorizacaoEntidadeEnum;
import br.com.sicoob.capes.negocio.entidades.Autorizacao;

/**
 * 03/02/2011
 * 
 * @author rodrigo.chaves
 */
public class ConsultaAutorizacaoDTO extends ConsultaDto<Autorizacao> {

	/** Serial UID */
	private static final long serialVersionUID = 5063141031113087454L;

	/** O atributo idsRegistroControlado. */
	private Set<String> idsRegistroControlado;
	
	/** O atributo idInstituicaoUsuario. */
	private Integer idInstituicaoUsuario;
	
	/** O atributo idPessoaSelecionada. */
	private Integer idPessoaSelecionada;
		
	/** O atributo idRegistro. */
	private Long idRegistro;
	
	private String idUsuarioEnvio;
	
	/** O atributo tipoAutorizacao. */
	private TipoAutorizacaoEntidadeEnum tipoAutorizacao;

	/**
	 * Recupera o valor de idsRegistroControlado.
	 *
	 * @return o valor de idsRegistroControlado
	 */
	public Set<String> getIdsRegistroControlado() {
		return idsRegistroControlado;
	}

	/**
	 * Define o valor de idsRegistroControlado.
	 *
	 * @param idsRegistroControlado o novo valor de idsRegistroControlado
	 */
	public void setIdsRegistroControlado(Set<String> idsRegistroControlado) {
		this.idsRegistroControlado = idsRegistroControlado;
	}

	/**
	 * Recupera o valor de idInstituicaoUsuario.
	 *
	 * @return o valor de idInstituicaoUsuario
	 */
	public Integer getIdInstituicaoUsuario() {
		return idInstituicaoUsuario;
	}

	/**
	 * Define o valor de idInstituicaoUsuario.
	 *
	 * @param idInstituicaoUsuario o novo valor de idInstituicaoUsuario
	 */
	public void setIdInstituicaoUsuario(Integer idInstituicaoUsuario) {
		this.idInstituicaoUsuario = idInstituicaoUsuario;
	}

	/**
	 * Recupera o valor de idPessoaSelecionada.
	 *
	 * @return o valor de idPessoaSelecionada
	 */
	public Integer getIdPessoaSelecionada() {
		return idPessoaSelecionada;
	}

	/**
	 * Define o valor de idPessoaSelecionada.
	 *
	 * @param idPessoaSelecionada o novo valor de idPessoaSelecionada
	 */
	public void setIdPessoaSelecionada(Integer idPessoaSelecionada) {
		this.idPessoaSelecionada = idPessoaSelecionada;
	}

	/**
	 * Recupera o valor de idRegistro.
	 *
	 * @return o valor de idRegistro
	 */
	public Long getIdRegistro() {
		return idRegistro;
	}

	/**
	 * Define o valor de idRegistro.
	 *
	 * @param idRegistro o novo valor de idRegistro
	 */
	public void setIdRegistro(Long idRegistro) {
		this.idRegistro = idRegistro;
	}

	/**
	 * Recupera o valor de tipoAutorizacao.
	 *
	 * @return o valor de tipoAutorizacao
	 */
	public TipoAutorizacaoEntidadeEnum getTipoAutorizacao() {
		return tipoAutorizacao;
	}

	/**
	 * Define o valor de tipoAutorizacao.
	 *
	 * @param tipoAutorizacao o novo valor de tipoAutorizacao
	 */
	public void setTipoAutorizacao(TipoAutorizacaoEntidadeEnum tipoAutorizacao) {
		this.tipoAutorizacao = tipoAutorizacao;
	}

	public String getIdUsuarioEnvio() {
		return idUsuarioEnvio;
	}

	public void setIdUsuarioEnvio(String idUsuarioEnvio) {
		this.idUsuarioEnvio = idUsuarioEnvio;
	}

}