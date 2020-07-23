/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.anotacao.estrategia;

import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.comum.negocio.dto.DetalheAnotacaoDTO;
import br.com.sicoob.capes.comum.negocio.dto.DetalheAnotacaoInternaDTO;
import br.com.sicoob.capes.negocio.entidades.Anotacao;
import br.com.sicoob.capes.negocio.entidades.Instituicao;

/**
 * Estratégia para detalhar anotações de origem interma.
 * 
 * @author Erico.Junior
 */
public class EstrategiaDetalharAnotacaoInterna extends EstrategiaDetalharAnotacaoAbstract {

	/**
	 * {@inheritDoc}
	 */
	public List<DetalheAnotacaoDTO> obterDetalhesAnotacao(Anotacao anotacao)
			throws BancoobException {

		DetalheAnotacaoInternaDTO detalhe = new DetalheAnotacaoInternaDTO();
		detalhe.setDataOcorrencia(anotacao.getDataHoraOcorrencia());
		detalhe.setValorOcorrencia(anotacao.getValor());
		detalhe.setObservacao(anotacao.getDescObservacao());
		detalhe.setQuantidade(anotacao.getQuantidade());
		detalhe.setResponsavel(anotacao.getUsuarioInclusao());

		Instituicao instituicao = anotacao.getInstituicao();
		if (instituicao != null) {
			detalhe.setIdInstituicao(instituicao.getIdInstituicao());
			detalhe.setIdUnidadeInst(instituicao.getIdUnidadeInst());
			detalhe.setNumero(recuperarNumeroCooperativa(instituicao));
		}
		
		List<DetalheAnotacaoDTO> listaDetalhe = new ArrayList<DetalheAnotacaoDTO>();
		listaDetalhe.add(detalhe);
		return listaDetalhe;
	}
}
