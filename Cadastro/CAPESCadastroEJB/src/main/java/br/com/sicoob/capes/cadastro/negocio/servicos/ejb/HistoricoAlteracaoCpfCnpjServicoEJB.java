/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.HistoricoAlteracaoCpfCnpjServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.HistoricoAlteracaoCpfCnpjServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.CAPESCadastroCrudDaoIF;
import br.com.sicoob.capes.cadastro.persistencia.dao.HistoricoAlteracaoCpfCnpjDAO;
import br.com.sicoob.capes.negocio.entidades.HistoricoAlteracaoCpfCnpj;

/**
 * Servico utilizado para o historico de alteracoes de cpf/cnpj
 * 
 * @author Erico.Junior
 */
@Stateless
@Local( { HistoricoAlteracaoCpfCnpjServicoLocal.class })
@Remote( { HistoricoAlteracaoCpfCnpjServicoRemote.class })
public class HistoricoAlteracaoCpfCnpjServicoEJB extends
		CAPESCadastroCrudServicoEJB<HistoricoAlteracaoCpfCnpj> implements
		HistoricoAlteracaoCpfCnpjServicoRemote, HistoricoAlteracaoCpfCnpjServicoLocal {

	@Inject
	@Default
	protected HistoricoAlteracaoCpfCnpjDAO historicoDAO;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected CAPESCadastroCrudDaoIF<HistoricoAlteracaoCpfCnpj> getDAO() {
		return historicoDAO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public HistoricoAlteracaoCpfCnpj incluir(HistoricoAlteracaoCpfCnpj historico)
			throws BancoobException {
		
		historico.setDataHoraAlteracao(new DateTimeDB());
		historico.setIdUsuario(recuperarLoginSemDominio());
		return super.incluir(historico);
	}

}
