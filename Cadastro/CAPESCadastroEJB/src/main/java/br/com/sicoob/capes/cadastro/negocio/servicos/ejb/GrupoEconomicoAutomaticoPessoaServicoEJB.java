package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.GrupoEconomicoAutomaticoPessoaServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.GrupoEconomicoAutomaticoPessoaServicoRemote;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.GrupoEconomicoManualPessoaServicoLocal;
import br.com.sicoob.capes.cadastro.persistencia.dao.GrupoEconomicoAutomaticoPessoaDAO;
import br.com.sicoob.capes.negocio.entidades.GrupoEconomicoAutomaticoPessoa;
import br.com.sicoob.capes.negocio.entidades.GrupoEconomicoManualPessoa;
import br.com.sicoob.capes.negocio.entidades.GrupoEconomicoNovo;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * EJB contendo servicos relacionados a GrupoEconomicoAutomaticoPessoa.
 */
@Stateless
@Remote(GrupoEconomicoAutomaticoPessoaServicoRemote.class)
@Local(GrupoEconomicoAutomaticoPessoaServicoLocal.class)
public class GrupoEconomicoAutomaticoPessoaServicoEJB extends CAPESCadastroCrudServicoEJB<GrupoEconomicoAutomaticoPessoa>
		implements GrupoEconomicoAutomaticoPessoaServicoRemote, GrupoEconomicoAutomaticoPessoaServicoLocal {

	@Inject
	@Default
	private GrupoEconomicoAutomaticoPessoaDAO grupoEconomicoAutomaticoPessoaDAO;

	@EJB
	private GrupoEconomicoManualPessoaServicoLocal grupoEconomicoManualPessoaServico;

	@Override
	public void alterar(GrupoEconomicoAutomaticoPessoa objeto) throws BancoobException {
		List<GrupoEconomicoManualPessoa> listaManuais = listarGrupoEconomicoManual(objeto.getPessoaCompartilhamento());
		for (GrupoEconomicoManualPessoa pessoaManual : listaManuais) {
			if (pessoaManual.getGrupoEconomicoAutomatico() != null
					&& !pessoaManual.getGrupoEconomicoAutomatico().getId().equals(objeto.getGrupoEconomico().getId())) {
				pessoaManual.setGrupoEconomicoAutomatico(objeto.getGrupoEconomico());
				grupoEconomicoManualPessoaServico.alterar(pessoaManual);
			}
		}
		super.alterar(objeto);
	}

	/**
	 * {@inheritDoc}
	 */
	public void excluirEntidade(GrupoEconomicoAutomaticoPessoa objeto) throws BancoobException {
		List<GrupoEconomicoManualPessoa> listaManuais = listarGrupoEconomicoManual(objeto.getPessoaCompartilhamento(), objeto.getGrupoEconomico());
		for (GrupoEconomicoManualPessoa pessoaManual : listaManuais) {
			pessoaManual.setGrupoEconomicoAutomatico(null);
			grupoEconomicoManualPessoaServico.alterar(pessoaManual);
		}
		super.excluir(objeto.getId());
	}

	/**
	 * {@inheritDoc}
	 */
	public GrupoEconomicoAutomaticoPessoa incluir(GrupoEconomicoAutomaticoPessoa objeto) throws BancoobException {
		List<GrupoEconomicoManualPessoa> listaManuais = listarGrupoEconomicoManual(objeto.getPessoaCompartilhamento());
		for (GrupoEconomicoManualPessoa pessoaManual : listaManuais) {
			pessoaManual.setGrupoEconomicoAutomatico(objeto.getGrupoEconomico());
			grupoEconomicoManualPessoaServico.alterar(pessoaManual);
		}
		return super.incluir(objeto);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected GrupoEconomicoAutomaticoPessoaDAO getDAO() {
		return grupoEconomicoAutomaticoPessoaDAO;
	}

	/**
	 * 
	 * @param pessoaCompartilhamento
	 * @return
	 * @throws BancoobException
	 */
	private List<GrupoEconomicoManualPessoa> listarGrupoEconomicoManual(PessoaCompartilhamento pessoaCompartilhamento) throws BancoobException {
		return listarGrupoEconomicoManual(pessoaCompartilhamento, null);
	}

	/**
	 * 
	 * @param pessoaCompartilhamento
	 * @param grupoEconomicoAutomatico
	 * @return
	 * @throws BancoobException
	 */
	private List<GrupoEconomicoManualPessoa> listarGrupoEconomicoManual(PessoaCompartilhamento pessoaCompartilhamento,
			GrupoEconomicoNovo grupoEconomicoAutomatico) throws BancoobException {
		GrupoEconomicoManualPessoa filtro = new GrupoEconomicoManualPessoa();
		filtro.setPessoaCompartilhamento(pessoaCompartilhamento);
		filtro.setGrupoEconomicoAutomatico(grupoEconomicoAutomatico);
		ConsultaDto<GrupoEconomicoManualPessoa> criterios = new ConsultaDto<>();
		criterios.setFiltro(filtro);
		return grupoEconomicoManualPessoaServico.listar(criterios);
	}

}
