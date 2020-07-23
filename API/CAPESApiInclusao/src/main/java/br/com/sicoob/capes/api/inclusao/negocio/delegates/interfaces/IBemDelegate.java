package br.com.sicoob.capes.api.inclusao.negocio.delegates.interfaces;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.inclusao.negocio.dto.BemDTO;
import br.com.sicoob.capes.api.inclusao.negocio.dto.BemProprietarioDTO;

/**
 * A interfacee BemDelegate.
 * 
 * @author bruno.carneiro
 */
public interface IBemDelegate extends ICAPESApiInclusaoDelegate<BemDTO> {

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
	 * Faz a alteração dos proprietários do bem.
	 * 
	 * @param dto
	 * @param proprietarios
	 * @throws BancoobException
	 */
	void alterarProprietarios(BemDTO dto, List<BemProprietarioDTO> proprietarios) throws BancoobException;

}