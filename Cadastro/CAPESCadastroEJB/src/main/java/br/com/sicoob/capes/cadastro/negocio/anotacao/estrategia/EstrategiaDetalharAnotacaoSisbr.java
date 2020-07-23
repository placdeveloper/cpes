/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.anotacao.estrategia;

import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.comum.negocio.dto.DetalheAnotacaoDTO;
import br.com.sicoob.capes.comum.negocio.dto.DetalheAnotacaoSisbrDTO;
import br.com.sicoob.capes.comum.negocio.vo.ModalidadeProdutoVO;
import br.com.sicoob.capes.comum.negocio.vo.ProdutoVO;
import br.com.sicoob.capes.integracao.negocio.delegates.ADMIntegracaoDelegate;
import br.com.sicoob.capes.integracao.negocio.delegates.CAPESIntegracaoFabricaDelegates;
import br.com.sicoob.capes.negocio.entidades.Anotacao;
import br.com.sicoob.capes.negocio.entidades.AnotacaoSisbr;
import br.com.sicoob.capes.negocio.entidades.Instituicao;

/**
 * Estrategia utilizada para detalhar anotações de origem SISBR.
 * 
 * @author Erico.Junior
 */
public class EstrategiaDetalharAnotacaoSisbr extends EstrategiaDetalharAnotacaoAbstract {
	
	/** O atributo admIntegracaoDelegate. */
	private transient ADMIntegracaoDelegate admIntegracaoDelegate = CAPESIntegracaoFabricaDelegates.getInstance().criarADMIntegracaoDelegate();
	
	/**
	 * {@inheritDoc}
	 */
	public List<DetalheAnotacaoDTO> obterDetalhesAnotacao(Anotacao anotacao)
			throws BancoobException {

		DetalheAnotacaoSisbrDTO detalhe = new DetalheAnotacaoSisbrDTO();
		detalhe.setDataOcorrencia(anotacao.getDataHoraOcorrencia());

		if(anotacao instanceof AnotacaoSisbr) {
			
			AnotacaoSisbr anotacaoSisbr = (AnotacaoSisbr) anotacao;
			Instituicao instituicao = anotacaoSisbr.getInstituicaoModalidadeProduto();

			detalhe.setNumeroCooperativa(super.recuperarNumeroCooperativa(instituicao));
			detalhe.setContrato(anotacaoSisbr.getNumeroContrato());
			detalhe.setDataVencimento(anotacaoSisbr.getDataVencimento());

			ProdutoVO produto = recuperarProduto(anotacaoSisbr.getIdProduto());
			if(produto != null) {
				detalhe.setDescricaoProduto(produto.getDescricaoProduto());
				ModalidadeProdutoVO modalidade = recuperarModalidadeProduto(
						anotacaoSisbr.getIdProduto(), anotacaoSisbr.getIdModalidadeProduto(), instituicao);
				
				if(modalidade != null) {
					detalhe.setDescricaoModalidade(modalidade.getDescricaoModalidadeProduto());
				}
			}
		}

		List<DetalheAnotacaoDTO> listaDetalhe = new ArrayList<DetalheAnotacaoDTO>();
		listaDetalhe.add(detalhe);
		return listaDetalhe;
	}

	/**
	 * Recuperar modalidade produto.
	 *
	 * @param idProduto o valor de id produto
	 * @param idModalidade o valor de id modalidade
	 * @param instituicao o valor de instituicao
	 * @return ModalidadeProdutoVO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private ModalidadeProdutoVO recuperarModalidadeProduto(Integer idProduto, Integer idModalidade, Instituicao instituicao) throws BancoobException {
		return admIntegracaoDelegate.obterModalidadeProduto(idProduto, idModalidade, instituicao.getIdInstituicao(), instituicao.getIdUnidadeInst());
	}
	
	/**
	 * Recuperar produto.
	 *
	 * @param idProduto o valor de id produto
	 * @return ProdutoVO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private ProdutoVO recuperarProduto(Integer idProduto) throws BancoobException{
		return admIntegracaoDelegate.obterProduto(idProduto);
	}
}
