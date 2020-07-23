/* 
 * Sicoob
 * TipoRelacionamentoPessoaFachada.java 
 * Criado em: 08/08/2011
 */
package br.com.sicoob.capes.cadastro.fachada;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.capes.cadastro.negocio.delegates.TipoRelacionamentoPessoaDelegate;
import br.com.sicoob.capes.negocio.entidades.TipoRelacionamentoPessoa;

/**
 * 08/08/2011
 * 
 * @author Rodrigo.Chaves
 */
@RemoteService
public class TipoRelacionamentoPessoaFachada extends CAPESCadastroBOCrudFachada<TipoRelacionamentoPessoa> {

	/** A constante TIPO_RELACIONAMENTO_PESSOA. */
	private static final String TIPO_RELACIONAMENTO_PESSOA = "tipoRelacionamentoPessoa";

	/**
	 * Construtor
	 * 
	 * @param chaveMapa
	 */
	public TipoRelacionamentoPessoaFachada() {
		super(TIPO_RELACIONAMENTO_PESSOA);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TipoRelacionamentoPessoaDelegate obterDelegate() {
		return obterFabricaDelegates().criarTipoRelacionamentoPessoaDelegate();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TipoRelacionamentoPessoa obterEntidade(RequisicaoReqDTO dto) {
		return (TipoRelacionamentoPessoa) dto.getDados().get(this.chaveMapa);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = super.obterDefinicoes(dto);
		retorno.getDados().put("codigo", obterDelegate().pesquisarProximoCodigo());
		return retorno;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RetornoDTO alterarDados(RequisicaoReqDTO dto) throws BancoobException {
		TipoRelacionamentoPessoa entidade = obterEntidade(dto);
		TipoRelacionamentoPessoa reverso = (TipoRelacionamentoPessoa) dto.getDados().get("reversoOriginal");
		obterDelegate().alterar(entidade, reverso);
		return obterMapRetorno(this.chaveMapa, entidade);
	}
	
	@Override
	protected void preencherFiltroPesquisaComponente(TipoRelacionamentoPessoa entidade, RequisicaoReqDTO dto) {
		Integer codigo = (Integer) dto.getDados().get("codigo");
		String descricao = (String) dto.getDados().get("descricao");

		entidade.setCodigo(codigo != null ? codigo.shortValue() : null);
		entidade.setDescricao(descricao);
	}

}