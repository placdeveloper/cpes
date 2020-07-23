// 28/10/2013 - 15:15:27
package br.com.sicoob.capes.comum.negocio.enums;

import static br.com.sicoob.capes.comum.negocio.enums.SituacaoCadastralRFBEnum.ATIVA;
import static br.com.sicoob.capes.comum.negocio.enums.SituacaoCadastralRFBEnum.BAIXADA;
import static br.com.sicoob.capes.comum.negocio.enums.SituacaoCadastralRFBEnum.CANCELADA_ENCERRAMENTO_ESPOLIO;
import static br.com.sicoob.capes.comum.negocio.enums.SituacaoCadastralRFBEnum.CANCELADA_MULTIPLICIDADE;
import static br.com.sicoob.capes.comum.negocio.enums.SituacaoCadastralRFBEnum.CANCELADA_OBITO_SEM_ESPOLIO;
import static br.com.sicoob.capes.comum.negocio.enums.SituacaoCadastralRFBEnum.CANCELADA_OFICIO;
import static br.com.sicoob.capes.comum.negocio.enums.SituacaoCadastralRFBEnum.INAPTA;
import static br.com.sicoob.capes.comum.negocio.enums.SituacaoCadastralRFBEnum.NULA;
import static br.com.sicoob.capes.comum.negocio.enums.SituacaoCadastralRFBEnum.PENDENTE_REGULARIZACAO;
import static br.com.sicoob.capes.comum.negocio.enums.SituacaoCadastralRFBEnum.REGULAR;
import static br.com.sicoob.capes.comum.negocio.enums.SituacaoCadastralRFBEnum.SUSPENSA;
import static br.com.sicoob.capes.comum.negocio.enums.TipoPessoaEnum.PESSOA_FISICA;
import static br.com.sicoob.capes.comum.negocio.enums.TipoPessoaEnum.PESSOA_JURIDICA;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.apache.commons.lang.math.NumberUtils;
import org.junit.Test;

/**
 * @author Rodrigo.Chaves
 */
public class SituacaoCadastralRFBEnumTest extends AbstractEnumTest {

	/**
	 * O método Test get codigo.
	 */
	@Test
	public void testGetCodigo() {

		assertEquals(ATIVA.getCodigo().intValue(), 0);
		assertEquals(BAIXADA.getCodigo().intValue(), 1);
		assertEquals(CANCELADA_ENCERRAMENTO_ESPOLIO.getCodigo().intValue(), 2);
		assertEquals(CANCELADA_MULTIPLICIDADE.getCodigo().intValue(), 3);
		assertEquals(CANCELADA_OBITO_SEM_ESPOLIO.getCodigo().intValue(), 4);
		assertEquals(CANCELADA_OFICIO.getCodigo().intValue(), 5);
		assertEquals(INAPTA.getCodigo().intValue(), 6);
		assertEquals(NULA.getCodigo().intValue(), 7);
		assertEquals(PENDENTE_REGULARIZACAO.getCodigo().intValue(), 8);
		assertEquals(REGULAR.getCodigo().intValue(), 9);
		assertEquals(SUSPENSA.getCodigo().intValue(), 10);
	}

	/**
	 * O método Test get descricao.
	 */
	@Test
	public void testGetDescricao() {

		assertEquals(ATIVA.getDescricao(), "Ativa");
		assertEquals(BAIXADA.getDescricao(), "Baixada");
		assertEquals(CANCELADA_ENCERRAMENTO_ESPOLIO.getDescricao(), "Cancelada por encerramento de espólio");
		assertEquals(CANCELADA_MULTIPLICIDADE.getDescricao(), "Cancelada por multiplicidade");
		assertEquals(CANCELADA_OBITO_SEM_ESPOLIO.getDescricao(), "Cancelada por óbito sem espólio");
		assertEquals(CANCELADA_OFICIO.getDescricao(), "Cancelada de ofício");
		assertEquals(INAPTA.getDescricao(), "Inapta");
		assertEquals(NULA.getDescricao(), "Nula");
		assertEquals(PENDENTE_REGULARIZACAO.getDescricao(), "Pendente de regularização");
		assertEquals(REGULAR.getDescricao(), "Regular");
		assertEquals(SUSPENSA.getDescricao(), "Suspensa");
	}

	/**
	 * O método Test get codigo pf.
	 */
	@Test
	public void testGetCodigoPF() {

		assertEquals(CANCELADA_ENCERRAMENTO_ESPOLIO.getCodigoPF().intValue(), 1);
		assertEquals(CANCELADA_MULTIPLICIDADE.getCodigoPF().intValue(), 5);
		assertEquals(CANCELADA_OBITO_SEM_ESPOLIO.getCodigoPF().intValue(), 3);
		assertEquals(CANCELADA_OFICIO.getCodigoPF().intValue(), 9);
		assertEquals(NULA.getCodigoPF().intValue(), 8);
		assertEquals(PENDENTE_REGULARIZACAO.getCodigoPF().intValue(), 4);
		assertEquals(REGULAR.getCodigoPF().intValue(), 0);
		assertEquals(SUSPENSA.getCodigoPF().intValue(), 2);

		// so PJ
		assertNull(ATIVA.getCodigoPF());
		assertNull(BAIXADA.getCodigoPF());
		assertNull(INAPTA.getCodigoPF());
	}

	/**
	 * O método Test get codigo pj.
	 */
	@Test
	public void testGetCodigoPJ() {

		assertEquals(ATIVA.getCodigoPJ().intValue(), 2);
		assertEquals(BAIXADA.getCodigoPJ().intValue(), 8);
		assertEquals(INAPTA.getCodigoPJ().intValue(), 4);
		assertEquals(NULA.getCodigoPJ().intValue(), 1);
		assertEquals(SUSPENSA.getCodigoPJ().intValue(), 3);

		// so PF
		assertNull(CANCELADA_ENCERRAMENTO_ESPOLIO.getCodigoPJ());
		assertNull(CANCELADA_MULTIPLICIDADE.getCodigoPJ());
		assertNull(CANCELADA_OBITO_SEM_ESPOLIO.getCodigoPJ());
		assertNull(CANCELADA_OFICIO.getCodigoPJ());
		assertNull(PENDENTE_REGULARIZACAO.getCodigoPJ());
		assertNull(REGULAR.getCodigoPJ());
	}

	/**
	 * O método Test to string.
	 */
	@Test
	public void testToString() {

		for (SituacaoCadastralRFBEnum value : SituacaoCadastralRFBEnum.values()) {
			assertEquals(value.toString(),
					value.getDescricao() + ": PF(" + value.getCodigoPF() + ")/PJ(" + value.getCodigoPJ() + ")");
		}
	}

	/**
	 * O método Test value of cod rfb cod tipo pessoa.
	 */
	@Test
	public void testValueOfCodRFBCodTipoPessoa() {

		assertEquals(ATIVA, SituacaoCadastralRFBEnum.valueOf(2, PESSOA_JURIDICA.getCodigo()));
		assertEquals(BAIXADA, SituacaoCadastralRFBEnum.valueOf(8, PESSOA_JURIDICA.getCodigo()));
		assertEquals(CANCELADA_ENCERRAMENTO_ESPOLIO, SituacaoCadastralRFBEnum.valueOf(1, PESSOA_FISICA.getCodigo()));
		assertEquals(CANCELADA_MULTIPLICIDADE, SituacaoCadastralRFBEnum.valueOf(5, PESSOA_FISICA.getCodigo()));
		assertEquals(CANCELADA_OBITO_SEM_ESPOLIO, SituacaoCadastralRFBEnum.valueOf(3, PESSOA_FISICA.getCodigo()));
		assertEquals(CANCELADA_OFICIO, SituacaoCadastralRFBEnum.valueOf(9, PESSOA_FISICA.getCodigo()));
		assertEquals(INAPTA, SituacaoCadastralRFBEnum.valueOf(4, PESSOA_JURIDICA.getCodigo()));
		assertEquals(NULA, SituacaoCadastralRFBEnum.valueOf(8, PESSOA_FISICA.getCodigo()));
		assertEquals(NULA, SituacaoCadastralRFBEnum.valueOf(1, PESSOA_JURIDICA.getCodigo()));
		assertEquals(PENDENTE_REGULARIZACAO, SituacaoCadastralRFBEnum.valueOf(4, PESSOA_FISICA.getCodigo()));
		assertEquals(REGULAR, SituacaoCadastralRFBEnum.valueOf(0, PESSOA_FISICA.getCodigo()));
		assertEquals(SUSPENSA, SituacaoCadastralRFBEnum.valueOf(2, PESSOA_FISICA.getCodigo()));
		assertEquals(SUSPENSA, SituacaoCadastralRFBEnum.valueOf(3, PESSOA_JURIDICA.getCodigo()));
	}

	/**
	 * O método Test value of cod rfb inexistente.
	 */
	@Test
	public void testValueOfCodRFBInexistente() {

		assertNull(SituacaoCadastralRFBEnum.valueOf(999, PESSOA_FISICA.getCodigo()));
		assertNull(SituacaoCadastralRFBEnum.valueOf(999, PESSOA_JURIDICA.getCodigo()));
		assertNull(SituacaoCadastralRFBEnum.valueOf(999, PESSOA_FISICA));
		assertNull(SituacaoCadastralRFBEnum.valueOf(999, PESSOA_JURIDICA));
	}

	/**
	 * O método Test value of tipo pessoa inexistente.
	 */
	@Test
	public void testValueOfTipoPessoaInexistente() {

		assertNull(SituacaoCadastralRFBEnum.valueOf(999, NumberUtils.SHORT_MINUS_ONE));
		assertNull(SituacaoCadastralRFBEnum.valueOf(999, NumberUtils.SHORT_MINUS_ONE));
		assertNull(SituacaoCadastralRFBEnum.valueOf(999, (TipoPessoaEnum) null));
		assertNull(SituacaoCadastralRFBEnum.valueOf(999, (TipoPessoaEnum) null));
	}

	/**
	 * O método Test value of codigo.
	 */
	@Test
	public void testValueOfCodigo() {

		assertEquals(ATIVA, SituacaoCadastralRFBEnum.valueOf(0));
		assertEquals(BAIXADA, SituacaoCadastralRFBEnum.valueOf(1));
		assertEquals(CANCELADA_ENCERRAMENTO_ESPOLIO, SituacaoCadastralRFBEnum.valueOf(2));
		assertEquals(CANCELADA_MULTIPLICIDADE, SituacaoCadastralRFBEnum.valueOf(3));
		assertEquals(CANCELADA_OBITO_SEM_ESPOLIO, SituacaoCadastralRFBEnum.valueOf(4));
		assertEquals(CANCELADA_OFICIO, SituacaoCadastralRFBEnum.valueOf(5));
		assertEquals(INAPTA, SituacaoCadastralRFBEnum.valueOf(6));
		assertEquals(NULA, SituacaoCadastralRFBEnum.valueOf(7));
		assertEquals(PENDENTE_REGULARIZACAO, SituacaoCadastralRFBEnum.valueOf(8));
		assertEquals(REGULAR, SituacaoCadastralRFBEnum.valueOf(9));
		assertEquals(SUSPENSA, SituacaoCadastralRFBEnum.valueOf(10));
	}
	
	/**
	 * O método Test value of codigo inexistente.
	 */
	@Test
	public void testValueOfCodigoInexistente() {
		
		assertNull(SituacaoCadastralRFBEnum.valueOf(-1));
		assertNull(SituacaoCadastralRFBEnum.valueOf(999));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Test
	public void testMetodosHerdados() {

		assertEquals(Integer.valueOf(11), Integer.valueOf(SituacaoCadastralRFBEnum.values().length));
		assertNotNull(SituacaoCadastralRFBEnum.valueOf("ATIVA"));
		assertNotNull(SituacaoCadastralRFBEnum.valueOf("BAIXADA"));
		assertNotNull(SituacaoCadastralRFBEnum.valueOf("CANCELADA_ENCERRAMENTO_ESPOLIO"));
		assertNotNull(SituacaoCadastralRFBEnum.valueOf("SUSPENSA"));
		assertNotNull(SituacaoCadastralRFBEnum.valueOf("CANCELADA_MULTIPLICIDADE"));
		assertNotNull(SituacaoCadastralRFBEnum.valueOf("CANCELADA_OBITO_SEM_ESPOLIO"));
		assertNotNull(SituacaoCadastralRFBEnum.valueOf("CANCELADA_OFICIO"));
		assertNotNull(SituacaoCadastralRFBEnum.valueOf("INAPTA"));
		assertNotNull(SituacaoCadastralRFBEnum.valueOf("NULA"));
		assertNotNull(SituacaoCadastralRFBEnum.valueOf("PENDENTE_REGULARIZACAO"));
		assertNotNull(SituacaoCadastralRFBEnum.valueOf("REGULAR"));
	}
}