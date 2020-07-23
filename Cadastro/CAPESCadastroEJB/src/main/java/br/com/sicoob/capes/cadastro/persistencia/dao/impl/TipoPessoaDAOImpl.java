/* 
 * Sicoob
 * TipoPessoaDAOImpl.java 
 * Criado em: 30/06/2011
 */
package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.negocio.entidades.TipoPessoa;
import br.com.sicoob.capes.cadastro.persistencia.dao.TipoPessoaDAO;

/**
 * 30/06/2011
 * 
 * @author Rodrigo.Chaves
 */
public class TipoPessoaDAOImpl extends CAPESCadastroCrudDao<TipoPessoa> implements
		TipoPessoaDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "";

	/**
	 * Construtor
	 * 
	 * @param clazz
	 * @param nomeComandoPesquisar
	 */
	public TipoPessoaDAOImpl() {
		super(TipoPessoa.class, NOME_COMANDO_PESQUISAR);
	}

	/**
	 * {@inheritDoc}
	 */
	public void alterar(TipoPessoa objeto) throws BancoobException {
		throw new UnsupportedOperationException();
	}

	/**
	 * {@inheritDoc}
	 */
	public TipoPessoa incluir(TipoPessoa objeto) throws BancoobException {
		throw new UnsupportedOperationException();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<TipoPessoa> listar(ConsultaDto<TipoPessoa> criterios) throws BancoobException {
		throw new UnsupportedOperationException();
	}

	/**
	 * {@inheritDoc}
	 */
	public ConsultaDto<TipoPessoa> pesquisar(ConsultaDto<TipoPessoa> criterios)
			throws BancoobException {
		throw new UnsupportedOperationException();
	}

}
