package br.com.sicoob.capes.integracao.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.comum.negocio.enums.TipoPessoaEnum;
import br.com.sicoob.capes.comum.negocio.vo.DadosReceitaFederalVO;
import br.com.sicoob.capes.integracao.negocio.servicos.ITXIntegracaoServico;
import br.com.sicoob.capes.integracao.negocio.servicos.locator.CAPESIntegracaoServiceLocator;
import br.com.sicoob.capes.integracao.negocio.vo.itx.fipe.DetalheFipeVO;
import br.com.sicoob.capes.integracao.negocio.vo.itx.fipe.MarcaFipeVO;
import br.com.sicoob.capes.integracao.negocio.vo.itx.fipe.ModeloFipeVO;
import br.com.sicoob.capes.integracao.negocio.vo.itx.fipe.VeiculoFipeVO;

/**
 * @author Rodrigo.Chaves
 */
public class ITXIntegracaoDelegate extends CAPESIntegracaoDelegate<ITXIntegracaoServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ITXIntegracaoServico localizarServico() {
		return CAPESIntegracaoServiceLocator.getInstance().localizarITXIntegracaoServico();
	}

	/**
	 * Obter dados receita.
	 * 
	 * @param tipoPessoa
	 *            the tipo pessoa
	 * @param cpfCnpj
	 *            the cpf cnpj
	 * @return dados receita federal vo
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	public DadosReceitaFederalVO obterDadosReceita(TipoPessoaEnum tipoPessoa, String cpfCnpj) throws BancoobException {
		return getServico().obterDadosReceita(tipoPessoa, cpfCnpj);
	}

	/**
	 * Requisitar consultas.
	 * 
	 * @param cpfCnpj
	 *            the cpf cnpj
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	public void requisitarConsultas(String cpfCnpj) throws BancoobException {
		getServico().requisitarConsultas(cpfCnpj);
	}

	/**
	 * Método que obtém os detalhes de uma consulta de anotações
	 * 
	 * @param idConsulta
	 * @return
	 * @throws BancoobException
	 */
	public byte[] gerarRelatorioConsulta(Integer idConsulta) throws BancoobException {
		return getServico().gerarRelatorioConsulta(idConsulta);
	}

	/**
	 * @param tipoRelatorio
	 *            BACEN, SERASA ou RECEITA
	 * @param idInstituicao
	 *            Dados do usuário logado
	 * @param idUnidadeInstituicao
	 *            Dados do usuário logado
	 * @param login
	 *            Dados do usuário logado
	 * @param idPessoaCompartilhamento
	 *            Id da pessoa consultada
	 * @param mesConsultaBacen
	 *            Caso o relatório seja do bacen é necessária informar o mês da
	 *            consulta
	 * @param anoConsultaBacen
	 *            Caso o relatório seja do bacen é necessária informar o ano da
	 *            consulta
	 * @param cpfCnpj
	 *            CPF ou CNPJ da pessoa consultada
	 * @return
	 * 
	 * @throws BancoobException
	 */
	public byte[] gerarRelatorioConsulta(String tipoRelatorio, String idInstituicao, String idUnidadeInstituicao, String login, String mesConsultaBacen,
			String anoConsultaBacen, String cpfCnpj) throws BancoobException {
		return getServico().gerarRelatorioConsulta(tipoRelatorio, idInstituicao, idUnidadeInstituicao, login, mesConsultaBacen, anoConsultaBacen, cpfCnpj);
	}
	
	public MarcaFipeVO[] obterMarcasFIPE(String tipoVeiculo) throws BancoobException {
		return getServico().obterMarcasFIPE(tipoVeiculo);
	}
	
	public VeiculoFipeVO[] obterVeiculosFIPE(String tipoVeiculo, Integer idMarca) throws BancoobException {
		return getServico().obterVeiculosFIPE(tipoVeiculo, idMarca);
	}
	
	public ModeloFipeVO[] obterModelosFIPE(String tipoVeiculo, Integer idMarca, Integer idVeiculo) throws BancoobException {
		return getServico().obterModelosFIPE(tipoVeiculo, idMarca, idVeiculo);
	}
	
	public DetalheFipeVO obterDetalheFIPE(String tipoVeiculo, Integer idMarca, Integer idVeiculo, Integer idModelo) throws BancoobException {
		return getServico().obterDetalheFIPE(tipoVeiculo, idMarca, idVeiculo, idModelo);
	}

}