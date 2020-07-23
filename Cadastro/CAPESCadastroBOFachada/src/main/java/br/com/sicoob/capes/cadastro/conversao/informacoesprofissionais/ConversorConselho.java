/**
 * 
 */
package br.com.sicoob.capes.cadastro.conversao.informacoesprofissionais;

import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.vo.informacoesprofissionais.ConselhoRegionalVO;
import br.com.sicoob.capes.negocio.entidades.ConselhoRegional;

/**
 * @author Erico.Junior
 *
 */
public final class ConversorConselho {

	/**
	 * Instancia um novo ConversorConselho.
	 */
	private ConversorConselho() {
	}
	
	/**
	 * Converter.
	 *
	 * @param conselho o valor de conselho
	 * @return ConselhoRegionalVO
	 */
	public static ConselhoRegionalVO converter(ConselhoRegional conselho){
		
		ConselhoRegionalVO vo = new ConselhoRegionalVO();
		if(conselho != null) {
			vo.setCodigo(conselho.getCodigo());
			vo.setDescricao(conselho.getDescricao());
		}
		
		return vo;
	}
	
	/**
	 * Obter conselho.
	 *
	 * @param codigo o valor de codigo
	 * @return ConselhoRegional
	 */
	public static ConselhoRegional obterConselho(Short codigo) {
		
		ConselhoRegional conselho = null;
		if(codigo != null) {
			conselho = new ConselhoRegional();
			conselho.setCodigo(codigo);
		}
		return conselho;
	}
	
	/**
	 * Converter.
	 *
	 * @param lista o valor de lista
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public static List<ConselhoRegionalVO> converter(List<ConselhoRegional> lista) 
			throws BancoobException {

		List<ConselhoRegionalVO> retorno = new ArrayList<ConselhoRegionalVO>();
		
		if(lista != null) {
			for (ConselhoRegional conselhoRegional : lista) {
				retorno.add(ConversorConselho.converter(conselhoRegional));
			}
		}
		
		return retorno;
	}	
}
