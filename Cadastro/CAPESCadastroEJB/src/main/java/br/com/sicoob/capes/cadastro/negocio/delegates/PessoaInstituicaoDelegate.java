/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.negocio.dto.TransfInformacoesDTO;
import br.com.sicoob.capes.cadastro.negocio.excecao.RegistroNaoEncontradoNegocioException;
import br.com.sicoob.capes.cadastro.negocio.servicos.PessoaInstituicaoServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
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
 * Business delegate para pessoa instituicao.  
 * @author juan.damasceno
 */
public class PessoaInstituicaoDelegate extends
	CAPESCadastroCrudDelegate<PessoaInstituicao, PessoaInstituicaoServico> {
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected PessoaInstituicaoServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarPessoaInstituicaoServico();
	}
	
	/**
	 * Obter por pessoa instituicao sem validacao.
	 *
	 * @param pessoaInstituicao o valor de pessoa instituicao
	 * @return PessoaInstituicao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public PessoaInstituicao obterPorPessoaInstituicaoSemValidacao(
			PessoaInstituicao pessoaInstituicao) throws BancoobException {
		return getServico().obterPorPessoaInstituicaoSemValidacao(pessoaInstituicao);
	}

	/**
	 * Obter por pessoa instituicao.
	 *
	 * @param pessoaInstituicao o valor de pessoa instituicao
	 * @return PessoaInstituicao
	 * @throws RegistroNaoEncontradoNegocioException lança a exceção RegistroNaoEncontradoNegocioException
	 */
	public PessoaInstituicao obterPorPessoaInstituicao(PessoaInstituicao pessoaInstituicao)
			throws RegistroNaoEncontradoNegocioException {
		return getServico().obterPorPessoaInstituicao(pessoaInstituicao);
	}
	
	/**
	 * Inclui o cliente na instituição informada. 
	 * @param pessoaInstituicao Os dados da pessoa instituição.
	 * @param instituicao A instituição na qual será feita a inclusão.
	 * @return o cliente na instituição informadao.
	 * @throws BancoobException Caso ocorra alguma exceção.
	 */
	public PessoaInstituicao incluir(PessoaInstituicao pessoaInstituicao, Instituicao instituicao, boolean produtoBancoob) 
			throws BancoobException {
		return getServico().incluir(pessoaInstituicao, instituicao, produtoBancoob);
	}
	
	/**
	 * Pesquisar numero registros.
	 *
	 * @param criterios o valor de criterios
	 * @return Long
	 */
	public Long pesquisarNumeroRegistros(ConsultaDto<PessoaInstituicao> criterios){
		return getServico().pesquisarNumeroRegistros(criterios);
	}
	
	/**
	 * Atualiza as {@link PessoaInstituicao} de acordo com a consulta passada com os parametros passados
	 * que são diferentes de null
	 * @param pessoaInstituicao
	 * @param idUnidadeInst
	 * @param gerente
	 * @param nucleo
	 * @return
	 * @throws BancoobException
	 */
	public void atualizarInformacoes(PessoaInstituicao pessoaInstituicao, Integer idUnidadeInst, Funcionario gerente, Nucleo nucleo, String idUsuario) 
			throws BancoobException{
		getServico().atualizarInformacoes(pessoaInstituicao, idUnidadeInst, gerente, nucleo, idUsuario);
	}
	
	/**
	 * Incorpora a pessoaInstituicao sem as validações de cliente. 
	 * Isso é feito, pois existe clientes que foram carregados do legado sem todas as informações necessárias.
	 * @param pessoaInstituicao A pessoa instituição que está sendo incorporada.
	 * @return A pessoa incorporada
	 * @throws BancoobException Caso ocorra alguma exceção.
	 */
	public PessoaInstituicao incorporar(PessoaInstituicao pessoaInstituicao) throws BancoobException {
		return getServico().incorporar(pessoaInstituicao);
	}
	
	/**
	 * Verifica se a integração está habilitada para a
	 * instituição do usuário logado.
	 * 
	 * @return {@code Boolean} se está ou não habilitado
	 * @throws BancoobException 
	 */
	public Boolean obterStatusCOP(boolean produtoBancoob) throws BancoobException {
		return getServico().obterStatusCOP(produtoBancoob);
	}
	
	/**
	 * Obter status transferencia cliente.
	 *
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public List<StatusTransferenciaClienteVO> obterStatusTransferenciaCliente() throws BancoobException {
		return getServico().obterStatusTransferenciaCliente();
	}

	/**
	 * Pesquisar id pessoa instituicao.
	 *
	 * @param criterios o valor de criterios
	 * @return ConsultaDto
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public ConsultaDto<PessoaInstituicao> pesquisarIdPessoaInstituicao(ConsultaDto<PessoaInstituicao> criterios) throws BancoobException {
		return getServico().pesquisarIdPessoaInstituicao(criterios);
	}
	
	/**
	 * Pesquisar id pessoa instituicao.
	 *
	 * @param listaCpfCnpj
	 * @param idInstituicaoUsuarioLogado
	 * @return ConsultaDto
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public ConsultaDto<PessoaInstituicao> pesquisarIdPessoaInstituicaoByCpfCnpj(List<String> listaCpfCnpj, Integer idInstituicaoUsuarioLogado) throws BancoobException {
		return getServico().pesquisarIdPessoaInstituicaoByCpfCnpj(listaCpfCnpj, idInstituicaoUsuarioLogado);
	}
	
	/**
	 * O método Verificar cadastro pessoa.
	 *
	 * @param pessoaInstituicao o valor de pessoa instituicao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public void verificarCadastroPessoa(PessoaInstituicao pessoaInstituicao) throws BancoobException {
		getServico().verificarCadastroPessoa(pessoaInstituicao);
	}
	
	/**
	 * O método Atualizar risco cliente.
	 *
	 * @param dto o valor de dto
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public void atualizarRiscoCliente(AtualizacaoRiscoClienteDTO dto) throws BancoobException {
		getServico().atualizarRiscoCliente(dto);
	}
	
	/**
	 * Obtém a lista de unidades de instituição para exibição na tela.
	 * 
	 * @param pessoaCompartilhamento
	 * @return
	 * @throws BancoobException
	 */
	public List<UnidadeInstituicaoVO> obterListaUnidadesInstituicao(Integer idInstituicao, PessoaCompartilhamento pessoaCompartilhamento) throws BancoobException {
		return getServico().obterListaUnidadesInstituicao(idInstituicao, pessoaCompartilhamento);
	}

	/**
	 * Atualiza as {@link PessoaInstituicao} de acordo com a consulta passada com os parametros passados em lote.
	 * que são diferentes de null
	 * @param pessoaInstituicao
	 * @param idUnidadeInst
	 * @param gerente
	 * @param nucleo
	 * @return
	 * @throws BancoobException
	 */
	public void atualizarInformacoesLote(ConsultaDto<? extends CAPESEntidade<?>> pesquisaEntidades,	 TransfInformacoesDTO dto) throws BancoobException{
			getServico().atualizarInformacoesLote(pesquisaEntidades, dto);
	}
		
}