package br.com.sicoob.capes.integracao.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.comum.negocio.enums.TipoPessoaEnum;
import br.com.sicoob.capes.comum.negocio.vo.DadosReceitaFederalVO;
import br.com.sicoob.capes.integracao.negocio.vo.itx.fipe.DetalheFipeVO;
import br.com.sicoob.capes.integracao.negocio.vo.itx.fipe.MarcaFipeVO;
import br.com.sicoob.capes.integracao.negocio.vo.itx.fipe.ModeloFipeVO;
import br.com.sicoob.capes.integracao.negocio.vo.itx.fipe.VeiculoFipeVO;

/**
 * Serviço para integração com o sistema "Integrações Externas" (Consultas
 * Externas)
 * 
 * @author Rodrigo.Chaves
 */
public interface ITXIntegracaoServico extends CAPESIntegracaoServico {

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
	DadosReceitaFederalVO obterDadosReceita(TipoPessoaEnum tipoPessoa, final String cpfCnpj) throws BancoobException;

	/**
	 * Requisitar consultas.
	 * 
	 * @param cpfCnpj
	 *            the cpf cnpj
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	void requisitarConsultas(String cpfCnpj) throws BancoobException;

	/**
	 * Método que obtém os detalhes de uma consulta de anotações
	 * 
	 * @param idConsulta
	 * @return
	 * @throws BancoobException
	 */
	byte[] gerarRelatorioConsulta(Integer idConsulta) throws BancoobException;

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
	byte[] gerarRelatorioConsulta(String tipoRelatorio, String idInstituicao, String idUnidadeInstituicao, String login, String mesConsultaBacen,
			String anoConsultaBacen, String cpfCnpj) throws BancoobException;
	
	MarcaFipeVO[] obterMarcasFIPE(String tipoVeiculo) throws BancoobException;
	
	VeiculoFipeVO[] obterVeiculosFIPE(String tipoVeiculo, Integer idMarca) throws BancoobException;
	
	ModeloFipeVO[] obterModelosFIPE(String tipoVeiculo, Integer idMarca, Integer idVeiculo) throws BancoobException;
	
	DetalheFipeVO obterDetalheFIPE(String tipoVeiculo, Integer idMarca, Integer idVeiculo, Integer idModelo) throws BancoobException;

}