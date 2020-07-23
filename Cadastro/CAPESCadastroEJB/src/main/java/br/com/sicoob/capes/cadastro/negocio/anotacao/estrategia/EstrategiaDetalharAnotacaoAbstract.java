/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.anotacao.estrategia;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.comum.negocio.vo.InstituicaoVO;
import br.com.sicoob.capes.integracao.negocio.delegates.CAPESIntegracaoFabricaDelegates;
import br.com.sicoob.capes.integracao.negocio.delegates.SCIIntegracaoDelegate;
import br.com.sicoob.capes.negocio.entidades.Instituicao;

/**
 * Superclasse para as estrat�gias do detalhamento de anota��es.
 * 
 * @author Erico.Junior
 */
public abstract class EstrategiaDetalharAnotacaoAbstract implements EstrategiaDetalharAnotacao {

	/** O atributo sciDelegate. */
	private final transient SCIIntegracaoDelegate sciDelegate = 
			CAPESIntegracaoFabricaDelegates.getInstance().criarSCIIntegracaoDelegate();

	/**
	 * Recupera o n�mero da cooperativa a partir da institui��o informado.
	 * @param instituicao A institui��o da qual se deseja o n�mero da cooperativa.
	 * @return o n�mero da cooperativa a partir da institui��o informado.
	 * @throws BancoobException Caso ocorra alguma exce��o na consulta.
	 */
	protected String recuperarNumeroCooperativa(Instituicao instituicao) throws BancoobException {

		String numCooperativa = "";

		if (instituicao != null && instituicao.getIdInstituicao() != null) {
			InstituicaoVO sci = sciDelegate.obterInstituicaoPorId(instituicao.getIdInstituicao());
			if (sci != null) {
				numCooperativa = String.valueOf(sci.getNumero());
			}
		}
		
		return numCooperativa;
	}

}
