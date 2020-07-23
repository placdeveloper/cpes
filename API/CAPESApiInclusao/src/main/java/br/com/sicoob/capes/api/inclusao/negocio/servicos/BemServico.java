package br.com.sicoob.capes.api.inclusao.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.inclusao.negocio.dto.BemDTO;
import br.com.sicoob.capes.api.inclusao.negocio.dto.BemProprietarioDTO;

/**
 * A Interface BemServico.
 *
 * @author bruno.carneiro
 */
public interface BemServico extends CAPESApiInclusaoServico<BemDTO> {

	/**
	 * Inclui um bem do tipo "sem patrimônio" para a pessoa informada.
	 * 
	 * @param dto
	 * @throws BancoobException
	 */
	void incluirSemPatrimonio(BemDTO dto) throws BancoobException;
	
	/**
	 * Inclui um bem do tipo "recusou-se a informar" para a pessoa informada.
	 * 
	 * @param dto
	 * @throws BancoobException
	 */
	void incluirRecusouInformar(BemDTO dto) throws BancoobException;
	
	/**
	 * Faz a troca de proprietários de um bem.
	 * 
	 * @param dto
	 * @param proprietarios
	 * @throws BancoobException
	 */
	void alterarProprietarios(BemDTO dto, List<BemProprietarioDTO> proprietarios) throws BancoobException;

}