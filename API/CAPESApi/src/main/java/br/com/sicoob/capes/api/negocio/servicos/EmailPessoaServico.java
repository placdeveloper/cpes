/*
 * SICOOB
 * 
 * EmailPessoaServico.java(br.com.sicoob.capes.api.negocio.servicos.EmailPessoaServico)
 */
package br.com.sicoob.capes.api.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.EmailPessoaVO;

/**
 * @author Lucas.Borges
 */
public interface EmailPessoaServico extends CAPESApiServicoPessoa<EmailPessoaVO>{

	/**
	 * Incluir email.
	 * 
	 * @param email
	 *            the email
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	void incluirEmail(EmailPessoaVO email) throws BancoobException;
	
	List<EmailPessoaVO> listarEmaisPessoaPorIdPessoaLegadoIdInstituicao(Integer idPessoaLegado, Integer idInstituicao) throws BancoobException;
}
