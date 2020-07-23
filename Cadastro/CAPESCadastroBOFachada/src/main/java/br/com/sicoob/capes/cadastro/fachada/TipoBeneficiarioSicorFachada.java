package br.com.sicoob.capes.cadastro.fachada;

import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.capes.cadastro.negocio.delegates.TipoBeneficiarioSicorDelegate;
import br.com.sicoob.capes.negocio.entidades.TipoBeneficiarioSicor;

@RemoteService
public class TipoBeneficiarioSicorFachada extends CAPESCadastroBOCrudFachada<TipoBeneficiarioSicor> {

	public TipoBeneficiarioSicorFachada() {
		super("tipoBeneficiarioSicor");
	}

	@Override
	protected TipoBeneficiarioSicor obterEntidade(RequisicaoReqDTO dto) {
		return (TipoBeneficiarioSicor) dto.getDados().get(this.chaveMapa);
	}

	@Override
	protected TipoBeneficiarioSicorDelegate obterDelegate() {
		return obterFabricaDelegates().criarTipoBeneficiarioSicorDelegate();
	}

	@Override
	protected void preencherFiltroPesquisaComponente(TipoBeneficiarioSicor entidade, RequisicaoReqDTO dto) {
		Integer codigo = (Integer) dto.getDados().get("codigo");
		String descricao = (String) dto.getDados().get("descricao");

		entidade.setCodigo(codigo != null ? codigo.shortValue() : null);
		entidade.setDescricao(descricao);
	}

}