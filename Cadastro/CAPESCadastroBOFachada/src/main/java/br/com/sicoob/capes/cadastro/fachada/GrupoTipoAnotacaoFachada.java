package br.com.sicoob.capes.cadastro.fachada;

import java.util.Collections;
import java.util.List;

import org.apache.commons.beanutils.BeanComparator;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.GrupoTipoAnotacaoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.TipoAnotacaoDelegate;
import br.com.sicoob.capes.negocio.entidades.GrupoTipoAnotacao;
import br.com.sicoob.capes.negocio.entidades.TipoAnotacao;

/**
 * A fachada dos grupos de tipo de anotação
 * 
 * 
 * @author Bruno.Carneiro
 */
@RemoteService
public class GrupoTipoAnotacaoFachada extends CAPESCadastroBOCrudFachada<GrupoTipoAnotacao> {

	/** O atributo delegate. */
	private GrupoTipoAnotacaoDelegate delegate = CAPESCadastroFabricaDelegates.getInstance().criarGrupoTipoAnotacaoDelegate();

	/**
	 * Instancia um novo DestinoExportacaoFachada.
	 */
	public GrupoTipoAnotacaoFachada() {
		super("grupoTipoAnotacao");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected GrupoTipoAnotacao obterEntidade(RequisicaoReqDTO dto) {
		return (GrupoTipoAnotacao) dto.getDados().get(this.chaveMapa);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected GrupoTipoAnotacaoDelegate obterDelegate() {
		return delegate;
	}

	@Override
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();

		TipoAnotacaoDelegate tipoAnotacaoDelegate = CAPESCadastroFabricaDelegates.getInstance().criarTipoAnotacaoDelegate();
		List<TipoAnotacao> listaTipoAnotacao = tipoAnotacaoDelegate.listar();
		Collections.sort(listaTipoAnotacao, new BeanComparator<TipoAnotacao>("codTipoAnotacao"));
		retorno.getDados().put("tiposAnotacao", listaTipoAnotacao);

		return retorno;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RetornoDTO excluirDados(RequisicaoReqDTO dto) throws BancoobException {
		GrupoTipoAnotacao entidade = obterEntidade(dto);
		obterDelegate().excluir(entidade.getId());
		return obterMapRetorno(this.chaveMapa, entidade);
	}

}