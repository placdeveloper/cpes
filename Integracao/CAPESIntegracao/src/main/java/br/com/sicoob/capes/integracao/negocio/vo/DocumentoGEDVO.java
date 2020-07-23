package br.com.sicoob.capes.integracao.negocio.vo;

import java.util.List;

public class DocumentoGEDVO extends CAPESIntegracaoVO {
	private static final long serialVersionUID = 9137939183114751353L;

	private Integer idModulo;
	private Short idSistema;

	private Integer idTipoDocumento;
	private String idUsuarioInclusao;
	private Integer idInstituicao;
	private Integer idUnidadeInstituicao;

	private List<PaginaDocumentoGEDVO> paginas;
	private List<ValorChaveDocumentoGEDVO> listaValorChave;

	public Integer getIdModulo() {
		return idModulo;
	}

	public void setIdModulo(Integer idModulo) {
		this.idModulo = idModulo;
	}

	public Short getIdSistema() {
		return idSistema;
	}

	public void setIdSistema(Short idSistema) {
		this.idSistema = idSistema;
	}

	public Integer getIdTipoDocumento() {
		return idTipoDocumento;
	}

	public void setIdTipoDocumento(Integer idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}

	public String getIdUsuarioInclusao() {
		return idUsuarioInclusao;
	}

	public void setIdUsuarioInclusao(String idUsuarioInclusao) {
		this.idUsuarioInclusao = idUsuarioInclusao;
	}

	public Integer getIdInstituicao() {
		return idInstituicao;
	}

	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	public Integer getIdUnidadeInstituicao() {
		return idUnidadeInstituicao;
	}

	public void setIdUnidadeInstituicao(Integer idUnidadeInstituicao) {
		this.idUnidadeInstituicao = idUnidadeInstituicao;
	}

	public List<PaginaDocumentoGEDVO> getPaginas() {
		return paginas;
	}

	public void setPaginas(List<PaginaDocumentoGEDVO> paginas) {
		this.paginas = paginas;
	}

	public List<ValorChaveDocumentoGEDVO> getListaValorChave() {
		return listaValorChave;
	}

	public void setListaValorChave(List<ValorChaveDocumentoGEDVO> listaValorChave) {
		this.listaValorChave = listaValorChave;
	}

}