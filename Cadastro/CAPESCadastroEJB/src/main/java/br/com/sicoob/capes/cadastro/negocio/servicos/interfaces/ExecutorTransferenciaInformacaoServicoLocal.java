package br.com.sicoob.capes.cadastro.negocio.servicos.interfaces;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.negocio.dto.TransfInformacoesDTO;
import br.com.sicoob.capes.cadastro.negocio.servicos.ExecutorTransferenciaInformacaoServico;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;

public interface ExecutorTransferenciaInformacaoServicoLocal extends ExecutorTransferenciaInformacaoServico {

	/**
	 * Executa o processamento da solicita��o de transfer�ncia de informa��es.
	 * 
	 * @param dto
	 */
	void processar(TransfInformacoesDTO dto);

	/**
	 * M�todo local que atualiza as informa��es nos servi�os de destino, uma solicita��o por vez.
	 * 
	 * @param entidade
	 *            a entidade que ser� atualizada
	 * @param dto
	 *            o dto com as informa��es de atualiza��o
	 * @throws BancoobException
	 */
	void executarTransferencia(CAPESEntidade<?> entidade, TransfInformacoesDTO dto);

	
	/**
	 * M�todo local que atualiza as informa��es nos servi�os de destino, solicita��es em lote.
	 * 
	 * @param pesquisaEntidades
	 *            a entidade que ser� atualizada
	 * @param dto
	 *            o dto com as informa��es de atualiza��o
	 * @throws BancoobException
	 */
	void executarTransferenciaLote(ConsultaDto<? extends CAPESEntidade<?>> pesquisaEntidades, TransfInformacoesDTO dto);

}