package br.com.sicoob.capes.api.inclusao.testes;

import java.math.BigDecimal;

import br.com.sicoob.capes.api.inclusao.negocio.dto.FonteRendaDTO;

/**
 * A Classe TesteFonteRenda.
 *
 * @author bruno.carneiro
 */
public class TesteFonteRenda extends CAPESApiInclusaoTesteAbstrato<FonteRendaDTO> {

	/**
	 * Instancia um novo TesteFonteRenda.
	 */
	public TesteFonteRenda() {
		super("FonteRendaServico");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void preencherInformacoesEspecificasDTO(FonteRendaDTO dto) {
		dto.setCodigoTipoFonteRenda((short) 0);
		dto.setDescFonteRenda("Teste fonte renda");
		dto.setValorReceitaBrutaMensal(BigDecimal.TEN);
		dto.setIdPessoaEmpregador(3336);
	}

}