/**
 * 
 */
package br.com.sicoob.capes.processamento.negocio.dominio.informacoesvencidas;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.negocio.delegates.CertidaoDelegate;
import br.com.sicoob.capes.negocio.entidades.vigente.Certidao;

/**
 * @author Erico.Junior
 *
 */
public class CertidaoVencida extends InformacoesVencidas {

	/** O atributo delegate. */
	private transient CertidaoDelegate delegate = getFabricaDelegate().criarCertidaoDelegate();
	
	/**
	 * {@inheritDoc}
	 */
	public void excluir() throws BancoobException {
		
		ConsultaDto<Certidao> criterios = new ConsultaDto<Certidao>();
		criterios.setPagina(0);
		criterios.setTamanhoPagina(getTamanhoPagina());
		
		List<Certidao> lista = delegate.listarVencidas(criterios);
		
		while(lista != null && !lista.isEmpty()) {
			getLogger().info("Excluindo... " + lista.size() + " certidoes vencidas.");
			delegate.excluir(lista);
			lista = delegate.listarVencidas(criterios);
		}
	}


}
