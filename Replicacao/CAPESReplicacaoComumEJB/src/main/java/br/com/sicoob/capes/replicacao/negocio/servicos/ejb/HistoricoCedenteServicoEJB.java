/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.servicos.ejb;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.legado.Cliente;
import br.com.sicoob.capes.negocio.entidades.legado.Pessoa;
import br.com.sicoob.capes.replicacao.negocio.dto.HistoricoCedenteDTO;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.ClienteServicoLocal;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.HistoricoCedenteServicoLocal;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.HistoricoCedenteServicoRemote;
import br.com.sicoob.capes.replicacao.persistencia.dao.HistoricoCedenteDAO;

/**
 * @author erico.junior
 * 
 */
@Stateless
@Local({ HistoricoCedenteServicoLocal.class })
@Remote({ HistoricoCedenteServicoRemote.class })
public class HistoricoCedenteServicoEJB extends CAPESReplicacaoComumServicoEJB implements HistoricoCedenteServicoRemote, HistoricoCedenteServicoLocal {

	@Inject
	@Default
	private HistoricoCedenteDAO dao;

	/** O atributo servicoCliente. */
	@EJB(mappedName = "capes/replicacao/ClienteServico")
	private ClienteServicoLocal servicoCliente;
	
	/**
	 * {@inheritDoc}
	 */
	public void incluirHistoricoCendente(Pessoa pessoa, Integer idInstituicao)
			throws BancoobException {

		Cliente cliente = recuperarCliente(pessoa, idInstituicao);
		
		if(cliente != null) {
			HistoricoCedenteDTO dto = new HistoricoCedenteDTO();
			dto.setNumCliente(cliente.getIdCliente());
			
			Integer numCooperativa = obterNumeroCooperativa(idInstituicao);
			dao.incluirHistoricoCendente(dto, numCooperativa);
		}
	}

	/**
	 * Recuperar cliente.
	 *
	 * @param pessoa o valor de pessoa
	 * @param idInstituicao o valor de id instituicao
	 * @return Cliente
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private Cliente recuperarCliente(Pessoa pessoa, Integer idInstituicao) throws BancoobException {
		
		Cliente cliente = null;
		try {
			cliente = servicoCliente.obter(pessoa.getId(), idInstituicao);
		} catch (EntityNotFoundException e) {
			getLogger().erro(e, "Erro ao recuperar cliente.");
		}
		return cliente;
	}

}
