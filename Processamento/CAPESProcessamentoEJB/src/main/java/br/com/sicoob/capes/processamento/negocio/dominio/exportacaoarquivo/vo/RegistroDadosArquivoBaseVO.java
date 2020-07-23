package br.com.sicoob.capes.processamento.negocio.dominio.exportacaoarquivo.vo;

import org.apache.commons.lang.builder.ToStringBuilder;

import br.com.sicoob.capes.comum.arquivos.negocio.annotation.CampoArquivo;
import br.com.sicoob.capes.comum.arquivos.negocio.annotation.CampoArquivo.AlinhamentoCampo;

/**
 * A Classe RegistroDadosArquivoBaseVO.
 */
public abstract class RegistroDadosArquivoBaseVO extends RegistroArquivoBaseVO {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -6054687820421547006L;

	/** O atributo idPessoaCompartilhamento. */
	@CampoArquivo(inicio = 2, tamanho = 19, alinhamento = AlinhamentoCampo.DIREITA, complemento = '0')
	private Long idPessoaCompartilhamento;

	/**
	 * Construtor
	 */
	protected RegistroDadosArquivoBaseVO(String codigoRegistro) {
		super(codigoRegistro);
	}

	/**
	 * Construtor
	 * 
	 * @param codigoRegistro
	 * @param idPessoaCompartilhamento
	 */
	protected RegistroDadosArquivoBaseVO(String codigoRegistro, Long idPessoaCompartilhamento) {
		super(codigoRegistro);
		this.idPessoaCompartilhamento = idPessoaCompartilhamento;
	}

	/**
	 * @return the idPessoaCompartilhamento
	 */
	public Long getIdPessoaCompartilhamento() {
		return idPessoaCompartilhamento;
	}

	/**
	 * @param idPessoaCompartilhamento
	 *            the idPessoaCompartilhamento to set
	 */
	public void setIdPessoaCompartilhamento(Long idPessoaCompartilhamento) {
		this.idPessoaCompartilhamento = idPessoaCompartilhamento;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}