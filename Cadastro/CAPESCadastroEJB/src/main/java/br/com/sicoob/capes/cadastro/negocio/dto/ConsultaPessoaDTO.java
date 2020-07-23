/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.dto;

import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * @author Erico.Junior
 *
 */
public class ConsultaPessoaDTO extends ConsultaDto<PessoaCompartilhamento> {

	/** Serial UID. */
	private static final long serialVersionUID = 6852636894625000090L;

	/** O atributo codigoCompartilhamento. */
	private Short codigoCompartilhamento;
	
	/** O atributo idPessoaCompartilhamentoInicio. */
	private Long idPessoaCompartilhamentoInicio;
	
	/** O atributo idPessoaCompartilhamentoFim. */
	private Long idPessoaCompartilhamentoFim;

	/**
	 * @return the codigoCompartilhamento
	 */
	public Short getCodigoCompartilhamento() {
		return codigoCompartilhamento;
	}

	/**
	 * @param codigoCompartilhamento the codigoCompartilhamento to set
	 */
	public void setCodigoCompartilhamento(Short codigoCompartilhamento) {
		this.codigoCompartilhamento = codigoCompartilhamento;
	}

	/**
	 * @return the idPessoaCompartilhamentoInicio
	 */
	public Long getIdPessoaCompartilhamentoInicio() {
		return idPessoaCompartilhamentoInicio;
	}

	/**
	 * @param idPessoaCompartilhamentoInicio the idPessoaCompartilhamentoInicio to set
	 */
	public void setIdPessoaCompartilhamentoInicio(
			Long idPessoaCompartilhamentoInicio) {
		this.idPessoaCompartilhamentoInicio = idPessoaCompartilhamentoInicio;
	}

	/**
	 * @return the idPessoaCompartilhamentoFim
	 */
	public Long getIdPessoaCompartilhamentoFim() {
		return idPessoaCompartilhamentoFim;
	}

	/**
	 * @param idPessoaCompartilhamentoFim the idPessoaCompartilhamentoFim to set
	 */
	public void setIdPessoaCompartilhamentoFim(Long idPessoaCompartilhamentoFim) {
		this.idPessoaCompartilhamentoFim = idPessoaCompartilhamentoFim;
	}
}
