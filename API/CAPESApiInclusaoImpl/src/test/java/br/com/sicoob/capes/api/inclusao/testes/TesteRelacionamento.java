package br.com.sicoob.capes.api.inclusao.testes;

import br.com.sicoob.capes.api.inclusao.negocio.dto.RelacionamentoDTO;

/**
 * Teste dos serviços de relacionamento
 * 
 * @author Bruno.Carneiro
 */
public class TesteRelacionamento extends CAPESApiInclusaoTesteAbstrato<RelacionamentoDTO> {

	/**
	 * Construtor
	 */
	public TesteRelacionamento() {
		super("RelacionamentoServico");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void preencherInformacoesEspecificasDTO(RelacionamentoDTO dto) {
		dto.setCodigoTipoRelacionamento((short) 2);
		dto.setIdPessoaRelacionada(755561);
	}

}