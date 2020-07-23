package br.com.sicoob.capes.replicacao.negocio.delegates;

import java.io.Serializable;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.delegates.BancoobCrudDelegate;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.negocio.entidades.legado.CAPESEntidadeLegado;
import br.com.sicoob.capes.replicacao.negocio.servicos.CAPESReplicacaoComumCrudServico;

/**
 * Business delegate padrao para operacoes de CRUD do sistema
 * ReplicacaoClientesBO
 * 
 * @author Stefanini IT Solutions
 */
public abstract class CAPESReplicacaoComumCrudDelegate<T extends CAPESEntidadeLegado<?>, S extends CAPESReplicacaoComumCrudServico<T>>
		extends BancoobCrudDelegate<T, S> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void alterar(T objeto) throws BancoobException {
		getServico().alterar(objeto);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void excluir(Serializable chave) throws BancoobException {
		throw new UnsupportedOperationException();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T incluir(T objeto) throws BancoobException {
		throw new UnsupportedOperationException();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T obter(Serializable chave) throws BancoobException {
		return getServico().obter(chave);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<T> listar() throws BancoobException {
		throw new UnsupportedOperationException();
	}

	/**
	 * Inclui a entidade para a instituição informada.
	 * 
	 * @param objeto
	 *            A entidade a ser incluída.
	 * @param instituicao
	 *            A instituição.
	 * @return O objeto incluído.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção.
	 */
	public T incluir(T objeto, Integer idInstituicao) throws BancoobException {
		return getServico().incluir(objeto, idInstituicao);
	}

	/**
	 * Altera uma entidade de uma determinada instituição.
	 * 
	 * @param objeto
	 *            A entidade a ser alterada.
	 * @param instituicao
	 *            A instituição.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção.
	 */
	public void alterar(T objeto, Integer idInstituicao) throws BancoobException {
		getServico().alterar(objeto, idInstituicao);
	}

	/**
	 * Exclui uma entidade de uma determinada instituição.
	 * 
	 * @param objeto
	 *            A entidade a ser excluída.
	 * @param instituicao
	 *            A instituição.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção.
	 */
	public void excluir(T objeto, Integer idInstituicao) throws BancoobException {
		getServico().excluir(objeto, idInstituicao);
	}

	/**
	 * Método para obter uma entidade.
	 * 
	 * @param chave
	 *            A chave da entidade.
	 * @param instituicao
	 *            A instituição.
	 * @return A entidade procurada na instituição informada.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção na alteração.
	 */
	public T obter(Serializable chave, Integer idInstituicao) throws BancoobException {
		return getServico().obter(chave, idInstituicao);
	}
	
	/**
	 * Método para listar 
	 * @param instituicao
	 *            A instituição.
	 * @return A lista de todas as entidade procuradas na instituição informada.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção na alteração.
	 */	
	public List<T> listar(Integer idInstituicao) throws BancoobException {
		return getServico().listar(idInstituicao);
	}

	/**
	 * Método para listar
	 * 
	 * @param criterios
	 *            Os critérios para filtro
	 * @param instituicao
	 *            A instituição.
	 * @return A lista de todas as entidade procuradas na instituição informada.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção na listagem.
	 */
	public List<T> listar(ConsultaDto<T> criterios, Integer idInstituicao) throws BancoobException {
		return getServico().listar(criterios, idInstituicao);
	}
}