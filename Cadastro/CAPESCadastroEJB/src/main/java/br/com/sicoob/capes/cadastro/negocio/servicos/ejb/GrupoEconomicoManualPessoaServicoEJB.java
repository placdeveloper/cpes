package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.GrupoEconomicoManualPessoaServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.GrupoEconomicoManualPessoaServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.GrupoEconomicoManualPessoaDAO;
import br.com.sicoob.capes.negocio.entidades.GrupoEconomicoManualPessoa;

/**
 * EJB contendo servicos relacionados a GrupoEconomicoManualPessoa.
 */
@Stateless
@Remote(GrupoEconomicoManualPessoaServicoRemote.class)
@Local(GrupoEconomicoManualPessoaServicoLocal.class)
public class GrupoEconomicoManualPessoaServicoEJB extends CAPESCadastroCrudServicoEJB<GrupoEconomicoManualPessoa>
		implements GrupoEconomicoManualPessoaServicoRemote, GrupoEconomicoManualPessoaServicoLocal {

	@Inject
	@Default
	private GrupoEconomicoManualPessoaDAO grupoEconomicoManualPessoaDAO;

	/**
	 * {@inheritDoc}
	 */
	public void excluirEntidade(GrupoEconomicoManualPessoa objeto) throws BancoobException {
		GrupoEconomicoManualPessoa novo = obter(objeto.getId());
		novo.setMotivoExclusao(objeto.getMotivoExclusao());
		super.excluirEntidade(novo);
	}

	/**
	 * {@inheritDoc}
	 */
	protected GrupoEconomicoManualPessoaDAO getDAO() {
		return grupoEconomicoManualPessoaDAO;
	}

}
