package br.com.sicoob.capes.cadastro.negocio.delegates;

import java.util.Map;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.negocio.excecao.GrupoCompartilhamentoInexistenteException;
import br.com.sicoob.capes.cadastro.negocio.servicos.PessoaIntegracaoServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.comum.negocio.vo.PessoaPlataformaVO;
import br.com.sicoob.capes.comum.negocio.vo.ProcurarPessoaExternoVO;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * Delegate que disponibliza os serviços de integração de pessoa
 * com as outras aplicações do SICOOB.
 * 
 * @author juan.damasceno
 *
 */
public class PessoaIntegracaoDelegate extends
		CAPESCadastroDelegate<PessoaIntegracaoServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected PessoaIntegracaoServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarPessoaIntegracaoServico();
	}
	
	/**
	 * Consulta o identificador da pessoa utilizando o documento como filtro.
	 * @param idInstituicao a instituição que será usada para consultar a pessoa.
	 * @param cpfCnpj o CPF ou CNPJ da pessoa que será pesquisada.
	 * @return uma instancia de PessoaPlataformaVO contendo os dados da pessoa pesquisada.
	 * @throws GrupoCompartilhamentoInexistenteException caso não exista um 
	 * GrupoCompartilhamento para a instituição usada como filtro da pesquisa. 
	 */
	public PessoaPlataformaVO consultarPorDocumento(Integer idInstituicao, String cpfCnpj) throws BancoobException {
		return getServico().consultarPorDocumento(idInstituicao, cpfCnpj);
	}
	
	/**
	 * Consulta o identificador da pessoa utilizando o documento como filtro.
	 * @param idInstituicao a instituição que será usada para consultar a pessoa.
	 * @param cpfCnpj o CPF ou CNPJ da pessoa que será pesquisada.
	 * @return uma instancia de PessoaPlataformaVO contendo os dados da pessoa pesquisada.
	 * @throws GrupoCompartilhamentoInexistenteException caso não exista um 
	 * GrupoCompartilhamento para a instituição usada como filtro da pesquisa. 
	 */
	public Integer consultarIdPessoaPorDocumento(String cpfCnpj) throws BancoobException {
		return getServico().consultarIdPessoaPorDocumento(cpfCnpj);
	}
	
	/**
	 * Consulta a pessoa utilizando o identificador da pessoa no sistema legado.
	 * @param idInstituicao a instituição que será usada para consultar a pessoa.
	 * @param idPessoaLegado o identificador da pessoa no sistema legado.
	 * @return uma instancia de PessoaPlataformaVO contendo os dados da pessoa.
	 * @throws BancoobException caso ocorra alguma exceção.
	 */
	public PessoaPlataformaVO consultarPessoaPlataformaPorIdPessoaLegado(Integer idInstituicao, Integer idPessoaLegado) throws BancoobException {
		return getServico().consultarPessoaPlataformaPorIdPessoaLegado(idInstituicao, idPessoaLegado);
	}

	/**
	 * Consulta a pessoa utilizando o identificador da pessoa no sistema legado e a instituição do usuário logado.
	 * @param idPessoaLegado o identificador da pessoa no sistema legado.
	 * @return uma instancia de PessoaPlataformaVO contendo os dados da pessoa.
	 * @throws BancoobException caso ocorra alguma exceção.
	 */
	public PessoaPlataformaVO consultarPessoaPlataformaPorIdPessoaLegado(Integer idPessoaLegado) throws BancoobException {
		return getServico().consultarPessoaPlataformaPorIdPessoaLegado(idPessoaLegado);
	}
	
	/**
	 * Consulta a pessoa.
	 * @param idInstituicao a instituição que será usada para consultar a pessoa.
	 * @param idPessoa o identificador da pessoa que será pesquisada.
	 * @return a pessoa pesquisada.
	 * @throws GrupoCompartilhamentoInexistenteException caso não exista um 
	 * GrupoCompartilhamento para a instituição usada como filtro da pesquisa. 
	 */
	public PessoaCompartilhamento obterPessoa(Integer idInstituicao, Integer idPessoa) throws BancoobException {
		return getServico().obter(idInstituicao, idPessoa);
	}

	/**
	 * Obter pessoa por id pessoa legado.
	 *
	 * @param idInstituicao o valor de id instituicao
	 * @param idPessoaLegado o valor de id pessoa legado
	 * @return PessoaCompartilhamento
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public PessoaCompartilhamento obterPessoaPorIdPessoaLegado(Integer idInstituicao, Integer idPessoaLegado) 
			throws BancoobException {
		return getServico().obterPessoaPorIdPessoaLegado(idInstituicao, idPessoaLegado);
	}
	
	/**
	 * Consulta os dados da pessoa na instituição do usuário logado.
	 * @param idPessoa o identificador da pessoa que será consultada
	 * @return proxy para a pessoa consultada na instituição do usuário logado.
	 */
	public PessoaPlataformaVO obterPessoaPlataforma(Integer idPessoa) throws BancoobException {
		return getServico().obterPessoaPlataforma(idPessoa);
	}
	
	/**
	 * Consulta os dados da pessoa na instituição informada.
	 * @param idPessoa o identificador da pessoa que será consultada
	 * @param idInstituicao a instituição que será usada para consultar a pessoa. 
	 * @return proxy para a pessoa consultada na instituição do usuário logado.
	 */
	public PessoaPlataformaVO obterPessoaPlataforma(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		return getServico().obterPessoaPlataforma(idPessoa, idInstituicao);
	}	
	
	/**
	 * Pesquisa pessoas com relacionamento na instituição do usuário logado, utilizando os 
	 * critérios informados.
	 * 
	 * @param criterios Os critérios utilizados na consulta.
	 * @return As pessoas encontradas.
	 * @throws GrupoCompartilhamentoInexistenteException caso não exista um 
	 * GrupoCompartilhamento para a instituição usada como filtro da pesquisa. 
	 */
	public ConsultaDto<PessoaPlataformaVO> pesquisarPessoaPlataforma(ConsultaDto<PessoaPlataformaVO> criterios)
			throws BancoobException {
		return getServico().pesquisarPessoaPlataforma(criterios);
	}

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
	public ConsultaDto<PessoaPlataformaVO> pesquisarPessoaPlataformaResumido(ConsultaDto<PessoaPlataformaVO> criterios)
			throws BancoobException {
		return getServico().pesquisarPessoaPlataformaResumido(criterios);
	}

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
	public Map<String, Object> obterDadosPessoaLegado(String cpfCnpj, Integer idInstituicao)
			throws BancoobException {
		return getServico().obterDadosPessoaLegado(cpfCnpj, idInstituicao);
	}
	
	/**
	 * Realiza a consulta do componente PROCURAR PESSOA EXTERNO CAPES
	 * @param criterios
	 * @return
	 * @throws BancoobException
	 */
	public ConsultaDto<ProcurarPessoaExternoVO> procurarPessoaExterno(ConsultaDto<ProcurarPessoaExternoVO> criterios) throws BancoobException {
		return getServico().procurarPessoaExterno(criterios);
	}
}