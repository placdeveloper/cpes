package br.com.sicoob.capes.comum.negocio.enums;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class TipoFonteRendaEnumTest extends AbstractEnumTest {

	/**
	 * O método Test value of.
	 */
	@Test
	public void testValueOf() {
		assertEquals(TipoFonteRendaEnum.AGROPECUARIA, TipoFonteRendaEnum.obterPorCodigo((short) 9));
		assertEquals(TipoFonteRendaEnum.APLICACAO, TipoFonteRendaEnum.obterPorCodigo((short) 10));
		assertEquals(TipoFonteRendaEnum.APOSENTADORIA, TipoFonteRendaEnum.obterPorCodigo((short) 3));
		assertEquals(TipoFonteRendaEnum.BOLSAS_DO_GOVERNO, TipoFonteRendaEnum.obterPorCodigo((short) 11));
		assertEquals(TipoFonteRendaEnum.CONSULTORIA, TipoFonteRendaEnum.obterPorCodigo((short) 5));
		assertEquals(TipoFonteRendaEnum.FATURAMENTO, TipoFonteRendaEnum.obterPorCodigo((short) 7));
		assertEquals(TipoFonteRendaEnum.MESADA, TipoFonteRendaEnum.obterPorCodigo((short) 13));
		assertEquals(TipoFonteRendaEnum.NAO_POSSUI_RENDA, TipoFonteRendaEnum.obterPorCodigo((short) 12));
		assertEquals(TipoFonteRendaEnum.OUTROS, TipoFonteRendaEnum.obterPorCodigo((short) 6));
		assertEquals(TipoFonteRendaEnum.PENSAO_ALIMENTICIA, TipoFonteRendaEnum.obterPorCodigo((short) 2));
		assertEquals(TipoFonteRendaEnum.PRESTACAO_SERVICO, TipoFonteRendaEnum.obterPorCodigo((short) 4));
		assertEquals(TipoFonteRendaEnum.PRO_LABORE, TipoFonteRendaEnum.obterPorCodigo((short) 1));
		assertEquals(TipoFonteRendaEnum.RENDIMENTO_NAO_COMPROVADO, TipoFonteRendaEnum.obterPorCodigo((short) 8));
		assertEquals(TipoFonteRendaEnum.SALARIO, TipoFonteRendaEnum.obterPorCodigo((short) 0));
	}

	/**
	 * O método Test value of inexistente.
	 */
	@Test
	public void testValueOfInexistente() {
		assertNotNull(TipoFonteRendaEnum.valueOf("SALARIO"));
		assertNotNull(TipoFonteRendaEnum.valueOf("PRO_LABORE"));
		assertNotNull(TipoFonteRendaEnum.valueOf("PENSAO_ALIMENTICIA"));
		assertNotNull(TipoFonteRendaEnum.valueOf("APOSENTADORIA"));
		assertNotNull(TipoFonteRendaEnum.valueOf("PRESTACAO_SERVICO"));
		assertNotNull(TipoFonteRendaEnum.valueOf("CONSULTORIA"));
		assertNotNull(TipoFonteRendaEnum.valueOf("OUTROS"));
		assertNotNull(TipoFonteRendaEnum.valueOf("FATURAMENTO"));
		assertNotNull(TipoFonteRendaEnum.valueOf("RENDIMENTO_NAO_COMPROVADO"));
		assertNotNull(TipoFonteRendaEnum.valueOf("AGROPECUARIA"));
		assertNotNull(TipoFonteRendaEnum.valueOf("APLICACAO"));
		assertNotNull(TipoFonteRendaEnum.valueOf("BOLSAS_DO_GOVERNO"));
		assertNotNull(TipoFonteRendaEnum.valueOf("NAO_POSSUI_RENDA"));
		assertNotNull(TipoFonteRendaEnum.valueOf("MESADA"));
	}

	@Override
	public void testMetodosHerdados() {
		assertEquals(Integer.valueOf(14), Integer.valueOf(TipoFonteRendaEnum.values().length));
		assertNotNull(TipoFonteRendaEnum.valueOf("SALARIO"));
		assertNotNull(TipoFonteRendaEnum.valueOf("PRO_LABORE"));
		assertNotNull(TipoFonteRendaEnum.valueOf("PENSAO_ALIMENTICIA"));
		assertNotNull(TipoFonteRendaEnum.valueOf("APOSENTADORIA"));
		assertNotNull(TipoFonteRendaEnum.valueOf("PRESTACAO_SERVICO"));
		assertNotNull(TipoFonteRendaEnum.valueOf("CONSULTORIA"));
		assertNotNull(TipoFonteRendaEnum.valueOf("OUTROS"));
		assertNotNull(TipoFonteRendaEnum.valueOf("FATURAMENTO"));
		assertNotNull(TipoFonteRendaEnum.valueOf("RENDIMENTO_NAO_COMPROVADO"));
		assertNotNull(TipoFonteRendaEnum.valueOf("AGROPECUARIA"));
		assertNotNull(TipoFonteRendaEnum.valueOf("APLICACAO"));
		assertNotNull(TipoFonteRendaEnum.valueOf("BOLSAS_DO_GOVERNO"));
		assertNotNull(TipoFonteRendaEnum.valueOf("NAO_POSSUI_RENDA"));
		assertNotNull(TipoFonteRendaEnum.valueOf("MESADA"));
	}

}