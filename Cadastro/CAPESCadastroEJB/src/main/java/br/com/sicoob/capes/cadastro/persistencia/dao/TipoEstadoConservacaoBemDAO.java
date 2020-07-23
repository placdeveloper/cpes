package br.com.sicoob.capes.cadastro.persistencia.dao;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.bem.TipoEstadoConservacaoBem;

public interface TipoEstadoConservacaoBemDAO extends CAPESCadastroCrudDaoIF<TipoEstadoConservacaoBem> {
	
	/**
	 * Obtém os tipos de estado de conservação por tipo de bem
	 * 
	 * @param bemImovel
	 * @return
	 * @throws BancoobException
	 */
	List<TipoEstadoConservacaoBem> listar(boolean bemImovel) throws BancoobException;

}