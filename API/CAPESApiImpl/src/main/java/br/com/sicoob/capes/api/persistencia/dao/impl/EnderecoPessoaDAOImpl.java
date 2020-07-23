package br.com.sicoob.capes.api.persistencia.dao.impl;

import javax.persistence.NoResultException;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.capes.api.negocio.vo.EnderecoPessoaVO;
import br.com.sicoob.capes.api.persistencia.dao.EnderecoPessoaDAO;

/**
 * A Classe EnderecoPessoaDAOImpl.
 */
public class EnderecoPessoaDAOImpl extends CAPESApiDAO<EnderecoPessoaVO> implements EnderecoPessoaDAO {

	/**
	 * Instancia um novo EnderecoPessoaDAOImpl.
	 */
	public EnderecoPessoaDAOImpl() {
		super("CONSULTA_ENDERECO_PESSOA");
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String obterNomeComandoPesquisar() throws BancoobException {
		return "PESQUISAR_ENDERECO_PESSOA";
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String obterNomeComandoQuantidadePesquisar() throws BancoobException {
		return "PESQUISAR_QUANTIDADE_ENDERECO_PESSOA";
	}

	/**
	 * {@inheritDoc}
	 */
	public EnderecoPessoaVO obterEnderecoCorrespondenciaPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		Comando comando = null;
		try {
			comando = getComando("PESQUISAR_ENDERECO_CORRESPONDENCIA_PESSOA");
			comando.adicionarVariavel(ID_PESSOA, idPessoa);
			comando.adicionarVariavel(ID_INSTITUICAO, idInstituicao);
			comando.configurar();

			return (EnderecoPessoaVO) criarQuery(comando).getSingleResult();
		} catch (NoResultException e) {
			return null;
		} finally {
			fecharComando(comando);
		}
	}

}