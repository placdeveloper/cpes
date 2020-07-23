package br.com.sicoob.capes.processamento.negocio.servicos.interfaces;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.Autorizacao;

public interface CancelarAutorizacoesVencidasServicoLocal {
	
	/**
	 * O m�todo Cancelar autorizacao vencida.
	 *
	 * @param autorizacao o valor de autorizacao
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	void cancelarAutorizacaoVencida(Autorizacao autorizacao, String justificativa) throws BancoobException;

}
