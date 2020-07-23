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
 * Interface para o DAO de anota��es.
 * @author Erico.Junior
 */
public interface AnotacaoDAO extends CAPESCadastroCrudDaoIF<Anotacao> {

	/**
	 * Consulta as anota��es para o filtro.
	 * @param filtro O filtro para a consulta.
	 * @return Lista de anota��es para o filtro informado.
	 */
	List<Anotacao> listarAnotacoesPorFiltro(ConsultaAnotacaoDTO filtro);
	
	/**
	 * Recupera o sum�rio das anota��es de uma pessoa.
	 * @param pessoa A pessoa da qual se deseja o sum�rio das anota��es.
	 * @return A lista com os itens do sum�rio de anota��es vigentes de uma pessoa.
	 */
	List<SumarioAnotacaoVO> listarSumarioAnotacoesVigentes(PessoaCompartilhamento pessoa);

	/**
	 * Lista as anota��es a partir do filtro informado.
	 * @param criterios Os crit�rios utilizados na consulta.
	 * @return A lista de anota��es para o filtro informado.
	 */
	List<AnotacaoSisbr> listarAnotacoesSisbr(ConsultaAnotacaoSisbrDTO criterios);

	/**
	 * Lista as anota��es a partir do filtro informado para gera��o de relat�rio
	 * 
	 * @param criterios
	 *            os filtros
	 * @return A lista de anota��es
	 */
	List<Anotacao> listarAnotacoesParaRelatorio(ConsultaAnotacaoDTO criterios)
			throws BancoobException;

	/**
	 * Consulta as anotacoes de um CPF/CNPJ
	 * @param codigoTipoAnotacao
	 * @return Se o CPF/CNPJ est� bloqueado.
	 */
	boolean isCadastroReceitaRegular(PessoaCompartilhamento pessoaCompartilhamento) throws BancoobException;
	
	/**
	 * Baixa as anota��es externas.
	 * @param anotacoes As anota��es a serem baixadas.
	 * @return TODO
	 * @throws BancoobException Caso ocorra alguma exce��o na altera��o.
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
	 * Verificar se a cooperativa pode utilizar o tipo de anota��o
	 * 
	 * @param tipoAnotacao
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	boolean cooperativaPodeUtilizarTipoAnotacao(TipoAnotacao tipoAnotacao, Integer idInstituicao) throws BancoobException;
	
	/**
	 * Baixa as anota��es da matriz e filiais.
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
	 * Flexibiliza as anota��es da matriz e filiais.
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
	 * Obt�m as anota��es que est�o no grupo informado.
	 * 
	 * @param pc
	 * @param codigoGrupoTipoAnotacao
	 * @return
	 * @throws BancoobException
	 */
	List<Anotacao> obterAnotacoesPorGrupoTipoAnotacao(Long idPessoaCompartilhamento, Short codigoGrupoTipoAnotacao) throws BancoobException;
}
