/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.negocio.proxies.FonteRendaProxy;
import br.com.sicoob.capes.negocio.entidades.vigente.FonteRenda;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * Define as operações do serviço de manipulação de fontes de renda.
 * 
 * @author Erico.Junior
 */
public interface FonteRendaServico extends EntidadeCadastroServico<FonteRenda> {

	/**
	 * Listar rendas.
	 *
	 * @param pessoa o valor de pessoa
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	List<FonteRendaProxy> listarRendas(PessoaCompartilhamento pessoa) throws BancoobException;
	
	/**
	 * Exclui as rendas informadas.
	 * @throws BancoobException 
	 */
	void excluirRendas(List<FonteRenda> rendas) throws BancoobException;
	
	/**
	 * Lista as rendas que possuem a dataValidadeRenda menor que a data atual.
	 * @param criterios DTO com o número de registros por pagina e o número da página.
	 * @throws BancoobException 
	 */
	List<FonteRenda> listarVencidas(ConsultaDto<FonteRenda> criterios) throws BancoobException;
	
	/**
	 * Importa a renda para pessoa;
	 * @param renda
	 * @return
	 * @throws BancoobException
	 */
	FonteRenda importar(FonteRenda renda) throws BancoobException;
	
	/**
	 * 
	 * @param renda
	 * @return
	 * @throws BancoobException
	 */
	String obterMensagensValidacao(FonteRenda renda) throws BancoobException;
	
	/**
	 * Atualiza Codigo Tipo Empresa com base na renda Anual!
	 * @param fonteRenda
	 * @param exclusao
	 * @throws BancoobException
	 */
	void atualizarTipoEmpresaIdeal(FonteRenda fonteRenda, boolean exclusao) throws BancoobException;

}
