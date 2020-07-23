package br.com.sicoob.capes.cadastro.fachada;

import java.util.Arrays;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.DadosSelGeralDTO;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.SelGeralReqDTO;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.ValidacaoCadastralRegraDelegate;
import br.com.sicoob.capes.comum.negocio.enums.FuncionalidadeValidacaoCadastralEnum;
import br.com.sicoob.capes.negocio.entidades.PerfilCadastro;
import br.com.sicoob.capes.negocio.entidades.TipoRegraValidacaoCadastral;
import br.com.sicoob.capes.negocio.entidades.ValidacaoCadastralRegra;

/**
 * A Classe ValidacaoCadastralRegraFachada.
 */
@RemoteService
public class ValidacaoCadastralRegraFachada extends
        CAPESCadastroBOCrudDominioFachada<ValidacaoCadastralRegra> {

	/** O atributo delegate. */
	private ValidacaoCadastralRegraDelegate delegate = CAPESCadastroFabricaDelegates.getInstance()
	        .criarValidacaoCadastralRegraDelegate();

	/**
	 * Instancia um novo ValidacaoCadastralRegraFachada.
	 */
	public ValidacaoCadastralRegraFachada() {
		super("regra");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ValidacaoCadastralRegra obterEntidade(RequisicaoReqDTO dto) {
		
		return (ValidacaoCadastralRegra) dto.getDados().get(this.chaveMapa);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto) throws BancoobException {

		RetornoDTO retorno = super.obterDefinicoes(dto);
		retorno.getDados().put("funcionalidades",
		        Arrays.asList(FuncionalidadeValidacaoCadastralEnum.values()));
		retorno.getDados().put("tipos", listarTipos());
		retorno.getDados().put("perfilCadastro", listarPerfilCadastro());
		return retorno;
	}

    /**
	 * {@inheritDoc}
	 */
    @Override
	public DadosSelGeralDTO obterDadosSelecao(SelGeralReqDTO dto) throws BancoobException {
		
		ConsultaDto<ValidacaoCadastralRegra> criterios = montarConsultaDto(dto,
		        ValidacaoCadastralRegra.class);
        return montarResultado(obterDelegate().pesquisar(criterios));
	}
    
	/**
	 * Obter proximo codigo.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO obterProximoCodigo(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		retorno.getDados().put("codigo", obterDelegate().pesquisarProximoCodigo());
		return retorno;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public RetornoDTO excluirDados(RequisicaoReqDTO dto) throws BancoobException {
	    ValidacaoCadastralRegra entidade = obterEntidade(dto);
        obterDelegate().excluir(entidade.getCodigoRegra());
        return obterMapRetorno(this.chaveMapa, entidade);
	}
	
	/**
	 * Listar tipos.
	 *
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private List<TipoRegraValidacaoCadastral> listarTipos() throws BancoobException {

		return CAPESCadastroFabricaDelegates.getInstance()
		        .criarTipoRegraValidacaoCadastralDelegate().listar();
	}
	
	/**
	 * Listar tipos.
	 *
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private List<PerfilCadastro> listarPerfilCadastro() throws BancoobException {

		return CAPESCadastroFabricaDelegates.getInstance()
		        .criarPerfilCadastroDelegate().listar();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ValidacaoCadastralRegraDelegate obterDelegate() {
		return delegate;
	}
}