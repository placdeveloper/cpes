/*
 * SICOOB
 * 
 * TelefonePessoaServico.java(br.com.sicoob.capes.api.negocio.servicos.TelefonePessoaServico)
 */
package br.com.sicoob.capes.api.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.TelefonePessoaVO;

/**
 * @author Lucas.Borges
 */
public interface TelefonePessoaServico extends CAPESApiServicoPessoa<TelefonePessoaVO>{

	/**
	 * Incluir telefone.
	 * 
	 * @param dto
	 *            the dto
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	void incluirTelefone(TelefonePessoaVO dto) throws BancoobException;
	
	List<TelefonePessoaVO> listarTelefonePessoaPorIdPessoaLegadoIdInstituicao(Integer idPessoaLegado, Integer idInstituicao) throws BancoobException;

}
