package br.com.sicoob.capes.relatorio.negocio.vo;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import br.com.sicoob.capes.comum.util.ReflexaoUtils;
import br.com.sicoob.capes.negocio.entidades.bem.Bem;
import br.com.sicoob.capes.negocio.entidades.bem.BemMovel;
import br.com.sicoob.capes.negocio.entidades.bem.BemMovelAvaliacao;
import br.com.sicoob.capes.negocio.entidades.bem.TipoOnusBem;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * A Classe FichaCadastralBemVO.
 */
public class FichaCadastralBemMovelNovoVO {

	private BemMovel bemMovel;

	private BemMovelAvaliacao bemMovelAvaliacao;

	private PessoaCompartilhamento pessoaAvaliador;

	private String tipoOnus = StringUtils.EMPTY;

	public BemMovel getBemMovel() {
		return bemMovel;
	}
	
	public BemMovel getBemMovel(Bem bem) {
		if (this.bemMovel != null) {
			this.bemMovel = new BemMovel();
			ReflexaoUtils.copiarPropriedades(this.bemMovel, bem);
		}
		return bemMovel;
	}

	public void setBemMovel(BemMovel bemMovel) {
		if (this.bemMovel != null) {
			this.bemMovel = new BemMovel();
		}
		this.bemMovel = bemMovel;
	}

	public void setBemMovel(BemMovelAvaliacao bemMovelAvaliacao) {
		if (this.bemMovel == null) {
			this.bemMovel = new BemMovel();
		}

		ReflexaoUtils.copiarPropriedades(this.bemMovel, bemMovelAvaliacao, "valorCompraVenda", "dataCompraVenda", "valorAvaliacao", "dataAvaliacao",
				"processoAquisicao", "idPessoaCompartilhamentoAvaliador", "tipoEstadoConservacao", "tiposOnus");
	}

	public BemMovelAvaliacao getBemMovelAvaliacao() {
		return bemMovelAvaliacao;
	}

	public void setBemMovelAvaliacao(BemMovelAvaliacao bemMovelAvaliacao) {
		if (this.bemMovelAvaliacao != null) {
			this.bemMovelAvaliacao = new BemMovelAvaliacao();
		}
		this.bemMovelAvaliacao = bemMovelAvaliacao;
	}

	public PessoaCompartilhamento getPessoaAvaliador() {
		return pessoaAvaliador;
	}

	public void setPessoaAvaliador(PessoaCompartilhamento pessoaAvaliador) {
		this.pessoaAvaliador = pessoaAvaliador;
	}

	public String getTipoOnus() {
		return tipoOnus;
	}

	public void setTipoOnus(String tipoOnus) {
		this.tipoOnus = tipoOnus;
	}

	public void setTipoOnus(List<TipoOnusBem> tiposOnus) {
		for (TipoOnusBem tipoOnusBem : tiposOnus) {
			this.tipoOnus += tipoOnusBem.getDescricao()+", ";
		}
		this.tipoOnus = StringUtils.removeEnd(this.tipoOnus,", ");
	}
	
}