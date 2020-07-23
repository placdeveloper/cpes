package br.com.sicoob.capes.cadastro.negocio.servicos;

import java.util.List;
import java.util.Map;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.negocio.entidades.Pessoa;
import br.com.sicoob.capes.negocio.entidades.TransicaoPessoa;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.cadastro.negocio.vo.DadosAlteracaoGrupoVO;
import br.com.sicoob.capes.cadastro.negocio.vo.TransicaoPessoaVO;

/**
 * Interface que define os métodos de serviço de TransicaoPessoa.
 * 
 * @author Juan.Damasceno
 *
 */
public interface TransicaoPessoaServico extends CAPESCadastroCrudServico<TransicaoPessoa>{

	/**
	 * Consulta a entidade TransicaoPessoa usando idInstituicao, idUnidadeInst e idPessoaLegado 
	 * como filtro.
	 * @param transicao 
	 * @return o resultado da consulta
	 */
	List<TransicaoPessoa> listar(TransicaoPessoa transicao);
	
	/**
	 * Lista todas as transições da pessoa informada. Utilizado na alteração do CPF/CNPJ.
	 * @param pessoa A pessoa a ser consultada.
	 * @return lista com todas as transições da pessoa, independente do compartilhamento.
	 */
	List<TransicaoPessoaVO> listarTodasTransicoes(Pessoa pessoa);
	
	/**
	 * Verifica se existe alguma referência para a instituição na TransicaoPessoa
	 * @param idInstituicao id da Instituição que deseja verificar.
	 * @return Boolean verdadeiro caso a instituição esteja cadastrada na TransicaoPessoa ou false caso não esteja.
	 */
	Boolean verificarInstituicao(Integer idInstituicao);

	/**
	 * Valida se a pessoa possui relacionamento com o Bancoob
	 * 
	 * @param pessoa
	 *            a pessoa que se deseja validar
	 */
	void validarPessoaPossuiRelacionamentoBancoob(PessoaCompartilhamento pessoa)
			throws BancoobException;
	
	/**
	 * Obtém a transição de determinada {@link Pessoa} em uma determinada {@link Instituicao}
	 * 
	 * @param pessoa
	 *            a pessoa
	 * @param idInstituicao
	 *            o ID da instituição
	 * @return a transição
	 * @throws BancoobException
	 *             se houver alguma exceção
	 */
	TransicaoPessoa obterTransicaoPorPessoaInstituicao(Pessoa pessoa, Integer idInstituicao)
			throws BancoobException;

	/**
	 * Lista as transições da pessoa compartilhamento para replicação.
	 * @param pessoaCompartilhamento 
	 * @return
	 */
	List<TransicaoPessoa> listarTransicoesParaReplicacao(PessoaCompartilhamento pessoaCompartilhamento);
	
	/**
	 * Verifica se a pessoaCompartilhamento possui relacionamento com o Bancoob ou não.
	 * @param pessoa A pessoa a ser verificada.
	 * @return se a pessoaCompartilhamento possui relacionamento com o Bancoob ou não.
	 * @throws BancoobException Caso ocorra algum problema.
	 */
	boolean possuiRelacionamentoBancoob(PessoaCompartilhamento pessoa) throws BancoobException;
	
	/**
	 * Recupera a transição com o Bancoob se existir ou nulo caso contrário.
	 * @param pessoa A pessoa da qual se deseja a trabsição.
	 * @return a transição com o Bancoob se existir ou nulo caso contrário.
	 * @throws BancoobException Caso ocorra algum problema.
	 */
	TransicaoPessoa obterTransicaoBancoob(PessoaCompartilhamento pessoa) throws BancoobException;
	
	/**
	 * Lista as transições de uma PessoaCompartilhamento
	 * @param pessoaCompartilhamento A pessoa  
	 * @return as transições de uma PessoaCompartilhamento.
	 * @throws BancoobException Caso ocorra alguma exceção.
	 */
	List<TransicaoPessoa> listar(PessoaCompartilhamento pessoaCompartilhamento) throws BancoobException;

	/**
	 * Verifica se eh incorporacao finalizada.
	 *
	 * @param idInsituicaoIncorporadora o valor de id insituicao incorporadora
	 * @param idInsituicaoIncorporada o valor de id insituicao incorporada
	 * @return {@code true}, se for incorporacao finalizada
	 * @throws BancoobException lança a exceção BancoobException
	 */
	Boolean isIncorporacaoFinalizada(Integer idInsituicaoIncorporadora, Integer idInsituicaoIncorporada)
			throws BancoobException;

	/**
	 * Obtém a quantidade de pessoas em uma instituição
	 * 
	 * @param idInstituicao
	 *            o ID da instituição
	 * @return a quantidade de pessoas
	 */
	Integer buscarQuantidadePessoasInstituicao(Integer idInstituicao) throws BancoobException;

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
	 *            o código do {@link CompartilhamentoCadastro}
	 * @return as quantidades de pessoas
	 */
	Map<String, Integer> buscarQuantidadesPessoasAlteracaoGrupo(
			Integer idInstituicao, Short codigoCompartilhamento)
			throws BancoobException;

	/**
	 * Obtém as pessoas de uma determinada instituição que existem em um determinado grupo de
	 * compartilhamento
	 * 
	 * @param idInstituicao
	 *            o ID da instituição
	 * @param codigoCompartilhamento
	 *            o código do {@link CompartilhamentoCadastro}
	 * @return a quantidade de pessoas
	 */
	List<TransicaoPessoa> buscarPessoasExistentesGrupo(Integer idInstituicao,
			Short codigoCompartilhamento) throws BancoobException;


	/**
	 * Recupera os dados ({@link TransicaoPessoa} de origem e destino), para alteração de grupo de
	 * compartilhamento, das pessoas que já estão no grupo de destino
	 * @param criterios os critérios da busca
	 * 
	 * @return lista com os dados
	 * @throws BancoobException
	 */
	ConsultaDto<DadosAlteracaoGrupoVO> buscarDadosAlteracaoGrupoPessoasNoDestino(
			ConsultaDto<DadosAlteracaoGrupoVO> criterios) throws BancoobException;
	
	
	TransicaoPessoa obterTransicaoPorPessoaCompartilhamentoInstituicao(Long idPessoaCompartilhamento, Integer idInstituicao) throws BancoobException;
}
