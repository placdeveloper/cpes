/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.vo.StatusTransferenciaGrupoEconomicoVO;
import br.com.sicoob.capes.negocio.entidades.GrupoEconomico;
import br.com.sicoob.capes.negocio.entidades.Pessoa;

/**
 * Interface para o DAO de grupo economico.
 * 
 * @author juan.damasceno
 */
public interface GrupoEconomicoDAO extends CAPESCadastroCrudDaoIF<GrupoEconomico> {

	/**
	 * Listar por pessoa.
	 *
	 * @param pessoa o valor de pessoa
	 * @return List
	 */
	List<GrupoEconomico> listarPorPessoa(Pessoa pessoa);

	/**
	 * Verifica se o {@code grupo} já existe, considerando apenas o valor de
	 * {@link GrupoEconomico#getDescricao()}
	 * 
	 * <p>
	 * Os seguintes atributos devem estar, obrigatóriamente, preenchidos:
	 * <ul>
	 * 	<li>{@link GrupoEconomico#getIdInstituicao()}</li>
	 * 	<li>{@link GrupoEconomico#getDescricao()}</li>
	 * </ul>
	 * </p>
	 * 
	 * @param grupo
	 *            o grupo econômico que se deseja validar
	 * @return <code>true</code> caso já exista um grupo econômico com a mesma
	 *         descrição e <code>false</code>, caso não exista
	 */
	Boolean isGrupoEconomicoExistente(GrupoEconomico grupo) throws BancoobException;
	
	/**
	 * 
	 * @return
	 * @throws BancoobException
	 */
	List<StatusTransferenciaGrupoEconomicoVO> obterStatusTransferenciaGrupoEconomico(Integer idInstituicao) throws BancoobException;

	/**
	 * Cria automatica atraves de Job a criação automatica de Grupos Economicos para 
	 * pessoa fisica e juridica.
	 * @return
	 * @throws BancoobException
	 */
	List<GrupoEconomico> gerarGruposEconomicos() throws BancoobException;
	
	/**
	 * 
	 * @param idInstituicao
	 * @param idPessoa
	 * @return
	 */
	List<GrupoEconomico> obterListaGrupoEconomico(Integer idInstituicao);

}