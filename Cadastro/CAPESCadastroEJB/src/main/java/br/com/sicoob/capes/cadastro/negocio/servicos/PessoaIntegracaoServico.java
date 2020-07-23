package br.com.sicoob.capes.cadastro.negocio.servicos;

import java.util.Map;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.negocio.excecao.GrupoCompartilhamentoInexistenteException;
import br.com.sicoob.capes.comum.negocio.vo.PessoaPlataformaVO;
import br.com.sicoob.capes.comum.negocio.vo.ProcurarPessoaExternoVO;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;


/**
 * Interface que estabelece os servi�os de integra��o de pessoa
 * com as outras aplica��es do SICOOB.
 * 
 * @author juan.damasceno
 *
 */
public interface PessoaIntegracaoServico extends
		CAPESCadastroServico {
	
	/**
	 * Consulta a pessoa utilizando o documento como filtro.
	 * @param idInstituicao a institui��o que ser� usada para consultar a pessoa.
	 * @param cpfCnpj o CPF ou CNPJ da pessoa que ser� pesquisada.
	 * @return uma instancia de PessoaPlataformaVO contendo os dados da pessoa pesquisada.
	 * @throws GrupoCompartilhamentoInexistenteException caso n�o exista um 
	 * GrupoCompartilhamento para a institui��o usada como filtro da pesquisa. 
	 */
	PessoaPlataformaVO consultarPorDocumento(Integer idInstituicao, String cpfCnpj)
			throws BancoobException;
	
	/**
	 * Consulta o identificador da pessoa utilizando o documento como filtro.
	 * @param idInstituicao a institui��o que ser� usada para consultar a pessoa.
	 * @param cpfCnpj o CPF ou CNPJ da pessoa que ser� pesquisada.
	 * @return uma instancia de PessoaPlataformaVO contendo os dados da pessoa pesquisada.
	 * @throws GrupoCompartilhamentoInexistenteException caso n�o exista um 
	 * GrupoCompartilhamento para a institui��o usada como filtro da pesquisa. 
	 */
	Integer consultarIdPessoaPorDocumento(String cpfCnpj) throws BancoobException;
	
	/**
	 * Consulta a pessoa utilizando o identificador da pessoa no sistema legado.
	 * @param idInstituicao a institui��o que ser� usada para consultar a pessoa.
	 * @param idPessoaLegado o identificador da pessoa no sistema legado.
	 * @return uma instancia de PessoaPlataformaVO contendo os dados da pessoa.
	 * @throws BancoobException caso ocorra alguma exce��o.
	 */
	PessoaPlataformaVO consultarPessoaPlataformaPorIdPessoaLegado(Integer idPessoaLegado,
			Integer instituicao) throws BancoobException;
	
	/**
	 * Consulta a pessoa utilizando o identificador da pessoa no sistema legado e a institui��o do usu�rio logado.
	 * @param idPessoaLegado o identificador da pessoa no sistema legado.
	 * @return uma instancia de PessoaPlataformaVO contendo os dados da pessoa.
	 * @throws BancoobException caso ocorra alguma exce��o.
	 */
	PessoaPlataformaVO consultarPessoaPlataformaPorIdPessoaLegado(Integer idPessoaLegado) throws BancoobException;
	
	/**
	 * Pesquisa pessoas com relacionamento na institui��o do usu�rio logado, utilizando os 
	 * crit�rios informados.
	 * 
	 * @param criterios Os crit�rios utilizados na consulta.
	 * @return As pessoas encontradas.
	 * @throws GrupoCompartilhamentoInexistenteException caso n�o exista um 
	 * GrupoCompartilhamento para a institui��o usada como filtro da pesquisa. 
	 */
	ConsultaDto<PessoaPlataformaVO> pesquisarPessoaPlataforma(
			ConsultaDto<PessoaPlataformaVO> criterios) throws BancoobException;

	/**
	 * Pesquisa pessoas com relacionamento na institui��o do usu�rio logado, utilizando os 
	 * crit�rios informados.
	 * 
	 * Retorna as informacoes minimas e com limite de registros.
	 * 
	 * @param criterios Os crit�rios utilizados na consulta.
	 * @return As pessoas encontradas.
	 * @throws GrupoCompartilhamentoInexistenteException caso n�o exista um 
	 * GrupoCompartilhamento para a institui��o usada como filtro da pesquisa. 
	 */
	ConsultaDto<PessoaPlataformaVO> pesquisarPessoaPlataformaResumido(
			ConsultaDto<PessoaPlataformaVO> criterios) throws BancoobException;

	/**
	 * Consulta a pessoa.
	 * @param idInstituicao a institui��o que ser� usada para consultar a pessoa.
	 * @param idPessoa o identificador da pessoa que ser� pesquisada.
	 * @return a pessoa pesquisada.
	 * @throws GrupoCompartilhamentoInexistenteException caso n�o exista um 
	 * GrupoCompartilhamento para a institui��o usada como filtro da pesquisa. 
	 */
	PessoaCompartilhamento obter(Integer idInstituicao, Integer idPessoa) throws BancoobException;
	
	/**
	 * Consulta os dados da pessoa na institui��o do usu�rio logado.
	 * @param idPessoa o identificador da pessoa que ser� consultada
	 * @return proxy para a pessoa consultada na institui��o do usu�rio logado.
	 */
	PessoaPlataformaVO obterPessoaPlataforma(Integer idPessoa) throws BancoobException;
	
	/**
	 * Consulta os dados da pessoa na institui��o informada.
	 * @param idPessoa o identificador da pessoa que ser� consultada
	 * @param idInstituicao a institui��o que ser� usada para consultar a pessoa. 
	 * @return proxy para a pessoa consultada na institui��o do usu�rio logado.
	 */
	PessoaPlataformaVO obterPessoaPlataforma(Integer idPessoa, Integer idInstituicao) throws BancoobException;
	
	/**
	 * Obter pessoa por id pessoa legado.
	 *
	 * @param idInstituicao o valor de id instituicao
	 * @param idPessoaLegado o valor de id pessoa legado
	 * @return PessoaCompartilhamento
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	PessoaCompartilhamento obterPessoaPorIdPessoaLegado(Integer idInstituicao, Integer idPessoaLegado) 
			throws BancoobException;

	/**
	 * Obt�m os dados da pessoa do legado para utiliza��o na plataforma de
	 * atendimento
	 * 
	 * @param cpfCnpj
	 *            O CPF (ou CNPJ) da pessoa
	 * @param idInstituicao
	 *            O ID da institui��o
	 * 
	 * @return um mapa com os dados
	 */
	Map<String, Object> obterDadosPessoaLegado(String cpfCnpj, Integer idInstituicao)
			throws BancoobException;
	
	/**
	 * Realiza a consulta do componente PROCURAR PESSOA EXTERNO CAPES
	 */
	ConsultaDto<ProcurarPessoaExternoVO> procurarPessoaExterno(ConsultaDto<ProcurarPessoaExternoVO> criterios) throws BancoobException;
}