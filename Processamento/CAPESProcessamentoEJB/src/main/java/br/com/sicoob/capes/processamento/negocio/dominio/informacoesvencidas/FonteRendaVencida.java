/**
 * 
 */
package br.com.sicoob.capes.processamento.negocio.dominio.informacoesvencidas;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.negocio.delegates.FonteRendaDelegate;
import br.com.sicoob.capes.negocio.entidades.vigente.FonteRenda;

/**
 * @author Erico.Junior
 *
 */
public class FonteRendaVencida extends InformacoesVencidas {

	/** O atributo delegate. */
	private transient FonteRendaDelegate delegate = getFabricaDelegate().criarFonteRendaDelegate();
	
	/**
	 * {@inheritDoc}
	 */
	public void excluir() throws BancoobException {
		
		ConsultaDto<FonteRenda> criterios = new ConsultaDto<FonteRenda>();
		criterios.setPagina(0);
		criterios.setTamanhoPagina(getTamanhoPagina());
		
		List<FonteRenda> lista = delegate.listarVencidas(criterios);
		
		while(lista != null && !lista.isEmpty()) {
			getLogger().info("Excluindo... " + lista.size() + " rendas vencidas.");
			delegate.excluir(lista);
			lista = delegate.listarVencidas(criterios);
		}
	}
	
}
