/*
 * SICOOB
 * 
 * FonteRendaPessoaServico.java(br.com.sicoob.capes.api.negocio.servicos.FonteRendaPessoaServico)
 */
package br.com.sicoob.capes.api.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.FonteRendaPessoaVO;

/**
 * @author Lucas.Borges
 */
public interface FonteRendaPessoaServico extends CAPESApiServicoPessoa<FonteRendaPessoaVO>{

	List<FonteRendaPessoaVO> listarFonteRendaPessoaPorIdPessoaLegadoIdInstituicao(Integer idPessoaLegado, Integer idInstituicao) throws BancoobException;
}
