package br.com.sicoob.capes.cadastro.negocio.servicos.interfaces;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.negocio.dto.TransfInformacoesDTO;
import br.com.sicoob.capes.cadastro.negocio.servicos.ExecutorTransferenciaInformacaoServico;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;

public interface ExecutorTransferenciaInformacaoServicoLocal extends ExecutorTransferenciaInformacaoServico {

	/**
	 * Executa o processamento da solicitação de transferência de informações.
	 * 
	 * @param dto
	 */
	void processar(TransfInformacoesDTO dto);

	/**
	 * Método local que atualiza as informações nos serviços de destino, uma solicitação por vez.
	 * 
	 * @param entidade
	 *            a entidade que será atualizada
	 * @param dto
	 *            o dto com as informações de atualização
	 * @throws BancoobException
	 */
	void executarTransferencia(CAPESEntidade<?> entidade, TransfInformacoesDTO dto);

	
	/**
	 * Método local que atualiza as informações nos serviços de destino, solicitações em lote.
	 * 
	 * @param pesquisaEntidades
	 *            a entidade que será atualizada
	 * @param dto
	 *            o dto com as informações de atualização
	 * @throws BancoobException
	 */
	void executarTransferenciaLote(ConsultaDto<? extends CAPESEntidade<?>> pesquisaEntidades, TransfInformacoesDTO dto);

}