package br.com.sicoob.capes.cadastro.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.bem.TipoEstadoConservacaoBem;

public interface TipoEstadoConservacaoBemServico extends CAPESCadastroCrudServico<TipoEstadoConservacaoBem> {
	
	/**
	 * Obt�m os tipos de estado de conserva��o utilizando por tipo de bem
	 * 
	 * @param bemImovel
	 * @return
	 * @throws BancoobException
	 */
	List<TipoEstadoConservacaoBem> listar(boolean bemImovel) throws BancoobException;

}