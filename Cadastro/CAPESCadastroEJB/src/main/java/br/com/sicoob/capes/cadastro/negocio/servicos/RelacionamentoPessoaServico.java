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
 * Servi�o para manuten��o do cadastro de {@link RelacionamentoPessoa}
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
	 * Recupera relacionamentos com as seguintes caracter�sticas iguais:
	 * <ul>
	 * <li>s�o entre as mesmas pessoas</li>
	 * <li>possuem o mesmo tipo de relacionamento</li>
	 * <li>s�o da mesma institui��o</li>
	 * </ul>
	 * 
	 * <p>
	 * ** IMPORTANTE! **
	 * </p>
	 * <p>
	 * Esta consulta <b>n�o</b> � paginada!!
	 * </p>
	 * 
	 * @param pessoa
	 *            a pessoa que possui o relacionamento
	 * @param pessoaRelacionada
	 *            a pessoa relacionada
	 * @param tipoRelacionamento
	 *            o tipo de relacionamento entre as pessoas
	 * @param idInstituicao
	 *            o ID da institui��o das pessoas
	 */
	List<RelacionamentoPessoa> pesquisarRelacionamentosSemelhantes(
			Pessoa pessoa, Pessoa pessoaRelacionada,
			TipoRelacionamentoPessoa tipoRelacionamento, Integer idInstituicao);

	/**
	 * M�todo delegate que simplifica a chamada ao m�todo de pesquisa, que
	 * retorna os relacionamentos de uma pessoa.
	 * 
	 * @see #pesquisar(ConsultaDto)
	 * @param idPessoa
	 *            o ID da {@link PessoaCompartilhamento} da qual se deseja obter os
	 *            relacionamentos.
	 * @param idInstituicao
	 *            o ID da {@link Instituicao} do usu�rio logado
	 * @return a lista dos relacionamentos da pessoa
	 */
	List<RelacionamentoPessoa> pesquisarRelacionamentosCedidos(
			Integer idPessoa, Integer idInstituicao) throws BancoobException;

	/**
	 * Obt�m a data atual do movimento do produto CADASTRO DE CLIENTES DO SFN -
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
	 * @throws BancoobException lan�a a exce��o BancoobException
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
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	RelacionamentoPessoa incluir(RelacionamentoPessoa relacionamento, Boolean incluirReverso) throws BancoobException;
	
	/**
	 * Pesquisa os relacionamentos vigentes por pessoa, institui��o e tipo de relacionamento.
	 * @param relacionamento com os filtros
	 * @return lista com os relacionamentos vigentes
	 * @throws BancoobException
	 */
	List<RelacionamentoPessoa> pesquisarRelacionamentosVigentesPorFiltro(RelacionamentoPessoa relacionamento) throws BancoobException;
	
	/**
	 * O m�todo Gerar relacionamento reverso.
	 *
	 * @param relacionamento o valor de relacionamento
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	void gerarRelacionamentoReverso(RelacionamentoPessoa relacionamento) throws BancoobException;
	
	/**
	 * O m�todo Alterar relacionamento reverso.
	 *
	 * @param relacionamento o valor de relacionamento
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	void alterarRelacionamentoReverso(RelacionamentoPessoa relacionamento) throws BancoobException;
	
	/**
	 * O m�todo Excluir relacionamento reverso.
	 *
	 * @param relacionamento o valor de relacionamento
	 * @throws BancoobException lan�a a exce��o BancoobException
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
