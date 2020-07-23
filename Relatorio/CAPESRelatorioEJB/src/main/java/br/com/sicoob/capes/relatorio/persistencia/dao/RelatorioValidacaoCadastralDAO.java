package br.com.sicoob.capes.relatorio.persistencia.dao;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.ValidacaoCadastral;
import br.com.sicoob.capes.relatorio.negocio.dto.RelatorioValidacaoCadastralDTO;
import br.com.sicoob.capes.relatorio.negocio.vo.RelatorioValidacaoCadastralAnaliticoVO;
import br.com.sicoob.capes.relatorio.negocio.vo.RelatorioValidacaoCadastralSinteticoVO;

/**
 * A Interface RelatorioValidacaoCadastralDAO.
 */
public interface RelatorioValidacaoCadastralDAO {
	
	/**
	 * Realiza a consulta do relatorio de validacao cadastral analitico
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	List<RelatorioValidacaoCadastralAnaliticoVO> obterDadosRelatorioAnalitico(RelatorioValidacaoCadastralDTO dto) throws BancoobException;
	
	/**
	 * Realiza a consulta do relatorio de validacao cadastral sintetico
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	List<RelatorioValidacaoCadastralSinteticoVO> obterDadosRelatorioSintetico(RelatorioValidacaoCadastralDTO dto) throws BancoobException;
	
	/**
	 * Consulta de dados de usuario envio e aprovacao, o ultimo realizado no cadastro da pessoa
	 * 
	 * @param idPessoa
	 * @return ValidacaoCadastral
	 * @throws BancoobException
	 */
	ValidacaoCadastral consultarUltimoEnvioAprovacao(Long idPessoa) throws BancoobException;
	
}