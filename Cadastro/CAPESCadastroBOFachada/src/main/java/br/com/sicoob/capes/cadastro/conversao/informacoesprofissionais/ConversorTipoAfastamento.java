/**
 * 
 */
package br.com.sicoob.capes.cadastro.conversao.informacoesprofissionais;

import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.vo.informacoesprofissionais.TipoAfastamentoVO;
import br.com.sicoob.capes.negocio.entidades.TipoAfastamento;

/**
 * @author Erico.Junior
 *
 */
public final class ConversorTipoAfastamento {
	
	/**
	 * Instancia um novo ConversorTipoAfastamento.
	 */
	private ConversorTipoAfastamento(){
	}

	/**
	 * Converter.
	 *
	 * @param tipo o valor de tipo
	 * @return TipoAfastamentoVO
	 */
	public static TipoAfastamentoVO converter(TipoAfastamento tipo){
		
		TipoAfastamentoVO vo = new TipoAfastamentoVO();
		if(tipo != null) {
			vo.setCodigo(tipo.getCodigo());
			vo.setDescricao(tipo.getDescricao());
		}
		return vo;
	}
	
	
	/**
	 * Obter tipo afastamento.
	 *
	 * @param codigo o valor de codigo
	 * @return TipoAfastamento
	 */
	public static TipoAfastamento obterTipoAfastamento(Short codigo) {
		
		TipoAfastamento tipo = null;
		if(codigo != null) {
			tipo = new TipoAfastamento();
			tipo.setCodigo(codigo);
		}
		return tipo;
	}
	
	/**
	 * Converter.
	 *
	 * @param lista o valor de lista
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public static List<TipoAfastamentoVO> converter(List<TipoAfastamento> lista) 
			throws BancoobException {

		List<TipoAfastamentoVO> retorno = new ArrayList<TipoAfastamentoVO>();
		
		if(lista != null) {
			for (TipoAfastamento tipo : lista) {
				retorno.add(ConversorTipoAfastamento.converter(tipo));
			}
		}
		
		return retorno;
	}

}
