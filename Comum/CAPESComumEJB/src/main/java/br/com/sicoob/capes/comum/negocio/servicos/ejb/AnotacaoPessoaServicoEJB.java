/*
 * SICOOB
 * 
 * AnotacaoPessoaServicoEJB.java(br.com.sicoob.capes.comum.negocio.servicos.ejb.AnotacaoPessoaServicoEJB)
 */
package br.com.sicoob.capes.comum.negocio.servicos.ejb;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.comum.negocio.servicos.interfaces.AnotacaoPessoaServicoLocal;
import br.com.sicoob.capes.comum.negocio.servicos.interfaces.AnotacaoPessoaServicoRemote;
import br.com.sicoob.capes.comum.persistencia.dao.AnotacaoPessoaDAO;

/**
 * The Class AnotacaoPessoaServicoEJB.
 */
@Stateless
@Local(AnotacaoPessoaServicoLocal.class)
@Remote(AnotacaoPessoaServicoRemote.class)
public class AnotacaoPessoaServicoEJB extends CAPESComumServicoEJB implements AnotacaoPessoaServicoLocal,
		AnotacaoPessoaServicoRemote {

	/** O atributo dao. */
	@Inject
	@Default
	private AnotacaoPessoaDAO dao;
	
	/**
	 * Recupera dao.
	 * 
	 * @return dao
	 */
	private AnotacaoPessoaDAO getDao() {
		return dao;
	}
	
	/** 
	 * {@inheritDoc}
	 */
	public List<Map<String, Object>> obterVigentesPorPessoaInstituicaoTipo(Integer idPessoa, Integer idInstituicao,
			Integer idTipoAnotacao) throws BancoobException {

		return getDao().obterVigentesPorPessoaInstituicaoTipo(idPessoa, null, idInstituicao, idTipoAnotacao);
	}
	
	/** 
	 * {@inheritDoc}
	 */
	public List<Map<String, Object>> obterVigentesPorPessoaInstituicaoTipo(String cpfCnpj, Integer idInstituicao,
			Integer idTipoAnotacao) throws BancoobException {
		
		return getDao().obterVigentesPorPessoaInstituicaoTipo(null, cpfCnpj, idInstituicao, idTipoAnotacao);
	}

}
