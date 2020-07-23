package br.com.sicoob.capes.cadastro.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.vo.CentralSingularVO;
import br.com.sicoob.capes.cadastro.negocio.vo.UnidadeVO;

/**
 * A Interface CentraisSingularesServico.
 */
public interface CentraisSingularesServico extends CAPESCadastroServico {
	
	/**
	 * Obter lista centrais.
	 *
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	List<CentralSingularVO> obterListaCentrais() throws BancoobException;

	/**
	 * Obter lista singulares.
	 *
	 * @param numeroCooperativa o valor de numero cooperativa
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	List<CentralSingularVO> obterListaSingulares(Integer numeroCooperativa) throws BancoobException;
	
	/**
	 * Obter lista pacs.
	 *
	 * @param numeroCooperativa o valor de numero cooperativa
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	List<UnidadeVO> obterListaPacs(Integer numeroCooperativa) throws BancoobException;

}