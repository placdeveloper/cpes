package br.com.sicoob.capes.cadastro.negocio.servicos;

import java.util.Map;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.negocio.excecao.GrupoCompartilhamentoInexistenteException;
import br.com.sicoob.capes.comum.negocio.vo.PessoaPlataformaVO;
import br.com.sicoob.capes.comum.negocio.vo.ProcurarPessoaExternoVO;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;


/**
 * Interface que estabelece os serviços de integração de pessoa
 * com as outras aplicações do SICOOB.
 * 
 * @author juan.damasceno
 *
 */
public interface PessoaIntegracaoServico extends
		CAPESCadastroServico {
	
	/**
	 * Consulta a pessoa utilizando o documento como filtro.
	 * @param idInstituicao a instituição que será usada para consultar a pessoa.
	 * @param cpfCnpj o CPF ou CNPJ da pessoa que será pesquisada.
	 * @return uma instancia de PessoaPlataformaVO contendo os dados da pessoa pesquisada.
	 * @throws GrupoCompartilhamentoInexistenteException caso não exista um 
	 * GrupoCompartilhamento para a instituição usada como filtro da pesquisa. 
	 */
	PessoaPlataformaVO consultarPorDocumento(Integer idInstituicao, String cpfCnpj)
			throws BancoobException;
	
	/**
	 * Consulta o identificador da pessoa utilizando o documento como filtro.
	 * @param idInstituicao a instituição que será usada para consultar a pessoa.
	 * @param cpfCnpj o CPF ou CNPJ da pessoa que será pesquisada.
	 * @return uma instancia de PessoaPlataformaVO contendo os dados da pessoa pesquisada.
	 * @throws GrupoCompartilhamentoInexistenteException caso não exista um 
	 * GrupoCompartilhamento para a instituição usada como filtro da pesquisa. 
	 */
	Integer consultarIdPessoaPorDocumento(String cpfCnpj) throws BancoobException;
	
	/**
	 * Consulta a pessoa utilizando o identificador da pessoa no sistema legado.
	 * @param idInstituicao a instituição que será usada para consultar a pessoa.
	 * @param idPessoaLegado o identificador da pessoa no sistema legado.
	 * @return uma instancia de PessoaPlataformaVO contendo os dados da pessoa.
	 * @throws BancoobException caso ocorra alguma exceção.
	 */
	PessoaPlataformaVO consultarPessoaPlataformaPorIdPessoaLegado(Integer idPessoaLegado,
			Integer instituicao) throws BancoobException;
	
	/**
	 * Consulta a pessoa utilizando o identificador da pessoa no sistema legado e a instituição do usuário logado.
	 * @param idPessoaLegado o identificador da pessoa no sistema legado.
	 * @return uma instancia de PessoaPlataformaVO contendo os dados da pessoa.
	 * @throws BancoobException caso ocorra alguma exceção.
	 */
	PessoaPlataformaVO consultarPessoaPlataformaPorIdPessoaLegado(Integer idPessoaLegado) throws BancoobException;
	
	/**
	 * Pesquisa pessoas com relacionamento na instituição do usuário logado, utilizando os 
	 * critérios informados.
	 * 
	 * @param criterios Os critérios utilizados na consulta.
	 * @return As pessoas encontradas.
	 * @throws GrupoCompartilhamentoInexistenteException caso não exista um 
	 * GrupoCompartilhamento para a instituição usada como filtro da pesquisa. 
	 */
	ConsultaDto<PessoaPlataformaVO> pesquisarPessoaPlataforma(
			ConsultaDto<PessoaPlataformaVO> criterios) throws BancoobException;

	/**
	 * Pesquisa pessoas com relacionamento na instituição do usuário logado, utilizando os 
	 * critérios informados.
	 * 
	 * Retorna as informacoes minimas e com limite de registros.
	 * 
	 * @param criterios Os critérios utilizados na consulta.
	 * @return As pessoas encontradas.
	 * @throws GrupoCompartilhamentoInexistenteException caso não exista um 
	 * GrupoCompartilhamento para a instituição usada como filtro da pesquisa. 
	 */
	ConsultaDto<PessoaPlataformaVO> pesquisarPessoaPlataformaResumido(
			ConsultaDto<PessoaPlataformaVO> criterios) throws BancoobException;

	/**
	 * Consulta a pessoa.
	 * @param idInstituicao a instituição que será usada para consultar a pessoa.
	 * @param idPessoa o identificador da pessoa que será pesquisada.
	 * @return a pessoa pesquisada.
	 * @throws GrupoCompartilhamentoInexistenteException caso não exista um 
	 * GrupoCompartilhamento para a instituição usada como filtro da pesquisa. 
	 */
	PessoaCompartilhamento obter(Integer idInstituicao, Integer idPessoa) throws BancoobException;
	
	/**
	 * Consulta os dados da pessoa na instituição do usuário logado.
	 * @param idPessoa o identificador da pessoa que será consultada
	 * @return proxy para a pessoa consultada na instituição do usuário logado.
	 */
	PessoaPlataformaVO obterPessoaPlataforma(Integer idPessoa) throws BancoobException;
	
	/**
	 * Consulta os dados da pessoa na instituição informada.
	 * @param idPessoa o identificador da pessoa que será consultada
	 * @param idInstituicao a instituição que será usada para consultar a pessoa. 
	 * @return proxy para a pessoa consultada na instituição do usuário logado.
	 */
	PessoaPlataformaVO obterPessoaPlataforma(Integer idPessoa, Integer idInstituicao) throws BancoobException;
	
	/**
	 * Obter pessoa por id pessoa legado.
	 *
	 * @param idInstituicao o valor de id instituicao
	 * @param idPessoaLegado o valor de id pessoa legado
	 * @return PessoaCompartilhamento
	 * @throws BancoobException lança a exceção BancoobException
	 */
	PessoaCompartilhamento obterPessoaPorIdPessoaLegado(Integer idInstituicao, Integer idPessoaLegado) 
			throws BancoobException;

	/**
	 * Obtém os dados da pessoa do legado para utilização na plataforma de
	 * atendimento
	 * 
	 * @param cpfCnpj
	 *            O CPF (ou CNPJ) da pessoa
	 * @param idInstituicao
	 *            O ID da instituição
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