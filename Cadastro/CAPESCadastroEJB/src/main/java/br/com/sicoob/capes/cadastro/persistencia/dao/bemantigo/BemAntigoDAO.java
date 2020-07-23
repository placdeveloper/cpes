package br.com.sicoob.capes.cadastro.persistencia.dao.bemantigo;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.persistencia.dao.EntidadeCadastroDaoIF;
import br.com.sicoob.capes.negocio.entidades.bemantigo.Bem;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * Interface que define os métodos de persistencia de Bem. 
 * @author Juan.Damasceno
 *
 */
@SuppressWarnings("rawtypes")
public interface BemAntigoDAO extends EntidadeCadastroDaoIF<Bem>{

	/**
	 * Lista os bens usando a pessoa como filtro.
	 * @param pessoa a pessoa que será usada como filtro.
	 * @return os bens usando a pessoa como filtro.
	 */
	List<Bem> listarPorPessoa(PessoaCompartilhamento pessoa);

	/**
	 * Listar bem imovel por pessoa.
	 *
	 * @param dto o valor de dto
	 * @return List
	 */
	List<Bem> listarBemImovelPorPessoa(ConsultaDto dto);

	/**
	 * Listar por bens diferentes de imovel por pessoa.
	 *
	 * @param dto o valor de dto
	 * @return List
	 */
	List<Bem> listarPorBensDiferentesDeImovelPorPessoa(ConsultaDto dto);

	/**
	 * Listar bem imovel rural por pessoa.
	 *
	 * @param pessoa o valor de pessoa
	 * @return List
	 */
	List<Bem> listarBemImovelRuralPorPessoa(PessoaCompartilhamento pessoa);
	
	/**
	 * Listar bem imovel rural vigente por pessoa.
	 *
	 * @param pessoa o valor de pessoa
	 * @return List
	 */
	List<Bem> listarBemImovelRuralVigentePorPessoa(PessoaCompartilhamento pessoa);
	
	List<Bem> listarBensPorIdBemNovo(ConsultaDto<Bem> criterios) throws BancoobException;

}