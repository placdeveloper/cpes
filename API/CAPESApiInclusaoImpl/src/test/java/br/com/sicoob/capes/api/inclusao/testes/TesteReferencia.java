package br.com.sicoob.capes.api.inclusao.testes;

import br.com.sicoob.capes.api.inclusao.negocio.dto.ReferenciaDTO;

/**
 * A Classe TesteReferencia.
 *
 * @author bruno.carneiro
 */
public class TesteReferencia extends CAPESApiInclusaoTesteAbstrato<ReferenciaDTO> {

	/**
	 * Instancia um novo TesteReferencia.
	 */
	public TesteReferencia() {
		super("ReferenciaServico");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void preencherInformacoesEspecificasDTO(ReferenciaDTO dto) {
		dto.setCodigoTipoReferencia((short) 2);
		dto.setObservacao("Teste referência");
		dto.setDdd((short) 61);
		dto.setTelefone("999999999");
	}

}