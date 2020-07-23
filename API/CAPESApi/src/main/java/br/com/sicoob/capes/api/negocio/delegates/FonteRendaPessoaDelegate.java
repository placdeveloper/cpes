/*
 * SICOOB
 * 
 * FonteRendaPessoaDelegate.java(br.com.sicoob.capes.api.negocio.delegates.FonteRendaPessoaDelegate)
 */
package br.com.sicoob.capes.api.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.delegates.interfaces.IFonteRendaPessoaDelegate;
import br.com.sicoob.capes.api.negocio.servicos.FonteRendaPessoaServico;
import br.com.sicoob.capes.api.negocio.servicos.locator.CAPESApiServiceLocator;
import br.com.sicoob.capes.api.negocio.vo.FonteRendaPessoaVO;

/**
 * @author Lucas.Borges
 */
public class FonteRendaPessoaDelegate extends AbstractCAPESApiPessoaDelegate<FonteRendaPessoaVO, FonteRendaPessoaServico> implements IFonteRendaPessoaDelegate {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected FonteRendaPessoaServico localizarServico() {
		return CAPESApiServiceLocator.getInstance().localizarFonteRendaPessoaServico();
	}

	public List<FonteRendaPessoaVO> listarFonteRendaPessoaPorIdPessoaLegadoIdInstituicao(Integer idPessoaLegado, Integer idInstituicao) throws BancoobException {
		return getServico().listarFonteRendaPessoaPorIdPessoaLegadoIdInstituicao(idPessoaLegado, idInstituicao);
	}
}
