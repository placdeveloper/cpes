// 29/10/2013 - 16:41:06
package br.com.sicoob.capes.comum.negocio.enums;

import static br.com.sicoob.capes.comum.negocio.enums.TipoDocumentoIdentificacaoEnum.CARTEIRA_IDENTIDADE;
import static br.com.sicoob.capes.comum.negocio.enums.TipoDocumentoIdentificacaoEnum.CARTEIRA_IDENTIDADE_MILITAR;
import static br.com.sicoob.capes.comum.negocio.enums.TipoDocumentoIdentificacaoEnum.CARTEIRA_IDENTIDADE_PROFISSIONAL;
import static br.com.sicoob.capes.comum.negocio.enums.TipoDocumentoIdentificacaoEnum.CERTIDaO_NASCIMENTO;
import static br.com.sicoob.capes.comum.negocio.enums.TipoDocumentoIdentificacaoEnum.CNH;
import static br.com.sicoob.capes.comum.negocio.enums.TipoDocumentoIdentificacaoEnum.CTPS;
import static br.com.sicoob.capes.comum.negocio.enums.TipoDocumentoIdentificacaoEnum.PASSAPORTE;
import static br.com.sicoob.capes.comum.negocio.enums.TipoDocumentoIdentificacaoEnum.RIC;
import static br.com.sicoob.capes.comum.negocio.enums.TipoDocumentoIdentificacaoEnum.RNE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.commons.lang.math.NumberUtils;
import org.junit.Test;

/**
 * @author Rodrigo.Chaves
 */
public class TipoDocumentoIdentificacaoEnumTest {

	/**
	 * Test method for
	 * {@link br.com.sicoob.capes.cadastro.negocio.enums.TipoDocumentoIdentificacaoEnum#getCodigo()}
	 * .
	 */
	@Test
	public void testGetCodigo() {
		assertEquals(CARTEIRA_IDENTIDADE.getCodigo(), NumberUtils.SHORT_ONE);
		assertEquals(CARTEIRA_IDENTIDADE_MILITAR.getCodigo(), Short.valueOf((short) 4));
		assertEquals(CARTEIRA_IDENTIDADE_PROFISSIONAL.getCodigo(), Short.valueOf((short) 3));
		assertEquals(CERTIDaO_NASCIMENTO.getCodigo(), Short.valueOf((short) 9));
		assertEquals(CNH.getCodigo(), Short.valueOf((short) 5));
		assertEquals(CTPS.getCodigo(), Short.valueOf((short) 7));
		assertEquals(PASSAPORTE.getCodigo(), Short.valueOf((short) 6));
		assertEquals(RIC.getCodigo(), Short.valueOf((short) 2));
		assertEquals(RNE.getCodigo(), Short.valueOf((short) 8));
	}

	/**
	 * Test method for
	 * {@link br.com.sicoob.capes.cadastro.negocio.enums.TipoDocumentoIdentificacaoEnum#getDescricao()}
	 * .
	 */
	@Test
	public void testGetDescricao() {
		assertEquals(CARTEIRA_IDENTIDADE.getDescricao(), "CARTEIRA DE IDENTIDADE");
		assertEquals(CARTEIRA_IDENTIDADE_MILITAR.getDescricao(), "CARTEIRA DE IDENTIDADE MILITAR");
		assertEquals(CARTEIRA_IDENTIDADE_PROFISSIONAL.getDescricao(), "CARTEIRA DE IDENTIDADE PROFISSIONAL");
		assertEquals(CERTIDaO_NASCIMENTO.getDescricao(), "CERTIDÃO DE NASCIMENTO");
		assertEquals(CNH.getDescricao(), "CARTEIRA NACIONAL DE HABILITAÇÃO - CNH");
		assertEquals(CTPS.getDescricao(), "CARTEIRA DE TRABALHO E PREVIDÊNCIA SOCIAL - CTPS");
		assertEquals(PASSAPORTE.getDescricao(), "PASSAPORTE");
		assertEquals(RIC.getDescricao(), "REGISTRO ÚNICO DE IDENTIDADE CIVIL RIC");
		assertEquals(RNE.getDescricao(), "REGISTRO NACIONAL DE ESTRANGEIRO - RNE");
	}

	/**
	 * O método Test metodos herdados.
	 */
	@Test
	public void testMetodosHerdados() {
		assertEquals(Integer.valueOf(9), Integer.valueOf(TipoDocumentoIdentificacaoEnum.values().length));
		assertNotNull(TipoDocumentoIdentificacaoEnum.valueOf("CARTEIRA_IDENTIDADE"));
		assertNotNull(TipoDocumentoIdentificacaoEnum.valueOf("CARTEIRA_IDENTIDADE_MILITAR"));
		assertNotNull(TipoDocumentoIdentificacaoEnum.valueOf("CARTEIRA_IDENTIDADE_PROFISSIONAL"));
		assertNotNull(TipoDocumentoIdentificacaoEnum.valueOf("CERTIDaO_NASCIMENTO"));
		assertNotNull(TipoDocumentoIdentificacaoEnum.valueOf("CNH"));
		assertNotNull(TipoDocumentoIdentificacaoEnum.valueOf("CTPS"));
		assertNotNull(TipoDocumentoIdentificacaoEnum.valueOf("PASSAPORTE"));
		assertNotNull(TipoDocumentoIdentificacaoEnum.valueOf("RIC"));
		assertNotNull(TipoDocumentoIdentificacaoEnum.valueOf("RNE"));
	}
}
