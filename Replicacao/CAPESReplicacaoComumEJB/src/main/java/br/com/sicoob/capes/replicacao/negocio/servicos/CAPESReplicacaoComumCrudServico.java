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
	 * M�todo para incluir um objeto.
	 * 
	 * @param objeto
	 *            o objeto a ser inclu�do.
	 * @param instituicao
	 *            A institui��o.
	 * @throws BancoobException
	 *             Caso ocorra alguma exce��o na inclus�o.
	 */
	T incluir(T objeto, Integer idInstituicao) throws BancoobException;

	/**
	 * M�todo para excluir um objeto.
	 * 
	 * @param objeto
	 *            o objeto a ser exclu�do.
	 * @param instituicao
	 *            A institui��o.
	 * @throws BancoobException
	 *             Caso ocorra alguma exce��o na exclus�o.
	 */
	void excluir(T objeto, Integer idInstituicao) throws BancoobException;

	/**
	 * M�todo para alterar um objeto.
	 * 
	 * @param objeto
	 *            o objeto a ser alterado.
	 * @param instituicao
	 *            A institui��o.
	 * @throws BancoobException
	 *             Caso ocorra alguma exce��o na altera��o.
	 */
	void alterar(T objeto, Integer idInstituicao) throws BancoobException;

	/**
	 * M�todo para obter uma entidade.
	 * 
	 * @param chave
	 *            A chave da entidade.
	 * @param instituicao
	 *            A institui��o.
	 * @return A entidade procurada na institui��o informada.
	 * @throws BancoobException
	 *             Caso ocorra alguma exce��o na altera��o.
	 */
	T obter(Serializable chave, Integer idInstituicao) throws BancoobException;	
	
	/**
	 * M�todo para listar 
	 * @param instituicao
	 *            A institui��o.
	 * @return A lista de todas as entidade procuradas na institui��o informada.
	 * @throws BancoobException
	 *             Caso ocorra alguma exce��o na altera��o.
	 */
	List<T> listar(Integer idInstituicao) throws BancoobException;

	/**
	 * M�todo para listar
	 * 
	 * @param criterios
	 *            os crit�rios para filtro
	 * @param instituicao
	 *            A institui��o.
	 * @return A lista de todas as entidade procuradas na institui��o informada.
	 * @throws BancoobException
	 *             Caso ocorra alguma exce��o na listagem.
	 */
	List<T> listar(ConsultaDto<T> criterios, Integer idInstituicao) throws BancoobException;

}
