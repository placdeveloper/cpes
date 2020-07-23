/**
 * 
 */
package br.com.sicoob.capes.cadastro.anotacao;

import java.util.ArrayList;
import java.util.List;

import br.com.sicoob.capes.cadastro.anotacao.builder.DefinicoesDetalheAnotacaoBuilder;
import br.com.sicoob.capes.cadastro.anotacao.builder.bacen.DefinicoesDetalheAnotacaoBacenBuilder;
import br.com.sicoob.capes.cadastro.anotacao.builder.interna.DefinicoesDetalheAnotacaoInternaBuilder;
import br.com.sicoob.capes.cadastro.anotacao.builder.serasa.DefinicoesDetalheAcaoBuilder;
import br.com.sicoob.capes.cadastro.anotacao.builder.serasa.DefinicoesDetalheAcheiBuilder;
import br.com.sicoob.capes.cadastro.anotacao.builder.serasa.DefinicoesDetalheCCFBuilder;
import br.com.sicoob.capes.cadastro.anotacao.builder.serasa.DefinicoesDetalheConvemDevedoresBuilder;
import br.com.sicoob.capes.cadastro.anotacao.builder.serasa.DefinicoesDetalheFalenciaBuilder;
import br.com.sicoob.capes.cadastro.anotacao.builder.serasa.DefinicoesDetalheParticipanteFalidaBuilder;
import br.com.sicoob.capes.cadastro.anotacao.builder.serasa.DefinicoesDetalhePefinBuilder;
import br.com.sicoob.capes.cadastro.anotacao.builder.serasa.DefinicoesDetalheProtestoBuilder;
import br.com.sicoob.capes.cadastro.anotacao.builder.serasa.DefinicoesDetalheRefinBuilder;
import br.com.sicoob.capes.cadastro.anotacao.builder.sisbr.DefinicoesDetalheAnotacaoSisbrBuilder;
import br.com.sicoob.capes.cadastro.negocio.dto.DefinicaoDTO;
import br.com.sicoob.capes.comum.negocio.dto.DetalheAcaoDTO;
import br.com.sicoob.capes.comum.negocio.dto.DetalheAcheiDTO;
import br.com.sicoob.capes.comum.negocio.dto.DetalheAnotacaoBacenDTO;
import br.com.sicoob.capes.comum.negocio.dto.DetalheAnotacaoDTO;
import br.com.sicoob.capes.comum.negocio.dto.DetalheAnotacaoInternaDTO;
import br.com.sicoob.capes.comum.negocio.dto.DetalheAnotacaoSisbrDTO;
import br.com.sicoob.capes.comum.negocio.dto.DetalheCCFDTO;
import br.com.sicoob.capes.comum.negocio.dto.DetalheConvemDevedoresDTO;
import br.com.sicoob.capes.comum.negocio.dto.DetalheFalenciaDTO;
import br.com.sicoob.capes.comum.negocio.dto.DetalheParticipanteFalidaDTO;
import br.com.sicoob.capes.comum.negocio.dto.DetalhePefinDTO;
import br.com.sicoob.capes.comum.negocio.dto.DetalheProtestoDTO;
import br.com.sicoob.capes.comum.negocio.dto.DetalheRefinDTO;

/**
 * @author erico.junior
 *
 */
public class FabricaDefinicoesDetalheAnotacao {

	/** Instancia do Fabrica de definicoes do detalhe. */
	private static FabricaDefinicoesDetalheAnotacao fabrica;

	/**
	 * Retorna a fabrica de delegates a ser utilizada.
	 * 
	 * @return a fabrica de delegates a ser utilizada.
	 */
	public static FabricaDefinicoesDetalheAnotacao getInstance() {
		if (fabrica == null) {
			synchronized (FabricaDefinicoesDetalheAnotacao.class) {
				if (fabrica == null) {
					fabrica = new FabricaDefinicoesDetalheAnotacao();
				}
			}
		}
		return fabrica;
	}
	
	/**
	 * Obter definicoes.
	 *
	 * @param detalhe o valor de detalhe
	 * @return List
	 */
	public List<DefinicaoDTO> obterDefinicoes(DetalheAnotacaoDTO detalhe) {
		
		List<DefinicaoDTO> lista = new ArrayList<DefinicaoDTO>();

		if(detalhe != null) {
			DefinicoesDetalheAnotacaoBuilder builder = null;
			
			if(detalhe instanceof DetalheAcaoDTO) {
				builder = new DefinicoesDetalheAcaoBuilder();
			} else if(detalhe instanceof DetalheAcheiDTO) {
				builder = new DefinicoesDetalheAcheiBuilder();
			} else if(detalhe instanceof DetalheCCFDTO) {
				builder = new DefinicoesDetalheCCFBuilder();
			} else if(detalhe instanceof DetalheConvemDevedoresDTO) {
				builder = new DefinicoesDetalheConvemDevedoresBuilder();
			} else if(detalhe instanceof DetalheFalenciaDTO) {
				builder = new DefinicoesDetalheFalenciaBuilder();
			} else if(detalhe instanceof DetalheParticipanteFalidaDTO) {
				builder = new DefinicoesDetalheParticipanteFalidaBuilder();
			} else if(detalhe instanceof DetalhePefinDTO) {
				builder = new DefinicoesDetalhePefinBuilder();
			} else if(detalhe instanceof DetalheProtestoDTO) {
				builder = new DefinicoesDetalheProtestoBuilder();
			} else if(detalhe instanceof DetalheRefinDTO) {
				builder = new DefinicoesDetalheRefinBuilder();
			} else if(detalhe instanceof DetalheAnotacaoInternaDTO) {
				builder = new DefinicoesDetalheAnotacaoInternaBuilder();
			} else if(detalhe instanceof DetalheAnotacaoBacenDTO){
				builder = new DefinicoesDetalheAnotacaoBacenBuilder();
			} else if(detalhe instanceof DetalheAnotacaoSisbrDTO) {
				builder = new DefinicoesDetalheAnotacaoSisbrBuilder();
			}
			
			if(builder != null) {
				lista = builder.obterListaDefinicoes();; 
			}
		}
		
		return lista;
	}
}
