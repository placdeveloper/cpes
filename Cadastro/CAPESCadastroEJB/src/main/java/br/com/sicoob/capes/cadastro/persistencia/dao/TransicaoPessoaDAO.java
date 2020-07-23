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
 * Interface que define os métodos de persistência de TransicaoPessoa.
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
	 * Lista todas as transições da pessoa informada. Utilizado na alteração do CPF/CNPJ.
	 * 
	 * @param pessoa
	 *            A pessoa a ser consultada.
	 * @return lista com todas as transições da pessoa, independente do compartilhamento.
	 */
	List<TransicaoPessoaVO> listarTodasTransicoes(Pessoa pessoa);

	/**
	 * Verifica se existe alguma referência para a instituição na TransicaoPessoa
	 * 
	 * @param idInstituicao
	 *            id da Instituição que deseja verificar.
	 * @return Boolean verdadeiro caso a instituição esteja cadastrada na TransicaoPessoa ou false
	 *         caso não esteja.
	 */
	Boolean verificarInstituicao(Integer idInstituicao);

	/**
	 * Obtém a transição de determinada {@link Pessoa} em uma determinada 
	 * {@link br.com.sicoob.capes.negocio.entidades.Instituicao}
	 * 
	 * @param idPessoa
	 *            o ID da pessoa
	 * @param idInstituicao
	 *            o ID da instituição
	 * @return a transição
	 * @throws BancoobException
	 *             se houver alguma exceção
	 */
	TransicaoPessoa obterTransicaoPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao)
			throws BancoobException;
	
	/**
	 * Obtém a transição por idPessoaCompartilhamento e idInstituicao
	 * 
	 * @param idPessoaCompartilhamento
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	TransicaoPessoa obterTransicaoPorPessoaCompartilhamentoInstituicao(Long idPessoaCompartilhamento, Integer idInstituicao) throws BancoobException;

	/**
	 * Lista as transições da pessoa compartilhamento para replicação.
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
	 * @throws BancoobException lança a exceção BancoobException
	 */
	Boolean isIncorporacaoFinalizada(Integer idInsituicaoIncorporadora,
			Integer idInsituicaoIncorporada) throws BancoobException;

	/**
	 * Obtém a quantidade de pessoas em uma instituição
	 * 
	 * @param idInstituicao
	 *            o ID da instituição
	 * @return a quantidade de pessoas
	 */
	Integer buscarQuantidadePessoasInstituicao(Integer idInstituicao) throws BancoobException;

	/**
	 * Obtém as pessoas de uma determinada instituição que existem em um determinado grupo de
	 * compartilhamento
	 * 
	 * @param idInstituicao
	 *            o ID da instituição
	 * @param codigoCompartilhamento
	 *            o código do {@link br.com.sicoob.capes.negocio.entidades.CompartilhamentoCadastro.CompartilhamentoCadastro}
	 * @return as pessoas
	 */
	List<TransicaoPessoa> buscarPessoasExistentesGrupo(Integer idInstituicao, Short codigoCompartilhamento)
			throws BancoobException;

	/**
	 * Recupera os dados ({@link TransicaoPessoa} de origem e destino), para alteração de grupo de
	 * compartilhamento, das pessoas que já estão no grupo de destino
	 * @param criterios 
	 * 
	 * @return lista com os dados
	 * @throws BancoobException
	 */
	ConsultaDto<DadosAlteracaoGrupoVO> buscarDadosAlteracaoGrupoPessoasNoDestino(ConsultaDto<DadosAlteracaoGrupoVO> criterios)
			throws BancoobException;

	/**
	 * Obtém as quantidades de pessoas de uma determinada instituição que:
	 * 
	 * <ul>
	 * <li>já existem em um determinado grupo de compartilhamento (chave: pessoasExistentes) </li>
	 * <li>NÃO existem em um determinado grupo de compartilhamento e são
	 * compartilhadas na origem (chave: pessoasInexistentesCompartilhadas)</li>
	 * <li>NÃO existem em um determinado grupo de compartilhamento e são únicas
	 * na origem (chave: pessoasInexistentesUnicas)</li>
	 * </ul>
	 * 
	 * @param idInstituicao
	 *            o ID da instituição
	 * @param codigoCompartilhamento
	 *            o código do {@link br.com.sicoob.capes.negocio.entidades.CompartilhamentoCadastro}
	 * @return as quantidades de pessoas
	 */
	Map<String, Integer> buscarQuantidadesPessoasAlteracaoGrupo(
			Integer idInstituicao, Short codigoCompartilhamento) throws BancoobException;

}