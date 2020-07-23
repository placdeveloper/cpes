/*
 * SICOOB
 * 
 * FonteRendaPessoaServicoEJB.java(br.com.sicoob.capes.api.negocio.servicos.ejb.FonteRendaPessoaServicoEJB)
 */
package br.com.sicoob.capes.api.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.FonteRendaPessoaServicoLocal;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.FonteRendaPessoaServicoRemote;
import br.com.sicoob.capes.api.negocio.vo.FonteRendaPessoaVO;
import br.com.sicoob.capes.api.persistencia.dao.FonteRendaPessoaDAO;

/**
 * The Class FonteRendaPessoaServicoEJB.
 */
@Stateless
@Local(FonteRendaPessoaServicoLocal.class)
@Remote(FonteRendaPessoaServicoRemote.class)
public class FonteRendaPessoaServicoEJB extends AbstractCAPESApiServicoPessoaEJB<FonteRendaPessoaVO> implements FonteRendaPessoaServicoRemote, FonteRendaPessoaServicoLocal {

	@Inject
	@Default
	private FonteRendaPessoaDAO fonteRendaPessoaDAO;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected FonteRendaPessoaDAO obterDAO() {
		return fonteRendaPessoaDAO;
	}

	public List<FonteRendaPessoaVO> listarFonteRendaPessoaPorIdPessoaLegadoIdInstituicao(Integer idPessoaLegado, Integer idInstituicao) throws BancoobException {
		return obterDAO().listarFonteRendaPessoaPorIdPessoaLegadoIdInstituicao(idPessoaLegado, idInstituicao);
	}

}