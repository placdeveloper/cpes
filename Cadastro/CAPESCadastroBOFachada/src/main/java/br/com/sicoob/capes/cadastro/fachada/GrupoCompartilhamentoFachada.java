/**
 * 
 */
package br.com.sicoob.capes.cadastro.fachada;

import java.util.Map;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.DadosSelGeralDTO;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroCrudDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.CompartilhamentoCadastroDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.GrupoCompartilhamentoDelegate;
import br.com.sicoob.capes.negocio.entidades.CompartilhamentoCadastro;
import br.com.sicoob.capes.negocio.entidades.vigente.GrupoCompartilhamento;

/**
 * Fachada para os tipos de anotações.
 * 
 * @author Erico.Junior
 */
@RemoteService
public class GrupoCompartilhamentoFachada extends
		CAPESCadastroBOCrudFachada<GrupoCompartilhamento> {
	
	/** A constante CODIGO. */
	private static final String CODIGO = "codigo";

	/** A constante COD_COMPARTILHAMENTO_CADASTRO. */
	private static final String COD_COMPARTILHAMENTO_CADASTRO = "codCompartilhamentoCadastro";

	/** O atributo compartilhamentoCadastroDelegate. */
	private CompartilhamentoCadastroDelegate compartilhamentoCadastroDelegate = CAPESCadastroFabricaDelegates
			.getInstance().criarCompartilhamentoCadastroDelegate();
	
	/** O atributo grupoCompartilhamentoDelegate. */
	private GrupoCompartilhamentoDelegate grupoCompartilhamentoDelegate = CAPESCadastroFabricaDelegates
			.getInstance().criarGrupoCompartilhamentoDelegate();
	
	/** O atributo TAMANHO_PAGINA. */
	private static int TAMANHO_PAGINA = 15;
	
	/**
	 * Instancia um novo GrupoCompartilhamentoFachada.
	 */
	public GrupoCompartilhamentoFachada() {
		super("GrupoCompartilhamento");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected CAPESCadastroCrudDelegate<GrupoCompartilhamento, ?> obterDelegate() {
		return this.grupoCompartilhamentoDelegate;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected GrupoCompartilhamento obterEntidade(RequisicaoReqDTO dto) {
		return (GrupoCompartilhamento) dto.getDados().get(this.chaveMapa);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		Map<String, Object> dados = retorno.getDados();
		dados.put("listaGrupoCompartilhamentoCadastro", compartilhamentoCadastroDelegate.listar());
		return retorno;
	}
	
	/**
	 * Listar instuicoes por compartilhamento.
	 *
	 * @param dto o valor de dto
	 * @return DadosSelGeralDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public DadosSelGeralDTO listarInstuicoesPorCompartilhamento(RequisicaoReqDTO dto) throws BancoobException {
		return pesquisar(dto);
	}
	
	/**
	 * Excluir grupos.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO excluirGrupos(RequisicaoReqDTO dto) throws BancoobException {
		compartilhamentoCadastroDelegate.excluir(Short.valueOf(dto.getDados().get(COD_COMPARTILHAMENTO_CADASTRO).toString()));
		return new RetornoDTO();
	}
	
	/**
	 * Excluir grupo compartilhamento.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO excluirGrupoCompartilhamento(RequisicaoReqDTO dto)
			throws BancoobException {
		grupoCompartilhamentoDelegate.excluir(Integer.valueOf(dto.getDados().get("idGrupoCompartilhamento").toString()));
		return new RetornoDTO();
	}
	
	/**
	 * Obter definicoes compartilhamento cadastro.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO obterDefinicoesCompartilhamentoCadastro(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		Map<String, Object> dados = retorno.getDados();
		dados.put(CODIGO, this.compartilhamentoCadastroDelegate.pesquisarProximoCodigo());
		return retorno;
	}
	
	/**
	 * Incluir compartilhamento cadastro.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO incluirCompartilhamentoCadastro(RequisicaoReqDTO dto) throws BancoobException {
		CompartilhamentoCadastro compartilhamentoCadastro = new CompartilhamentoCadastro();
		compartilhamentoCadastro.setCodigo(Short.valueOf(dto.getDados().get(CODIGO).toString()));
		compartilhamentoCadastro.setDescricao(dto.getDados().get("descricao").toString());
		compartilhamentoCadastroDelegate.incluir(compartilhamentoCadastro);
		
		habilitarIntegracaoSRFCompartilhamentoCadastro(dto);
		
		return new RetornoDTO();
	}
	
	/**
	 * Alterar compartilhamento cadastro.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO alterarCompartilhamentoCadastro(RequisicaoReqDTO dto) throws BancoobException {
		CompartilhamentoCadastro compartilhamentoCadastro = compartilhamentoCadastroDelegate.obter(Short.valueOf(dto.getDados().get(CODIGO).toString()));		
		compartilhamentoCadastro.setDescricao(dto.getDados().get("descricao").toString());
		compartilhamentoCadastroDelegate.alterar(compartilhamentoCadastro);
		
		habilitarIntegracaoSRFCompartilhamentoCadastro(dto);
		
		return new RetornoDTO();
	}
	
	/**
	 * Listar instuicoes sem grupo.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO listarInstuicoesSemGrupo(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		Map<String, Object> dados = retorno.getDados();		
		dados.put("listaGrupoCompartilhamento", grupoCompartilhamentoDelegate.listarInstuicoesSemGrupo());
		return retorno;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public RetornoDTO incluirDados(RequisicaoReqDTO dto) throws BancoobException {
		Map<String, Object> dados = dto.getDados();
		
		Object[] listInstituicoes = (Object[])dados.get("instituicoes");	
		Short codigo = Short.valueOf(dados.get("idGrupoCompartilhamento").toString());
		
		for(int i = 0; i < listInstituicoes.length; i++) {
			GrupoCompartilhamento grupoCompartilhamento = new GrupoCompartilhamento();
			CompartilhamentoCadastro compartilhamentoCadastro = new CompartilhamentoCadastro();//compartilhamentoCadastroDelegate.obter(codigo);
			compartilhamentoCadastro.setCodigo(codigo);
			grupoCompartilhamento.setCompartilhamentoCadastro(compartilhamentoCadastro);			
			grupoCompartilhamento.setIdGrupoCompartilhamento(null);
			grupoCompartilhamento.setDataHoraInicio(new DateTimeDB());
			grupoCompartilhamento.setIdInstituicao(Integer.valueOf(listInstituicoes[i].toString()));
			
			grupoCompartilhamentoDelegate.incluir(grupoCompartilhamento);
		}
		return new RetornoDTO();
	}
	
	/**
	 * Pesquisar.
	 *
	 * @param dto o valor de dto
	 * @return DadosSelGeralDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private DadosSelGeralDTO pesquisar(RequisicaoReqDTO dto) throws BancoobException{
		int pagina = Integer.valueOf(dto.getDados().get("pagina").toString()) - DESLOCAMENTO_NUMERO_PAGINA;
		GrupoCompartilhamento filtro = new GrupoCompartilhamento();
		CompartilhamentoCadastro compCadastro = new CompartilhamentoCadastro();
		
		if(dto.getDados().get(COD_COMPARTILHAMENTO_CADASTRO) != null){
			compCadastro.setCodigo(Short.valueOf(dto.getDados().get(COD_COMPARTILHAMENTO_CADASTRO).toString()));
		}
		
		filtro.setCompartilhamentoCadastro(compCadastro);
		
		if(dto.getDados().get("codInstituicao") != null){
			filtro.setIdInstituicao(Integer.valueOf(dto.getDados().get("codInstituicao").toString()));
		}
		
		ConsultaDto<GrupoCompartilhamento> consultaDto = new ConsultaDto<GrupoCompartilhamento>();
		consultaDto.setTamanhoPagina(TAMANHO_PAGINA);
		consultaDto.setPagina(pagina);
		consultaDto.setFiltro(filtro);
		
		ConsultaDto<?> resultado = grupoCompartilhamentoDelegate.listarInstuicoesPorCompartilhamento(consultaDto);
		
		return montarResultado(resultado);
	}
	
	/**
	 * Alterar vinculo instituicao.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO alterarVinculoInstituicao(RequisicaoReqDTO dto) throws BancoobException {
		GrupoCompartilhamento grupoCompartilhamento = grupoCompartilhamentoDelegate.obter(Integer.valueOf(dto.getDados().get("idGrupoCompartilhamento").toString()));
		CompartilhamentoCadastro compartilhamentoCadastro = new CompartilhamentoCadastro();
		compartilhamentoCadastro.setId(Short.valueOf(dto.getDados().get("idCompartilhamentoCadastro").toString()));
		grupoCompartilhamento.setCompartilhamentoCadastro(compartilhamentoCadastro);
		grupoCompartilhamento.setIntegracaoSrf(Boolean.valueOf(dto.getDados().get("integracaoSrf").toString()));
		grupoCompartilhamentoDelegate.alterar(grupoCompartilhamento);
		
		return new RetornoDTO();
	}
	
	/**
	 * O método Habilitar integracao srf compartilhamento cadastro.
	 *
	 * @param dto o valor de dto
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void habilitarIntegracaoSRFCompartilhamentoCadastro(RequisicaoReqDTO dto) throws BancoobException {
		if(dto.getDados().get("habilitarIntegracaoSRF") != null){
			boolean habilitarIntegracao = (Boolean) dto.getDados().get("habilitarIntegracaoSRF");
			Integer codigoCompartilhamentoCadastro = Integer.valueOf(dto.getDados().get(CODIGO).toString());
			compartilhamentoCadastroDelegate.habilitarIntegracaoSRF(codigoCompartilhamentoCadastro, habilitarIntegracao);
		}
	}
	
}