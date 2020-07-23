/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.servicos.ejb;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.negocio.entidades.legado.AvaliacaoFinanceira;
import br.com.sicoob.capes.negocio.entidades.legado.Cliente;
import br.com.sicoob.capes.negocio.entidades.legado.Cooperativa;
import br.com.sicoob.capes.negocio.entidades.legado.CooperativaPessoa;
import br.com.sicoob.capes.negocio.entidades.legado.Pessoa;
import br.com.sicoob.capes.negocio.entidades.legado.PessoaFisica;
import br.com.sicoob.capes.negocio.entidades.legado.PessoaJuridica;
import br.com.sicoob.capes.negocio.entidades.legado.Telefone;
import br.com.sicoob.capes.negocio.entidades.legado.pk.CooperativaPK;
import br.com.sicoob.capes.negocio.entidades.legado.pk.CooperativaPessoaPK;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.AvaliacaoFinanceiraServicoLocal;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.ClienteServicoLocal;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.HistoricoCedenteServicoLocal;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.PessoaServicoLocal;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.PessoaServicoRemote;
import br.com.sicoob.capes.replicacao.persistencia.dao.PessoaDAO;

/**
 * Serviço utilizado na replicação de pessoas.
 * 
 * @author Erico.Junior
 */
@Stateless
@Local( { PessoaServicoLocal.class })
@Remote( { PessoaServicoRemote.class })
public class PessoaServicoEJB extends EntidadeReplicavelServicoEJB<Pessoa> implements PessoaServicoRemote, PessoaServicoLocal {

	@Inject
	@Default
	private PessoaDAO pessoaDAO;
	
	/** O atributo servicoHistoricoCedente. */
	@EJB(mappedName = "capes/replicacao/HistoricoCedenteServico")
	private HistoricoCedenteServicoLocal servicoHistoricoCedente;
	
	/** O atributo servicoCliente. */
	@EJB(mappedName = "capes/replicacao/ClienteServico")
	private ClienteServicoLocal servicoCliente;
	
	/** O atributo servicoAvaliacao. */
	@EJB(mappedName = "capes/replicacao/AvaliacaoFinanceiraServico")
	private AvaliacaoFinanceiraServicoLocal servicoAvaliacao;
	
	/** A constante ID_INSTITUICAO_BANCOOB. */
	private static final Integer ID_INSTITUICAO_BANCOOB = 1;
	
	/** A constante NUM_PAC_ORIGEM. */
	private static final Short NUM_PAC_ORIGEM = 0;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Pessoa incluir(Pessoa objeto, Integer idInstituicao) throws BancoobException {
		Pessoa pessoa = super.incluir(objeto, idInstituicao);
		incluirAvaliacaoFinanceira(pessoa, idInstituicao);
		return pessoa;
	}
	
	/**
	 * Inclui a avaliação financeira para a pessoa informada.
	 * @param pessoa
	 * @param idInstituicao
	 * @throws BancoobException
	 */
	private void incluirAvaliacaoFinanceira(Pessoa pessoa, Integer idInstituicao) 
			throws BancoobException {

		AvaliacaoFinanceira avaliacao = new AvaliacaoFinanceira();
		avaliacao.setDataUltimaAtualizacao(pessoa.getDataUltimaAtualizacao());
		avaliacao.setPessoa(pessoa);
		avaliacao.setNumPessoa(pessoa.getId());
		avaliacao.setValorLimiteCreditoCurtoPrazo(BigDecimal.ZERO);
		avaliacao.setValorLimiteCreditoLongoPrazo(BigDecimal.ZERO);
		avaliacao.setValorLimiteCreditoRotativo(BigDecimal.ZERO);
		avaliacao.setValorTotalGastoFamiliar(BigDecimal.ZERO);
		
		servicoAvaliacao.incluir(avaliacao, idInstituicao);
	}

	/**
	 * {@inheritDoc}
	 */
	public void alterarCpfCnpj(Pessoa pessoa, Integer idInstituicao) throws BancoobException {

		Pessoa pessoaAlteracao = obter(pessoa.getId(), idInstituicao);
		pessoaAlteracao.setNumeroCgcCpf(pessoa.getNumeroCgcCpf());
		alterar(pessoaAlteracao, idInstituicao);
	}

	/**
	 * {@inheritDoc}
	 */
	public void alterarEmail(Pessoa pessoa, Integer idInstituicao) throws BancoobException {
		
		final String strIdInstituicao = String.valueOf(idInstituicao);
		final String tipoPessoa = pessoa.getClass().getSimpleName();
		final String strIdPessoa = String.valueOf(pessoa.getId());

		getLogger().debug("Iniciando alteração de e-mail: ", tipoPessoa, "=", strIdPessoa,
				", idInstituicao=", strIdInstituicao);
		Pessoa pessoaPersistente = obter(pessoa.getId(), idInstituicao);
		
		/*
		 * Esta replicação é realizada devido à depenência que outros sistemas possuem. Porém, no
		 * DB2 o e-mail aceita até 200 caracteres e no SQL só 40. Por este motivo e seguindo
		 * orientações do analista Luciano Lamounier, deve-se cortar o e-mail no tamanho correto.
		 */
		String email = StringUtils.substring(pessoa.getEmail(), 0, 40);
		
		getLogger().debug("Truncando e-mail: ", pessoa.getEmail(), " --> ", email);
		
		pessoaPersistente.setEmail(email);
		pessoaPersistente.setDataUltimaAtualizacao(new Date());
		
		getLogger().debug("Alterando e-mail: ", tipoPessoa, "=", strIdPessoa, ", idInstituicao=",
				strIdInstituicao);
		
		Integer numeroCooperativa = obterNumeroCooperativa(idInstituicao);
		getDAO().alterarEmail(pessoaPersistente, numeroCooperativa);
		
		getLogger().info("E-mail alterado na instituição ", strIdInstituicao);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected PessoaDAO getDAO() {
		return pessoaDAO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void alterar(Pessoa pessoa, Integer idInstituicao) throws BancoobException {
		Integer numeroCooperativa = obterNumeroCooperativa(idInstituicao);
		getDAO().alterar(pessoa, numeroCooperativa);
		incluirHistoricoCedente(pessoa, idInstituicao);
		servicoCliente.alterarDataSFN(pessoa, idInstituicao);
		if(pessoa instanceof PessoaFisica) {
			PessoaFisica pf = (PessoaFisica) pessoa;
			if(pf.isNomeAlterado() || pf.isProfissaoAlterada()) {
				atualizarConjuge((PessoaFisica) pessoa, numeroCooperativa);
			}
		}
		
		// Alteração para replicação da autorização de consultas ao BACEN
		Cliente cliente = servicoCliente.obter(pessoa.getId(), idInstituicao);
		if(cliente != null){
			cliente.setAutorizaConsulta(pessoa.isAutorizaConsulta());
			servicoCliente.alterar(cliente, idInstituicao);
		}
	}
	
	/**
	 * Atualiza o nome do cônjuge em todas as pessoas que possuem o cpf da pessoa informada como cônjuge.
	 * @param pessoaFisica a pessoa física 
	 * @param numCooperativa
	 * @throws BancoobException
	 */
	private void atualizarConjuge(PessoaFisica pessoaFisica, Integer numCooperativa) throws BancoobException {
		
		// Listando os cônjuges da pessoa
		List<PessoaFisica> conjuges = getDAO().listarPessoasPorCpfConjuge(
				pessoaFisica.getNumeroCgcCpf(), numCooperativa);

		if(conjuges != null && !conjuges.isEmpty()) {
			for (PessoaFisica conjuge : conjuges) {
				conjuge.setNomeConjuge(pessoaFisica.getNome());
				conjuge.setCodigoProfissaoConjuge(pessoaFisica.getCodigoProfissao());
				getDAO().alterar(conjuge, numCooperativa);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void excluir(Pessoa pessoa, Integer idInstituicao) throws BancoobException {
		Integer numeroCooperativa = obterNumeroCooperativa(idInstituicao);
		getDAO().excluir(pessoa, numeroCooperativa);
		incluirHistoricoCedente(pessoa, idInstituicao);		
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void excluirSemHistorico(Pessoa pessoa, Integer idInstituicao) throws BancoobException {
		Integer numeroCooperativa = obterNumeroCooperativa(idInstituicao);
		getDAO().excluir(pessoa, numeroCooperativa);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void atualizarAssinaturaFotoBancoob(Integer idPessoa,
			Integer idInstituicao) throws BancoobException {
		Integer numeroCooperativa = obterNumeroCooperativa(idInstituicao);
		getDAO().atualizarAssinaturaFotoBancoob(idPessoa, numeroCooperativa);
	}
	
	/**
	 * O método Incluir historico cedente.
	 *
	 * @param pessoa o valor de pessoa
	 * @param idInstituicao o valor de id instituicao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void incluirHistoricoCedente(Pessoa pessoa, Integer idInstituicao) throws BancoobException {
		servicoHistoricoCedente.incluirHistoricoCendente(pessoa, idInstituicao);
 	}

	/**
	 * {@inheritDoc}
	 */
	public void atualizarCooperativaOrigem(Integer numPessoa, Integer numCoopOrigem) throws BancoobException {
		
		getLogger().info("Atualizando a cooperativa de origem do numPessoa: " + numPessoa);
		Pessoa pessoaAlteracao = obter(numPessoa, ID_INSTITUICAO_BANCOOB);
		pessoaAlteracao.setNumCoopOrigemPessoa(numCoopOrigem);
		pessoaAlteracao.setNumPacOrigemPessoa(NUM_PAC_ORIGEM);
		
		Integer numeroCooperativa = obterNumeroCooperativa(ID_INSTITUICAO_BANCOOB);
		getDAO().alterarComFlush(pessoaAlteracao, numeroCooperativa);
	}
	
	/**
	 * {@inheritDoc}
	 */	
	public void atualizarCooperativasPessoa(Integer numPessoaBancoob,
			List<Cooperativa> cooperativas) throws BancoobException {
		
		Pessoa pessoa = obter(numPessoaBancoob, ID_INSTITUICAO_BANCOOB);
		getLogger().info("Atualizando cooperativas para a pessoa: " + numPessoaBancoob + " no Bancoob.");

		Set<CooperativaPessoa> cooperativasPessoa = pessoa.getCooperativas();
		
		if(cooperativas != null) {
			for (Cooperativa cooperativa : cooperativas) {
				if (cooperativasPessoa == null) {
					cooperativasPessoa = new java.util.HashSet<CooperativaPessoa>();
				}
				cooperativasPessoa.add(criarCooperativaPessoa(pessoa, cooperativa.getId()));
			}
		}
		
		Integer numeroCooperativa = obterNumeroCooperativa(ID_INSTITUICAO_BANCOOB);
		getDAO().alterarComFlush(pessoa, numeroCooperativa);
	}
	
	/**
	 * Criar cooperativa pessoa.
	 *
	 * @param pessoa o valor de pessoa
	 * @param cooperativa o valor de cooperativa
	 * @return CooperativaPessoa
	 */
	private CooperativaPessoa criarCooperativaPessoa(Pessoa pessoa, CooperativaPK cooperativa) {

		CooperativaPessoaPK pk = new CooperativaPessoaPK();
		pk.setNumCooperativaOrigem(cooperativa.getNumCooperativa());
		pk.setNumPacOrigem(0); // Solicitação para incluir o PAC sempre 0.
		pk.setPessoa(pessoa);
		
		CooperativaPessoa cooperativaPessoa = new CooperativaPessoa();
		cooperativaPessoa.setDataOrigem(new DateTimeDB());
		cooperativaPessoa.setId(pk);
		
		return cooperativaPessoa;
	}

	/**
	 * {@inheritDoc}
	 */	
	public void atualizarTelefonePessoa(Pessoa pessoa, Telefone telefone, Integer idInstituicao)
			throws BancoobException {
		
		Pessoa pessoaAlteracao = obter(pessoa.getId(), idInstituicao);
		pessoaAlteracao.setNumCelFax(telefone.getNumero());
		pessoaAlteracao.setNumRamal(telefone.getRamal());
		pessoaAlteracao.setDdd(telefone.getDdd());
		
		Integer numeroCooperativa = obterNumeroCooperativa(idInstituicao);
		getDAO().alterar(pessoaAlteracao, numeroCooperativa);
	}

    /**
	 * {@inheritDoc}
	 */	
	public void atualizarConjuge(Pessoa pessoa, Integer idInstituicao) throws BancoobException {
		if (pessoa instanceof PessoaFisica) {
			Integer numeroCooperativa = obterNumeroCooperativa(idInstituicao);
			getDAO().atualizarConjuge(pessoa, numeroCooperativa);
			incluirHistoricoCedente(pessoa, idInstituicao);
			servicoCliente.alterarDataSFN(pessoa, idInstituicao);
			
			PessoaFisica pf = (PessoaFisica) pessoa;
			if (pf.isNomeAlterado() || pf.isProfissaoAlterada()) {
				atualizarConjuge((PessoaFisica) pessoa, numeroCooperativa);
			}

			// Alteração para replicação da autorização de consultas ao BACEN
			Cliente cliente = servicoCliente.obter(pessoa.getId(), idInstituicao);
			if (cliente != null) {
				cliente.setAutorizaConsulta(pessoa.isAutorizaConsulta());
				servicoCliente.alterar(cliente, idInstituicao);
			}
		}
	}

	public void atualizaCodTipoEmpresa(PessoaJuridica pjTemp) throws BancoobException {
		getDAO().atualizaCodTipoEmpresa(pjTemp);
	}
	
}