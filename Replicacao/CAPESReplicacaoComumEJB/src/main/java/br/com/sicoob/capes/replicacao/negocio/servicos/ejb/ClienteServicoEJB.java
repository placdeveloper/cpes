/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.legado.Cliente;
import br.com.sicoob.capes.negocio.entidades.legado.Pessoa;
import br.com.sicoob.capes.replicacao.negocio.dto.AtualizacaoRiscoClienteDTO;
import br.com.sicoob.capes.replicacao.negocio.excecao.ParametroObrigatorioException;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.ClienteServicoLocal;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.ClienteServicoRemote;
import br.com.sicoob.capes.replicacao.persistencia.dao.ClienteDAO;

/**
 * Serviço utilizado na replicação de clientes.
 * @author juan.damasceno
 */
@Stateless
@Local( { ClienteServicoLocal.class })
@Remote( { ClienteServicoRemote.class })
public class ClienteServicoEJB extends EntidadeReplicavelServicoEJB<Cliente> implements
	ClienteServicoRemote, ClienteServicoLocal {

	@Inject
	@Default
	private transient ClienteDAO clienteDAO;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Cliente incluir(Cliente cliente, Integer idInstituicao) throws BancoobException {
		
		// os clientes devem ser inseridos com as flags de cobrança ligadas por padrão
		cliente.setCobrarCPMF(Boolean.TRUE);
		cliente.setCobrarIOF(Boolean.TRUE);
		cliente.setCobrarIR(Boolean.TRUE);
		return super.incluir(cliente, idInstituicao);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void excluir(Cliente objeto, Integer idInstituicao)
			throws BancoobException {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void alterar(Cliente objeto, Integer idInstituicao) throws BancoobException {
		
		if(objeto != null) {
			Integer numeroCooperativa = obterNumeroCooperativa(idInstituicao);
			clienteDAO.alterar(objeto, numeroCooperativa);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ClienteDAO getDAO() {
		return clienteDAO;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Cliente> pesquisarPorListaID(List<Integer> ids) throws BancoobException {
		return clienteDAO.pesquisarPorListaID(ids);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void alterarDataSFN(Pessoa pessoa, Integer idInstituicao)
			throws BancoobException {
		
		Integer numPessoa = pessoa.getId();
		Cliente cliente = obter(numPessoa, idInstituicao);

		if (cliente != null) {
			cliente.setDataCadastramentoSFN(pessoa.getDataSFN());
			alterar(cliente, idInstituicao);
			getLogger().info("Data SFN alterada para o numPessoa: " + numPessoa + " na instituicao: " + idInstituicao);
		}
	}
	
	/** 
	 * {@inheritDoc}
	 */
	public void atualizarRiscoCliente(AtualizacaoRiscoClienteDTO dto) throws BancoobException {

		getLogger().debug("Iniciando atualizacao de risco: ", dto.toString());
		if (dto.getIdInstituicao() == null) {
			throw new ParametroObrigatorioException("idInstituicao");
		}
		if (StringUtils.isBlank(dto.getIdNivelRisco())) {
			throw new ParametroObrigatorioException("idNivelRisco");
		}
		if (StringUtils.isBlank(dto.getMotivoRisco())) {
			throw new ParametroObrigatorioException("motivoRisco");
		}
		if (dto.getNumCliente() == null) {
			throw new ParametroObrigatorioException("numCliente");
		}
		Integer numeroCooperativa = obterNumeroCooperativa(dto.getIdInstituicao());
		
		getLogger().debug("Atualizando o risco do cliente na cooperativa ",
				String.valueOf(numeroCooperativa), ": ", dto.toString());
		clienteDAO.atualizarRiscoCliente(dto, numeroCooperativa);
		
		getLogger().debug("Atualizacao de risco concluida: ", dto.toString());
	}

	/** 
	 * {@inheritDoc}
	 */
	public void replicarClienteLegadoLote(List<Cliente> clientesIncluir, List<Cliente> clientesAlterar, Integer idInstituicao) throws BancoobException {
		if(!clientesAlterar.isEmpty() || !clientesIncluir.isEmpty() ) {
			clienteDAO.replicarLote(clientesIncluir, clientesAlterar, idInstituicao);
		}
	}	
	
}
