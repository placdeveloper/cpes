package br.com.sicoob.capes.cadastro.fachada;

import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.capes.cadastro.negocio.delegates.OcupacaoProfissionalDelegate;
import br.com.sicoob.capes.negocio.entidades.OcupacaoProfissional;

@RemoteService
public class OcupacaoProfissionalFachada extends CAPESCadastroBOCrudFachada<OcupacaoProfissional> {

	public OcupacaoProfissionalFachada() {
		super("ocupacaoProfissional");
	}

	@Override
	protected OcupacaoProfissional obterEntidade(RequisicaoReqDTO dto) {
		return (OcupacaoProfissional) dto.getDados().get(this.chaveMapa);
	}

	@Override
	protected OcupacaoProfissionalDelegate obterDelegate() {
		return obterFabricaDelegates().criarOcupacaoProfissionalDelegate();
	}
	
	@Override
	protected void preencherFiltroPesquisaComponente(OcupacaoProfissional entidade, RequisicaoReqDTO dto) {
		Integer codigo = (Integer) dto.getDados().get("codigo");
		String descricao = (String) dto.getDados().get("descricao");

		entidade.setId(codigo);
		entidade.setDescricao(descricao);
		entidade.setAtivo(Boolean.TRUE);
		entidade.setCodigoTipoOcupacao((short) 1);
	}

}