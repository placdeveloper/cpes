/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.servicos.ejb;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.legado.Pessoa;
import br.com.sicoob.capes.negocio.entidades.legado.historico.HistoricoAlteracaoCnpjCpf;
import br.com.sicoob.capes.negocio.entidades.legado.pk.HistoricoAlteracaoCnpjCpfPK;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.HistoricoAlteracaoCnpjCpfServicoLocal;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.HistoricoAlteracaoCnpjCpfServicoRemote;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.PessoaServicoLocal;
import br.com.sicoob.capes.replicacao.persistencia.dao.EntidadeReplicavelDaoIF;
import br.com.sicoob.capes.replicacao.persistencia.dao.HistoricoAlteracaoCnpjCpfDAO;

/**
 * Serviço utilizado para o histórico de alteração de cpf/cnpj
 * 
 * @author Erico.Junior
 */
@Stateless
@Local( { HistoricoAlteracaoCnpjCpfServicoLocal.class })
@Remote( { HistoricoAlteracaoCnpjCpfServicoRemote.class })
public class HistoricoAlteracaoCnpjCpfServicoEJB extends
		EntidadeReplicavelServicoEJB<HistoricoAlteracaoCnpjCpf> implements
		HistoricoAlteracaoCnpjCpfServicoRemote, HistoricoAlteracaoCnpjCpfServicoLocal {

	@Inject
	@Default
	private transient HistoricoAlteracaoCnpjCpfDAO historicoDAO;

	/** O atributo servicoPessoa. */
	@EJB(mappedName = "capes/replicacao/PessoaServico")
	private PessoaServicoLocal servicoPessoa;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public HistoricoAlteracaoCnpjCpf incluir(HistoricoAlteracaoCnpjCpf historico,
			Integer idInstituicao) throws BancoobException {

		HistoricoAlteracaoCnpjCpfPK chave = historico.getPk();
		Pessoa pessoa = chave.getPessoa();

		servicoPessoa.alterarCpfCnpj(pessoa, idInstituicao);
		return super.incluir(historico, idInstituicao);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected EntidadeReplicavelDaoIF<HistoricoAlteracaoCnpjCpf> getDAO() {
		return historicoDAO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Serializable obterIdSQL(HistoricoAlteracaoCnpjCpf historico, Integer idInstituicao)
			throws BancoobException {
		return historico.getIdSQL();
	}

}
