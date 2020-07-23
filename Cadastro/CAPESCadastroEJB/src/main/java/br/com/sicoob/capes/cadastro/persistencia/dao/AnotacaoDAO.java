/*
 * SICOOB
 * 
 * AnotacaoDAO.java(br.com.sicoob.capes.cadastro.persistencia.dao.AnotacaoDAO)
 */
package br.com.sicoob.capes.cadastro.persistencia.dao;

import java.util.Date;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.cadastro.negocio.dto.ConsultaAnotacaoDTO;
import br.com.sicoob.capes.cadastro.negocio.dto.ConsultaAnotacaoSisbrDTO;
import br.com.sicoob.capes.cadastro.negocio.vo.SumarioAnotacaoVO;
import br.com.sicoob.capes.comum.negocio.dto.AnotacaoExternaDTO;
import br.com.sicoob.capes.negocio.entidades.Anotacao;
import br.com.sicoob.capes.negocio.entidades.AnotacaoSisbr;
import br.com.sicoob.capes.negocio.entidades.OrigemInformacao;
import br.com.sicoob.capes.negocio.entidades.TipoAnotacao;
import br.com.sicoob.capes.negocio.entidades.TipoBaixa;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * Interface para o DAO de anotações.
 * @author Erico.Junior
 */
public interface AnotacaoDAO extends CAPESCadastroCrudDaoIF<Anotacao> {

	/**
	 * Consulta as anotações para o filtro.
	 * @param filtro O filtro para a consulta.
	 * @return Lista de anotações para o filtro informado.
	 */
	List<Anotacao> listarAnotacoesPorFiltro(ConsultaAnotacaoDTO filtro);
	
	/**
	 * Recupera o sumário das anotações de uma pessoa.
	 * @param pessoa A pessoa da qual se deseja o sumário das anotações.
	 * @return A lista com os itens do sumário de anotações vigentes de uma pessoa.
	 */
	List<SumarioAnotacaoVO> listarSumarioAnotacoesVigentes(PessoaCompartilhamento pessoa);

	/**
	 * Lista as anotações a partir do filtro informado.
	 * @param criterios Os critérios utilizados na consulta.
	 * @return A lista de anotações para o filtro informado.
	 */
	List<AnotacaoSisbr> listarAnotacoesSisbr(ConsultaAnotacaoSisbrDTO criterios);

	/**
	 * Lista as anotações a partir do filtro informado para geração de relatório
	 * 
	 * @param criterios
	 *            os filtros
	 * @return A lista de anotações
	 */
	List<Anotacao> listarAnotacoesParaRelatorio(ConsultaAnotacaoDTO criterios)
			throws BancoobException;

	/**
	 * Consulta as anotacoes de um CPF/CNPJ
	 * @param codigoTipoAnotacao
	 * @return Se o CPF/CNPJ está bloqueado.
	 */
	boolean isCadastroReceitaRegular(PessoaCompartilhamento pessoaCompartilhamento) throws BancoobException;
	
	/**
	 * Baixa as anotações externas.
	 * @param anotacoes As anotações a serem baixadas.
	 * @return TODO
	 * @throws BancoobException Caso ocorra alguma exceção na alteração.
	 */
	List<Anotacao> obterAnotacoesExternasBaixa(PessoaCompartilhamento pessoaCompartilhamento, OrigemInformacao origemInformacao, AnotacaoExternaDTO dto) throws BancoobException;

	/**
	 * Alterar.
	 * 
	 * @param anotacoes
	 *            the anotacoes
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	void alterar(List<Anotacao> anotacoes) throws BancoobException;
	
	/**
	 * Verificar se a cooperativa pode utilizar o tipo de anotação
	 * 
	 * @param tipoAnotacao
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	boolean cooperativaPodeUtilizarTipoAnotacao(TipoAnotacao tipoAnotacao, Integer idInstituicao) throws BancoobException;
	
	/**
	 * Baixa as anotações da matriz e filiais.
	 * 
	 * @param filiais
	 * @param tipoAnotacao
	 * @param tipoBaixa
	 * @param dataHoraBaixa
	 * @param usuarioBaixa
	 * @throws BancoobException
	 */
	void baixarAnotacoesFiliais(List<Long> filiais, TipoAnotacao tipoAnotacao, TipoBaixa tipoBaixa, DateTimeDB dataHoraBaixa, String usuarioBaixa) throws BancoobException;
	
	/**
	 * Flexibiliza as anotações da matriz e filiais.
	 * 
	 * @param filiais
	 * @param dataAlteracao
	 * @param usuarioAlteracao
	 * @param flexibilizar
	 * @throws BancoobException
	 */
	void flexibilizarAnotacoesFiliais(List<Long> filiais, TipoAnotacao tipoAnotacao, DateTimeDB dataAlteracao, String usuarioAlteracao, boolean flexibilizar) throws BancoobException;

	/**
	 * 
	 * @param dataLimite
	 * @param codigoGrupoTipoAnotacao
	 * @param paginaAtual
	 * @param tamanhoPagina
	 * @return
	 */
	ConsultaDto<Anotacao> obterAnotacoesVencidasPorGrupoTipoAnotacao(Date dataLimite, Short codigoGrupoTipoAnotacao, int paginaAtual, int tamanhoPagina) throws BancoobException;

	/**
	 * Obtém as anotações que estão no grupo informado.
	 * 
	 * @param pc
	 * @param codigoGrupoTipoAnotacao
	 * @return
	 * @throws BancoobException
	 */
	List<Anotacao> obterAnotacoesPorGrupoTipoAnotacao(Long idPessoaCompartilhamento, Short codigoGrupoTipoAnotacao) throws BancoobException;
}
