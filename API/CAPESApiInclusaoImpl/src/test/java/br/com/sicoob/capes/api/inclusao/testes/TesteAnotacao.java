package br.com.sicoob.capes.api.inclusao.testes;

import java.util.Date;

import br.com.sicoob.capes.api.inclusao.negocio.dto.AnotacaoDTO;

/**
 * A Classe TesteAnotacao.
 *
 * @author bruno.carneiro
 */
public class TesteAnotacao extends CAPESApiInclusaoTesteAbstrato<AnotacaoDTO> {

	/**
	 * Instancia um novo TesteAnotacao.
	 */
	public TesteAnotacao() {
		super("AnotacaoServico");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void preencherInformacoesEspecificasDTO(AnotacaoDTO dto) {
		dto.setCodigoTipoAnotacao(518);
		dto.setUsuarioInclusao(dto.getIdUsuarioAprovacao());
		dto.setDataHoraOcorrencia(new Date());
	}

}