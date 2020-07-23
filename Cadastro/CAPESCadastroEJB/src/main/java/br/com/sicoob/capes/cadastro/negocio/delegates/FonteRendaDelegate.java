/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.negocio.proxies.FonteRendaProxy;
import br.com.sicoob.capes.cadastro.negocio.servicos.FonteRendaServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.vigente.FonteRenda;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * Business delegate para as fontes de renda.
 * @author Erico.Junior
 */
public class FonteRendaDelegate extends
	EntidadeCadastroDelegate<FonteRenda, FonteRendaServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected FonteRendaServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarFonteRendaServico();
	}
	
	/**
	 * Lista as rendas da pessoa informada.
	 * @param pessoa A pessoa da qual se deseja a lista de rendas.
	 * @return as rendas da pessoa informada.
	 * @throws BancoobException Caso ocorra alguma exceção;
	 */
	public List<FonteRendaProxy> listarRendas(PessoaCompartilhamento pessoa) throws BancoobException {
		return getServico().listarRendas(pessoa);
	}
	
	/**
	 * Exclui as rendas informadas.
	 * @throws BancoobException 
	 */
	public void excluir(List<FonteRenda> rendas) throws BancoobException {
		getServico().excluirRendas(rendas);
	}
	
	/**
	 * Lista as rendas que possuem a dataValidadeRenda menor que a data atual.
	 * @param criterios DTO com o número de registros por pagina e o número da página.
	 * @throws BancoobException 
	 */
	public List<FonteRenda> listarVencidas(ConsultaDto<FonteRenda> criterios) throws BancoobException {
		return getServico().listarVencidas(criterios);
	}	
	
	/**
	 * Importa a renda para pessoa;
	 * @param renda
	 * @return
	 * @throws BancoobException
	 */
	public FonteRenda importar(FonteRenda renda) throws BancoobException {
		return getServico().importar(renda);
	}	
	
	/**
	 * Obter mensagens validacao.
	 *
	 * @param renda o valor de renda
	 * @return String
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public String obterMensagensValidacao(FonteRenda renda) throws BancoobException {
		return getServico().obterMensagensValidacao(renda);
	}
	/**
	 * Seta Codigo Tipo Empresa Baseada na Renda Anual
	 * @param fonteRenda
	 * @param autorizacao
	 * @throws BancoobException
	 */
	public void atualizarTipoEmpresaIdeal(FonteRenda fonteRenda, boolean exclusao) throws BancoobException{
		getServico().atualizarTipoEmpresaIdeal(fonteRenda, exclusao);
	}
}
