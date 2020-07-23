package br.com.sicoob.capes.api.inclusao.testes;

import java.math.BigDecimal;
import java.util.Date;

import br.com.sicoob.capes.api.inclusao.negocio.dto.BemDTO;
import br.com.sicoob.capes.api.inclusao.negocio.dto.BemMovelDTO;

/**
 * Classe com os testes de bem móvel.
 * 
 * @author Bruno.Carneiro
 */
public class TesteBemMovel extends TesteBem {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected BemDTO instanciarDTO() {
		return new BemMovelDTO();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void preencherInformacoesEspecificasDTO(BemDTO dto) {
		super.preencherInformacoesEspecificasDTO(dto);

		BemMovelDTO bemMovelDTO = (BemMovelDTO) dto;

		bemMovelDTO.setCodigoTipoBem((short) 1);
		bemMovelDTO.setCodigoTipoChassi((short) 2);
		bemMovelDTO.setNumeroChassi("125553");
		bemMovelDTO.setRenavam("51561");
		bemMovelDTO.setPlaca("61");
		bemMovelDTO.setUf("DF");
		bemMovelDTO.setAnoFabricacaoAutomovel((short) 2012);
		bemMovelDTO.setAnoModeloAutomovel((short) 2012);
		bemMovelDTO.setInscricaoEmbarcacao("");
//		bemMovelDTO.setAnoConstrucaoEmbarcacao((short) 2012);
//		bemMovelDTO.setMatriculaAeronave("");
//		bemMovelDTO.setAnoConstrucaoAeronave((short) 2012);
		bemMovelDTO.setCodigoTipoCorAutomovel((short) 1);
		bemMovelDTO.setCodigoTipoEstadoConservacao((short) 1);
		bemMovelDTO.setValorCompraVenda(new BigDecimal(213));
		bemMovelDTO.setDataCompraVenda(new Date());
		bemMovelDTO.setValorAvaliacao(new BigDecimal(213));
		bemMovelDTO.setDataAvaliacao(new Date());
		bemMovelDTO.setIdPessoaAvaliador(979528);
		bemMovelDTO.setIdInstituicaoAvaliador(910);
	}
}