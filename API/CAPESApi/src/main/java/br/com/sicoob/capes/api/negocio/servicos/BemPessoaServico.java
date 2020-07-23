/*
 * SICOOB
 * 
 * BemPessoaServico.java(br.com.sicoob.capes.api.negocio.servicos.BemPessoaServico)
 */
package br.com.sicoob.capes.api.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.BemPessoaVO;

/**
 * @author Lucas.Borges
 */
public interface BemPessoaServico extends CAPESApiServicoPessoa<BemPessoaVO>{
	
	void bloquearBem(Short codigoUtilizaInformacao, Long idBemPessoa) throws BancoobException;

}
