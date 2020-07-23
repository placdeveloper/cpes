/* 
 * Sicoob
 * RelacionamentoPessoaServicoEJB.java 
 * Criado em: 28/10/2011
 */
package br.com.sicoob.capes.replicacao.negocio.servicos.ejb;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.legado.PessoaFisica;
import br.com.sicoob.capes.negocio.entidades.legado.RelacionamentoPessoa;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.PessoaServicoLocal;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.RelacionamentoPessoaServicoLocal;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.RelacionamentoPessoaServicoRemote;
import br.com.sicoob.capes.replicacao.persistencia.dao.RelacionamentoPessoaDAO;

/**
 * Serviço utilizado para replicação de relacionamentos.
 *
 * 28/10/2011
 * @author Rodrigo.Chaves
 */
@Stateless
@Local(RelacionamentoPessoaServicoLocal.class)
@Remote(RelacionamentoPessoaServicoRemote.class)
public class RelacionamentoPessoaServicoEJB extends
		EntidadeReplicavelServicoEJB<RelacionamentoPessoa> implements RelacionamentoPessoaServicoRemote, RelacionamentoPessoaServicoLocal {

	@Inject
	@Default
	private RelacionamentoPessoaDAO dao;
	
	/** O atributo pessoaServico. */
	@EJB(mappedName = "capes/replicacao/PessoaServico")
	private PessoaServicoLocal pessoaServico;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void alterar(RelacionamentoPessoa relacionamento, Integer idInstituicao) throws BancoobException {
		
		super.alterar(relacionamento, idInstituicao);
		if (relacionamento.isTipoConjuge()) {
			PessoaFisica pessoa = (PessoaFisica) relacionamento.getPessoa();
			PessoaFisica conjuge = (PessoaFisica) relacionamento.getPessoaRelacionada();
			atualizarConjuge(pessoa, idInstituicao, conjuge.getNome(), conjuge
					.getNumeroCgcCpf(), conjuge.getCodigoProfissao());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void excluir(RelacionamentoPessoa relacionamento, Integer idInstituicao) throws BancoobException {
		
		super.excluir(relacionamento, idInstituicao);
		if (relacionamento.isTipoConjuge()) {
			PessoaFisica pessoa = (PessoaFisica) relacionamento.getPessoa();
			atualizarConjuge(pessoa, idInstituicao, null, null, null);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RelacionamentoPessoa incluir(RelacionamentoPessoa relacionamento, Integer idInstituicao)
			throws BancoobException {
		
		relacionamento = super.incluir(relacionamento, idInstituicao);
		if (relacionamento.isTipoConjuge()) {
			PessoaFisica pessoa = (PessoaFisica) relacionamento.getPessoa();
			PessoaFisica conjuge = (PessoaFisica) relacionamento.getPessoaRelacionada();
			atualizarConjuge(pessoa, idInstituicao, conjuge.getNome(), conjuge
					.getNumeroCgcCpf(), conjuge.getCodigoProfissao());
		}
		return relacionamento;
	}

	/**
	 * O método Atualizar conjuge.
	 *
	 * @param pessoa o valor de pessoa
	 * @param idInstituicao o valor de id instituicao
	 * @param nomeConjuge o valor de nome conjuge
	 * @param cpfConjuge o valor de cpf conjuge
	 * @param codigoProfissaoConjuge o valor de codigo profissao conjuge
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void atualizarConjuge(PessoaFisica pessoa, Integer idInstituicao, String nomeConjuge,
			String cpfConjuge, Integer codigoProfissaoConjuge) throws BancoobException {

		pessoa.setNomeConjuge(nomeConjuge);
		pessoa.setCpfConjuge(cpfConjuge);
		pessoa.setCodigoProfissaoConjuge(codigoProfissaoConjuge);
		this.pessoaServico.atualizarConjuge(pessoa, idInstituicao);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected RelacionamentoPessoaDAO getDAO() {
		return dao;
	}

	/**
	 * Define o valor de pessoaServico.
	 *
	 * @param servico o novo valor de pessoaServico
	 */
	public void setPessoaServico(PessoaServicoLocal servico) {
		this.pessoaServico = servico;
	}

}
