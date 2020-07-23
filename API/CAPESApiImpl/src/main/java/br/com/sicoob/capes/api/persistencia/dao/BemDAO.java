package br.com.sicoob.capes.api.persistencia.dao;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.BemProprietarioVO;
import br.com.sicoob.capes.api.negocio.vo.BemVO;
import br.com.sicoob.capes.api.negocio.vo.TipoBemVO;
import br.com.sicoob.capes.api.negocio.vo.TipoClassificacaoBemVO;

public interface BemDAO extends CAPESApiDao {

	/**
	 * Obtém as informações do bem por idPessoa e idInstituicao
	 * 
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	List<BemVO> obterPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException;

	/**
	 * Obtém as informações do bem por ID.
	 * 
	 * @param idBem
	 * @return
	 * @throws BancoobException
	 */
	BemVO obterPorIdBem(Long idBem) throws BancoobException;

	/**
	 * Obtém as classificações do bem.
	 * 
	 * @return
	 * @throws BancoobException
	 */
	List<TipoClassificacaoBemVO> obterClassificacoesBem() throws BancoobException;

	/**
	 * Obtém os tipos de bem por tipo de classificação
	 * 
	 * @param codigoTipoClassificacaoBem
	 * @return
	 */
	List<TipoBemVO> obterTiposBemPorClassificacao(boolean bemImovel) throws BancoobException;
	
	/**
	 * Obtém a lista dos proprietários do bem.
	 * 
	 * @param idBem
	 * @return
	 * @throws BancoobException
	 */
	List<BemProprietarioVO> obterProprietarios(Long idBem) throws BancoobException;

}