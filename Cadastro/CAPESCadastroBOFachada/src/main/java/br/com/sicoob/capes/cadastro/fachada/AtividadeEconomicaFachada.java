package br.com.sicoob.capes.cadastro.fachada;

import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.capes.cadastro.negocio.delegates.AtividadeEconomicaDelegate;
import br.com.sicoob.capes.negocio.entidades.AtividadeEconomica;

@RemoteService
public class AtividadeEconomicaFachada extends CAPESCadastroBOCrudFachada<AtividadeEconomica> {

	public AtividadeEconomicaFachada() {
		super("atividadeEconomica");
	}

	@Override
	protected AtividadeEconomica obterEntidade(RequisicaoReqDTO dto) {
		return (AtividadeEconomica) dto.getDados().get(this.chaveMapa);
	}

	@Override
	protected AtividadeEconomicaDelegate obterDelegate() {
		return obterFabricaDelegates().criarAtividadeEconomicaDelegate();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void preencherFiltroPesquisaComponente(AtividadeEconomica entidade, RequisicaoReqDTO dto) {
		Integer codigo = (Integer) dto.getDados().get("codigo");
		String descricao = (String) dto.getDados().get("descricao");

		entidade.setCodigo(codigo != null ? codigo.shortValue() : null);
		entidade.setDescricao(descricao);
	}

}