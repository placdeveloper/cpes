/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.negocio.dto.TransfInformacoesDTO;
import br.com.sicoob.capes.cadastro.negocio.excecao.RegistroNaoEncontradoNegocioException;
import br.com.sicoob.capes.cadastro.negocio.vo.StatusTransferenciaClienteVO;
import br.com.sicoob.capes.comum.negocio.dto.AtualizacaoRiscoClienteDTO;
import br.com.sicoob.capes.comum.negocio.vo.UnidadeInstituicaoVO;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;
import br.com.sicoob.capes.negocio.entidades.Funcionario;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.Nucleo;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaInstituicao;

/**
 * Define as opera��es do servi�o de manipula��o de pessoa instutuicao.
 * 
 * @author Juan.Damasceno
 */
public interface PessoaInstituicaoServico extends
		CAPESCadastroCrudServico<PessoaInstituicao> {

	/**
	 * Obter por pessoa instituicao.
	 *
	 * @param pessoaInstituicao o valor de pessoa instituicao
	 * @return PessoaInstituicao
	 * @throws RegistroNaoEncontradoNegocioException lan�a a exce��o RegistroNaoEncontradoNegocioException
	 */
	PessoaInstituicao obterPorPessoaInstituicao(
			PessoaInstituicao pessoaInstituicao)
			throws RegistroNaoEncontradoNegocioException;

	/**
	 * Obter por pessoa instituicao sem validacao.
	 *
	 * @param pessoaInstituicaoFiltro o valor de pessoa instituicao filtro
	 * @return PessoaInstituicao
	 * @throws RegistroNaoEncontradoNegocioException lan�a a exce��o RegistroNaoEncontradoNegocioException
	 */
	PessoaInstituicao obterPorPessoaInstituicaoSemValidacao(
			PessoaInstituicao pessoaInstituicaoFiltro)
			throws RegistroNaoEncontradoNegocioException;

	/**
	 * Inclui o cliente na institui��o informadao.
	 * 
	 * @param pessoaInstituicao
	 *            Os dados da pessoa institui��o.
	 * @param instituicao
	 *            A institui��o na qual ser� feita a inclus�o.
	 * @return o cliente na institui��o informadao.
	 * @throws BancoobException
	 *             Caso ocorra alguma exce��o.
	 */
	PessoaInstituicao incluir(PessoaInstituicao pessoaInstituicao,
			Instituicao instituicao, boolean produtoBancoob) throws BancoobException;

	/**
	 * Pesquisar numero registros.
	 *
	 * @param criterios o valor de criterios
	 * @return Long
	 */
	Long pesquisarNumeroRegistros(
			ConsultaDto<PessoaInstituicao> criterios);

	/**
	 * Atualiza as {@link PessoaInstituicao} de acordo com a consulta passada
	 * com os parametros passados que s�o diferentes de null
	 * 
	 * @param pessoaInstituicao
	 * @param idUnidadeInst
	 * @param gerente
	 * @param nucleo
	 * @throws BancoobException
	 */
	void atualizarInformacoes(PessoaInstituicao pessoaInstituicao,
			Integer idUnidadeInst, Funcionario gerente, Nucleo nucleo, String idUsuario)
			throws BancoobException;

	/**
	 * Incorpora a pessoaInstituicao sem as valida��es de cliente. 
	 * Isso � feito, pois existe clientes que foram carregados do legado sem todas as informa��es necess�rias.
	 * @param pessoaInstituicao A pessoa institui��o que est� sendo incorporada.
	 * @return A pessoa incorporada
	 * @throws BancoobException Caso ocorra alguma exce��o.
	 */
	PessoaInstituicao incorporar(PessoaInstituicao pessoaInstituicao) throws BancoobException;
	
	/**
	 * Lista os clientes por funcion�rio respons�vel.
	 * @param funcionario O funcion�rio utilzado na pesquisa.
	 * @return os clientes por funcion�rio respons�vel.
	 */
	List<PessoaInstituicao> listarPorFuncionarioResponsavel(Funcionario funcionario);
	
	/**
	 * Recupera a quantidade de clientes por n�cleo.
	 * @param nucleo O n�cleo utilizado como filtro.
	 * @return a quantidade de clientes por n�cleo.
	 */
	Long obterQuantidadeClientesPorNucleo(Nucleo nucleo);
	
	/**
	 * Verifica se a integra��o est� habilitada para a
	 * institui��o do usu�rio logado.
	 * 
	 * @return {@code Boolean} se est� ou n�o habilitado
	 */
	Boolean obterStatusCOP(boolean produtoBancoob) throws BancoobException;
	
	/**
	 * 
	 * @return
	 * @throws BancoobException
	 */
	List<StatusTransferenciaClienteVO> obterStatusTransferenciaCliente() throws BancoobException;

	/**
	 * Retoena o id das pessoas institui��o consultadas
	 * @param criterios
	 * @return
	 * @throws BancoobException
	 */
	ConsultaDto<PessoaInstituicao> pesquisarIdPessoaInstituicao(ConsultaDto<PessoaInstituicao> criterios) throws BancoobException;
	
	/**
	 * Retoena o id das pessoas institui��o consultadas
	 * @param listaCpfCnpj
	 * @param idInstituicaoUsuarioLogado
	 * @return
	 * @throws BancoobException
	 */
	ConsultaDto<PessoaInstituicao> pesquisarIdPessoaInstituicaoByCpfCnpj(List<String> listaCpfCnpj, Integer idInstituicaoUsuarioLogado) throws BancoobException;
	
	/**
	 * Verifica o cadastro da pessoa e executa as regras
	 * @param pessoaInstituicao
	 * @throws BancoobException
	 */
	void verificarCadastroPessoa(PessoaInstituicao pessoaInstituicao) throws BancoobException;
	
	/**
	 * Verifica se o funcion�rio a ser exclu�do j� foi associado a algum cliente.
	 * @param funcionario
	 * @return
	 * @throws BancoobException
	 */
	boolean verificaFuncionarioAssociadoClienteHistorico(Funcionario funcionario) throws BancoobException;
	
	/**
	 * Atualizar risco cliente.
	 * @param dto
	 * @throws BancoobException
	 */
	void atualizarRiscoCliente(AtualizacaoRiscoClienteDTO dto) throws BancoobException;
	
	/**
	 * Obt�m a lista de unidades de institui��o para exibi��o na tela.
	 * 
	 * @param pessoaCompartilhamento
	 * @return
	 * @throws BancoobException
	 */
	List<UnidadeInstituicaoVO> obterListaUnidadesInstituicao(Integer idInstituicao, PessoaCompartilhamento pessoaCompartilhamento) throws BancoobException;

	/**
	 * Atualiza as {@link PessoaInstituicao} de acordo com a consulta passada
	 * com os parametros passados que s�o diferentes de null em Lote.
	 * 
	 * @param pessoaInstituicao
	 * @param idUnidadeInst
	 * @param gerente
	 * @param nucleo
	 * @throws BancoobException
	 */
	void atualizarInformacoesLote(ConsultaDto<? extends CAPESEntidade<?>> pesquisaEntidades,  TransfInformacoesDTO dto) throws BancoobException;;
	
}