package br.com.sicoob.capes.negocio.entidades.pk;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import br.com.bancoob.negocio.entidades.BancoobChavePrimaria;
import br.com.sicoob.capes.comum.util.ReflexaoUtils;

/**
 * 
 * @author Paulo.Stoppa
 *
 */
@Embeddable
public class ParametroInstituicaoPK extends BancoobChavePrimaria {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4283635937605843292L;

	/**
	 * Código do parâmetro
	 */
	@Column(name = "CODPARAMETRO")
	private Long codigo;

	/**
	 * id da instituição
	 */
	@Column(name = "IDINSTITUICAO")
	private Short idInstituicao;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Short getIdInstituicao() {
		return idInstituicao;
	}

	public void setIdInstituicao(Short idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return ReflexaoUtils.hashCode(this, "codigo", "idInstituicao");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		return ReflexaoUtils.equals(this, obj, "codigo", "idInstituicao");
	}

}