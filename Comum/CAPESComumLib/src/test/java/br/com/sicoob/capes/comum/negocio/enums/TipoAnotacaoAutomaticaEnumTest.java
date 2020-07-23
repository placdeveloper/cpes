// 30/10/2013 - 16:45:59
package br.com.sicoob.capes.comum.negocio.enums;

import static br.com.sicoob.capes.comum.negocio.enums.TipoAnotacaoAutomaticaEnum.BACEN_CREDITO_BAIXADO_PREJUIXO_SFN;
import static br.com.sicoob.capes.comum.negocio.enums.TipoAnotacaoAutomaticaEnum.BACEN_NADA_CONSTA;
import static br.com.sicoob.capes.comum.negocio.enums.TipoAnotacaoAutomaticaEnum.BACEN_VENCIDO_15_90_DIAS_SFN;
import static br.com.sicoob.capes.comum.negocio.enums.TipoAnotacaoAutomaticaEnum.BACEN_VENCIDO_ACIMA_90_DIAS_SFN;
import static br.com.sicoob.capes.comum.negocio.enums.TipoAnotacaoAutomaticaEnum.CADASTRO_DIGITAL;
import static br.com.sicoob.capes.comum.negocio.enums.TipoAnotacaoAutomaticaEnum.RECEITA_CNPJ_IRREGULAR;
import static br.com.sicoob.capes.comum.negocio.enums.TipoAnotacaoAutomaticaEnum.RECEITA_CPF_IRREGULAR;
import static br.com.sicoob.capes.comum.negocio.enums.TipoAnotacaoAutomaticaEnum.RECEITA_NADA_CONSTA;
import static br.com.sicoob.capes.comum.negocio.enums.TipoAnotacaoAutomaticaEnum.SERASA_ACAO_JUDICIAL;
import static br.com.sicoob.capes.comum.negocio.enums.TipoAnotacaoAutomaticaEnum.SERASA_ACHEI;
import static br.com.sicoob.capes.comum.negocio.enums.TipoAnotacaoAutomaticaEnum.SERASA_CCF;
import static br.com.sicoob.capes.comum.negocio.enums.TipoAnotacaoAutomaticaEnum.SERASA_CONVEM_DEVEDORES;
import static br.com.sicoob.capes.comum.negocio.enums.TipoAnotacaoAutomaticaEnum.SERASA_FALENCIA;
import static br.com.sicoob.capes.comum.negocio.enums.TipoAnotacaoAutomaticaEnum.SERASA_NADA_CONSTA;
import static br.com.sicoob.capes.comum.negocio.enums.TipoAnotacaoAutomaticaEnum.SERASA_PARTICIPANTE_FALIDA;
import static br.com.sicoob.capes.comum.negocio.enums.TipoAnotacaoAutomaticaEnum.SERASA_PEFIN;
import static br.com.sicoob.capes.comum.negocio.enums.TipoAnotacaoAutomaticaEnum.SERASA_PROTESTO;
import static br.com.sicoob.capes.comum.negocio.enums.TipoAnotacaoAutomaticaEnum.SERASA_RECUPERACAO_JUDICIAL;
import static br.com.sicoob.capes.comum.negocio.enums.TipoAnotacaoAutomaticaEnum.SERASA_RECUPERACA_EXTRAJUDICIAL;
import static br.com.sicoob.capes.comum.negocio.enums.TipoAnotacaoAutomaticaEnum.SERASA_REFIN;
import static br.com.sicoob.capes.comum.negocio.enums.TipoAnotacaoAutomaticaEnum.SISBR_AVALISTA_INADIMPLENCIA_90_DIAS;
import static br.com.sicoob.capes.comum.negocio.enums.TipoAnotacaoAutomaticaEnum.SISBR_AVALISTA_OPERACAO_PREJUIZO;
import static br.com.sicoob.capes.comum.negocio.enums.TipoAnotacaoAutomaticaEnum.SISBR_CHEQUE_SEM_FUNDO;
import static br.com.sicoob.capes.comum.negocio.enums.TipoAnotacaoAutomaticaEnum.SISBR_INADIMPLENCIA_15_DIAS;
import static br.com.sicoob.capes.comum.negocio.enums.TipoAnotacaoAutomaticaEnum.SISBR_INADIMPLENCIA_30_DIAS;
import static br.com.sicoob.capes.comum.negocio.enums.TipoAnotacaoAutomaticaEnum.SISBR_INADIMPLENCIA_90_DIAS;
import static br.com.sicoob.capes.comum.negocio.enums.TipoAnotacaoAutomaticaEnum.SISBR_OPERACAO_TRANSFERIDA_PREJUIZO;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.apache.commons.lang.math.NumberUtils;
import org.junit.Test;

/**
 * 
 * 
 * @author Rodrigo.Chaves
 */
public class TipoAnotacaoAutomaticaEnumTest extends 
AbstractEnumTest {

	/**
	 * Test method for {@link br.com.sicoob.capes.cadastro.negocio.enums.TipoAnotacaoAutomaticaEnum#getCodTipoAnotacao()}.
	 */
	@Test
	public void testGetCodTipoAnotacao() {
		assertEquals(NumberUtils.INTEGER_ONE, SERASA_NADA_CONSTA.getCodTipoAnotacao());
		assertEquals(Integer.valueOf(101), SERASA_ACHEI.getCodTipoAnotacao());
		assertEquals(Integer.valueOf(305), SERASA_PROTESTO.getCodTipoAnotacao());
		assertEquals(Integer.valueOf(306), SERASA_ACAO_JUDICIAL.getCodTipoAnotacao());
		assertEquals(Integer.valueOf(307), SERASA_RECUPERACA_EXTRAJUDICIAL.getCodTipoAnotacao());
		assertEquals(Integer.valueOf(308), SERASA_RECUPERACAO_JUDICIAL.getCodTipoAnotacao());
		assertEquals(Integer.valueOf(309), SERASA_REFIN.getCodTipoAnotacao());
		assertEquals(Integer.valueOf(310), SERASA_PEFIN.getCodTipoAnotacao());
		assertEquals(Integer.valueOf(311), SERASA_CONVEM_DEVEDORES.getCodTipoAnotacao());
		assertEquals(Integer.valueOf(502), SERASA_CCF.getCodTipoAnotacao());
		assertEquals(Integer.valueOf(503), SERASA_FALENCIA.getCodTipoAnotacao());
		assertEquals(Integer.valueOf(504), SERASA_PARTICIPANTE_FALIDA.getCodTipoAnotacao());
		assertEquals(Integer.valueOf(2), BACEN_NADA_CONSTA.getCodTipoAnotacao());
		assertEquals(Integer.valueOf(314), BACEN_VENCIDO_15_90_DIAS_SFN.getCodTipoAnotacao());
		assertEquals(Integer.valueOf(315), BACEN_VENCIDO_ACIMA_90_DIAS_SFN.getCodTipoAnotacao());
		assertEquals(Integer.valueOf(316), BACEN_CREDITO_BAIXADO_PREJUIXO_SFN.getCodTipoAnotacao());
		assertEquals(Integer.valueOf(100), SISBR_CHEQUE_SEM_FUNDO.getCodTipoAnotacao());
		assertEquals(Integer.valueOf(300), SISBR_INADIMPLENCIA_15_DIAS.getCodTipoAnotacao());
		assertEquals(Integer.valueOf(320), SISBR_INADIMPLENCIA_30_DIAS.getCodTipoAnotacao());
		assertEquals(Integer.valueOf(301), SISBR_INADIMPLENCIA_90_DIAS.getCodTipoAnotacao());
		assertEquals(Integer.valueOf(302), SISBR_OPERACAO_TRANSFERIDA_PREJUIZO.getCodTipoAnotacao());
		assertEquals(Integer.valueOf(303), SISBR_AVALISTA_INADIMPLENCIA_90_DIAS.getCodTipoAnotacao());
		assertEquals(Integer.valueOf(304), SISBR_AVALISTA_OPERACAO_PREJUIZO.getCodTipoAnotacao());
		assertEquals(Integer.valueOf(3), RECEITA_NADA_CONSTA.getCodTipoAnotacao());
		assertEquals(Integer.valueOf(500), RECEITA_CPF_IRREGULAR.getCodTipoAnotacao());
		assertEquals(Integer.valueOf(501), RECEITA_CNPJ_IRREGULAR.getCodTipoAnotacao());
		assertEquals(Integer.valueOf(518), CADASTRO_DIGITAL.getCodTipoAnotacao());
	}

	/**
	 * Test method for {@link br.com.sicoob.capes.cadastro.negocio.enums.TipoAnotacaoAutomaticaEnum#getDescricao()}.
	 */
	@Test
	public void testGetDescricao() {
		assertEquals("Nada Consta", SERASA_NADA_CONSTA.getDescricao());
		assertEquals("Achei", SERASA_ACHEI.getDescricao());
		assertEquals("Protesto", SERASA_PROTESTO.getDescricao());
		assertEquals("Ação Judicial", SERASA_ACAO_JUDICIAL.getDescricao());
		assertEquals("Recuperação extrajudicial", SERASA_RECUPERACA_EXTRAJUDICIAL.getDescricao());
		assertEquals("Recuperação judicial", SERASA_RECUPERACAO_JUDICIAL.getDescricao());
		assertEquals("Refin/Financiamentos", SERASA_REFIN.getDescricao());
		assertEquals("Pefin", SERASA_PEFIN.getDescricao());
		assertEquals("Convem Devedores", SERASA_CONVEM_DEVEDORES.getDescricao());
		assertEquals("CCF", SERASA_CCF.getDescricao());
		assertEquals("Falência/Concordata", SERASA_FALENCIA.getDescricao());
		assertEquals("Participante de empresa falida", SERASA_PARTICIPANTE_FALIDA.getDescricao());
		assertEquals("Nada Consta", BACEN_NADA_CONSTA.getDescricao());
		assertEquals("Crédito vencido de 15 a 90 dias no SFN", BACEN_VENCIDO_15_90_DIAS_SFN.getDescricao());
		assertEquals("Crédito vencido acima de 90 dias no SFN", BACEN_VENCIDO_ACIMA_90_DIAS_SFN.getDescricao());
		assertEquals("Crédito baixado como prejuízo no SFN", BACEN_CREDITO_BAIXADO_PREJUIXO_SFN.getDescricao());
		assertEquals("Cheque sem fundo", SISBR_CHEQUE_SEM_FUNDO.getDescricao());
		assertEquals("Inadimplência 15 dias – INAD15", SISBR_INADIMPLENCIA_15_DIAS.getDescricao());
		assertEquals("Inadimplência 30 dias  INAD30", SISBR_INADIMPLENCIA_30_DIAS.getDescricao());
		assertEquals("Inadimplência 90 dias – INAD90", SISBR_INADIMPLENCIA_90_DIAS.getDescricao());
		assertEquals("Operação transferida para prejuízo", SISBR_OPERACAO_TRANSFERIDA_PREJUIZO.getDescricao());
		assertEquals("Avalista de operação com INAD90", SISBR_AVALISTA_INADIMPLENCIA_90_DIAS.getDescricao());
		assertEquals("Avalista de operação em prejuízo", SISBR_AVALISTA_OPERACAO_PREJUIZO.getDescricao());
		assertEquals("Nada Consta", RECEITA_NADA_CONSTA.getDescricao());
		assertEquals("CPF Irregular", RECEITA_CPF_IRREGULAR.getDescricao());
		assertEquals("CNPJ Irregular", RECEITA_CNPJ_IRREGULAR.getDescricao());
		assertEquals("Cadastro incluído/alterado por uma conta digital", CADASTRO_DIGITAL.getDescricao());
		
	}

	/**
	 * Test method for {@link br.com.sicoob.capes.cadastro.negocio.enums.TipoAnotacaoAutomaticaEnum#isNadaConsta()}.
	 */
	@Test
	public void testIsNadaConsta() {
		assertTrue(SERASA_NADA_CONSTA.isNadaConsta());
		assertFalse(SERASA_ACHEI.isNadaConsta());
		assertFalse(SERASA_PROTESTO.isNadaConsta());
		assertFalse(SERASA_ACAO_JUDICIAL.isNadaConsta());
		assertFalse(SERASA_RECUPERACA_EXTRAJUDICIAL.isNadaConsta());
		assertFalse(SERASA_RECUPERACAO_JUDICIAL.isNadaConsta());
		assertFalse(SERASA_REFIN.isNadaConsta());
		assertFalse(SERASA_PEFIN.isNadaConsta());
		assertFalse(SERASA_CONVEM_DEVEDORES.isNadaConsta());
		assertFalse(SERASA_CCF.isNadaConsta());
		assertFalse(SERASA_FALENCIA.isNadaConsta());
		assertFalse(SERASA_PARTICIPANTE_FALIDA.isNadaConsta());
		assertTrue(BACEN_NADA_CONSTA.isNadaConsta());
		assertFalse(BACEN_VENCIDO_15_90_DIAS_SFN.isNadaConsta());
		assertFalse(BACEN_VENCIDO_ACIMA_90_DIAS_SFN.isNadaConsta());
		assertFalse(BACEN_CREDITO_BAIXADO_PREJUIXO_SFN.isNadaConsta());
		assertFalse(SISBR_CHEQUE_SEM_FUNDO.isNadaConsta());
		assertFalse(SISBR_INADIMPLENCIA_15_DIAS.isNadaConsta());
		assertFalse(SISBR_INADIMPLENCIA_30_DIAS.isNadaConsta());
		assertFalse(SISBR_INADIMPLENCIA_90_DIAS.isNadaConsta());
		assertFalse(SISBR_OPERACAO_TRANSFERIDA_PREJUIZO.isNadaConsta());
		assertFalse(SISBR_AVALISTA_INADIMPLENCIA_90_DIAS.isNadaConsta());
		assertFalse(SISBR_AVALISTA_OPERACAO_PREJUIZO.isNadaConsta());
		assertFalse(RECEITA_NADA_CONSTA.isNadaConsta());
		assertFalse(RECEITA_CPF_IRREGULAR.isNadaConsta());
		assertFalse(RECEITA_CNPJ_IRREGULAR.isNadaConsta());
		assertFalse(CADASTRO_DIGITAL.isNadaConsta());
	}

	/**
	 * Test method for {@link br.com.sicoob.capes.cadastro.negocio.enums.TipoAnotacaoAutomaticaEnum#getOrigem()}.
	 */
	@Test
	public void testGetOrigem() {
		assertEquals(OrigemInformacaoEnum.SERASA, SERASA_NADA_CONSTA.getOrigem());
		assertEquals(OrigemInformacaoEnum.SERASA, SERASA_ACHEI.getOrigem());
		assertEquals(OrigemInformacaoEnum.SERASA, SERASA_PROTESTO.getOrigem());
		assertEquals(OrigemInformacaoEnum.SERASA, SERASA_ACAO_JUDICIAL.getOrigem());
		assertEquals(OrigemInformacaoEnum.SERASA, SERASA_RECUPERACA_EXTRAJUDICIAL.getOrigem());
		assertEquals(OrigemInformacaoEnum.SERASA, SERASA_RECUPERACAO_JUDICIAL.getOrigem());
		assertEquals(OrigemInformacaoEnum.SERASA, SERASA_REFIN.getOrigem());
		assertEquals(OrigemInformacaoEnum.SERASA, SERASA_PEFIN.getOrigem());
		assertEquals(OrigemInformacaoEnum.SERASA, SERASA_CONVEM_DEVEDORES.getOrigem());
		assertEquals(OrigemInformacaoEnum.SERASA, SERASA_CCF.getOrigem());
		assertEquals(OrigemInformacaoEnum.SERASA, SERASA_FALENCIA.getOrigem());
		assertEquals(OrigemInformacaoEnum.SERASA, SERASA_PARTICIPANTE_FALIDA.getOrigem());
		assertEquals(OrigemInformacaoEnum.BACEN, BACEN_NADA_CONSTA.getOrigem());
		assertEquals(OrigemInformacaoEnum.BACEN, BACEN_VENCIDO_15_90_DIAS_SFN.getOrigem());
		assertEquals(OrigemInformacaoEnum.BACEN, BACEN_VENCIDO_ACIMA_90_DIAS_SFN.getOrigem());
		assertEquals(OrigemInformacaoEnum.BACEN, BACEN_CREDITO_BAIXADO_PREJUIXO_SFN.getOrigem());
		assertEquals(OrigemInformacaoEnum.SISBR, SISBR_CHEQUE_SEM_FUNDO.getOrigem());
		assertEquals(OrigemInformacaoEnum.SISBR, SISBR_INADIMPLENCIA_15_DIAS.getOrigem());
		assertEquals(OrigemInformacaoEnum.SISBR, SISBR_INADIMPLENCIA_30_DIAS.getOrigem());
		assertEquals(OrigemInformacaoEnum.SISBR, SISBR_INADIMPLENCIA_90_DIAS.getOrigem());
		assertEquals(OrigemInformacaoEnum.SISBR, SISBR_OPERACAO_TRANSFERIDA_PREJUIZO.getOrigem());
		assertEquals(OrigemInformacaoEnum.SISBR, SISBR_AVALISTA_INADIMPLENCIA_90_DIAS.getOrigem());
		assertEquals(OrigemInformacaoEnum.SISBR, SISBR_AVALISTA_OPERACAO_PREJUIZO.getOrigem());
		assertEquals(OrigemInformacaoEnum.RECEITA, RECEITA_NADA_CONSTA.getOrigem());
		assertEquals(OrigemInformacaoEnum.RECEITA, RECEITA_CPF_IRREGULAR.getOrigem());
		assertEquals(OrigemInformacaoEnum.RECEITA, RECEITA_CNPJ_IRREGULAR.getOrigem());
	}

	/**
	 * Test method for {@link br.com.sicoob.capes.cadastro.negocio.enums.TipoAnotacaoAutomaticaEnum#recuperarTipoAnotacaoAutomaticaEnumPorCodigo(java.lang.Integer)}.
	 */
	@Test
	public void testRecuperarTipoAnotacaoAutomaticaEnumPorCodigo() {
		assertEquals(SERASA_NADA_CONSTA,
				TipoAnotacaoAutomaticaEnum.recuperarTipoAnotacaoAutomaticaEnumPorCodigo(NumberUtils.INTEGER_ONE));
		assertEquals(SERASA_ACHEI,
				TipoAnotacaoAutomaticaEnum.recuperarTipoAnotacaoAutomaticaEnumPorCodigo(Integer
						.valueOf(101)));
		assertEquals(SERASA_PROTESTO,
				TipoAnotacaoAutomaticaEnum.recuperarTipoAnotacaoAutomaticaEnumPorCodigo(Integer
						.valueOf(305)));
		assertEquals(SERASA_ACAO_JUDICIAL,
				TipoAnotacaoAutomaticaEnum.recuperarTipoAnotacaoAutomaticaEnumPorCodigo(Integer
						.valueOf(306)));
		assertEquals(SERASA_RECUPERACA_EXTRAJUDICIAL,
				TipoAnotacaoAutomaticaEnum.recuperarTipoAnotacaoAutomaticaEnumPorCodigo(Integer
						.valueOf(307)));
		assertEquals(SERASA_RECUPERACAO_JUDICIAL,
				TipoAnotacaoAutomaticaEnum.recuperarTipoAnotacaoAutomaticaEnumPorCodigo(Integer
						.valueOf(308)));
		assertEquals(SERASA_REFIN,
				TipoAnotacaoAutomaticaEnum.recuperarTipoAnotacaoAutomaticaEnumPorCodigo(Integer
						.valueOf(309)));
		assertEquals(SERASA_PEFIN,
				TipoAnotacaoAutomaticaEnum.recuperarTipoAnotacaoAutomaticaEnumPorCodigo(Integer
						.valueOf(310)));
		assertEquals(SERASA_CONVEM_DEVEDORES,
				TipoAnotacaoAutomaticaEnum.recuperarTipoAnotacaoAutomaticaEnumPorCodigo(Integer
						.valueOf(311)));
		assertEquals(SERASA_CCF,
				TipoAnotacaoAutomaticaEnum.recuperarTipoAnotacaoAutomaticaEnumPorCodigo(Integer
						.valueOf(502)));
		assertEquals(SERASA_FALENCIA,
				TipoAnotacaoAutomaticaEnum.recuperarTipoAnotacaoAutomaticaEnumPorCodigo(Integer
						.valueOf(503)));
		assertEquals(SERASA_PARTICIPANTE_FALIDA,
				TipoAnotacaoAutomaticaEnum.recuperarTipoAnotacaoAutomaticaEnumPorCodigo(Integer
						.valueOf(504)));
		assertEquals(BACEN_NADA_CONSTA,
				TipoAnotacaoAutomaticaEnum.recuperarTipoAnotacaoAutomaticaEnumPorCodigo(Integer
						.valueOf(2)));
		assertEquals(BACEN_VENCIDO_15_90_DIAS_SFN,
				TipoAnotacaoAutomaticaEnum.recuperarTipoAnotacaoAutomaticaEnumPorCodigo(Integer
						.valueOf(314)));
		assertEquals(BACEN_VENCIDO_ACIMA_90_DIAS_SFN,
				TipoAnotacaoAutomaticaEnum.recuperarTipoAnotacaoAutomaticaEnumPorCodigo(Integer
						.valueOf(315)));
		assertEquals(BACEN_CREDITO_BAIXADO_PREJUIXO_SFN,
				TipoAnotacaoAutomaticaEnum.recuperarTipoAnotacaoAutomaticaEnumPorCodigo(Integer
						.valueOf(316)));
		assertEquals(SISBR_CHEQUE_SEM_FUNDO,
				TipoAnotacaoAutomaticaEnum.recuperarTipoAnotacaoAutomaticaEnumPorCodigo(Integer
						.valueOf(100)));
		assertEquals(SISBR_INADIMPLENCIA_15_DIAS,
				TipoAnotacaoAutomaticaEnum.recuperarTipoAnotacaoAutomaticaEnumPorCodigo(Integer
						.valueOf(300)));
		assertEquals(SISBR_INADIMPLENCIA_30_DIAS,
				TipoAnotacaoAutomaticaEnum.recuperarTipoAnotacaoAutomaticaEnumPorCodigo(Integer
						.valueOf(320)));
		assertEquals(SISBR_INADIMPLENCIA_90_DIAS,
				TipoAnotacaoAutomaticaEnum.recuperarTipoAnotacaoAutomaticaEnumPorCodigo(Integer
						.valueOf(301)));
		assertEquals(SISBR_OPERACAO_TRANSFERIDA_PREJUIZO,
				TipoAnotacaoAutomaticaEnum.recuperarTipoAnotacaoAutomaticaEnumPorCodigo(Integer
						.valueOf(302)));
		assertEquals(SISBR_AVALISTA_INADIMPLENCIA_90_DIAS,
				TipoAnotacaoAutomaticaEnum.recuperarTipoAnotacaoAutomaticaEnumPorCodigo(Integer
						.valueOf(303)));
		assertEquals(SISBR_AVALISTA_OPERACAO_PREJUIZO,
				TipoAnotacaoAutomaticaEnum.recuperarTipoAnotacaoAutomaticaEnumPorCodigo(Integer
						.valueOf(304)));
		assertEquals(RECEITA_NADA_CONSTA,
				TipoAnotacaoAutomaticaEnum.recuperarTipoAnotacaoAutomaticaEnumPorCodigo(Integer
						.valueOf(3)));
		assertEquals(RECEITA_CPF_IRREGULAR,
				TipoAnotacaoAutomaticaEnum.recuperarTipoAnotacaoAutomaticaEnumPorCodigo(Integer
						.valueOf(500)));
		assertEquals(RECEITA_CNPJ_IRREGULAR,
				TipoAnotacaoAutomaticaEnum.recuperarTipoAnotacaoAutomaticaEnumPorCodigo(Integer
						.valueOf(501)));
		assertEquals(CADASTRO_DIGITAL,
				TipoAnotacaoAutomaticaEnum.recuperarTipoAnotacaoAutomaticaEnumPorCodigo(Integer
						.valueOf(518)));
	}
	
	/**
	 * Test method for {@link br.com.sicoob.capes.cadastro.negocio.enums.TipoAnotacaoAutomaticaEnum#recuperarTipoAnotacaoAutomaticaEnumPorCodigo(java.lang.Integer)}.
	 */
	@Test
	public void testRecuperarTipoAnotacaoAutomaticaEnumPorCodigoInexistente() {
		assertNull(TipoAnotacaoAutomaticaEnum
				.recuperarTipoAnotacaoAutomaticaEnumPorCodigo(NumberUtils.INTEGER_MINUS_ONE));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Test
	public void testMetodosHerdados() {
		assertEquals(Integer.valueOf(34), Integer.valueOf(TipoAnotacaoAutomaticaEnum.values().length));
		assertNotNull(TipoAnotacaoAutomaticaEnum.valueOf("SERASA_NADA_CONSTA"));
		assertNotNull(TipoAnotacaoAutomaticaEnum.valueOf("SERASA_ACHEI"));
		assertNotNull(TipoAnotacaoAutomaticaEnum.valueOf("SERASA_PROTESTO"));
		assertNotNull(TipoAnotacaoAutomaticaEnum.valueOf("SERASA_ACAO_JUDICIAL"));
		assertNotNull(TipoAnotacaoAutomaticaEnum.valueOf("SERASA_RECUPERACA_EXTRAJUDICIAL"));
		assertNotNull(TipoAnotacaoAutomaticaEnum.valueOf("SERASA_RECUPERACAO_JUDICIAL"));
		assertNotNull(TipoAnotacaoAutomaticaEnum.valueOf("SERASA_REFIN"));
		assertNotNull(TipoAnotacaoAutomaticaEnum.valueOf("SERASA_PEFIN"));
		assertNotNull(TipoAnotacaoAutomaticaEnum.valueOf("SERASA_CONVEM_DEVEDORES"));
		assertNotNull(TipoAnotacaoAutomaticaEnum.valueOf("SERASA_CCF"));
		assertNotNull(TipoAnotacaoAutomaticaEnum.valueOf("SERASA_FALENCIA"));
		assertNotNull(TipoAnotacaoAutomaticaEnum.valueOf("SERASA_PARTICIPANTE_FALIDA"));
		assertNotNull(TipoAnotacaoAutomaticaEnum.valueOf("BACEN_NADA_CONSTA"));
		assertNotNull(TipoAnotacaoAutomaticaEnum.valueOf("BACEN_VENCIDO_15_90_DIAS_SFN"));
		assertNotNull(TipoAnotacaoAutomaticaEnum.valueOf("BACEN_VENCIDO_ACIMA_90_DIAS_SFN"));
		assertNotNull(TipoAnotacaoAutomaticaEnum.valueOf("BACEN_CREDITO_BAIXADO_PREJUIXO_SFN"));
		assertNotNull(TipoAnotacaoAutomaticaEnum.valueOf("SISBR_CHEQUE_SEM_FUNDO"));
		assertNotNull(TipoAnotacaoAutomaticaEnum.valueOf("SISBR_INADIMPLENCIA_15_DIAS"));
		assertNotNull(TipoAnotacaoAutomaticaEnum.valueOf("SISBR_INADIMPLENCIA_30_DIAS"));
		assertNotNull(TipoAnotacaoAutomaticaEnum.valueOf("SISBR_INADIMPLENCIA_90_DIAS"));
		assertNotNull(TipoAnotacaoAutomaticaEnum.valueOf("SISBR_OPERACAO_TRANSFERIDA_PREJUIZO"));
		assertNotNull(TipoAnotacaoAutomaticaEnum.valueOf("SISBR_AVALISTA_INADIMPLENCIA_90_DIAS"));
		assertNotNull(TipoAnotacaoAutomaticaEnum.valueOf("SISBR_AVALISTA_OPERACAO_PREJUIZO"));
		assertNotNull(TipoAnotacaoAutomaticaEnum.valueOf("RECEITA_NADA_CONSTA"));
		assertNotNull(TipoAnotacaoAutomaticaEnum.valueOf("RECEITA_CPF_IRREGULAR"));
		assertNotNull(TipoAnotacaoAutomaticaEnum.valueOf("RECEITA_CNPJ_IRREGULAR"));
		assertNotNull(TipoAnotacaoAutomaticaEnum.valueOf("CADASTRO_DIGITAL"));
	}
	
}
