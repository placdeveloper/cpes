// 30/10/2013 - 11:41:16
package br.com.sicoob.capes.cadastro.negocio.enums;

import static br.com.sicoob.capes.cadastro.negocio.enums.TipoRelacionamentoPessoaEnum.ADMINISTRADOR;
import static br.com.sicoob.capes.cadastro.negocio.enums.TipoRelacionamentoPessoaEnum.CONJUGE;
import static br.com.sicoob.capes.cadastro.negocio.enums.TipoRelacionamentoPessoaEnum.CURADOR;
import static br.com.sicoob.capes.cadastro.negocio.enums.TipoRelacionamentoPessoaEnum.INVENTARIANTE;
import static br.com.sicoob.capes.cadastro.negocio.enums.TipoRelacionamentoPessoaEnum.PROCURADOR;
import static br.com.sicoob.capes.cadastro.negocio.enums.TipoRelacionamentoPessoaEnum.REPRESENTATE_LEGAL;
import static br.com.sicoob.capes.cadastro.negocio.enums.TipoRelacionamentoPessoaEnum.RESPONSAVEL_LEGAL;
import static br.com.sicoob.capes.cadastro.negocio.enums.TipoRelacionamentoPessoaEnum.SOCIO;
import static br.com.sicoob.capes.cadastro.negocio.enums.TipoRelacionamentoPessoaEnum.SOCIO_ADMINISTRADOR;
import static br.com.sicoob.capes.cadastro.negocio.enums.TipoRelacionamentoPessoaEnum.TUTOR;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

/**
 * @author Rodrigo.Chaves
 */
public class TipoRelacionamentoPessoaEnumTest {

	/**
	 * Test method for {@link br.com.sicoob.capes.cadastro.negocio.enums.TipoRelacionamentoPessoaEnum#getCodigo()}.
	 */
	@Test
	public void testGetCodigo() {
		assertEquals(Short.valueOf((short) 1), ADMINISTRADOR.getCodigo());
		assertEquals(Short.valueOf((short) 2), CONJUGE.getCodigo());
		assertEquals(Short.valueOf((short) 3), CURADOR.getCodigo());
		assertEquals(Short.valueOf((short) 4), INVENTARIANTE.getCodigo());
		assertEquals(Short.valueOf((short) 5), PROCURADOR.getCodigo());
		assertEquals(Short.valueOf((short) 6), REPRESENTATE_LEGAL.getCodigo());
		assertEquals(Short.valueOf((short) 7), RESPONSAVEL_LEGAL.getCodigo());
		assertEquals(Short.valueOf((short) 8), SOCIO.getCodigo());
		assertEquals(Short.valueOf((short) 9), SOCIO_ADMINISTRADOR.getCodigo());
		assertEquals(Short.valueOf((short) 10), TUTOR.getCodigo());
	}

	/**
	 * Test method for {@link br.com.sicoob.capes.cadastro.negocio.enums.TipoRelacionamentoPessoaEnum#getDescricao()}.
	 */
	@Test
	public void testGetDescricao() {
		assertEquals("ADMINISTRADOR", ADMINISTRADOR.getDescricao());
		assertEquals("CÔNJUGE", CONJUGE.getDescricao());
		assertEquals("CURADOR", CURADOR.getDescricao());
		assertEquals("INVENTARIANTE", INVENTARIANTE.getDescricao());
		assertEquals("PROCURADOR", PROCURADOR.getDescricao());
		assertEquals("REPRESENTATE LEGAL", REPRESENTATE_LEGAL.getDescricao());
		assertEquals("RESPONSÁVEL LEGAL", RESPONSAVEL_LEGAL.getDescricao());
		assertEquals("SÓCIO", SOCIO.getDescricao());
		assertEquals("SÓCIO/ADMINISTRADOR", SOCIO_ADMINISTRADOR.getDescricao());
		assertEquals("TUTOR", TUTOR.getDescricao());
	}

	/**
	 * O método Test metodos herdados.
	 */
	@Test
	public void testMetodosHerdados() {
		assertEquals(Integer.valueOf(10), Integer.valueOf(TipoRelacionamentoPessoaEnum.values().length));
		assertNotNull(TipoRelacionamentoPessoaEnum.valueOf("ADMINISTRADOR"));
		assertNotNull(TipoRelacionamentoPessoaEnum.valueOf("CONJUGE"));
		assertNotNull(TipoRelacionamentoPessoaEnum.valueOf("CURADOR"));
		assertNotNull(TipoRelacionamentoPessoaEnum.valueOf("INVENTARIANTE"));
		assertNotNull(TipoRelacionamentoPessoaEnum.valueOf("PROCURADOR"));
		assertNotNull(TipoRelacionamentoPessoaEnum.valueOf("REPRESENTATE_LEGAL"));
		assertNotNull(TipoRelacionamentoPessoaEnum.valueOf("RESPONSAVEL_LEGAL"));
		assertNotNull(TipoRelacionamentoPessoaEnum.valueOf("SOCIO"));
		assertNotNull(TipoRelacionamentoPessoaEnum.valueOf("SOCIO_ADMINISTRADOR"));
		assertNotNull(TipoRelacionamentoPessoaEnum.valueOf("TUTOR"));
	}
	
}
