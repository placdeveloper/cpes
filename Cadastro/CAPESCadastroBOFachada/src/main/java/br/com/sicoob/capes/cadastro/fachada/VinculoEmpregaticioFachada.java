package br.com.sicoob.capes.cadastro.fachada;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroCrudDominioDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.VinculoEmpregaticioDelegate;
import br.com.sicoob.capes.negocio.entidades.VinculoEmpregaticio;

/**
 * A Classe VinculoEmpregaticioFachada.
 */
@RemoteService
public class VinculoEmpregaticioFachada extends CAPESCadastroBOCrudDominioFachada<VinculoEmpregaticio> {

	/** O atributo vinculoEmpregaticioDelegate. */
	private final VinculoEmpregaticioDelegate vinculoEmpregaticioDelegate = CAPESCadastroFabricaDelegates.getInstance().criarVinculoEmpregaticioDelegate();

	/**
	 * Instancia um novo VinculoEmpregaticioFachada.
	 */
	public VinculoEmpregaticioFachada() {
		super("VinculoEmpregaticio");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected VinculoEmpregaticio obterEntidade(RequisicaoReqDTO dto) {
		return (VinculoEmpregaticio) dto.getDados().get(this.chaveMapa);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected CAPESCadastroCrudDominioDelegate<VinculoEmpregaticio, ?> obterDelegate() {
		return this.vinculoEmpregaticioDelegate;
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