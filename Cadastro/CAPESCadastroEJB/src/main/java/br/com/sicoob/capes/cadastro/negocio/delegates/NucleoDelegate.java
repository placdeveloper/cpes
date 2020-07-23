/* 
 * Sicoob
 * TipoPessoaDelegate.java 
 * Criado em: 07/07/2011
 */
package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.negocio.servicos.NucleoServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.Nucleo;

/**
 * @author juan.damasceno
 */
public class NucleoDelegate extends
		CAPESCadastroCrudDelegate<Nucleo, NucleoServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected NucleoServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarNucleoServico();
	}
	
	/**
	 * Pesquisar por instituicao.
	 *
	 * @param criterios o valor de criterios
	 * @return ConsultaDto
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public ConsultaDto<Nucleo> pesquisarPorInstituicao(ConsultaDto<Nucleo> criterios)
			throws BancoobException {
		return getServico().pesquisarPorInstituicao(criterios);
	}
	
	/**
	 * Pesquisar por instituicao.
	 *
	 * @param criterios o valor de criterios
	 * @return ConsultaDto com nucleos ativos
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public ConsultaDto<Nucleo> pesquisarPorInstituicaoAtivos(ConsultaDto<Nucleo> criterios)
			throws BancoobException {
		return getServico().pesquisarPorInstituicaoAtivos(criterios);
	}
	
	/**
	 * Pesquisar proximo codigo.
	 *
	 * @return Integer
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public Integer pesquisarProximoCodigo() throws BancoobException {
		return getServico().pesquisarProximoCodigo();
	}
	
	/**
	 * O método Excluir.
	 *
	 * @param nucleo o valor de nucleo
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public void excluir(Nucleo nucleo) throws BancoobException {
		getServico().excluir(nucleo);
	}
	
	/**
	 * Consultar nucleo nenhum.
	 *
	 * @param instituicao o valor de instituicao
	 * @return Nucleo
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public Nucleo consultarNucleoNenhum(Instituicao instituicao) throws BancoobException {
		return getServico().consultarNucleoNenhum(instituicao);
	}
	
	/**
	 * Consultar nucleo.
	 *
	 * @param instituicao o valor de instituicao
	 * @param numNucleo o valor de num nucleo
	 * @return Nucleo
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public Nucleo consultarNucleo(Instituicao instituicao, Integer numNucleo) throws BancoobException {
		return getServico().consultarNucleo(instituicao, numNucleo);
	}
	
	/**
	 * Incluir.
	 *
	 * @param nucleo o valor de nucleo
	 * @param instituicao o valor de instituicao
	 * @return Nucleo
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public Nucleo incluir(Nucleo nucleo, Instituicao instituicao) throws BancoobException {
		return getServico().incluir(nucleo, instituicao);
	}
}
