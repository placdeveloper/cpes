package br.com.sicoob.capes.cadastro.persistencia.dao;

import java.util.List;
import java.util.Map;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.negocio.vo.DadosAlteracaoGrupoVO;
import br.com.sicoob.capes.cadastro.negocio.vo.TransicaoPessoaVO;
import br.com.sicoob.capes.negocio.entidades.Pessoa;
import br.com.sicoob.capes.negocio.entidades.TransicaoPessoa;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * Interface que define os m�todos de persist�ncia de TransicaoPessoa.
 * 
 * @author Juan.Damasceno
 * 
 */
public interface TransicaoPessoaDAO extends CAPESCadastroCrudDaoIF<TransicaoPessoa> {

	/**
	 * Consulta a entidade TransicaoPessoa usando idInstituicao, idUnidadeInst e idPessoaLegado como
	 * filtro.
	 * 
	 * @param transicao
	 * @return o resultado da consulta
	 */
	List<TransicaoPessoa> listar(TransicaoPessoa transicao);

	/**
	 * Lista todas as transi��es da pessoa informada. Utilizado na altera��o do CPF/CNPJ.
	 * 
	 * @param pessoa
	 *            A pessoa a ser consultada.
	 * @return lista com todas as transi��es da pessoa, independente do compartilhamento.
	 */
	List<TransicaoPessoaVO> listarTodasTransicoes(Pessoa pessoa);

	/**
	 * Verifica se existe alguma refer�ncia para a institui��o na TransicaoPessoa
	 * 
	 * @param idInstituicao
	 *            id da Institui��o que deseja verificar.
	 * @return Boolean verdadeiro caso a institui��o esteja cadastrada na TransicaoPessoa ou false
	 *         caso n�o esteja.
	 */
	Boolean verificarInstituicao(Integer idInstituicao);

	/**
	 * Obt�m a transi��o de determinada {@link Pessoa} em uma determinada 
	 * {@link br.com.sicoob.capes.negocio.entidades.Instituicao}
	 * 
	 * @param idPessoa
	 *            o ID da pessoa
	 * @param idInstituicao
	 *            o ID da institui��o
	 * @return a transi��o
	 * @throws BancoobException
	 *             se houver alguma exce��o
	 */
	TransicaoPessoa obterTransicaoPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao)
			throws BancoobException;
	
	/**
	 * Obt�m a transi��o por idPessoaCompartilhamento e idInstituicao
	 * 
	 * @param idPessoaCompartilhamento
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	TransicaoPessoa obterTransicaoPorPessoaCompartilhamentoInstituicao(Long idPessoaCompartilhamento, Integer idInstituicao) throws BancoobException;

	/**
	 * Lista as transi��es da pessoa compartilhamento para replica��o.
	 * 
	 * @param pessoaCompartilhamento
	 * @return
	 */
	List<TransicaoPessoa> listarTransicoesParaReplicacao(
			PessoaCompartilhamento pessoaCompartilhamento);

	/**
	 * Verifica se eh incorporacao finalizada.
	 *
	 * @param idInsituicaoIncorporadora o valor de id insituicao incorporadora
	 * @param idInsituicaoIncorporada o valor de id insituicao incorporada
	 * @return {@code true}, se for incorporacao finalizada
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	Boolean isIncorporacaoFinalizada(Integer idInsituicaoIncorporadora,
			Integer idInsituicaoIncorporada) throws BancoobException;

	/**
	 * Obt�m a quantidade de pessoas em uma institui��o
	 * 
	 * @param idInstituicao
	 *            o ID da institui��o
	 * @return a quantidade de pessoas
	 */
	Integer buscarQuantidadePessoasInstituicao(Integer idInstituicao) throws BancoobException;

	/**
	 * Obt�m as pessoas de uma determinada institui��o que existem em um determinado grupo de
	 * compartilhamento
	 * 
	 * @param idInstituicao
	 *            o ID da institui��o
	 * @param codigoCompartilhamento
	 *            o c�digo do {@link br.com.sicoob.capes.negocio.entidades.CompartilhamentoCadastro.CompartilhamentoCadastro}
	 * @return as pessoas
	 */
	List<TransicaoPessoa> buscarPessoasExistentesGrupo(Integer idInstituicao, Short codigoCompartilhamento)
			throws BancoobException;

	/**
	 * Recupera os dados ({@link TransicaoPessoa} de origem e destino), para altera��o de grupo de
	 * compartilhamento, das pessoas que j� est�o no grupo de destino
	 * @param criterios 
	 * 
	 * @return lista com os dados
	 * @throws BancoobException
	 */
	ConsultaDto<DadosAlteracaoGrupoVO> buscarDadosAlteracaoGrupoPessoasNoDestino(ConsultaDto<DadosAlteracaoGrupoVO> criterios)
			throws BancoobException;

	/**
	 * Obt�m as quantidades de pessoas de uma determinada institui��o que:
	 * 
	 * <ul>
	 * <li>j� existem em um determinado grupo de compartilhamento (chave: pessoasExistentes) </li>
	 * <li>N�O existem em um determinado grupo de compartilhamento e s�o
	 * compartilhadas na origem (chave: pessoasInexistentesCompartilhadas)</li>
	 * <li>N�O existem em um determinado grupo de compartilhamento e s�o �nicas
	 * na origem (chave: pessoasInexistentesUnicas)</li>
	 * </ul>
	 * 
	 * @param idInstituicao
	 *            o ID da institui��o
	 * @param codigoCompartilhamento
	 *            o c�digo do {@link br.com.sicoob.capes.negocio.entidades.CompartilhamentoCadastro}
	 * @return as quantidades de pessoas
	 */
	Map<String, Integer> buscarQuantidadesPessoasAlteracaoGrupo(
			Integer idInstituicao, Short codigoCompartilhamento) throws BancoobException;

}