package br.com.sicoob.capes.api.inclusao.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.servicos.BancoobServico;
import br.com.sicoob.capes.api.inclusao.negocio.dto.PlataformaContabilDTO;

/**
 * Classe com os servi�os exclusivos para a Plataforma Contabil;
 *
 * @author Valdemar.xavier
 */
public interface PlataformaContabilServico extends BancoobServico {

	/**
	 * Atualiza faturamento e patrimonio das institui��es.
	 * 
	 * @param idInstituicao
	 *            O idInstituicao, na qual se refere a cooperativa a atualizar os dados.
	 * @param valorPatrimonio
	 *            O Valor do Patrimonio bruto da cooperativa, para ser informado na fonte de renda da cooperativa.
	 * @param valorFaturamento
	 * 			  O Valor do Faturamento bruto anual da Cooperativa, onde ser� convertido para mensal.
	 * @throws BancoobException
	 *             Caso ocorra alguma exce��o.
	 */
	boolean atualizarDadosContabil(PlataformaContabilDTO dto) throws BancoobException;
	
}