// 28/10/2013 - 10:51:50
package br.com.sicoob.capes.cadastro.negocio.enums;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

/**
 * @author Rodrigo.Chaves
 */
public class EstadoCivilEnumTest {

	/**
	 * O método Test get codigo.
	 */
	@Test
	public void testGetCodigo() {
		assertEquals(EstadoCivilEnum.SOLTEIRO.getCodigo(), Short.valueOf((short) 1));
		assertEquals(EstadoCivilEnum.CASADO.getCodigo(), Short.valueOf((short) 2));
		assertEquals(EstadoCivilEnum.VIUVO.getCodigo(), Short.valueOf((short) 3));
		assertEquals(EstadoCivilEnum.DIVORCIADO.getCodigo(), Short.valueOf((short) 5));
		assertEquals(EstadoCivilEnum.SEPARADO.getCodigo(), Short.valueOf((short) 6));
		assertEquals(EstadoCivilEnum.UNIAO_ESTAVEL.getCodigo(), Short.valueOf((short) 7));
	}

	/**
	 * O método Test get descricao.
	 */
	@Test
	public void testGetDescricao() {
		assertEquals(EstadoCivilEnum.CASADO.getDescricao(), "CASADO(A)");
		assertEquals(EstadoCivilEnum.SOLTEIRO.getDescricao(), "SOLTEIRO(A)");
		assertEquals(EstadoCivilEnum.CASADO.getDescricao(), "CASADO(A)");
		assertEquals(EstadoCivilEnum.VIUVO.getDescricao(), "VIÚVO(A)");
		assertEquals(EstadoCivilEnum.DIVORCIADO.getDescricao(), "DIVORCIADO(A)");
		assertEquals(EstadoCivilEnum.SEPARADO.getDescricao(), "SEPARADO(A)");
		assertEquals(EstadoCivilEnum.UNIAO_ESTAVEL.getDescricao(), "UNIÃO ESTÁVEL");
	}
	
	/**
	 * O método Test metodos herdados.
	 */
	@Test
	public void testMetodosHerdados() {
		assertEquals(Integer.valueOf(6), Integer.valueOf(EstadoCivilEnum.values().length));
		assertNotNull(EstadoCivilEnum.valueOf("SOLTEIRO"));
		assertNotNull(EstadoCivilEnum.valueOf("CASADO"));
		assertNotNull(EstadoCivilEnum.valueOf("VIUVO"));
		assertNotNull(EstadoCivilEnum.valueOf("DIVORCIADO"));
		assertNotNull(EstadoCivilEnum.valueOf("SEPARADO"));
		assertNotNull(EstadoCivilEnum.valueOf("UNIAO_ESTAVEL"));
	}
	
}
