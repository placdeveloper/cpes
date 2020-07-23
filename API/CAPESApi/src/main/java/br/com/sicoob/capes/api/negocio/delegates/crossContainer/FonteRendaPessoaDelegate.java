/*
 * SICOOB
 * 
 * FonteRendaPessoaDelegate.java(br.com.sicoob.capes.api.negocio.delegates.FonteRendaPessoaDelegate)
 */
package br.com.sicoob.capes.api.negocio.delegates.crossContainer;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.delegates.interfaces.IFonteRendaPessoaDelegate;
import br.com.sicoob.capes.api.negocio.servicos.FonteRendaPessoaServico;
import br.com.sicoob.capes.api.negocio.vo.FonteRendaPessoaVO;

/**
 * @author Lucas.Borges
 */
public class FonteRendaPessoaDelegate extends AbstractCAPESApiPessoaDelegate<FonteRendaPessoaVO, FonteRendaPessoaServico> implements IFonteRendaPessoaDelegate {
	
	/**
	 * 
	 */
	protected FonteRendaPessoaDelegate() {
	}

	/**
	 * 
	 * @return
	 */
	public static FonteRendaPessoaDelegate getInstance() {
		return valueOf(FonteRendaPessoaDelegate.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected FonteRendaPessoaServico localizarServico() {
		return getLocator().localizarFonteRendaPessoaServico();
	}

	public List<FonteRendaPessoaVO> listarFonteRendaPessoaPorIdPessoaLegadoIdInstituicao(Integer idPessoaLegado, Integer idInstituicao) throws BancoobException {
		return getServico().listarFonteRendaPessoaPorIdPessoaLegadoIdInstituicao(idPessoaLegado, idInstituicao);
	}
}
