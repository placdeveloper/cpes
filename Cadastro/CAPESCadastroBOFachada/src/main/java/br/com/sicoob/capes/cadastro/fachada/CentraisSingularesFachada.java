package br.com.sicoob.capes.cadastro.fachada;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.CentraisSingularesDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.FuncionarioDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.NucleoDelegate;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.Nucleo;

/**
 * A Classe CentraisSingularesFachada.
 */
@RemoteService
public class CentraisSingularesFachada extends CAPESCadastroBOFachada {

	/** O atributo centraisSingularesDelegate. */
	private CentraisSingularesDelegate centraisSingularesDelegate = CAPESCadastroFabricaDelegates.getInstance().criarCentraisSingularesDelegate();

	/**
	 * Obter centrais.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO obterCentrais(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		retorno.getDados().put("listaCentrais", centraisSingularesDelegate.obterListaCentrais());
		return retorno;
	}

	/**
	 * Obter singulares.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO obterSingulares(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();

		Integer numeroCooperativa = (Integer) dto.getDados().get("numeroCentral");
		retorno.getDados().put("listaSingulares", centraisSingularesDelegate.obterListaSingulares(numeroCooperativa));

		return retorno;
	}

	/**
	 * Obter unidades.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO obterUnidades(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		
		Integer numeroCooperativa = (Integer) dto.getDados().get("numeroInstituicao");
		retorno.getDados().put("listaUnidades", centraisSingularesDelegate.obterListaPacs(numeroCooperativa));

		return retorno;
	}
	
	/**
	 * Obter nucleos.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO obterNucleos(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		
		Integer idInstituicao = (Integer) dto.getDados().get("idInstituicao");
		
		NucleoDelegate nucleoDelegate = CAPESCadastroFabricaDelegates.getInstance().criarNucleoDelegate();
		Nucleo nucleo = new Nucleo();
		nucleo.setIdInstituicao(idInstituicao);
		
		ConsultaDto<Nucleo> criterios = new ConsultaDto<Nucleo>();
		criterios.setFiltro(nucleo);
		
		ConsultaDto<Nucleo> retornoConsulta = nucleoDelegate.pesquisarPorInstituicaoAtivos(criterios);
		retorno.getDados().put("listaNucleos", retornoConsulta.getResultado());

		return retorno;
	}
	
	/**
	 * Obter gerentes.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO obterGerentes(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();

		Integer idInstituicao = (Integer) dto.getDados().get("idInstituicao");

		FuncionarioDelegate delegate = CAPESCadastroFabricaDelegates.getInstance().criarFuncionarioDelegate();
		Instituicao instituicao = new Instituicao();
		instituicao.setIdInstituicao(idInstituicao);
		
		retorno.getDados().put("listaGerentes", delegate.listarGerentes(instituicao));

		return retorno;
	}

}