/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.servicos.ejb;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.infraestrutura.seguranca.UsuarioBancoob;
import br.com.sicoob.capes.replicacao.negocio.dto.ReplicacaoCadastroDTO;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.ReplicacaoAssincronaServicoLocal;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.ReplicacaoCadastroServicoLocal;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.ReplicacaoCadastroServicoRemote;
import br.com.sicoob.capes.replicacao.persistencia.dao.ReplicacaoCadastroDAO;

/**
 * @author Erico.Junior
 * 
 */
@Stateless
@Local( { ReplicacaoCadastroServicoLocal.class })
@Remote( { ReplicacaoCadastroServicoRemote.class })
public class ReplicacaoCadastroServicoEJB extends CAPESReplicacaoComumServicoEJB implements
		ReplicacaoCadastroServicoRemote, ReplicacaoCadastroServicoLocal {

	@Inject
	@Default
	private ReplicacaoCadastroDAO dao;
	
	@EJB
	private ReplicacaoCadastroServicoLocal servicoLocal;
	
	@EJB
	private ReplicacaoAssincronaServicoLocal servicoReplicacaoAssincrona;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer replicarCadastro(ReplicacaoCadastroDTO replicacao) throws BancoobException {
		UsuarioBancoob usuario = obterUsuario();
		Integer cooperativaOrigem = obterNumeroCooperativa(replicacao.getIdInstituicaoOrigem());
		Integer cooperativaDestino = Integer.valueOf(usuario.getCooperativa());
		Integer numPessoa = servicoLocal.replicarCadastroCooperativa(replicacao, cooperativaDestino);
		servicoReplicacaoAssincrona.replicarCadastroAssincrono(replicacao.getNumPessoaOrigem(), cooperativaOrigem, cooperativaDestino);
		return numPessoa;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer replicarCadastro(ReplicacaoCadastroDTO replicacao, Integer idInstituicaoDestino) throws BancoobException {
		Integer cooperativaOrigem = obterNumeroCooperativa(replicacao.getIdInstituicaoOrigem());
		Integer cooperativaDestino = obterNumeroCooperativa(idInstituicaoDestino);
		Integer numPessoa = servicoLocal.replicarCadastroCooperativa(replicacao, cooperativaDestino);
		servicoReplicacaoAssincrona.replicarCadastroAssincrono(replicacao.getNumPessoaOrigem(), cooperativaOrigem, cooperativaDestino);
		return numPessoa;
	}

	/**
	 * Replica o cadastro da pessoa informada para a cooperativa de destino.
	 * 
	 * @param replicacao
	 *            Os dados da pessoa e a cooperativa de origem dos dados.
	 * @param cooperativaDestino
	 *            A cooperativa de destino.
	 * @return O identificador da pessoa na nova cooperativa.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção.
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Integer replicarCadastroCooperativa(ReplicacaoCadastroDTO replicacao, Integer cooperativaDestino) throws BancoobException {
		Integer cooperativaOrigem = obterNumeroCooperativa(replicacao.getIdInstituicaoOrigem());
		Integer numPessoa = dao.replicarCadastro(replicacao.getNumPessoaOrigem(), cooperativaOrigem, cooperativaDestino);
		return numPessoa;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void atualizarPessoaAlteracaoGrupo(Integer idPessoaOrigem, Integer idPessoaDestino, Integer idInstituicaoOrigem, Integer idInstituicaoDestino) throws BancoobException {
		Integer numeroCoopOrigem = obterNumeroCooperativa(idInstituicaoOrigem);
		Integer numeroCoopDestino = obterNumeroCooperativa(idInstituicaoDestino);
		dao.atualizarPessoaAlteracaoGrupo(idPessoaOrigem, idPessoaDestino, numeroCoopOrigem, numeroCoopDestino);
	}

}