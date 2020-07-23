/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.anotacao.estrategia.EstrategiaDetalharAnotacao;
import br.com.sicoob.capes.cadastro.negocio.anotacao.estrategia.EstrategiaDetalharAnotacaoInterna;
import br.com.sicoob.capes.cadastro.negocio.anotacao.estrategia.EstrategiaDetalharAnotacaoSisbr;
import br.com.sicoob.capes.cadastro.negocio.delegates.AnotacaoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.excecao.AnotacaoSemDetalheException;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.DetalharAnotacaoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.DetalharAnotacaoServicoRemote;
import br.com.sicoob.capes.comum.negocio.dto.DetalheAnotacaoDTO;
import br.com.sicoob.capes.comum.negocio.enums.OrigemInformacaoEnum;
import br.com.sicoob.capes.negocio.entidades.Anotacao;
import br.com.sicoob.capes.negocio.entidades.OrigemInformacao;

/**
 * Serviço utilizado para detalhar uma anotação
 * 
 * @author Erico.Junior
 * 
 */
@Stateless
@Local( { DetalharAnotacaoServicoLocal.class })
@Remote( { DetalharAnotacaoServicoRemote.class })
public class DetalharAnotacaoServicoEJB extends CAPESCadastroServicoEJB implements
		DetalharAnotacaoServicoRemote, DetalharAnotacaoServicoLocal {

	/** O atributo anotacaoDelegate. */
	private transient final AnotacaoDelegate anotacaoDelegate = 
			CAPESCadastroFabricaDelegates.getInstance().criarAnotacaoDelegate();
	
	/**
	 * {@inheritDoc}
	 */
	public List<DetalheAnotacaoDTO> detalharAnotacao(Anotacao anotacao) throws BancoobException {

		Anotacao existente = obterAnotacao(anotacao.getIdAnotacao());
		EstrategiaDetalharAnotacao estrategia = null;
		
		if(isOrigemInformacaoIgual(existente, OrigemInformacaoEnum.INTERNA) || isOrigemInformacaoIgual(existente, OrigemInformacaoEnum.AML_CONSULTING)) {
			estrategia = new EstrategiaDetalharAnotacaoInterna();
		} else if(isOrigemInformacaoIgual(existente, OrigemInformacaoEnum.SISBR)) {
			estrategia = new EstrategiaDetalharAnotacaoSisbr();
		}else {
			throw new AnotacaoSemDetalheException();
		}
		
		List<DetalheAnotacaoDTO> lista = estrategia.obterDetalhesAnotacao(existente);
		if(lista == null || lista.isEmpty()) {
			throw new AnotacaoSemDetalheException();
		}
		
		return lista; 
	}

	/**
	 * Verifica se a origem da informação é igual a origem informada no enum.
	 * @param anotacao A anotação a ser verificada.
	 * @param origemEnum O enum da origem da informação para verificação.
	 * @return boolean indicando se a origem da informação é igual a origem informada no enum.
	 */
	private boolean isOrigemInformacaoIgual(Anotacao anotacao, OrigemInformacaoEnum origemEnum) {
		
		OrigemInformacao origem = anotacao.getOrigemInformacao();
		Short idOrigem = origem.getId();

		return origemEnum.getIdOrigemInformacao().equals(idOrigem); 
	}

	/**
	 * Recupera a anotação a partir do identificador informado.
	 * @param idAnotacao O identificador da anotação.
	 * @return A anotação.
	 * @throws BancoobException Caso ocorra alguma exceção na consulta.
	 */
	private Anotacao obterAnotacao(Long idAnotacao) throws BancoobException {
		return anotacaoDelegate.obter(idAnotacao);
	}

}
