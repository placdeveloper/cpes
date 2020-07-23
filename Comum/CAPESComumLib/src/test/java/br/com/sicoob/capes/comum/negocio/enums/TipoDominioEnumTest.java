package br.com.sicoob.capes.comum.negocio.enums;

import org.junit.Assert;
import org.junit.Test;


/**
 * A Classe TipoDominioEnumTest.
 */
public class TipoDominioEnumTest extends AbstractEnumTest {

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Test
	public void testMetodosHerdados() {

		Assert.assertEquals(34, TipoDominioEnum.values().length);
		Assert.assertEquals(TipoDominioEnum.ATIVIDADE_ECONOMICA, TipoDominioEnum.valueOf("ATIVIDADE_ECONOMICA"));
		Assert.assertEquals(TipoDominioEnum.CATEGORIA_PRODUTOR, TipoDominioEnum.valueOf("CATEGORIA_PRODUTOR"));
		Assert.assertEquals(TipoDominioEnum.CONSELHO_REGIONAL, TipoDominioEnum.valueOf("CONSELHO_REGIONAL"));
		Assert.assertEquals(TipoDominioEnum.ESTADO_CIVIL, TipoDominioEnum.valueOf("ESTADO_CIVIL"));
		Assert.assertEquals(TipoDominioEnum.FINALIDADE_EMPREENDIMENTO, TipoDominioEnum.valueOf("FINALIDADE_EMPREENDIMENTO"));
		Assert.assertEquals(TipoDominioEnum.FUNCAO, TipoDominioEnum.valueOf("FUNCAO"));
		Assert.assertEquals(TipoDominioEnum.GRAU_INSTRUCAO, TipoDominioEnum.valueOf("GRAU_INSTRUCAO"));
		Assert.assertEquals(TipoDominioEnum.NACIONALIDADE, TipoDominioEnum.valueOf("NACIONALIDADE"));
		Assert.assertEquals(TipoDominioEnum.ORGAO_EMISSAO_CERTIDAO, TipoDominioEnum.valueOf("ORGAO_EMISSAO_CERTIDAO"));
		Assert.assertEquals(TipoDominioEnum.REGIME_CASAMENTO, TipoDominioEnum.valueOf("REGIME_CASAMENTO"));
		Assert.assertEquals(TipoDominioEnum.SUB_TIPO_CERTIDAO, TipoDominioEnum.valueOf("SUB_TIPO_CERTIDAO"));
		Assert.assertEquals(TipoDominioEnum.TIPO_ABRANGENCIA_CERTIDAO, TipoDominioEnum.valueOf("TIPO_ABRANGENCIA_CERTIDAO"));
		Assert.assertEquals(TipoDominioEnum.TIPO_AFASTAMENTO, TipoDominioEnum.valueOf("TIPO_AFASTAMENTO"));
		Assert.assertEquals(TipoDominioEnum.TIPO_BAIXA, TipoDominioEnum.valueOf("TIPO_BAIXA"));
		Assert.assertEquals(TipoDominioEnum.TIPO_BEM, TipoDominioEnum.valueOf("TIPO_BEM"));
		Assert.assertEquals(TipoDominioEnum.TIPO_CAPTURA, TipoDominioEnum.valueOf("TIPO_CAPTURA"));
		Assert.assertEquals(TipoDominioEnum.TIPO_CERTIDAO, TipoDominioEnum.valueOf("TIPO_CERTIDAO"));
		Assert.assertEquals(TipoDominioEnum.TIPO_DOCUMENTO_IDENTIFICACAO, TipoDominioEnum.valueOf("TIPO_DOCUMENTO_IDENTIFICACAO"));
		Assert.assertEquals(TipoDominioEnum.TIPO_EMAIL, TipoDominioEnum.valueOf("TIPO_EMAIL"));
		Assert.assertEquals(TipoDominioEnum.TIPO_EMPRESA, TipoDominioEnum.valueOf("TIPO_EMPRESA"));
		Assert.assertEquals(TipoDominioEnum.TIPO_ENDERECO, TipoDominioEnum.valueOf("TIPO_ENDERECO"));
		Assert.assertEquals(TipoDominioEnum.TIPO_FONTE_RENDA, TipoDominioEnum.valueOf("TIPO_FONTE_RENDA"));
		Assert.assertEquals(TipoDominioEnum.TIPO_FORMA_CONSTITUICAO, TipoDominioEnum.valueOf("TIPO_FORMA_CONSTITUICAO"));
		Assert.assertEquals(TipoDominioEnum.TIPO_FUNCIONARIO, TipoDominioEnum.valueOf("TIPO_FUNCIONARIO"));
		Assert.assertEquals(TipoDominioEnum.TIPO_OBJETO_CERTIDAO, TipoDominioEnum.valueOf("TIPO_OBJETO_CERTIDAO"));
		Assert.assertEquals(TipoDominioEnum.TIPO_PESSOA, TipoDominioEnum.valueOf("TIPO_PESSOA"));
		Assert.assertEquals(TipoDominioEnum.TIPO_PODER_RELACIONAMENTO, TipoDominioEnum.valueOf("TIPO_PODER_RELACIONAMENTO"));
		Assert.assertEquals(TipoDominioEnum.TIPO_POSSE_BEM, TipoDominioEnum.valueOf("TIPO_POSSE_BEM"));
		Assert.assertEquals(TipoDominioEnum.TIPO_PRAZO_CERTIDAO, TipoDominioEnum.valueOf("TIPO_PRAZO_CERTIDAO"));
		Assert.assertEquals(TipoDominioEnum.TIPO_REFERENCIA, TipoDominioEnum.valueOf("TIPO_REFERENCIA"));
		Assert.assertEquals(TipoDominioEnum.TIPO_RELACIONAMENTO, TipoDominioEnum.valueOf("TIPO_RELACIONAMENTO"));
		Assert.assertEquals(TipoDominioEnum.TIPO_TELEFONE, TipoDominioEnum.valueOf("TIPO_TELEFONE"));
		Assert.assertEquals(TipoDominioEnum.UNIDADE_MEDIDA, TipoDominioEnum.valueOf("UNIDADE_MEDIDA"));
		Assert.assertEquals(TipoDominioEnum.VINCULO_EMPREGATICIO, TipoDominioEnum.valueOf("VINCULO_EMPREGATICIO"));
	}


}
