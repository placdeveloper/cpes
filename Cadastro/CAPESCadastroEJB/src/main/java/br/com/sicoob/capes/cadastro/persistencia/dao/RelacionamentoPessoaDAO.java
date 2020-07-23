/* 
 * Sicoob
 * RelacionamentoPessoaDAO.java 
 * Criado em: 24/08/2011
 */
package br.com.sicoob.capes.cadastro.persistencia.dao;


import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.negocio.dto.ConsultaDtoCUC;
import br.com.sicoob.capes.negocio.entidades.Pessoa;
import br.com.sicoob.capes.negocio.entidades.RelacionamentoPessoaBase;
import br.com.sicoob.capes.negocio.entidades.TipoRelacionamentoPessoa;
import br.com.sicoob.capes.negocio.entidades.vigente.RelacionamentoPessoa;

/**
 * Dao para {@link RelacionamentoPessoa}
 * 
 * 24/08/2011
 * @author Rodrigo.Chaves
 */
public interface RelacionamentoPessoaDAO extends EntidadeCadastroDaoIF<RelacionamentoPessoa> {

	/**
	 * Pesquisar relacionamentos exercidos.
	 *
	 * @param criterios o valor de criterios
	 * @return ConsultaDto
	 */
	ConsultaDto<RelacionamentoPessoa> pesquisarRelacionamentosExercidos(
			ConsultaDto<RelacionamentoPessoa> criterios);

	/**
	 * Obtém o relacionamento reverso, caso exista.
	 * 
	 * @param relacionamento
	 *            o relacionamento do qual se deseja obter o reverso
	 * @return o relacionamento reverso ou <code>null</code>
	 */
	RelacionamentoPessoa obterRelacionamentoReverso(RelacionamentoPessoa relacionamento);

	/**
	 * Recupera relacionamentos com as seguintes características iguais:
	 * <ul>
	 * <li>são entre as mesmas pessoas</li>
	 * <li>possuem o mesmo tipo de relacionamento</li>
	 * <li>são da mesma instituição</li>
	 * </ul>
	 * 
	 * <p>** IMPORTANTE! **</p> 
	 * <p>Esta consulta <b>não</b> é paginada!!</p>
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
	List<RelacionamentoPessoa> pesquisarRelacionamentosSemelhantes(Pessoa pessoa,
			Pessoa pessoaRelacionada, TipoRelacionamentoPessoa tipoRelacionamento,
			Integer idInstituicao);

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
	 * Pesquisa os relacionamentos vigentes por pessoa, instituição e tipo de relacionamento.
	 * @param relacionamentoPessoa com os filtros
	 * @return lista de relacionamentos vigentes
	 * @throws BancoobException
	 */
	List<RelacionamentoPessoa> pesquisarRelacionamentosVigentesPorFiltro(RelacionamentoPessoa relacionamentoPessoa) throws BancoobException;

	/**
	 * O método Atualizar relacionamento reverso.
	 *
	 * @param idRelacionamento o valor de id relacionamento
	 * @param idRelacionamentoReverso o valor de id relacionamento reverso
	 * @throws BancoobException lança a exceção BancoobException
	 */
	void atualizarRelacionamentoReverso(Long idRelacionamento,
			Long idRelacionamentoReverso)throws BancoobException;
	
	/**
	 * Pesquisa o historico de relacionamentos cedidos por pessoa.
	 * 
	 * @param criterios
	 * @return
	 * @throws BancoobException
	 */
	List<RelacionamentoPessoaBase> listarHistoricoEspecifico(
			ConsultaDtoCUC<RelacionamentoPessoa> criterios) throws BancoobException;
}
