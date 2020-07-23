package br.com.sicoob.capes.cadastro.negocio.servicos.bemantigo;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.negocio.servicos.EntidadeCadastroServico;
import br.com.sicoob.capes.negocio.entidades.bemantigo.Bem;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * Interface que define os métodos de negocio de Bem
 * @author Juan.Damasceno
 *
 */
public interface BemAntigoServico extends EntidadeCadastroServico<Bem> {

	/**
	 * Lista os bens usando a pessoa como filtro.
	 * @param pessoa a pessoa que será usada como filtro.
	 * @return os bens usando a pessoa como filtro.
	 * @throws BancoobException 
	 */
	List<Bem> listarPorPessoa(PessoaCompartilhamento pessoa) throws BancoobException;

	/**
	 * Listar bem imovel por pessoa.
	 *
	 * @param consultaDto o valor de consulta dto
	 * @return List
	 */
	List<Bem> listarBemImovelPorPessoa(ConsultaDto<?> consultaDto);

	/**
	 * Listar por bens diferentes de imovel por pessoa.
	 *
	 * @param consultaDto o valor de consulta dto
	 * @return List
	 */
	List<Bem> listarPorBensDiferentesDeImovelPorPessoa(ConsultaDto<?> consultaDto);

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
	 * @throws BancoobException lança a exceção BancoobException
	 */
	List<Bem> listarBemImovelRuralVigentePorPessoa(PessoaCompartilhamento pessoa) throws BancoobException;
	
	/**
	 * Importa o bem para pessoa;
	 * @param bem
	 * @return
	 * @throws BancoobException
	 */
	Bem importar(Bem bem) throws BancoobException;

	/**
	 * Cria um registro com o subtipo de "Nao possui patrimonio"
	 * @param idPessoaCompartilhamento
	 * @throws BancoobException
	 */
	void criarRegistroSemPatrimonio(Long idPessoaCompartilhamento) throws BancoobException;
	
	/**
	 * Cria um registro com o subtipo de "Nao possui patrimonio"
	 * 
	 * @param idPessoaCompartilhamento
	 * @param idBemNovo
	 * @throws BancoobException
	 */
	void criarRegistroSemPatrimonio(Long idPessoaCompartilhamento, Long idBemNovo) throws BancoobException;
	
}