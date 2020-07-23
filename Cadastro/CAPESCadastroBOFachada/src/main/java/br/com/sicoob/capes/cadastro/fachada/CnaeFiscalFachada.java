package br.com.sicoob.capes.cadastro.fachada;

import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.capes.cadastro.negocio.delegates.CnaeFiscalDelegate;
import br.com.sicoob.capes.negocio.entidades.CnaeFiscal;

@RemoteService
public class CnaeFiscalFachada extends CAPESCadastroBOCrudFachada<CnaeFiscal> {

	public CnaeFiscalFachada() {
		super("cnaeFiscal");
	}

	@Override
	protected CnaeFiscal obterEntidade(RequisicaoReqDTO dto) {
		return (CnaeFiscal) dto.getDados().get(this.chaveMapa);
	}

	@Override
	protected CnaeFiscalDelegate obterDelegate() {
		return obterFabricaDelegates().criarCnaeFiscalDelegate();
	}

	@Override
	protected void preencherFiltroPesquisaComponente(CnaeFiscal entidade, RequisicaoReqDTO dto) {
		String codigo = (String) dto.getDados().get("codigo");
		String descricao = (String) dto.getDados().get("descricao");

		entidade.setCodigo(codigo);
		entidade.setDescricao(descricao);
	}

}