/**
 * 
 */
package br.com.sicoob.capes.processamento.negocio.dominio.informacoesvencidas;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.negocio.delegates.MensagemDelegate;
import br.com.sicoob.capes.negocio.entidades.Mensagem;

/**
 * @author Erico.Junior
 *
 */
public class MensagemVencida extends InformacoesVencidas {

	/** O atributo delegate. */
	private transient MensagemDelegate delegate = getFabricaDelegate().criarMensagemDelegate();
	
	/**
	 * {@inheritDoc}
	 */
	public void excluir() throws BancoobException {
		
		ConsultaDto<Mensagem> criterios = new ConsultaDto<Mensagem>();
		criterios.setPagina(0);
		criterios.setTamanhoPagina(getTamanhoPagina());
		
		List<Mensagem> lista = delegate.listarVencidas(criterios);
		
		while(lista != null && !lista.isEmpty()) {
			getLogger().info("Excluindo... " + lista.size() + " mensagens vencidas.");
			delegate.excluir(lista);
			lista = delegate.listarVencidas(criterios);
		}
	}

}
