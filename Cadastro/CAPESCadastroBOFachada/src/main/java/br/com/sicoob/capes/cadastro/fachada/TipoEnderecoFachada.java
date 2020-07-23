/* 
 * Sicoob
 * TipoEnderecoFachada.java 
 * Criado em: 05/05/2011
 */
package br.com.sicoob.capes.cadastro.fachada;

import java.util.Map;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroCrudDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.TipoEnderecoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.TipoPessoaContatoDelegate;
import br.com.sicoob.capes.negocio.entidades.TipoEndereco;

/**
 * Define as operações do serviço de manipulação de tipo de endereço
 * 
 * 05/05/2011
 * 
 * @author Rodrigo.Chaves
 */
@RemoteService
public class TipoEnderecoFachada extends
		CAPESCadastroBOCrudDominioFachada<TipoEndereco>  {

	/** O atributo delegate. */
	private TipoEnderecoDelegate delegate = CAPESCadastroFabricaDelegates
			.getInstance().criarTipoEnderecoDelegate();
	
	/** O atributo tipoPessoaContatoDelegate. */
	private TipoPessoaContatoDelegate tipoPessoaContatoDelegate = CAPESCadastroFabricaDelegates
			.getInstance().criarTipoPessoaContatoDelegate();

	/**
	 * Construtor
	 */
	public TipoEnderecoFachada() {
		super("tipoEndereco");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected CAPESCadastroCrudDelegate<TipoEndereco, ?> obterDelegate() {
		return this.delegate;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TipoEndereco obterEntidade(RequisicaoReqDTO dto) {
		return (TipoEndereco) dto.getDados().get(this.chaveMapa);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		Map<String, Object> dados = retorno.getDados();
		dados.put("codigo", this.delegate.pesquisarProximoCodigo());
		dados.put("listaPessoaContato", tipoPessoaContatoDelegate.listar());
		return retorno;
	}
}
