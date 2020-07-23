/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.comum.util.ReflexaoUtils;
import br.com.sicoob.capes.negocio.entidades.legado.Endereco;
import br.com.sicoob.capes.negocio.entidades.legado.Pessoa;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.EnderecoServicoLocal;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.EnderecoServicoRemote;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.HistoricoCedenteServicoLocal;
import br.com.sicoob.capes.replicacao.persistencia.dao.EnderecoDAO;

/**
 * Serviço para a replicação dos endereços
 * 
 * @author erico.junior
 */
@Stateless
@Local( { EnderecoServicoLocal.class })
@Remote( { EnderecoServicoRemote.class })
public class EnderecoServicoEJB extends EntidadeReplicavelServicoEJB<Endereco> implements
		EnderecoServicoRemote, EnderecoServicoLocal {

	@Inject
	@Default
	private transient EnderecoDAO dao;

	/** O atributo servicoHistoricoCedente. */
	@EJB(mappedName = "capes/replicacao/HistoricoCedenteServico")
	private transient HistoricoCedenteServicoLocal servicoHistoricoCedente;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected EnderecoDAO getDAO() {
		return dao;
	}

	/**
	 * {@inheritDoc}
	 */
	public void tornarPadraoCorrespondencia(Endereco correspondencia, Integer idInstituicao) 
			throws BancoobException {
	
		List<Endereco> lista = listarEnderecos(correspondencia.getPessoa(), idInstituicao);

		if(lista != null && !lista.isEmpty()) {
			for (Endereco endereco : lista) {
				boolean padrao = ReflexaoUtils.equals(
						correspondencia, endereco, "idEnderecoPessoaDB2");
				endereco.setEnderecoCorrespondencia(padrao);
				alterar(endereco, idInstituicao);
			}
		}
	}

	/**
	 * Lista todos os endereços por pessoa.
	 * 
	 * @param pessoa
	 *            A pessoa na qual se desja os endereços.
	 * @param idInstituicao
	 * 			  O identificador da instituição para conexão.
	 * @return todos os endereços por pessoa.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção.
	 */
	private List<Endereco> listarEnderecos(Pessoa pessoa, Integer idInstituicao) 
			throws BancoobException {

		Endereco endereco = new Endereco();
		endereco.setPessoa(pessoa);

		ConsultaDto<Endereco> criterios = new ConsultaDto<Endereco>();
		criterios.setFiltro(endereco);

		return super.listar(criterios, idInstituicao);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Endereco incluir(Endereco endereco, Integer idInstituicao)
			throws BancoobException {
		
		Endereco retorno = null;
		
		if(endereco.getPessoa() != null) {
			retorno = super.incluir(endereco, idInstituicao);
			incluirHistoricoCedente(retorno, idInstituicao);
		}
	
		return retorno;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void alterar(Endereco endereco, Integer idInstituicao)
			throws BancoobException {
		super.alterar(endereco, idInstituicao);
		incluirHistoricoCedente(endereco, idInstituicao);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void excluir(Endereco endereco, Integer idInstituicao)
			throws BancoobException {

		super.excluir(endereco, idInstituicao);
		incluirHistoricoCedente(endereco, idInstituicao);
	}

	/**
	 * O método Incluir historico cedente.
	 *
	 * @param endereco o valor de endereco
	 * @param idInstituicao o valor de id instituicao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void incluirHistoricoCedente(Endereco endereco, Integer idInstituicao) throws BancoobException {
		servicoHistoricoCedente.incluirHistoricoCendente(endereco.getPessoa(), idInstituicao);
 	}
	
}
