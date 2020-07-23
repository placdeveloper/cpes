/**
 * 
 */
package br.com.sicoob.capes.cadastro.conversao.informacoesprofissionais;

import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.vo.informacoesprofissionais.TipoFuncionarioVO;
import br.com.sicoob.capes.negocio.entidades.TipoFuncionario;

/**
 * @author Erico.Junior
 *
 */
public final class ConversorTipoFuncionario {

	/**
	 * Instancia um novo ConversorTipoFuncionario.
	 */
	private ConversorTipoFuncionario(){
	}
	
	/**
	 * Converter.
	 *
	 * @param tipo o valor de tipo
	 * @return TipoFuncionarioVO
	 */
	public static TipoFuncionarioVO converter(TipoFuncionario tipo){
		
		TipoFuncionarioVO vo = new TipoFuncionarioVO();
		if(tipo != null) {
			vo.setCodigo(tipo.getCodigo());
			vo.setDescricao(tipo.getDescricao());
		}
		return vo;
	}
	
	
	/**
	 * Obter tipo funcionario.
	 *
	 * @param codigo o valor de codigo
	 * @return TipoFuncionario
	 */
	public static TipoFuncionario obterTipoFuncionario(Short codigo) {
		
		TipoFuncionario tipo = null;
		if(codigo != null) {
			tipo = new TipoFuncionario();
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
	public static List<TipoFuncionarioVO> converter(List<TipoFuncionario> lista)
			throws BancoobException {

		List<TipoFuncionarioVO> retorno = new ArrayList<TipoFuncionarioVO>();
		
		if(lista != null) {
			for (TipoFuncionario tipo : lista) {
				retorno.add(ConversorTipoFuncionario.converter(tipo));
			}
		}
		
		return retorno;
	}	
}
