/*
 * SICOOB
 * 
 * MensagemReplicacao.java(br.com.sicoob.capes.negocio.entidades.MensagemReplicacao)
 */
package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.comum.util.ReflexaoUtils;

/**
 * The Class MensagemReplicacao.
 */
@Entity
@Table(schema = "CLI", name = "MENSAGEMREPLICACAO")
public class MensagemReplicacao extends CAPESEntidade<Integer> {

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = -5549668048309993498L;
	
	/** A Constante SEPARADOR_MENSAGEM. */
	public static final String SEPARADOR_MENSAGEM = ",";
	
	/** A Constante DELAY. */
	public static final Integer DELAY = -2;

	/** O atributo id mensagem replicacao. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDMENSAGEMREPLICACAO")
	private Integer idMensagemReplicacao;

	/** O atributo identificador operacao. */
	@Column(name = "CODTRANSACAO", length = 36, nullable = false)
	private String identificadorOperacao;

	/** O atributo id instituicao. */
	@Column(name = "IDINSTITUICAO", nullable = false)
	private Integer idInstituicao;

	/** O atributo id pessoa legado. */
	@Column(name = "IDPESSOALEGADO", nullable = true)
	private Integer idPessoaLegado;

	/** O atributo tipo operacao. */
	@Column(name = "DESCTIPOOPERACAO", length = 100)
	private String tipoOperacao;

	/** O atributo conteudo mensagem. */
	@Column(name = "DESCCONTEUDOMENSAGEM", nullable = false)
	private String conteudoMensagem;

	/** O atributo data cadastro. */
	@Column(name = "DATAHORACADASTRO", nullable = false)
	private DateTimeDB dataCadastro;

	/** O atributo data envio. */
	@Column(name = "DATAHORAENVIO", nullable = true)
	private DateTimeDB dataEnvio;

	/** O atributo data processamento. */
	@Column(name = "DATAHORAPROCESSAMENTO", nullable = true)
	private DateTimeDB dataProcessamento;
	
	/** O atributo entidade cadastro. */
	@Column(name = "DESCENTIDADECADASTRO", nullable = false, length = 150)
	private String entidadeCadastro;

	/** O atributo erro. */
	@Column(name = "DESCERRO", length = 8000, nullable = true)
	private String erro;
	
	/** O atributo id registro. */
	@Column(name = "IDREGISTRODB2", nullable = false, updatable = false)
	private Long idRegistro = 0L;// FIXME rodrigo.chaves: remover valor default de idRegistroDB2
	
	/**
	 * Recupera id mensagem replicacao.
	 * 
	 * @return id mensagem replicacao
	 */
	public Integer getIdMensagemReplicacao() {
		return idMensagemReplicacao;
	}

	/**
	 * Preenche id mensagem replicacao.
	 * 
	 * @param idMensagemReplicacao
	 *            o novo id mensagem replicacao
	 */
	public void setIdMensagemReplicacao(Integer idMensagemReplicacao) {
		this.idMensagemReplicacao = idMensagemReplicacao;
	}

	/**
	 * Recupera identificador operacao.
	 * 
	 * @return identificador operacao
	 */
	public String getIdentificadorOperacao() {
		return identificadorOperacao;
	}

	/**
	 * Preenche identificador operacao.
	 * 
	 * @param identificador
	 *            o novo identificador operacao
	 */
	public void setIdentificadorOperacao(String identificador) {
		this.identificadorOperacao = identificador;
	}

	/**
	 * Recupera id instituicao.
	 * 
	 * @return id instituicao
	 */
	public Integer getIdInstituicao() {
		return idInstituicao;
	}

	/**
	 * Preenche id instituicao.
	 * 
	 * @param idInstituicao
	 *            o novo id instituicao
	 */
	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	/**
	 * Recupera id pessoa legado.
	 * 
	 * @return id pessoa legado
	 */
	public Integer getIdPessoaLegado() {
		return idPessoaLegado;
	}

	/**
	 * Preenche id pessoa legado.
	 * 
	 * @param idPessoaLegado
	 *            o novo id pessoa legado
	 */
	public void setIdPessoaLegado(Integer idPessoaLegado) {
		this.idPessoaLegado = idPessoaLegado;
	}

	/**
	 * Recupera tipo operacao.
	 * 
	 * @return tipo operacao
	 */
	public String getTipoOperacao() {
		return tipoOperacao;
	}

	/**
	 * Preenche tipo operacao.
	 * 
	 * @param tipoOperacao
	 *            o novo tipo operacao
	 */
	public void setTipoOperacao(String tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}

	/**
	 * Recupera conteudo mensagem.
	 * 
	 * @return conteudo mensagem
	 */
	public String getConteudoMensagem() {
		return conteudoMensagem;
	}

	/**
	 * Preenche conteudo mensagem.
	 * 
	 * @param conteudoMensagem
	 *            o novo conteudo mensagem
	 */
	public void setConteudoMensagem(String conteudoMensagem) {
		this.conteudoMensagem = conteudoMensagem;
	}

	/**
	 * Recupera data cadastro.
	 * 
	 * @return data cadastro
	 */
	public DateTimeDB getDataCadastro() {
		return dataCadastro;
	}

	/**
	 * Preenche data cadastro.
	 * 
	 * @param dataCadastro
	 *            o novo data cadastro
	 */
	public void setDataCadastro(DateTimeDB dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	/**
	 * Recupera data envio.
	 * 
	 * @return data envio
	 */
	public DateTimeDB getDataEnvio() {
		return dataEnvio;
	}

	/**
	 * Preenche data envio.
	 * 
	 * @param dataEnvio
	 *            o novo data envio
	 */
	public void setDataEnvio(DateTimeDB dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

	/**
	 * Recupera data processamento.
	 * 
	 * @return data processamento
	 */
	public DateTimeDB getDataProcessamento() {
		return dataProcessamento;
	}

	/**
	 * Preenche data processamento.
	 * 
	 * @param dataProcessamento
	 *            o novo data processamento
	 */
	public void setDataProcessamento(DateTimeDB dataProcessamento) {
		this.dataProcessamento = dataProcessamento;
	}

	/**
	 * Recupera entidade cadastro.
	 * 
	 * @return entidade cadastro
	 */
	public String getEntidadeCadastro() {
		return entidadeCadastro;
	}

	/**
	 * Preenche entidade cadastro.
	 * 
	 * @param entidadeCadastro
	 *            o novo entidade cadastro
	 */
	public void setEntidadeCadastro(String entidadeCadastro) {
		this.entidadeCadastro = entidadeCadastro;
	}

	/**
	 * Recupera erro.
	 * 
	 * @return erro
	 */
	public String getErro() {
		return erro;
	}
	
	/**
	 * Preenche erro.
	 * 
	 * @param erro
	 *            o novo erro
	 */
	public void setErro(String erro) {
		this.erro = erro;
	}
	
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Integer getId() {
		return getIdMensagemReplicacao();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Integer id) {
		setIdMensagemReplicacao(id);
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return ReflexaoUtils.hashCode(this, "idMensagemReplicacao", "identificadorOperacao",
				"idInstituicao");
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		return ReflexaoUtils.equals(this, obj, "idMensagemReplicacao", "identificadorOperacao",
				"idInstituicao");
	}
	
	/**
	 * Recupera id registro.
	 * 
	 * @return id registro
	 */
	public Long getIdRegistro() {
		return idRegistro;
	}

	/**
	 * Preenche id registro.
	 * 
	 * @param idRegistro
	 *            o novo id registro
	 */
	public void setIdRegistro(Long idRegistro) {
		this.idRegistro = idRegistro;
	}

}
