package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.GrupoEconomicoPessoaServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.GrupoEconomicoPessoaServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.GrupoEconomicoPessoaDAO;
import br.com.sicoob.capes.negocio.entidades.GrupoEconomico;
import br.com.sicoob.capes.negocio.entidades.vigente.GrupoEconomicoPessoa;

/**
 * EJB contendo servicos relacionados a GrupoEconomicoPessoa.
 */
@Stateless
@Remote(GrupoEconomicoPessoaServicoRemote.class)
@Local(GrupoEconomicoPessoaServicoLocal.class)
public class GrupoEconomicoPessoaServicoEJB extends CAPESCadastroCrudServicoEJB<GrupoEconomicoPessoa> implements GrupoEconomicoPessoaServicoRemote,
		GrupoEconomicoPessoaServicoLocal {

	@Inject
	@Default
	protected GrupoEconomicoPessoaDAO dao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected GrupoEconomicoPessoaDAO getDAO() {
		return dao;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<GrupoEconomicoPessoa> pesquisarPorPessoaInstituicao(Integer... idsPessoaInstituicao) throws BancoobException {

		return getDAO().pesquisarPorPessoaInstituicao(idsPessoaInstituicao);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void atualizarGrupo(GrupoEconomicoPessoa grupoEconomicoPessoa, GrupoEconomico grupo, String idUsuario) throws BancoobException {
		grupoEconomicoPessoa.setGrupoEconomico(grupo);
		grupoEconomicoPessoa.setIdUsuarioAprovacao(idUsuario);
		alterar(grupoEconomicoPessoa);
	}

	@Override
	public Long pesquisarNumeroRegistros(Integer idGrupo) throws BancoobException {
		return getDAO().pesquisarNumeroRegistros(idGrupo);
	}
}
