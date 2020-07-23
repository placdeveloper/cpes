/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.Nucleo;

/**
 * Define as opera��es do servi�o de Nucleo.
 * 
 * @author Juan.Damasceno
 */
public interface NucleoServico extends
		CAPESCadastroCrudServico<Nucleo> {
	
	/**
	 * Pesquisar proximo codigo.
	 *
	 * @return Integer
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	Integer pesquisarProximoCodigo() throws BancoobException;

	/**
	 * Pesquisar por instituicao.
	 *
	 * @param criterios o valor de criterios
	 * @return ConsultaDto
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	ConsultaDto<Nucleo> pesquisarPorInstituicao(ConsultaDto<Nucleo> criterios)
			throws BancoobException;
	
	/**
	 * Pesquisar por instituicao.
	 *
	 * @param criterios o valor de criterios
	 * @return ConsultaDto com n�cleos ativos
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	ConsultaDto<Nucleo> pesquisarPorInstituicaoAtivos(ConsultaDto<Nucleo> criterios)
			throws BancoobException;
	
	/**
	 * O m�todo Excluir.
	 *
	 * @param nucleo o valor de nucleo
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	void excluir(Nucleo nucleo) throws BancoobException;
	
	/**
	 * Consultar nucleo nenhum.
	 *
	 * @param instituicao o valor de instituicao
	 * @return Nucleo
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	Nucleo consultarNucleoNenhum(Instituicao instituicao) throws BancoobException;
	
	/**
	 * Consultar nucleo.
	 *
	 * @param instituicao o valor de instituicao
	 * @param numNucleo o valor de num nucleo
	 * @return Nucleo
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	Nucleo consultarNucleo(Instituicao instituicao, Integer numNucleo) throws BancoobException;

	/**
	 * Incluir.
	 *
	 * @param nucleo o valor de nucleo
	 * @param instituicao o valor de instituicao
	 * @return Nucleo
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	Nucleo incluir(Nucleo nucleo, Instituicao instituicao) throws BancoobException;
}