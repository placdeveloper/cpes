package br.com.sicoob.capes.api.inclusao.testes;

import br.com.sicoob.capes.api.inclusao.negocio.dto.TelefoneDTO;
import br.com.sicoob.capes.comum.negocio.enums.TipoTelefoneEnum;

/**
 * A Classe TesteTelefone.
 *
 * @author bruno.carneiro
 */
public class TesteTelefone extends CAPESApiInclusaoTesteAbstrato<TelefoneDTO> {

	/**
	 * Instancia um novo TesteTelefone.
	 */
	public TesteTelefone() {
		super("TelefoneServico");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void preencherInformacoesEspecificasDTO(TelefoneDTO dto) {
		dto.setCodigoTipoTelefone(TipoTelefoneEnum.OUTROS.getCodigo());
		dto.setDdd("61");
		dto.setObservacao("Teste");
		dto.setRamal("999");
		dto.setTelefone("9999999999");
	}

}