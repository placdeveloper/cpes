/*
 * SICOOB
 * 
 * ValidacaoCadastralServicoEJB.java(br.com.sicoob.capes.cadastro.negocio.servicos.ejb.ValidacaoCadastralServicoEJB)
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.excecao.BancoobRuntimeException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.ValidacaoCadastralServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.ValidacaoCadastralServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.ValidacaoCadastralDAO;
import br.com.sicoob.capes.negocio.entidades.ValidacaoCadastral;

/**
 * EJB contendo servicos relacionados a ValidacaoCadastral.
 */
@Stateless
@Local(ValidacaoCadastralServicoLocal.class)
@Remote(ValidacaoCadastralServicoRemote.class)
public class ValidacaoCadastralServicoEJB extends CAPESCadastroCrudServicoEJB<ValidacaoCadastral> implements
		ValidacaoCadastralServicoRemote, ValidacaoCadastralServicoLocal {

	@Inject
	@Default
	protected ValidacaoCadastralDAO dao;
	
	/** 
	 * {@inheritDoc}
	 */
	public void atualizarDataUltimaValidacao(Long idPessoaCompartilhamento) throws BancoobException {
		if (idPessoaCompartilhamento == null) {
			throw new BancoobRuntimeException(new IllegalArgumentException(
			        "O ID da pessoa nao pode ser nulo"));
		}
		getDAO().atualizarDataUltimaValidacao(idPessoaCompartilhamento, new DateTimeDB());
	}

	/**
	 * {@inheritDoc}
	 */
	public void atualizarDataUltimaValidacao(DateTimeDB data) throws BancoobException {
		if (data == null) {
			throw new BancoobRuntimeException(new IllegalArgumentException(
			        "A data não pode ser nula"));
		}
		getDAO().atualizarDataUltimaValidacao(null, data);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void verificarPendenciasPrazoRenovacaoCadastro() throws BancoobException {
		getDAO().verificarPendenciasPrazoRenovacaoCadastro();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected ValidacaoCadastralDAO getDAO() {
		return dao;
	}


}
