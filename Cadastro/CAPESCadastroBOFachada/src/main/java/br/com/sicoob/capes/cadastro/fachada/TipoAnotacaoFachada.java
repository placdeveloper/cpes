/**
 * 
 */
package br.com.sicoob.capes.cadastro.fachada;

import java.util.Map;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.DadosSelGeralDTO;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.SelGeralReqDTO;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.CategoriaAnotacaoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.PeriodicidadeAnotacaoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.TipoAnotacaoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.TipoCapturaDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.TipoConsultaOrigemDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.TipoObservacaoAnotacaoDelegate;
import br.com.sicoob.capes.comum.negocio.vo.InstituicaoVO;
import br.com.sicoob.capes.integracao.negocio.delegates.CAPESIntegracaoFabricaDelegates;
import br.com.sicoob.capes.integracao.negocio.delegates.SCIIntegracaoDelegate;
import br.com.sicoob.capes.negocio.entidades.MapaTipoAnotacao;
import br.com.sicoob.capes.negocio.entidades.TipoAnotacao;

/**
 * Fachada para os tipos de anotações.
 * 
 * @author Erico.Junior
 */
@RemoteService
public class TipoAnotacaoFachada extends CAPESCadastroBOFachada {

	/** O atributo tipoAnotacaoDelegate. */
	private TipoAnotacaoDelegate tipoAnotacaoDelegate = CAPESCadastroFabricaDelegates
			.getInstance().criarTipoAnotacaoDelegate();

	/** O atributo categoriaAnotacaoDelegate. */
	private CategoriaAnotacaoDelegate categoriaAnotacaoDelegate = 
			CAPESCadastroFabricaDelegates.getInstance().criarCategoriaAnotacaoDelegate();
	
	/** O atributo tipoCapturaDelegate. */
	private TipoCapturaDelegate tipoCapturaDelegate = CAPESCadastroFabricaDelegates
			.getInstance().criarTipoCapturaDelegate();

	/** O atributo periodicidadeAnotacaoDelegate. */
	private PeriodicidadeAnotacaoDelegate periodicidadeAnotacaoDelegate = CAPESCadastroFabricaDelegates.getInstance()
			.criarPeriodicidadeAnotacaoDelegate();
	
	/** O atributo tipoConsultaOrigemDelegate. */
	private TipoConsultaOrigemDelegate tipoConsultaOrigemDelegate = CAPESCadastroFabricaDelegates.getInstance()
			.criarTipoConsultaOrigemDelegate();
	
	/** O atributo tipoObservacaoAnotacaoDelegate. */
	private TipoObservacaoAnotacaoDelegate tipoObservacaoAnotacaoDelegate = CAPESCadastroFabricaDelegates.getInstance()
			.criarTipoObservacaoAnotacaoDelegate();
	
	/**
	 * Alterar dados.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO alterarDados(RequisicaoReqDTO dto) throws BancoobException {
		TipoAnotacao tipoAnotacao = (TipoAnotacao) dto.getDados().get("tipoAnotacao");
		preencherDadosMapaTipoAnotacao(tipoAnotacao);
		tipoAnotacaoDelegate.alterar(tipoAnotacao);
		return new RetornoDTO();
	}

	/**
	 * Excluir dados.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO excluirDados(RequisicaoReqDTO dto) throws BancoobException {
		TipoAnotacao tipoAnotacao = (TipoAnotacao) dto.getDados().get("tipoAnotacao");
		tipoAnotacaoDelegate.excluir(tipoAnotacao.getCodTipoAnotacao());
		return new RetornoDTO();
	}

	/**
	 * Incluir dados.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO incluirDados(RequisicaoReqDTO dto) throws BancoobException {
		TipoAnotacao tipoAnotacao = (TipoAnotacao) dto.getDados().get("tipoAnotacao");
		
		preencherDadosMapaTipoAnotacao(tipoAnotacao);
		tipoAnotacaoDelegate.incluir(tipoAnotacao);
		
		return new RetornoDTO();
	}

	/**
	 * Obter dados.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO obterDados(RequisicaoReqDTO dto) throws BancoobException {
		return null;
	}

	/**
	 * Obter dados selecao.
	 *
	 * @param req o valor de req
	 * @return DadosSelGeralDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public DadosSelGeralDTO obterDadosSelecao(SelGeralReqDTO req) throws BancoobException {
		ConsultaDto<TipoAnotacao> consultaDto = montarConsultaDto(req, TipoAnotacao.class);
		ConsultaDto<TipoAnotacao> resposta = tipoAnotacaoDelegate.pesquisar(consultaDto);
		return montarResultado(resposta);
	}

	/**
	 * Obter definicoes.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		Map<String, Object> dados = retorno.getDados();
		dados.put("listaCategoria", categoriaAnotacaoDelegate.listar());
		dados.put("listaPeriodicidade", periodicidadeAnotacaoDelegate.listar());
		dados.put("listaTipoCaptura", tipoCapturaDelegate.listar());
		dados.put("listaTipoConsultaOrigem", tipoConsultaOrigemDelegate.listar());
		dados.put("listaObservacao", tipoObservacaoAnotacaoDelegate.listar());
		
		return retorno;
	}
	
	/**
	 * O método Preencher dados mapa tipo anotacao.
	 *
	 * @param tipoAnotacao o valor de tipo anotacao
	 */
	private void preencherDadosMapaTipoAnotacao(TipoAnotacao tipoAnotacao){
		for(MapaTipoAnotacao mapa : tipoAnotacao.getMapasTipoAnotacao()){
			mapa.setTipoAnotacao(tipoAnotacao);
		}
	}
	
	/**
	 * Obter instituicao.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO obterInstituicao(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		Integer numeroCooperativa = (Integer) dto.getDados().get("numeroCooperativa");
		
		SCIIntegracaoDelegate sciIntegracaoDelegate = CAPESIntegracaoFabricaDelegates.getInstance().criarSCIIntegracaoDelegate();
		InstituicaoVO instituicao = sciIntegracaoDelegate.obterInstituicaoPorNumeroCooperativa(numeroCooperativa);
		
		retorno.getDados().put("instituicao", instituicao);
		
		return retorno;
	}
	
	/**
	 * Obter instituicoes.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO obterInstituicoes(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		
		TipoAnotacao tipoAnotacao = (TipoAnotacao) dto.getDados().get("tipoAnotacao");
		tipoAnotacaoDelegate.carregarInstituicoes(tipoAnotacao);
		
		retorno.getDados().put("tipoAnotacao", tipoAnotacao);
		
		return retorno;
	}

}