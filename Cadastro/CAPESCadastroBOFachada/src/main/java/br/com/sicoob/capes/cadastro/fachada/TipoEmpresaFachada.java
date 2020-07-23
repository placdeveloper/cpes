package br.com.sicoob.capes.cadastro.fachada;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroCrudDominioDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.TipoEmpresaDelegate;
import br.com.sicoob.capes.negocio.entidades.TipoEmpresa;

/**
 * A Classe TipoEmpresaFachada.
 */
@RemoteService
public class TipoEmpresaFachada extends CAPESCadastroBOCrudDominioFachada<TipoEmpresa> {

	/** O atributo tipoEmpresaDelegate. */
	private final TipoEmpresaDelegate tipoEmpresaDelegate = CAPESCadastroFabricaDelegates.getInstance().criarTipoEmpresaDelegate();

	/**
	 * Instancia um novo TipoEmpresaFachada.
	 */
	public TipoEmpresaFachada() {
		super("TipoEmpresa");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TipoEmpresa obterEntidade(RequisicaoReqDTO dto) {
		return (TipoEmpresa) dto.getDados().get(this.chaveMapa);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected CAPESCadastroCrudDominioDelegate<TipoEmpresa, ?> obterDelegate() {
		return this.tipoEmpresaDelegate;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		retorno.getDados().put("codigo", this.obterDelegate().pesquisarProximoCodigo());
		return retorno;
	}
}