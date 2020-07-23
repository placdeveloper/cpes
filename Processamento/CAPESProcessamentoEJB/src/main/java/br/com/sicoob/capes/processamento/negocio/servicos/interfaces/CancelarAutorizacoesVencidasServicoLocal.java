package br.com.sicoob.capes.processamento.negocio.servicos.interfaces;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.Autorizacao;

public interface CancelarAutorizacoesVencidasServicoLocal {
	
	/**
	 * O método Cancelar autorizacao vencida.
	 *
	 * @param autorizacao o valor de autorizacao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	void cancelarAutorizacaoVencida(Autorizacao autorizacao, String justificativa) throws BancoobException;

}
