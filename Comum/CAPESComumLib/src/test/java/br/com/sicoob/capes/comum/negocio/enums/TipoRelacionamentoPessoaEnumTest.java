package br.com.sicoob.capes.comum.negocio.enums;

import org.junit.Assert;
import org.junit.Test;

/**
 * A Classe TipoRelacionamentoPessoaEnumTest.
 */
public class TipoRelacionamentoPessoaEnumTest extends AbstractEnumTest {


	/**
	 * O método Test get codigo.
	 */
	@Test
	public void testGetCodigo() {

		Assert.assertEquals(Short.valueOf((short) 1), TipoRelacionamentoPessoaEnum.ADMINISTRADOR.getCodigo());
		Assert.assertEquals(Short.valueOf((short) 2), TipoRelacionamentoPessoaEnum.CONJUGE.getCodigo());
		Assert.assertEquals(Short.valueOf((short) 3), TipoRelacionamentoPessoaEnum.CURADOR.getCodigo());
		Assert.assertEquals(Short.valueOf((short) 4), TipoRelacionamentoPessoaEnum.INVENTARIANTE.getCodigo());
		Assert.assertEquals(Short.valueOf((short) 5), TipoRelacionamentoPessoaEnum.PROCURADOR.getCodigo());
		Assert.assertEquals(Short.valueOf((short) 6), TipoRelacionamentoPessoaEnum.REPRESENTATE_LEGAL.getCodigo());
		Assert.assertEquals(Short.valueOf((short) 7), TipoRelacionamentoPessoaEnum.RESPONSAVEL_LEGAL.getCodigo());
		Assert.assertEquals(Short.valueOf((short) 8), TipoRelacionamentoPessoaEnum.SOCIO.getCodigo());
		Assert.assertEquals(Short.valueOf((short) 9), TipoRelacionamentoPessoaEnum.SOCIO_ADMINISTRADOR.getCodigo());
		Assert.assertEquals(Short.valueOf((short) 10), TipoRelacionamentoPessoaEnum.TUTOR.getCodigo());
	}

	/**
	 * O método Test get descricao.
	 */
	@Test
	public void testGetDescricao() {

		Assert.assertEquals("ADMINISTRADOR", TipoRelacionamentoPessoaEnum.ADMINISTRADOR.getDescricao());
		Assert.assertEquals("CÔNJUGE", TipoRelacionamentoPessoaEnum.CONJUGE.getDescricao());
		Assert.assertEquals("CURADOR", TipoRelacionamentoPessoaEnum.CURADOR.getDescricao());
		Assert.assertEquals("INVENTARIANTE", TipoRelacionamentoPessoaEnum.INVENTARIANTE.getDescricao());
		Assert.assertEquals("PROCURADOR", TipoRelacionamentoPessoaEnum.PROCURADOR.getDescricao());
		Assert.assertEquals("REPRESENTATE LEGAL", TipoRelacionamentoPessoaEnum.REPRESENTATE_LEGAL.getDescricao());
		Assert.assertEquals("RESPONSÁVEL LEGAL", TipoRelacionamentoPessoaEnum.RESPONSAVEL_LEGAL.getDescricao());
		Assert.assertEquals("SÓCIO", TipoRelacionamentoPessoaEnum.SOCIO.getDescricao());
		Assert.assertEquals("SÓCIO/ADMINISTRADOR", TipoRelacionamentoPessoaEnum.SOCIO_ADMINISTRADOR.getDescricao());
		Assert.assertEquals("TUTOR", TipoRelacionamentoPessoaEnum.TUTOR.getDescricao());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Test
	public void testMetodosHerdados() {

		Assert.assertEquals(10, TipoRelacionamentoPessoaEnum.values().length);
		Assert.assertEquals(TipoRelacionamentoPessoaEnum.ADMINISTRADOR, TipoRelacionamentoPessoaEnum.valueOf("ADMINISTRADOR"));
		Assert.assertEquals(TipoRelacionamentoPessoaEnum.CONJUGE, TipoRelacionamentoPessoaEnum.valueOf("CONJUGE"));
		Assert.assertEquals(TipoRelacionamentoPessoaEnum.CURADOR, TipoRelacionamentoPessoaEnum.valueOf("CURADOR"));
		Assert.assertEquals(TipoRelacionamentoPessoaEnum.INVENTARIANTE, TipoRelacionamentoPessoaEnum.valueOf("INVENTARIANTE"));
		Assert.assertEquals(TipoRelacionamentoPessoaEnum.PROCURADOR, TipoRelacionamentoPessoaEnum.valueOf("PROCURADOR"));
		Assert.assertEquals(TipoRelacionamentoPessoaEnum.REPRESENTATE_LEGAL, TipoRelacionamentoPessoaEnum.valueOf("REPRESENTATE_LEGAL"));
		Assert.assertEquals(TipoRelacionamentoPessoaEnum.RESPONSAVEL_LEGAL, TipoRelacionamentoPessoaEnum.valueOf("RESPONSAVEL_LEGAL"));
		Assert.assertEquals(TipoRelacionamentoPessoaEnum.SOCIO, TipoRelacionamentoPessoaEnum.valueOf("SOCIO"));
		Assert.assertEquals(TipoRelacionamentoPessoaEnum.SOCIO_ADMINISTRADOR, TipoRelacionamentoPessoaEnum.valueOf("SOCIO_ADMINISTRADOR"));
		Assert.assertEquals(TipoRelacionamentoPessoaEnum.TUTOR, TipoRelacionamentoPessoaEnum.valueOf("TUTOR"));
	}

}
