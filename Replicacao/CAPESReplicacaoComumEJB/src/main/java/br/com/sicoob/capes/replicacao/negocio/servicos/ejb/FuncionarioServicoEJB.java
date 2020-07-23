/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.excecao.ViolacaoIntegridadeException;
import br.com.sicoob.capes.negocio.entidades.legado.Funcionario;
import br.com.sicoob.capes.replicacao.negocio.excecao.FuncionarioLegadoExclusaoException;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.FuncionarioServicoLocal;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.FuncionarioServicoRemote;
import br.com.sicoob.capes.replicacao.persistencia.dao.FuncionarioDAO;

/**
 * Serviço utilizado na replicação de funcionario.
 * @author juan.damasceno
 */
@Stateless
@Local( { FuncionarioServicoLocal.class })
@Remote( { FuncionarioServicoRemote.class })
public class FuncionarioServicoEJB extends EntidadeReplicavelServicoEJB<Funcionario> implements
	FuncionarioServicoRemote, FuncionarioServicoLocal {

	@Inject
	@Default
	private FuncionarioDAO funcionarioDAO;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected FuncionarioDAO getDAO() {
		return funcionarioDAO;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Funcionario incluir(Funcionario objeto, Integer idInstituicao)
			throws BancoobException {
		Integer numeroCooperativa = obterNumeroCooperativa(idInstituicao);
		objeto.setNumCooperativa(numeroCooperativa);
		return getDAO().incluir(objeto, numeroCooperativa);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void alterar(Funcionario objeto, Integer idInstituicao) throws BancoobException {
		Integer numeroCooperativa = obterNumeroCooperativa(idInstituicao);
		objeto.setNumCooperativa(numeroCooperativa);
		getDAO().alterar(objeto, numeroCooperativa);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void excluir(Funcionario objeto, Integer idInstituicao) throws BancoobException {
		try {
			Integer numeroCooperativa = obterNumeroCooperativa(idInstituicao);
			getDAO().excluir(objeto.getIdFuncionario(), numeroCooperativa);
		} catch (ViolacaoIntegridadeException e) {
			throw new FuncionarioLegadoExclusaoException(e);
		}
	}

}
