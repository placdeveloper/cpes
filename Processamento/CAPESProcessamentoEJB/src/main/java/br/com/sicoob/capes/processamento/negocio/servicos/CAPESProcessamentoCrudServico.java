package br.com.sicoob.capes.processamento.negocio.servicos;

import java.io.Serializable;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.servicos.BancoobCrudServico;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidadeBase;

/**
 * Interface que define um contrato padrao para as operacoes de CRUD de todo o
 * sistema CapesProcessamento
 * 
 * @author Stefanini IT Solutions
 */
public interface CAPESProcessamentoCrudServico<T extends CAPESEntidadeBase<?>> extends
		CAPESProcessamentoServico, BancoobCrudServico<T> {

	/**
	 * Obter.
	 *
	 * @param chave o valor de chave
	 * @param numeroCooperativa o valor de numero cooperativa
	 * @return T
	 * @throws BancoobException lança a exceção BancoobException
	 */
	T obter(Serializable chave, Integer numeroCooperativa) throws BancoobException;

}
