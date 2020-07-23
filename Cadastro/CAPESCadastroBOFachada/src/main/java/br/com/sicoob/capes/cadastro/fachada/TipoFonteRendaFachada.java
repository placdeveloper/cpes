package br.com.sicoob.capes.cadastro.fachada;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroCrudDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.TipoFonteRendaDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.TipoPessoaDelegate;
import br.com.sicoob.capes.negocio.entidades.TipoFonteRenda;

/**
 * A Classe TipoFonteRendaFachada.
 */
@RemoteService
public class TipoFonteRendaFachada extends
		CAPESCadastroBOCrudDominioFachada<TipoFonteRenda> {

	/** O atributo delegateTipoFonteRenda. */
	private final TipoFonteRendaDelegate delegateTipoFonteRenda = CAPESCadastroFabricaDelegates
			.getInstance().criarTipoFonteRendaDelegate();
	
	/** O atributo delegateTipoPessoa. */
	private final TipoPessoaDelegate delegateTipoPessoa = CAPESCadastroFabricaDelegates
			.getInstance().criarTipoPessoaDelegate();
	
	/**
	 * Instancia um novo TipoFonteRendaFachada.
	 */
	public TipoFonteRendaFachada() {
		super("tipoFonteRenda");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TipoFonteRenda obterEntidade(RequisicaoReqDTO dto) {
		return (TipoFonteRenda) dto.getDados().get(this.chaveMapa);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected CAPESCadastroCrudDelegate<TipoFonteRenda, ?> obterDelegate() {
		return this.delegateTipoFonteRenda;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		retorno.getDados().put("codigo", this.delegateTipoFonteRenda.pesquisarProximoCodigo());
		return retorno;
	}
	
	/**
	 * Obter tps pessoa.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO obterTpsPessoa(RequisicaoReqDTO dto)
			throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		retorno.getDados().put("pessoaFisica", delegateTipoPessoa.obter(Short.valueOf("0")));
		retorno.getDados().put("pessoaJuridica", delegateTipoPessoa.obter(Short.valueOf("1")));
		return retorno;
	}
}
