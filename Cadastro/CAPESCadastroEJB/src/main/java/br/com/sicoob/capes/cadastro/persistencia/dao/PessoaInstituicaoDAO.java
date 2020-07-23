/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.negocio.dto.TransfInformacoesDTO;
import br.com.sicoob.capes.cadastro.negocio.vo.StatusTransferenciaClienteVO;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;
import br.com.sicoob.capes.negocio.entidades.Funcionario;
import br.com.sicoob.capes.negocio.entidades.Nucleo;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaInstituicao;

/**
 * Interface para o DAO de Referencia.
 * 
 * @author juan.damasceno
 */
public interface PessoaInstituicaoDAO extends
		CAPESCadastroCrudDaoIF<PessoaInstituicao> {
	
	/**
	 * Obter por pessoa instituicao.
	 *
	 * @param pessoaInstituicaoFiltro o valor de pessoa instituicao filtro
	 * @return PessoaInstituicao
	 */
	PessoaInstituicao obterPorPessoaInstituicao(
			PessoaInstituicao pessoaInstituicaoFiltro);
	
	/**
	 * Pesquisar numero registros.
	 *
	 * @param criterios o valor de criterios
	 * @return Long
	 */
	Long pesquisarNumeroRegistros(ConsultaDto<PessoaInstituicao> criterios);
	
	/**
	 * Listar por funcionario responsavel.
	 *
	 * @param funcionario o valor de funcionario
	 * @return List
	 */
	List<PessoaInstituicao> listarPorFuncionarioResponsavel(Funcionario funcionario);
	
	/**
	 * Recupera a quantidade de clientes por núcleo.
	 * @param nucleo O núcleo utilizado como filtro.
	 * @return a quantidade de clientes por núcleo.
	 */
	Long obterQuantidadeClientesPorNucleo(Nucleo nucleo);
	
	/**
	 * 
	 * @return
	 * @throws BancoobException
	 */
	List<StatusTransferenciaClienteVO> obterStatusTransferenciaCliente(Integer idInstituicao) throws BancoobException;

	/**
	 * Pesquisar id pessoa instituicao.
	 *
	 * @param criterios o valor de criterios
	 * @return ConsultaDto
	 * @throws BancoobException lança a exceção BancoobException
	 */
	ConsultaDto<PessoaInstituicao> pesquisarIdPessoaInstituicao(ConsultaDto<PessoaInstituicao> criterios) throws BancoobException;
	
	/**
	 * Pesquisar id pessoa instituicao.
	 * @param listaCpfCnpj
	 * @param idInstituicaoUsuarioLogado
	 * @return
	 * @throws BancoobException
	 */
	ConsultaDto<PessoaInstituicao> pesquisarIdPessoaInstituicaoByCpfCnpj(List<String> listaCpfCnpj, Integer idInstituicaoUsuarioLogado) throws BancoobException;
	
	/**
	 * Verifica se o funcionário a ser excluído já foi associado a algum cliente.
	 * @param funcionario
	 * @return
	 * @throws BancoobException
	 */
	boolean verificaFuncionarioAssociadoClienteHistorico(Funcionario funcionario) throws BancoobException;

	/**
	 * Altera em lote a pessoa instituição.
	 * @param pesquisaEntidades
	 * @param dto 
	 */
	void alterarLote(ConsultaDto<? extends CAPESEntidade<?>> pesquisaEntidades, TransfInformacoesDTO dto) throws BancoobException;

	/**
	 * Geração de historico apartir de uma lista com idPessoaInstituicao.
	 * @param pesquisaEntidades
	 * @param idUsuarioExclusao 
	 */
	void gerarHistoricoLote(ConsultaDto<? extends CAPESEntidade<?>> pesquisaEntidades, String idUsuarioExclusao) throws BancoobException;

	/**
	 * Atualiza as pessoas na transição pessoa.
	 */
	void atualizarTransicaoLote() throws BancoobException;
	
}