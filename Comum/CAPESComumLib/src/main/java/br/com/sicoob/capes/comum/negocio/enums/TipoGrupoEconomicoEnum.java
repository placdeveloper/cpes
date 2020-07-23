package br.com.sicoob.capes.comum.negocio.enums;

import br.com.bancoob.excecao.BancoobRuntimeException;

/**
 * 
 * @author Paulo.Stoppa
 *
 */
public enum TipoGrupoEconomicoEnum {

	AUTOMATICO((short) 1), MANUAL((short) 2);

	/** O atributo id tipo localidade. */
	private final Short codigo;

	private TipoGrupoEconomicoEnum(Short codigo) {
		this.codigo = codigo;
	}
	
	public Short getCodigo() {
		return codigo;
	}
	
	/**
	 * 
	 * @param codigo
	 * @return
	 */
	public static TipoGrupoEconomicoEnum obterPorCodigo(Short codigo) {
		switch (codigo) {
		case (short) 1:
			return TipoGrupoEconomicoEnum.AUTOMATICO;
		case (short) 2:
			return TipoGrupoEconomicoEnum.MANUAL;
		default:
			throw new BancoobRuntimeException("Tipo de Grupo não esperado");
		}
	}
	
}
