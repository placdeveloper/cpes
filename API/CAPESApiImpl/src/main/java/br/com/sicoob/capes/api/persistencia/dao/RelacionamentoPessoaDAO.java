package br.com.sicoob.capes.api.persistencia.dao;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.api.negocio.filtros.FiltroConsultaAPIAbstrato;
import br.com.sicoob.capes.api.negocio.vo.DadosRegistroVO;
import br.com.sicoob.capes.api.negocio.vo.RelacionamentoPessoaPoderVO;
import br.com.sicoob.capes.api.negocio.vo.RelacionamentoPessoaVO;

/**
 * A Interface RelacionamentoPessoaDAO.
 */
public interface RelacionamentoPessoaDAO extends CAPESApiDaoIF<RelacionamentoPessoaVO> {

	/**
	 * Obter relacionamentos conjuges por pessoa instituicao.
	 *
	 * @param idPessoa o valor de id pessoa
	 * @param idInstituicao o valor de id instituicao
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	List<RelacionamentoPessoaVO> obterRelacionamentosConjugesPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException;

	/**
	 * Obter relacionamentos quadro societario por pessoa instituicao.
	 *
	 * @param idPessoa o valor de id pessoa
	 * @param idInstituicao o valor de id instituicao
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	List<RelacionamentoPessoaVO> obterRelacionamentosQuadroSocietarioPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException;

	/**
	 * Obter relacionamentos por pessoa instituicao tipo.
	 *
	 * @param idPessoa o valor de id pessoa
	 * @param idInstituicao o valor de id instituicao
	 * @param codigoTipoRelacionamento o valor de codigo tipo relacionamento
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	List<RelacionamentoPessoaVO> obterRelacionamentosPorPessoaInstituicaoTipo(Integer idPessoa, Integer idInstituicao, Short codigoTipoRelacionamento) throws BancoobException;

	/**
	 * Obter poderes.
	 *
	 * @param idRelacionamento o valor de id relacionamento
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	List<RelacionamentoPessoaPoderVO> obterPoderes(Long idRelacionamento) throws BancoobException;

	/**
	 * Obter dados registro.
	 *
	 * @param idRelacionamento o valor de id relacionamento
	 * @return DadosRegistroVO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	DadosRegistroVO obterDadosRegistro(Long idRelacionamento) throws BancoobException;
	
	/**
	 * Obter Lista de relacionamentos Compartilhados por tipo.
	 * 
	 * @param idPessoa
	 * @param codigoTipoRelacionamento
	 * @param isCompartilhado
	 * @return
	 * @throws BancoobException
	 */
	List<RelacionamentoPessoaVO> obterByPessoaTipoCompartilhado(Integer idPessoa, Short codigoTipoRelacionamento, Integer idInstituicao) throws BancoobException;
	
	/**
	 * 
	 * @param criterios
	 * @return
	 * @throws BancoobException
	 */
	ConsultaDto<RelacionamentoPessoaVO> pesquisarPaginadoEspecifico(ConsultaDto<FiltroConsultaAPIAbstrato> criterios) throws BancoobException;
	
	/**
	 * 
	 * @param filtro
	 * @return
	 * @throws BancoobException
	 */
	List<RelacionamentoPessoaVO> pesquisarEspecifico(FiltroConsultaAPIAbstrato filtro) throws BancoobException;

}