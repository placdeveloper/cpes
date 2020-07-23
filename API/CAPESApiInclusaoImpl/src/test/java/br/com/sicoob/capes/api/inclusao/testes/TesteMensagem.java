package br.com.sicoob.capes.api.inclusao.testes;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import br.com.sicoob.capes.api.inclusao.negocio.dto.MensagemDTO;

/**
 * Classe com os testes de mensagens.
 * 
 * @author Bruno.Carneiro
 */
public class TesteMensagem extends CAPESApiInclusaoTesteAbstrato<MensagemDTO> {

	/**
	 * Construtor
	 */
	public TesteMensagem() {
		super("MensagemServico");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void preencherInformacoesEspecificasDTO(MensagemDTO dto) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			dto.setCodigoTipoMensagem((short) 1);
			dto.setCodigoTipoDestinoExibicao((short) 0);
			dto.setMensagem("TESTE API INCLUSÃO");
			dto.setDataValidade(sdf.parse("02/02/2022"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void preencherInformacoesAlteracaoDTO(MensagemDTO dto) {
		super.preencherInformacoesAlteracaoDTO(dto);
		dto.setId(10344396L);
		dto.setMensagem("TESTE API INCLUSÃO 2");
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void preencherInformacoesExclusaoDTO(MensagemDTO dto) {
		super.preencherInformacoesExclusaoDTO(dto);
		dto.setId(10344412L);
	}

}