package br.com.sicoob.capes.replicacao.negocio.servicos;

import java.io.Serializable;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.negocio.servicos.BancoobCrudServico;
import br.com.sicoob.capes.negocio.entidades.legado.CAPESEntidadeLegado;

/**
 * Interface que define um contrato padrao para as operacoes de CRUD de todo o
 * sistema ReplicacaoClientesBO
 * 
 * @author Stefanini IT Solutions
 */
public interface CAPESReplicacaoComumCrudServico<T extends CAPESEntidadeLegado<?>> 
		extends	CAPESReplicacaoComumServico, BancoobCrudServico<T> {
	
	/**
	 * Método para incluir um objeto.
	 * 
	 * @param objeto
	 *            o objeto a ser incluído.
	 * @param instituicao
	 *            A instituição.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção na inclusão.
	 */
	T incluir(T objeto, Integer idInstituicao) throws BancoobException;

	/**
	 * Método para excluir um objeto.
	 * 
	 * @param objeto
	 *            o objeto a ser excluído.
	 * @param instituicao
	 *            A instituição.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção na exclusão.
	 */
	void excluir(T objeto, Integer idInstituicao) throws BancoobException;

	/**
	 * Método para alterar um objeto.
	 * 
	 * @param objeto
	 *            o objeto a ser alterado.
	 * @param instituicao
	 *            A instituição.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção na alteração.
	 */
	void alterar(T objeto, Integer idInstituicao) throws BancoobException;

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
	T obter(Serializable chave, Integer idInstituicao) throws BancoobException;	
	
	/**
	 * Método para listar 
	 * @param instituicao
	 *            A instituição.
	 * @return A lista de todas as entidade procuradas na instituição informada.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção na alteração.
	 */
	List<T> listar(Integer idInstituicao) throws BancoobException;

	/**
	 * Método para listar
	 * 
	 * @param criterios
	 *            os critérios para filtro
	 * @param instituicao
	 *            A instituição.
	 * @return A lista de todas as entidade procuradas na instituição informada.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção na listagem.
	 */
	List<T> listar(ConsultaDto<T> criterios, Integer idInstituicao) throws BancoobException;

}
