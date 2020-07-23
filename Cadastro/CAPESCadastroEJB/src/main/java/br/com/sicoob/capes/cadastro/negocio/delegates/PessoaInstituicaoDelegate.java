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
	 * @throws BancoobException lan�a a exce��o BancoobException
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
	 * @throws RegistroNaoEncontradoNegocioException lan�a a exce��o RegistroNaoEncontradoNegocioException
	 */
	public PessoaInstituicao obterPorPessoaInstituicao(PessoaInstituicao pessoaInstituicao)
			throws RegistroNaoEncontradoNegocioException {
		return getServico().obterPorPessoaInstituicao(pessoaInstituicao);
	}
	
	/**
	 * Inclui o cliente na institui��o informada. 
	 * @param pessoaInstituicao Os dados da pessoa institui��o.
	 * @param instituicao A institui��o na qual ser� feita a inclus�o.
	 * @return o cliente na institui��o informadao.
	 * @throws BancoobException Caso ocorra alguma exce��o.
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
	 * que s�o diferentes de null
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
	 * Incorpora a pessoaInstituicao sem as valida��es de cliente. 
	 * Isso � feito, pois existe clientes que foram carregados do legado sem todas as informa��es necess�rias.
	 * @param pessoaInstituicao A pessoa institui��o que est� sendo incorporada.
	 * @return A pessoa incorporada
	 * @throws BancoobException Caso ocorra alguma exce��o.
	 */
	public PessoaInstituicao incorporar(PessoaInstituicao pessoaInstituicao) throws BancoobException {
		return getServico().incorporar(pessoaInstituicao);
	}
	
	/**
	 * Verifica se a integra��o est� habilitada para a
	 * institui��o do usu�rio logado.
	 * 
	 * @return {@code Boolean} se est� ou n�o habilitado
	 * @throws BancoobException 
	 */
	public Boolean obterStatusCOP(boolean produtoBancoob) throws BancoobException {
		return getServico().obterStatusCOP(produtoBancoob);
	}
	
	/**
	 * Obter status transferencia cliente.
	 *
	 * @return List
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	public List<StatusTransferenciaClienteVO> obterStatusTransferenciaCliente() throws BancoobException {
		return getServico().obterStatusTransferenciaCliente();
	}

	/**
	 * Pesquisar id pessoa instituicao.
	 *
	 * @param criterios o valor de criterios
	 * @return ConsultaDto
	 * @throws BancoobException lan�a a exce��o BancoobException
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
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	public ConsultaDto<PessoaInstituicao> pesquisarIdPessoaInstituicaoByCpfCnpj(List<String> listaCpfCnpj, Integer idInstituicaoUsuarioLogado) throws BancoobException {
		return getServico().pesquisarIdPessoaInstituicaoByCpfCnpj(listaCpfCnpj, idInstituicaoUsuarioLogado);
	}
	
	/**
	 * O m�todo Verificar cadastro pessoa.
	 *
	 * @param pessoaInstituicao o valor de pessoa instituicao
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	public void verificarCadastroPessoa(PessoaInstituicao pessoaInstituicao) throws BancoobException {
		getServico().verificarCadastroPessoa(pessoaInstituicao);
	}
	
	/**
	 * O m�todo Atualizar risco cliente.
	 *
	 * @param dto o valor de dto
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	public void atualizarRiscoCliente(AtualizacaoRiscoClienteDTO dto) throws BancoobException {
		getServico().atualizarRiscoCliente(dto);
	}
	
	/**
	 * Obt�m a lista de unidades de institui��o para exibi��o na tela.
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
	 * que s�o diferentes de null
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