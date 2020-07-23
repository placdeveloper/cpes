/* 
 * Sicoob
 * TipoEmailFachada.java 
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
import br.com.sicoob.capes.cadastro.negocio.delegates.TipoEmailDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.TipoPessoaContatoDelegate;
import br.com.sicoob.capes.negocio.entidades.TipoEmail;

/**
 * Define as operações do serviço de manipulação de tipo de e-mail
 * 
 * 05/05/2011
 * 
 * @author Rodrigo.Chaves
 */
@RemoteService
public class TipoEmailFachada extends
		CAPESCadastroBOCrudDominioFachada<TipoEmail> {

	/** O atributo delegate. */
	private TipoEmailDelegate delegate = CAPESCadastroFabricaDelegates.getInstance()
			.criarTipoEmailDelegate();
	
	/** O atributo tipoPessoaContatoDelegate. */
	private TipoPessoaContatoDelegate tipoPessoaContatoDelegate = CAPESCadastroFabricaDelegates
			.getInstance().criarTipoPessoaContatoDelegate();
	
	/**
	 * Construtor
	 */
	public TipoEmailFachada() {
		super("tipoEmail");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected CAPESCadastroCrudDelegate<TipoEmail, ?> obterDelegate() {
		return this.delegate;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TipoEmail obterEntidade(RequisicaoReqDTO dto) {
		return (TipoEmail) dto.getDados().get(this.chaveMapa);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		Map<String, Object> dados = retorno.getDados();
		dados.put("codigo", this.delegate.pesquisarProximoCodigo());
		dados.put("listaPessoaContato", this.tipoPessoaContatoDelegate.listar());
		return retorno;
	}

}
