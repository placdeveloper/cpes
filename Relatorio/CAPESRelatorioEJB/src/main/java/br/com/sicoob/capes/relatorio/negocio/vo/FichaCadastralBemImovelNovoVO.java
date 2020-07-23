package br.com.sicoob.capes.relatorio.negocio.vo;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import br.com.sicoob.capes.comum.negocio.vo.LOCIntegracaoLocalidadeVO;
import br.com.sicoob.capes.comum.negocio.vo.LocalidadeVO;
import br.com.sicoob.capes.comum.util.ReflexaoUtils;
import br.com.sicoob.capes.negocio.entidades.bem.Bem;
import br.com.sicoob.capes.negocio.entidades.bem.BemImovel;
import br.com.sicoob.capes.negocio.entidades.bem.BemImovelAvaliacaoRural;
import br.com.sicoob.capes.negocio.entidades.bem.BemImovelAvaliacaoUrbano;
import br.com.sicoob.capes.negocio.entidades.bem.BemImovelTipoRelacionamentoPessoa;
import br.com.sicoob.capes.negocio.entidades.bem.TipoOnusBem;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * A Classe FichaCadastralBemVO.
 */
public class FichaCadastralBemImovelNovoVO {

	private BemImovel bemImovel;

	private BemImovelAvaliacaoRural avaliacaoRural;

	private BemImovelAvaliacaoUrbano avaliacaoUrbano;

	private PessoaCompartilhamento pessoaAvaliador;

	private LocalidadeVO localidade;

	private List<BemImovelTipoRelacionamentoPessoa> relacionamentoPessoas;

	private String tipoOnus = StringUtils.EMPTY;

	public BemImovel getBemImovel() {
		return bemImovel;
	}

	public BemImovel getBemImovel(Bem bem) {
		if (bemImovel == null) {
			bemImovel = new BemImovel();
			ReflexaoUtils.copiarPropriedades(bemImovel, bem);
		}
		return bemImovel;
	}

	public void setBemImovel(BemImovel bemImovel) {
		this.bemImovel = bemImovel;
	}

	public BemImovelAvaliacaoRural getAvaliacaoRural() {
		return avaliacaoRural;
	}

	public void setAvaliacaoRural(BemImovelAvaliacaoRural avaliacaoRural) {
		this.avaliacaoRural = avaliacaoRural;
	}

	public BemImovelAvaliacaoUrbano getAvaliacaoUrbano() {
		return avaliacaoUrbano;
	}

	public void setAvaliacaoUrbano(BemImovelAvaliacaoUrbano avaliacaoUrbano) {
		this.avaliacaoUrbano = avaliacaoUrbano;
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

	public LocalidadeVO getLocalidade() {
		return localidade;
	}

	public void setLocalidade(LocalidadeVO localidade) {
		this.localidade = localidade;
	}

	public void setLocalidade(LOCIntegracaoLocalidadeVO vo) {
		this.localidade = new LocalidadeVO();
		this.localidade.setNomeLocalidade(vo.getNome());
		this.localidade.setSiglaUF(vo.getUf().getSiglaUF());
	}
	
	public List<BemImovelTipoRelacionamentoPessoa> getRelacionamentoPessoas() {
		if (relacionamentoPessoas == null) {
			relacionamentoPessoas = new ArrayList<BemImovelTipoRelacionamentoPessoa>();
		}
		return relacionamentoPessoas;
	}

	public void setRelacionamentoPessoas(List<BemImovelTipoRelacionamentoPessoa> relacionamentoPessoas) {
		this.relacionamentoPessoas = relacionamentoPessoas;
	}

}