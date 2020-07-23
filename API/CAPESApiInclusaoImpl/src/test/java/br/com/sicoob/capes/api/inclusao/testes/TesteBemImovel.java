package br.com.sicoob.capes.api.inclusao.testes;

import java.math.BigDecimal;
import java.util.Date;

import br.com.sicoob.capes.api.inclusao.negocio.dto.BemDTO;
import br.com.sicoob.capes.api.inclusao.negocio.dto.BemImovelDTO;

/**
 * Classe com os testes de bem imóvel.
 * 
 * @author Bruno.Carneiro
 */
public class TesteBemImovel extends TesteBem {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected BemDTO instanciarDTO() {
		return new BemImovelDTO();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void preencherInformacoesEspecificasDTO(BemDTO dto) {
		super.preencherInformacoesEspecificasDTO(dto);
		
		BemImovelDTO bemImovelDTO = (BemImovelDTO) dto;
		
		bemImovelDTO.setDescricao("Teste imóvel");
		bemImovelDTO.setNumero("123");
		bemImovelDTO.setComplemento("teste 11");
		bemImovelDTO.setIdLogradouro(1);
		bemImovelDTO.setDescricaoRoteiro("teste  a");
		bemImovelDTO.setLatitude(new BigDecimal(12));
		bemImovelDTO.setLongitude(new BigDecimal(12));
		bemImovelDTO.setAreaTotal(new BigDecimal(122));
		bemImovelDTO.setIdPessoaCartorio(123);
		bemImovelDTO.setIdInstituicaoPessoaCartorio(910);
		bemImovelDTO.setMatricula("51156516");
		bemImovelDTO.setNirf("41551");
		bemImovelDTO.setIncra("879546");

		bemImovelDTO.setCodigoTipoBem((short) 101);
		bemImovelDTO.setCodigoTipoLocalizacaoBem((short) 1);
		bemImovelDTO.setCodigoUnidadeMedida((short) 23);
		bemImovelDTO.setCodigoTipoUsoBem((short) 1);

		bemImovelDTO.setCodigoTipoImplantacaoBemImovel((short) 1);
		bemImovelDTO.setCodigoTipoEstadoConservacao((short) 1);
		bemImovelDTO.setCodigoTipoPadraoAcabamentoBemImovel((short) 1);
		bemImovelDTO.setCodigoTipoServicoCondominialBemImovel((short) 1);

		bemImovelDTO.setAreaPrivativa(new BigDecimal(121));
		bemImovelDTO.setAreaTerreno(new BigDecimal(12));
		bemImovelDTO.setAreaTestada(new BigDecimal(12));
		bemImovelDTO.setQuantidadeDormitorios((short) 12);
		bemImovelDTO.setQuantidadeVagasGaragem((short) 12);

		bemImovelDTO.setBenfeitoria("benfeito");
		bemImovelDTO.setValorBenfeitoria(new BigDecimal(112));

		bemImovelDTO.setValorCompraVenda(new BigDecimal(12));
		bemImovelDTO.setDataCompraVenda(new Date());
		bemImovelDTO.setValorAvaliacao(new BigDecimal(12));
		bemImovelDTO.setDataAvaliacao(new Date());
		bemImovelDTO.setIdPessoaAvaliador(5665252);
		bemImovelDTO.setIdInstituicaoAvaliador(910);
	}
}