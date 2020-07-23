package br.com.sicoob.capes.cadastro.negocio.vo;

import java.util.Set;

import br.com.sicoob.capes.comum.negocio.enums.TipoPessoaEnum;
import br.com.sicoob.capes.comum.util.Constantes.Negocio;

/**
 * A Classe DefinicoesComponenteGedVO.
 */
@SuppressWarnings("unused")
public class DefinicoesComponenteGedVO {
	
	/** O atributo idTipoPessoa. */
	private Short idTipoPessoa;
	
	/** O atributo siglaTipoDocumento. */
	private String siglaTipoDocumento;
	
	/** O atributo obterCodigoTipoPessoaSelecionada. */
	private String obterCodigoTipoPessoaSelecionada;
	
	/** O atributo obterSiglaAssuntoPessoaSelecionada. */
	private String obterSiglaAssuntoPessoaSelecionada;
	
	/** O atributo ChavesNegocio. */
	private Set<String> ChavesNegocio;

	/**
	 * Recupera o valor de idTipoPessoa.
	 *
	 * @return o valor de idTipoPessoa
	 */
	public Short getIdTipoPessoa() {
		return idTipoPessoa;
	}

	/**
	 * Define o valor de idTipoPessoa.
	 *
	 * @param idTipoPessoa o novo valor de idTipoPessoa
	 */
	public void setIdTipoPessoa(Short idTipoPessoa) {
		this.idTipoPessoa = idTipoPessoa;
	}

	/**
	 * Recupera o valor de siglaTipoDocumento.
	 *
	 * @return o valor de siglaTipoDocumento
	 */
	public String getSiglaTipoDocumento() {
		return siglaTipoDocumento;
	}

	/**
	 * Define o valor de siglaTipoDocumento.
	 *
	 * @param siglaTipoDocumento o novo valor de siglaTipoDocumento
	 */
	public void setSiglaTipoDocumento(String siglaTipoDocumento) {
		this.siglaTipoDocumento = siglaTipoDocumento;
	}

	/**
	 * Recupera o valor de chavesNegocio.
	 *
	 * @return o valor de chavesNegocio
	 */
	public Set<String> getChavesNegocio() {
		return ChavesNegocio;
	}

	/**
	 * Define o valor de chavesNegocio.
	 *
	 * @param ChavesNegocio o novo valor de chavesNegocio
	 */
	public void setChavesNegocio(Set<String> ChavesNegocio) {
		this.ChavesNegocio = ChavesNegocio;
	}
	
	/**
	 * Define o valor de obterCodigoTipoPessoaSelecionada.
	 *
	 * @param obterCodigoTipoPessoaSelecionada o novo valor de obterCodigoTipoPessoaSelecionada
	 */
	public void setObterCodigoTipoPessoaSelecionada(String obterCodigoTipoPessoaSelecionada) {
		this.obterCodigoTipoPessoaSelecionada = obterCodigoTipoPessoaSelecionada;
	}

	/**
	 * Define o valor de obterSiglaAssuntoPessoaSelecionada.
	 *
	 * @param obterSiglaAssuntoPessoaSelecionada o novo valor de obterSiglaAssuntoPessoaSelecionada
	 */
	public void setObterSiglaAssuntoPessoaSelecionada(String obterSiglaAssuntoPessoaSelecionada) {
		this.obterSiglaAssuntoPessoaSelecionada = obterSiglaAssuntoPessoaSelecionada;
	}

	/**
	 * Método para retornar o código do tipo de pessoa (PF/PJ), de acordo com o
	 * esperado pelo conmponente GED para montar as chaves de negócio.
	 */
	public String getObterCodigoTipoPessoaSelecionada() {
		if (TipoPessoaEnum.isPessoaFisica(getIdTipoPessoa())) {
			return Negocio.GED_SIGLA_CHAVE_DOCUMENTO_PF;
		} else if (TipoPessoaEnum.isPessoaJuridica(getIdTipoPessoa())) {
			return Negocio.GED_SIGLA_CHAVE_DOCUMENTO_PJ;
		}
		return "";
	}

	/**
	 * Método para retornar a sigla do assunto, dependendo do tipo de pessoa
	 * selecionada (PF/PJ).
	 */
	public String getObterSiglaAssuntoPessoaSelecionada() {
		return TipoPessoaEnum.getSiglaAssuntoGED(getIdTipoPessoa());
	}

}