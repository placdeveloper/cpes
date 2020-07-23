/*
 * SICOOB
 * 
 * GEDIntegracaoDTO.java(br.com.sicoob.capes.comum.negocio.dto.GEDIntegracaoDTO)
 */
package br.com.sicoob.capes.comum.negocio.dto;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

/**
 * The Class GEDIntegracaoDTO.
 */
public class GEDIntegracaoDTO {

	/** O atributo id pessoa. */
	private Integer idPessoa;
	
	/** O atributo cpf cnpj. */
	private String cpfCnpj;
	
	/** O atributo cod tipo pessoa. */
	private Short codTipoPessoa;
	
	/** O atributo cod compartilhamento cadastro. */
	private Short codCompartilhamentoCadastro;
	
	/** O atributo id registro controlado. */
	private String idRegistroControlado;
	
	/** O atributo lista id documento. */
	private List<Long> listaIdDocumento;
	
	/** O atributo id instituicao destino. */
	private Integer idInstituicaoDestino;
	
	/** O atributo id unidade inst destino. */
	private Integer idUnidadeInstDestino;

	/**
	 * Cria uma nova instância de GED integracao dto.
	 */
	public GEDIntegracaoDTO() {
	}

	/**
	 * Recupera id pessoa.
	 * 
	 * @return id pessoa
	 */
	public Integer getIdPessoa() {

		return this.idPessoa;
	}

	/**
	 * Preenche id pessoa.
	 * 
	 * @param idPessoa
	 *            o novo id pessoa
	 */
	public void setIdPessoa(Integer idPessoa) {

		this.idPessoa = idPessoa;
	}

	/**
	 * Recupera cpf cnpj.
	 * 
	 * @return cpf cnpj
	 */
	public String getCpfCnpj() {

		return this.cpfCnpj;
	}

	/**
	 * Preenche cpf cnpj.
	 * 
	 * @param cpfCnpj
	 *            o novo cpf cnpj
	 */
	public void setCpfCnpj(String cpfCnpj) {

		this.cpfCnpj = cpfCnpj;
	}

	/**
	 * Recupera cod tipo pessoa.
	 * 
	 * @return cod tipo pessoa
	 */
	public Short getCodTipoPessoa() {

		return this.codTipoPessoa;
	}

	/**
	 * Preenche cod tipo pessoa.
	 * 
	 * @param codTipoPessoa
	 *            o novo cod tipo pessoa
	 */
	public void setCodTipoPessoa(Short codTipoPessoa) {

		this.codTipoPessoa = codTipoPessoa;
	}

	/**
	 * Recupera cod compartilhamento cadastro.
	 * 
	 * @return cod compartilhamento cadastro
	 */
	public Short getCodCompartilhamentoCadastro() {

		return this.codCompartilhamentoCadastro;
	}

	/**
	 * Preenche cod compartilhamento cadastro.
	 * 
	 * @param codCompartilhamentoCadastro
	 *            o novo cod compartilhamento cadastro
	 */
	public void setCodCompartilhamentoCadastro(Short codCompartilhamentoCadastro) {

		this.codCompartilhamentoCadastro = codCompartilhamentoCadastro;
	}

	/**
	 * Recupera id registro controlado.
	 * 
	 * @return id registro controlado
	 */
	public String getIdRegistroControlado() {

		return this.idRegistroControlado;
	}

	/**
	 * Preenche id registro controlado.
	 * 
	 * @param idRegistroControlado
	 *            o novo id registro controlado
	 */
	public void setIdRegistroControlado(String idRegistroControlado) {

		this.idRegistroControlado = idRegistroControlado;
	}

	/**
	 * Recupera lista id documento.
	 * 
	 * @return lista id documento
	 */
	public List<Long> getListaIdDocumento() {

		return this.listaIdDocumento;
	}

	/**
	 * Preenche lista id documento.
	 * 
	 * @param listaIdDocumento
	 *            o novo lista id documento
	 */
	public void setListaIdDocumento(List<Long> listaIdDocumento) {

		this.listaIdDocumento = listaIdDocumento;
	}

	/**
	 * Adds the id documento.
	 * 
	 * @param idDocumento
	 *            the id documento
	 */
	public void addIdDocumento(Long idDocumento) {
		if (this.listaIdDocumento == null) {
			this.listaIdDocumento = new ArrayList<Long>();
		}
		CollectionUtils.addIgnoreNull(this.listaIdDocumento, idDocumento);
	}
	
	/**
	 * Recupera id instituicao destino.
	 * 
	 * @return id instituicao destino
	 */
	public Integer getIdInstituicaoDestino() {

		return this.idInstituicaoDestino;
	}


	/**
	 * Preenche id instituicao destino.
	 * 
	 * @param idInstituicaoDestino
	 *            o novo id instituicao destino
	 */
	public void setIdInstituicaoDestino(Integer idInstituicaoDestino) {

		this.idInstituicaoDestino = idInstituicaoDestino;
	}


	/**
	 * Recupera id unidade inst destino.
	 * 
	 * @return id unidade inst destino
	 */
	public Integer getIdUnidadeInstDestino() {

		return this.idUnidadeInstDestino;
	}


	/**
	 * Preenche id unidade inst destino.
	 * 
	 * @param idUnidadeInstDestino
	 *            o novo id unidade inst destino
	 */
	public void setIdUnidadeInstDestino(Integer idUnidadeInstDestino) {

		this.idUnidadeInstDestino = idUnidadeInstDestino;
	}
}
