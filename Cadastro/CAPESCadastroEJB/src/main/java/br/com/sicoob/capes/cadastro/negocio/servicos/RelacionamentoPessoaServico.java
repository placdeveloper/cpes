/* 
 * Sicoob
 * RelacionamentoPessoaServico.java 
 * Criado em: 24/08/2011
 */
package br.com.sicoob.capes.cadastro.negocio.servicos;

import java.util.Date;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.negocio.dto.ConsultaDtoCUC;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.Pessoa;
import br.com.sicoob.capes.negocio.entidades.RelacionamentoPessoaBase;
import br.com.sicoob.capes.negocio.entidades.TipoRelacionamentoPessoa;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.RelacionamentoPessoa;

/**
 * Serviço para manutenção do cadastro de {@link RelacionamentoPessoa}
 * 
 * 24/08/2011
 * 
 * @author Rodrigo.Chaves
 */
public interface RelacionamentoPessoaServico extends
	EntidadeCadastroServico<RelacionamentoPessoa> {

	/**
	 * Pesquisar relacionamentos exercidos.
	 *
	 * @param criterios o valor de criterios
	 * @return ConsultaDto
	 */
	ConsultaDto<RelacionamentoPessoa> pesquisarRelacionamentosExercidos(
			ConsultaDto<RelacionamentoPessoa> criterios);

	/**
	 * Recupera relacionamentos com as seguintes características iguais:
	 * <ul>
	 * <li>são entre as mesmas pessoas</li>
	 * <li>possuem o mesmo tipo de relacionamento</li>
	 * <li>são da mesma instituição</li>
	 * </ul>
	 * 
	 * <p>
	 * ** IMPORTANTE! **
	 * </p>
	 * <p>
	 * Esta consulta <b>não</b> é paginada!!
	 * </p>
	 * 
	 * @param pessoa
	 *            a pessoa que possui o relacionamento
	 * @param pessoaRelacionada
	 *            a pessoa relacionada
	 * @param tipoRelacionamento
	 *            o tipo de relacionamento entre as pessoas
	 * @param idInstituicao
	 *            o ID da instituição das pessoas
	 */
	List<RelacionamentoPessoa> pesquisarRelacionamentosSemelhantes(
			Pessoa pessoa, Pessoa pessoaRelacionada,
			TipoRelacionamentoPessoa tipoRelacionamento, Integer idInstituicao);

	/**
	 * Método delegate que simplifica a chamada ao método de pesquisa, que
	 * retorna os relacionamentos de uma pessoa.
	 * 
	 * @see #pesquisar(ConsultaDto)
	 * @param idPessoa
	 *            o ID da {@link PessoaCompartilhamento} da qual se deseja obter os
	 *            relacionamentos.
	 * @param idInstituicao
	 *            o ID da {@link Instituicao} do usuário logado
	 * @return a lista dos relacionamentos da pessoa
	 */
	List<RelacionamentoPessoa> pesquisarRelacionamentosCedidos(
			Integer idPessoa, Integer idInstituicao) throws BancoobException;

	/**
	 * Obtém a data atual do movimento do produto CADASTRO DE CLIENTES DO SFN -
	 * CCS (ID 41)
	 * @param idInstituicao 
	 * 
	 * @return {@link RetornoDTO} com a data atual do movimento
	 */
	Date obterDataMovimentoCCS(Integer idInstituicao) throws BancoobException;

	/**
	 * {@inheritDoc}
	 */
	void excluirEntidade(RelacionamentoPessoa relacionamento) throws BancoobException;

	/**
	 * Recuperar relacionamentos incorporacao.
	 *
	 * @param criterios o valor de criterios
	 * @return ConsultaDto
	 * @throws BancoobException lança a exceção BancoobException
	 */
	ConsultaDto<RelacionamentoPessoa> recuperarRelacionamentosIncorporacao(
			ConsultaDto<RelacionamentoPessoa> criterios)
			throws BancoobException;

	/**
	 * {@inheritDoc}
	 */
	RelacionamentoPessoa incluir(RelacionamentoPessoa relacionamento) throws BancoobException;
	
	/**
	 * Incluir.
	 *
	 * @param relacionamento o valor de relacionamento
	 * @param incluirReverso o valor de incluir reverso
	 * @return RelacionamentoPessoa
	 * @throws BancoobException lança a exceção BancoobException
	 */
	RelacionamentoPessoa incluir(RelacionamentoPessoa relacionamento, Boolean incluirReverso) throws BancoobException;
	
	/**
	 * Pesquisa os relacionamentos vigentes por pessoa, instituição e tipo de relacionamento.
	 * @param relacionamento com os filtros
	 * @return lista com os relacionamentos vigentes
	 * @throws BancoobException
	 */
	List<RelacionamentoPessoa> pesquisarRelacionamentosVigentesPorFiltro(RelacionamentoPessoa relacionamento) throws BancoobException;
	
	/**
	 * O método Gerar relacionamento reverso.
	 *
	 * @param relacionamento o valor de relacionamento
	 * @throws BancoobException lança a exceção BancoobException
	 */
	void gerarRelacionamentoReverso(RelacionamentoPessoa relacionamento) throws BancoobException;
	
	/**
	 * O método Alterar relacionamento reverso.
	 *
	 * @param relacionamento o valor de relacionamento
	 * @throws BancoobException lança a exceção BancoobException
	 */
	void alterarRelacionamentoReverso(RelacionamentoPessoa relacionamento) throws BancoobException;
	
	/**
	 * O método Excluir relacionamento reverso.
	 *
	 * @param relacionamento o valor de relacionamento
	 * @throws BancoobException lança a exceção BancoobException
	 */
	void excluirRelacionamentoReverso(RelacionamentoPessoa relacionamento) throws BancoobException;
	
	/**
	 * @param pessoa
	 * @return
	 * @throws BancoobException
	 */
	void verificaPendenciasPessoaRelacionada(RelacionamentoPessoa relacionamento) throws BancoobException;
	
	/**
	 * @param relacionamento o valor de relacionamento
	 * @throws BancoobException
	 */
	Boolean validarTransicaoPessoaRelacionada(RelacionamentoPessoa relacionamento) throws BancoobException;
	
	/**
	 * Pesquisa o historico de relacionamentos cedidos por pessoa.
	 * 
	 * @param criterios
	 * @return
	 * @throws BancoobException
	 */
	List<RelacionamentoPessoaBase> listarHistoricoEspecifico(ConsultaDtoCUC<RelacionamentoPessoa> criterios) throws BancoobException;
	
}
