// 29/10/2013 - 11:17:33
package br.com.sicoob.capes.comum.negocio.enums;

import static br.com.sicoob.capes.comum.negocio.enums.TipoAnotacaoReplicavelEnum.FALECIDO;
import static br.com.sicoob.capes.comum.negocio.enums.TipoAnotacaoReplicavelEnum.PESSOA_POLITICAMENTE_EXPOSTA;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.apache.commons.lang.math.NumberUtils;
import org.junit.Test;

/**
 * @author Rodrigo.Chaves
 */
public class TipoAnotacaoReplicavelEnumTest extends 
AbstractEnumTest {

	/**
	 * O método Test get cod tipo anotacao.
	 */
	@Test
	public void testGetCodTipoAnotacao() {
		assertEquals(FALECIDO.getCodTipoAnotacao(), Integer.valueOf(505));
		assertEquals(PESSOA_POLITICAMENTE_EXPOSTA.getCodTipoAnotacao(), Integer.valueOf(102));
	}

	/**
	 * O método Test get descricao.
	 */
	@Test
	public void testGetDescricao() {
		assertEquals(FALECIDO.getDescricao(), "Falecido (a)");
		assertEquals(PESSOA_POLITICAMENTE_EXPOSTA.getDescricao(), "Pessoa Politicamente Exposta");
	}

	/**
	 * O método Test value of.
	 */
	@Test
	public void testValueOf() {
		assertEquals(FALECIDO, TipoAnotacaoReplicavelEnum.valueOf(505));
		assertEquals(PESSOA_POLITICAMENTE_EXPOSTA,
				TipoAnotacaoReplicavelEnum.valueOf(102));
	}

	/**
	 * O método Test value of inexistente.
	 */
	@Test
	public void testValueOfInexistente() {
		assertNull(TipoAnotacaoReplicavelEnum.valueOf(NumberUtils.INTEGER_MINUS_ONE));
	}

	/**
	 * {@inheritDoc}
	 */
	@Test
	public void testMetodosHerdados() {
		assertEquals(Integer.valueOf(2), Integer.valueOf(TipoAnotacaoReplicavelEnum.values().length));
		assertNotNull(TipoAnotacaoReplicavelEnum.valueOf("FALECIDO"));
		assertNotNull(TipoAnotacaoReplicavelEnum.valueOf("PESSOA_POLITICAMENTE_EXPOSTA"));
	}	
}
