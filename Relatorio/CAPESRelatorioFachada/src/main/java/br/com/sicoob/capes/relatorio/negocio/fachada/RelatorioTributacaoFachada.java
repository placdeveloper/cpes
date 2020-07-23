package br.com.sicoob.capes.relatorio.negocio.fachada;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.FuncionarioDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.NucleoDelegate;
import br.com.sicoob.capes.negocio.entidades.Funcionario;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.Nucleo;

/**
 * A Classe RelatorioTributacaoFachada.
 */
public class RelatorioTributacaoFachada extends CAPESRelatorioComumFachada {

	/**
	 * Instancia um novo RelatorioTributacaoFachada.
	 */
	public RelatorioTributacaoFachada() {
		super("tributacao");
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
		
		Instituicao instituicao = new Instituicao();
		instituicao.setIdInstituicao((Integer) dto.getDados().get("idInstituicao"));
		
		retorno.getDados().put("nucleos", obterNucleos(instituicao));
		retorno.getDados().put("gerentes", obterGerentes(instituicao));
		return retorno;
	}

	/**
	 * Obter nucleos.
	 *
	 * @param instituicao o valor de instituicao
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private List<Nucleo> obterNucleos(Instituicao instituicao) throws BancoobException {
		
		ConsultaDto<Nucleo> criterios = new ConsultaDto<Nucleo>();
		criterios.setFiltro(instituicao);
		
		NucleoDelegate nucleoDelegate = CAPESCadastroFabricaDelegates.getInstance()
				.criarNucleoDelegate();
		
		return nucleoDelegate.listar(criterios);
    }

	/**
	 * Obter gerentes.
	 *
	 * @param instituicao o valor de instituicao
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private List<Funcionario> obterGerentes(Instituicao instituicao) throws BancoobException {

		FuncionarioDelegate delegate = CAPESCadastroFabricaDelegates.getInstance()
		        .criarFuncionarioDelegate();
		return delegate.listarGerentes(instituicao);
	}
	
}